package org.example;

import org.example.entity.Categoria;

public class EstrategiaTipoCliente implements EstrategiaObtenerCategoria{
    @Override
    public Categoria obtenerCategoria() {

        //Algoritmo para obtener la categoria

        return new Categoria();
    }
}
