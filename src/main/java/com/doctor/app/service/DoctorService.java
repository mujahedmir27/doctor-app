package com.doctor.app.service;

import com.doctor.app.dto.DoctorDTO;
import java.util.List;

public interface DoctorService {
    List<DoctorDTO> getActiveDoctors();
}