package com.seguimiento.pagos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seguimiento.pagos.entity.Rol;
import com.seguimiento.pagos.entity.Usuario;
import com.seguimiento.pagos.enums.RolNombre;
import com.seguimiento.pagos.security.modelo.request.RolModel;
import com.seguimiento.pagos.security.modelo.request.UsuarioModel;
import com.seguimiento.pagos.service.RolService;
import com.seguimiento.pagos.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = {"*", "http://localhost:4100/", "http://localhost:4100/"}, maxAge = 3600)
public class UsuarioController{


	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RolService rolService;
	
    @GetMapping("/obtenerTodos")
    public ResponseEntity<List<Usuario>> list(){
        return new ResponseEntity<List<Usuario>>(usuarioService.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id") Long id){
        return new ResponseEntity<Usuario>(usuarioService.obtenerPorId(id), HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<Usuario> create(@RequestBody @Valid UsuarioModel usu){
    	usu.setId(null);
        Usuario userEntity = new Usuario();
        BeanUtils.copyProperties(usu, userEntity); // En caso de querer mappear el objeto de un entity a un DTO o visebersa
        
        // Mapear todos los Ids con Null
        usu.setRoles(usu.getRoles().stream().map(idRol -> new RolModel(null, idRol.getNombre())).collect(Collectors.toList()));
        
        // Si el Usuario a crear llega sin roles, le va a mappear el usuario por defecto de ROLE_USER
        if(usu.getRoles() == null || usu.getRoles().isEmpty()) {
        	usu.setRoles(new ArrayList<>());
        	usu.getRoles().add(new RolModel(null, RolNombre.ROLE_USER.toString()));
        }
        
        List<Rol> roles = new ArrayList<>();
    	for(RolModel itemRol : usu.getRoles()) {
    		Rol rol = rolService.getByRolNombre(itemRol.getNombre()); 
        	roles.add(rol);
    	}
        userEntity.setRoles(roles);
        
    	return new ResponseEntity<Usuario>(usuarioService.guardar(userEntity), HttpStatus.OK);
    }

    @PutMapping("/actualizarEstado")
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario){
    	return new ResponseEntity<Usuario>(usuarioService.actualizarEstado(usuario), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Boolean> delete(@PathVariable("id")Long id){
    	return new ResponseEntity<Boolean>(usuarioService.desactivar(id), HttpStatus.OK);
    }
    
    @GetMapping("/logueado")
    public ResponseEntity<Usuario> getUserAutenticated(Authentication authentication){
    	Usuario user = usuarioService.getByNombreUsuario(authentication.getPrincipal().toString()).orElse(null);
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }


}
