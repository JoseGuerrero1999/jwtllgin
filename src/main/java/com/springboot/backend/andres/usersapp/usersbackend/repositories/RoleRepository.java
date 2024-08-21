package com.springboot.backend.andres.usersapp.usersbackend.repositories;


import org.springframework.data.jpa.repository.JpaRepository;


import com.springboot.backend.andres.usersapp.usersbackend.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

    Role findByNombreRol(String nombreRol);

}
