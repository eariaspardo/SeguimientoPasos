package com.seguimiento.pagos.service;

import java.util.List;

import com.seguimiento.pagos.entity.Adjunto;

public interface AdjuntoService{

	public List<Adjunto> obtenerTodos();
	
	public Adjunto obtenerAdjuntoPorCuota(Long idAdjunto);
	
	public Adjunto saveAdjunto(Adjunto adjunto);
	
	public Adjunto updateAdjunto(Adjunto adjunto);
	
}
