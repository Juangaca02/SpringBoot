package com.example.demo.repositorios;

import java.io.Serializable;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entidades.UsuarioTipo;

public interface UsuarioTipoRepositorio extends JpaRepository<UsuarioTipo,Serializable>{

	@Bean
	public abstract UsuarioTipo findById(int id);

}
