package com.seguimiento.pagos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seguimiento.pagos.entity.Rol;
import com.seguimiento.pagos.repository.RolRepository;
import com.seguimiento.pagos.service.RolService;

@Service
@Transactional
public class RolServiceImpl implements RolService {

    @Autowired
    RolRepository rolRepository;

    public Rol getByRolNombre(String rolNombre){
        return rolRepository.findByNombre(rolNombre);
    }

    public void save(Rol rol){
        rolRepository.save(rol);
    }
}
