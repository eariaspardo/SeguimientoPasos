package com.seguimiento.pagos.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seguimiento.pagos.entity.Proyecto;
import com.seguimiento.pagos.modelo.response.Resumen;
import com.seguimiento.pagos.service.ProyectoService;

@RestController
@RequestMapping("/proyecto")
@CrossOrigin(origins = {"*", "http://localhost:4100/", "http://localhost:4100/"}, maxAge = 3600)
public class ProyectosController{

	@Autowired
	private ProyectoService proyectoService;
	
	@GetMapping("/obtenerTodos")
    public ResponseEntity<List<Proyecto>> obtenerTodos(){
        return new ResponseEntity<List<Proyecto>>(proyectoService.obtenerTodos(), HttpStatus.OK);
    }
	
	@GetMapping("/porUsuario/{idUsuario}")
    public ResponseEntity<List<Proyecto>> porUsuario(@PathVariable(value = "idUsuario") Integer idUsuario){
        return new ResponseEntity<List<Proyecto>>(proyectoService.proyectoPorUsuario(idUsuario), HttpStatus.OK);
    }
	
	@GetMapping("/{idProyecto}")
    public ResponseEntity<Proyecto> proyecto(@PathVariable(value = "idProyecto") Long idProyecto){
        return new ResponseEntity<Proyecto>(proyectoService.proyecto(idProyecto), HttpStatus.OK);
    }
	
	@GetMapping("/resumenProyectos/{idUsuario}")
    public ResponseEntity<Resumen> resumenProyectosUsuario(@PathVariable(value = "idUsuario") Integer idUsuario){
        return new ResponseEntity<Resumen>(proyectoService.resumenProyectos(idUsuario), HttpStatus.OK);
    }
	
	@PostMapping("/guardar")
    public ResponseEntity<Proyecto> guardarProyecto(@RequestBody @Valid Proyecto proyecto){
        return new ResponseEntity<Proyecto>(proyectoService.guardar(proyecto), HttpStatus.OK);
    }
	
	@PutMapping("/actualizarEstado")
    public ResponseEntity<Proyecto> actualizarProyecto(@RequestBody Proyecto proyecto){
        return new ResponseEntity<Proyecto>(proyectoService.actualizarProyecto(proyecto), HttpStatus.OK);
    }
	
}
