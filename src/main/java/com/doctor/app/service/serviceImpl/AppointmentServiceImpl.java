package com.doctor.app.service.serviceImpl;

import com.doctor.app.model.Appointment;
import com.doctor.app.model.Doctor;
import com.doctor.app.repository.AppointmentRepository;
import com.doctor.app.repository.DoctorRepository;
import com.doctor.app.service.AppointmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.doctor.app.client.PaymentClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PaymentClient paymentClient;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PaymentClient paymentClient) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.paymentClient = paymentClient;
    }

    @Override
    public String bookAppointment(Appointment appointment) {

        Long doctorId = appointment.getDoctor().getId();
        LocalDate appointmentDate = appointment.getAppointmentDate();
        LocalTime startTime = appointment.getStartTime();
        LocalDateTime appointmentDateTime = LocalDateTime.of(appointmentDate, startTime);
        LocalDateTime now = LocalDateTime.now();

        if (appointmentDateTime.isBefore(now)) {
            return "Cannot book an appointment in the past!";
        }

        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
        if (doctor == null) {
            return "Cannot book appointment: no doctor found with the given ID";
        }
        else if(!doctor.isActive()){
            return "Cannot book appointment: selected doctor is inactive";
        }

        Optional<Appointment> existingAppointment = appointmentRepository
                .findByDoctorIdAndAppointmentDate(doctorId, appointmentDate)
                .stream()
                .filter(a -> a.getStartTime().equals(startTime))
                .findFirst();

        if (existingAppointment.isPresent()) {
            return "Selected appointment slot is already booked";
        }

        try {
            LocalTime end = startTime.plusMinutes(30);
            appointment.setEndTime(end);
        } catch (DateTimeParseException e) {
            return "Invalid start time format";
        }

        appointment.setStatus("BOOKED");
        appointmentRepository.save(appointment);
        paymentClient.makePayment(appointment.getId(), appointment.getAmount());
        return "Appointment booked and payment initiated!";
    }

    @Override
    public String updateAppointmentStatus(Long appointmentId, String status) {
        Optional<Appointment> appointmentOpt = appointmentRepository.findById(appointmentId);
        if (appointmentOpt.isPresent()) {
            Appointment appointment = appointmentOpt.get();
            appointment.setStatus(status);
            appointmentRepository.save(appointment);
            return "Appointment status updated successfully";
        }
        return "Appointment not found";
    }
}
