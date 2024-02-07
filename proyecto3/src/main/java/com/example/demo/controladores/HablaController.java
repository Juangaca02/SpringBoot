package com.example.demo.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidades.Habla;
import com.example.demo.repositorios.HablaRepositorio;
import com.example.demo.repositorios.UsuariaRepositorio;

@CrossOrigin
@RestController
@RequestMapping("/habla")
public class HablaController {
	
	@Autowired
	HablaRepositorio hr;
	@Autowired
	UsuariaRepositorio ur; 
	
	@GetMapping("/quienhablaque")
	public List<DTO> getidiomas(){
		List<DTO> listaSalida=new  ArrayList<DTO>();
		List<Habla> la= hr.findAll();
		
		for(Habla h:la) {
			DTO dtoHabla=new DTO();
			dtoHabla.put("id", h.getId());
			dtoHabla.put("idioma", h.getIdioma().getNombre());
			dtoHabla.put("usuario", h.getUsuaria().getNombre());
			listaSalida.add(dtoHabla);
		}
		
		return listaSalida;
	}
	
}