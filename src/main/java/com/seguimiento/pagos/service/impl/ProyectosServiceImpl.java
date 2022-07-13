package com.seguimiento.pagos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seguimiento.pagos.entity.Proyecto;
import com.seguimiento.pagos.modelo.response.Resumen;
import com.seguimiento.pagos.repository.ProyectosRepository;
import com.seguimiento.pagos.service.ProyectoService;

@Service
@Transactional
public class ProyectosServiceImpl implements ProyectoService{

	@Autowired
	private ProyectosRepository proyectosRepository;
	
    public ProyectosServiceImpl(ProyectosRepository proyectosRepository) {
        this.proyectosRepository = proyectosRepository;
    }
	
	public List<Proyecto> obtenerTodos(){
		return proyectosRepository.findAll();
	}

	public List<Proyecto> proyectoPorUsuario(Integer idUsuario) {
		return proyectosRepository.findByUsuario(idUsuario);
	}

	public Proyecto proyecto(Long idProyecto) {
		return proyectosRepository.findById(idProyecto).orElse(null);
	}
	
	public Proyecto guardar(Proyecto proyecto) {
		return proyectosRepository.save(proyecto);
	}
	
	public Resumen resumenProyectos(Integer idUsuario) {
		Resumen resumen = new Resumen();
		List<Long> proyectosIds = new ArrayList<>();
		List<Proyecto> listaProyectos = proyectosRepository.findByUsuario(idUsuario);
		for(Proyecto listado : listaProyectos) {
			if(listado.getEstado().equals("Abierto")) {
				resumen.setProyectosAbiertos(resumen.getProyectosAbiertos()+1);				
			}
			if(listado.getEstado().equals("Cerrado")) {
				resumen.setProyectosCerrados(resumen.getProyectosCerrados()+1);				
			}
			if(listado.getEstado().equals("Pausado")) {
				resumen.setProyectosPausados(resumen.getProyectosPausados()+1);				
			}
			resumen.setTotalDeudas(resumen.getTotalDeudas() + listado.getValorproyecto());
			proyectosIds.add(listado.getId());
		}
		if(!proyectosIds.isEmpty()) {
			resumen.setTotalInversiones(proyectosRepository.consultarInversion(proyectosIds));
		}
		resumen.setTotalProyectos(listaProyectos.size());
		
		return resumen;
	}

	@Transactional
	public Proyecto actualizarProyecto(Proyecto proyecto) {
		proyectosRepository.actualizarEstado(proyecto.getId(), proyecto.getEstado());
		return proyecto(proyecto.getId());
	}
	
}
