package com.springboot.backend.andres.usersapp.usersbackend.controllers;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.springboot.backend.andres.usersapp.usersbackend.entities.Role;
import com.springboot.backend.andres.usersapp.usersbackend.entities.User;
import com.springboot.backend.andres.usersapp.usersbackend.services.RolService;
import com.springboot.backend.andres.usersapp.usersbackend.services.UserService;

import jakarta.validation.Valid;



@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userservi;

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RolService rolservi;
  
  
	@GetMapping("/listadeusuarios")
	@ResponseBody
	public ResponseEntity<List<User>>listaproduct() {
	List<User> lista = userservi.listausuarios();
	return ResponseEntity.ok(lista);
	}
	
	
	@PostMapping("/registrousuario")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> registrarUsuario(@Valid @RequestBody User usue, Errors errors) {
	    Map<String, Object> response = new HashMap<>();
	    List<String> mensajes = new ArrayList<>();
	    response.put("errores", mensajes);  
	    if (errors.hasErrors()) {
	        errors.getAllErrors().forEach(error -> mensajes.add(error.getDefaultMessage()));
	        return ResponseEntity.badRequest().body(response);
	    } 
	    try {
	        String encryptedPassword = passwordEncoder.encode(usue.getContraUsuario());
	        usue.setContraUsuario(encryptedPassword);
	        usue.setFecharegistroUsuario(Date.valueOf(LocalDate.now()));
	        Role rolCliente = rolservi.nombreRol("Cliente"); 
	        
	        if (rolCliente != null) {
	            usue.getRoles().add(rolCliente);
	        } else {
	            mensajes.add("El rol 'Cliente' no se encontró en la base de datos.");
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	        }
	        User usuarioGuardado = userservi.insertaActualizausuario(usue);
	        
	        if (usuarioGuardado != null) {
	            mensajes.add("Se registró el usuario con ID => " + usuarioGuardado.getIdUsuario() + " y Nombre: " + usuarioGuardado.getNombreUsuario());
	            return ResponseEntity.ok(response);
	        } else {
	            mensajes.add("Ocurrió un error al registrar el usuario.");
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	        }
	    } catch (Exception e) {
	        mensajes.add("Error inesperado: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

	
	@PutMapping("/editarusuario/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizarUsuario(@PathVariable Long id, @Valid @RequestBody User usue, Errors errors) {
	    Map<String, Object> response = new HashMap<>();
	    List<String> mensajes = new ArrayList<>();
	    response.put("errores", mensajes);

	    if (errors.hasErrors()) {
	        errors.getAllErrors().forEach(error -> mensajes.add(error.getDefaultMessage()));
	        return ResponseEntity.badRequest().body(response);
	    }

	    try {
	    	User usuarioExistente = userservi.buscausuarioxid(id).orElse(null);

	        if (usuarioExistente == null) {
	            mensajes.add("No existe el usuario con ID => " + id);
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }
	        if (usue.getContraUsuario() != null && !usue.getContraUsuario().isEmpty()) {
	            String encryptedPassword = passwordEncoder.encode(usue.getContraUsuario());
	            usue.setContraUsuario(encryptedPassword);
	        } else {
	            usue.setContraUsuario(usuarioExistente.getContraUsuario());
	        }

	        usue.setIdUsuario(id);
	        User usuarioActualizado = userservi.insertaActualizausuario(usue);

	        if (usuarioActualizado != null) {
	            mensajes.add("Se actualizó el usuario con ID => " + usuarioActualizado.getIdUsuario() + " y Nombre: " + usuarioActualizado.getNombreUsuario());
	            return ResponseEntity.ok(response);
	        } else {
	            mensajes.add("Ocurrió un error al actualizar el usuario.");
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	        }
	    } catch (Exception e) {
	        mensajes.add("Error inesperado: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}


	@DeleteMapping("/eliminarusuario/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaUsuario(@PathVariable("id") Long id) {
	    Map<String, Object> salida = new HashMap<>();
	    Optional<User> usuario = userservi.buscausuarioxid(id);
	    if (usuario.isPresent()) {
	    
	        userservi.eliminausuarioxid(id);
	        salida.put("mensaje", "Se eliminó el usuario con ID => " + id);
	        return ResponseEntity.ok(salida);
	    } else {
	        salida.put("mensaje", "No existe el usuario con ID => " + id);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(salida);
	    }
	    
	}
 
    
  
}
