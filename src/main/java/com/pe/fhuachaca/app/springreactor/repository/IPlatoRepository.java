package com.pe.fhuachaca.app.springreactor.repository;

import com.pe.fhuachaca.app.springreactor.model.Plato;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IPlatoRepository extends ReactiveMongoRepository<Plato, String> {
}
