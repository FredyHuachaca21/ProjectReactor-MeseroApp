package com.pe.fhuachaca.app.springreactor.serviceImpl;

import com.pe.fhuachaca.app.springreactor.model.Cliente;
import com.pe.fhuachaca.app.springreactor.model.Rol;
import com.pe.fhuachaca.app.springreactor.repository.IClienteRepository;
import com.pe.fhuachaca.app.springreactor.repository.IGenericRepository;
import com.pe.fhuachaca.app.springreactor.repository.IRolRepository;
import com.pe.fhuachaca.app.springreactor.service.IClienteService;
import com.pe.fhuachaca.app.springreactor.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl extends CrudServiceImpl<Rol, String> implements IRolService {

    @Autowired
    private IRolRepository repository;


    @Override
    protected IGenericRepository<Rol, String> getRepository() {
        return repository;
    }
}
