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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author irvin
 */
@Data//proporciona métodos principales sin definirlos
@Entity//declare clase como entidad
public class TBL_Detalle_Historial_Planillas implements Serializable{
//variables
    private static final long serialVersionUID = 1L;//constante propia de la clase serializable

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_detalle;
//mapeo de llaves entre tablas
    @JoinColumn(name = "id_empleado", nullable = false)
    @ManyToOne(optional = false)
    private TBL_Empleados empleado;

    @JoinColumn(name = "id_Hist_Planilla", nullable = false)
    @ManyToOne(optional = false)
    private TBL_Historial_Planillas planilla;

    @NotNull(message = "El salario base es requerido")
    private double salario_base;

    @NotNull(message = "El salario bruto es requerido")
    private double salario_bruto;

    @NotNull(message = "El salario neto es requerido")
    private double salario_neto;

    @NotEmpty(message = "El nombre es requerido")
    private String nombre_puesto;

    @OneToMany(mappedBy = "historial")
    private List<TBL_Detalle_Pagos> pagos;

    @OneToMany(mappedBy = "historial")
    private List<TBL_Detalle_Deducciones> deducciones;
}
