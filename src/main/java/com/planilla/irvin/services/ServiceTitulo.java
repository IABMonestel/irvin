/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.planilla.irvin.services;

import com.planilla.irvin.dao.IPuestoDAO;
import com.planilla.irvin.dao.ITituloDAO;
import com.planilla.irvin.dominio.TBL_Detalle_Historial_Planillas;
import com.planilla.irvin.dominio.TBL_Empleados;
import com.planilla.irvin.dominio.TBL_Historial_Planillas;
import com.planilla.irvin.dominio.TBL_Titulos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author irvin
 */
@Service//declara clase como servicio
public class ServiceTitulo implements IServiceTitulo {

    @Autowired//Cuando el servicio necesite la instancia la genera automaticamente//InyecciÃ³n de dependencias
    private ITituloDAO tituloDao;

    @Override
    public List<TBL_Titulos> listarTitulos(TBL_Empleados empleado) {
        return (List<TBL_Titulos>) tituloDao.findByEmpleado(empleado);
    }

//guarda un titulo
    @Override
    public void guardar(TBL_Titulos titulo) {
        tituloDao.save(titulo);
    }
//borrado lógico de titulos

    @Override
    public void eliminar(TBL_Titulos titulo) {
        tituloDao.delete(titulo);
    }

    @Override
    public TBL_Titulos obtenerTitulo(TBL_Titulos titulo) {
        return tituloDao.findById(titulo.getId_titulo()).orElse(null);
    }

}
