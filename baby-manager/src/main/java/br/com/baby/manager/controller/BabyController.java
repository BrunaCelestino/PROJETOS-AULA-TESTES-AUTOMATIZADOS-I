package br.com.baby.manager.controller;

import br.com.baby.manager.exception.InvalidInformationException;
import br.com.baby.manager.model.Baby;
import br.com.baby.manager.model.Measurement;
import br.com.baby.manager.service.BabyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/baby")
@RequiredArgsConstructor
@Slf4j
@RestControllerAdvice
public class BabyController {

    private final BabyService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Baby> save(@RequestBody Baby baby) throws InvalidInformationException {
        Optional<Baby> newBaby = service.save(baby);
        if (newBaby.isPresent()) {
            Baby response = newBaby.get();

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Fail saving it!");
        }

    }

    @GetMapping
    public ResponseEntity<List<Baby>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Baby> findById(@PathVariable String id) {
        Optional<Baby> baby = service.findById(id);

        if (baby.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Baby register not found.");
        } else {
            return new ResponseEntity<>(baby.get(), HttpStatus.OK);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Baby> update(@RequestBody Baby baby, @PathVariable String id) throws InvalidInformationException {

        Optional<Baby> updatedBaby = service.update(baby, id);

        if (updatedBaby.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Baby register not found.");
        } else {
            return new ResponseEntity<>(updatedBaby.get(), HttpStatus.OK);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Baby> delete(@PathVariable String id) {

        Optional<Baby> deletedBaby = service.delete(id);

        if (deletedBaby.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Baby register not found.");
        } else {
            return new ResponseEntity<>(deletedBaby.get(), HttpStatus.OK);
        }

    }

    @PatchMapping("/measurement/{id}")
    public ResponseEntity<Baby> addMeasurement(@PathVariable String id,
                                               @RequestParam("data") String measurementId) {

        Optional<Baby> updatedBaby = service.addMeasurements(id, measurementId);

        if (updatedBaby.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Measurement or Baby register not found.");
        } else {
            return new ResponseEntity<>(updatedBaby.get(), HttpStatus.OK);
        }

    }

    @PatchMapping("/doctor/{id}")
    public ResponseEntity<Baby> addDoctor(@PathVariable String id,
                                          @RequestParam("data") String doctorId) {

        Optional<Baby> updatedBaby = service.addDoctor(id, doctorId);

        if (updatedBaby.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor or Baby register not found.");
        } else {
            return new ResponseEntity<>(updatedBaby.get(), HttpStatus.OK);
        }


    }

}
