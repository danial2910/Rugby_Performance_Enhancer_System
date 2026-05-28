package com.utm.rugbyplanner.repository;

import com.utm.rugbyplanner.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {

    /** All appointments for an athlete, newest first */
    List<Appointment> findByAthleteIdOrderByCreatedAtDesc(String athleteId);

    /** All appointments assigned to a trainer, newest first */
    List<Appointment> findByTrainerIdOrderByCreatedAtDesc(String trainerId);

    /** Count by status for a trainer */
    long countByTrainerIdAndStatus(String trainerId, String status);
}
