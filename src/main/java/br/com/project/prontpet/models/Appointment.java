package br.com.project.prontpet.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_APPOiNTMENT")
public class Appointment {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String speciality;
    private String symptoms;
    private String dignosis;
    private String observations;

    @ManyToOne
    private Clinic clinic;

    @ManyToOne
    private Pet pet;
}
