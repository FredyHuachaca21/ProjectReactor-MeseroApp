package com.pe.fhuachaca.app.springreactor.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "facturas")
@Data
public class Factura {

    @Id
    private String id;

    @Size(min = 3)
    @Field(name = "descripcion")
    private String descripcion;

    @Field(name = "observacion")
    private String observacion;

    @NotNull
    @Field(name = "cliente")
    private Cliente cliente;

    @Field(name = "items")
    private List<FacturaItem> items;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime creadoEn = LocalDateTime.now();
}
