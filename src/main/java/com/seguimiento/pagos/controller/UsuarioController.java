package com.seguimiento.pagos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seguimiento.pagos.entity.Usuario;
import com.seguimiento.pagos.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {


	@Autowired
	private UsuarioService usuarioService;
	
    @GetMapping("/obtenerTodos")
    public ResponseEntity<List<Usuario>> list(){
        return new ResponseEntity(usuarioService.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id") int id){
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> create(@RequestBody Usuario usuario){
    	return new ResponseEntity(null, HttpStatus.OK);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> update(@RequestBody Usuario usuario){
    	return new ResponseEntity(null, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
    	return new ResponseEntity(null, HttpStatus.OK);
    }


}
