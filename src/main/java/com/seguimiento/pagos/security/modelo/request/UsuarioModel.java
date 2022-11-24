package com.seguimiento.pagos.security.modelo.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.seguimiento.pagos.validators.UniqueUserNameAnnotations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioModel {
   
	private Long id;
	
    private String nombreApellido;
    
	@NotNull
	@UniqueUserNameAnnotations
    private String nombreUsuario;
    
    private String password;
    
    private boolean activo;
    
    private String email;	
	
	private List<RolModel> roles;

}
