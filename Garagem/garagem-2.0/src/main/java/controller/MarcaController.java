package controller;

import java.util.List;

import entity.Marca;
import model.MarcaModel;

public class MarcaController {

    MarcaModel marcaModel;

    public MarcaController() {
        this.marcaModel = new MarcaModel();
    }

    public Marca save(Marca marca){

        return this.marcaModel.save(marca);

    }

    public boolean update(int id, Marca m){
        return marcaModel.update(id, m);
    }

    public boolean delete(int id){
        return marcaModel.delete(id);
    }

    public List<Marca> findAll(){
    	return marcaModel.findAll();
    }

    public Marca findById(int id){
        return marcaModel.findById(id);
    }

}
