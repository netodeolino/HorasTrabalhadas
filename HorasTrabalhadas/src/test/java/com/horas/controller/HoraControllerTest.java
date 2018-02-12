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
public class HoraControllerTest {

	@Autowired
	private MockMvc mvc;
	
	private static final String ID_STR = "1";
	private static final Long ID_LONG = 1L;
	
	@Test
	@WithMockUser
	public void testBuscarHora() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/hora/buscar")
			.param("id", ID_STR)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().is(200));
	}
	
	@Test
	@WithMockUser
	public void testRemoverHoraAcessoNegado() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/hora/remover")
			.param("id", ID_STR)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isForbidden());
	}
}
