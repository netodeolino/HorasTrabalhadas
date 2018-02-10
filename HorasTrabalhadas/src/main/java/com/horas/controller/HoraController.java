package com.horas.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.horas.model.Hora;
import com.horas.model.Usuario;
import com.horas.service.HoraService;
import com.horas.service.UsuarioService;

@RestController
@RequestMapping(value="/hora")
public class HoraController {

	@Autowired
	HoraService horaService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping(path="/cadastrar")
	public String salvar(@RequestBody Hora hora) {
		Date today = new Date();
		Usuario usuario = usuarioService.buscar(hora.getUsuario().getId());
		
		hora.setUsuario(usuario);
		hora.setData(today);

		horaService.salvar(hora);
		return "sucesso";
	}
	
	@Cacheable("horasCache")
	@GetMapping(path="/listar")
	public List<Hora> listar() {
		return horaService.listar();
	}
	
	@Cacheable("horaCache")
	@GetMapping(path="/buscar")
	public Hora buscar(Long id) {
		return horaService.buscar(id);
	}
	
	@CachePut("horaCache")
	@PutMapping(path="/atualizar")
	public String atualizar(@RequestBody Hora usuario) {
		horaService.salvar(usuario);
		return "sucesso";
	}
	
	@DeleteMapping(path="/remover")
	public String remover(Long id) {
		horaService.excluir(id);
		return "sucesso";
	}
	
	@Cacheable("horasCache")
	@GetMapping(path="/listar/periodo")
	public List<Hora> listarPorPeriodo(
			@RequestParam(value = "date1") String date1,
			@RequestParam(value = "date2") String date2,
			@RequestParam(value = "id") Long id)
		throws ParseException {
		
		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date dateObject1 = sdf.parse(date1);
		Date dateObject2 = sdf.parse(date2);
		
		return horaService.listarPorPeriodo(dateObject1, dateObject2, 2L);
	}
}
