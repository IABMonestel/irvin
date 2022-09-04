/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.planilla.irvin.dominio;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author irvin
 */
@Data//proporciona métodos principales sin definirlos
@Entity//declare clase como entidad
public class TBL_Detalle_Deducciones implements Serializable{
//variables
    private static final long serialVersionUID = 1L;//constante propia de la clase serializable

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_detalle_deduccion;
//mapeo de llaves entre tablas
    @JoinColumn(name = "codigo", nullable = false)
    @ManyToOne(optional = false)
    private TBL_Deducciones deduccion;

    @JoinColumn(name = "id_Historial", nullable = false)
    @ManyToOne(optional = false)
    private TBL_Detalle_Historial_Planillas historial;

    @NotNull(message = "El monto es requerido")
    private double monto;

}
