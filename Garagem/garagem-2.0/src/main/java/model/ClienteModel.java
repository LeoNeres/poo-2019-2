package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import config.SQLConnection;
import entity.Cliente;
import entity.Pessoa;

public class ClienteModel {

    Connection connection;
    PessoaModel pessoaModel;
	private int count;

    public ClienteModel() {
        this.connection = SQLConnection.getConnection();
        this.pessoaModel = new PessoaModel();
    }

    public Cliente save(Cliente Cliente){

        String SQL = "INSERT INTO cliente VALUES (?,?,?)";

        try{

            PreparedStatement ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "0");
            ps.setInt(2, Cliente.getCodigo());
            ps.setInt(3, Cliente.getPessoa().getId());

            ps.executeUpdate();


            setCount(0);
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                Cliente c = new Cliente();
                c.setId(rs.getInt(1));
                return c;
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean update(int id, Cliente c){
    	
    	String sql = "UPDATE cliente SET codigo=? WHERE id=?";

    	try { 
	    	PreparedStatement ps = connection.prepareStatement(sql);
	    	ps.setInt(1, c.getCodigo());
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
    	
    	String SQL = "DELETE FROM cliente WHERE id = ?";
    	
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

    public List<Cliente> findAll(){
    	
    	String SQL = "SELECT * FROM cliente ORDER BY id";
    	
    	ArrayList<Cliente> Clientes = new ArrayList<Cliente>();
    	int count = 0;
    	
    	try {
    	
	    	java.sql.Statement st = connection.createStatement();
	    	ResultSet rs = st.executeQuery(SQL);
	
	    	while(rs.next())
	    	{
	    	    Cliente cliente = new Cliente();
	    	    cliente.setId(rs.getInt("id"));
	    	    cliente.setCodigo(rs.getInt("codigo"));
	    	    int id = rs.getInt("pessoa");
                Pessoa pessoa = new Pessoa();
                pessoa = pessoaModel.findById(id);
                cliente.setPessoa(pessoa);
	    	    
	    	    Clientes.add(cliente);
	    	    count++;
	    	}

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    	if(count < 1) {
    		return null;
    	}
        return Clientes;
    }

    public Cliente findById(int id){
    	String SQL = "SELECT * FROM cliente WHERE id = ?";
    	
    	try {
    		PreparedStatement ps = connection.prepareStatement(SQL);
	    	ps.setInt(1, id);
	    		    	
	    	ResultSet rs = ps.executeQuery();
	    	
	    	if(rs.next()) {
	    		Cliente cliente = new Cliente();
	    	    cliente.setId(rs.getInt("id"));
	    	    cliente.setCodigo(rs.getInt("codigo"));
	    	    int i = rs.getInt("pessoa");
                Pessoa pessoa = new Pessoa();
                pessoa = pessoaModel.findById(i);
                cliente.setPessoa(pessoa);
	    	    
                return cliente;
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
