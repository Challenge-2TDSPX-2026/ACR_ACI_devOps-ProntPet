package br.com.project.prontpet.controllers;

import br.com.project.prontpet.dtos.ClinicRequest;
import br.com.project.prontpet.dtos.ClinicResponse;
import br.com.project.prontpet.models.Clinic;
import br.com.project.prontpet.services.ClinicService;
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
    public List<Clinic> getClinics() {
        return clinicService.getClinics();
    }

    @PostMapping
    public ResponseEntity<ClinicResponse> addClinic(@Valid @RequestBody ClinicRequest clinicRequest) {
        Clinic clinic = clinicService.addClinic(clinicRequest.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(ClinicResponse.fromEntity(clinic));
    }




}
