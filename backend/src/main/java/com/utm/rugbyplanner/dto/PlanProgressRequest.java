package com.utm.rugbyplanner.dto;

import lombok.Data;

import java.util.List;

/**
 * PlanProgressRequest — payload for PATCH /api/workout/plans/{id}/progress
 *                                         PATCH /api/meal/plans/{id}/progress
 *
 * Replaces the plan's completedItems list with the provided list.
 * The frontend sends the full updated list on every checkbox toggle.
 */
@Data
public class PlanProgressRequest {

    /**
     * Full list of completed item keys.
     * Workout example: ["Day 1", "Day 3"]
     * Meal example:    ["Day 1 – Breakfast", "Day 1 – Lunch", "Day 2 – Breakfast"]
     */
    private List<String> completedItems;
}
