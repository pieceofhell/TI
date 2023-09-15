package model;

import java.io.Serializable;

public class Carro implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String MODELO_PADRAO = "Novo Carro";
	public static final int MAX_ESTOQUE = 1000;
	private int id;
	private String modelo;
	private float preco;
	private int cavalos;
	
	public Carro() {
		id = -1;
		modelo = MODELO_PADRAO;
		preco = 0.01F;
		cavalos = 0;
	}

	public Carro(int id, String modelo, float preco, int cavalos) {
		setId(id);
		setModelo(modelo);
		setPreco(preco);
		setCavalos(cavalos);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		if (modelo.length() >= 3)
			this.modelo = modelo;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		if (preco > 0)
			this.preco = preco;
	}

	public int getCavalos() {
		return cavalos;
	}
	
	public void setCavalos(int cavalos) {
		if (cavalos >= 0 && cavalos <= MAX_ESTOQUE)
			this.cavalos = cavalos;
	}

	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Carro: " + modelo + "   Preço: R$" + preco + "   Cavalos: " + cavalos;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Carro) obj).getId());
	}	
}