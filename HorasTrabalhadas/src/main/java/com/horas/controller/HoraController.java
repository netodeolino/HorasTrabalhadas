package com.horas.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public String salvar(@RequestBody Hora hora){
		Date today = new Date();
		Usuario usuario = usuarioService.buscar(hora.getUsuario().getId());
		
		hora.setUsuario(usuario);
		hora.setData(today);
		
		horaService.salvar(hora);
		return "sucesso";
	}
	
	@GetMapping(path="/listar")
	public List<Hora> listar(){
		return horaService.listar();
	}
	
	@GetMapping(path="/buscar")
	public Hora buscar(Long id) {
		return horaService.buscar(id);
	}
	
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
}
