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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "OrdineAcquisto")
public class OrdineAcquisto {
	// Costanti
//	public static final String PROPERTY_idOrdineAcquisto = "idOrdineAcquisto";
//	public static final String PROPERTY_importo = "importo";
//	public static final String PROPERTY_ordineAcquisto = "ordineAcquisto";
//	public static final String PROPERTY_data = "data";
//	public static final String PROPERTY_idFornitore = "idFornitore";
	
	// Attributi
	@Column(name = "idOrdineAcquisto")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "Chiave univoca dell'ordine")
	private int idOrdineAcquisto;

	@Column(name = "ordineAcquisto")
	@NotBlank(message="Il campo non può essere vuoto")
	private String ordineAcquisto;
	
	@Column(name = "importo")
	@NotNull(message="Il campo non può essere vuoto")
	private float importo;
	
	@Column(name = "data")
	@NotBlank(message="Il campo non può essere vuoto")
	private String data;
	
	@ManyToOne
	@JoinColumn(name = "idFornitore")
	@NotNull(message = "il campo non può essere nullo")
	private Fornitore oFornitore;
	
	@OneToMany(mappedBy = "oOrdineAcquisto", cascade = CascadeType.ALL, fetch = FetchType.EAGER/*, orphanRemoval = true*/)
    @Fetch(FetchMode.SUBSELECT)
    private List<OrdineAcquistoDettaglio> dettagli = new ArrayList<OrdineAcquistoDettaglio>();
	
	
	// Get e set
	public int getIdOrdineAcquisto() {
		return idOrdineAcquisto;
	}

	public void setIdOrdineAcquisto(int idOrdineAcquisto) {
		this.idOrdineAcquisto = idOrdineAcquisto;
	}

	public String getOrdineAcquisto() {
		return ordineAcquisto;
	}

	public void setOrdineAcquisto(String ordineAcquisto) {
		this.ordineAcquisto = ordineAcquisto;
	}

	public float getImporto() {
		return importo;
	}

	public void setImporto(float importo) {
		this.importo = importo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Fornitore getoFornitore() {
		return oFornitore;
	}

	public void setoFornitore(Fornitore oFornitore) {
		this.oFornitore = oFornitore;
	}

	public List<OrdineAcquistoDettaglio> getDettagli() {
		return dettagli;
	}

	public void setDettagli(List<OrdineAcquistoDettaglio> dettagli) {
		this.dettagli = dettagli;
	}
	
}