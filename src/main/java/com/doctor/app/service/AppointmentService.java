package com.doctor.app.service;

import com.doctor.app.model.Appointment;

public interface AppointmentService {
    String bookAppointment(Appointment appointment);
    String updateAppointmentStatus(Long appointmentId, String status);
}

