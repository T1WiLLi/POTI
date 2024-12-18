package poti.project.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import java.util.function.Supplier;

import jakarta.servlet.http.HttpServletRequest;
import poti.project.server.components.RateLimitingFilter;

/**
 * This is the web security configuration for the application.
 * It is used to configure the security settings for the application. It
 * implements different security settings such as Anti-DDos , CSRF protection,
 * and authentication.
 * 
 * @author William Beaudin
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

        private final RateLimitingFilter rateLimitingFilter;

        public SecurityConfig(RateLimitingFilter rateLimitingFilter) {
                this.rateLimitingFilter = rateLimitingFilter;
        }

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .addFilterBefore(rateLimitingFilter, UsernamePasswordAuthenticationFilter.class)
                                .csrf(csrf -> csrf
                                                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                                                .ignoringRequestMatchers("/api/**", "/actuator/**"))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/api/**").access(this::checkServerRequest)
                                                .requestMatchers("/actuator/**").permitAll()
                                                .requestMatchers("/**").permitAll()
                                                .anyRequest().denyAll())
                                .headers(headers -> headers
                                                .xssProtection(xss -> xss.headerValue(
                                                                XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK))
                                                .frameOptions(Customizer.withDefaults())
                                                .httpStrictTransportSecurity(hsts -> hsts
                                                                .includeSubDomains(true)
                                                                .maxAgeInSeconds(31536000)
                                                                .preload(true))
                                                .contentSecurityPolicy(csp -> csp
                                                                .policyDirectives(
                                                                                "default-src 'self'; script-src 'self'")));
                return http.build();
        }

        private AuthorizationDecision checkServerRequest(Supplier<Authentication> authentication,
                        RequestAuthorizationContext context) {
                HttpServletRequest request = context.getRequest();
                String remoteAddress = request.getRemoteAddr();
                String localAddress = request.getLocalAddr();
                return new AuthorizationDecision(remoteAddress.equals(localAddress));
        }
}