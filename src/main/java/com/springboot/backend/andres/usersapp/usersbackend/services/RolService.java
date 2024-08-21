package com.springboot.backend.andres.usersapp.usersbackend.services;

import java.util.List;

import com.springboot.backend.andres.usersapp.usersbackend.entities.Role;



public interface RolService {

	List<Role> listatipos();
	 
	Role nombreRol(String nombreRol);
}
