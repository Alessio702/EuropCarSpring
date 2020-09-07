package com.example.demo.model;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Utente")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "active")
	private int active;
	
	@Column(name = "roles")
	private String roles;
	
	@Column(name = "permissions")
	private String permissions;
	
	@ManyToOne
	@JoinColumn(name = "idVenditore")
	@NotNull(message = "il campo non può essere nullo")
	private Venditore oVenditore = null;
	
	

	// Get e set
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	
	public Venditore getoVenditore() {
		return oVenditore;
	}

	public void setoVenditore(Venditore oVenditore) {
		this.oVenditore = oVenditore;
	}
	
	
	

	public List<String> getRolesList() {
		if (this.roles.length() > 0) {
			return Arrays.asList(this.roles.split(regex(",").toString()));
		}
		
		return new ArrayList<>();
	}
	
	public List<String> getPermissionList() {
		if (this.permissions.length() > 0) {
			return Arrays.asList(this.permissions.split(regex(",").toString()));
		}
		
		return new ArrayList<>();
	}
	
	
	
	// Costruttori
	public User() {
			
	}

	public User(String username, String password, String roles, String permissions, Venditore oVenditore) {
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.permissions = permissions;
		this.active = 1;
		this.oVenditore = oVenditore;
	}
	
	
	
	
}
