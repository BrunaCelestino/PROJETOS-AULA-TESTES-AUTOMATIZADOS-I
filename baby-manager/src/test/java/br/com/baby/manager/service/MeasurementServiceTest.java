package br.com.baby.manager.service;

import br.com.baby.manager.model.Measurement;
import br.com.baby.manager.repository.MeasurementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class MeasurementServiceTest {

    @Mock
    private MeasurementRepository measurementRepository;

    @InjectMocks
    private MeasurementService measurementService;

    private final Measurement measurement1 = new Measurement("1", 50.0, 3.0, 33.0);
    private final Measurement measurement2 = new Measurement("2", 55.0, 4.0, 36.0);
    private final Measurement measurement3 = new Measurement("3", 60.0, 5.0, 39.0);


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {
        when(measurementRepository.save(measurement1)).thenReturn(measurement1);

        Optional<Measurement> savedMeasurement = measurementService.save(measurement1);

        assertEquals(measurement1, savedMeasurement.get());

    }

    @Test
    void findAll() {
        List<Measurement> measurements = new ArrayList<>();
        measurements.add(measurement1);
        measurements.add(measurement2);
        measurements.add(measurement3);

        when(measurementRepository.findAll()).thenReturn(measurements);

        List<Measurement> foundMeasurements = measurementService.findAll();

        assertEquals(3, foundMeasurements.size());
        assertEquals(measurements, foundMeasurements);
    }

    @Test
    void findById() {
        when(measurementRepository.findById("1")).thenReturn(Optional.of(measurement1));

        Optional<Measurement> foundMeasurement = measurementService.findById("1");

        assertEquals(measurement1, foundMeasurement.get());


    }

    @Test
    void update() {
        when(measurementRepository.findById("1")).thenReturn(Optional.of(measurement1));

        Measurement updatedMeasurement = new Measurement("1", 60.0, 4.0, 39.0);

        when(measurementRepository.save(measurement1)).thenReturn(updatedMeasurement);

        Optional<Measurement> foundMeasurement = measurementService.update(updatedMeasurement, "1");

        assertEquals(updatedMeasurement, foundMeasurement.get());
    }

    @Test
    void delete() {
        when(measurementRepository.findById("1")).thenReturn(Optional.of(measurement1));

        Optional<Measurement> deletedMeasurement = measurementService.delete("1");

        assertEquals(measurement1, deletedMeasurement.get());

    }
}