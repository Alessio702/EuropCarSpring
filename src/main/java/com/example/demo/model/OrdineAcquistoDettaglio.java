package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.example.demo.validator.Min0;

@Entity
@Table(name = "OrdineAcquistoDettaglio")
public class OrdineAcquistoDettaglio {
	// Costanti
//	public static final String PROPERTY_idOrdineAcquistoDettaglio = "idOrdineAcquistoDettaglio";
//	public static final String PROPERTY_idOrdineAcquisto = "idOrdineAcquisto";
//	public static final String PROPERTY_idSpesaInvestimento = "idSpesaInvestimento";
//	public static final String PROPERTY_idProgetto = "idProgetto";
//	public static final String PROPERTY_importo = "importo";
//	public static final String PROPERTY_quantita = "quantita";
	
	// Attributi
	@Column(name = "idOrdineAcquistoDettaglio")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idOrdineAcquistoDettaglio;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "idOrdineAcquisto")
	private OrdineAcquisto oOrdineAcquisto;
	
	@ManyToOne
	@JoinColumn(name = "idSpesaInvestimento")
	@NotNull(message = "il campo non può essere nullo")
	private SpesaInvestimento oSpesaInvestimento;
	
	@ManyToOne
	@JoinColumn(name = "idProgetto")
	@NotNull(message = "il campo non può essere nullo")
	private Progetto oProgetto;
	
	@Column(name = "importo")
	@Min0
	private float importo;
	
	@Column(name = "quantita")
	@Min(message = "il campo deve essere maggiore di 0!", value = 1)
	private int quantita;
	
	
	@Transient
	private int identifier;
	
	
	// Get e set
	public int getIdOrdineAcquistoDettaglio() {
		return idOrdineAcquistoDettaglio;
	}

	public void setIdOrdineAcquistoDettaglio(int idOrdineAcquistoDettaglio) {
		this.idOrdineAcquistoDettaglio = idOrdineAcquistoDettaglio;
	}

	public OrdineAcquisto getoOrdineAcquisto() {
		return oOrdineAcquisto;
	}

	public void setoOrdineAcquisto(OrdineAcquisto oOrdineAcquisto) {
		this.oOrdineAcquisto = oOrdineAcquisto;
	}

	public SpesaInvestimento getoSpesaInvestimento() {
		return oSpesaInvestimento;
	}

	public void setoSpesaInvestimento(SpesaInvestimento oSpesaInvestimento) {
		this.oSpesaInvestimento = oSpesaInvestimento;
	}

	public Progetto getoProgetto() {
		return oProgetto;
	}

	public void setoProgetto(Progetto oProgetto) {
		this.oProgetto = oProgetto;
	}

	public float getImporto() {
		return importo;
	}

	public void setImporto(float importo) {
		this.importo = importo;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}
}