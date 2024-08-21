package com.springboot.backend.andres.usersapp.usersbackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name ="tb_usuarios")
public class User   {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long idUsuario;
	
	@NotBlank(message = "Debe escribir el nombre del usuario")
	@Pattern(regexp = "^[a-zA-Z\\s]+$")
	@Size(min = 2, max = 99, message = "El nombre del usuario debe tener entre 2 y 99 caracteres")
	@Column(name = "nombre_usuario")
	private String nombreUsuario;
	
	
	@NotBlank(message = "Debe escribir el apellido del usuario")
	@Pattern(regexp = "^[a-zA-Z\\s]+$")
	@Size(min = 2, max = 99, message = "El apellido del usuario debe tener entre 2 y 99 caracteres")
	@Column(name = "apellido_usuario")
	private String apellidoUsuario;
	
	@NotBlank(message = "Debe escribir el correo del usuario")
	@Email(message = "Formato de correo inválido")
	@Pattern(regexp = "^[\\w\\.-]+@reservas\\.com$", message = "El correo debe terminar en @reservas.com")
	@Column(name = "correo_usuario")
	private String correoUsuario;
	
	@NotBlank(message = "Debe escribir su contraseña")
    @Size(min = 2, max = 99, message = "La contraseña debe tener entre 2 y 99 caracteres")
	@Column(name = "contrapassword_usuario")
	private String contraUsuario;
	
	@Column(name = "fecharegistro_usuario")
	private Date fecharegistroUsuario;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	    name = "tb_rolesxusuario",
	    joinColumns = @JoinColumn(name = "id_usuariox"), 
	    inverseJoinColumns = @JoinColumn(name = "id_rolx"),
	    uniqueConstraints = @UniqueConstraint(columnNames = {"id_usuariox", "id_rolx"}) 
	)
	private List<Role> roles = new ArrayList<>();



    
}
