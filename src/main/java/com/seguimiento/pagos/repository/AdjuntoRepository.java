package com.seguimiento.pagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seguimiento.pagos.entity.Adjunto;

@Repository
public interface AdjuntoRepository extends JpaRepository<Adjunto, Long> {
	
}
