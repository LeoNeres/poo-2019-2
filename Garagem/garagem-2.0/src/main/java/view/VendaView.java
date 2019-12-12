package view;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.VendaController;
import entity.Automovel;
import entity.Cliente;
import entity.Funcionario;
import entity.Venda;
import entity.VendaRelatorio;

public class VendaView {

    VendaController VendaController;
    
    ArrayList<Venda> vendas = new ArrayList<Venda>();

	private Scanner sc;

	private Scanner c;

	private Scanner c2;

	private Scanner c3;

	private Scanner c4;

	private Scanner sc2;

	private int i;

    public VendaView() {
        this.VendaController = new VendaController();
    }

    public void menuVenda(){
    	
    	int op = 0;
    	
    	System.out.println("----Menu Venda----");
    	System.out.println("1 - Cadastrar");
    	System.out.println("2 - Listar");
    	System.out.println("3 - Buscar");
    	System.out.println("4 - Alterar");
    	System.out.println("5 - Excluir");
    	System.out.println("6 - Relatório de Vendas");
    	System.out.println("0 - Voltar");
    	
    	sc = new Scanner(System.in);
    	
    	op = sc.nextInt();
    	
    	switch (op) {
			
			case 1:{
				System.out.println(" Cadastrar Venda");
		        Venda v = new Venda();
		        c = new Scanner(System.in);
		        
		        System.out.println("Informe o id do Automóvel:");
		        Automovel automovel = new Automovel();
		        
		        automovel = this.findAutomovel(c.nextInt());
		        
		        if(automovel == null) {
		        	System.out.println("Este automovel não existe!");
		        	this.menuVenda();
		        	break;
		        }
		        else {
		        	System.out.println("Automovel: "+automovel.getModelo().getNome());
		        }
		        
		        v.setAutomovel(automovel);
		        
		        
		        System.out.println("Informe o valor da Venda:");
		        v.setValor_venda(c.nextFloat());
		        
		        System.out.println("Informe o id do Cliente:");
		        Cliente cliente = new Cliente();
		        
		        cliente = this.findCliente(c.nextInt());
		        
		        if(cliente == null) {
		        	System.out.println("Este cliente não existe!");
		        	this.menuVenda();
		        	break;
		        }
		        else {
		        	System.out.println("Cliente: "+cliente.getPessoa().getNome());
		        }
		        
		        v.setCliente(cliente);
		        
		        System.out.println("Informe o id do Funcionário:");
		        Funcionario funcionario = new Funcionario();
		        
		        funcionario = this.findFuncionario(c.nextInt());
		        
		        if(funcionario == null) {
		        	System.out.println("Este funcionario não existe!");
		        	this.menuVenda();
		        	break;
		        }
		        else {
		        	System.out.println("Funcionario: "+funcionario.getPessoa().getNome());
		        }
		        
		        v.setFuncionario(funcionario);
		        
		        System.out.println("Informe a data da Venda:");
		        String dt = new String();
		        dt = c.next();
				
		        DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		        
				try {
					java.util.Date dataUtil = new java.util.Date(fmt.parse(dt).getTime());
					java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
					//data = new java.sql.Date(fmt.parse(dt).getTime());
					v.setDt_venda(dataSql);
				} catch (ParseException e) {
					e.printStackTrace();
				}
		        
		        System.out.println("Informe o código da Venda:");
		        v.setCod_venda(c.nextInt());
		        
		        Venda m2 = this.save(v);

		        if(m2 == null){
		            System.out.println("\n\nVenda não foi cadastrada!\n\n");
		        } else {
		            System.out.println("\n\nVenda cadastrada com sucesso!");
		        }
		        
		        this.menuVenda();
		        break;
	        }
			
			case 2:{
		    	System.out.println(" Listar Venda ");
		    	vendas = (ArrayList<Venda>) this.findAll();
		    	if(vendas != null) {
		    		vendas.forEach((v) -> {
		    			if(v.getId() < 10) {
		    				System.out.println("ID - 0"+v.getId());
		    			}else {
		    				System.out.println("ID - "+v.getId());
		    			}
		    			System.out.println("CÓDIGO - "+v.getCod_venda());
		    			System.out.println("DATA DA VENDA - "+v.getDt_venda());
		    			System.out.println("CLIENTE - "+v.getCliente().getPessoa().getNome());
			            System.out.println("AUTOMOVEL - "+v.getAutomovel().getModelo().getNome());
			            System.out.println("VALOR - "+v.getValor_venda());
			            System.out.println("FUNCIONÁRIO - "+v.getFuncionario().getPessoa().getNome());
			            System.out.println("COMISSÃO - "+v.getComissao_venda());
			            
			            System.out.println("----------------------------------");
		    		});
		    	}else {
		    		System.out.println("Não há vendas cadastradas!");
		    	}
		    	System.out.println("\n");
		    	
				this.menuVenda();
		        break;
	        }
			
			case 3:{
				System.out.println(" Buscar Venda");
				c2 = new Scanner(System.in);
				System.out.println("Informe o id da Venda:");
		        int n = c2.nextInt();
		        Venda v = new Venda();
				v = this.findById(n);
				if(v != null) {
					if(v.getId() < 10) {
	    				System.out.println("ID - 0"+v.getId());
	    			}else {
	    				System.out.println("ID - "+v.getId());
	    			}
					System.out.println("CÓDIGO - "+v.getCod_venda());
					System.out.println("CLIENTE - "+v.getCliente().getPessoa().getNome());
		            System.out.println("AUTOMOVEL - "+v.getAutomovel().getModelo().getNome());
		            System.out.println("VALOR - "+v.getValor_venda());
		            System.out.println("FUNCIONÁRIO - "+v.getFuncionario().getPessoa().getNome());
		            System.out.println("COMISSÃO - "+v.getComissao_venda());
				}
				else {
					System.out.println("Este id não existe!\n");
				}
				System.out.println("\n");
				this.menuVenda();
		        break;
	        }
			
			case 4:{
				System.out.println(" Alterar Venda");
				c3 = new Scanner(System.in);
				System.out.println("Informe o id do Venda:");
		        int n = c3.nextInt();
		        Venda v = this.findById(n);
		        if(v == null) {
		        	System.out.println("Este id não existe!\n");
		        	this.menuVenda();
		        }
		        else {
		        	if(v.getId() < 10) {
	    				System.out.println("ID - 0"+v.getId());
	    			}else {
	    				System.out.println("ID - "+v.getId());
	    			}
		        	System.out.println("CÓDIGO - "+v.getCod_venda());
		        	System.out.println("CLIENTE - "+v.getCliente().getPessoa().getNome());
		            System.out.println("AUTOMOVEL - "+v.getAutomovel().getModelo().getNome());
		            System.out.println("VALOR - "+v.getValor_venda());
		            System.out.println("FUNCIONÁRIO - "+v.getFuncionario().getPessoa().getNome());
		            System.out.println("COMISSÃO - "+v.getComissao_venda());
		        }
		        
		        System.out.println("Informe o novo id do Automóvel:");
		        Automovel automovel = new Automovel();
		        
		        automovel = this.findAutomovel(c3.nextInt());
		        
		        if(automovel == null) {
		        	System.out.println("Este automovel não existe!");
		        	this.menuVenda();
		        }
		        else {
		        	System.out.println("Automovel: "+automovel.getModelo().getNome());
		        }
		        
		        v.setAutomovel(automovel);
		        
		        
		        System.out.println("Informe o novo valor da Venda:");
		        v.setValor_venda(c3.nextFloat());
		        
		        System.out.println("Informe o novo id do Cliente:");
		        Cliente cliente = new Cliente();
		        
		        cliente = this.findCliente(c3.nextInt());
		        
		        if(cliente == null) {
		        	System.out.println("Este cliente não existe!");
		        	this.menuVenda();
		        	break;
		        }
		        else {
		        	System.out.println("Cliente: "+cliente.getPessoa().getNome());
		        }
		        
		        v.setCliente(cliente);
		        
		        System.out.println("Informe o novo id do Funcionário:");
		        Funcionario funcionario = new Funcionario();
		        
		        funcionario = this.findFuncionario(c3.nextInt());
		        
		        if(funcionario == null) {
		        	System.out.println("Este funcionario não existe!");
		        	this.menuVenda();
		        	break;
		        }
		        else {
		        	System.out.println("Funcionario: "+funcionario.getPessoa().getNome());
		        }
		        
		        v.setFuncionario(funcionario);
		        
		        System.out.println("Informe a nova data da Venda:");
		        String dt = new String();
		        dt = c3.next();
				
		        DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		        
				try {
					java.util.Date dataUtil = new java.util.Date(fmt.parse(dt).getTime());
					java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
					
					v.setDt_venda(dataSql);
				} catch (ParseException e) {
					e.printStackTrace();
				}
		        
		        System.out.println("Informe o novo código da Venda:");
		        v.setCod_venda(c3.nextInt());

		        boolean r = this.update(n, v);
		        if(r == true) {
		        	System.out.println("Venda alterada com sucesso!");
		        }
		        else {
		        	System.out.println("O Venda não foi alterada!");
		        }
		        
		        this.menuVenda();
		        break;
	        }
			
			case 5:{
				System.out.println(" Excluir Venda");
				c4 = new Scanner(System.in);
				System.out.println("Informe o id da Venda:");
		        int n = c4.nextInt();
		        boolean r = this.delete(n);
		        if(r == true) {
		        	System.out.println("Venda excluído com sucesso!");
		        }
		        else {
		        	System.out.println("A Venda não foi excluído ou não existe!");
		        }
		        
				this.menuVenda();
		        break;
	        }
			
			case 6:{
				System.out.println(" Relatório de Venda");
				
				sc2 = new Scanner(System.in);
				java.sql.Date dataInicio = null;
				java.sql.Date dataFim = null;
								
				System.out.println("Informe a data de início do Relatório: ");
				String dt = new String();
		        dt = sc2.next();
				
		        DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		        
				try {
					java.util.Date dataUtil = new java.util.Date(fmt.parse(dt).getTime());
					dataInicio = new java.sql.Date(dataUtil.getTime());
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				System.out.println("Informe a data de fim do Relatório: ");
		        dt = sc2.next();
		        
		        try {
					java.util.Date dataUtil = new java.util.Date(fmt.parse(dt).getTime());
					dataFim = new java.sql.Date(dataUtil.getTime());
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
		        ArrayList<VendaRelatorio> relatorio = new ArrayList<VendaRelatorio>();
		        relatorio = (ArrayList<VendaRelatorio>) gerarRelatorio(dataInicio, dataFim);
		        
		        if(relatorio != null) {
		        	System.out.println("-----------------------------------------------------------------------------------------------------");
		        	System.out.println("                                        RELATÓRIO DE VENDAS                                          ");
		        	System.out.println("-----------------------------------------------------------------------------------------------------");
			        System.out.println("DATA INICIAL: " + dataInicio + "           DATA FINAL: " + dataFim);
			        System.out.println("-----------------------------------------------------------------------------------------------------");
			        System.out.println("VENDEDOR                     |     VENDAS TOTAL                 |     TOTAL COMISSAO                 ");
			        System.out.println("-----------------------------------------------------------------------------------------------------");
			        relatorio.forEach((v) -> {
	    			String vendedor = new String();
	    			String totalV = new String();
	    			
	    			vendedor = v.getVendedor();
	    			totalV = Float.toString(v.getTotal_venda());
	    			int l = 35 - vendedor.length();
	    			int c;
	    			for(c=l;c>0;c--) {
	    				if(c==6)
	    				{
	    					vendedor = vendedor +"|";
	    				}
	    				else {
	    					vendedor = vendedor +" ";
	    				}
	    				
	    			}
	    			l = 32 - totalV.length();
	    			for(c=l;c>0;c--) {
	    				if(c==6) {
	    					totalV = totalV +"|";
	    				}
	    				else {
	    					totalV = totalV +" ";
	    				}
	    			}
	    			System.out.println(vendedor+"R$ "+totalV+"R$ "+v.getTotal_comissao());
	    			System.out.println("-----------------------------------------------------------------------------------------------------");
		        	});
		        	System.out.println("\n");
		        }
		        else {
		        	System.out.println("Não há vendas no período informado!");
		        	this.menuVenda();
		        	break;
		        }
		        
				this.menuVenda();
		        break;
	        }
			
			case 0:{
				break;
			}
    	}
    }


    public Venda save(Venda v){
    	return VendaController.save(v);
    }

    public boolean update(int id, Venda v){
        return VendaController.update(id, v);
    }

    public boolean delete(int id){
    	return VendaController.delete(id);
    }
    
    public Automovel findAutomovel(int id) {
    	return VendaController.findAutomovel(id);
    }
    
    public Cliente findCliente(int id) {
    	return VendaController.findCliente(id);
    }
    
    public Funcionario findFuncionario(int id) {
    	return VendaController.findFuncionario(id);
    }

    public List<Venda> findAll(){
    	return VendaController.findAll();
    }

    public Venda findById(int id){
    	return VendaController.findById(id);
    }
    
    public ArrayList<VendaRelatorio> gerarRelatorio(java.sql.Date dtInicio, java.sql.Date dtFim){
    	return VendaController.gerarRelatorio(dtInicio, dtFim);
    }

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

}
