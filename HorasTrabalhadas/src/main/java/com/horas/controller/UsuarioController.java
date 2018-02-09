package com.horas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.horas.model.Usuario;
import com.horas.service.UsuarioService;

@RestController
@RequestMapping(value="/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping(path="/cadastrar")
	public String salvar(@RequestBody Usuario usuario){
		usuarioService.salvar(usuario);
		return "sucesso";
	}
	
	@GetMapping(path="/listar")
	public List<Usuario> listar(){
		return usuarioService.listar();
	}
	
	@GetMapping(path="/buscar")
	public Usuario buscar(Long id) {
		return usuarioService.buscar(id);
	}
	
	@PutMapping(path="/atualizar")
	public String atualizar(@RequestBody Usuario usuario) {
		usuarioService.salvar(usuario);
		return "sucesso";
	}
	
	@DeleteMapping(path="/remover")
	public String remover(Long id) {
		usuarioService.excluir(id);
		return "sucesso";
	}
}
