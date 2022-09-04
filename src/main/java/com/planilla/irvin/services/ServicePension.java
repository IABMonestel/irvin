/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.planilla.irvin.services;

import com.planilla.irvin.dao.IPensionDAO;
import com.planilla.irvin.dao.ITituloDAO;
import com.planilla.irvin.dominio.TBL_Empleados;
import com.planilla.irvin.dominio.TBL_Pensiones;
import com.planilla.irvin.dominio.TBL_Titulos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author irvin
 */
@Service//declara clase como servicio
public class ServicePension implements IServicePension {

    @Autowired//Cuando el servicio necesite la instancia la genera automaticamente//InyecciÃ³n de dependencias
    private IPensionDAO pensionDao;

    @Override
    public List<TBL_Pensiones> listarPensiones(TBL_Empleados empleado) {
        return (List<TBL_Pensiones>) pensionDao.findByEmpleado(empleado);
    }

//guarda un titulo
    @Override
    public void guardar(TBL_Pensiones pension) {
        pensionDao.save(pension);
    }
//borrado lógico de titulos

    @Override
    public void eliminar(TBL_Pensiones pension) {
        pensionDao.delete(pension);
    }

    @Override
    public TBL_Pensiones obtenerPension(TBL_Pensiones pension) {
        return pensionDao.findById(pension.getCodigo()).orElse(null);
    }

}
