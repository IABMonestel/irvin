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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author irvin
 */
@Data//proporciona métodos principales sin definirlos
@Entity//declare clase como entidad
public class TBL_Pagos implements Serializable{
//atributos
    private static final long serialVersionUID = 1L;//constante propia de la clase serializable

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;

    @NotEmpty(message = "El nombre es requerido")
    private String nombre;

    private boolean porcentaje;

    @NotNull(message = "El monto es requerido")
    private double monto;

    private boolean activo;

    private short categoria;

    private boolean anualidad;

    private boolean carrera_profesional;
//mapeo entre clases
    @OneToMany(mappedBy = "pago")
    private List<TBL_Detalle_Pagos> pagos;

}
