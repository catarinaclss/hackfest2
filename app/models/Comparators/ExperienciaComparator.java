package models.Comparators;

import java.util.Comparator;

import models.Usuario;

public class ExperienciaComparator implements Comparator<Usuario> {
	@Override
	public int compare(Usuario user1, Usuario user2) {

		int exp = user1.getExperiencia().getExperienciaComoAdministrador()
				- user2.getExperiencia().getExperienciaComoAdministrador();
		if (exp == 0) {
			return user1.getExperiencia().getExperienciaComoParticipante()
					- user2.getExperiencia().getExperienciaComoParticipante();
		} else {
			return exp;
		}
	}
}