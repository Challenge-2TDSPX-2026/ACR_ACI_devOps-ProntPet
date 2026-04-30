package br.com.project.prontpet.models;


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
    private String race;
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
