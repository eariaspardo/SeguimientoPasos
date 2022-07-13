package com.seguimiento.pagos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.seguimiento.pagos.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	 Optional<Usuario> findByNombreUsuario(String nombreUsuario);
	 
	 boolean existsByNombreUsuario(String nombreUsuario);
	    
	 boolean existsByEmail(String email);
	 
	 @Modifying
	@Query(value ="UPDATE Usuario SET activo=:activo WHERE id=:id")
	void actualizarEstado(@Param("id") Long id, @Param("activo") boolean activo);

}
