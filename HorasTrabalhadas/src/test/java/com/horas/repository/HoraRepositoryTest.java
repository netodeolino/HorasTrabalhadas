package com.horas.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.horas.model.Hora;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class HoraRepositoryTest {

	@Autowired
	private HoraRepository horaRepository;

	@Before
	public void setUp() throws Exception {
		Hora hora = new Hora();
		hora.setData(new Date());
		hora.setHorasTrabalhadas(8.0);
		hora.setUsuario(null);
		
		this.horaRepository.save(hora);
	}
	
	@Test
	public void testUsuarioIsNull() {
		Date today = new Date();
		Hora hora = new Hora();
		hora.setData(today);
		hora.setHorasTrabalhadas(8.0);
		
		Hora horaSalva = this.horaRepository.save(hora);
		
		assertNull(horaSalva.getUsuario());
	}
	
	@Test
	public void testDateNotNull() {
		Date today = new Date();
		Hora hora = new Hora();
		hora.setData(today);
		hora.setHorasTrabalhadas(8.0);
		
		Hora horaSalva = this.horaRepository.save(hora);
		
		assertNotNull(horaSalva.getData());
	}
	
	@After
    public final void tearDown() { 
		this.horaRepository.deleteAll();
	}
}
