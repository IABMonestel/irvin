/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.planilla.irvin.dao;

import com.planilla.irvin.dominio.TBL_Historial_Planillas;
import java.util.HashMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author irvin
 */
//clase que define métodos de acceso a datos de planillas
public interface IPlanillaDAO extends JpaRepository<TBL_Historial_Planillas, Long> {

    //lista de planilla ordinaria
    public Iterable<TBL_Historial_Planillas> findByOrdinariaTrue();

    //lista de planilla extraordinaria
    public Iterable<TBL_Historial_Planillas> findByOrdinariaFalse();

//busca planillas por anio
    public Iterable<TBL_Historial_Planillas> findByAnio(short anio);

    //procedimiento que crea una planilla ordinaria
    @Transactional
    @Procedure(name = "SP_PLANILLA_ORDINARIA", outputParameterName = "MENSAJE")
    public String SP_PLANILLA_ORDINARIA(@Param("ANIO") short anio,
             @Param("MES") short mes);
    //,@Param("MENSAJE") String mensaje);
    //procedimiento que cancela una planilla

    @Transactional
    @Procedure(name = "SP_PLANILLA_EXTRAORDINARIA", outputParameterName = "MENSAJE")
    public String SP_PLANILLA_EXTRAORDINARIA(@Param("ANIO") short anio,
             @Param("MES") short mes,
             @Param("ID_EMPLEADO") String id_emplado);
    //,@Param("MENSAJE") String mensaje);
}
