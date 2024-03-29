package com.pe.fhuachaca.app.springreactor.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Document(collection = "platos")
@Data
public class Plato {

    @Id
    private String id;

    @Field(name = "nombre")
    @NotEmpty
    private String nombre;

    private Double precio;

    @Field(name = "estado")
    @NotNull
    private Boolean estado;
}
