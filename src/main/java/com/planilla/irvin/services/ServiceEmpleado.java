/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.planilla.irvin.services;

import com.planilla.irvin.dao.IEmpleadoDAO;
import com.planilla.irvin.dominio.TBL_Empleados;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author irvin
 */
@Service//define clase como servicio 
public class ServiceEmpleado implements IServiceEmpleado {

    @Autowired//Cuando el servicio necesite la instancia la genera automaticamente//InyecciÃ³n de dependencias
    private IEmpleadoDAO empleadoDao;

    @Transactional(readOnly = true)//solo consulta no hace transacciones
    @Override
    public List<TBL_Empleados> listarEmpleados() {
        return empleadoDao.findAll();//lista de todos los empleados
    }

    @Transactional(readOnly = true)//solo consulta no hace transacciones
    @Override
    public List<TBL_Empleados> listarEmpleados(String nombre) {
        return (List<TBL_Empleados>) empleadoDao.findByNombreContainsOrApellido1Contains(nombre, nombre);
//lista de empleados con filtro
    }

    @Transactional
    @Override
    public void guardar(TBL_Empleados empleado) {
        empleadoDao.save(empleado);//guarda un empleado
    }

    @Override
    public Boolean eliminar(TBL_Empleados empleado) {
//borrado lógico de empleado
        return empleadoDao.eliminar_Empleado(empleado.getId_empleado());
    }

    @Override//obtiene un empleado
    public TBL_Empleados obtenerEmpleado(TBL_Empleados empleado) {
        return empleadoDao.findById(empleado.getId_empleado()).orElse(null);
    }

    @Override//planilla extraordinaria
    public Iterable<TBL_Empleados> findByPlanilla(long id_planilla) {
        return empleadoDao.findByPlanilla(id_planilla);
    }

}
