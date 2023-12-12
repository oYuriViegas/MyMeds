package com.wima.medicine.controllers;

import com.wima.medicine.dto.*;
import com.wima.medicine.models.Medico;
import com.wima.medicine.models.Paciente;
import com.wima.medicine.models.Remedios;
import com.wima.medicine.repositories.MedicoRepository;
import com.wima.medicine.repositories.PacienteRepository;
import com.wima.medicine.repositories.RemediosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pacientes")
@CrossOrigin("*")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    private RemediosRepository remediosRepository;

    @Autowired
    private MedicoRepository medicosRepository;


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

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody PacienteLoginDto loginDto) {
        final Paciente paciente = this.repository.findByEmail(loginDto.getEmail());
        if (Objects.isNull(paciente)) {
            return ResponseEntity.badRequest().body("Paciente não encontrado");
        }
        if (Objects.nonNull(paciente) && !Objects.equals(paciente.getSenha(), loginDto.getSenha())) {
            return ResponseEntity.badRequest().body("Credenciais inválidas");
        }
        return ResponseEntity.ok(PacienteDto.fromEntity(paciente));
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreatePacienteDto paciente) {
        final Medico medico = this.medicosRepository.findByCrm(paciente.getCrm());
        if(Objects.isNull(medico)){
            return ResponseEntity.badRequest().body("Médico não encontrado");
        }
        final Paciente.PacienteBuilder pacienteBuilder = CreatePacienteDto.getPacienteBuilder(paciente).medicoId(medico.getId());
        final Paciente saved = this.repository.save(pacienteBuilder.build());
        return ResponseEntity.ok(PacienteDto.fromEntity(saved));
    }

}
