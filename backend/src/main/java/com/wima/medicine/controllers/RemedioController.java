package com.wima.medicine.controllers;

import com.wima.medicine.models.Remedios;
import com.wima.medicine.repositories.RemediosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/remedios")
@CrossOrigin("*")
public class RemedioController {

    @Autowired
    private RemediosRepository repository;

    @GetMapping
    public ResponseEntity<List<Remedios>> getAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Remedios> findOne(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.repository.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<Remedios> create(@RequestBody Remedios remedios) {
        return ResponseEntity.ok(this.repository.insert(remedios));
    }

    @PutMapping("{id}")
    public ResponseEntity<Remedios> update(@RequestBody Remedios remedios, @PathVariable("id") String id) {
        return ResponseEntity.ok(this.repository.save(remedios));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id")String id){
        Optional<Remedios> remedioOpt = this.repository.findById(id);
        remedioOpt.ifPresent(remedios -> this.repository.delete(remedios));
    }

}
