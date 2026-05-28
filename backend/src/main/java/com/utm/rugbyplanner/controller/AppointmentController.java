package com.utm.rugbyplanner.controller;

import com.utm.rugbyplanner.dto.*;
import com.utm.rugbyplanner.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AppointmentController
 *
 * ┌──────────────────────────────────────────────────────────────────────────────────┐
 * │  Method │  URL                                     │  Role     │  Use Case       │
 * ├─────────┼──────────────────────────────────────────┼───────────┼─────────────────┤
 * │  GET    │  /api/appointments/trainers              │  ATHLETE  │  Booking form   │
 * │  POST   │  /api/appointments                       │  ATHLETE  │  UC008 Create   │
 * │  GET    │  /api/appointments                       │  ATHLETE  │  UC009 List     │
 * │  PUT    │  /api/appointments/{id}/edit             │  ATHLETE  │  UC009 Edit     │
 * │  PUT    │  /api/appointments/{id}/cancel           │  ATHLETE  │  UC009 Cancel   │
 * │  GET    │  /api/appointments/trainer               │  TRAINER  │  UC012 List     │
 * │  PUT    │  /api/appointments/{id}/approve          │  TRAINER  │  UC012 Approve  │
 * │  PUT    │  /api/appointments/{id}/reject           │  TRAINER  │  UC012 Reject   │
 * └──────────────────────────────────────────────────────────────────────────────────┘
 */
@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    // ── Trainer listing (ATHLETE accesses when booking) ───────────────────────

    @GetMapping("/trainers")
    @PreAuthorize("hasRole('ATHLETE')")
    public ResponseEntity<ApiResponse<List<TrainerSummaryResponse>>> getAvailableTrainers() {
        return ResponseEntity.ok(
                ApiResponse.success("Trainers loaded.", appointmentService.getAvailableTrainers()));
    }

    // ── UC008: Create appointment ─────────────────────────────────────────────

    @PostMapping
    @PreAuthorize("hasRole('ATHLETE')")
    public ResponseEntity<ApiResponse<AppointmentResponse>> createAppointment(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody AppointmentRequest req) {
        AppointmentResponse result = appointmentService.createAppointment(
                userDetails.getUsername(), req);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Appointment booked successfully!", result));
    }

    // ── UC009: Athlete views own appointments ─────────────────────────────────

    @GetMapping
    @PreAuthorize("hasRole('ATHLETE')")
    public ResponseEntity<ApiResponse<List<AppointmentResponse>>> getAthleteAppointments(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(
                ApiResponse.success("Appointments loaded.",
                        appointmentService.getAthleteAppointments(userDetails.getUsername())));
    }

    // ── UC009: Athlete edits PENDING appointment ──────────────────────────────

    @PutMapping("/{id}/edit")
    @PreAuthorize("hasRole('ATHLETE')")
    public ResponseEntity<ApiResponse<AppointmentResponse>> editAppointment(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable String id,
            @Valid @RequestBody AppointmentRequest req) {
        return ResponseEntity.ok(
                ApiResponse.success("Appointment updated.",
                        appointmentService.editAppointment(userDetails.getUsername(), id, req)));
    }

    // ── UC009: Athlete cancels PENDING appointment ────────────────────────────

    @PutMapping("/{id}/cancel")
    @PreAuthorize("hasRole('ATHLETE')")
    public ResponseEntity<ApiResponse<AppointmentResponse>> cancelAppointment(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable String id) {
        return ResponseEntity.ok(
                ApiResponse.success("Appointment cancelled.",
                        appointmentService.cancelAppointment(userDetails.getUsername(), id)));
    }

    // ── UC012: Trainer views their appointments ───────────────────────────────

    @GetMapping("/trainer")
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<ApiResponse<List<AppointmentResponse>>> getTrainerAppointments(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(
                ApiResponse.success("Appointments loaded.",
                        appointmentService.getTrainerAppointments(userDetails.getUsername())));
    }

    // ── UC012: Trainer approves appointment ───────────────────────────────────

    @PutMapping("/{id}/approve")
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<ApiResponse<AppointmentResponse>> approveAppointment(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable String id,
            @RequestBody(required = false) AppointmentRemarkRequest req) {
        return ResponseEntity.ok(
                ApiResponse.success("Appointment approved.",
                        appointmentService.approveAppointment(userDetails.getUsername(), id, req)));
    }

    // ── UC012: Trainer rejects appointment ────────────────────────────────────

    @PutMapping("/{id}/reject")
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<ApiResponse<AppointmentResponse>> rejectAppointment(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable String id,
            @RequestBody(required = false) AppointmentRemarkRequest req) {
        return ResponseEntity.ok(
                ApiResponse.success("Appointment rejected.",
                        appointmentService.rejectAppointment(userDetails.getUsername(), id, req)));
    }
}
