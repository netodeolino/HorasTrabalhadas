package com.horas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horas.model.Usuario;
import com.horas.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public Usuario buscar(Long id) {
		return usuarioRepository.findOne(id);
	}
	
	public void excluir(Long id) {
		usuarioRepository.delete(id);
	}
	
	public List<Usuario> listar(){
		return usuarioRepository.findAll();
	}
}
