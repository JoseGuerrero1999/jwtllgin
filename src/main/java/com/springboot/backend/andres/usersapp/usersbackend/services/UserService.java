package com.springboot.backend.andres.usersapp.usersbackend.services;


import java.util.List;
import java.util.Optional;


import com.springboot.backend.andres.usersapp.usersbackend.entities.User;
import com.springboot.backend.andres.usersapp.usersbackend.models.UserRequest;

public interface UserService {
	
	/*CRUD*/
	
     List<User> listausuarios();
	 
     User insertaActualizausuario(User usuarionew);
	
	 Optional<User>buscausuarioxid(Long idusuario);
	 
	 void eliminausuarioxid(Long idusuario);
	
	 
	 /*jwt*/
	 
    Optional<User> update(UserRequest user, Long id);
}
