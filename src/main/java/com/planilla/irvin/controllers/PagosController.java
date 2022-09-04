/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planilla.irvin.controllers;

import com.planilla.irvin.dominio.TBL_Pagos;
import com.planilla.irvin.services.IServicePago;
import java.text.ParseException;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Progra
 */
@Controller//anotacion controlador
@Slf4j//imprimir mensajes en consola
public class PagosController {

    @Autowired//se inicializa instancia cuando es necesario
    private IServicePago serviciePago;//servicios del empleado como métodos

    @GetMapping("pagosL")
    public String ListaPagos(Model modelo) {
        List<TBL_Pagos> pagos = serviciePago.listarPagos();//lista de pagos
        modelo.addAttribute("pagos", pagos);//agregar lista de pagos al modelo
        return "ListaPagos";
    }

    //filtra empleados por nombre
    @PostMapping("/buscarPA")//peticion post
    public String BuscarEmpleados(String txtNombre, Model modelo) {
        List<TBL_Pagos> pagos = serviciePago.listarPagos(txtNombre);//lista de empleados con filtro
        modelo.addAttribute("pagos", pagos);//agregar lista de pagos al modelo
        return "ListaPagos";//retorna archivo html
    }

    //agregar un pago
    @GetMapping("/agregarPA")
    public String GuardarEmpl(TBL_Pagos pago, Model model) throws ParseException {
        model.addAttribute("pago", pago);
        return "editarPago";

    }

    @PostMapping("/guardarPA")
    public String GuardarEmpl(@Valid TBL_Pagos pago, Errors errors, Model modelo) throws ParseException {

        try {
            if (errors.hasErrors()) {
                modelo.addAttribute("pago", pago);
                return "editarPago";
            }
            serviciePago.guardar(pago);
            List<TBL_Pagos> pagos = serviciePago.listarPagos();//lista de pagos
            modelo.addAttribute("pagos", pagos);//agregar lista de pagos al modelo
            return "ListaPagos";
        } catch (Exception e) {
            modelo.addAttribute("pago", pago);
            return "editarPago";
        }
    }
    
    //edita un pago
    @GetMapping("/editarPA/{codigo}")
    public String EditarEmpleado(TBL_Pagos pago, Model modelo) {

        pago = serviciePago.obtenerPago(pago);
        log.info("puesto: " + pago.getCodigo());
        if (pago != null) {
            modelo.addAttribute("pago", pago);
            return "editarPago";
        } else {
            String mensaje = "No se encuentra el pago";
            List<TBL_Pagos> empleados = serviciePago.listarPagos();
            modelo.addAttribute("pago", pago);
            return "ListaPagos";
        }
    }

}
