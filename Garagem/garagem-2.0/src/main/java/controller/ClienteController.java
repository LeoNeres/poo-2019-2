package controller;

import java.util.List;

import entity.Cliente;
import entity.Pessoa;
import model.ClienteModel;
import model.PessoaModel;

public class ClienteController {

    ClienteModel clienteModel;
    PessoaModel pessoaModel;
    
    public ClienteController() {
        this.clienteModel = new ClienteModel();
        this.pessoaModel = new PessoaModel();
    }

    public Cliente save(Cliente cliente){
        return this.clienteModel.save(cliente);
    }

    public boolean update(int id, Cliente m){
        return clienteModel.update(id, m);
    }

    public boolean delete(int id){
        return clienteModel.delete(id);
    }

    public List<Cliente> findAll(){
    	return clienteModel.findAll();
    }

    public Cliente findById(int id){
        return clienteModel.findById(id);
    }

	public Pessoa findPessoa(int id) {
		return pessoaModel.findById(id);
	}

}
