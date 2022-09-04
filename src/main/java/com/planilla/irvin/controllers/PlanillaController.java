/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.planilla.irvin.controllers;

import com.planilla.irvin.dominio.TBL_Detalle_Deducciones;
import com.planilla.irvin.dominio.TBL_Detalle_Historial_Planillas;
import com.planilla.irvin.dominio.TBL_Detalle_Pagos;
import com.planilla.irvin.dominio.TBL_Empleados;
import com.planilla.irvin.dominio.TBL_Historial_Planillas;
import com.planilla.irvin.services.IServiceDetalleDeduccion;
import com.planilla.irvin.services.IServiceDetallePago;
import com.planilla.irvin.services.IServiceDetallePlanilla;
import com.planilla.irvin.services.IServiceEmpleado;
import com.planilla.irvin.services.IServicePlanilla;
import java.util.ArrayList;
import java.util.Calendar;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author irvin
 */
@Controller//se declara clase como controlador
@Slf4j//imprimir mensajes en consola
public class PlanillaController {

    @Autowired//instancias de clases solo cuando es necesario
    private IServicePlanilla serviciePlanilla;//métodos de planilla
    @Autowired
    private IServiceDetallePlanilla servicieDetallePlanilla;
    @Autowired
    private IServiceDetallePago servicieDetallePago;
    @Autowired
    private IServiceDetalleDeduccion servicieDetalleDeduccion;
    @Autowired
    private IServiceEmpleado servicieEmpleado;
    private Calendar fecha = Calendar.getInstance();//instancia de fecha actual

    @GetMapping("/planillaO")//petición get
    public String ListarPlanillasO(Model modelo) {
        List<TBL_Historial_Planillas> planillas = serviciePlanilla.listarPlanillaO();//lista de planillas ordinarias
        modelo.addAttribute("planillas", planillas);//agregar lista al modelo
        modelo.addAttribute("fecha", fecha);//agregar fecha al modelo
        return "Planilla";//retorna archivo html
    }
//buscar planilla ordinaria por anio

    @PostMapping("/buscarPlaniO")
    public String BuscarPlaniO(Model modelo, String txtAnio) {
        if (txtAnio != "") {
            List<TBL_Historial_Planillas> planillas = (List<TBL_Historial_Planillas>) serviciePlanilla.findByAnio((short) Integer.parseInt(txtAnio));//lista de planillas ordinarias
            List<TBL_Historial_Planillas> planillasO = new ArrayList<>();
            for (TBL_Historial_Planillas planilla : planillas) {
                if (planilla.isOrdinaria()) {
                    planillasO.add(planilla);
                }
            }
            modelo.addAttribute("planillas", planillasO);//agregar lista al modelo
            modelo.addAttribute("fecha", fecha);//agregar fecha al modelo
            return "Planilla";//retorna archivo html
        } else {
            List<TBL_Historial_Planillas> planillas = serviciePlanilla.listarPlanillaO();//lista de planillas extraordinarias
            modelo.addAttribute("planillas", planillas);//agregar lista al modelo
            String mensaje = "Indique un año";
            modelo.addAttribute("mensaje", mensaje);
            modelo.addAttribute("fecha", fecha);//agregar fecha al modelo
            return "Planilla";
        }
    }
//busca planillas por anio, extraordinaria

    @PostMapping("/buscarPlaniE")
    public String BuscarPlaniE(Model modelo, String txtAnio) {
        if (txtAnio != "") {
            List<TBL_Historial_Planillas> planillas = (List<TBL_Historial_Planillas>) serviciePlanilla.findByAnio((short) Integer.parseInt(txtAnio));//lista de planillas ordinarias
            List<TBL_Historial_Planillas> planillasO = new ArrayList<>();
            for (TBL_Historial_Planillas planilla : planillas) {
                if (!planilla.isOrdinaria()) {
                    planillasO.add(planilla);
                }
            }
            modelo.addAttribute("planillas", planillasO);//agregar lista al modelo
            modelo.addAttribute("fecha", fecha);//agregar fecha al modelo
            return "PlanillaE";//retorna archivo html
        } else {
            List<TBL_Historial_Planillas> planillas = serviciePlanilla.listarPlanillaE();//lista de planillas extraordinarias
            modelo.addAttribute("planillas", planillas);//agregar lista al modelo
            String mensaje = "Indique un año";
            modelo.addAttribute("mensaje", mensaje);
            return "PlanillaE";
        }
    }
//lista de planillas extraordinarias

    @GetMapping("/planillaE")//petición get
    public String ListarPlanillasE(Model modelo) {
        List<TBL_Historial_Planillas> planillas = serviciePlanilla.listarPlanillaE();//lista de planillas ordinarias
        modelo.addAttribute("planillas", planillas);//agregar lista al modelo
        return "PlanillaE";//retorna archivo html
    }
//planilla ordinaria

    @GetMapping("/calcPlanillaO")//petición get
    public String CrearPlanillasO(Model modelo) {
        TBL_Historial_Planillas planilla = new TBL_Historial_Planillas();
        planilla.setAnio((short) fecha.get(YEAR));
        planilla.setMes((short) (fecha.get(MONTH) + 1));

        String mensaje = serviciePlanilla.guardarO(planilla);//lista de planillas ordinarias
        List<TBL_Historial_Planillas> planillas = serviciePlanilla.listarPlanillaO();//lista de planillas ordinarias
        modelo.addAttribute("planillas", planillas);//agregar lista al modelo
        modelo.addAttribute("fecha", fecha);//agregar fecha al modelo
        modelo.addAttribute("mensaje", mensaje);
        return "Planilla";//retorna archivo html
    }
//detalles de una planilla

    @GetMapping("/detalles/{id_historial}")//petición get
    public String DetallesPlanillaO(Model modelo, TBL_Historial_Planillas planilla) {
        planilla = serviciePlanilla.obtenerPlanilla(planilla);
        List<TBL_Detalle_Historial_Planillas> detPlanillas = servicieDetallePlanilla.listarDetalles(planilla);//lista de detalles planillas ordinarias
        modelo.addAttribute("detPlanillas", detPlanillas);
        modelo.addAttribute("planilla", planilla);
        return "DetallePlanillaO";
    }
//Detalles de un empleado

    @GetMapping("/detallesEmpl/{id_detalle}")//petición get
    public String DetPlaEmplOrd(Model modelo, TBL_Detalle_Historial_Planillas detalle) {
        detalle = servicieDetallePlanilla.obtenerPlanilla(detalle);
        List<TBL_Detalle_Pagos> detPagos = servicieDetallePago.listarDetalles(detalle);//lista de detalles planillas ordinarias
        List<TBL_Detalle_Deducciones> detDeducciones = servicieDetalleDeduccion.listarDetalles(detalle);//lista de detalles planillas ordinarias
        modelo.addAttribute("detalle", detalle);
        modelo.addAttribute("detPagos", detPagos);
        modelo.addAttribute("detDeducciones", detDeducciones);
        return "DetPlaEmplOrd";
    }
//Anular planilla ordinaria

    @GetMapping("/anular/{id_historial}")
    public String AnularPlanilla(Model modelo, TBL_Historial_Planillas planilla) {
        planilla = serviciePlanilla.obtenerPlanilla(planilla);
        String mensaje = null;
        if (planilla.isCancelada()) {
            mensaje = "Error, planilla cancelada, no se puede anular";
        } else {
            planilla.setAnulada(true);
            planilla = serviciePlanilla.modificar(planilla);
        }
        List<TBL_Historial_Planillas> planillas = serviciePlanilla.listarPlanillaO();//lista de planillas ordinarias
        modelo.addAttribute("planillas", planillas);//agregar lista al modelo
        modelo.addAttribute("mensaje", mensaje);
        modelo.addAttribute(planilla);
        return "Planilla";
    }
//Cancelar planilla ordinaria

    @GetMapping("/cancelar")
    public String CancelarPlanilla(TBL_Historial_Planillas planilla, Model modelo) {
        planilla = serviciePlanilla.obtenerPlanilla(planilla);
        String mensaje = null;
        if (planilla.isAnulada()) {
            mensaje = "Error, planilla anulada, no se puede cancelar";
        } else {
            planilla.setCancelada(true);
            planilla = serviciePlanilla.modificar(planilla);
        }
        List<TBL_Historial_Planillas> planillas = serviciePlanilla.listarPlanillaO();//lista de planillas ordinarias
        modelo.addAttribute("planillas", planillas);//agregar lista al modelo
        modelo.addAttribute("mensaje", mensaje);
        modelo.addAttribute(planilla);
        return "Planilla";
    }
//Anular planilla extraordinaria

    @GetMapping("/anularE/{id_historial}")
    public String AnularPlanillaE(Model modelo, TBL_Historial_Planillas planilla) {
        planilla = serviciePlanilla.obtenerPlanilla(planilla);
        String mensaje = null;
        if (planilla.isCancelada()) {
            mensaje = "Error, planilla cancelada, no se puede anular";
        } else {
            planilla.setAnulada(true);
            planilla = serviciePlanilla.modificar(planilla);
        }
        List<TBL_Historial_Planillas> planillas = serviciePlanilla.listarPlanillaE();//lista de planillas ordinarias
        modelo.addAttribute("planillas", planillas);//agregar lista al modelo
        modelo.addAttribute("mensaje", mensaje);
        modelo.addAttribute(planilla);
        return "PlanillaE";
    }
//cancelar planilla extraordinaria

    @GetMapping("/cancelarE")
    public String CancelarPlanillaE(TBL_Historial_Planillas planilla, Model modelo) {
        planilla = serviciePlanilla.obtenerPlanilla(planilla);
        String mensaje = null;
        if (planilla.isAnulada()) {
            mensaje = "Error, planilla anulada, no se puede cancelar";
        } else {
            planilla.setCancelada(true);
            planilla = serviciePlanilla.modificar(planilla);
        }
        List<TBL_Historial_Planillas> planillas = serviciePlanilla.listarPlanillaE();//lista de planillas ordinarias
        modelo.addAttribute("planillas", planillas);//agregar lista al modelo
        modelo.addAttribute("mensaje", mensaje);
        modelo.addAttribute(planilla);
        return "PlanillaE";
    }

//Calcular planilla extraordinaria
    @GetMapping("/extraordinaria")
    public String EmpleExtraordinaria(Model model, TBL_Historial_Planillas planilla) {
        planilla = serviciePlanilla.obtenerPlanilla(planilla);
        if (planilla.isAnulada()) {
            List<TBL_Historial_Planillas> planillas = serviciePlanilla.listarPlanillaO();//lista de planillas ordinarias
            model.addAttribute("planillas", planillas);//agregar lista al modelo
            model.addAttribute("fecha", fecha);//agregar fecha al modelo
            String mensaje = "Error, planilla anulada";
            model.addAttribute("mensaje", mensaje);
            return "Planilla";
        }
        List<TBL_Empleados> empleados = (List<TBL_Empleados>) servicieEmpleado.findByPlanilla(planilla.getId_historial());
        model.addAttribute("id_historial", planilla.getId_historial());
        model.addAttribute("anio", planilla.getAnio());
        model.addAttribute("mes", planilla.getMes());
        model.addAttribute("empleados", empleados);
        return "EmpleExtraordinaria";
    }
//mostrar detalle de empleados planilla extraordinaria

    @GetMapping("/calcularE/{id_empleado}/{id_historial}/{anio}/{mes}")
    public String CalcularExtra(TBL_Historial_Planillas planilla, Model modelo, TBL_Empleados empleado) {
        log.info("id_historial: " + planilla.getId_historial());
        TBL_Historial_Planillas planillaE = new TBL_Historial_Planillas();
        planillaE.setAnio(planilla.getAnio());
        planillaE.setMes(planilla.getMes());
        String mensaje = serviciePlanilla.guardarE(planillaE, empleado.getId_empleado());//lista de planillas ordinarias

        if (Integer.parseInt(mensaje) > 0) {
            planilla.setId_historial((short) Integer.parseInt(mensaje));
            planilla = serviciePlanilla.obtenerPlanilla(planilla);

            List<TBL_Detalle_Historial_Planillas> detPlanillas = servicieDetallePlanilla.listarDetalles(planilla);//lista de detalles planillas ordinarias
            modelo.addAttribute("detPlanillas", detPlanillas);
            modelo.addAttribute("fecha", fecha);//agregar fecha al modelo
            modelo.addAttribute("mensaje", mensaje);
            modelo.addAttribute("planilla", planilla);
            return "DetallePlanillaO";
        } else {
            mensaje = "Error al calcular planilla extraordinaria";
            planilla = serviciePlanilla.obtenerPlanilla(planilla);
            modelo.addAttribute("planilla", planilla);
            List<TBL_Empleados> empleados = (List<TBL_Empleados>) servicieEmpleado.findByPlanilla(planilla.getId_historial());
            modelo.addAttribute("anio", planilla.getAnio());
            modelo.addAttribute("mes", planilla.getMes());
            modelo.addAttribute("id_historial", planilla.getId_historial());
            modelo.addAttribute("empleados", empleados);
            modelo.addAttribute("mensaje", mensaje);
            return "EmpleExtraordinaria";
        }
    }

}
