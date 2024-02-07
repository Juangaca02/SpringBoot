package com.example.demo.repositorios;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entidades.Habla;
import com.example.demo.entidades.Idioma;
import com.example.demo.entidades.Usuaria;

import jakarta.transaction.Transactional;

public interface HablaRepositorio extends JpaRepository<Habla,Serializable> {
	
	@Bean
	public abstract List<Habla> findAll();
	public abstract Habla findByid(int id);
	public abstract List<Habla> findByUsuaria(Usuaria u);
	public abstract List<Habla> findByIdioma(Idioma i);
	
	@Transactional
	public abstract void deleteById(int id);
	
	@Transactional 
	public abstract Habla save(Habla h);
}
