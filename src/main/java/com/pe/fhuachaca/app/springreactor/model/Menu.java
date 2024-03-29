package com.pe.fhuachaca.app.springreactor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "menus")
public class Menu {

    @Id
    private String id;

    @Field(name = "icono")
    private String icono;

    @Field(name = "nombre")
    private String nombre;

    @Field(name = "url")
    private String url;

    private List<String> roles;

}
