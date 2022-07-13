package com.seguimiento.pagos.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seguimiento.pagos.entity.Adjunto;
import com.seguimiento.pagos.service.AdjuntoService;

@RestController
@RequestMapping("/adjunto")
@CrossOrigin(origins = {"*", "http://localhost:4100/"}, maxAge = 3600)
public class AdjuntoController {

	@Autowired
	private AdjuntoService adjuntoService;
	
	@GetMapping("/obtenerTodos")
    public ResponseEntity<List<Adjunto>> obtenerTodos(){
        return new ResponseEntity<List<Adjunto>>(adjuntoService.obtenerTodos(), HttpStatus.OK);
    }
	
	@GetMapping("/obtenerTodos/{idAdjunto}")
    public ResponseEntity<Adjunto> obtenerAdjuntoPorCuota(@PathVariable(value = "idAdjunto") Long idAdjunto){
        return new ResponseEntity<Adjunto>(adjuntoService.obtenerAdjuntoPorCuota(idAdjunto), HttpStatus.OK);
    }
	
	@GetMapping("/guardarAdjunto")
    public ResponseEntity<Adjunto> saveAdjunto(@RequestBody Adjunto saveAdjunto){
        return new ResponseEntity<Adjunto>(adjuntoService.saveAdjunto(saveAdjunto), HttpStatus.OK);
    }
	
	@GetMapping("/actualizarAdjunto")
    public ResponseEntity<Adjunto> updateAdjunto(@RequestBody @Valid Adjunto saveAdjunto){
        return new ResponseEntity<Adjunto>(adjuntoService.updateAdjunto(saveAdjunto), HttpStatus.OK);
    }
	
}
