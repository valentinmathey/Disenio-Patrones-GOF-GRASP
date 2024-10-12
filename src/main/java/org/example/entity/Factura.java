package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factura {

    private int nroFactura;

    private float montoFactura;

    private Date fechaFactura;

    private Cliente clienteFactura;

    private Sucursal sucursalFactura;
}
