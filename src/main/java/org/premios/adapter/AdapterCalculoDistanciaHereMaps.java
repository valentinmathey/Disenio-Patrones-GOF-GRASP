package org.premios.adapter;

import org.premios.dto.DtoCalcularDistancia;

public class AdapterCalculoDistanciaHereMaps implements AdaptadorCalculoDistancia {

    @Override
    public double obtenerDistancia(DtoCalcularDistancia dtoCalcularDistancia) {
        // Simulamos una conexión con la API de Here Maps y devolvemos la distancia calculada
        // En una implementación real, aquí se haría la llamada a la API de Here Maps
        return 0.0;
    }
}
