package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import models.exceptions.PessoaInvalidaException;

import org.hibernate.validator.constraints.Email;

import play.data.validation.Constraints.MaxLength;

@Entity
public class Participante extends Usuario {

	

	@Id
	@GeneratedValue
	private long id;

	@NotNull
	@MaxLength(value = 70)
	private String nome;

	@Email
	@NotNull
	@MaxLength(value = 70)
	private String email;

	@ManyToOne
	private Evento evento;

	public Participante() {
	}

	public Participante(String nome, String email, Evento evento)
			throws PessoaInvalidaException {
		setNome(nome);
		setEmail(email);
		setEvento(evento);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws PessoaInvalidaException {
		if (nome == null)
			throw new PessoaInvalidaException("Parametro nulo");
		if (nome.length() > 70)
			throw new PessoaInvalidaException("Nome longo");
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) throws PessoaInvalidaException {
		if (evento == null)
			throw new PessoaInvalidaException("Parametro nulo");
		this.evento = evento;
	}
}
