package com.springboot.backend.andres.usersapp.usersbackend.services;


import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.springboot.backend.andres.usersapp.usersbackend.entities.User;

import com.springboot.backend.andres.usersapp.usersbackend.models.UserRequest;
import com.springboot.backend.andres.usersapp.usersbackend.repositories.RoleRepository;
import com.springboot.backend.andres.usersapp.usersbackend.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository repository;


    
    
    public UserServiceImpl(UserRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
     
      
    }

    @Override
    @Transactional
    public Optional<User> update(UserRequest user, Long id) {
        Optional<User> userOptional = repository.findById(id);

        if (userOptional.isPresent()) {
            User userDb = userOptional.get();
            userDb.setCorreoUsuario(user.getCorreoUsuario());
            userDb.setNombreUsuario(user.getNombreUsuario());
            userDb.setApellidoUsuario(user.getApellidoUsuario());
            return Optional.of(repository.save(userDb));
        }
        return Optional.empty();
    }

   
 

	@Override
	public List<User> listausuarios() {
	
		return repository.findAll();
	}

	@Override
	public User insertaActualizausuario(User usuarionew) {
	
		return repository.save(usuarionew);
	}

	@Override
	public Optional<User> buscausuarioxid(Long idusuario) {
		
		return repository.findById(idusuario);
	}

	@Override
	public void eliminausuarioxid(Long idusuario) {
	
		repository.deleteById(idusuario);
		
	}

}
