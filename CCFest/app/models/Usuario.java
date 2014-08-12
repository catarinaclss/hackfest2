package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import models.exceptions.PessoaInvalidaException;

@Entity(name="Usuario")
public abstract class Usuario {
	
	private final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String email;
	private String senha;
	private String nome;
	
	public Usuario() {}
	
	public Usuario(String email, String senha, String nome) {
		this.email = email;
		this.nome = nome;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws PessoaInvalidaException {
		if (email == null)
			throw new PessoaInvalidaException("Parametro nulo");
		if (!email.matches(EMAIL_PATTERN))
			throw new PessoaInvalidaException("Email inválido");
		if (email.length() > 70)
			throw new PessoaInvalidaException("Email longo");
		this.email = email;
	}

	public String getPass() {
		return senha;
	}

	public void setPass(String pass) {
		this.senha = pass;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws PessoaInvalidaException {
		this.nome = nome;
	}
	
	public abstract 
}
