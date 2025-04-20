package com.eduardomagalhaes.frontend.bean;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.eduardomagalhaes.frontend.model.Appointment;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Named
@ViewScoped
@Getter
@Setter
public class AppointmentBean implements Serializable {

    private static final String API_URL = "http://localhost:8080/appointments"; // API Gateway
    private static final String JWT_TOKEN = "SEU_TOKEN_JWT_FIXO_PARA_TESTES"; // substituir dinamicamente depois

    private List<Appointment> appointments;
    private String customerName;
    private String serviceType;
    private LocalDateTime dateTime;

    private ObjectMapper mapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        fetchAppointments();
    }

    public void save() {
        try {
            Appointment newAppointment = new Appointment(customerName, serviceType, dateTime);
            String json = mapper.writeValueAsString(newAppointment);

            HttpURLConnection conn = (HttpURLConnection) new URL(API_URL).openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + JWT_TOKEN);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes());
            os.flush();
            os.close();

            if (conn.getResponseCode() == 200) {
                showMessage("Agendamento criado com sucesso.");
                fetchAppointments();
                clearForm();
            } else {
                showMessage("Erro ao agendar. Código: " + conn.getResponseCode());
            }

            conn.disconnect();
        } catch (Exception e) {
            showMessage("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void fetchAppointments() {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(API_URL).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + JWT_TOKEN);

            InputStream inputStream = conn.getInputStream();
            appointments = mapper.readValue(inputStream, new TypeReference<List<Appointment>>() {});
            inputStream.close();
            conn.disconnect();
        } catch (Exception e) {
            showMessage("Erro ao buscar agendamentos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void clearForm() {
        this.customerName = "";
        this.serviceType = "";
        this.dateTime = null;
    }

    private void showMessage(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        PrimeFaces.current().ajax().update("appointmentForm");
    }
}

