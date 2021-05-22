package com.pe.fhuachaca.app.springreactor.controller;

import com.pe.fhuachaca.app.springreactor.model.Plato;
import com.pe.fhuachaca.app.springreactor.service.IPlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static reactor.function.TupleUtils.function;

@RestController
@RequestMapping("/platos")
public class PlatoController {

    @Autowired
    private IPlatoService service;

    @GetMapping
    public Mono<ResponseEntity<Flux<Plato>>> listar() {
        Flux<Plato> fxPlato = service.listar();
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fxPlato));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Plato>> listarPorId(@PathVariable("id") String id) {
        return service.listarPorId(id)
                .map(p -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Plato>> registrar(@Valid  @RequestBody Plato plato, final ServerHttpRequest REQ) {
        return service.registrar(plato)
                .map(p -> ResponseEntity
                        .created(URI.create(REQ.getURI()
                                .toString().concat("/")
                                .concat(p.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Plato>> modificar(@Valid @RequestBody Plato plato, @PathVariable("id") String id) {
        Mono<Plato> monoPlato = Mono.just(plato);
        Mono<Plato> monoDB = service.listarPorId(id);

        return monoDB
                .zipWith(monoPlato, (db, p) -> {
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
    public Mono<ResponseEntity<Void>> eliminar(@PathVariable("id") String id) {
      return  service.listarPorId(id)
                .flatMap(p -> {
                    return service.eliminar(p.getId())
                            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
                })
                .defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

    private Plato platoHateoas;

    @GetMapping("/hateoas/{id}")
    public Mono<EntityModel<Plato>> listarHateoasPorId(@PathVariable("id") String id){
        Mono<Link> link1 = Mono.just(linkTo(methodOn(PlatoController.class).listarPorId(id)).withSelfRel());
        Mono<Link> link2 = Mono.just(linkTo(methodOn(PlatoController.class).listarPorId(id)).withSelfRel());

        //PRACTICA NO RECOMENDADA
        /*return service.listarPorId(id)
                .flatMap(p -> {
                    this.platoHateoas = p;
                    return link1;
                })
                .map(link -> {
                    return EntityModel.of(this.platoHateoas, link);
                });*/

        //PRACTICA INTERMEDIA
        /*return service.listarPorId(id)
                .flatMap(p->{
                    return link1.map(link ->EntityModel.of(p, link));
                });*/

        //PRACTICA IDEAL
        /*return service.listarPorId(id)
                .zipWith(link1, (p, link)-> EntityModel.of(p, link));*/

        //Mas de un Link
        return link1.zipWith(link2)
                .map(function((left, right)->Links.of(left, right)))
                .zipWith(service.listarPorId(id), (link, p) -> EntityModel.of(p, link));

    }




}
