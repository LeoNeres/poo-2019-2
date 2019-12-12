package controller;

import entity.Automovel;
import entity.Cliente;
import entity.Funcionario;
import entity.Venda;
import entity.VendaRelatorio;
import model.AutomovelModel;
import model.ClienteModel;
import model.FuncionarioModel;
import model.VendaModel;

import java.util.ArrayList;
import java.util.List;

public class VendaController {

    VendaModel vendaModel;
    ClienteModel clienteModel;
    FuncionarioModel funcionarioModel;
    AutomovelModel automovelModel;
    
    public VendaController() {
        this.vendaModel = new VendaModel();
        this.clienteModel = new ClienteModel();
        this.funcionarioModel = new FuncionarioModel();
        this.automovelModel = new AutomovelModel();
    }

    public Venda save(Venda venda){
        return this.vendaModel.save(venda);
    }

    public boolean update(int id, Venda m){
        return vendaModel.update(id, m);
    }

    public boolean delete(int id){
        return vendaModel.delete(id);
    }

    public List<Venda> findAll(){
    	return vendaModel.findAll();
    }

    public Venda findById(int id){
        return vendaModel.findById(id);
    }
    
    public Automovel findAutomovel(int id) {
    	return automovelModel.findById(id);
    }
    
    public Cliente findCliente(int id) {
    	return clienteModel.findById(id);
    }
    
    public Funcionario findFuncionario(int id) {
    	return funcionarioModel.findById(id);
    }
    
    public ArrayList<VendaRelatorio> gerarRelatorio(java.sql.Date dtInicio, java.sql.Date dtFim){
    	return vendaModel.gerarRelatorio(dtInicio, dtFim);
    }
    
}
