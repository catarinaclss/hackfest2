package models;

public class Experiencia {
	
	
	
	private int experienciaComoParticipante;
	
	private int experienciaComoAdmin; 
	
	public Experiencia(){
		this.experienciaComoAdmin = 0;
		this.experienciaComoParticipante = 0;
	}
	
	public int getExperienciaComoParticipante(){
		return this.experienciaComoParticipante;
	}

	public int getExperienciaComoAdministrador(){
		return this.experienciaComoAdmin;
	}
	
	public void setExperienciaComoParticipante(){
		this.experienciaComoParticipante++;
	}
	
	public void setExperienciaComoAdministrador(){
		this.experienciaComoAdmin++;
	}
}
