package com.utm.rugbyplanner.dto;

import com.utm.rugbyplanner.model.Trainer;
import lombok.*;

import java.util.List;

/**
 * TrainerSummaryResponse — lightweight trainer card shown on the
 * appointment booking form so the athlete can choose who to book with.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainerSummaryResponse {

    private String userId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String profilePicture;
    private String expertise;
    private String experience;
    private List<Trainer.AvailabilitySlot> availabilitySlots;
}
