package org.premios.adapter;

import org.premios.dto.DtoCalcularDistancia;

public class AdaptadorCalculoDistanciaGoogleMaps implements AdaptadorCalculoDistancia {

    @Override
    public double obtenerDistancia(DtoCalcularDistancia dtoCalcularDistancia) {
        // Simulamos una conexión con la API de Google Maps y devolvemos la distancia calculada
        // En una implementación real, se haría la llamada a la API de Google Maps aquí
        return 0.0;
    }
}
