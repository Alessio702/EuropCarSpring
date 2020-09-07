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
@Table(name = "SpesaInvestimento")
public class SpesaInvestimento {
	// Costanti
	public static final String PROPERTY_idSpesaInvestimento = "idSpesaInvestimento";
	public static final String PROPERTY_spesaInvestimento = "spesaInvestimento";
	public static final String PROPERTY_oSottoCategoria = "oSottoCategoria";
	
	// Attributi
	@Column(name = "idSpesaInvestimento")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idSpesaInvestimento;

	@Column(name = "spesaInvestimento")
	@NotBlank(message="Il campo non può essere vuoto")
	private String spesaInvestimento;
	
	@ManyToOne
	@JoinColumn(name = "idSottoCategoria")
	@NotNull(message="Il campo non può essere vuoto")
	private SottoCategoria oSottoCategoria;
	
	
	// Get e set
	public int getIdSpesaInvestimento() {
		return idSpesaInvestimento;
	}

	public void setIdSpesaInvestimento(int idSpesaInvestimento) {
		this.idSpesaInvestimento = idSpesaInvestimento;
	}

	public String getSpesaInvestimento() {
		return spesaInvestimento;
	}

	public void setSpesaInvestimento(String spesaInvestimento) {
		this.spesaInvestimento = spesaInvestimento;
	}

	public SottoCategoria getoSottoCategoria() {
		return oSottoCategoria;
	}

	public void setoSottoCategoria(SottoCategoria oSottoCategoria) {
		this.oSottoCategoria = oSottoCategoria;
	}
	

}