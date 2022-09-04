/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.planilla.irvin.dao;

import com.planilla.irvin.dominio.TBL_Detalle_Historial_Planillas;
import com.planilla.irvin.dominio.TBL_Detalle_Pagos;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author irvin
 */
public interface IDetallePagosDAO extends JpaRepository<TBL_Detalle_Pagos, Long> {

    public Iterable<TBL_Detalle_Pagos> findByHistorial(TBL_Detalle_Historial_Planillas detalle);

}
