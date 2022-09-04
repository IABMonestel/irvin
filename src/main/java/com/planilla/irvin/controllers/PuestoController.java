/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.planilla.irvin.controllers;

import com.planilla.irvin.dominio.TBL_Puestos;
import com.planilla.irvin.services.IServicePuesto;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author irvin
 */
@Controller//se declara clase como controlador
@Slf4j//imprimir mensajes en consola
public class PuestoController {

    @Autowired//instancias solo cuando sea necesario
    private IServicePuesto serviciePuesto;//métodos de los puestos

    @GetMapping("/puestosL")//peticion get
    public String ListarPuestos(Model modelo) {
        List<TBL_Puestos> puestos = serviciePuesto.listarPuestos();//lista de puestos
        modelo.addAttribute("puestos", puestos);//agrega lista al modelo
        return "ListaP";//retorna archivo html
    }

    @PostMapping("/buscarP")//peticion post
    public String BuscarPuestos(String txtNombre, Model modelo) {
        List<TBL_Puestos> puestos = serviciePuesto.listarPuestos(txtNombre);//lista de puestos con filtro
        modelo.addAttribute("puestos", puestos);//agrega lista al modelo
        return "ListaP";//retorna archivo html
    }

    @GetMapping("/eliminarP")
    public String eliminarPuesto(TBL_Puestos puesto, Model model) {
        puesto = serviciePuesto.obtenerPuesto(puesto);
        serviciePuesto.eliminar(puesto);
        String mensaje;
        mensaje = "Puesto eliminado con éxito";
        List<TBL_Puestos> puestos = serviciePuesto.listarPuestos();
        model.addAttribute("puestos", puestos);
        model.addAttribute("mensaje", mensaje);
        return "ListaP";
    }

    @GetMapping("/agregarP")
    public String Agregar(TBL_Puestos puesto, Model model) {
        model.addAttribute("puesto", puesto);
        return "editarPuesto";
    }

    @PostMapping("/guardarP")
    public String Guardar(@Valid TBL_Puestos puesto, Errors errors) {
        if (errors.hasErrors()) {
            return "editarPuesto";
        }        
        serviciePuesto.guardar(puesto);
        return "redirect:/puestosL";
    }

    @GetMapping("/editarP/{id_puesto}")
    public String editarPuesto(TBL_Puestos puesto, Model modelo) {
        
        puesto = serviciePuesto.obtenerPuesto(puesto);
        if (puesto != null) {
            modelo.addAttribute("puesto", puesto);
            return "editarPuesto";
        } else {
            String mensaje = "No se encuentra el puesto";
            List<TBL_Puestos> puestos = serviciePuesto.listarPuestos();
            modelo.addAttribute("puestos", puestos);
            modelo.addAttribute("mensaje", mensaje);
            return "ListaP";
        }
    }

}
