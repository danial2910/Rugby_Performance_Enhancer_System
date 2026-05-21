package com.utm.rugbyplanner.config;

import com.utm.rugbyplanner.model.User;
import com.utm.rugbyplanner.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * DataInitializer — seeds a default ADMIN account on application startup.
 *
 * Only creates the admin if no user with the configured admin username exists.
 * This ensures idempotent seeding: safe to restart the app without duplicates.
 *
 * Credentials are configured in application.properties under:
 *   app.admin.username, app.admin.email, app.admin.password, app.admin.fullName
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Value("${app.admin.fullName}")
    private String adminFullName;

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.existsByUsername(adminUsername)) {
            log.info("DataInitializer: Admin account '{}' already exists — skipping seed.", adminUsername);
            return;
        }

        User admin = User.builder()
                .username(adminUsername)
                .email(adminEmail)
                .password(passwordEncoder.encode(adminPassword))
                .fullName(adminFullName)
                .userRole(User.UserRole.ADMIN)
                .enabled(true)
                .build();

        userRepository.save(admin);
        log.info("DataInitializer: Admin account '{}' seeded successfully.", adminUsername);
    }
}
