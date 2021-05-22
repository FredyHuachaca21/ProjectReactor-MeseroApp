package com.pe.fhuachaca.app.springreactor.serviceImpl;

import com.pe.fhuachaca.app.springreactor.model.Plato;
import com.pe.fhuachaca.app.springreactor.repository.IGenericRepository;
import com.pe.fhuachaca.app.springreactor.repository.IPlatoRepository;
import com.pe.fhuachaca.app.springreactor.service.IPlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlatoServiceImpl extends CrudServiceImpl<Plato, String> implements IPlatoService {
    @Autowired
    private IPlatoRepository repository;


    @Override
    protected IGenericRepository<Plato, String> getRepository() {
        return repository;
    }
}
