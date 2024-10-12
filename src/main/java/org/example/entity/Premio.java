package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Premio {

    private String codPremio;

    private String nombrePremio;

    private Date fechaBajaPremio;

    private String descripcionPremio;

    private float valorPremio;

    private Categoria categoriaPremio;
}
