package com.seguimiento.pagos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.seguimiento.pagos.entity.Proyecto;

@Repository
public interface ProyectosRepository extends JpaRepository<Proyecto, Long> {
	
	List<Proyecto> findByUsuario(Integer usuario);
	
	@Query(value ="SELECT sum(valor_pagado) from detalle_proyecto where id_proyecto in (:proyectos)", nativeQuery = true)
	Integer consultarInversion(@Param("proyectos") List<Long> proyectos);
	
	@Modifying
	@Query(value ="UPDATE Proyecto SET estado=:estado WHERE id=:id")
	void actualizarEstado(@Param("id") Long id, @Param("estado") String estado);
	
}
