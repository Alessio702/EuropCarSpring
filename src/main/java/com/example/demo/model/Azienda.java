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
@Table(name = "Azienda")
public class Azienda {
	// Costanti
	public static final String PROPERTY_idAzienda = "idAzienda";
	public static final String PROPERTY_contractId = "contractId";
	public static final String PROPERTY_indirizzo = "indirizzo";
	public static final String PROPERTY_ragioneSociale = "ragioneSociale";
	public static final String PROPERTY_idGruppo = "idGruppo";
	
	// Attributi
	@Column(name = "idAzienda")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idAzienda;

	@Column(name = "contractId")
	@NotBlank(message="Il campo non può essere vuoto")
	private String contractId;

	@Column(name = "indirizzo")
	@NotBlank(message="Il campo non può essere vuoto")
	private String indirizzo;
	
	@Column(name = "ragioneSociale")
	@NotBlank(message="Il campo non può essere vuoto")
	private String ragioneSociale;
		
	@ManyToOne
	@JoinColumn(name = "idGruppo")
	@NotNull(message = "il campo non può essere nullo")
	private Gruppo oGruppo;
	
	// Get e set
	public int getIdAzienda() {
		return idAzienda;
	}

	public void setIdAzienda(int idAzienda) {
		this.idAzienda = idAzienda;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public Gruppo getoGruppo() {
		return oGruppo;
	}

	public void setoGruppo(Gruppo oGruppo) {
		this.oGruppo = oGruppo;
	}
	
	
}