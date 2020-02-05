package br.fbteste.domain;

import org.springframework.stereotype.Component;

@Component
public class Usuario {

	private String nome;
	private String email;
	private String id;

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(String id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
