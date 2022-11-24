package com.seguimiento.pagos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seguimiento.pagos.entity.DetalleCuotas;
import com.seguimiento.pagos.repository.DetalleProyectoRepository;
import com.seguimiento.pagos.service.DetalleProyectoService;

@Service
@Transactional
public class DetalleProyectosServiceImpl implements DetalleProyectoService{

	@Autowired
	private DetalleProyectoRepository detalleProyectoRepository;
	
    public DetalleProyectosServiceImpl(DetalleProyectoRepository detalleProyectoRepository) {
        this.detalleProyectoRepository = detalleProyectoRepository;
    }
	
	public List<DetalleCuotas> obtenerTodos(){
		return detalleProyectoRepository.findAll();
	}

	public List<DetalleCuotas> detallePorProyecto(Long idProyecto) {
		return detalleProyectoRepository.findByIdProyecto(idProyecto);
	}

	public DetalleCuotas detalleCuota(Long idDetalleProyecto) {
		return detalleProyectoRepository.findById(idDetalleProyecto).orElse(null);
	}

	public DetalleCuotas updateCuota(DetalleCuotas detalleProyecto) {
		return detalleProyectoRepository.save(detalleProyecto);
	}

	public DetalleCuotas saveCuota(DetalleCuotas detalleProyecto) {
		detalleProyecto.setId(null);
		detalleProyecto.setIdAdjunto(detalleProyecto.getIdAdjunto() == 0 ? null : detalleProyecto.getIdAdjunto());
		return detalleProyectoRepository.save(detalleProyecto);
	}

}
