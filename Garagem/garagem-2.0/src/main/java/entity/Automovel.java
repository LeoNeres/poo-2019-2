package entity;

import java.sql.Date;

public class Automovel {

    private int id;
    private String cor;
    private java.sql.Date ano_fabricacao;
    private java.sql.Date ano_modelo;
    private String chassi;
    private String placa;
    private String km;
    private float valor;
    private Modelo modelo;
        
	public Modelo getModelo() {
		return modelo;
	}
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public Date getAno_fabricacao() {
		return ano_fabricacao;
	}
	public void setAno_fabricacao(Date ano_fabricacao) {
		this.ano_fabricacao = ano_fabricacao;
	}
	public Date getAno_modelo() {
		return ano_modelo;
	}
	public void setAno_modelo(Date ano_modelo) {
		this.ano_modelo = ano_modelo;
	}
	public String getChassi() {
		return chassi;
	}
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getKm() {
		return km;
	}
	public void setKm(String km) {
		this.km = km;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
}
