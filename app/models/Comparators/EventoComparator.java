package models.Comparators;

import java.util.Comparator;

import models.Evento;

public class EventoComparator implements Comparator<Evento> {

	@Override
	public int compare(Evento e1, Evento e2) {
		return e2.getTotalDeParticipantes().compareTo(
				e1.getTotalDeParticipantes());
	}
}