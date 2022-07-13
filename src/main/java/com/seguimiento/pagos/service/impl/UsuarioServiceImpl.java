package com.seguimiento.pagos.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seguimiento.pagos.entity.Usuario;
import com.seguimiento.pagos.repository.UsuarioRepository;
import com.seguimiento.pagos.service.UsuarioService;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	// Metodo para encriptar codigo
	BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
	
	public List<Usuario> obtenerTodos(){
		return usuarioRepository.findAll();
	}
	
	public Usuario guardar(Usuario usuario){
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		return usuarioRepository.save(usuario);
	}
	
	public Usuario obtenerPorId(Long id){
		return usuarioRepository.findById(id).orElse(null);
	}
	
	public Boolean actualizar(Usuario usuario){
		Usuario user = usuarioRepository.findById(usuario.getId()).orElse(null);
		
		if(user != null) {
			usuarioRepository.save(usuario);
			return true;
		}
		
		return false;
	}
	
	public boolean desactivar(Long idUsuario){
		Usuario user = usuarioRepository.findById(idUsuario).orElse(null);
		
		if(user != null) {
			user.setActivo(false);
			usuarioRepository.save(user);
			return true;
		}
		return false;
	}
	
	public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public boolean existsByNombreUsuario(String nombreUsuario){
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    // Consultar el usuario a autenticar
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
    	Usuario userEntity = usuarioRepository.findByNombreUsuario(nombreUsuario).orElse(null);
    	if(userEntity==null) {
    		throw new UsernameNotFoundException(nombreUsuario);
    	}
		return new User(userEntity.getNombreUsuario(), userEntity.getPassword(), new ArrayList<>()); // la clase "User" es de Security
	}
    
    public Usuario actualizarEstado(Usuario usuario){
		usuarioRepository.actualizarEstado(usuario.getId(), usuario.isActivo());
		return obtenerPorId(usuario.getId());
	}
    
}
