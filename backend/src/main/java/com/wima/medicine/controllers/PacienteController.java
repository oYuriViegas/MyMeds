package com.wima.medicine.controllers;

import com.wima.medicine.dto.PacienteDto;
import com.wima.medicine.models.Remedios;
import com.wima.medicine.models.Paciente;
import com.wima.medicine.repositories.RemediosRepository;
import com.wima.medicine.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    private RemediosRepository remediosRepository;

    @GetMapping
    public ResponseEntity<List<PacienteDto>> getAll() {
        return ResponseEntity.ok(
            this.repository.findAll()
                .stream()
                .map(PacienteDto::fromEntity)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}/remedios")
    public ResponseEntity<List<Remedios>> findAllByMedicinesByPacient(@PathVariable("id") String pacientId) {
        return ResponseEntity.ok(this.remediosRepository.findByPacienteId(pacientId));
    }

    @PostMapping
    public ResponseEntity<PacienteDto> create(@RequestBody Paciente paciente) {
        final Paciente saved = this.repository.save(paciente);
        return ResponseEntity.ok(PacienteDto.fromEntity(saved));
    }

}
