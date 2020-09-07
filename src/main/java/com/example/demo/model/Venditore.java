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
@Table(name = "Venditore")
public class Venditore {
	// Attributi
	@Column(name = "idVenditore")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idVenditore;
	
	@Column(name = "cognome")
	@NotBlank(message="Il campo non può essere vuoto")
	private String cognome;
	
	@Column(name = "nome")
	@NotBlank(message="Il campo non può essere vuoto")
	private String nome;
	
	@Column(name = "indirizzo")
	@NotBlank(message="Il campo non può essere vuoto")
	private String indirizzo;
	
	@Column(name = "numeroTelefono")
	@NotNull(message="Il campo non può essere vuoto")
	private String numeroTelefono;

	@ManyToOne
	@JoinColumn(name = "idTipoVenditore")
	@NotNull(message = "il campo non può essere nullo")
	private TipoVenditore oTipoVenditore;
	
	
	
	// Get e set
	public int getIdVenditore() {
		return idVenditore;
	}

	public void setIdVenditore(int idVenditore) {
		this.idVenditore = idVenditore;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public TipoVenditore getoTipoVenditore() {
		return oTipoVenditore;
	}

	public void setoTipoVenditore(TipoVenditore oTipoVenditore) {
		this.oTipoVenditore = oTipoVenditore;
	}
	
	// Costruttore (solo per prova connessione con db postgre)
	public Venditore(int id, String nome, String cognome, String indirizzo, String numeroTel, TipoVenditore oTipo) {
		this.idVenditore = id;
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.numeroTelefono = numeroTel;
		this.oTipoVenditore = oTipo;
	}
	
	public Venditore() {
		
	}
}