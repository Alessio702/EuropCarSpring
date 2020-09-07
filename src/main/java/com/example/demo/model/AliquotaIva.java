package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Table(name = "AliquotaIva")
public class AliquotaIva {
	// Costanti
	public static final String PROPERTY_idArea = "idaliquotaIva";
	public static final String PROPERTY_codice = "codice";
	
	// Attributi
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idaliquotaIva;

	@Column(name = "Codice")
	@NotBlank(message="Il campo non può essere vuoto")
	private String codice;

	@Column(name = "AliquotaIva")
	@NotBlank(message="Il campo non può essere vuoto")
	private int aliquotaIva;
	
	public String getCodice() {
		return codice;
	}
	public int getIdaliquotaIva() {
		return idaliquotaIva;
	}
	public void setIdaliquotaIva(int idaliquotaIva) {
		this.idaliquotaIva = idaliquotaIva;
	}
	public int getAliquotaIva() {
		return aliquotaIva;
	}
	public void setAliquotaIva(int aliquotaIva) {
		this.aliquotaIva = aliquotaIva;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
}