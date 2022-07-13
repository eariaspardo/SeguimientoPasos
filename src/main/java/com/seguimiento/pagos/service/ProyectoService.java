package com.seguimiento.pagos.service;

import java.util.List;

import com.seguimiento.pagos.entity.Proyecto;
import com.seguimiento.pagos.modelo.response.Resumen;

public interface ProyectoService{

	public List<Proyecto> obtenerTodos();
	
	public List<Proyecto> proyectoPorUsuario(Integer idUsuario);
	
	public Proyecto proyecto(Long idProyecto);
	
	public Resumen resumenProyectos(Integer idUsuario);
	
	public Proyecto guardar(Proyecto proyecto);
	
	public Proyecto actualizarProyecto(Proyecto proyecto);
	
}
