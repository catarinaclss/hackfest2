package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOCAL")
public class Local {
	
	@Id
	@GeneratedValue
	@Column(name = "LOCAL_ID")
	private Long id;
	@Column
	private String nome;
	@Column
	private String trajeto;
	@Column
	private Integer capacidade;
	
	public Local(){}
	
	public Local(String nome, String trajeto, Integer capacidade){
		this.nome = nome;
		this.trajeto = trajeto;
		this.capacidade = capacidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTrajeto() {
		return trajeto;
	}

	public void setTrajeto(String trajeto) {
		this.trajeto = trajeto;
	}

	public Integer getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}

}
