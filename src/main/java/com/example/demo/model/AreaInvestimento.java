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
@Table(name = "AreaInvestimento")
public class AreaInvestimento {
	// Costanti
	public static final String PROPERTY_idAreaInvestimento = "idAreaInvestimento";
	public static final String PROPERTY_codice = "codice";
	public static final String PROPERTY_areaInvestimento = "areaInvestimento";
	public static final String PROPERTY_oAnnoContabile = "oAnnoContabile";

	// Attributi
	@Column(name = "idAreaInvestimento")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idAreaInvestimento;
	
	@Column(name = "Codice")
	@NotBlank(message="Il campo non può essere vuoto")
	private String codice;

	@Column(name = "AreaInvestimento")
	@NotBlank(message="Il campo non può essere vuoto")
	private String areaInvestimento;
	
	@ManyToOne
	@JoinColumn(name = "idAnno")
	@NotNull(message = "il campo non può essere nullo")
	private AnnoContabile oAnnoContabile;
	
	// Get e Set
	public int getIdAreaInvestimento() {
		return idAreaInvestimento;
	}

	public void setIdAreaInvestimento(int idAreaInvestimento) {
		this.idAreaInvestimento = idAreaInvestimento;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getAreaInvestimento() {
		return areaInvestimento;
	}

	public void setAreaInvestimento(String areaInvestimento) {
		this.areaInvestimento = areaInvestimento;
	}

	public AnnoContabile getoAnnoContabile() {
		return oAnnoContabile;
	}

	public void setoAnnoContabile(AnnoContabile oAnnoContabile) {
		this.oAnnoContabile = oAnnoContabile;
	}
	
}
