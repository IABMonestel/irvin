/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.planilla.irvin.services;

import com.planilla.irvin.dominio.TBL_Empleados;
import com.planilla.irvin.dominio.TBL_Titulos;
import java.util.List;

/**
 *
 * @author irvin
 */
public interface IServiceTitulo {

//obtiene titulos de un empleado
    public List<TBL_Titulos> listarTitulos(TBL_Empleados empleado);

//guarda un titulo
    public void guardar(TBL_Titulos titulo);
//borrado lógico de titulos
    public void eliminar(TBL_Titulos titulo);
//obtiene un titulo por id de titulo
    public TBL_Titulos obtenerTitulo(TBL_Titulos titulo);

}
