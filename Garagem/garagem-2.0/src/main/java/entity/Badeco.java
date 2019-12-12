package entity;

public class Badeco extends Funcionario {
	
	private String funcao;
	
	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	@Override
	public float calculaSalario(float salario) {
		return (float) (salario*(0.8));
	}
}
