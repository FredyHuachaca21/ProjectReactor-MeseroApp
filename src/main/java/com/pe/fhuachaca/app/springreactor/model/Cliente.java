package com.pe.fhuachaca.app.springreactor.model;

import lombok.Data;
import org.springframework.context.annotation.Primary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.time.LocalDate;

@Document(collection = "clientes")
@Data
public class Cliente {

    @Id
    private String id;

    @Field(name = "nombres")
    @Size(min = 3)
    private String nombres;

    @Field(name = "apellidos")
    private String aplledidos;

    @DateTimeFormat(pattern = "dd/MM/yy")
    private LocalDate fechNac;

    @Field(name = "urlFoto")
    private String urlFoto;
}
