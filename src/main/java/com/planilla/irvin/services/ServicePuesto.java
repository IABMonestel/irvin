/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.planilla.irvin.services;

import com.planilla.irvin.dao.IPuestoDAO;
import com.planilla.irvin.dominio.TBL_Puestos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author irvin
 */
@Service//declara clase como servicio
public class ServicePuesto implements IServicePuesto {
    
    @Autowired//Cuando el servicio necesite la instancia la genera automaticamente//InyecciÃ³n de dependencias
    private IPuestoDAO puestoDao;
    
    @Transactional(readOnly = true)//solo consulta no hace transacciones
    @Override//lista de todos los puestos
    public List<TBL_Puestos> listarPuestos() {
        return (List<TBL_Puestos>) puestoDao.findByActivo(true);
    }
    
    @Transactional(readOnly = true)//solo consulta no hace transacciones
    @Override//lista de puestos por filtro
    public List<TBL_Puestos> listarPuestos(String grado) {
        return (List<TBL_Puestos>) puestoDao.findByGradoContains(grado);
        //return null;
    }
    
    @Transactional
    @Override//guarda un puesto
    public void guardar(TBL_Puestos puesto) {
        puesto.setActivo(true);
        puestoDao.save(puesto);
    }
    
    @Override//borrado logico de puesto
    public void eliminar(TBL_Puestos puesto) {
        
        puesto.setActivo(false);
        puestoDao.save(puesto);
    }
    
    @Override//obtiene un puesto
    public TBL_Puestos obtenerPuesto(TBL_Puestos puesto) {
        return puestoDao.findById(puesto.getId_puesto()).orElse(null);
    }
    
    @Override//Puestos de un empleado
    public Iterable<TBL_Puestos> findByEmpleado(String id_empleado){
        return puestoDao.findByEmpleado(id_empleado);
    }
    
}
