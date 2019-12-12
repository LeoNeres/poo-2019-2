package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.Statement;

import config.SQLConnection;
import entity.Gerente;

public class GerenteModel {

    Connection connection;
    
    public GerenteModel() {
        this.connection = SQLConnection.getConnection();
    }

    public Gerente save(Gerente Gerente, int id){
    	
    	String sql = "INSERT INTO gerente VALUES (?,?,?)";
    	
    	try{

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "0");
            ps.setString(2, Gerente.getDepartamento());
            ps.setInt(3, id);

            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                Gerente g = new Gerente();
                g.setId(rs.getInt(1));
                return g;
            }
                
    	} catch (Exception e){
            System.out.println("Here: "+e.getMessage());
        }
    	
    	return null;
    }

    public boolean update(int id, Gerente g){
    	
    	String sql = "UPDATE gerente SET departamento=? WHERE funcionario=?";

    	try { 
	    	PreparedStatement ps = connection.prepareStatement(sql);
	    	ps.setString(1, g.getDepartamento());
            ps.setInt(2, id);
	    	 
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
    	
    	String SQL = "DELETE FROM gerente WHERE funcionario = ?";
    	
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

    public Gerente findById(int id){
    	String SQL = "SELECT * FROM gerente WHERE funcionario = ?";
    	
    	try {
    		PreparedStatement ps = connection.prepareStatement(SQL);
	    	ps.setInt(1, id);
	    		    	
	    	ResultSet rs = ps.executeQuery();
	    	
	    	if(rs.next()) {
	    		Gerente gerente = new Gerente();
	    		gerente.setId(rs.getInt(1));
                gerente.setDepartamento(rs.getString(2));
	    	    return gerente;
	    	}
    	    
    	} catch (Exception e){
            System.out.println(e.getMessage());
        }
			return null;
    	}
    }
