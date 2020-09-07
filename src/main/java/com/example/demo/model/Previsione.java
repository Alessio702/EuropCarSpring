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
@Table(name = "Previsione")
public class Previsione {
	// Attributi
	@Column(name = "idPrevisione")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPrevisione;
	
	@Column(name = "annoDiRiferimento")
	@NotBlank(message="Il campo non può essere vuoto")
	private String annoDiRiferimento;
	
	@Column(name = "confidenza")
//	@NotBlank(message="Il campo non può essere vuoto")
	private String confidenza;
	
	@Column(name = "dataRegistrazione")
//	@NotBlank(message = "il campo non può essere vuoto")
	private String dataRegistrazione;
	
	@Column(name = "dataVisita")
//	@NotBlank(message="Il campo non può essere nullo")
	private String dataVisita;
	
	@Column(name = "potenzialeAzienda")
//	@NotBlank(message="Il campo non può essere vuoto")
	private String potenzialeAzienda;
	
	@Column(name = "potenzialeEuropCar")
//	@NotBlank(message="Il campo non può essere vuoto")
	private String potenzialeEuropCar;
	
	@Column(name = "shareAnte")
//	@NotBlank(message="Il campo non può essere vuoto")
	private String shareAnte;
	
	@Column(name = "sharePost")
//	@NotBlank(message="Il campo non può essere vuoto")
	private String sharePost;
	
	@Column(name = "shareAvis")
//	@NotBlank(message="Il campo non può essere vuoto")
	private String shareAvis;
	
	@Column(name = "shareHertz")
//	@NotBlank(message="Il campo non può essere vuoto")
	private String shareHertz;
	
	@Column(name = "shareMaggiore")
//	@NotBlank(message="Il campo non può essere vuoto")
	private String shareMaggiore;
	
	@Column(name = "shareSixt")
//	@NotBlank(message="Il campo non può essere vuoto")
	private String shareSixt;
	
	@ManyToOne
	@JoinColumn(name = "idarea")
	@NotNull(message = "il campo non può essere nullo")
	private Area oAreaGeo;
	
	@ManyToOne
	@JoinColumn(name = "idAzienda")
	@NotNull(message = "il campo non può essere nullo")
	private Azienda oAzienda;
	
	@ManyToOne
	@JoinColumn(name = "idSottoCategoria")
	@NotNull(message = "il campo non può essere nullo")
	private SottoCategoria oSottoCategoria;
	
	@ManyToOne
	@JoinColumn(name = "idVenditore")
	@NotNull(message = "il campo non può essere nullo")
	private Venditore oVenditore;
	
	
	
	// Get e set
	public int getIdPrevisione() {
		return idPrevisione;
	}

	public void setIdPrevisione(int idPrevisione) {
		this.idPrevisione = idPrevisione;
	}

	public String getAnnoDiRiferimento() {
		return annoDiRiferimento;
	}

	public void setAnnoDiRiferimento(String annoDiRiferimento) {
		this.annoDiRiferimento = annoDiRiferimento;
	}

	public String getConfidenza() {
		return confidenza;
	}

	public void setConfidenza(String confidenza) {
		this.confidenza = confidenza;
	}

	public String getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(String dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public String getDataVisita() {
		return dataVisita;
	}

	public void setDataVisita(String dataVisita) {
		this.dataVisita = dataVisita;
	}

	public String getPotenzialeAzienda() {
		return potenzialeAzienda;
	}

	public void setPotenzialeAzienda(String potenzialeAzienda) {
		this.potenzialeAzienda = potenzialeAzienda;
	}

	public String getPotenzialeEuropCar() {
		return potenzialeEuropCar;
	}

	public void setPotenzialeEuropCar(String potenzialeEuropCar) {
		this.potenzialeEuropCar = potenzialeEuropCar;
	}

	public String getShareAnte() {
		return shareAnte;
	}

	public void setShareAnte(String shareAnte) {
		this.shareAnte = shareAnte;
	}

	public String getSharePost() {
		return sharePost;
	}

	public void setSharePost(String sharePost) {
		this.sharePost = sharePost;
	}

	public String getShareAvis() {
		return shareAvis;
	}

	public void setShareAvis(String shareAvis) {
		this.shareAvis = shareAvis;
	}

	public String getShareHertz() {
		return shareHertz;
	}

	public void setShareHertz(String shareHertz) {
		this.shareHertz = shareHertz;
	}

	public String getShareMaggiore() {
		return shareMaggiore;
	}

	public void setShareMaggiore(String shareMaggiore) {
		this.shareMaggiore = shareMaggiore;
	}

	public String getShareSixt() {
		return shareSixt;
	}

	public void setShareSixt(String shareSixt) {
		this.shareSixt = shareSixt;
	}

	public Area getoAreaGeo() {
		return oAreaGeo;
	}

	public void setoAreaGeo(Area oAreaGeo) {
		this.oAreaGeo = oAreaGeo;
	}

	public Azienda getoAzienda() {
		return oAzienda;
	}

	public void setoAzienda(Azienda oAzienda) {
		this.oAzienda = oAzienda;
	}

	public SottoCategoria getoSottoCategoria() {
		return oSottoCategoria;
	}

	public void setoSottoCategoria(SottoCategoria oSottoCategoria) {
		this.oSottoCategoria = oSottoCategoria;
	}

	public Venditore getoVenditore() {
		return oVenditore;
	}

	public void setoVenditore(Venditore oVenditore) {
		this.oVenditore = oVenditore;
	}
}