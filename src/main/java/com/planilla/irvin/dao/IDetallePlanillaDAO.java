/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.planilla.irvin.dao;

import com.planilla.irvin.dominio.TBL_Detalle_Historial_Planillas;
import com.planilla.irvin.dominio.TBL_Historial_Planillas;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author irvin
 */
public interface IDetallePlanillaDAO extends JpaRepository<TBL_Detalle_Historial_Planillas, Long> {

    public Iterable<TBL_Detalle_Historial_Planillas> findByPlanilla(TBL_Historial_Planillas planilla);

}
