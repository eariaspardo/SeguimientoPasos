package com.seguimiento.pagos.modelo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseAdjunto {
   
	private String nombre;
	
	private String urlAdjunto;
	
	private String descripcion;
	
}
