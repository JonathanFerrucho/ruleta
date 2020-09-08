package com.ruleta.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.ruleta.app.dto.ApuestaDTO;
import com.ruleta.app.dto.RuletaDTO;
import com.ruleta.app.models.Apuesta;
import com.ruleta.app.models.Ruleta;
import com.ruleta.app.repositories.ApuestaRepository;

public class ApuestaService {
	@Autowired
	private ApuestaRepository apuestaRepository;

	public ApuestaDTO crear(ApuestaDTO dto) {
		Apuesta apuesta= apuestaRepository.save(this.convertirDTO(dto));
		return this.convertirEntidad(apuesta);
	}
	
	public List<ApuestaDTO> convertirEntidad(List<Apuesta> entidades) {
		if (entidades == null) {
			return null;
		}
		
		List<ApuestaDTO> dtos = new ArrayList<>();
		for (Apuesta entidad : entidades) {
			dtos.add(this.convertirEntidad(entidad));
		}

		return dtos;
	}
	
	public ApuestaDTO convertirEntidad(Apuesta entidad) {
		if (entidad == null) {
			return null;
		}
		ApuestaDTO dto = new ApuestaDTO();
		dto.setId(entidad.getId());
		dto.setColor(entidad.getColor());
		dto.setDinero(entidad.getDinero());
		if(dto.getNumeros() != null) {
			entidad.setNumeros(dto.getNumeros().stream().map(Object::toString).collect(Collectors.joining(",")));
		}
		dto.setColor(entidad.getColor());
		return dto;
	}

	public List<Apuesta> convertirDTO(List<ApuestaDTO> dtos) {
		if (dtos == null) {
			return null;
		}
		
		List<Apuesta> entidades = new ArrayList<>();
		for (ApuestaDTO dto: dtos) {
			entidades.add(this.convertirDTO(dto));
		}

		return entidades;
	}
	
	public Apuesta convertirDTO(ApuestaDTO dto) {
		if (dto == null) {
			return null;
		}

		Apuesta entidad = new Apuesta();
		entidad.setId(dto.getId());
		entidad.setColor(dto.getColor());
		entidad.setDinero(dto.getDinero());
		if(entidad.getNumeros() != null) {
			List<String> numeros=Arrays.asList(entidad.getNumeros().split(","));
			
			dto.setNumeros(numeros.stream() 
		            .map(Integer::parseInt) 
		            .collect(Collectors.toList()));
		}
		return entidad;
	}

}
