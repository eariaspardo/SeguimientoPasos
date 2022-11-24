package com.seguimiento.pagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seguimiento.pagos.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
   
	Rol findByNombre(String rolNombre);
	
}
