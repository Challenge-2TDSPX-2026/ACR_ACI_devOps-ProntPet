package br.com.project.prontpet.models;

import br.com.project.prontpet.enums.Sex;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@Entity
@Table(name = "TB_PET")
@AllArgsConstructor
@NoArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String species;

    private String breed;

    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    private BigDecimal weight;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private Owner owner;
}
