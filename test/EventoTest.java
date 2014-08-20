import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import models.Evento;
import models.EventoPrioritario;
import models.Local;
import models.Tema;
import models.Usuario;
import models.exceptions.EventoInvalidoException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EventoTest {
	
	private List<Tema> temas;
	
	@Before
	public void setUp(){
		temas = new ArrayList<>();
	}
	
	@Test
	public void deveCriarUmEvento() {
		temas.add(Tema.ARDUINO);
		try {
			new Evento("Python na cabeça", "Vamos programar em Python!", new Date(), temas, new Local());
		} catch (EventoInvalidoException e) {
			fail();
		}
	}
	
	@Test
	public void deveDarException() {
		try {
			new Evento(null,
					"Vamos programar em Python!", new Date(), temas, new Local());
			fail();
		} catch (EventoInvalidoException e) {
			assertEquals("Parametro nulo", e.getMessage());
		}
		try {
			new Evento("Python na cabeça",
					null, new Date(), temas, new Local());
			fail();
		} catch (EventoInvalidoException e) {
			assertEquals("Parametro nulo", e.getMessage());
		}
		try {
			new Evento("Python na cabeça",
				"Vamos programar em Python!", null, temas, new Local());
			fail();
		} catch (EventoInvalidoException e) {
			assertEquals("Parametro nulo", e.getMessage());
		}
	try {
			new Evento("Python na cabeça",
					"Vamos programar em Python!", new Date(), null, new Local());
		fail();
		} catch (EventoInvalidoException e) {
			assertEquals("Parametro nulo", e.getMessage());
	}
		try {
			new Evento("Python na cabeça",
					"Vamos programar em Python!", new Date(), new ArrayList<Tema>(), new Local());
		fail();
		} catch (EventoInvalidoException e) {
			assertEquals("Nenhum tema", e.getMessage());
		}
		try {
			String descricaoLonga = "Vamos programar em Python!";
			
		for (int i = 0; i < 5; i++) {
				descricaoLonga += descricaoLonga;
			}
			
			new Evento("Python na cabeça",
				descricaoLonga, new Date(), temas, new Local());
			fail();
		} catch (EventoInvalidoException e) {
		assertEquals("Descrição longa", e.getMessage());
		}
		try {
			new Evento("Python na cabeça na mente e no coração uhuuu",
					"Vamos programar em Python!", new Date(), null, new Local());
			fail();
		} catch (EventoInvalidoException e) {
			assertEquals("Título longo", e.getMessage());
		}
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_WEEK, -1);

			new Evento("Python na cabeça",
					"Vamos programar em Python!", calendar.getTime(), temas, new Local());
			fail();
	} catch (EventoInvalidoException e) {
			assertEquals("Data inválida", e.getMessage());
		}
	}
	
	@Test
	public void devePossuirLimiteDeParticipantes() throws Exception{
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_WEEK, -1);
		temas.add(Tema.ARDUINO);
		Local local = new Local();
		local.setCapacidade(3);

		try {
			Evento evento = new Evento("", "", new Date(), temas, local);
			evento.participar(new Usuario());
			evento.participar(new Usuario());
			evento.participar(new Usuario());
			evento.participar(new Usuario());
			evento.participar(new Usuario());
		}catch(EventoInvalidoException e) {
			assertEquals("Vagas esgotadas para este evento", e.getMessage());
		}
		
		try{
			System.out.println(local.getCapacidade());
			
			Evento evento2 = new EventoPrioritario("", "", new Date(), temas, local);
			List<Usuario> users = new ArrayList<Usuario>();
			int experiencia = 0;
			
			for (int i=0; i<5; i++){
				users.add(new Usuario());
				users.get(i).getExperiencia();//.setExperienciaComoParticipante();;
				users.get(i).setNome("user"+i);
				experiencia++;
			}
			
			
			
			
			evento2.participar(users.get(0));
			
			Assert.assertTrue(evento2.getParticipantes().contains(users.get(0)));
			evento2.participar(users.get(1));
			evento2.participar(users.get(2));
			evento2.participar(users.get(3));
			evento2.participar(users.get(4));
			
			for (Usuario user: evento2.getParticipantes()){
				System.out.println(user.getNome());
			}
			
		}catch (EventoInvalidoException e){
			assertEquals("O usuário não possui experiência suficiente para participar desse evento", e.getMessage());
		}
		
//		try {
//			
//			local.setCapacidade(3);
//			Evento evento = new EventoPrioritario("", "", new Date(), temas, local);
//			
//			
//			
//			Usuario user1 = new Usuario();
//			user1.setExperiencia(5);
//			evento.participar(user1);
//			
//			Assert.assertTrue(evento.getParticipantes().contains(user1));
//			
//			Usuario user2 = new Usuario();
//			user2.setExperiencia(4); 
//			evento.participar(user2);
//			System.out.println(evento.existemVagas());
//			Assert.assertTrue(evento.getParticipantes().contains(user2));
//			int num_participantes = evento.getParticipantes().size();
//			Assert.assertTrue(evento.getParticipantes().get(num_participantes-1).equals(user2));
//			
//			Usuario user3 = new Usuario();
//			user3.setExperiencia(6);
//			evento.participar(user3);
//			
//			for (Usuario usuario: evento.getParticipantes()){
//				System.out.println(usuario.getExperiencia());
//			}
//	
//			System.out.println(evento.getParticipantes().size());
//			Assert.assertFalse(evento.getParticipantes().contains(user2));
//			Assert.assertTrue(evento.getParticipantes().contains(user3));
//			Assert.assertTrue(evento.getParticipantes().get(evento.getParticipantes().size()-1).equals(user3));
//			System.out.println(evento.getParticipantes().size());
//			Usuario user4 = new Usuario();
//			user4.setExperiencia(7);
//			
//			evento.participar(user4);
//		
//		}catch(EventoInvalidoException e) {
//			assertEquals("O usuário não possui experiência suficiente para participar desse evento", e.getMessage());
//		}
//		
	
	}
}
