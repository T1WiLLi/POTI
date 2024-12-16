package poti.project.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import jakarta.servlet.http.HttpServletRequest;
import poti.project.server.components.RateLimitingFilter;

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
                                .addFilterBefore(rateLimitingFilter, UsernamePasswordAuthenticationFilter.class) // Add
                                                                                                                 // rate-limiting
                                                                                                                 // filter
                                                                                                                 // before
                                                                                                                 // authentication
                                .csrf(csrf -> csrf
                                                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/api/**").access((authentication, context) -> {
                                                        HttpServletRequest request = context.getRequest();
                                                        return new AuthorizationDecision(isRequestFromServer(request));
                                                })
                                                .requestMatchers("/**").permitAll() // Only apply rate limiting to /**
                                                                                    // paths
                                                .anyRequest().denyAll())
                                .headers(headers -> headers
                                                .frameOptions(Customizer.withDefaults())
                                                .httpStrictTransportSecurity(hsts -> hsts
                                                                .includeSubDomains(true)
                                                                .maxAgeInSeconds(31536000))
                                                .contentSecurityPolicy(csp -> csp
                                                                .policyDirectives(
                                                                                "default-src 'self'; script-src 'self'")));
                return http.build();
        }

        private boolean isRequestFromServer(HttpServletRequest request) {
                String remoteAddress = request.getRemoteAddr();
                String localAddress = request.getLocalAddr();
                return remoteAddress.equals(localAddress);
        }
}
