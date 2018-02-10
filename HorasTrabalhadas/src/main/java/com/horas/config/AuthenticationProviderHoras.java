package com.horas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.horas.model.Usuario;
import com.horas.service.UsuarioService;

@Component
public class AuthenticationProviderHoras implements AuthenticationProvider {

	@Autowired
	UsuarioService usuarioService;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String email = auth.getName();
		String senha = auth.getCredentials().toString();
		System.out.println(email);
		System.out.println(senha);
		if(usuarioService.logar(email, senha)){
			Usuario userBanco = usuarioService.buscar(email);
			return new UsernamePasswordAuthenticationToken(userBanco, senha, userBanco.getAuthorities());
		}
		throw new UsernameNotFoundException("Login e/ou Senha inválidos.");
	}

	@Override
	public boolean supports(Class<?> auth) {
		return true;
	}

}
