package br.com.baby.manager.service;
import br.com.baby.manager.model.Measurement;
import br.com.baby.manager.repository.MeasurementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MeasurementService {

    @Autowired
    private MeasurementRepository measurementRepository;

    public Optional<Measurement> save(Measurement measurement) {
        Measurement savedMeasurement = measurementRepository.save(measurement);
        return Optional.of(measurement);
    }

    public List<Measurement> findAll() {
        return (List<Measurement>) measurementRepository.findAll();
    }

    public Optional<Measurement> findById(String id) {
        return measurementRepository.findById(id);
    }

    public Optional<Measurement> update(Measurement measurement, String id) {
        Optional<Measurement> foundMeasurement = measurementRepository.findById(id);

        if(foundMeasurement.isPresent()) {
            if(measurement.getHeight() != null) {
                foundMeasurement.get().setHeight(measurement.getHeight());
            }


            if(measurement.getWeight() != null) {
                foundMeasurement.get().setWeight(measurement.getWeight());
            }

            if(measurement.getHeadCircumference() != null) {
                foundMeasurement.get().setHeadCircumference(measurement.getHeadCircumference());
            }

            measurementRepository.save(foundMeasurement.get());


        }

        return foundMeasurement;
    }

    public Optional<Measurement> delete(String id) {
        Optional<Measurement> foundMeasurement = measurementRepository.findById(id);

        foundMeasurement.ifPresent(doctor -> measurementRepository.delete(doctor));

        return foundMeasurement;
    }
}
