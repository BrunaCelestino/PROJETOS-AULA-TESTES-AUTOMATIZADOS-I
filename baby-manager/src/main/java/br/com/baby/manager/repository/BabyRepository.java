package br.com.baby.manager.repository;

import br.com.baby.manager.model.Baby;
import org.springframework.data.repository.CrudRepository;

public interface BabyRepository extends CrudRepository<Baby, String> {
}