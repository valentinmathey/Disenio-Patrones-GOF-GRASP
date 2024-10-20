package org.premios.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    private String nombreCliente;

    private String domicilioCliente;

    private int dniCliente;

    private String usuario;

    private String contraseña;

    private Sucursal sucursalCliente;

    private TipoCliente tipoCliente;
}
