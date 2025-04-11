package com.eduardomagalhaes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardomagalhaes.model.Appointment;
import com.eduardomagalhaes.repository.AppointmentRepository;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repository;

    public Appointment create(Appointment appointment) {
        return repository.save(appointment);
    }

    public List<Appointment> getAll() {
        return repository.findAll();
    }
}

