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
public class TBL_Pensiones implements Serializable {
//variables de clase
    private static final long serialVersionUID = 1L;//constante propia de la clase serializable

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;
//mapeo entre tablas
    @JoinColumn(name = "id_empleado", nullable = false)
    @ManyToOne(optional = false)
    private TBL_Empleados empleado;

    @NotNull(message = "El monto es requerido")
    private double monto;

}
