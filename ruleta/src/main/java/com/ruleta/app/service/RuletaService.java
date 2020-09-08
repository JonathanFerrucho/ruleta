package com.ruleta.app.service;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruleta.app.dto.ApuestaDTO;
import com.ruleta.app.dto.RuletaDTO;
import com.ruleta.app.models.Apuesta;
import com.ruleta.app.models.Ruleta;
import com.ruleta.app.repositories.RuletaRepository;

@Service
public class RuletaService {

	@Autowired
	private RuletaRepository ruletaRepository;
	@Autowired
	private ApuestaService apuestaService;

	private static final Long MAXIMA_APUESTA = 10000L;
	private static final String COLOR_NEGRO = "NEGRO";
	private static final String COLOR_BLANCO = "BLANCO";
	private static final int NUMERO_MENOR = 0;
	private static final int NUMERO_MAYOR = 36;
	
	public List<RuletaDTO> obtenerTodos() {
		return this.convertirEntidad(ruletaRepository.obtenerTodos());
	}
	
	public Long crear() {
		Ruleta ruleta = new Ruleta();
		ruleta.setEstado(Boolean.FALSE);

		ruleta = ruletaRepository.save(ruleta);
		return ruleta.getId();
	}

	public Boolean abrir(Long idRuleta) {
		Optional<Ruleta> optRuleta = ruletaRepository.findById(idRuleta);

		if (optRuleta.isPresent()) {
			RuletaDTO ruletaDTO = this.convertirEntidad(optRuleta.get());
			if (!ruletaDTO.getEstado()) {
				ruletaDTO.setEstado(Boolean.TRUE);
				ruletaRepository.save(this.convertirDTO(ruletaDTO));
				return Boolean.TRUE;
			}
		}

		return Boolean.FALSE;
	}
	
	public RuletaDTO cerrar(Long idRuleta) {
		Optional<Ruleta> optRuleta = ruletaRepository.findById(idRuleta);

		if (optRuleta.isPresent()) {
			RuletaDTO ruletaDTO = this.convertirEntidad(optRuleta.get());
			if (!ruletaDTO.getEstado()) {
				ruletaDTO.setEstado(Boolean.FALSE);
				ruletaRepository.save(this.convertirDTO(ruletaDTO));
				return ruletaDTO;
			}
		}

		return null;
	}
	public Boolean apostar(Long idRuleta, ApuestaDTO apuestaDTO) {
		Optional<Ruleta> optRuleta = ruletaRepository.findById(idRuleta);
		if (apuestaDTO != null && MAXIMA_APUESTA >= apuestaDTO.getDinero()
				&& (apuestaDTO.getColor() == null || COLOR_NEGRO.equalsIgnoreCase(apuestaDTO.getColor())
						|| COLOR_BLANCO.equalsIgnoreCase(apuestaDTO.getColor()))) {

			if (apuestaDTO.getNumeros().stream().anyMatch(n -> NUMERO_MENOR < n || NUMERO_MAYOR > n)) {
				return Boolean.FALSE;
			}

			if (optRuleta.isPresent()) {
				RuletaDTO ruletaDTO = this.convertirEntidad(optRuleta.get());
				if (ruletaDTO.getEstado()) {

					List<ApuestaDTO> apuestas = ruletaDTO.getApuestas();
					if (apuestas == null) {
						apuestas = new ArrayList<>();
					}
					apuestaDTO = apuestaService.crear(apuestaDTO);
					apuestas.add(apuestaDTO);
					ruletaRepository.save(this.convertirDTO(ruletaDTO));
					return Boolean.TRUE;
				}
			}
		}

		return Boolean.FALSE;
	}

	public List<RuletaDTO> convertirEntidad(List<Ruleta> entidades) {
		if (entidades == null) {
			return null;
		}
		
		List<RuletaDTO> dtos = new ArrayList<>();
		for (Ruleta entidad : entidades) {
			dtos.add(this.convertirEntidad(entidad));
		}

		return dtos;
	}

	
	public RuletaDTO convertirEntidad(Ruleta entidad) {
		if (entidad == null) {
			return null;
		}
		RuletaDTO dto = new RuletaDTO();
		dto.setId(entidad.getId());
		dto.setEstado(entidad.getEstado());
		if(entidad.getApuestas() != null && ! entidad.getApuestas().isEmpty()) {
			dto.setApuestas(apuestaService.convertirEntidad(entidad.getApuestas()));
		}
		
		
		return dto;
	}

	public Ruleta convertirDTO(RuletaDTO dto) {
		if (dto == null) {
			return null;
		}
		Ruleta entidad = new Ruleta();
		entidad.setId(dto.getId());
		entidad.setEstado(dto.getEstado());
		if (dto.getApuestas() != null && !dto.getApuestas().isEmpty()) {
			List<Apuesta> apuestas = new ArrayList<>();
			for (ApuestaDTO apuestaDTO : dto.getApuestas()) {
				apuestas.add(apuestaService.convertirDTO(apuestaDTO));
			}
		}
		return entidad;
	}
}
