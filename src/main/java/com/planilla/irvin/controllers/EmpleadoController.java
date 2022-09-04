/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.planilla.irvin.controllers;

import com.planilla.irvin.dominio.TBL_Empleados;
import com.planilla.irvin.dominio.TBL_Pensiones;
import com.planilla.irvin.dominio.TBL_Puestos;
import com.planilla.irvin.dominio.TBL_Titulos;
import com.planilla.irvin.services.IServiceEmpleado;
import com.planilla.irvin.services.IServicePuesto;
import com.planilla.irvin.services.IServiceTitulo;
import com.planilla.irvin.services.IServicePension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@Controller//anotacion controlador
@Slf4j//imprimir mensajes en consola
public class EmpleadoController {
//métodos de empleado

    @Autowired//se inicializa instancia cuando es necesario
    private IServiceEmpleado servicieEmpleado;//servicios del empleado como métodos
//métodos de títulos
    @Autowired//se inicializa instancia cuando es necesario
    private IServiceTitulo servicieTitulo;//servicios del empleado como métodos
//métodos de puestos
    @Autowired
    private IServicePuesto serviciePuesto;
//métodos de pensión
    @Autowired
    private IServicePension serviciePension;
//muestra la lista de empleados

    @GetMapping("/empleadosL")//peticion get
    public String ListarEmpleados(Model modelo) {
        List<TBL_Empleados> empleados = servicieEmpleado.listarEmpleados();//lista de empleados
        modelo.addAttribute("empleados", empleados);//agregar lista de empleados al modelo
        return "ListaE";//retorna archivo html
    }
//filtra empleados por nombre

    @PostMapping("/buscarE")//peticion post
    public String BuscarEmpleados(String txtNombre, Model modelo) {
        List<TBL_Empleados> clientes = servicieEmpleado.listarEmpleados(txtNombre);//lista de empleados con filtro
        modelo.addAttribute("empleados", clientes);//agregar lista al modelo
        return "ListaE";//retorna archivo html
    }
//agrega un empleado

    @GetMapping("/agregarE")
    public String Agregar(TBL_Empleados empleado, Model model) {
        model.addAttribute("empleado", empleado);
        return "editarEmpleado";
    }
//edita un empleado

    @GetMapping("/editarE/{id_empleado}")
    public String EditarEmpleado(TBL_Empleados empleado, Model modelo) {

        empleado = servicieEmpleado.obtenerEmpleado(empleado);
        log.info("puesto: " + empleado.getPuesto().getId_puesto());
        if (empleado != null) {
            modelo.addAttribute("empleado", empleado);
            return "editarEmpleado";
        } else {
            String mensaje = "No se encuentra el empleado";
            List<TBL_Empleados> empleados = servicieEmpleado.listarEmpleados();
            modelo.addAttribute("empleados", empleados);
            modelo.addAttribute("mensaje", mensaje);
            return "ListaE";
        }
    }
//guarda un empleado

    @PostMapping("/guardarE")
    public String GuardarEmpl(@Valid TBL_Empleados empleado, Errors errors, Model model, String ingreso, String nacimiento, String idpuesto) throws ParseException {

        try {
            if (errors.hasErrors()) {
                model.addAttribute("empleado", empleado);
                return "editarEmpleado";
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date ingresoDate = formatter.parse(ingreso);
            Date nacimientoDate = formatter.parse(nacimiento);
            empleado.setFecha_ingreso(ingresoDate);
            empleado.setFecha_nacimiento(nacimientoDate);
            TBL_Titulos titulo = new TBL_Titulos();
            TBL_Puestos puesto = new TBL_Puestos();
            List<TBL_Titulos> titulos = servicieTitulo.listarTitulos(empleado);
            log.info("id_puesto: " + idpuesto);
            puesto.setId_puesto(1);
            empleado.setTitulos(titulos);
            empleado.setActivo(true);
            empleado.setPuesto(puesto);
            servicieEmpleado.guardar(empleado);
            model.addAttribute("empleado", empleado);
            model.addAttribute("titulo", titulo);
            return "AsignarTitulos";
        } catch (Exception e) {
            model.addAttribute("mensaje", e.getMessage());
            model.addAttribute("empleado", empleado);
            return "editarEmpleado";
        }
    }
//guarda el título de un empleado

    @PostMapping("/guardarT")
    public String GuardarTituloEmpl(@Valid TBL_Titulos titulo, Errors errors, Model model, String fecha, String id_empleado) throws ParseException {
        log.info("id_empleado" + id_empleado);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaT = formatter.parse(fecha);
        titulo.setFecha(fechaT);
        TBL_Empleados empleado = new TBL_Empleados();
        empleado.setId_empleado(id_empleado);
        empleado = servicieEmpleado.obtenerEmpleado(empleado);
        titulo.setEmpleado(empleado);
        servicieTitulo.guardar(titulo);
        TBL_Titulos tituloN = new TBL_Titulos();
        List<TBL_Titulos> titulos = servicieTitulo.listarTitulos(empleado);
        empleado.setTitulos(titulos);
        servicieEmpleado.guardar(empleado);
        model.addAttribute("empleado", empleado);
        model.addAttribute("titulo", tituloN);
        return "AsignarTitulos";
    }
//muestra puestos disponibles de un empleado según grado académico

    @GetMapping("/asignarPuestos/{id_empleado}")//petición get
    public String AsignarPuesto(Model model, TBL_Empleados empleado) {
        //enviar lista de puestos disponibles
        List<TBL_Puestos> puestos = (List<TBL_Puestos>) serviciePuesto.findByEmpleado(empleado.getId_empleado());
        List<TBL_Puestos> puestosPG = (List<TBL_Puestos>) serviciePuesto.listarPuestos("Pre-Grado");
        Boolean bandera = false;
        Boolean bandera2 = false;
        if (puestos.isEmpty()) {
            model.addAttribute("puestos", puestosPG);
        } else {
            for (TBL_Puestos puesto : puestos) {
                if (puesto.getGrado().equals("Bachiller")) {
                    bandera = true;
                }
                if (puesto.getGrado().equals("Pre-Grado")) {
                    bandera2 = true;
                }
            }
            if (bandera && !bandera2) {
                for (TBL_Puestos tBL_Puestos : puestosPG) {
                    puestos.add(tBL_Puestos);
                }
            }
            model.addAttribute("puestos", puestos);
        }
        model.addAttribute("empleado", empleado);
        return "AsignarPuesto";
    }

    //asignar puesto
    @GetMapping("/asignarPuesto/{id_puesto}/{id_empleado}")//petición get
    public String AsignarPuesto(Model model, TBL_Empleados empleado, TBL_Puestos puesto) {
        //asignar Puesto al empleado
        puesto = serviciePuesto.obtenerPuesto(puesto);
        empleado = servicieEmpleado.obtenerEmpleado(empleado);
        empleado.setPuesto(puesto);
        servicieEmpleado.guardar(empleado);
        String mensaje = "Puesto asignado satisfactoriamnte";
        List<TBL_Puestos> puestos = (List<TBL_Puestos>) serviciePuesto.findByEmpleado(empleado.getId_empleado());
        List<TBL_Puestos> puestosPG = (List<TBL_Puestos>) serviciePuesto.listarPuestos("Pre-Grado");
        Boolean bandera = false;
        Boolean bandera2 = false;
        if (puestos.isEmpty()) {
            model.addAttribute("puestos", puestosPG);
        } else {
            for (TBL_Puestos puestoB : puestos) {
                if (puestoB.getGrado().equals("Bachiller")) {
                    bandera = true;
                }
                if (puestoB.getGrado().equals("Pre-Grado")) {
                    bandera2 = true;
                }
            }
            if (bandera && !bandera2) {
                for (TBL_Puestos tBL_Puestos : puestosPG) {
                    puestos.add(tBL_Puestos);
                }
            }
            model.addAttribute("puestos", puestos);
        }
        model.addAttribute("empleado", empleado);
        model.addAttribute("mensaje", mensaje);
        return "AsignarPuesto";
    }

    //borrado lógico de empleados
    @GetMapping("/eliminarE")//petición get
    public String EliminarEmpleado(TBL_Empleados empleado, Model modelo) {
        empleado = servicieEmpleado.obtenerEmpleado(empleado);
        empleado.setActivo(false);
        servicieEmpleado.guardar(empleado);
        String mensaje = "Empleado eliminado.";
        List<TBL_Empleados> empleados = servicieEmpleado.listarEmpleados();//lista de empleados
        modelo.addAttribute("empleados", empleados);//agregar lista de empleados al modelo
        modelo.addAttribute("mensaje", mensaje);
        return "ListaE";//retorna archivo html
    }

//eliminar un titulo
    @GetMapping("/eliminarTE/{id_titulo}/{id_empleado}")
    public String EliminarTitulo(TBL_Titulos titulo, TBL_Empleados empleado, Model model) {
        titulo = servicieTitulo.obtenerTitulo(titulo);
        servicieTitulo.eliminar(titulo);
        empleado.setId_empleado(empleado.getId_empleado());
        empleado = servicieEmpleado.obtenerEmpleado(empleado);
        titulo.setEmpleado(empleado);
        TBL_Titulos tituloN = new TBL_Titulos();
        List<TBL_Titulos> titulos = servicieTitulo.listarTitulos(empleado);
        empleado.setTitulos(titulos);
        servicieEmpleado.guardar(empleado);
        model.addAttribute("empleado", empleado);
        model.addAttribute("titulo", tituloN);
        return "AsignarTitulos";
    }

    //agregar pensiones a empleado
    @GetMapping("/pensionE")
    public String AgregarPensiones(TBL_Empleados empleado, Model modelo) {

        empleado = servicieEmpleado.obtenerEmpleado(empleado);
        TBL_Pensiones pension = new TBL_Pensiones();
        List<TBL_Pensiones> pensiones = serviciePension.listarPensiones(empleado);
        empleado.setPensiones(pensiones);
        modelo.addAttribute("empleado", empleado);
        modelo.addAttribute("pension", pension);
        return "AsignarPensiones";
    }
//guarda la pension de un empleado

    @PostMapping("/guardarPE")
    public String GuardarPensionEmpl(@Valid TBL_Pensiones pension, Errors errors, Model model, String id_empleado) throws ParseException {

        try {
            log.info("id: " + id_empleado);
            TBL_Empleados empleado = new TBL_Empleados();
            empleado.setId_empleado(id_empleado);
            empleado = servicieEmpleado.obtenerEmpleado(empleado);
            pension.setEmpleado(empleado);
            serviciePension.guardar(pension);
            TBL_Pensiones pensionN = new TBL_Pensiones();
            List<TBL_Pensiones> pensiones = serviciePension.listarPensiones(empleado);
            empleado.setPensiones(pensiones);
            servicieEmpleado.guardar(empleado);
            model.addAttribute("empleado", empleado);
            model.addAttribute("pension", pensionN);
            return "AsignarPensiones";
        } catch (Exception e) {
            String mensaje = "Id inválido";
            TBL_Empleados empleado = new TBL_Empleados();
            empleado.setId_empleado(id_empleado);
            empleado = servicieEmpleado.obtenerEmpleado(empleado);
            TBL_Pensiones pensionN = new TBL_Pensiones();
            model.addAttribute("mensaje", mensaje);
            model.addAttribute("empleado", empleado);
            model.addAttribute("pension", pensionN);
            return "AsignarPensiones";
        }
    }

    //Eliminar pension
    @GetMapping("/eliminarPE/{codigo}/{id_empleado}")
    public String EliminarPension(TBL_Pensiones pension, TBL_Empleados empleado, Model model) {
        serviciePension.eliminar(pension);
        empleado = servicieEmpleado.obtenerEmpleado(empleado);
        serviciePension.guardar(pension);
        TBL_Pensiones pensionN = new TBL_Pensiones();
        List<TBL_Pensiones> pensiones = serviciePension.listarPensiones(empleado);
        empleado.setPensiones(pensiones);
        servicieEmpleado.guardar(empleado);
        model.addAttribute("empleado", empleado);
        model.addAttribute("pension", pensionN);
        return "AsignarPensiones";
    }

}
