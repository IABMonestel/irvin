/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planilla.irvin.services;

import com.planilla.irvin.dominio.TBL_Pagos;
import java.util.List;

/**
 *
 * @author Progra
 */
public interface IServicePago {
    
    //lista de puestos
    public List<TBL_Pagos> listarPagos();
//lista de puestos con filtro
    public List<TBL_Pagos> listarPagos(String nombre);
//guarda un puesto
    public void guardar(TBL_Pagos pago);
//borrado lógico de puestos
    public void eliminar(TBL_Pagos pago);
//obtiene un puesto
    public TBL_Pagos obtenerPago(TBL_Pagos pago);
    
}
