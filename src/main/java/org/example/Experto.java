package org.example;

import org.example.entity.Categoria;
import org.example.entity.Cliente;
import org.example.entity.Premio;

import java.util.List;

public class Experto {

    public List<Premio> obtenerPremios(Cliente cliente){
        FactoriaEstrategiaObtenerCategoria factoria = new FactoriaEstrategiaObtenerCategoria();
        EstrategiaObtenerCategoria estrategia = factoria.obtenerEstrategia(cliente.getSucursalCliente());
        Categoria categoria = estrategia.obtenerCategoria();
        return categoria.getPremios();
    }
}

/* Experto sin aplicar patrones GOF (Strategy y Factory)

public class Experto {

    public void obtenerPremios(Cliente cliente){
        if (sucursal.getMetodoSucursal().getNombreMetodo().equals("MENDOZA")){
            //Algoritmo para obtener la categoria
        }

        if (sucursal.getMetodoSucursal().getNombreMetodo().equals("BUENOS AIRES")){
            //Algoritmo para obtener la categoria
        }
    }
}

 */