package com.utm.rugbyplanner.controller;

import com.utm.rugbyplanner.dto.AdminUserResponse;
import com.utm.rugbyplanner.dto.ApiResponse;
import com.utm.rugbyplanner.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AdminController — admin-only REST endpoints.
 *
 * All endpoints require a valid JWT AND the ADMIN role.
 * Role check is enforced via @PreAuthorize("hasRole('ADMIN')").
 *
 * ┌──────────────────────────────────────────────────────────────┐
 * │  Method  │  URL                  │  Purpose                 │
 * ├──────────┼───────────────────────┼──────────────────────────┤
 * │  GET     │  /api/admin/users     │  List all users          │
 * └──────────────────────────────────────────────────────────────┘
 */
@Slf4j
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    /**
     * GET /api/admin/users
     *
     * Returns general information for all registered users.
     * Accessible only to users with role ADMIN.
     *
     * 200 OK  → { success: true, data: [ { userId, username, fullName, email, phoneNumber, userRole, enabled, createdAt }, … ] }
     * 403     → Forbidden — caller is not ADMIN
     */
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<AdminUserResponse>>> getAllUsers() {
        log.debug("Admin: GET /api/admin/users");
        List<AdminUserResponse> users = adminService.getAllUsers();
        return ResponseEntity.ok(
                ApiResponse.success("Users retrieved successfully.", users)
        );
    }
}
