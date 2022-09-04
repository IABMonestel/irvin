/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.planilla.irvin.dao;

import com.planilla.irvin.dominio.TBL_Empleados;
import com.planilla.irvin.dominio.TBL_Titulos;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author irvin
 */
public interface ITituloDAO extends JpaRepository<TBL_Titulos, Long> {

    public Iterable<TBL_Titulos> findByEmpleado(TBL_Empleados empleado);

}
