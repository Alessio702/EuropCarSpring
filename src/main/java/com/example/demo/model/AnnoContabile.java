package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "AnnoContabile")
public class AnnoContabile {
	
	// Attributi
	@Column(name = "idAnno")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idAnno;
	
	@Column(name = "Descrizione")
	@NotBlank(message="Il campo non può essere vuoto")
	private String descrizione;

	@Column(name = "DataInizio")
	@NotNull(message="Il campo non può essere vuoto")
	private Date dataInizio;
	
	@Column(name = "DataFine")
	@NotNull(message="Il campo non può essere vuoto")
	private Date dataFine;
	
	
	// Get e Set
	public int getIdAnno() {
		return idAnno;
	}

	public void setIdAnno(int idAnno) {
		this.idAnno = idAnno;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
}