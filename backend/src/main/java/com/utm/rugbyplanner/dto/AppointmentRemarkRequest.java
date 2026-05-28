package com.utm.rugbyplanner.dto;

import lombok.Data;

/**
 * AppointmentRemarkRequest — optional remarks when trainer
 * approves or rejects an appointment (UC012).
 */
@Data
public class AppointmentRemarkRequest {
    private String trainerRemarks;
}
