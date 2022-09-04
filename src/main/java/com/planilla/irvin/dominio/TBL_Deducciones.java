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
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author irvin
 */
@Data//anotacion data pproporciona métodos principales sin definirlos
@Entity//define clase como entidad
public class TBL_Deducciones implements Serializable {

    private static final long serialVersionUID = 1L;//constante propia de la clase serializable
//variables
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

    private boolean renta;

    private boolean pension;
//mapeo de llaves
    @OneToMany(mappedBy = "deduccion")
    private List<TBL_Detalle_Deducciones> deducciones;

//    @OneToOne(mappedBy = "id_deduccion")
//    private TBL_Topes_Renta id_deduccion;

}
