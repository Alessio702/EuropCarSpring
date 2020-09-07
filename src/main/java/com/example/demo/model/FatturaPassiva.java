package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "FatturaPassiva")
public class FatturaPassiva {
	// Attributi
	@Column(name = "idFatturaPassiva")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idFatturaPassiva;
	
	@Column(name = "descrizione")
	@NotBlank(message="Il campo non può essere vuoto")
	private String descrizione;
	
	@Column(name = "data")
	@NotBlank(message="Il campo non può essere vuoto")
	private String data;
	
	@Column(name = "numero")
	@Min(message = "il campo deve essere maggiore di 0!", value = 1)
	private int numero;
	
	@ManyToOne
	@JoinColumn(name = "idFornitore")
	@NotNull(message = "il campo non può essere nullo")
	private Fornitore oFornitore;
	
	@OneToMany(mappedBy = "oFatturaPassiva", cascade = CascadeType.ALL, fetch = FetchType.EAGER/*, orphanRemoval = true*/)
	@Fetch(FetchMode.SUBSELECT)
	private List<FatturaPassivaDettaglio> dettagli = new ArrayList<FatturaPassivaDettaglio>();
	
	
	// Get e set
	public int getIdFatturaPassiva() {
		return idFatturaPassiva;
	}

	public void setIdFatturaPassiva(int idFatturaPassiva) {
		this.idFatturaPassiva = idFatturaPassiva;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Fornitore getoFornitore() {
		return oFornitore;
	}

	public void setoFornitore(Fornitore oFornitore) {
		this.oFornitore = oFornitore;
	}

	public List<FatturaPassivaDettaglio> getDettagli() {
		return dettagli;
	}

	public void setDettagli(List<FatturaPassivaDettaglio> dettagli) {
		this.dettagli = dettagli;
	}
}