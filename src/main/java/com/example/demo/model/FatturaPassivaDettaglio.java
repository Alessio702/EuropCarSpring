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

import com.example.demo.validator.Min0;

@Entity
@Table(name = "FatturaPassivaDettaglio")
public class FatturaPassivaDettaglio {
	// Attributi
	@Column(name = "idDettaglioFattura")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idDettaglioFattura;
	
//	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@ManyToOne
	@JoinColumn(name = "idFatturaPassiva")
	private FatturaPassiva oFatturaPassiva;
	
	@Column(name = "dettaglioFattura")
	@NotBlank(message="Il campo non può essere vuoto")
	private String dettaglioFattura;
	
	@Column(name = "importo")
	@Min0
	private float importo;
	
	@ManyToOne
	@JoinColumn(name = "idaliquotaIva")
	@NotNull(message = "il campo non può essere nullo")
	private AliquotaIva oAliquotaIva;
	
	@ManyToOne
	@JoinColumn(name = "idPreventivo")
	@NotNull(message = "il campo non può essere nullo")
	private Preventivo oPreventivo;
	
	@ManyToOne
	@JoinColumn(name = "idSpesaInvestimento")
	@NotNull(message = "il campo non può essere nullo")
	private SpesaInvestimento oSpesaInvestimento;
	
	@Transient
	private int identifier;
	
	
	// Get e set
	public int getIdDettaglioFattura() {
		return idDettaglioFattura;
	}

	public void setIdDettaglioFattura(int idDettaglioFattura) {
		this.idDettaglioFattura = idDettaglioFattura;
	}
	
	public FatturaPassiva getoFatturaPassiva() {
		return oFatturaPassiva;
	}

	public void setoFatturaPassiva(FatturaPassiva oFatturaPassiva) {
		this.oFatturaPassiva = oFatturaPassiva;
	}

	public String getDettaglioFattura() {
		return dettaglioFattura;
	}

	public void setDettaglioFattura(String dettaglioFattura) {
		this.dettaglioFattura = dettaglioFattura;
	}

	public float getImporto() {
		return importo;
	}

	public void setImporto(float importo) {
		this.importo = importo;
	}

	public AliquotaIva getoAliquotaIva() {
		return oAliquotaIva;
	}

	public void setoAliquotaIva(AliquotaIva oAliquotaIva) {
		this.oAliquotaIva = oAliquotaIva;
	}

	public SpesaInvestimento getoSpesaInvestimento() {
		return oSpesaInvestimento;
	}

	public void setoSpesaInvestimento(SpesaInvestimento oSpesaInvestimento) {
		this.oSpesaInvestimento = oSpesaInvestimento;
	}

	public Preventivo getoPreventivo() {
		return oPreventivo;
	}

	public void setoPreventivo(Preventivo oPreventivo) {
		this.oPreventivo = oPreventivo;
	}
	
	public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}
}