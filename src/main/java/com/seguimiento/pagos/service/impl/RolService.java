package com.seguimiento.pagos.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seguimiento.pagos.entity.Rol;
import com.seguimiento.pagos.repository.RolRepository;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(String rolNombre){
        return rolRepository.findByNombre(rolNombre);
    }

    public void save(Rol rol){
        rolRepository.save(rol);
    }
}
