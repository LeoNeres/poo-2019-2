package model;

import config.SQLConnection;
import entity.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

public class PessoaModel {

    Connection connection;
	private int count;
    
    public PessoaModel() {
        this.connection = SQLConnection.getConnection();
    }

    public Pessoa save(Pessoa Pessoa){

        String SQL = "INSERT INTO pessoa VALUES (?,?,?,?,?,?)";

        try{

            PreparedStatement ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "0");
            ps.setString(2, Pessoa.getNome());
            ps.setString(3, Pessoa.getCpf());
            ps.setString(4, Pessoa.getEndereco());
            ps.setString(5, Pessoa.getTelefone());
            ps.setDate(6, Pessoa.getDt_nascimento());

            ps.executeUpdate();


            setCount(0);
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt(1));
                return pessoa;
            }


        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean update(int id, Pessoa p){
    	
    	String sql = "UPDATE pessoa SET nome=?, cpf=?, endereco=?, telefone=?, dt_nascimento=? WHERE id=?";

    	try { 
	    	PreparedStatement ps = connection.prepareStatement(sql);
	    	ps.setString(1, p.getNome());
            ps.setString(2, p.getCpf());
            ps.setString(3, p.getEndereco());
            ps.setString(4, p.getTelefone());
            ps.setDate(5, p.getDt_nascimento());
            ps.setInt(6, id);
	    	 
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
    	
    	String SQL = "DELETE FROM pessoa WHERE id = ?";
    	
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

    public List<Pessoa> findAll(){
    	
    	String SQL = "SELECT * FROM pessoa ORDER BY id";
    	
    	ArrayList<Pessoa> Pessoas = new ArrayList<Pessoa>();
    	int count = 0;
    	
    	try {
    	
	    	java.sql.Statement st = connection.createStatement();
	    	ResultSet rs = st.executeQuery(SQL);
	
	    	while(rs.next())
	    	{
	    	    Pessoa pessoa = new Pessoa();
	    	    pessoa.setId(rs.getInt(1));
                pessoa.setNome(rs.getString(2));
                pessoa.setCpf(rs.getString(3));
                pessoa.setEndereco(rs.getString(4));
                pessoa.setTelefone(rs.getString(5));
                pessoa.setDt_nascimento(rs.getDate(6));
	    	    
	    	    Pessoas.add(pessoa);
	    	    count++;
	    	}

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    	if(count < 1) {
    		return null;
    	}
        return Pessoas;
    }

    public Pessoa findById(int id){
    	String SQL = "SELECT * FROM Pessoa WHERE id = ?";
    	
    	try {
    		PreparedStatement ps = connection.prepareStatement(SQL);
	    	ps.setInt(1, id);
	    		    	
	    	ResultSet rs = ps.executeQuery();
	    	
	    	if(rs.next()) {
	    		Pessoa pessoa = new Pessoa();
	    		pessoa.setId(rs.getInt(1));
                pessoa.setNome(rs.getString(2));
                pessoa.setCpf(rs.getString(3));
                pessoa.setEndereco(rs.getString(4));
                pessoa.setTelefone(rs.getString(5));
                pessoa.setDt_nascimento(rs.getDate(6));
	    	    return pessoa;
	    	}else {
	    		return null;
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
