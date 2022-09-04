/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.planilla.irvin.services;

import com.planilla.irvin.dominio.TBL_Detalle_Historial_Planillas;
import com.planilla.irvin.dominio.TBL_Historial_Planillas;
import java.util.List;

/**
 *
 * @author irvin
 */
public interface IServiceDetallePlanilla {

    public List<TBL_Detalle_Historial_Planillas> listarDetalles(TBL_Historial_Planillas planilla);

    public TBL_Detalle_Historial_Planillas obtenerPlanilla(TBL_Detalle_Historial_Planillas detalle);

}
