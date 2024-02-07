package com.example.demo.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.JwtSecurity.AutenticadorJWT;
import com.example.demo.entidades.Usuaria;
import com.example.demo.repositorios.UsuariaRepositorio;
import com.example.demo.repositorios.UsuarioTipoRepositorio;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.DatatypeConverter;

@CrossOrigin
@RestController
@RequestMapping("/proyecto3")
public class UsuariaController {

	@Autowired
	UsuariaRepositorio usuRep;
	@Autowired
	UsuarioTipoRepositorio usuTipoRep;

	@PostMapping(path="/autentica", consumes= MediaType.APPLICATION_JSON_VALUE)
	public DTO autenticaUsuario(@RequestBody DatosAutenticaUsuario datos,
			 HttpServletResponse response) {
		DTO dto=new DTO();
		dto.put("result","fail");
		Usuaria uAutenticado=usuRep.findByUsernameAndPass(datos.username,datos.pass);
		if(uAutenticado!=null) {
			dto.put("result","ok");	
			
			dto.put("jwt", AutenticadorJWT.codificaJWT(uAutenticado));
			Cookie cook=new Cookie("jwt",AutenticadorJWT.codificaJWT(uAutenticado));
			cook.setMaxAge(-1);
			response.addCookie(cook);
		}
		return dto;
	}
	
	@GetMapping("/listarTodos")
	public List<DTO> listarTodos() {
		List<DTO> listarTodosDTO = new ArrayList();
		List<Usuaria> listaUsuarios = usuRep.findAll();

		for (Usuaria u : listaUsuarios) {
			DTO DTOUsuario = new DTO();
			DTOUsuario.put("Id", u.getId());
			DTOUsuario.put("Nombre", u.getNombre());
			DTOUsuario.put("Username", u.getUsername());
			DTOUsuario.put("Pass", u.getPass());
			DTOUsuario.put("FechaNac", u.getFechaNac().toString());
			if (u.getFechaElim() != null) {
				DTOUsuario.put("FechaElim", u.getFechaElim().toString());
			} else {
				DTOUsuario.put("FechaElim",new Date(0));
			}
			DTOUsuario.put("idRol",u.getNombre());//Aqui hay otra cosa
			//DTOUsuario.put("Hablas", u.getHablas());
			//DTOUsuario.put("Img", u.getImg());
			//DTOUsuario.put("UsuarioTipo", u.getUsuarioTipo());

			listarTodosDTO.add(DTOUsuario);
		}

		return listarTodosDTO;
	}
	
	@PostMapping(path="/obtener1", consumes=MediaType.APPLICATION_JSON_VALUE)
	public DTO getUsuario(@RequestBody DTO idRecogida, HttpServletRequest request) {
		DTO usuarioObtenido=new DTO();
		
		Usuaria u = usuRep.findById(Integer.parseInt(idRecogida.get("id").toString()));
		 
		if(u!=null) {
			usuarioObtenido.put("nombre", u.getNombre());
			usuarioObtenido.put("fechaNac", u.getFechaNac().toString());
			usuarioObtenido.put("idRol",u.getNombre());
		}else {
			usuarioObtenido.put("resultado", "nulo");
		}
		return usuarioObtenido;
	}
	
	@DeleteMapping(path="/borrar1", consumes=MediaType.APPLICATION_JSON_VALUE)
	public DTO borrarUsuario(@RequestBody DTO idRecogida, HttpServletRequest request) {
		DTO resultado=new DTO();
		
		Usuaria u = usuRep.findById(Integer.parseInt(idRecogida.get("id").toString()));
		 
		if(u!=null) {
			usuRep.delete(u);
			resultado.put("borrado", "ok");
		}else {
			resultado.put("borrado", "fail");
		}
		return resultado;
	}
	
	@PostMapping(path="/insertar1", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void insertarRegistro(@RequestBody DatosAltaUsuario u, HttpServletRequest request) {

		usuRep.save(new Usuaria(u.id,u.FechaElim,u.FechaNac,
				DatatypeConverter.parseBase64Binary(u.img),
				u.nombre,u.username,u.pass,usuTipoRep.findById(u.rol)));
		//System.out.println(u.FechaNac.toString());
		
	}
	
	
	static class DatosAltaUsuario{

		int id;
		Date FechaElim;
		Date FechaNac;
		String img;
		String nombre;
		String username;
		String pass;
		int rol;
		public DatosAltaUsuario(int id, Date fechaElim, Date fechaNac, String img, String nombre, String username,
				String pass, int rol) {
			super();
			this.id = id;
			this.FechaElim = fechaElim;
			this.FechaNac = fechaNac;
			this.img = img;
			this.nombre = nombre;
			this.username = username;
			this.pass = pass;
			this.rol = rol;
		}
	}
	
	static class DatosAutenticaUsuario {
		String username;
		String pass;
	public DatosAutenticaUsuario(String username, String pass) {
		super();
		
		this.username = username;
		this.pass=pass;
	}
	}
	
	

	@GetMapping("/findAll")
	public void findAll() {
		usuRep.findAll().forEach(System.out::println);
	}

	@GetMapping("/obtenertodos")
	@ResponseBody
	public List<Usuaria> getUsuarios() {
		List<Usuaria> u = this.usuRep.findAll();
		return u;
	}

	@GetMapping("/obtenerByName")
	@ResponseBody
	public Usuaria getUsuariosByName() {
		Usuaria u = this.usuRep.findByNombre("kevin");
		return u;
	}

}
