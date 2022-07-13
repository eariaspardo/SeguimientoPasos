package com.seguimiento.pagos.modelo.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseProyecto {
	
	private Long id;

	private String nombreproyecto;
	
	private Integer valorproyecto;
	
	private Integer cuotas;
	
	private Date fechapagos;
	
	private ResponseUsuario usuario;
	
	private List<ResponseDetalleCuotas> detallesPagos; 
	
}
