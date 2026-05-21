package com.utm.rugbyplanner.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * UC003: Update Profile — User personal info fields.
 * Now includes profilePicture as a base64 data URL string.
 */
@Data
public class UpdateUserRequest {

    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    private String email;

    @Pattern(
        regexp = "^(\\+?[0-9]{10,15})?$",
        message = "Please enter a valid phone number"
    )
    private String phoneNumber;

    /** UTM matrix number — optional, e.g. "A22EC0001" */
    @Size(max = 20, message = "Matrix number cannot exceed 20 characters")
    private String matrixNumber;

    /** Malaysian IC number — optional, e.g. "020101-10-1234" */
    @Pattern(
        regexp = "^(\\d{6}-\\d{2}-\\d{4})?$",
        message = "IC number must be in the format XXXXXX-XX-XXXX"
    )
    private String icNumber;

    /**
     * Base64 data URL of the profile picture.
     * Format: "data:image/jpeg;base64,/9j/4AAQSkZJRgAB..."
     * Null or empty means no change / remove picture.
     * Frontend must compress to < 200 KB before sending.
     */
    private String profilePicture;
}