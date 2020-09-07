package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Preventivo")
public class Preventivo {
	// Attributi
	@Column(name = "idPreventivo")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPreventivo;
	
	@Column(name = "codice")
	@NotBlank(message="Il campo non può essere vuoto")
	private String codice;
	
	@Column(name = "preventivo")
	@NotBlank(message="Il campo non può essere vuoto")
	private String preventivo;
	
	@ManyToOne
	@JoinColumn(name = "idFornitore")
	@NotNull(message = "il campo non può essere nullo")
	private Fornitore oFornitore;
	
	
	// Get e set
	public int getIdPreventivo() {
		return idPreventivo;
	}

	public void setIdPreventivo(int idPreventivo) {
		this.idPreventivo = idPreventivo;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getPreventivo() {
		return preventivo;
	}

	public void setPreventivo(String preventivo) {
		this.preventivo = preventivo;
	}

	public Fornitore getoFornitore() {
		return oFornitore;
	}

	public void setoFornitore(Fornitore oFornitore) {
		this.oFornitore = oFornitore;
	}
}