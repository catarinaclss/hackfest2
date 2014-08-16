package models;

import java.util.Collections;

import models.Comparators.ExperienciaComparator;
import models.exceptions.PessoaInvalidaException;

public class EventoPrioritario extends Evento {
	
	@Override
	public void participar(Usuario usuario) throws Exception{
		if (this.existemVagas()) {
			this.getParticipantes().add(usuario);
		} else {
			Collections.sort(this.getParticipantes(), new ExperienciaComparator());
			int numParticipantes = this.getTotalDeParticipantes();
			if (this.getParticipantes().get(numParticipantes - 1).getExperiencia() < usuario.getExperiencia()) {
				this.getParticipantes().remove(numParticipantes - 1);
				this.participar(usuario);
		
				Collections.sort(this.getParticipantes(), new ExperienciaComparator());
			} else {
				throw new PessoaInvalidaException(
						"O usuário não possui experiência suficiente para participar desse evento");
			}
		}
	}

}
