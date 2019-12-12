 package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import config.SQLConnection;
import entity.Automovel;
import entity.Cliente;
import entity.Funcionario;
import entity.Venda;
import entity.VendaRelatorio;

public class VendaModel {

    Connection connection;
    AutomovelModel automovelModel;
    ClienteModel clienteModel;
    FuncionarioModel funcionarioModel;
	private int count;

    public VendaModel() {
        this.connection = SQLConnection.getConnection();
        this.automovelModel = new AutomovelModel();
        this.clienteModel = new ClienteModel();
        this.funcionarioModel = new FuncionarioModel();
    }

    public Venda save(Venda Venda){

        String SQL = "INSERT INTO venda VALUES (?,?,?,?,?,?,?,?)";

        try{

            PreparedStatement ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "0");
            ps.setInt(2, Venda.getAutomovel().getId());
            ps.setFloat(3, Venda.getValor_venda());
            ps.setInt(4, Venda.getCliente().getId());
            ps.setInt(5, Venda.getFuncionario().getId());
            ps.setDate(6, Venda.getDt_venda());
            ps.setInt(7, Venda.getCod_venda());
            ps.setFloat(8, Venda.getValor_venda()/10);

            ps.executeUpdate();


            setCount(0);
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                Venda v = new Venda();
                v.setId(rs.getInt(1));
                return v;
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean update(int id, Venda v){
    	
    	String sql = "UPDATE venda SET automovel=?, valor_venda=?, cliente=?, funcionario=?, dt_venda=?, cod_venda=?, comissao_venda=? WHERE id=?";

    	try { // mostrar os valores
	    	PreparedStatement ps = connection.prepareStatement(sql);
	    	ps.setInt(1, v.getFuncionario().getId());
            ps.setFloat(2, v.getValor_venda());
            ps.setInt(3, v.getCliente().getId());
            ps.setInt(4, v.getFuncionario().getId());
            ps.setDate(5, v.getDt_venda());
            ps.setInt(6, v.getCod_venda());
            ps.setFloat(7, v.getValor_venda()/10);
            ps.setInt(8, id);
	    	 
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
    	
    	String SQL = "DELETE FROM venda WHERE id = ?";
    	
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

    public List<Venda> findAll(){
    	
    	String SQL = "SELECT * FROM venda ORDER BY id";
    	
    	ArrayList<Venda> Vendas = new ArrayList<Venda>();
    	int count = 0;
    	
    	try {
    	
	    	java.sql.Statement st = connection.createStatement();
	    	ResultSet rs = st.executeQuery(SQL);
	
	    	while(rs.next())
	    	{
	    		Venda venda = new Venda();
	    	    venda.setId(rs.getInt("id"));
	    	    int i = rs.getInt("automovel");
	    	    Automovel automovel = new Automovel();
                automovel = automovelModel.findById(i);
                venda.setAutomovel(automovel);
	    	    venda.setValor_venda(rs.getFloat("valor_venda"));
	    	    i = rs.getInt("cliente");
	    	    Cliente cliente = new Cliente();
                cliente = clienteModel.findById(i);
                venda.setCliente(cliente);
                i = rs.getInt("funcionario");
	    	    Funcionario funcionario = new Funcionario();
                funcionario = funcionarioModel.findById(i);
                venda.setFuncionario(funcionario);
                venda.setDt_venda(rs.getDate("dt_venda"));
                venda.setCod_venda(rs.getInt("cod_venda"));
                venda.setComissao_venda(rs.getFloat("comissao_venda"));
	    	    
	    	    Vendas.add(venda);
	    	    count++;
	    	}

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    	if(count < 1) {
    		return null;
    	}
        return Vendas;
    }

    public Venda findById(int id){
    	String SQL = "SELECT * FROM venda WHERE id = ?";
    	
    	try {
    		PreparedStatement ps = connection.prepareStatement(SQL);
	    	ps.setInt(1, id);
	    		    	
	    	ResultSet rs = ps.executeQuery();
	    	
	    	if(rs.next()) {
	    		Venda venda = new Venda();
	    	    venda.setId(rs.getInt("id"));
	    	    int i = rs.getInt("automovel");
	    	    Automovel automovel = new Automovel();
                automovel = automovelModel.findById(i);
                venda.setAutomovel(automovel);
	    	    venda.setValor_venda(rs.getFloat("valor_venda"));
	    	    i = rs.getInt("cliente");
	    	    Cliente cliente = new Cliente();
                cliente = clienteModel.findById(i);
                venda.setCliente(cliente);
                i = rs.getInt("funcionario");
	    	    Funcionario funcionario = new Funcionario();
                funcionario = funcionarioModel.findById(i);
                venda.setFuncionario(funcionario);
                venda.setDt_venda(rs.getDate("dt_venda"));
                venda.setCod_venda(rs.getInt("cod_venda"));
                venda.setComissao_venda(rs.getFloat("comissao_venda"));
	    	    return venda;
	    	}else {
	    		return null;
	    	}
    	    
    	} catch (Exception e){
            System.out.println(e.getMessage());
        }
		return null;
    }
    
    public ArrayList<VendaRelatorio> gerarRelatorio(java.sql.Date dtInicio, java.sql.Date dtFim){
    	
    	DateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
    	
    	String dt1 = dt.format(dtInicio);
    	String dt2 = dt.format(dtFim);
    	
    	
    	String SQL = "SELECT p.nome AS nome_vendedor, sum(v.valor_venda) AS total_venda, sum(v.comissao_venda) AS total_comissao FROM funcionario AS f JOIN venda AS v ON v.funcionario = f.id JOIN pessoa AS p ON f.pessoa = p.id WHERE v.dt_venda BETWEEN '" + dt1 + "' AND '" + dt2 + "' GROUP BY p.nome ORDER BY p.nome";
    	
    	ArrayList<VendaRelatorio> Relatorio = new ArrayList<VendaRelatorio>();
    	int count = 0;
    	
    	try {
    	
    		PreparedStatement ps = connection.prepareStatement(SQL);
	    	ResultSet rs = ps.executeQuery(SQL);
	
	    	while(rs.next())
	    	{
	    		VendaRelatorio venda = new VendaRelatorio();
	    	    venda.setVendedor(rs.getString("nome_vendedor"));
	    		venda.setTotal_venda(rs.getFloat("total_venda"));
	    		venda.setTotal_comissao(rs.getFloat("total_comissao"));
	    	    
	    	    Relatorio.add(venda);
	    	    count++;
	    	}

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    	if(count < 1) {
    		return null;
    	}
        return Relatorio;
    }

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
