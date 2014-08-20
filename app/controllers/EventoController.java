package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Evento;
import models.Tema;
import models.Usuario;
import models.Comparators.EventoComparator;
import models.exceptions.EventoInvalidoException;
import models.exceptions.PessoaInvalidaException;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EventoController extends Controller {
	private final static Form<Evento> EVENTO_FORM = new Form<Evento>(
			Evento.class);
	// se for assim eh redundante
	private final static Form<Usuario> participanteForm = new Form<Usuario>(
			Usuario.class);

	@Transactional
	public static Result eventosPorTema(int id) throws PessoaInvalidaException,
			EventoInvalidoException {

		List<Evento> todosEventos = Application.getDao().findAllByClassName(
				"Evento");

		List<Evento> eventosRequeridos = new ArrayList<>();

		for (Evento ev : todosEventos) {
			if (ev.getTemas().contains(Tema.values()[(int) id])) {
				eventosRequeridos.add(ev);
			}
		}

		Collections.sort(eventosRequeridos, new EventoComparator());

		ObjectMapper mapper = new ObjectMapper();
		String json = "";

		try {
			json = mapper.writeValueAsString(eventosRequeridos);
		} catch (Exception _) {
			return badRequest();
		}

		return ok(json);
	}

	@Transactional
	public static Result novo() throws PessoaInvalidaException,
			EventoInvalidoException {
		Form<Evento> eventoFormRequest = EVENTO_FORM.bindFromRequest();

		if (EVENTO_FORM.hasErrors()) {
			return badRequest();
		} else {
			Evento novoEvento = eventoFormRequest.get();
			Application.getDao().persist(novoEvento);
			Application.getDao().merge(novoEvento);
			Application.getDao().flush();
			return redirect(controllers.routes.Application.index());
		}
	}

	@Transactional
	public static Result participar(long id) throws PessoaInvalidaException,
			EventoInvalidoException {
		Form<Usuario> participanteFormRequest = participanteForm
				.bindFromRequest();

		if (participanteForm.hasErrors()) {
			return badRequest();
		} else {
			Evento evento = Application.getDao().findByEntityId(Evento.class,
					id);
			Usuario novoParticipante = participanteFormRequest.get();
			// novoParticipante.setEvento(evento);

			Application.getDao().persist(novoParticipante);
			Application.getDao().merge(novoParticipante);
			Application.getDao().flush();
			return redirect(controllers.routes.Application.index());
		}
	}

}
