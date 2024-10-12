package org.example;

import org.example.entity.Sucursal;

public class FactoriaEstrategiaObtenerCategoria {

    public EstrategiaObtenerCategoria obtenerEstrategia(Sucursal sucursal){
        if (sucursal.getMetodoSucursal().getNombreMetodo().equals("MENDOZA")){
            return new EstrategiaPorDistancia();
        }

        if (sucursal.getMetodoSucursal().getNombreMetodo().equals("BUENOS AIRES")){
            return new EstrategiaTipoCliente();
        }else {
            return new EstrategiaBase();
        }
    }
}
