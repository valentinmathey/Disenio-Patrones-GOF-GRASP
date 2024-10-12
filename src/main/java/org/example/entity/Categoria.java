package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    private String codCategoria;

    private String nombreCategoria;

    private Date fechaBajaCategoria;

    private List<Premio> premios;
}
