package br.com.baby.manager.controller;

import br.com.baby.manager.exception.InvalidInformationException;
import br.com.baby.manager.model.Measurement;
import br.com.baby.manager.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/measurement")
@RequiredArgsConstructor
@Slf4j
@RestControllerAdvice
public class MeasurementController {

    private final MeasurementService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Measurement> save(@RequestBody Measurement measurement) {
        Optional<Measurement> newMeasurement = service.save(measurement);
        if(newMeasurement.isPresent()) {
            Measurement response = newMeasurement.get();

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Fail saving it!");
        }

    }

    @GetMapping
    public ResponseEntity<List<Measurement>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Measurement> findById(@PathVariable String id) {
        Optional<Measurement> measurement = service.findById(id);

        if (measurement.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Measurement register not found.");
        } else {
            return new ResponseEntity<>(measurement.get(), HttpStatus.OK);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Measurement> update(@RequestBody Measurement measurement, @PathVariable String id)  {

        Optional<Measurement> updatedMeasurement = service.update(measurement, id);

        if (updatedMeasurement.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Measurement register not found.");
        } else {
            return new ResponseEntity<>(updatedMeasurement.get(), HttpStatus.OK);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Measurement> delete(@PathVariable String id) {

        Optional<Measurement> deletedMeasurement = service.delete(id);

        if (deletedMeasurement.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Measurement register not found.");
        } else {
            return new ResponseEntity<>(deletedMeasurement.get(), HttpStatus.OK);
        }

    }
}
