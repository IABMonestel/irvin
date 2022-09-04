/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.planilla.irvin.services;

import com.planilla.irvin.dominio.TBL_Empleados;
import java.util.List;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author irvin
 */
//define métodos para empleados
public interface IServiceEmpleado {
//lista de empleados

    public List<TBL_Empleados> listarEmpleados();
//lista con filtro

    public List<TBL_Empleados> listarEmpleados(String nombre);
//guarda un empleado

    public void guardar(TBL_Empleados empleado);
//borrado lógico de empleado

    public Boolean eliminar(TBL_Empleados empleado);
//obtiene un empleado

    public TBL_Empleados obtenerEmpleado(TBL_Empleados empleado);
//Empleados por planilla Extraordinaria

    public Iterable<TBL_Empleados> findByPlanilla(long id_planilla);

}
