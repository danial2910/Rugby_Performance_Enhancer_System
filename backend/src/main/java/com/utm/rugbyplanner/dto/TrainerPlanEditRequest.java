package com.utm.rugbyplanner.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * TrainerPlanEditRequest — payload for trainer PUT /api/trainer/workout-plans/{id}
 *                                               PUT /api/trainer/meal-plans/{id}
 *
 * The trainer MUST provide a reason note whenever modifying an athlete's plan.
 */
@Data
public class TrainerPlanEditRequest {

    @NotBlank(message = "Plan name is required.")
    @Size(max = 120, message = "Plan name must be 120 characters or fewer.")
    private String planName;

    @NotBlank(message = "Plan content is required.")
    private String generatedPlan;

    /**
     * Mandatory explanation for why the trainer is changing the plan.
     * Stored in the plan document and displayed to the athlete.
     */
    @NotBlank(message = "Please provide a note explaining why you are modifying this plan.")
    @Size(max = 1000, message = "Note must be 1000 characters or fewer.")
    private String trainerNote;
}
