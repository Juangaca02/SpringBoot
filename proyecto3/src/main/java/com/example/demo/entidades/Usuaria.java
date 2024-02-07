package com.example.demo.entidades;

import java.io.Serializable;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the usuaria database table.
 * 
 */
@Entity
@NamedQuery(name = "Usuaria.findAll", query = "SELECT u FROM Usuaria u")
//@NamedQueries(@NamedQuery(););
public class Usuaria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_elim")
	private Date fechaElim;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_nac")
	private Date fechaNac;

	@Lob
	private byte[] img;

	private String nombre;

	private String pass;

	private String username;

	// bi-directional many-to-one association to Habla
	@JsonIgnore
	@OneToMany(mappedBy = "usuaria")
	private List<Habla> hablas;

	// bi-directional many-to-one association to UsuarioTipo
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "id_tipo_usuario")
	private UsuarioTipo usuarioTipo;

	public Usuaria() {
	}

	public Usuaria(int id2, Date fechaNac2, Date fechaElim2, byte[] img2, String nombre2, String username2,
			String pass2, UsuarioTipo ut) {
		this.id = id2;
		this.fechaElim = fechaNac2;
		this.fechaNac = fechaElim2;
		this.img = img2;
		this.nombre = nombre2;
		this.pass = pass2;
		this.username = username2;
		this.usuarioTipo = ut;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaElim() {
		return this.fechaElim;
	}

	public void setFechaElim(Date fechaElim) {
		this.fechaElim = fechaElim;
	}

	public Date getFechaNac() {
		return this.fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public byte[] getImg() {
		return this.img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Habla> getHablas() {
		return this.hablas;
	}

	public void setHablas(List<Habla> hablas) {
		this.hablas = hablas;
	}

	public Habla addHabla(Habla habla) {
		getHablas().add(habla);
		habla.setUsuaria(this);

		return habla;
	}

	public Habla removeHabla(Habla habla) {
		getHablas().remove(habla);
		habla.setUsuaria(null);

		return habla;
	}

	public UsuarioTipo getUsuarioTipo() {
		return this.usuarioTipo;
	}

	public void setUsuarioTipo(UsuarioTipo usuarioTipo) {
		this.usuarioTipo = usuarioTipo;
	}

	@Override
	public String toString() {
		return "Usuaria [id=" + id + ", fechaElim=" + fechaElim + ", fechaNac=" + fechaNac + ", img="
				+ Arrays.toString(img) + ", nombre=" + nombre + ", pass=" + pass + ", username=" + username
				+ ", hablas=" + hablas + ", usuarioTipo=" + usuarioTipo + "]";
	}

}