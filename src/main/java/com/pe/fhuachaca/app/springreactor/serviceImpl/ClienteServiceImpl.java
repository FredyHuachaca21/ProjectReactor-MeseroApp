package com.pe.fhuachaca.app.springreactor.serviceImpl;

import com.pe.fhuachaca.app.springreactor.model.Cliente;
import com.pe.fhuachaca.app.springreactor.repository.IClienteRepository;
import com.pe.fhuachaca.app.springreactor.repository.IGenericRepository;
import com.pe.fhuachaca.app.springreactor.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl extends CrudServiceImpl<Cliente, String> implements IClienteService {

    @Autowired
    private IClienteRepository repository;


    @Override
    protected IGenericRepository<Cliente, String> getRepository() {
        return repository;
    }
}
