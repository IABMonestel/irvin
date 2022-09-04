/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.planilla.irvin.services;

import com.planilla.irvin.dao.IDetalleDeduccionesDAO;
import com.planilla.irvin.dao.IDetallePagosDAO;
import com.planilla.irvin.dominio.TBL_Detalle_Deducciones;
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
public class ServiceDetalleDeduccion implements IServiceDetalleDeduccion {

@Autowired//Cuando el servicio necesite la instancia la genera automaticamente//InyecciÃ³n de dependencias
    private IDetalleDeduccionesDAO detalleDeduccionDao;

    public List<TBL_Detalle_Deducciones> listarDetalles(TBL_Detalle_Historial_Planillas detalle) {
        return (List<TBL_Detalle_Deducciones>) detalleDeduccionDao.findByHistorial(detalle);
    }
    
}
