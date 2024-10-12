package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Solicitud {

    private int nroSolicitud;

    private Date fechaRegistroSolicitud;

    private Premio premioSolicitud;

    private Cliente clienteSolicitud;
}
