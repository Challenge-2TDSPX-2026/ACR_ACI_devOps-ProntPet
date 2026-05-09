package br.com.project.prontpet.services;


import br.com.project.prontpet.models.Appointment;
import br.com.project.prontpet.repositories.AppointmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private AppointmentRepository appointmentRepository;
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Long id, Appointment newAppointment) {
        var optionalAppointment = getAppointmentById(id);
        if (optionalAppointment.isEmpty())throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment Not Found");
        newAppointment.setId(id);
        appointmentRepository.save(newAppointment);
        return newAppointment;
    }

}
