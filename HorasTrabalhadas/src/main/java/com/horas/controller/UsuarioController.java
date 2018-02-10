package com.horas.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.horas.model.Papel;
import com.horas.model.Usuario;
import com.horas.response.AuthToken;
import com.horas.service.UsuarioService;
import com.horas.util.Constants;
import com.horas.util.Senha;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping(value="/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	private static final long EXPIRATION_TIME = 1000 * Constants.TOKEN_EXPIRAR_MINUTOS;
	
	@PostMapping(path="/cadastrar")
	public String salvar(@RequestBody Usuario usuario){
		usuario.setPapel(Papel.USER);
		usuario.setSenha(Senha.criptografarSenha(usuario.getSenha()));
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
	
	@PostMapping(path="/logar")
	public AuthToken logar(@RequestBody Usuario usuario){
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				usuario.getUsername(),
				usuario.getPassword(),
				Collections.emptyList()
		));
		String JWT = Jwts.builder()
						.setSubject(authentication.getName())
						.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
						.signWith(SignatureAlgorithm.HS512, Constants.CHAVE_SECRETA)
						.compact();
		return new AuthToken(Constants.TOKEN_PREFIX + " " + JWT);
	}
}
