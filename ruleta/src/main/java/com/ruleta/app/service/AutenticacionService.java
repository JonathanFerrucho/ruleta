package com.ruleta.app.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public class AutenticacionService {
	
	public Boolean validacionAutenticacion(HttpServletRequest request){
		String usuarioId = (String) request.getHeader("usuarioID");
		return usuarioId == null ? Boolean.FALSE:Boolean.TRUE;
		
	}

}
