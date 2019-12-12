package entity;

public class Venda {
	
	private int id;
	private Automovel automovel;
	private float valor_venda;
	private Cliente cliente;
	private Funcionario funcionario;
	private java.sql.Date dt_venda;
	private int cod_venda;
	private float comissao_venda;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Automovel getAutomovel() {
		return automovel;
	}
	public void setAutomovel(Automovel automovel) {
		this.automovel = automovel;
	}
	public float getValor_venda() {
		return valor_venda;
	}
	public void setValor_venda(float valor_venda) {
		this.valor_venda = valor_venda;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public java.sql.Date getDt_venda() {
		return dt_venda;
	}
	public void setDt_venda(java.sql.Date venda) {
		this.dt_venda = venda;
	}
	public int getCod_venda() {
		return cod_venda;
	}
	public void setCod_venda(int cod_venda) {
		this.cod_venda = cod_venda;
	}
	public float getComissao_venda() {
		return comissao_venda;
	}
	public void setComissao_venda(float comissao_venda) {
		this.comissao_venda = comissao_venda;
	}
}
