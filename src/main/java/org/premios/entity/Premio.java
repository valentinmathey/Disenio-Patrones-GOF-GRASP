package org.premios.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Premio {

    private int codPremio;

    private String nombrePremio;

    private Date fechaBajaPremio;

    private String descripcionPremio;

    private int valorPremio;

    private Categoria categoriaPremio;
}
