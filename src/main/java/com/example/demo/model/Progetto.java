package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Progetto")
public class Progetto {
	// Costanti
	public static final String PROPERTY_idProgetto = "idProgetto";
	public static final String PROPERTY_codice = "codice";
	
	// Attributi
	@Column(name = "idProgetto")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idProgetto;

	@Column(name = "Codice")
	@NotBlank(message="Il campo non può essere vuoto")
	private String codice;
	
	// Get e set
	public int getIdProgetto() {
		return idProgetto;
	}

	public void setIdProgetto(int idProgetto) {
		this.idProgetto = idProgetto;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}
	

}