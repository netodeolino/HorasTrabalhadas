package com.horas.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.horas.model.Hora;
import com.horas.model.Papel;
import com.horas.model.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private HoraRepository horaRepository;
	
	private static final String EMAIL = "user@email.com";
	
	@Before
	public void setUp() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setEmail(EMAIL);
		usuario.setSenha("user");
		usuario.setPapel(Papel.USER);
		
		Usuario usuarioSalvo = this.usuarioRepository.save(usuario);
		
		Hora hora = new Hora();
		hora.setData(new Date());
		hora.setHorasTrabalhadas(7.0);
		
		Hora horaSalva = this.horaRepository.save(hora);
		
		List<Hora> horas = new ArrayList<Hora>();
		horas.add(horaSalva);
		
		usuarioSalvo.setHoras(horas);
		
		usuarioSalvo = this.usuarioRepository.save(usuarioSalvo);
	}
	
	@Test
	public void testBuscarPorEmail() {
		Usuario usuario = this.usuarioRepository.findByEmail(EMAIL);
		
		assertEquals(EMAIL, usuario.getEmail());
	}
	
	@Test
	public void testUsuarioIsNull() {
		Hora hora = new Hora();
		hora.setData(new Date());
		hora.setHorasTrabalhadas(8.0);
		
		Hora horaSalva = this.horaRepository.save(hora);
		
		assertNull(horaSalva.getUsuario());
	}
	
	@After
    public final void tearDown() { 
		this.usuarioRepository.deleteAll();
	}
}
