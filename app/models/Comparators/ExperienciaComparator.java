package models.Comparators;

import java.util.Comparator;

import models.Usuario;

public class ExperienciaComparator implements Comparator<Usuario> {
	
	@Override
	public int compare(Usuario user1, Usuario user2) {
		if (user1.getExperiencia() < user2.getExperiencia()) {
			return 1;
		} else if (user1.getExperiencia() > user2.getExperiencia()) {
			return -1;
		}
		return 0;
	}
}