package com.utm.rugbyplanner.service;

import com.utm.rugbyplanner.dto.AdminUserResponse;
import com.utm.rugbyplanner.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * AdminService — business logic for admin-only operations.
 *
 * Currently supports:
 *   - getAllUsers(): returns general info for every registered user
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    /**
     * Returns a list of all users with general information only.
     * Excludes passwords and plan data.
     */
    public List<AdminUserResponse> getAllUsers() {
        log.debug("Admin: fetching all users");
        return userRepository.findAll().stream()
                .map(user -> AdminUserResponse.builder()
                        .userId(user.getId())
                        .username(user.getUsername())
                        .fullName(user.getFullName())
                        .email(user.getEmail())
                        .phoneNumber(user.getPhoneNumber())
                        .userRole(user.getUserRole())
                        .enabled(user.isEnabled())
                        .createdAt(user.getCreatedAt())
                        .build()
                )
                .collect(Collectors.toList());
    }
}
