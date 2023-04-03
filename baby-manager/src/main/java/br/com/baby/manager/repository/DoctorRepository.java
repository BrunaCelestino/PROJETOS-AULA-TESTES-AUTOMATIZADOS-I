package br.com.baby.manager.repository;

import br.com.baby.manager.model.Doctor;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DoctorRepository extends CrudRepository<Doctor, String> {
}
