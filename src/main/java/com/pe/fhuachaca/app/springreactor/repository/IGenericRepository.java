package com.pe.fhuachaca.app.springreactor.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean //Ayuda para abstraer código
public interface IGenericRepository<T, ID> extends ReactiveMongoRepository<T, ID> {
}
