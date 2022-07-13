package com.seguimiento.pagos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seguimiento.pagos.entity.Adjunto;
import com.seguimiento.pagos.repository.AdjuntoRepository;
import com.seguimiento.pagos.service.AdjuntoService;

@Service
@Transactional
public class AdjuntoServiceImpl implements AdjuntoService{

	@Autowired
	private AdjuntoRepository adjuntoRepository;
	
    public AdjuntoServiceImpl(AdjuntoRepository adjuntoRepository) {
        this.adjuntoRepository = adjuntoRepository;
    }
	
	public List<Adjunto> obtenerTodos(){
		return adjuntoRepository.findAll();
	}

	public Adjunto obtenerAdjuntoPorCuota(Long idAdjunto) {
		return adjuntoRepository.findById(idAdjunto).orElse(null);
	}

	public Adjunto saveAdjunto(Adjunto adjunto) {
		return adjuntoRepository.save(adjunto);
	}

	public Adjunto updateAdjunto(Adjunto adjunto) {
		return adjuntoRepository.save(adjunto);
	}

}
