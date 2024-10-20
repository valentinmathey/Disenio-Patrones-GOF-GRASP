package org.premios.strategy;

import org.premios.entity.Categoria;
import org.premios.entity.Cliente;

public interface EstrategiaObtenerCategoria {

    public Categoria obtenerCategoria(Cliente cliente);
}
