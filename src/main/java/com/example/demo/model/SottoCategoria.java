package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "SottoCategoria")
public class SottoCategoria {
	// Costanti
	public static final String PROPERTY_idSottoCategoria = "idSottoCategoria";
	public static final String PROPERTY_SottoCategoria = "sottoCategoria";
	public static final String PROPERTY_codice = "codice";
	public static final String PROPERTY_budget = "budget";
	public static final String PROPERTY_budgetSpeso = "budgetSpeso";
	public static final String PROPERTY_idArea = "idArea";
	
	// Attributi
	@Column(name = "idSottoCategoria")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idSottoCategoria;

	@Column(name = "SottoCategoria")
	@NotBlank(message="Il campo non può essere vuoto")
	private String sottoCategoria;

	@Column(name = "Codice")
	@NotBlank(message="Il campo non può essere vuoto")
	private String codice;
	
	@Column(name = "budget")
	@NotNull(message="Il campo non può essere vuoto")
	private float budget;
	
	@Column(name = "budgetSpeso")
	private float budgetSpeso;
	
	@ManyToOne
	@JoinColumn(name = "idAreaInvestimento")
	@NotNull(message = "il campo non può essere nullo")
	private AreaInvestimento oAreaInvestimento;
	
	@Transient
	private float avanzamento;
	
	
	// Get e set
	public int getIdSottoCategoria() {
		return idSottoCategoria;
	}

	public void setIdSottoCategoria(int idSottoCategoria) {
		this.idSottoCategoria = idSottoCategoria;
	}

	public String getSottoCategoria() {
		return sottoCategoria;
	}

	public void setSottoCategoria(String sottoCategoria) {
		this.sottoCategoria = sottoCategoria;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public float getBudget() {
		return budget;
	}

	public void setBudget(float budget) {
		this.budget = budget;
	}

	public float getBudgetSpeso() {
		return budgetSpeso;
	}

	public void setBudgetSpeso(float budgetSpeso) {
		this.budgetSpeso = budgetSpeso;
	}
	
	public AreaInvestimento getoAreaInvestimento() {
		return oAreaInvestimento;
	}

	public void setoAreaInvestimento(AreaInvestimento oAreaInvestimento) {
		this.oAreaInvestimento = oAreaInvestimento;
	}
	
	public float getAvanzamento() {
		return avanzamento;
	}

	public void setAvanzamento(float avanzamento) {
		this.avanzamento = avanzamento;
	}
	
}