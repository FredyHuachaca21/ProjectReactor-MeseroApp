package com.pe.fhuachaca.app.springreactor.serviceImpl;

import com.pe.fhuachaca.app.springreactor.model.Cliente;
import com.pe.fhuachaca.app.springreactor.model.Usuario;
import com.pe.fhuachaca.app.springreactor.repository.IClienteRepository;
import com.pe.fhuachaca.app.springreactor.repository.IGenericRepository;
import com.pe.fhuachaca.app.springreactor.repository.IUsuarioRepository;
import com.pe.fhuachaca.app.springreactor.service.IClienteService;
import com.pe.fhuachaca.app.springreactor.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl extends CrudServiceImpl<Usuario, String> implements IUsuarioService {

    @Autowired
    private IUsuarioRepository repository;


    @Override
    protected IGenericRepository<Usuario, String> getRepository() {
        return repository;
    }
}
