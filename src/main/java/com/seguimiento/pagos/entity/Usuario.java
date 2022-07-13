package com.seguimiento.pagos.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@DynamicUpdate
@Table(name = "usuarios")
public class Usuario {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombreapellido")
    private String nombreApellido;
    
	@Column(unique = true, length = 30, name = "nombreusuario")
    private String nombreUsuario;
    
	@JsonIgnore
    private String password;
    
    private boolean activo;
    
    private String email;	
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "id_usuarios"), 
	inverseJoinColumns = @JoinColumn(name = "id_roles"), 
	uniqueConstraints = {@UniqueConstraint(columnNames = { "id_usuarios", "id_roles" }) })
	private List<Rol> roles;

}
