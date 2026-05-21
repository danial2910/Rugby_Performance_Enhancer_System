package com.utm.rugbyplanner.dto;

import com.utm.rugbyplanner.model.User;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Admin-only response DTO — general user information.
 * Returned by GET /api/admin/users.
 *
 * Does NOT expose passwords, JWTs, or plan data.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserResponse {

    private String        userId;
    private String        username;
    private String        fullName;
    private String        email;
    private String        phoneNumber;
    private User.UserRole userRole;
    private boolean       enabled;
    private LocalDateTime createdAt;
}
