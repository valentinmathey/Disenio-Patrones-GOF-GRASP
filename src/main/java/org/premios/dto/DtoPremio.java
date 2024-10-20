package org.premios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoPremio {

    private int codDtoPremio;

    private String descripcionDtoPremio;

    private String nombreDtoPremio;

    private int valorDtoPremio;
}
