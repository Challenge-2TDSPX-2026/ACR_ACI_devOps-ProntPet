package br.com.project.prontpet.dtos;

import br.com.project.prontpet.models.Appointment;
import br.com.project.prontpet.models.Clinic;
import br.com.project.prontpet.models.Pet;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

public record AppointmentRequest (

        @NotBlank(message = "specialty is required")
        String speciality,

        @NotBlank(message = "symptoms is required")
        String symptoms,

        @NotBlank(message = "dignosis is required")
        String dignosis,

        @NotBlank(message = "observations is required")
        String observations,

        @NotBlank(message = "clinic is required")
        Clinic clinic,

        @NotBlank(message = "pet is required")
        Pet pet
){
    public Appointment toEntity(){
        return Appointment.builder()
                .speciality(speciality)
                .symptoms(symptoms)
                .dignosis(dignosis)
                .observations(observations)
                .clinic(clinic)
                .pet(pet)
                .build();
    }
}
