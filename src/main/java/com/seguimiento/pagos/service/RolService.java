package com.seguimiento.pagos.service;

import com.seguimiento.pagos.entity.Rol;

public interface RolService {
	
	public Rol getByRolNombre(String rolNombre);
	
	public void save(Rol rol);

}
