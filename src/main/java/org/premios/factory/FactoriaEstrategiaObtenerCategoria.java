package org.premios.factory;

import org.premios.entity.Sucursal;
import org.premios.strategy.EstrategiaObtenerCategoria;
import org.premios.strategy.EstrategiaPorDistancia;
import org.premios.strategy.EstrategiaTipoCliente;

public class FactoriaEstrategiaObtenerCategoria {

    // Instancia estática única de la clase (Singleton)
    private static FactoriaEstrategiaObtenerCategoria instancia;

    // Constructor privado para evitar instanciación externa
    private FactoriaEstrategiaObtenerCategoria() {}

    // Método público y estático para obtener la única instancia (Singleton)
    public static FactoriaEstrategiaObtenerCategoria getInstancia() {
        if (instancia == null) {
            instancia = new FactoriaEstrategiaObtenerCategoria();
        }
        return instancia;
    }

    // Método para obtener la estrategia en función de la sucursal
    public EstrategiaObtenerCategoria obtenerEstrategia(Sucursal sucursal) {

        // Si el método de la sucursal es "Interior", usa la estrategia por distancia
        if (sucursal.getMetodoSucursal().getNombreMetodo().equals("Interior")) {
            return new EstrategiaPorDistancia();

            // Si no, usa la estrategia por tipo de cliente
        } else {
            return new EstrategiaTipoCliente();
        }
    }
}
