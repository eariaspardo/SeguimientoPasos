package com.seguimiento.pagos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seguimiento.pagos.entity.DetalleCuotas;

@Repository
public interface DetalleProyectoRepository extends JpaRepository<DetalleCuotas, Long> {
	
	List<DetalleCuotas> findByIdProyecto(Long idProyecto);
	
}
