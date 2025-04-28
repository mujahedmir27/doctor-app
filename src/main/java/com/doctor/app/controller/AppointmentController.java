package com.doctor.app.controller;

import com.doctor.app.model.*;
import com.doctor.app.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/book")
    @PreAuthorize("hasAnyRole('ADMIN', 'PATIENT')")
    public ResponseEntity<String> bookAppointment(@Valid @RequestBody Appointment appointment) {
        logger.info("Booking appointment for patient {} with doctor {}",
                appointment.getPatient().getId(), appointment.getDoctor().getId());

        String result = appointmentService.bookAppointment(appointment);
        if ("Appointment booked and payment initiated!".equals(result)) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PatchMapping("/updateStatus")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<String> updateAppointmentStatus(@RequestParam Long appointmentId,
                                                          @RequestParam String status) {
        String result = appointmentService.updateAppointmentStatus(appointmentId, status);
        if ("Appointment status updated successfully".equals(result)) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
}

