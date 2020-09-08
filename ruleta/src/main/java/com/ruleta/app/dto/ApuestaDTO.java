package com.ruleta.app.dto;

import java.util.List;

public class ApuestaDTO {
	
	Long id;
	List<Integer> numeros;
	String Color;
	Integer dinero;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Integer> getNumeros() {
		return numeros;
	}
	public void setNumeros(List<Integer> numeros) {
		this.numeros = numeros;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
	public Integer getDinero() {
		return dinero;
	}
	public void setDinero(Integer dinero) {
		this.dinero = dinero;
	}
}
