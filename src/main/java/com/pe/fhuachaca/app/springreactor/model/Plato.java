package com.pe.fhuachaca.app.springreactor.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "platos")
@Data
public class Plato {

    @Id
    private String id;

    @Field(name = "nombre")
    private String nombre;

    private Double precio;

    @Field(name = "estado")
    private Boolean estado;
}
