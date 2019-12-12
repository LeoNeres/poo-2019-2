package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import config.SQLConnection;
import entity.Automovel;
import entity.Modelo;

public class AutomovelModel {

    Connection connection;
    MarcaModel marcaModel;
    ModeloModel modeloModel;
	private int count;

    public AutomovelModel() {
        this.connection = SQLConnection.getConnection();
        this.marcaModel = new MarcaModel();
        this.modeloModel = new ModeloModel();
    }

    public Automovel save(Automovel Automovel){

        String SQL = "INSERT INTO automovel VALUES (?,?,?,?,?,?,?,?,?)";

        try{

            PreparedStatement ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "0");
            ps.setString(2, Automovel.getCor());
            ps.setDate(3, Automovel.getAno_fabricacao());
            ps.setDate(4, Automovel.getAno_modelo());
            ps.setString(5, Automovel.getChassi());
            ps.setString(6, Automovel.getPlaca());
            ps.setString(7, Automovel.getKm());
            ps.setFloat(8, Automovel.getValor());
            ps.setInt(9, Automovel.getModelo().getId());

            ps.executeUpdate();


            setCount(0);
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                Automovel a = new Automovel();
                a.setId(rs.getInt(1));
                return a;
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(" AQUI 2");
        return null;
    }

    public boolean update(int id, Automovel a){
    	
    	String sql = "UPDATE automovel SET cor=?, ano_fabricacao=?, ano_modelo=?, chassi=?, placa=?, km=?, valor=?  WHERE id=?";

    	try { 
	    	PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, a.getCor());
            ps.setDate(2, a.getAno_fabricacao());
            ps.setDate(3, a.getAno_modelo());
            ps.setString(4, a.getChassi());
            ps.setString(5, a.getPlaca());
            ps.setString(6, a.getKm());
            ps.setFloat(7, a.getValor());
            ps.setInt(8, a.getModelo().getId());
	    	 
	    	int rowsUpdated = ps.executeUpdate();
	    	if (rowsUpdated > 0) {
	    	    return true;
	    	}
	    	else {
	    		return false;
	    	}
    	} catch (Exception e){
            System.out.println(e.getMessage());
        }
    	return false;
    }

    public boolean delete(int id){
    	
    	String SQL = "DELETE FROM automovel WHERE id = ?";
    	
    	try {
	    	PreparedStatement ps = connection.prepareStatement(SQL);
	    	ps.setInt(1, id);
	    	 
	    	int rowsDeleted = ps.executeUpdate();
	    	if (rowsDeleted > 0) {
	    	    return true;
	    	}
	    	else {
	    		return false;
	    	}
    	} catch (Exception e){
            System.out.println(e.getMessage());
        }
		return false;
    }

    public List<Automovel> findAll(){
    	
    	String SQL = "SELECT * FROM automovel ORDER BY id";
    	
    	ArrayList<Automovel> automoveis = new ArrayList<Automovel>();
    	int count = 0;
    	
    	try {
    	
	    	java.sql.Statement st = connection.createStatement();
	    	ResultSet rs = st.executeQuery(SQL);
	
	    	while(rs.next())
	    	{
	    	    Automovel automovel = new Automovel();
	    	    automovel.setId(rs.getInt("id"));
	    	    automovel.setCor(rs.getString("cor"));
	    	    automovel.setAno_fabricacao(rs.getDate("ano_fabricacao"));
	    	    automovel.setAno_modelo(rs.getDate("ano_modelo"));
	    	    automovel.setChassi(rs.getString("chassi"));
	    	    automovel.setPlaca(rs.getString("placa"));
	    	    automovel.setKm(rs.getString("km"));
	    	    automovel.setValor(rs.getFloat("valor"));
	    	    int id = rs.getInt("modelo");
	    	    Modelo modelo = new Modelo();
                modelo = modeloModel.findById(id);
                automovel.setModelo(modelo);
	    	    
	    	    automoveis.add(automovel);
	    	    count++;
	    	}

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    	if(count < 1) {
    		return null;
    	}
        return automoveis;
    }

    public Automovel findById(int id){
    	String SQL = "SELECT * FROM Automovel WHERE id = ?";
    	
    	try {
    		PreparedStatement ps = connection.prepareStatement(SQL);
	    	ps.setInt(1, id);
	    		    	
	    	ResultSet rs = ps.executeQuery();
	    	
	    	if(rs.next()) {
	    		Automovel automovel = new Automovel();
	    	    automovel.setId(rs.getInt("id"));
	    	    automovel.setCor(rs.getString("cor"));
	    	    automovel.setAno_fabricacao(rs.getDate("ano_fabricacao"));
	    	    automovel.setAno_modelo(rs.getDate("ano_modelo"));
	    	    automovel.setChassi(rs.getString("chassi"));
	    	    automovel.setPlaca(rs.getString("placa"));
	    	    automovel.setKm(rs.getString("km"));
	    	    automovel.setValor(rs.getFloat("valor"));
	    	    int i = rs.getInt("modelo");
	    	    Modelo modelo = new Modelo();
                modelo = modeloModel.findById(i);
                automovel.setModelo(modelo);
	    	    return automovel;
	    	}
    	    
    	} catch (Exception e){
            System.out.println(e.getMessage());
        }
		return null;
    }

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
