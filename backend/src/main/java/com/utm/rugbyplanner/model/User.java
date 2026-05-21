package com.utm.rugbyplanner.model;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * User — MongoDB collection: "users"
 *
 * profilePicture → base64 data URL (e.g. "data:image/jpeg;base64,...")
 *                  stored directly in the document.
 *                  Max recommended size: 200 KB after compression.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    @Indexed(unique = true)
    private String email;

    private String password;

    private String fullName;

    private String phoneNumber;

    /** UTM matrix number, e.g. "A22EC0001" */
    private String matrixNumber;

    /** Malaysian IC number, e.g. "020101-10-1234" */
    private String icNumber;

    /** Base64 data URL of the user's profile picture */
    private String profilePicture;

    private UserRole userRole;

    @Builder.Default
    private boolean enabled = true;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public enum UserRole {
        ATHLETE,
        TRAINER,
        ADMIN
    }
}