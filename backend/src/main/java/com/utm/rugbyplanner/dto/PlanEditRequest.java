package com.utm.rugbyplanner.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * PlanEditRequest — payload for PUT /api/workout/plans/{id}
 *                                    PUT /api/meal/plans/{id}
 *
 * Allows the user to rename a plan and/or edit the generated text.
 */
@Data
public class PlanEditRequest {

    @NotBlank(message = "Plan name is required.")
    @Size(max = 120, message = "Plan name must be 120 characters or fewer.")
    private String planName;

    /**
     * The full plan text (markdown). User may edit the AI-generated content
     * to customise exercises or meals to their situation.
     */
    @NotBlank(message = "Plan content is required.")
    private String generatedPlan;
}
