package com.seguimiento.pagos.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.seguimiento.pagos.entity.Usuario;
import com.seguimiento.pagos.repository.UsuarioRepository;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserNameAnnotations, String> {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Usuario user = usuarioRepository.findByNombreUsuario(value).orElse(null);
		if(user == null) {
			return true;
		}else {
			return false;
		}
	}

}
