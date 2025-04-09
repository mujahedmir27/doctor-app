package com.doctor.app.service;

import com.doctor.app.model.Doctor;
import com.doctor.app.model.User;

public interface AdminService {
    Doctor addDoctor(Doctor doctor);
    User addUser(User user);
}
