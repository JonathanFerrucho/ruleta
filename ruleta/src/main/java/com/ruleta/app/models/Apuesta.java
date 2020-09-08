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
public class Apuesta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="id")
	private Long id;
	@Column(name="color")
	String color;
	@Column(name="dinero")
	Integer dinero;
	@Column(name="numeros")
	String numeros;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getDinero() {
		return dinero;
	}
	public void setDinero(Integer dinero) {
		this.dinero = dinero;
	}
	public String getNumeros() {
		return numeros;
	}
	public void setNumeros(String numeros) {
		this.numeros = numeros;
	}
}
