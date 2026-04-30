package br.com.project.prontpet.dtos;

import br.com.project.prontpet.models.Owner;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record OwnerRequest (

        @NotBlank(message = "name is required")
        String name,

        @NotBlank(message = "cpf is required")
        @CPF
        String cpf,

        @NotBlank(message = "email is required")
        @Email
        String email,

        @NotBlank(message = "phone is required")
        @Size(min = 11)
        String phone,

        @NotBlank(message = "password is required")
        @Size(min = 8, max = 20, message = "the password must be at least 8 characters long and no more than 20.")
        String password
){
    public Owner toEntity(){
        return Owner.builder()
                .name(name)
                .cpf(cpf)
                .email(email)
                .phone(phone)
                .password(password)
                .build();
    }
}
