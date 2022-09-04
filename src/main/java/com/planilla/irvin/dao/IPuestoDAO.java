/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.planilla.irvin.dao;

import com.planilla.irvin.dominio.TBL_Empleados;
import com.planilla.irvin.dominio.TBL_Puestos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author irvin
 */
//clase que define métodos de acceso a datos de los puestos
public interface IPuestoDAO extends JpaRepository<TBL_Puestos, Long> {

    //lista de puestos por nombre de puesto
    public Iterable<TBL_Puestos> findByGradoContains(String grado);

    //solo puestos activos
    public Iterable<TBL_Puestos> findByActivo(Boolean activo);

//puestos aplicables para un empleado según grado académico
    @Query(value = "SELECT Id_Puesto, Nombre_Puesto, Categoria, Salario_Base, Activo, Grado_Minimo \n"
            + " FROM TBL_Puestos P Inner Join TBL_Titulos T\n"
            + " ON P.Grado_Minimo = T.Grado_Academico \n"
            + " WHERE T.Id_Empleado = ?1 AND P.Activo = 1\n"
            + " GROUP BY Nombre_Puesto, Id_Puesto, Categoria, Salario_Base, Activo, Grado_Minimo",
            nativeQuery = true)
    public Iterable<TBL_Puestos> findByEmpleado(String id_empleado);

}
