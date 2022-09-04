/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.planilla.irvin.services;

import com.planilla.irvin.dominio.TBL_Puestos;
import java.util.List;

/**
 *
 * @author irvin
 */
//métodos principales para puestos
public interface IServicePuesto {
//lista de puestos
    public List<TBL_Puestos> listarPuestos();
//lista de puestos con filtro
    public List<TBL_Puestos> listarPuestos(String grado);
//guarda un puesto
    public void guardar(TBL_Puestos puesto);
//borrado lógico de puestos
    public void eliminar(TBL_Puestos puesto);
//obtiene un puesto
    public TBL_Puestos obtenerPuesto(TBL_Puestos puesto);
    //obtener puestos para un empleado
    public Iterable<TBL_Puestos> findByEmpleado(String id_empleado);
}
