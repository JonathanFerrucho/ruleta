package com.ruleta.app.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruleta.app.dto.ApuestaDTO;
import com.ruleta.app.dto.RuletaDTO;
import com.ruleta.app.service.AutenticacionService;
import com.ruleta.app.service.RuletaService;

@RestController
@RequestMapping("/ruleta")
public class RuletaController {
	
	@Autowired
	private RuletaService ruletaService;
	@Autowired
	private AutenticacionService autenticacionService;
	
	@GetMapping("/obtenerTodos")
	public  List<RuletaDTO> obtenerTodos() {
		return ruletaService.obtenerTodos();
	}
	
	@GetMapping("/crear")
	public Long crear() {
		return ruletaService.crear();
	}
	
	@GetMapping({"/abrir"})
	public Boolean abrir( @RequestParam(name="idRuleta") Long idRuleta) {
		return ruletaService.abrir(idRuleta);
	}
	
	@GetMapping({"/cerrar"})
	public RuletaDTO cerrar( @RequestParam(name="idRuleta") Long idRuleta) {
		return ruletaService.cerrar(idRuleta);
	}
	
	@PostMapping({"/apostar"})
	public Boolean apostar(HttpServletRequest request, 
			@RequestParam(name="idRuleta") Long idRuleta,
			@RequestParam(name="apuesta") ApuestaDTO apuestaDTO) {
		
		return autenticacionService.validacionAutenticacion(request) ?
				ruletaService.apostar(idRuleta, apuestaDTO):Boolean.FALSE;
	}
}
