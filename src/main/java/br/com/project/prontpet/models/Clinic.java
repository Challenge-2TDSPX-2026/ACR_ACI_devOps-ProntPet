package br.com.project.prontpet.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_CLINIC")
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String cnpj;

    private String address;

    private String phone;

    @Column(name = "OPENING_HOURS")
    private LocalTime openingHours;

    @Column(name = "CLOSING_HOURS")
    private LocalTime closingHours;
}
