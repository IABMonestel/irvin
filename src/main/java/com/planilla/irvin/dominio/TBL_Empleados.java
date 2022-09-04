/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.planilla.irvin.dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author irvin
 */
@Data//proporciona métodos principales sin definirlos
@Entity//declare clase como entidad
public class TBL_Empleados implements Serializable {
//variables

    private static final long serialVersionUID = 1L;//constante propia de la clase serializable

    @Id
    private String id_empleado;

    @NotEmpty(message = "El nombre es requerido")
    private String nombre;

    @NotEmpty(message = "El apellido es requerido")
    private String apellido1;

    @NotEmpty(message = "El apellido es requerido")
    private String apellido2;
//mapeo de tablas
    @JoinColumn(name = "id_puesto", nullable = true)
    @OneToOne(optional = false)
    private TBL_Puestos puesto;

    @NotEmpty(message = "El teléfono es requerido")
    private String telefono;

    @NotEmpty(message = "El email es requerido")
    private String email;

    private String provincia;

    private String canton;

    private String distrito;

    private String dir_exacta;
//tipo de fecha
    @Column(name = "Fecha_Ingreso")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date fecha_ingreso;

    @Column(name = "Fecha_Nacimiento")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date fecha_nacimiento;

    private boolean activo;

    @NotEmpty(message = "La cuenta cliente es requerida")
    private String cuenta_cliente;

    private short puntos_carrera_profesional;

    //mappedBy
    @OneToMany(mappedBy = "empleado")
    private List<TBL_Incapacidades> incapacidades;

    @OneToMany(mappedBy = "empleado")
    private List<TBL_Pensiones> pensiones;

    @OneToMany(mappedBy = "empleado")
    private List<TBL_Titulos> titulos;

    @OneToMany(mappedBy = "empleado")
    private List<TBL_Detalle_Historial_Planillas> detalles;

}
