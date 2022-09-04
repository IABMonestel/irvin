/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planilla.irvin.dao;

import com.planilla.irvin.dominio.TBL_Empleados;
import com.planilla.irvin.dominio.TBL_Pensiones;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Progra
 */
public interface IPensionDAO extends JpaRepository<TBL_Pensiones, Long>{
    
    public Iterable<TBL_Pensiones> findByEmpleado(TBL_Empleados empleado);
    
}
