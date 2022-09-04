/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.planilla.irvin.services;

import com.planilla.irvin.dao.IPlanillaDAO;
import com.planilla.irvin.dominio.TBL_Historial_Planillas;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author irvin
 */
@Service//declara clase como servicio
public class ServicePlanilla implements IServicePlanilla {

    @Autowired//Cuando el servicio necesite la instancia la genera automaticamente//InyecciÃ³n de dependencias
    private IPlanillaDAO planillaDao;

    @Transactional(readOnly = true)//solo consulta no hace transacciones
    @Override//lista de planillas ordinarias
    public List<TBL_Historial_Planillas> listarPlanillaO() {
        return (List<TBL_Historial_Planillas>) planillaDao.findByOrdinariaTrue();
    }

    @Transactional(readOnly = true)//solo consulta no hace transacciones
    @Override//lista de planillas extraordinarias
    public List<TBL_Historial_Planillas> listarPlanillaE() {
        return (List<TBL_Historial_Planillas>) planillaDao.findByOrdinariaFalse();
    }
//guarda una planilla ordinaria

    @Transactional//solo consulta no hace transacciones
    @Override
    public String guardarO(TBL_Historial_Planillas planilla) {
        String mensaje = planillaDao.SP_PLANILLA_ORDINARIA(planilla.getAnio(), planilla.getMes());//, mensaje);        
        return mensaje;
    }
//guarda una planilla extraordinaria

    @Transactional(readOnly = true)//solo consulta no hace transacciones
    @Override
    public String guardarE(TBL_Historial_Planillas planilla, String id_empleado) {
        String mensaje = planillaDao.SP_PLANILLA_EXTRAORDINARIA(planilla.getAnio(), planilla.getMes(), id_empleado);
        return mensaje;
    }

//obtiene una planilla
    @Override
    public TBL_Historial_Planillas obtenerPlanilla(TBL_Historial_Planillas planilla) {
        return planillaDao.findById(planilla.getId_historial()).orElse(null);
    }
//modifica planilla

    public TBL_Historial_Planillas modificar(TBL_Historial_Planillas planilla) {
        planillaDao.save(planilla);
        return planilla;
    }

//busca planilla por anio
    public Iterable<TBL_Historial_Planillas> findByAnio(short anio) {
        return planillaDao.findByAnio(anio);
    }

}
