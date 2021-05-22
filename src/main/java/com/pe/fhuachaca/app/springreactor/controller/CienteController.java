package com.pe.fhuachaca.app.springreactor.controller;

import com.pe.fhuachaca.app.springreactor.model.Cliente;
import com.pe.fhuachaca.app.springreactor.model.Plato;
import com.pe.fhuachaca.app.springreactor.service.IClienteService;
import com.pe.fhuachaca.app.springreactor.service.IPlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/clientes")
public class CienteController {

    @Autowired
    private IClienteService service;

    @GetMapping
    public Mono<ResponseEntity<Flux<Cliente>>> listar() {
        Flux<Cliente> fxCliente = service.listar();
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fxCliente));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Cliente>> listarPorId(@PathVariable("id") String id) {
        return service.listarPorId(id)
                .map(p -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Cliente>> registrar(@Valid @RequestBody Cliente cliente, final ServerHttpRequest REQ) {
        return service.registrar(cliente)
                .map(p -> ResponseEntity
                        .created(URI.create(REQ.getURI()
                                .toString().concat("/")
                                .concat(p.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Cliente>> modificar(@Valid @RequestBody Cliente cliente, @PathVariable("id") String id) {
        Mono<Cliente> monoCliente = Mono.just(cliente);
        Mono<Cliente> monoDB = service.listarPorId(id);

        return monoDB
                .zipWith(monoCliente, (db, p) -> {
                    db.setId(id);
                    db.setNombres(p.getNombres());
                    db.setAplledidos(p.getAplledidos());
                    db.setFechNac(p.getFechNac());
                    db.setUrlFoto(p.getUrlFoto());
                    return db;
                })
                .flatMap(service::modificar) //p -> service.modificar(p)
                .map(p -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .defaultIfEmpty(new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminar(@PathVariable("id") String id) {
      return  service.listarPorId(id)
                .flatMap(p -> {
                    return service.eliminar(p.getId())
                            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
                })
                .defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }


}
