package br.com.baby.manager.controller;

import br.com.baby.manager.model.Doctor;
import br.com.baby.manager.service.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
@Slf4j
@RestControllerAdvice
public class DoctorController {

    private final DoctorService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Doctor> save(@RequestBody Doctor doctor) {
        Optional<Doctor> newDoctor = service.save(doctor);
        if(newDoctor.isPresent()) {
            Doctor response = newDoctor.get();
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Fail saving it!");
        }

    }

    @GetMapping
    public ResponseEntity<List<Doctor>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> findById(@PathVariable String id) {
        Optional<Doctor> doctor = service.findById(id);

        if (doctor.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor register not found.");
        } else {
            return new ResponseEntity<>(doctor.get(), HttpStatus.OK);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Doctor> update(@RequestBody Doctor doctor, @PathVariable String id) {

        Optional<Doctor> updatedDoctor = service.update(doctor, id);

        if (updatedDoctor.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor register not found.");
        } else {
            return new ResponseEntity<>(updatedDoctor.get(), HttpStatus.OK);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Doctor> delete(@PathVariable String id) {

        Optional<Doctor> deletedDoctor = service.delete(id);

        if (deletedDoctor.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor register not found.");
        } else {
            return new ResponseEntity<>(deletedDoctor.get(), HttpStatus.OK);
        }

    }
}
