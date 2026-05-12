package br.com.project.prontpet.controllers;

import br.com.project.prontpet.dtos.AppointmentRequest;
import br.com.project.prontpet.dtos.AppointmentResponse;
import br.com.project.prontpet.dtos.ClinicResponse;
import br.com.project.prontpet.models.Appointment;
import br.com.project.prontpet.services.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments(){
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @PostMapping
    public ResponseEntity<AppointmentResponse> addAppointment(@Valid @RequestBody AppointmentRequest appointmentRequest){
        Appointment appointment = appointmentService.addAppointment(appointmentRequest.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(AppointmentResponse.fromEntity(appointment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponse> updateAppointment(@PathVariable Long id, @Valid @RequestBody AppointmentRequest appointmentRequest){
        Appointment appointment = appointmentService.updateAppointment(id, appointmentRequest.toEntity());
        return ResponseEntity.ok(AppointmentResponse.fromEntity(appointment));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id){
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

}
