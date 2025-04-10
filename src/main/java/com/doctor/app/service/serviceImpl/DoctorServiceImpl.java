package com.doctor.app.service.serviceImpl;

import com.doctor.app.dto.DoctorDTO;
import com.doctor.app.model.Doctor;
import com.doctor.app.repository.DoctorRepository;
import com.doctor.app.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<DoctorDTO> getActiveDoctors() {
        List<Doctor> activeDoctors = doctorRepository.findByActiveTrue();
        return activeDoctors.stream()
                .map(doctor -> new DoctorDTO(
                        doctor.getId(),
                        doctor.getName(),
                        doctor.getSpecialization()
                ))
                .collect(Collectors.toList());
    }
}