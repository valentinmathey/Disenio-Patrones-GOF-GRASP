package org.premios.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sucursal {

    private int nroSucursal;

    private String domicilioSucursal;

    private Date fechaBajaSucursal;

    private Metodo metodoSucursal;
}
