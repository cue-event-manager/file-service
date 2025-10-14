package cue.edu.co.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


@Configuration
public class AuditConfig {

    @Bean
    public AuditorAware<Long> auditorAware() {
        return () -> {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            if (auth == null || !auth.isAuthenticated()) {
                return Optional.of(0L);
            }

            Object principal = auth.getPrincipal();

            if (principal instanceof String userIdStr) {
                try {
                    return Optional.of(Long.parseLong(userIdStr));
                } catch (NumberFormatException e) {
                    return Optional.of(0L);
                }
            }

            try {
                return Optional.of(Long.parseLong(auth.getName()));
            } catch (NumberFormatException e) {
                return Optional.of(0L);
            }
        };
    }
}