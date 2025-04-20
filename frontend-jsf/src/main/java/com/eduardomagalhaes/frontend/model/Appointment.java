package com.eduardomagalhaes.frontend.model;

import java.time.LocalDateTime;

public class Appointment {
    private String customerName;
    private String serviceType;
    private LocalDateTime dateTime;

    public Appointment() {}

    public Appointment(String customerName, String serviceType, LocalDateTime dateTime) {
        this.customerName = customerName;
        this.serviceType = serviceType;
        this.dateTime = dateTime;
    }

    // Getters e setters
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getServiceType() {
        return serviceType;
    }
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}