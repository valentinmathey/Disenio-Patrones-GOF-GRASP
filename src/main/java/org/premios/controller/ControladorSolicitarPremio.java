package org.premios.controller;

import org.premios.dto.DtoPremio;
import org.premios.service.Experto;

import java.util.List;

public class ControladorSolicitarPremio {

    // Instancia del experto que maneja la lógica de negocio
    private Experto expertoSolicitarPremio;

    // Obtiene los premios disponibles para el cliente
    public List<DtoPremio> obtenerPremios(String usuario, String contraseña) {
        return expertoSolicitarPremio.obtenerPremios(usuario, contraseña);
    }

    // Genera una solicitud de premio para un cliente
    public void generarSolicitud(int codPremio, int dniCliente) {
        expertoSolicitarPremio.generarSolicitud(codPremio, dniCliente);
    }
}
