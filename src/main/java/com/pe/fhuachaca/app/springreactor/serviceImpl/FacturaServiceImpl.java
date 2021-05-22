package com.pe.fhuachaca.app.springreactor.serviceImpl;

import com.pe.fhuachaca.app.springreactor.model.Cliente;
import com.pe.fhuachaca.app.springreactor.model.Factura;
import com.pe.fhuachaca.app.springreactor.repository.IClienteRepository;
import com.pe.fhuachaca.app.springreactor.repository.IFacturaRepository;
import com.pe.fhuachaca.app.springreactor.repository.IGenericRepository;
import com.pe.fhuachaca.app.springreactor.service.IClienteService;
import com.pe.fhuachaca.app.springreactor.service.IFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaServiceImpl extends CrudServiceImpl<Factura, String> implements IFacturaService {

    @Autowired
    private IFacturaRepository repository;


    @Override
    protected IGenericRepository<Factura, String> getRepository() {
        return repository;
    }
}
