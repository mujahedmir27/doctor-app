package com.doctor.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorDTO {
    private Long id;
    private String name;
    private String specialization;

    public DoctorDTO() {}

    public DoctorDTO(Long id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }
}
