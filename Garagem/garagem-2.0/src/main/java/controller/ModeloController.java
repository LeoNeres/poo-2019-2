package controller;

import java.util.List;

import entity.Marca;
import entity.Modelo;
import model.MarcaModel;
import model.ModeloModel;

public class ModeloController {

    ModeloModel modeloModel;
    MarcaModel marcaModel;
    
    public ModeloController() {
        this.modeloModel = new ModeloModel();
        this.marcaModel = new MarcaModel();
    }

    public Modelo save(Modelo modelo){
        return this.modeloModel.save(modelo);
    }

    public boolean update(int id, Modelo m){
        return modeloModel.update(id, m);
    }

    public boolean delete(int id){
        return modeloModel.delete(id);
    }

    public List<Modelo> findAll(){
    	return modeloModel.findAll();
    }

    public Modelo findById(int id){
        return modeloModel.findById(id);
    }

	public Marca findMarca(int id) {
		return marcaModel.findById(id);
	}

}
