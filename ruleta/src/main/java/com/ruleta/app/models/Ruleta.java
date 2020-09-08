package com.ruleta.app.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.ruleta.app.dto.ApuestaDTO;

@Entity
@Table(name = "ruleta")
public class Ruleta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	private Long id;
	@Column(name="estado")
	private Boolean estado;
	@JoinColumn(name="apuesta_id")
	private List<Apuesta> apuestas;

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

	public List<Apuesta> getApuestas() {
		return apuestas;
	}

	public void setApuestas(List<Apuesta> apuestas) {
		this.apuestas = apuestas;
	}

}
