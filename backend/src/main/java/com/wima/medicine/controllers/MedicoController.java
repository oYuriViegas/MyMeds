package com.wima.medicine.controllers;

import com.wima.medicine.dto.MedicoDto;
import com.wima.medicine.dto.MedicoLoginDto;
import com.wima.medicine.dto.PacienteDto;
import com.wima.medicine.models.Medico;
import com.wima.medicine.repositories.MedicoRepository;
import com.wima.medicine.repositories.PacienteRepository;
import com.wima.medicine.service.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medicos")
@CrossOrigin("*")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping
    public ResponseEntity<List<MedicoDto>> getAll() {
        return ResponseEntity.ok(
            this.repository.findAll()
                .stream()
                .map(MedicoDto::fromEntity)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}/pacientes")
    public ResponseEntity<List<PacienteDto>> findAllByPacientsByDoctor(@PathVariable("id") String doctorId) {
        return ResponseEntity.ok(
                this.pacienteRepository.findByMedicoId(doctorId)
                        .stream()
                        .map(PacienteDto::fromEntity)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody MedicoLoginDto loginDto) {
        final Medico medico = this.repository.findByCrm(loginDto.getCrm());
        if (Objects.isNull(medico)) {
            return ResponseEntity.badRequest().body("Médico não encontrado");
        }
        if (Objects.nonNull(medico) && !Objects.equals(medico.getSenha(), loginDto.getSenha())) {
            return ResponseEntity.badRequest().body("Credenciais inválidas");
        }
        return ResponseEntity.ok(MedicoDto.fromEntity(medico));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Medico medico) {
        Medico validated = Cliente.main(medico);
        System.out.println("Consulta recebida.");

        if (Objects.isNull(validated)) {
            return ResponseEntity.badRequest().body("Falha ao validar CRM ou registro em duplicidade");
        }
        final Medico saved = this.repository.save(validated);
        return ResponseEntity.ok(MedicoDto.fromEntity(saved));
    }

    @PostMapping("/verificarCredenciais")
    public ResponseEntity<String> verificarCredenciais(@RequestBody Map<String, String> credenciais) {
        String crm = credenciais.get("crm");
        String senha = credenciais.get("senha");

        Medico medico = repository.findByCrmAndSenha(crm, senha);

        if (medico != null) {
            return ResponseEntity.ok(medico.getId());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

