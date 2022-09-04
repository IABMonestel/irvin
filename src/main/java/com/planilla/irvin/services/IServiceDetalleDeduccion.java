/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.planilla.irvin.services;

import com.planilla.irvin.dominio.TBL_Detalle_Deducciones;
import com.planilla.irvin.dominio.TBL_Detalle_Historial_Planillas;
import java.util.List;

/**
 *
 * @author irvin
 */
public interface IServiceDetalleDeduccion {

    public List<TBL_Detalle_Deducciones> listarDetalles(TBL_Detalle_Historial_Planillas detalle);
}
