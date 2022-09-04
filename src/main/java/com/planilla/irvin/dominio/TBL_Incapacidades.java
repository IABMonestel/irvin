/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.planilla.irvin.dominio;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author irvin
 */
@Data//proporciona métodos principales sin definirlos
@Entity//declare clase como entidad
public class TBL_Incapacidades implements Serializable {
//atributos
    private static final long serialVersionUID = 1L;//constante propia de la clase serializable

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;
//mapeo entre tablas
    @JoinColumn(name = "id_empleado", nullable = false)
    @ManyToOne(optional = false)
    private TBL_Empleados empleado;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Calendar fecha_inicio;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Calendar fecha_final;

}
