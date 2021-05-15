package com.pe.fhuachaca.app.springreactor.controller;

import com.pe.fhuachaca.app.springreactor.model.Plato;
import com.pe.fhuachaca.app.springreactor.service.IPlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/platos")
public class PlatoController {

    @Autowired
    private IPlatoService service;

    @GetMapping
    public Flux<Plato> listar(){
        return service.listar();
    }

    @PostMapping
    public Mono<Plato> registrar(@RequestBody Plato plato){
        return service.registrar(plato);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Plato>> modificar(@RequestBody Plato plato, @PathVariable String id){
        Mono<Plato> monoPlato = Mono.just(plato);
        Mono<Plato> monoDB = service.listarPorId(id);

        return monoDB
                .zipWith(monoPlato, (db, p)->{
                    db.setId(id);
                    db.setNombre(p.getNombre());
                    db.setPrecio(p.getPrecio());
                    db.setEstado(p.getEstado());
                    return db;
                })
                .flatMap(service::modificar) //p -> service.modificar(p)
                .map(p -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .defaultIfEmpty(new ResponseEntity<Plato>(HttpStatus.NO_CONTENT));
    }

    @DeleteMapping("/{id}")
    public void eliminar(String id){
        service.eliminar(id);
    }


}
