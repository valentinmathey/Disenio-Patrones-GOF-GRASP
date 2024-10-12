package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogoTipoCliente {

    private float montoDesdeCatalogoTipoCliente;

    private float montoHastaCatalogoTipoCliente;

    private Categoria categoriaCatalogoTipoCliente;

    private TipoCliente tipoClienteCatalogo;
}
