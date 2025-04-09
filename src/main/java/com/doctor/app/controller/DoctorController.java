package com.doctor.app.controller;

import com.doctor.app.model.Doctor;
import com.doctor.app.service.DoctorService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    private DoctorService doctorService;
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/active")
    public ResponseEntity<List<Doctor>> getActiveDoctors() {
        List<Doctor> activeDoctor = doctorService.getActiveDoctors();
        return ResponseEntity.ok(activeDoctor);
    }
}

