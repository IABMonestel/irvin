/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.planilla.irvin.dao;

import com.planilla.irvin.dominio.TBL_Empleados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author irvin
 */
//clase para definir métodos de acceso a datos
public interface IEmpleadoDAO extends JpaRepository<TBL_Empleados, String> {

    //lista de empleados por nombre o apellido
    public Iterable<TBL_Empleados> findByNombreContainsOrApellido1Contains(String nombre, String apellido);

    //procedimiento que hace borrado lógico de empleado
    @Transactional
    @Procedure(name = "SP_ELIMINAR_EMPLEADO", outputParameterName = "BANDERA")
    public Boolean eliminar_Empleado(@Param("ID_CLIENTE") String id);

//empleados de planilla extraordinaria
    @Query(value = "SELECT * from TBL_Empleados E Left Join FN_EMPLEADOS_PLANILLA_ORDINARIA(?1)"
            + " B ON E.Id_Empleado = B.Id_Empleado WHERE Id_Historial is NULL",
            nativeQuery = true)
    public Iterable<TBL_Empleados> findByPlanilla(long id_planilla);

}
