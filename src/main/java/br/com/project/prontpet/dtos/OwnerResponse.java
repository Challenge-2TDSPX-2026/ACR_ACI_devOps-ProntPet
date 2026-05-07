package br.com.project.prontpet.dtos;

import br.com.project.prontpet.models.Owner;

public record OwnerResponse(

        Long id,
        String name,
        String cpf,
        String email,
        String phone
) {
    public static OwnerResponse fromEntity(Owner o){
        return new OwnerResponse(
                o.getId(),
                o.getName(),
                o.getCpf(),
                o.getEmail(),
                o.getPhone()
        );
    }
}
