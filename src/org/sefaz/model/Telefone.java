package org.sefaz.model;

public class Telefone {
	private int id_telefone;
	private int ddd;
	private String numero;
	private String tipo;
	private int fk_id_cliente;

	public Telefone(int id_telefone, int ddd, String numero, String tipo, int fk_id_cliente) {
		super();
		this.id_telefone = id_telefone;
		this.ddd = ddd;
		this.numero = numero;
		this.tipo = tipo;
		this.fk_id_cliente = fk_id_cliente;
	}

	public Telefone() {
		// TODO Auto-generated constructor stub
	}

	public int getId_telefone() {
		return id_telefone;
	}

	public void setId_telefone(int id_telefone) {
		this.id_telefone = id_telefone;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getFk_id_cliente() {
		return fk_id_cliente;
	}

	public void setFk_id_cliente(int fk_id_cliente) {
		this.fk_id_cliente = fk_id_cliente;
	}

	@Override
	public String toString() {
		return "Telefone [id_telefone=" + id_telefone + ", ddd=" + ddd + ", numero=" + numero + ", tipo=" + tipo
				+ ", fk_id_cliente=" + fk_id_cliente + "]";
	}

}
