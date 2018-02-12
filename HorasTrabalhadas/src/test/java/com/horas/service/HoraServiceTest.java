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

import com.horas.model.Hora;
import com.horas.repository.HoraRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class HoraServiceTest {

	@MockBean
	private HoraRepository horaRepository;
	
	@Autowired
	private HoraService horaService;
	
	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.horaRepository.findOne(Mockito.anyLong())).willReturn(new Hora());
		BDDMockito.given(this.horaRepository.save(Mockito.any(Hora.class))).willReturn(new Hora());
	}
	
	@Test
	public void testBuscarHoraPorId() {
		Hora hora = this.horaService.salvar(new Hora());
		Hora horaSalva = this.horaService.buscar(hora.getId());

		assertTrue(hora.getId() == horaSalva.getId());
	}
	
	@Test
	public void testSalvarHora() {
		Hora hora = this.horaService.salvar(new Hora());

		assertNotNull(hora);
	}
}
