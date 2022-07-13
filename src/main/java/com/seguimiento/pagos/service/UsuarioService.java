package com.seguimiento.pagos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.seguimiento.pagos.entity.Usuario;

// se requiere que extienda de UserDetailsService para haga la busqueda en base de datos de usuario y realice la autenticacion
public interface UsuarioService extends UserDetailsService{

	public List<Usuario> obtenerTodos();
	
	public Usuario guardar(Usuario usuario);
	
	public Usuario obtenerPorId(Long id);
	
	public Boolean actualizar(Usuario usuario);
	
	public boolean desactivar(Long idUsuario);
	
	public Optional<Usuario> getByNombreUsuario(String nombreUsuario);
	
	public boolean existsByNombreUsuario(String nombreUsuario);
	
	public boolean existsByEmail(String email);
	
	//Consultar el usuario a autenticar
	public UserDetails loadUserByUsername(String nombreUsuario);
	
	public Usuario actualizarEstado(Usuario usuario);
	
}
