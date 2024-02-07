package com.example.demo.entidades;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the idioma database table.
 * 
 */
@Entity
@NamedQuery(name="Idioma.findAll", query="SELECT i FROM Idioma i")
public class Idioma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String nombre;

	//bi-directional many-to-one association to Habla
	@OneToMany(mappedBy="idioma")
	@JsonIgnore
	private List<Habla> hablas;

	public Idioma() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Habla> getHablas() {
		return this.hablas;
	}

	public void setHablas(List<Habla> hablas) {
		this.hablas = hablas;
	}

	public Habla addHabla(Habla habla) {
		getHablas().add(habla);
		habla.setIdioma(this);

		return habla;
	}

	public Habla removeHabla(Habla habla) {
		getHablas().remove(habla);
		habla.setIdioma(null);

		return habla;
	}

}