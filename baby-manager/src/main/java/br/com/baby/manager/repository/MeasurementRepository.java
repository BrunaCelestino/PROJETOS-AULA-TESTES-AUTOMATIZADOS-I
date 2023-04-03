package br.com.baby.manager.repository;

import br.com.baby.manager.model.Measurement;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MeasurementRepository extends CrudRepository<Measurement, String> {
}
