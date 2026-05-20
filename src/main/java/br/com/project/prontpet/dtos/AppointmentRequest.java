package br.com.project.prontpet.dtos;

import br.com.project.prontpet.models.Appointment;
import br.com.project.prontpet.models.Clinic;
import br.com.project.prontpet.models.Pet;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record AppointmentRequest (

        @NotBlank(message = "specialty is required")
        String speciality,

        @NotBlank(message = "symptoms is required")
        String symptoms,

        @NotBlank(message = "dignosis is required")
        String dignosis,

        @NotBlank(message = "observations is required")
        String observations,

        Clinic clinic,

        Pet pet,

        @NotNull(message = "appointmentDate is required")
        @FutureOrPresent(message = "appointmentDate might not be in the past")
        LocalDateTime appointmentDate,

        @NotNull(message =  "updatedWeight is required")
        Double updatedWeight
){
    public Appointment toEntity(){
        return Appointment.builder()
                .speciality(speciality)
                .symptoms(symptoms)
                .dignosis(dignosis)
                .observations(observations)
                .clinic(clinic)
                .pet(pet)
                .appointmentDate(appointmentDate)
                .updatedWeight(updatedWeight)
                .build();
    }
}
