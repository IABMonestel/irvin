/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.planilla.irvin.dominio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author irvin
 */
@Data//proporciona métodos principales sin definirlos
@Entity//declare clase como entidad
public class TBL_Historial_Planillas implements Serializable {
//variables
    private static final long serialVersionUID = 1L;//constante propia de la clase serializable

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_historial;

    @NotNull(message = "El año es requerido")
    private short anio;

    @NotNull(message = "El mes es requerido")
    private short mes;

    private boolean ordinaria;

    private boolean anulada;

    private boolean cancelada;
//mapeo entre tablas
    @OneToMany(mappedBy = "planilla")
    private List<TBL_Detalle_Historial_Planillas> detalles;

}
