/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.planilla.irvin.services;

import com.planilla.irvin.dao.IDetallePlanillaDAO;
import com.planilla.irvin.dominio.TBL_Detalle_Historial_Planillas;
import com.planilla.irvin.dominio.TBL_Historial_Planillas;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author irvin
 */
@Service
public class ServiceDetallePlanilla implements IServiceDetallePlanilla {

    @Autowired//Cuando el servicio necesite la instancia la genera automaticamente//InyecciÃ³n de dependencias
    private IDetallePlanillaDAO detallePlanillaDao;

    public List<TBL_Detalle_Historial_Planillas> listarDetalles(TBL_Historial_Planillas planilla) {
        return (List<TBL_Detalle_Historial_Planillas>) detallePlanillaDao.findByPlanilla(planilla);
    }

    public TBL_Detalle_Historial_Planillas obtenerPlanilla(TBL_Detalle_Historial_Planillas detalle) {
        return detallePlanillaDao.findById(detalle.getId_detalle()).orElse(null);
    }

}
