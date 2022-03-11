package com.seguimiento.pagos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seguimiento.pagos.entity.Usuario;
import com.seguimiento.pagos.repository.UsuarioRepository;
import com.seguimiento.pagos.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> obtenerTodos(){
		return usuarioRepository.findAll();
	}
	
	public Usuario guardar(Usuario usuario){
		usuarioRepository.save(usuario);
		return new Usuario();
	}
	
	public Usuario obtenerPorId(Long id){
		return usuarioRepository.findById(id).orElse(null);
	}
	
	public Usuario actualizar(Usuario usuario){
		usuarioRepository.save(usuario);
		return new Usuario();
	}
	
	public boolean desactivar(){
		return true;
	}
}
