package br.com.project.prontpet.services;


import br.com.project.prontpet.models.Appointment;
import br.com.project.prontpet.models.Clinic;
import br.com.project.prontpet.models.Pet;
import br.com.project.prontpet.repositories.AppointmentRepository;
import br.com.project.prontpet.repositories.ClinicRepository;
import br.com.project.prontpet.repositories.PetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final PetRepository petRepository;
    private final AppointmentRepository appointmentRepository;
    private final ClinicRepository clinicRepository;
    public AppointmentService(AppointmentRepository appointmentRepository,  PetRepository petRepository,  ClinicRepository clinicRepository) {
        this.appointmentRepository = appointmentRepository;
        this.petRepository = petRepository;
        this.clinicRepository = clinicRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public void validateAppointment(Appointment appointment) {
        Clinic clinic = appointment.getClinic();
        LocalTime appointmentTime = appointment.getAppointmentDate().toLocalTime();
        if(appointmentTime.isBefore(clinic.getOpeningHours()) || appointmentTime.isAfter(clinic.getClosingHours())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Appointment time is outside clinic opening hours");
        }

    }
    public void validateToAddAppointment(Appointment appointment) {
        validateAppointment(appointment);
        if (appointmentRepository.existsByPetAndAppointmentDate(
                appointment.getPet(), appointment.getAppointmentDate()
        )){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Pet already has an appointment scheduled for this date and time");
        }
    }

    public Appointment addAppointment(Appointment appointment) {
        Clinic clinic = clinicRepository.findById(appointment.getClinic().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clinic not found"));
        appointment.setClinic(clinic);

        Pet pet = petRepository.findById(appointment.getPet().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found"));
        appointment.setPet(pet);
        validateToAddAppointment(appointment);

        pet.setWeight(appointment.getUpdatedWeight());
        petRepository.save(pet);
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Long id, Appointment newAppointment) {
        Clinic clinic = clinicRepository.findById(newAppointment.getClinic().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clinic not found"));
        newAppointment.setClinic(clinic);

        Pet pet = petRepository.findById(newAppointment.getPet().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found"));
        newAppointment.setPet(pet);
        var optionalAppointment = getAppointmentById(id);
        if (optionalAppointment.isEmpty())throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment Not Found");
        validateAppointment(newAppointment);

        newAppointment.setId(id);
        pet.setWeight(newAppointment.getUpdatedWeight());
        petRepository.save(pet);
        return appointmentRepository.save(newAppointment);
    }

    public void deleteAppointment(Long id) {
        var optionalAppointment = getAppointmentById(id);
        if (optionalAppointment.isEmpty())throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment Not Found");
        appointmentRepository.deleteById(id);
    }
}
