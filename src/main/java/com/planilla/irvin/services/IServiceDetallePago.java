/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.planilla.irvin.services;

import com.planilla.irvin.dominio.TBL_Detalle_Historial_Planillas;
import com.planilla.irvin.dominio.TBL_Detalle_Pagos;
import java.util.List;

/**
 *
 * @author irvin
 */
public interface IServiceDetallePago {

    public List<TBL_Detalle_Pagos> listarDetalles(TBL_Detalle_Historial_Planillas detalle);
}
