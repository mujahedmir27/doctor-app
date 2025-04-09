package com.doctor.app.service.serviceImpl;

import com.doctor.app.model.Doctor;
import com.doctor.app.model.User;
import com.doctor.app.repository.DoctorRepository;
import com.doctor.app.repository.UserRepository;
import com.doctor.app.service.AdminService ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminServiceImpl(DoctorRepository doctorRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        logger.info("AdminServiceImpl - Adding new doctor: {}", doctor.getName());
        Doctor savedDoctor = doctorRepository.save(doctor);

        User user = new User();
        user.setUsername(doctor.getName());
        user.setPassword(passwordEncoder.encode(doctor.getPassword()));
        user.setRole("DOCTOR");
        userRepository.save(user);
        return savedDoctor;
    }

    @Override
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        logger.info("AdminServiceImpl - Adding new user: {}", user.getUsername());
        return userRepository.save(user);
    }
}
