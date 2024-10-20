package org.premios.strategy;

import org.premios.adapter.AdaptadorCalculoDistancia;
import org.premios.dto.DtoCalcularDistancia;
import org.premios.entity.*;
import org.premios.factory.FactoriaAdaptadorCalculoDistacia;
import org.premios.repository.IndireccionPersistencia;

import java.util.Date;
import java.util.List;

public class EstrategiaPorDistancia implements EstrategiaObtenerCategoria {

    @Override
    public Categoria obtenerCategoria(Cliente cliente) {

        // Crear DTO para calcular la distancia entre cliente y sucursal
        DtoCalcularDistancia dtoCalcularDistancia = new DtoCalcularDistancia();
        dtoCalcularDistancia.setOrigen(cliente.getDomicilioCliente());  // Origen: domicilio del cliente

        Sucursal sucursal = cliente.getSucursalCliente();  // Obtener sucursal del cliente
        dtoCalcularDistancia.setDestino(sucursal.getDomicilioSucursal());  // Destino: domicilio de la sucursal

        // Obtener el adaptador de cálculo de distancia (Factory y Adapter)
        final FactoriaAdaptadorCalculoDistacia factoriaAdaptadorCalculoDistacia = FactoriaAdaptadorCalculoDistacia.getInstancia();
        AdaptadorCalculoDistancia adaptadorCalculoDistancia = factoriaAdaptadorCalculoDistacia.obtenerAdaptadorCalculoDistancia();

        // Calcular la distancia entre el cliente y la sucursal
        Double distanciaCalculada = adaptadorCalculoDistancia.obtenerDistancia(dtoCalcularDistancia);

        // Usar la persistencia para buscar las facturas del cliente
        final IndireccionPersistencia indireccionPersistencia = IndireccionPersistencia.getInstancia();
        List<Factura> facturas = indireccionPersistencia.buscarFacturas("cliente='" + cliente.toString() + "' AND fecha.año=" + new Date().getYear());

        // Calcular el monto total de todas las facturas del cliente
        Double montoTotal = 0.0;
        for (Factura factura : facturas) {
            montoTotal += factura.getMontoFactura();
        }

        // Buscar el catálogo por distancia según el monto total y la distancia calculada
        CatalogoPorDistancia catalogoPorDistancia = indireccionPersistencia.buscarCatalogoPorDistancia(
                "montoMinimo <= " + montoTotal +
                        " AND montoMaximo >= " + montoTotal +
                        " AND distanciaMinima <= " + distanciaCalculada +
                        " AND distanciaMaxima >= " + distanciaCalculada
        );

        // Obtener la categoría del catálogo
        Categoria categoria = catalogoPorDistancia.getCategoriaCatalogoPorDistancia();

        return categoria;  // Retornar la categoría obtenida
    }
}
