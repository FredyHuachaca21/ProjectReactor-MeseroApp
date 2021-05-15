package com.pe.fhuachaca.app.springreactor.service;

import com.pe.fhuachaca.app.springreactor.model.Plato;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPlatoService {

    Mono<Plato> registrar(Plato p);
    Mono<Plato>modificar(Plato p);
    Flux<Plato> listar();
    Mono<Plato>listarPorId(String id);
    Mono<Void>eliminar(String id);

}
