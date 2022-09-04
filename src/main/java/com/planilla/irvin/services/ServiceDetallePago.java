/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.planilla.irvin.services;

import com.planilla.irvin.dao.IDetallePagosDAO;
import com.planilla.irvin.dao.IDetallePlanillaDAO;
import com.planilla.irvin.dominio.TBL_Detalle_Historial_Planillas;
import com.planilla.irvin.dominio.TBL_Detalle_Pagos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author irvin
 */
@Service
public class ServiceDetallePago implements IServiceDetallePago {

    @Autowired//Cuando el servicio necesite la instancia la genera automaticamente//InyecciÃ³n de dependencias
    private IDetallePagosDAO detallePagosDao;

    public List<TBL_Detalle_Pagos> listarDetalles(TBL_Detalle_Historial_Planillas detalle) {
        return (List<TBL_Detalle_Pagos>) detallePagosDao.findByHistorial(detalle);
    }

}
