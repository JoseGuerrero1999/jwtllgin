package com.springboot.backend.andres.usersapp.usersbackend.models;


import lombok.Data;
@Data
public class UserRequest implements IUser {


	
	
	private String nombreUsuario;
	
	private String apellidoUsuario;
	

	private String correoUsuario;
	
	
	private String contraUsuario;

    private boolean admin;


    
}
