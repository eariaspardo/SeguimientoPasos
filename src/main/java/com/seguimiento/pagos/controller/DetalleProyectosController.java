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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seguimiento.pagos.entity.DetalleCuotas;
import com.seguimiento.pagos.service.DetalleProyectoService;

@RestController
@RequestMapping("/detalle")
@CrossOrigin(origins = {"*", "http://localhost:4100/"}, maxAge = 3600)
public class DetalleProyectosController{

	@Autowired
	private DetalleProyectoService detalleProyectoService;
	
	@GetMapping("/obtenerTodos")
    public ResponseEntity<List<DetalleCuotas>> obtenerTodos(){
        return new ResponseEntity<List<DetalleCuotas>>(detalleProyectoService.obtenerTodos(), HttpStatus.OK);
    }
	
	@GetMapping("/detalleProyecto/{idProyecto}")
    public ResponseEntity<List<DetalleCuotas>> detallePorProyecto(@PathVariable(value = "idProyecto") Long idProyecto){
        return new ResponseEntity<List<DetalleCuotas>>(detalleProyectoService.detallePorProyecto(idProyecto), HttpStatus.OK);
    }
	
	@GetMapping("/detalleCuota/{idDetalleCuota}")
    public ResponseEntity<DetalleCuotas> detalleCuota(@PathVariable(value = "idDetalleCuota") Long idDetalleCuota){
        return new ResponseEntity<DetalleCuotas>(detalleProyectoService.detalleCuota(idDetalleCuota), HttpStatus.OK);
    }
	
	@PostMapping("/actualizarCuota")
    public ResponseEntity<DetalleCuotas> updateCuota(@RequestBody DetalleCuotas detalleProyecto) {
        return new ResponseEntity<DetalleCuotas>(detalleProyectoService.updateCuota(detalleProyecto), HttpStatus.OK);
    }
	
	@PostMapping("/guardarCuota")
    public ResponseEntity<DetalleCuotas> saveCuota(@RequestBody @Valid DetalleCuotas detalleProyecto){
        return new ResponseEntity<DetalleCuotas>(detalleProyectoService.saveCuota(detalleProyecto), HttpStatus.OK);
    }
	
}
