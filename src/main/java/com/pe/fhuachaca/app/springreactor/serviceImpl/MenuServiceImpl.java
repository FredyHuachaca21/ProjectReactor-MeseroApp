package com.pe.fhuachaca.app.springreactor.serviceImpl;

import com.pe.fhuachaca.app.springreactor.model.Cliente;
import com.pe.fhuachaca.app.springreactor.model.Menu;
import com.pe.fhuachaca.app.springreactor.repository.IClienteRepository;
import com.pe.fhuachaca.app.springreactor.repository.IGenericRepository;
import com.pe.fhuachaca.app.springreactor.repository.IMenuRepository;
import com.pe.fhuachaca.app.springreactor.service.IClienteService;
import com.pe.fhuachaca.app.springreactor.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl extends CrudServiceImpl<Menu, String> implements IMenuService {

    @Autowired
    private IMenuRepository repository;


    @Override
    protected IGenericRepository<Menu, String> getRepository() {
        return repository;
    }
}
