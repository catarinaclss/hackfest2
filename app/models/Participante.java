package models;


import models.exceptions.PessoaInvalidaException;

public class Participante extends Usuario {

	private String nome;

	private String email;
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
