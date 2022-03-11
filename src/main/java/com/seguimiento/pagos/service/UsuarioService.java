package com.seguimiento.pagos.service;

import java.util.List;

import com.seguimiento.pagos.entity.Usuario;

public interface UsuarioService {

	public List<Usuario> obtenerTodos();
	
	public Usuario guardar(Usuario usuario);
	
	public Usuario obtenerPorId(Long id);
	
	public Usuario actualizar(Usuario usuario);
	
	public boolean desactivar();
	
}
