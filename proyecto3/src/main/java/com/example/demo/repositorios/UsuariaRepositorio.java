package com.example.demo.repositorios;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.Usuaria;

import jakarta.transaction.Transactional;


@Repository
public interface UsuariaRepositorio extends JpaRepository<Usuaria,Serializable>{

	public Usuaria findByNombre(String nombre);
	
	@Bean
	public abstract List<Usuaria> findAll();
	public abstract Usuaria findById(int id); 
	public abstract Usuaria findByUsernameAndPass(String username, String pass);
	
	@Transactional
	public abstract Usuaria save(Usuaria u);
	@Transactional
	public abstract void  deleteById(int id);
	
}