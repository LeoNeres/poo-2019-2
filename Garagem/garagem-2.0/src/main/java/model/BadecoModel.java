package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.Statement;

import config.SQLConnection;
import entity.Badeco;

public class BadecoModel {

    Connection connection;
    
    public BadecoModel() {
        this.connection = SQLConnection.getConnection();
    }

    public Badeco save(Badeco Badeco, int id){
    	
    	String sql = "INSERT INTO badeco VALUES (?,?,?)";
    	
    	try{

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "0");
            ps.setString(2, Badeco.getFuncao());
            ps.setInt(3, id);

            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                Badeco g = new Badeco();
                g.setId(rs.getInt(1));
                return g;
            }
                
    	} catch (Exception e){
            System.out.println("Here: "+e.getMessage());
        }
    	
    	return null;
    }

    public boolean update(int id, Badeco g){
    	
    	String sql = "UPDATE badeco SET funcao=? WHERE funcionario=?";

    	try { 
	    	PreparedStatement ps = connection.prepareStatement(sql);
	    	ps.setString(1, g.getFuncao());
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
    	
    	String SQL = "DELETE FROM badeco WHERE funcionario = ?";
    	
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

    public Badeco findById(int id){
    	String SQL = "SELECT * FROM badeco WHERE funcionario = ?";
    	
    	try {
    		PreparedStatement ps = connection.prepareStatement(SQL);
	    	ps.setInt(1, id);
	    		    	
	    	ResultSet rs = ps.executeQuery();
	    	
	    	if(rs.next()) {
	    		Badeco badeco = new Badeco();
	    		badeco.setId(rs.getInt(1));
                badeco.setFuncao(rs.getString(2));
	    	    return badeco;
	    	}
    	    
    	} catch (Exception e){
            System.out.println(e.getMessage());
        }
			return null;
    	}
    }
