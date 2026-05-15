package br.com.project.prontpet.controllers;

import br.com.project.prontpet.dtos.ClinicRequest;
import br.com.project.prontpet.dtos.ClinicResponse;
import br.com.project.prontpet.models.Clinic;
import br.com.project.prontpet.services.ClinicService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clinics")
public class ClinicController {

    private ClinicService clinicService;

    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping
    @Operation(
            tags = "Clinic",
            summary = "Listar todas as clínicas",
            description = "Retorna uma lista com todas as clínicas cadastradas no sistema."
    )
    public List<Clinic> getClinics() {
        return clinicService.getClinics();
    }

    @PostMapping
    @Operation(
            tags = "Clinic",
            summary = "Cadastrar nova clínica",
            description = "Recebe os dados da clínica via body, persiste no banco e retorna a entidade criada com status 201."
    )
    public ResponseEntity<ClinicResponse> addClinic(@Valid @RequestBody ClinicRequest clinicRequest) {
        Clinic clinic = clinicService.addClinic(clinicRequest.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(ClinicResponse.fromEntity(clinic));
    }

    @PutMapping("/{id}")
    @Operation(
            tags = "Clinic",
            summary = "Atualizar clínica",
            description = "Recebe o ID da clínica e os novos dados via body, atualiza no banco e retorna a entidade atualizada."
    )
    public ResponseEntity<ClinicResponse> updateClinic(@PathVariable Long id, @Valid @RequestBody ClinicRequest clinicRequest) {
        Clinic clinic = clinicService.updateClinic(id, clinicRequest.toEntity());
        return ResponseEntity.ok(ClinicResponse.fromEntity(clinic));
    }

    @DeleteMapping("/{id}")
    @Operation(
            tags = "Clinic",
            summary = "Deletar clínica",
            description = "Remove a clínica com o ID informado do banco de dados. Retorna 204 sem conteúdo."
    )
    public ResponseEntity<Void> deleteClinic(@PathVariable Long id) {
        clinicService.deleteClinic(id);
        return ResponseEntity.noContent().build();
    }
}