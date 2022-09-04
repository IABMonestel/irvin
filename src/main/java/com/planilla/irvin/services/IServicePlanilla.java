/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.planilla.irvin.services;

import com.planilla.irvin.dominio.TBL_Historial_Planillas;
import java.util.List;

/**
 *
 * @author irvin
 */
//métodos para planillas
public interface IServicePlanilla {
//lista de planillas ordinarias

    public List<TBL_Historial_Planillas> listarPlanillaO();
//lista planilla extraordinaria

    public List<TBL_Historial_Planillas> listarPlanillaE();
//guardar crear planilla ordinaria

    public String guardarO(TBL_Historial_Planillas planilla);
//guardar crear planilla extra ordinaria

    public String guardarE(TBL_Historial_Planillas planilla, String id_empleado);
//obtiene una planilla

    public TBL_Historial_Planillas obtenerPlanilla(TBL_Historial_Planillas planilla);
//modificar una planilla

    public TBL_Historial_Planillas modificar(TBL_Historial_Planillas planilla);

//buscar planilla por anio
    public Iterable<TBL_Historial_Planillas> findByAnio(short anio);

}
