package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoCliente {

    private String codTipoCliente;

    private String nombreTipoCliente;

    private Date fechaBajaTipoCliente;
}
