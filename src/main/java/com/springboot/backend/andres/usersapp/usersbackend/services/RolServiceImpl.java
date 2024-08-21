package com.springboot.backend.andres.usersapp.usersbackend.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.springboot.backend.andres.usersapp.usersbackend.entities.Role;
import com.springboot.backend.andres.usersapp.usersbackend.repositories.RoleRepository;

@Service
public class RolServiceImpl implements RolService{

	@Autowired
	RoleRepository rolrepo;
	
	@Override
	public List<Role> listatipos() {
	
		return rolrepo.findAll();
	}

	@Override
	public Role nombreRol(String nombreRol) {
		
		return rolrepo.findByNombreRol(nombreRol);
	}

}
