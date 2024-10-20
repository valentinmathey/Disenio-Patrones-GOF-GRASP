package org.premios.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogoPorDistancia {

    private float montoDesdeCatalogoPorDistancia;

    private float montoHastaCatalogoPorDistancia;

    private float distanciaDesdeCatalogoPorDistancia;

    private float distanciaHastaCatalogoPorDistancia;

    private Categoria categoriaCatalogoPorDistancia;
}
