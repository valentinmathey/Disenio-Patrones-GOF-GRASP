package org.premios.factory;

import org.premios.adapter.AdaptadorCalculoDistancia;
import org.premios.adapter.AdaptadorCalculoDistanciaGoogleMaps;
import org.premios.adapter.AdapterCalculoDistanciaHereMaps;
import org.premios.adapter.ParametroAdaptadorCalculoDistancia;
import org.premios.repository.IndireccionPersistencia;

public class FactoriaAdaptadorCalculoDistacia {

    // Instancia estática única de la clase (Singleton)
    private static FactoriaAdaptadorCalculoDistacia instancia;

    // Constructor privado para evitar instanciación externa
    private FactoriaAdaptadorCalculoDistacia() {}

    // Método público y estático para obtener la única instancia (Singleton)
    public static FactoriaAdaptadorCalculoDistacia getInstancia() {
        if (instancia == null) {
            instancia = new FactoriaAdaptadorCalculoDistacia();
        }
        return instancia;
    }

    // Método para obtener el adaptador adecuado para calcular la distancia
    public AdaptadorCalculoDistancia obtenerAdaptadorCalculoDistancia() {

        // Obtener instancia de la persistencia
        final IndireccionPersistencia indireccionPersistencia = IndireccionPersistencia.getInstancia();

        // Buscar los parámetros del adaptador para determinar cuál usar
        ParametroAdaptadorCalculoDistancia parametroAdaptadorCalculoDistancia = indireccionPersistencia.buscarParametroAdaptadorCalculoDistancia("");

        // Si el parámetro indica "Google Maps", usar el adaptador correspondiente
        if (parametroAdaptadorCalculoDistancia.getNombre().equals("Google Maps")) {
            return new AdaptadorCalculoDistanciaGoogleMaps();

            // Si no, usar el adaptador de Here Maps
        } else {
            return new AdapterCalculoDistanciaHereMaps();
        }
    }
}
