package org.premios.service;

import org.premios.dto.DtoPremio;
import org.premios.entity.*;
import org.premios.factory.FactoriaEstrategiaObtenerCategoria;
import org.premios.repository.IndireccionPersistencia;
import org.premios.strategy.EstrategiaObtenerCategoria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Experto {

    // Método para obtener los premios disponibles para un cliente
    public List<DtoPremio> obtenerPremios(String usuario, String contraseña) {

        // Instancia de IndireccionPersistencia (Singleton)
        final IndireccionPersistencia indireccionPersistencia = IndireccionPersistencia.getInstancia();

        // Buscar el cliente en la base de datos
        Cliente cliente = indireccionPersistencia.buscarCliente("usuario='" + usuario + "' AND contraseña='" + contraseña + "'");

        // Si no existe el cliente, se termina el caso
        if (cliente == null) {
            System.out.println("Cliente no encontrado");
            return new ArrayList<>();
        }

        // Buscar si ya tiene una solicitud en el año actual
        Date fechaActual = new Date(); // Definir la fecha actual
        Solicitud solicitud = indireccionPersistencia.buscarSolicitud("cliente='" + cliente.toString() + "' AND fechaSolicitud.año=" + fechaActual.getYear());

        // Si ya existe una solicitud, se termina el caso
        if (solicitud != null) {
            System.out.println("Ya existe una solicitud para este cliente.");
            return new ArrayList<>();
        }

        // Obtener la sucursal del cliente
        Sucursal sucursal = cliente.getSucursalCliente();

        // Obtener la estrategia adecuada según la sucursal (Strategy)
        FactoriaEstrategiaObtenerCategoria factoria = FactoriaEstrategiaObtenerCategoria.getInstancia();
        EstrategiaObtenerCategoria estrategia = factoria.obtenerEstrategia(sucursal);

        // Determinar la categoría del cliente usando la estrategia
        Categoria categoria = estrategia.obtenerCategoria(cliente);

        // Buscar los premios disponibles según la categoría
        List<Premio> premios = indireccionPersistencia.buscarPremiosPorCategoria("categoria='" + categoria.toString() + "' AND fechaBajaPremio IS NULL");

        // Convertir los premios a DTO
        List<DtoPremio> DtoPremios = new ArrayList<>();

        for (Premio premio : premios) {
            DtoPremio dtoPremio = new DtoPremio();
            dtoPremio.setCodDtoPremio(premio.getCodPremio());
            dtoPremio.setValorDtoPremio(premio.getValorPremio());
            dtoPremio.setNombreDtoPremio(premio.getNombrePremio());
            dtoPremio.setDescripcionDtoPremio(premio.getDescripcionPremio());

            DtoPremios.add(dtoPremio);
        }

        return DtoPremios;
    }

    // Método para generar una solicitud de premio para un cliente
    public void generarSolicitud(int codPremio, int dniCliente) {

        // Instancia de IndireccionPersistencia (Singleton)
        final IndireccionPersistencia indireccionPersistencia = IndireccionPersistencia.getInstancia();

        // Buscar el premio por su código
        Premio premio = indireccionPersistencia.buscarPremio("codPremio=" + codPremio);

        // Buscar el cliente en la base de datos
        Cliente cliente = indireccionPersistencia.buscarCliente("dniCliente=" + dniCliente);

        // Crear una nueva solicitud
        Solicitud solicitud = new Solicitud();
        solicitud.setFechaRegistroSolicitud(new Date());
        solicitud.setPremioSolicitud(premio);
        solicitud.setClienteSolicitud(cliente);

        // Guardar la solicitud en la base de datos
        indireccionPersistencia.guardarSolicitud(solicitud);
    }
}
