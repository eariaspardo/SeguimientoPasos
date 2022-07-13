package com.seguimiento.pagos.service;

import java.util.List;

import com.seguimiento.pagos.entity.DetalleCuotas;

public interface DetalleProyectoService{

	public List<DetalleCuotas> obtenerTodos();
	
	public List<DetalleCuotas> detallePorProyecto(Long idProyecto);
	
	public DetalleCuotas detalleCuota(Long idDetalleProyecto);
	
	public DetalleCuotas updateCuota(DetalleCuotas detalleProyecto);
	
	public DetalleCuotas saveCuota(DetalleCuotas detalleProyecto);
	
}
