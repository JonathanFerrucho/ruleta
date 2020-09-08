package com.ruleta.app.dto;

import java.util.List;

public class RuletaDTO {
	
	private Long id;
	private Boolean estado;
	private List<ApuestaDTO> apuestas;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public List<ApuestaDTO> getApuestas() {
		return apuestas;
	}
	public void setApuestas(List<ApuestaDTO> apuestas) {
		this.apuestas = apuestas;
	}
	
	
}
