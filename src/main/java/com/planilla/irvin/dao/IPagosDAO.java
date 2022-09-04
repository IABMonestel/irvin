/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planilla.irvin.dao;

import com.planilla.irvin.dominio.TBL_Pagos;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Progra
 */
public interface IPagosDAO extends JpaRepository<TBL_Pagos, Long>{
    
     public Iterable<TBL_Pagos> findByNombreContains(String nombre);
    
}
