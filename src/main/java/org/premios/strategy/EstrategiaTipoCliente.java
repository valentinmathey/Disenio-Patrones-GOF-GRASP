package org.premios.strategy;

import org.premios.entity.*;
import org.premios.repository.IndireccionPersistencia;

import java.util.Date;
import java.util.List;

public class EstrategiaTipoCliente implements EstrategiaObtenerCategoria {

    @Override
    public Categoria obtenerCategoria(Cliente cliente) {

        // Obtener instancia de la clase de persistencia (Singleton)
        final IndireccionPersistencia indireccionPersistencia = IndireccionPersistencia.getInstancia();

        // Obtener el tipo de cliente
        TipoCliente tipoCliente = cliente.getTipoCliente();

        // Buscar las facturas del cliente en el año actual
        List<Factura> facturas = indireccionPersistencia.buscarFacturas("cliente='" + cliente.toString() + "' AND fecha.año=" + new Date().getYear());

        // Calcular el monto total de todas las facturas del cliente
        Double montoTotal = 0.0;
        for (Factura factura : facturas) {
            montoTotal += factura.getMontoFactura();
        }

        // Buscar el catálogo basado en el tipo de cliente y el monto total
        CatalogoPorDistancia catalogoPorDistancia = indireccionPersistencia.buscarCatalogoPorDistancia(
                "tipoCliente='" + tipoCliente.toString() + "' AND montoMinimo <= " + montoTotal + " AND montoMaximo >= " + montoTotal
        );

        // Obtener la categoría del catálogo
        Categoria categoria = catalogoPorDistancia.getCategoriaCatalogoPorDistancia();

        return categoria;  // Retornar la categoría obtenida
    }
}
