/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.planilla.irvin.services;

import com.planilla.irvin.dao.IPagosDAO;
import com.planilla.irvin.dominio.TBL_Pagos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author irvin
 */
@Service//declara clase como servicio
public class ServicePago implements IServicePago {
    
    @Autowired//Cuando el servicio necesite la instancia la genera automaticamente//InyecciÃ³n de dependencias
    private IPagosDAO pagosDao;
    
    @Transactional(readOnly = true)//solo consulta no hace transacciones
    @Override//lista de todos los pagos
    public List<TBL_Pagos> listarPagos() {
        return (List<TBL_Pagos>) pagosDao.findAll();
    }
    
    @Transactional(readOnly = true)//solo consulta no hace transacciones
    @Override//lista de pagos por filtro
    public List<TBL_Pagos> listarPagos(String nombre) {
        return (List<TBL_Pagos>) pagosDao.findByNombreContains(nombre);
        //return null;
    }
    
    @Transactional
    @Override//guarda un pago
    public void guardar(TBL_Pagos pago) {
        pago.setActivo(true);
        pagosDao.save(pago);
    }
    
    @Override//borrado logico de pago
    public void eliminar(TBL_Pagos pago) {        
        pago.setActivo(false);
        pagosDao.save(pago);
    }
    
    @Override//obtiene un pago
    public TBL_Pagos obtenerPago(TBL_Pagos pago) {
        return pagosDao.findById(pago.getCodigo()).orElse(null);
    } 
}
