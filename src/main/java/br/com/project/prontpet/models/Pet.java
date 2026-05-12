package br.com.project.prontpet.models;


import br.com.project.prontpet.enums.Sex;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@Entity
@Table(name = "TB_PET")
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String species;
    private String breed;
    private Integer age;
    private Double weight;
    private Sex sex;

    @ManyToOne
    private Owner owner;
}
