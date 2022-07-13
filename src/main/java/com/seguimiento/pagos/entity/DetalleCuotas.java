package com.seguimiento.pagos.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@DynamicUpdate
@Table(name = "detalle_proyecto")
public class DetalleCuotas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private Integer numeroCuota;
	
	@NotNull
	private Integer valorPagado;
	
	@NotNull
	private Date fechaPagado;
	
	@NotNull
	private Integer usuarioPago;
	
	private Integer idAdjunto;
	
	/*@ManyToOne
    @JoinColumn(name = "id", updatable = false, insertable = false)
	private Proyecto proyecto;*/
	
	private Long idProyecto;

}
