package br.com.project.prontpet.dtos;

import br.com.project.prontpet.models.Clinic;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalDateTime;

public record ClinicRequest(


        @NotBlank(message = "name is required")
        String name,

        @CNPJ(message = "the CNPJ must be valid")
        String CNPJ,


        String address,
        String phone,
        LocalDateTime openingHours,
        LocalDateTime closingHours
) {
        public Clinic toEntity(){
                return Clinic.builder()
                        .
        }
}
