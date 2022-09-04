/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.planilla.irvin.dominio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author irvin
 */
@Data//proporciona métodos principales sin definirlos
@Entity//declare clase como entidad
@Table(name = "TBL_Puestos")
public class TBL_Puestos implements Serializable {
//variables
    private static final long serialVersionUID = 1L;//constante propia de la clase serializable

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_puesto;
//mapeo entre tablas
    @Column(name = "nombre_puesto")
    @NotEmpty(message = "El nombre es requerido")
    private String nombre;

    @NotNull(message = "La categoría es requerida")
    private short categoria;

    @NotNull(message = "El salario base es requerido")
    private double salario_base;

    private boolean activo;

    @Column(name = "grado_minimo")
    @NotEmpty(message = "El grado es requerido")
    private String grado;
}
