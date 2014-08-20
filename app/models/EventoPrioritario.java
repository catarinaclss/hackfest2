package models;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import models.Comparators.ExperienciaComparator;
import models.exceptions.EventoInvalidoException;
import models.exceptions.PessoaInvalidaException;

public class EventoPrioritario extends Evento {
	
	private int numParticipantes = this.getTotalDeParticipantes();
	
	
	public EventoPrioritario() {}

	public EventoPrioritario(String titulo, String descricao, Date data, List<Tema> temas, Local local) throws EventoInvalidoException {
		super(titulo, descricao, data, temas, local);
	}
	
	@Override
	public void participar(Usuario usuario) throws Exception{

		if (this.existemVagas()) {
			this.getParticipantes().add(usuario);
	
		} else {
			System.out.println("nao tem vagas");
			ExperienciaComparator comparadorPorExperiencia = new ExperienciaComparator();
			
			Collections.sort(this.getParticipantes(), new ExperienciaComparator());
			
			
			System.out.println("participantes" + numParticipantes);
			
			if (comparadorPorExperiencia.compare(getUsuarioComMenorExperiencia(), usuario) < 0) {
				this.getParticipantes().remove(numParticipantes - 1);
				this.getParticipantes().add(usuario);
		
				Collections.sort(this.getParticipantes(), new ExperienciaComparator());
			
			} else {
				throw new PessoaInvalidaException(
						"O usuário não possui experiência suficiente para participar desse evento");
			}
		}
	}
	
	public Usuario getUsuarioComMenorExperiencia(){
		
		return this.getParticipantes().get(numParticipantes - 1);
	}

}
