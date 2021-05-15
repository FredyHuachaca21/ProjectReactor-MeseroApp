package com.pe.fhuachaca.app.springreactor.serviceImpl;

import com.pe.fhuachaca.app.springreactor.model.Plato;
import com.pe.fhuachaca.app.springreactor.repository.IPlatoRepository;
import com.pe.fhuachaca.app.springreactor.service.IPlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlatoServiceImpl implements IPlatoService {
    @Autowired
    private IPlatoRepository repository;

    @Override
    public Mono<Plato> registrar(Plato p) {
        return repository.save(p);
    }

    @Override
    public Mono<Plato> modificar(Plato p) {
        return repository.save(p);
    }

    @Override
    public Flux<Plato> listar() {
        return repository.findAll();
    }

    @Override
    public Mono<Plato> listarPorId(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Void> eliminar(String id) {
        return repository.deleteById(id);
    }
}
