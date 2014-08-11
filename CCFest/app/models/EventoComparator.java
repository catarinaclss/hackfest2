package models;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class EventoComparator implements Comparator<Evento> {
	@Override
    public int compare(Evento e1, Evento e2) {
       return e2.getTotalDeParticipantes().compareTo(e1.getTotalDeParticipantes());
    }

}