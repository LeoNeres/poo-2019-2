package controller;

import java.util.List;

import entity.Funcionario;
import entity.Pessoa;
import model.FuncionarioModel;
import model.PessoaModel;

public class FuncionarioController {

    FuncionarioModel funcionarioModel;
    PessoaModel pessoaModel;

    public FuncionarioController() {
        this.funcionarioModel = new FuncionarioModel();
        this.pessoaModel = new PessoaModel();
    }

    public Funcionario save(Funcionario funcionario){
        return this.funcionarioModel.save(funcionario);
    }

    public boolean update(int id, Funcionario m){
        return funcionarioModel.update(id, m);
    }

    public boolean delete(int id){
        return funcionarioModel.delete(id);
    }

    public List<Funcionario> findAll(){
    	return funcionarioModel.findAll();
    }

    public Funcionario findById(int id){
        return funcionarioModel.findById(id);
    }
    
    public Pessoa findPessoa(int id){
        return pessoaModel.findById(id);
    }
    
    public boolean verificaSalarioFuncionario(float salario){
    	return funcionarioModel.verificaSalarioFuncionario(salario);
    }
    
    public boolean verificaSalarioBadeco(float salario){
    	return funcionarioModel.verificaSalarioBadeco(salario);
    }

}
