package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Fornitore")
public class Fornitore {
	// Costanti
//	public static final String PROPERTY_idFornitore = "idFornitore";
//	public static final String PROPERTY_ragioneSociale = "ragioneSociale";
//	public static final String PROPERTY_indirizzo = "indirizzo";
//	public static final String PROPERTY_citta = "citta";
//	public static final String PROPERTY_cap = "cap";
//	public static final String PROPERTY_provincia = "provincia";
//	public static final String PROPERTY_partitaIva = "partitaIva";
	
	
	// Attributi
	@Column(name = "idFornitore")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idFornitore;

	@Column(name = "ragioneSociale")
	@NotBlank(message="Il campo non può essere vuoto")
	private String ragioneSociale;
	
	@Column(name = "indirizzo")
	@NotBlank(message="Il campo non può essere vuoto")
	private String indirizzo;
	
	@Column(name = "citta")
	@NotBlank(message="Il campo non può essere vuoto")
	private String citta;
	
	@Column(name = "cap")
	@NotBlank(message="Il campo non può essere vuoto")
	private String cap;
	
	@Column(name = "provincia")
	@NotBlank(message="Il campo non può essere vuoto")
	private String provincia;
	
	@Column(name = "partitaIva")
	@NotBlank(message="Il campo non può essere vuoto")
	private String partitaIva;
	
	
	// Get e set
	public int getIdFornitore() {
		return idFornitore;
	}

	public void setIdFornitore(int idFornitore) {
		this.idFornitore = idFornitore;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}
	

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
	
}