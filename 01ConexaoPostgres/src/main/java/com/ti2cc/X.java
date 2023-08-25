package com.ti2cc;

public class X {
	private int idNum;
	private String nome;
	private int idade;
	private String email;
	
	public X() {
		this.idNum = 0;
		this.nome = "";
		this.idade = 0;
		this.email = "";
	}
	
	public X(int idNum, String nome, int idade, String email) {
		this.idNum = idNum;
		this.nome = nome;
		this.idade = idade;
		this.email = email;
	}

	public int getIdNum() {
		return idNum;
	}

	public void setIdNum(int idNum) {
		this.idNum = idNum;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Usuario [idNum=" + idNum + ", nome=" + nome + ", idade=" + idade + ", email=" + email + "]";
	}	
}