package com.springboot.backend.andres.usersapp.usersbackend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.backend.andres.usersapp.usersbackend.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User>  findByCorreoUsuario(String correoUsuario);
}
