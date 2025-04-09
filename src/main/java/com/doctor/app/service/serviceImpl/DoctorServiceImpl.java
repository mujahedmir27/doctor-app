package com.doctor.app.service.serviceImpl;

import com.doctor.app.model.Doctor;
import com.doctor.app.repository.DoctorRepository;
import com.doctor.app.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Doctor> getActiveDoctors() {
        return doctorRepository.findByActiveTrue();
    }
}