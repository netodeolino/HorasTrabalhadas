package com.horas.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UsuarioControllerTest {

	@Autowired
	private MockMvc mvc;
	
	private static final String ID_STR = "1";
	private static final Long ID_LONG = 1L;
	
	@Test
	@WithMockUser
	public void testBuscarUsuario() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/usuario/buscar")
			.param("id", ID_STR)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().is(200));
	}
	
	@Test
	@WithMockUser
	public void testRemoverUsuarioAcessoNegado() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/usuario/remover")
			.param("id", ID_STR)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isForbidden());
	}
}
