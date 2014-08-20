package controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import models.Evento;
import models.Local;
import models.Tema;
import models.Usuario;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import models.exceptions.EventoInvalidoException;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Application extends Controller {

	private static boolean criouEventosFake = false;
	private static GenericDAO dao = new GenericDAOImpl();

	@Transactional
	public static Result index() {
		if (!criouEventosFake) {
			List<Evento> eventos = criarEventosFakes();
			criarParticipacoesFake(eventos);

			criouEventosFake = true;
		}
		return ok(index.render());
	}

	// @Transactional
	// public static Result index(){
	// //povoaBD();
	// // session().clear();
	// List<Usuario> lista = dao.findByAttributeName("Usuario", "nome",
	// session().get("user"));
	// if (lista == null || lista.isEmpty()) {
	// // para o caso de não haver um usuario logado eu mando ele entrar no
	// // sistema.
	// return Login.showLogin();
	// }
	// usuarioLogado = lista.get(0);
	// return ok(index.render(usuarioLogado));
	// }

	public static GenericDAO getDao() {
		return dao;
	}

	private static List<Evento> criarEventosFakes() {
		try {
			List<Evento> eventos = new ArrayList<>();
			Evento evento;
			Calendar calendar;

			List<Tema> temas = new ArrayList<>();
			Local local = new Local("", "", 0);
		

			temas.add(Tema.DESAFIOS);
			temas.add(Tema.PROGRAMACAO);

			calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_WEEK, 7);

			evento = new Evento(
					"Python na mente e coração",
					"Neste evento iremos debater e propor soluções para novas releases.",
					calendar.getTime(), temas, local);
			eventos.add(evento);
			criarEvento(evento);

			temas = new ArrayList<>();
			temas.add(Tema.ARDUINO);
			temas.add(Tema.ELETRONICA);

			calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_WEEK, 3);

			evento = new Evento(
					"Luta de robôs",
					"Traga seu robô feito em arduino e traga para competir com outros.",
					calendar.getTime(), temas, local);
			eventos.add(evento);
			criarEvento(evento);

			temas = new ArrayList<>();
			temas.add(Tema.DESAFIOS);
			temas.add(Tema.PROGRAMACAO);

			calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, 1);

			evento = new Evento(
					"IV Olímpiadas de programação da UFCG",
					"Traga sua equipe e venha competir nessa maratona de programação.",
					calendar.getTime(), temas, local);
			eventos.add(evento);
			criarEvento(evento);

			temas = new ArrayList<>();
			temas.add(Tema.DESAFIOS);
			temas.add(Tema.PROGRAMACAO);

			calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_WEEK, 12);

			evento = new Evento(
					"II Encontro para programadores de Python",
					"O encontro contará com a participação de um de seus fundadores, inúmeras palestras e maratonas. Não percam!!",
					calendar.getTime(), temas, local);
			eventos.add(evento);
			criarEvento(evento);

			temas = new ArrayList<>();
			temas.add(Tema.PROGRAMACAO);
			temas.add(Tema.DESAFIOS);

			calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, 2);
			calendar.add(Calendar.DAY_OF_WEEK, 3);

			evento = new Evento(
					"III Semana da Computação Verde",
					"Exiba sua proposta para uma computação mais verde e concorra a diversos prêmios",
					calendar.getTime(), temas, local);
			eventos.add(evento);
			criarEvento(evento);

			temas = new ArrayList<>();
			temas.add(Tema.PROGRAMACAO);
			temas.add(Tema.WEB);

			calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_WEEK, 17);

			evento = new Evento(
					"Web em foco",
					"Este evento contará com a participação de um dos fundadores da Web, e juntos iremos compartilhar diversas dicas e boas práticas nessa vasta área.",
					calendar.getTime(), temas, local);
			eventos.add(evento);
			criarEvento(evento);

			temas = new ArrayList<>();
			temas.add(Tema.ELETRONICA);
			temas.add(Tema.ARDUINO);

			calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_WEEK, 5);

			evento = new Evento(
					"Minicurso Arduino",
					"Evento destinado a alunos de LOAC, caso sobre vagas iremos disponibilizar em breve",
					calendar.getTime(), temas, local);
			eventos.add(evento);
			criarEvento(evento);

			temas = new ArrayList<>();
			temas.add(Tema.ELETRONICA);
			temas.add(Tema.ARDUINO);

			calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_WEEK, 21);

			evento = new Evento(
					"Curto circuito",
					"Evento sobre circuitos eletrônicos, venha dar curto em seus circuitos também!!",
					calendar.getTime(), temas, local);
			eventos.add(evento);
			criarEvento(evento);

			temas = new ArrayList<>();
			temas.add(Tema.DESAFIOS);

			calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_WEEK, 15);

			evento = new Evento(
					"VI Encontro de Docentes de CC",
					"Evento para debatermos propostas e soluções para os problemas enfrentados pelos alunos de CC.",
					calendar.getTime(), temas, local);
			eventos.add(evento);
			criarEvento(evento);

			temas = new ArrayList<>();
			temas.add(Tema.PROGRAMACAO);
			temas.add(Tema.DESAFIOS);

			calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_WEEK, 8);

			evento = new Evento(
					"Café com Java",
					"Curso destinado apenas a alunos cursando a disciplina LP2.",
					calendar.getTime(), temas, local);
			eventos.add(evento);
			criarEvento(evento);

			return eventos;
		} catch (EventoInvalidoException e) {
			return null;
		}
	}

	private static void criarParticipacoesFake(List<Evento> eventos) {
		Random rnd = new Random();

	}

	@Transactional
	private static void criarEvento(Evento evento) {
		dao.persist(evento);
		dao.merge(evento);
		dao.flush();
	}

	@Transactional
	private static void criarParticipacao(Usuario participante) {
		dao.persist(participante);
		dao.flush();
	}

}
