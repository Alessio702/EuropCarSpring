package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "TipoVenditore")
public class TipoVenditore {
	// Attributi
	@Column(name = "idTipoVenditore")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idTipoVenditore;

	@Column(name = "tipoVenditore")
	@NotBlank(message="Il campo non può essere vuoto")
	private String tipoVenditore;

	
	
	// Get e set
	public int getIdTipoVenditore() {
		return idTipoVenditore;
	}

	public void setIdTipoVenditore(int idTipoVenditore) {
		this.idTipoVenditore = idTipoVenditore;
	}

	public String getTipoVenditore() {
		return tipoVenditore;
	}

	public void setTipoVenditore(String tipoVenditore) {
		this.tipoVenditore = tipoVenditore;
	}
	
	// Costruttore (temporaneo)
	public TipoVenditore(int id, String tipo) {
		this.idTipoVenditore = id;
		this.tipoVenditore = tipo;
	}
	
	public  TipoVenditore() {
		
	}
}