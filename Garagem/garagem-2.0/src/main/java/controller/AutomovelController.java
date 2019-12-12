package controller;

import java.util.List;

import entity.Automovel;
import entity.Modelo;
import model.AutomovelModel;
import model.MarcaModel;
import model.ModeloModel;

public class AutomovelController {

	AutomovelModel automovelModel;
	ModeloModel modeloModel;
	MarcaModel marcaModel;
    
    public AutomovelController() {
    	this.automovelModel = new AutomovelModel();
    	this.modeloModel = new ModeloModel();
    	this.marcaModel = new MarcaModel();
    }

    public Automovel save(Automovel automovel){
        return this.automovelModel.save(automovel);
    }

    public boolean update(int id, Automovel m){
        return automovelModel.update(id, m);
    }

    public boolean delete(int id){
        return automovelModel.delete(id);
    }

    public List<Automovel> findAll(){
    	return automovelModel.findAll();
    }

    public Automovel findById(int id){
        return automovelModel.findById(id);
    }
    
    public Modelo findModelo(int id) {
    	return modeloModel.findById(id);
    }

}
