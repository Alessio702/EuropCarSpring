package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Gruppo")
public class Gruppo {
	// Costanti
	public static final String PROPERTY_idGruppo = "idGruppo";
	public static final String PROPERTY_nomeGruppo = "nomeGruppo";
	public static final String PROPERTY_codice = "codice";
	
	// Attributi
	@Column(name = "idGruppo")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idGruppo;

	@Column(name = "nomeGruppo")
	@NotBlank(message="Il campo non può essere vuoto")
	private String nomeGruppo;

	@Column(name = "Codice")
	@NotBlank(message="Il campo non può essere vuoto")
	private String codice;
	
	
	// Get e set
	public int getIdGruppo() {
		return idGruppo;
	}

	public void setIdGruppo(int idGruppo) {
		this.idGruppo = idGruppo;
	}

	public String getNomeGruppo() {
		return nomeGruppo;
	}

	public void setNomeGruppo(String nomeGruppo) {
		this.nomeGruppo = nomeGruppo;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}
}