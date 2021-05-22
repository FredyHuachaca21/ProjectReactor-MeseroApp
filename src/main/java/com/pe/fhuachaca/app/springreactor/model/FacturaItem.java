package com.pe.fhuachaca.app.springreactor.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "facturaItems")
@Data
public class FacturaItem {

    private Integer cantidad;
    private Plato plato;
}
