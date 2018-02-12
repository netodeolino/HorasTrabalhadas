package com.horas.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.horas.model.Usuario;
import com.horas.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioServiceTest {

	@MockBean
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.usuarioRepository.findOne(Mockito.anyLong())).willReturn(new Usuario());
		BDDMockito.given(this.usuarioRepository.save(Mockito.any(Usuario.class))).willReturn(new Usuario());
	}
	
	@Test
	public void testBuscarUsuarioPorId() {
		Usuario usuario = this.usuarioService.salvar(new Usuario());
		Usuario usuarioSalva = this.usuarioService.buscar(usuario.getId());

		assertTrue(usuario.getId() == usuarioSalva.getId());
	}
	
	@Test
	public void testSalvarUsuario() {
		Usuario usuario = this.usuarioService.salvar(new Usuario());

		assertNotNull(usuario);
	}
}
