package com.seguimiento.pagos.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@DynamicUpdate
@Table(name = "proyectos")
public class Proyecto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nombreproyecto;
	
	@NotNull
	private Integer valorproyecto;
	
	@NotNull
	private Integer cuotas;
	
	private Date fechapagos;
	
	@NotNull
	private Integer usuario;
	
	private String icono;
	
	@OneToMany(mappedBy = "id")
	private List<DetalleCuotas> detallesPagos; 
	
	private String estado;
	
}
