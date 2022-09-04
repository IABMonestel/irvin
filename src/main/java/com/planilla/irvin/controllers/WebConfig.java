/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.planilla.irvin.controllers;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author irvin
 */
public class WebConfig implements WebMvcConfigurer{//define configuraciones del proyecto

    @Override
    public void addViewControllers(ViewControllerRegistry registro ){
       registro.addViewController("/").setViewName("index");//define ruta por defecto del proyecto
       
    }    
}
