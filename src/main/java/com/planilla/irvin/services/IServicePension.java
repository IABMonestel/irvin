/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.planilla.irvin.services;

import com.planilla.irvin.dominio.TBL_Empleados;
import com.planilla.irvin.dominio.TBL_Pensiones;
import java.util.List;

/**
 *
 * @author irvin
 */
public interface IServicePension {

//obtiene titulos de un empleado
    public List<TBL_Pensiones> listarPensiones(TBL_Empleados empleado);

//guarda un titulo
    public void guardar(TBL_Pensiones pension);
//borrado lógico de titulos
    public void eliminar(TBL_Pensiones pension);
//obtiene un titulo por id de titulo
    public TBL_Pensiones obtenerPension(TBL_Pensiones pension);

}
