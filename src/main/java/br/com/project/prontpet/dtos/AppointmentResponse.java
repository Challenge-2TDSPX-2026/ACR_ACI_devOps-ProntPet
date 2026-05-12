package br.com.project.prontpet.dtos;

import br.com.project.prontpet.models.Appointment;
import br.com.project.prontpet.models.Clinic;
import br.com.project.prontpet.models.Pet;
import jakarta.persistence.ManyToOne;

public record AppointmentResponse(

        Long id,

        String speciality,
        String symptoms,
        String dignosis,
        String observations,
        Clinic clinic,
        Pet pet
) {
    public static AppointmentResponse fromEntity(Appointment a) {
        return new AppointmentResponse(
                a.getId(),
                a.getSpeciality(),
                a.getSymptoms(),
                a.getDignosis(),
                a.getObservations(),
                a.getClinic(),
                a.getPet()
        );
    }
}
