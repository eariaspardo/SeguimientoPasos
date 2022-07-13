package com.seguimiento.pagos.modelo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseUsuario {
   
	private Long id;
	
    private String nombreApellido;
    
    private String nombreUsuario;
    
    private String email;	
	
}
