package com.wima.medicine.controllers;

import com.wima.medicine.service.MedicoService;
import com.wima.medicine.dto.MedicoDto;
import com.wima.medicine.dto.PacienteDto;
import com.wima.medicine.models.Medico;
import com.wima.medicine.repositories.MedicoRepository;
import com.wima.medicine.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import java.net.*;
import java.io.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoService medicoService;

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

    @PostMapping
    public ResponseEntity create(@RequestBody Medico medico) {

        try {
            Socket conexao = new Socket("localhost",69420);

            ObjectOutputStream transmissor = new ObjectOutputStream(conexao.getOutputStream());

            ObjectInputStream receptor = new ObjectInputStream(conexao.getInputStream());

            transmissor.writeObject(medico);
            transmissor.flush();

            Medico validated = null;
            try {
                validated = (Medico) receptor.readObject();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (Objects.isNull(validated)) {
                return ResponseEntity.badRequest().body("Falha ao validar CRM ou registro em duplicidade");
            }
            final Medico saved = this.repository.save(medico);
            return ResponseEntity.ok(MedicoDto.fromEntity(saved));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
