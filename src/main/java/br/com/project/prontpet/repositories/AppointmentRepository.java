package br.com.project.prontpet.repositories;

import br.com.project.prontpet.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
