package entity;

public class Gerente extends Funcionario {
	
	private String departamento;
	
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	@Override
	public float calculaSalario(float salario) {
		return (float) (salario*(1.5));
	}
	
	
}
