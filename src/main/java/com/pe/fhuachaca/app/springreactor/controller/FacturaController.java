package com.pe.fhuachaca.app.springreactor.controller;

import com.pe.fhuachaca.app.springreactor.model.Factura;
import com.pe.fhuachaca.app.springreactor.model.Plato;
import com.pe.fhuachaca.app.springreactor.service.IFacturaService;
import com.pe.fhuachaca.app.springreactor.service.IPlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/facturas")
public class FacturaController {

    @Autowired
    private IFacturaService service;

    @GetMapping
    public Mono<ResponseEntity<Flux<Factura>>> listar() {
        Flux<Factura> fxFactura = service.listar();
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fxFactura));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Factura>> listarPorId(@PathVariable("id") String id) {
        return service.listarPorId(id)
                .map(p -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Factura>> registrar(@RequestBody Factura factura, final ServerHttpRequest REQ) {
        return service.registrar(factura)
                .map(p -> ResponseEntity
                        .created(URI.create(REQ.getURI()
                                .toString().concat("/")
                                .concat(p.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Factura>> modificar(@RequestBody Factura factura, @PathVariable("id") String id) {
        Mono<Factura> monoFactura = Mono.just(factura);
        Mono<Factura> monoDB = service.listarPorId(id);

        return monoDB
                .zipWith(monoFactura, (db, f) -> {
                    db.setId(id);
                    db.setDescripcion(f.getDescripcion());
                    db.setObservacion(f.getObservacion());
                    db.setCreadoEn(f.getCreadoEn());
                    db.setCliente(f.getCliente());
                    db.setItems(f.getItems());
                    return db;
                })
                .flatMap(service::modificar) //p -> service.modificar(p)
                .map(p -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .defaultIfEmpty(new ResponseEntity<Factura>(HttpStatus.NO_CONTENT));
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
