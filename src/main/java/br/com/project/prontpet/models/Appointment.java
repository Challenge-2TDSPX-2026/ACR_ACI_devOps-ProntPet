package br.com.project.prontpet.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_APPOINTMENT")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String speciality;

    private String symptoms;

    @Column(name = "DIGNOSIS")
    private String dignosis;

    private String observations;

    @Column(name = "APPOINTMENT_DATE")
    private LocalDateTime appointmentDate;

    @Column(name = "UPDATED_WEIGHT")
    private BigDecimal updatedWeight;

    @ManyToOne
    @JoinColumn(name = "CLINIC_ID")
    private Clinic clinic;

    @ManyToOne
    @JoinColumn(name = "PET_ID")
    private Pet pet;
}
