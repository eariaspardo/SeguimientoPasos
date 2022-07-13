package com.seguimiento.pagos.modelo.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDetalleCuotas {
	
	private Long id;
	
	private String numeroCuota;
	
	private Integer valorPagado;
	
	private Date fechaPagado;
	
	private Integer usuarioPago;
	
	private ResponseAdjunto idAdjunto;
	
}
