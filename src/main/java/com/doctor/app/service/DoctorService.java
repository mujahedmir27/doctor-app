package com.doctor.app.service;

import com.doctor.app.model.Doctor;
import java.util.List;

public interface DoctorService {
    List<Doctor> getActiveDoctors();
}