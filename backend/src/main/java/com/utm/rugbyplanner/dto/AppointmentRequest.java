package com.utm.rugbyplanner.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * AppointmentRequest — payload for POST /api/appointments (UC008)
 */
@Data
public class AppointmentRequest {

    @NotBlank(message = "Please select a service type.")
    private String serviceType;

    @NotBlank(message = "Please select a trainer.")
    private String trainerId;

    @NotBlank(message = "Please select a date.")
    private String date;

    @NotBlank(message = "Please select a time.")
    private String time;

    @NotNull(message = "Please select a duration.")
    private Integer duration;

    @NotBlank(message = "Please select a location.")
    private String location;

    @NotBlank(message = "Please describe the purpose of your appointment.")
    private String purpose;

    private String specialRequirements;
}
