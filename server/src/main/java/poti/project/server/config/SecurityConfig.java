package poti.project.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll()
                        .requestMatchers("/api/**").access((authentication, context) -> {
                            HttpServletRequest request = context.getRequest();
                            return new AuthorizationDecision(isRequestFromServer(request));
                        })
                        .anyRequest().denyAll());
        return http.build();
    }

    private boolean isRequestFromServer(HttpServletRequest request) {
        String remoteAddress = request.getRemoteAddr();
        String localAddress = request.getLocalAddr();
        return remoteAddress.equals(localAddress);
    }
}
