package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import config.SQLConnection;
import entity.Badeco;
import entity.Funcionario;
import entity.Gerente;
import entity.Pessoa;

public class FuncionarioModel {

    Connection connection;
    GerenteModel gerenteModel;
    BadecoModel badecoModel;
    PessoaModel pessoaModel;
	private int count;
    
    public FuncionarioModel() {
        this.connection = SQLConnection.getConnection();
        this.gerenteModel = new GerenteModel();
        this.badecoModel = new BadecoModel();
        this.pessoaModel = new PessoaModel();
    }

    public Funcionario save(Funcionario Funcionario){

    	String SQL = "INSERT INTO funcionario VALUES (?,?,?,?,?)";

        try{

            PreparedStatement ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "0");
            ps.setString(2, Funcionario.getUsuario());
            ps.setString(3, Funcionario.getSenha());
            ps.setFloat(4, Funcionario.getSalario());
            ps.setInt(5, Funcionario.getPessoa().getId());

            ps.executeUpdate();


            setCount(0);
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
            	
            	if(Funcionario instanceof Gerente) {
            		gerenteModel.save((Gerente) Funcionario, rs.getInt(1));
            	}
            	
            	if(Funcionario instanceof Badeco) {
            		badecoModel.save((Badeco) Funcionario, rs.getInt(1));
            	}
            	
                Funcionario a = new Funcionario();
                a.setId(rs.getInt(1));
                return a;
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(" AQUI 2");
        return null;
    }

    public boolean update(int id, Funcionario f){
    	
    	String sql = "UPDATE funcionario SET usuario=?, senha=?, salario=? WHERE id=?";
    	
    	 if(f instanceof Gerente) {
         	Gerente g = new Gerente();
         	g.setDepartamento(((Gerente) f).getDepartamento());
         	boolean g2 = gerenteModel.update(f.getId(), g);
         	if(g2 == false) {
         		return g2;
         	}
         }
         
         if(f instanceof Badeco) {
         	Badeco b = new Badeco();
         	b.setFuncao(((Badeco) f).getFuncao());
         	boolean b2 = badecoModel.update(f.getId(), b);
         	if(b2 == false) {
         		return b2;
         	}
         }

    	try { 
	    	PreparedStatement ps = connection.prepareStatement(sql);
	    	ps.setString(1, f.getUsuario());
            ps.setString(2, f.getSenha());
            ps.setFloat(3, f.getSalario());
            ps.setInt(4, f.getId());
            
           
            
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
    	
    	String SQL = "DELETE FROM funcionario WHERE id = ?";
    	
    	if(gerenteModel.findById(id) != null) {
    		boolean r = gerenteModel.delete(id);
    		if(r = false) {
    			return r;
    		}
		}
    	
		if(badecoModel.findById(id) != null) {
			boolean r = badecoModel.delete(id);
			if(r = false) {
    			return r;
    		}
		}
    	
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

    public List<Funcionario> findAll(){
    	
    	String SQL = "SELECT * FROM funcionario ORDER BY id";
    	
    	ArrayList<Funcionario> Funcionarios = new ArrayList<Funcionario>();
    	int count = 0;
    	
    	try {
    		
	    	java.sql.Statement st = connection.createStatement();
	    	ResultSet rs = st.executeQuery(SQL);
	
	    	while(rs.next())
	    	{
	    		Funcionario f;
	    		Gerente g = gerenteModel.findById(rs.getInt(1));
	    		Badeco b = badecoModel.findById(rs.getInt(1));
	    		if(g != null) {
	    			f = g;
	    		}
	    		else if(b != null) {
	    			f = b;
	    		}
	    		else {
	    			f = new Funcionario();
	    		}
	    		
	    	    f.setId(rs.getInt(1));
                f.setUsuario(rs.getString(2));
                f.setSenha(rs.getString(3));
                f.setSalario(rs.getFloat(4));
                int id = rs.getInt("pessoa");
	    	    Pessoa pessoa = new Pessoa();
                pessoa = pessoaModel.findById(id);
                f.setPessoa(pessoa);
	    	    
	    	    Funcionarios.add(f);
	    	    count++;
	    	}

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    	if(count < 1) {
    		return null;
    	}
        return Funcionarios;
    }

    public Funcionario findById(int id){
    	String SQL = "SELECT * FROM Funcionario WHERE id = ?";
    	
    	try {
    		PreparedStatement ps = connection.prepareStatement(SQL);
	    	ps.setInt(1, id);
	    		    	
	    	ResultSet rs = ps.executeQuery();
	    	
	    	if(rs.next()) {
	    		Funcionario f;
	    		Gerente g = gerenteModel.findById(rs.getInt(1));
	    		Badeco b = badecoModel.findById(rs.getInt(1));
	    		if(g != null) {
	    			f = g;
	    		}
	    		else if(b != null) {
	    			f = b;
	    		}
	    		else {
	    			f = new Funcionario();
	    		}
	    		
	    	    f.setId(rs.getInt(1));
                f.setUsuario(rs.getString(2));
                f.setSenha(rs.getString(3));
                f.setSalario(rs.getFloat(4));
                int i = rs.getInt("pessoa");
	    	    Pessoa pessoa = new Pessoa();
                pessoa = pessoaModel.findById(i);
                f.setPessoa(pessoa);
	    	    
                return f;
	    	    
	    	}else {
	    		return null;
	    	}
    	    
    	} catch (Exception e){
            System.out.println(e.getMessage());
        }
		return null;
    }

    public boolean verificaSalarioFuncionario(float s){
    	
    	String SQL = "SELECT f.id, f.usuario, f.senha, MIN(f.salario) AS salario FROM gerente AS g JOIN funcionario AS f WHERE f.id = g.funcionario";
    	
    	try {
    		PreparedStatement ps = connection.prepareStatement(SQL);
	    		    		    	
	    	ResultSet rs = ps.executeQuery();
	    	
	    	if(rs.next()) {
	    		
	    		if(rs.getFloat("salario") > 0 && rs.getFloat("salario") < s) {
	    			return true;
	    		}
	    		else {
	    			return false;
	    		}
	    		
	    	}    	    
    	} catch (Exception e){
            System.out.println(e.getMessage());
        }
    	
    	return false;
    }
    
    public boolean verificaSalarioBadeco(float s){
    	
    	String SQL = "SELECT f.id, f.usuario, f.senha, MIN(f.salario) AS salario FROM funcionario AS f JOIN badeco AS b JOIN gerente AS g WHERE f.id <> b.funcionario AND f.id <> g.funcionario";
    	
    	try {
    		PreparedStatement ps = connection.prepareStatement(SQL);
	    		    		    	
	    	ResultSet rs = ps.executeQuery();
	    	
	    	if(rs.next()) {
	    		
	    		if(rs.getFloat("salario") > 0 && rs.getFloat("salario") < s) {
	    			return true;
	    		}
	    		else {
	    			return false;
	    		}
	    		
	    	}    	    
    	} catch (Exception e){
            System.out.println(e.getMessage());
        }
    	
    	return false;
    }

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
