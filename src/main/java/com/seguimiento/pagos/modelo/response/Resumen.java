package com.seguimiento.pagos.modelo.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Resumen {
	
	private Integer proyectosAbiertos = 0;
	
	private Integer proyectosCerrados = 0;
	
	private Integer proyectosPausados = 0;
	
	private Integer totalInversiones = 0;
	
	private Integer totalDeudas = 0;
	
	private Integer totalProyectos = 0;
	
}
