package com.pe.fhuachaca.app.springreactor.serviceImpl;

import com.pe.fhuachaca.app.springreactor.repository.IGenericRepository;
import com.pe.fhuachaca.app.springreactor.service.ICrudService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class CrudServiceImpl<T, ID> implements ICrudService<T, ID> {

    protected abstract IGenericRepository<T, ID> getRepository();

    @Override
    public Mono<T> registrar(T t) {
        return getRepository().save(t);
    }

    @Override
    public Mono<T> modificar(T t) {
        return getRepository().save(t);
    }

    @Override
    public Flux<T> listar() {
        return getRepository().findAll();
    }

    @Override
    public Mono<T> listarPorId(ID id) {
        return getRepository().findById(id);
    }

    @Override
    public Mono<Void> eliminar(ID id) {
        return getRepository().deleteById(id);
    }
}
