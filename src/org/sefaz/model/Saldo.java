package org.sefaz.model;

public class Saldo {
	private int id_saldo;
	private float saldo;
	private int fk_id_cliente;
	
	public Saldo(int id_saldo, float saldo, int fk_id_cliente) {
		super();
		this.id_saldo = id_saldo;
		this.saldo = saldo;
		this.fk_id_cliente = fk_id_cliente;
	}

	public Saldo() {
		// TODO Auto-generated constructor stub
	}

	public int getId_saldo() {
		return id_saldo;
	}

	public void setId_saldo(int id_saldo) {
		this.id_saldo = id_saldo;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public int getFk_id_cliente() {
		return fk_id_cliente;
	}

	public void setFk_id_cliente(int fk_id_cliente) {
		this.fk_id_cliente = fk_id_cliente;
	}
	
	@Override
	public String toString() {
		return "Saldo [id_saldo=" + id_saldo + ", saldo=" + saldo + ", fk_id_cliente=" + fk_id_cliente + "]";
	}
	
}
