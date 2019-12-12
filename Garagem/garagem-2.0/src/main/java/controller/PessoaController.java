package controller;

import java.util.List;

import entity.Pessoa;
import model.PessoaModel;

public class PessoaController {

    PessoaModel pessoaModel;

    public PessoaController() {
        this.pessoaModel = new PessoaModel();
    }

    public Pessoa save(Pessoa pessoa){
        return this.pessoaModel.save(pessoa);
    }

    public boolean update(int id, Pessoa m){
        return pessoaModel.update(id, m);
    }

    public boolean delete(int id){
        return pessoaModel.delete(id);
    }

    public List<Pessoa> findAll(){
    	return pessoaModel.findAll();
    }

    public Pessoa findById(int id){
        return pessoaModel.findById(id);
    }

}
