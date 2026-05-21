package com.utm.rugbyplanner.controller;

import com.utm.rugbyplanner.dto.ApiResponse;
import com.utm.rugbyplanner.dto.MealPlanResponse;
import com.utm.rugbyplanner.dto.TrainerAthleteResponse;
import com.utm.rugbyplanner.dto.TrainerPlanEditRequest;
import com.utm.rugbyplanner.dto.WorkoutPlanResponse;
import com.utm.rugbyplanner.service.TrainerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TrainerController — REST endpoints for trainer plan management.
 *
 * All routes require TRAINER role.
 *
 * GET  /api/trainer/athletes                           → list all athletes
 * GET  /api/trainer/athletes/{userId}/workout-plans    → athlete's workout plans
 * GET  /api/trainer/athletes/{userId}/meal-plans       → athlete's meal plans
 * PUT  /api/trainer/workout-plans/{id}                 → edit a workout plan (+ note)
 * PUT  /api/trainer/meal-plans/{id}                    → edit a meal plan (+ note)
 */
@RestController
@RequestMapping("/api/trainer")
@PreAuthorize("hasRole('TRAINER')")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;

    // ── Athlete list ──────────────────────────────────────────────────────────

    @GetMapping("/athletes")
    public ResponseEntity<ApiResponse<List<TrainerAthleteResponse>>> getAllAthletes() {
        return ResponseEntity.ok(
                ApiResponse.success("Athletes loaded.", trainerService.getAllAthletes()));
    }

    // ── Plans for a specific athlete ──────────────────────────────────────────

    @GetMapping("/athletes/{userId}/workout-plans")
    public ResponseEntity<ApiResponse<List<WorkoutPlanResponse>>> getAthleteWorkoutPlans(
            @PathVariable String userId) {
        return ResponseEntity.ok(
                ApiResponse.success("Workout plans loaded.", trainerService.getAthleteWorkoutPlans(userId)));
    }

    @GetMapping("/athletes/{userId}/meal-plans")
    public ResponseEntity<ApiResponse<List<MealPlanResponse>>> getAthleteMealPlans(
            @PathVariable String userId) {
        return ResponseEntity.ok(
                ApiResponse.success("Meal plans loaded.", trainerService.getAthleteMealPlans(userId)));
    }

    // ── Edit plans ────────────────────────────────────────────────────────────

    @PutMapping("/workout-plans/{id}")
    public ResponseEntity<ApiResponse<WorkoutPlanResponse>> editWorkoutPlan(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable String id,
            @Valid @RequestBody TrainerPlanEditRequest req) {
        return ResponseEntity.ok(
                ApiResponse.success("Workout plan updated.",
                        trainerService.editWorkoutPlan(userDetails.getUsername(), id, req)));
    }

    @PutMapping("/meal-plans/{id}")
    public ResponseEntity<ApiResponse<MealPlanResponse>> editMealPlan(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable String id,
            @Valid @RequestBody TrainerPlanEditRequest req) {
        return ResponseEntity.ok(
                ApiResponse.success("Meal plan updated.",
                        trainerService.editMealPlan(userDetails.getUsername(), id, req)));
    }
}
