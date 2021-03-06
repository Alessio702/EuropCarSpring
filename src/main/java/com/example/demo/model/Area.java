package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Area")
public class Area {
	// Costanti
	public static final String PROPERTY_idArea = "idarea";
	public static final String PROPERTY_Venditore = "ovenditore";
	public static final String PROPERTY_codice = "codice";
	
	// Attributi
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idarea;

	//@ManyToOne
	// @JoinColumn(name = "idvenditore")
	// @NotNull(message="il campo non può essere nullo")
	// private Venditore ovenditore;

	@Column(name = "Codice")
	@NotBlank(message="Il campo non può essere vuoto")
	private String codice;

	@Column(name = "Area")
	@NotBlank(message="Il campo non può essere vuoto")
	private String area;

	// Get e Set
	// public Venditore getOvenditore() {
	//  return ovenditore;
	// }
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getIdarea() {
		return idarea;
	}
	public void setIdarea(int idarea) {
		this.idarea = idarea;
	}
	// public void setOvenditore(Venditore ovenditore) {
	//  this.ovenditore = ovenditore;
	// }
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}

}