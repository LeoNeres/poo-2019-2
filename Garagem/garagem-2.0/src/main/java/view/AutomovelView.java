package view;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.AutomovelController;
import entity.Automovel;
import entity.Modelo;

public class AutomovelView {

    AutomovelController AutomovelController;
    
    ArrayList<Automovel> automoveis = new ArrayList<Automovel>();

	private Scanner sc;

	private Scanner c;

	private Scanner c2;

	private Scanner c3;

	private Scanner c4;

    public AutomovelView() {
        this.AutomovelController = new AutomovelController();
    }

    public void menuAutomovel(){
    	
    	int op = 0;
    	
    	System.out.println("========== Menu Automovel ==========");
    	System.out.println("1 - Cadastrar");
    	System.out.println("2 - Listar");
    	System.out.println("3 - Buscar");
    	System.out.println("4 - Alterar");
    	System.out.println("5 - Excluir");
    	System.out.println("0 - Voltar");
    	
    	sc = new Scanner(System.in);
    	
    	op = sc.nextInt();
    	
    	switch (op) {
			
			case 1:{
				System.out.println("========== Cadastrar Automóvel ==========");
		        Automovel a = new Automovel();
		        c = new Scanner(System.in);
		        
		        System.out.println("Informe o id do Modelo deste Automóvel:");
		        
		        Modelo modelo = new Modelo();
		        modelo = this.findModelo(c.nextInt());
		        
		        if(modelo == null) {
		        	System.out.println("Este modelo não existe!");
		        	this.menuAutomovel();
		        	break;
		        }
		        else {
		        	System.out.println("Modelo: "+modelo.getNome());
		        	System.out.println("Marca: "+modelo.getMarca().getNome());
		        }
		        
		        a.setModelo(modelo);
		        
		        System.out.println("Informe a cor do Automóvel:");
		        a.setCor(c.next());
		        
		        System.out.println("Informe o ano de fabricação do Automóvel:");
		        String dt = new String();
		        dt = c.next();
				
		        DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		        
				try {
					java.util.Date dataUtil = new java.util.Date(fmt.parse(dt).getTime());
					java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
					//data = new java.sql.Date(fmt.parse(dt).getTime());
					a.setAno_fabricacao(dataSql);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				System.out.println("Informe o ano do modelo do Automóvel:");
				dt = c.next();
				try {
					java.util.Date dataUtil = new java.util.Date(fmt.parse(dt).getTime());
					dataUtil = new java.sql.Date(fmt.parse(dt).getTime());
					java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
					a.setAno_modelo(dataSql);
				} catch (ParseException e) {
					e.printStackTrace();
				}
		        
		        System.out.println("Informe o número do chassi do Automóvel:");
		        a.setChassi(c.next());
		        
		        System.out.println("Informe a quilometragem do Automóvel:");
		        a.setKm(c.next());
		        
		        System.out.println("Informe o valor do Automóvel:");
		        a.setValor(c.nextFloat());
		        
		        System.out.println("Informe a placa do Automóvel:");
		        a.setPlaca(c.next());
		        
		        Automovel a2 = this.save(a);

		        if(a2 == null){
		            System.out.println("\n\nAutomóvel não foi cadastrado!\n\n");
		        } else {
		            System.out.println("\n\nAutomóvel cadastrado com sucesso!");
		        }
		        
		        this.menuAutomovel();
		        break;
	        }
			
			case 2:{
		    	System.out.println("========== Listar Automovel ==========");
		    	automoveis = (ArrayList<Automovel>) this.findAll();
		    	if(automoveis != null) {
		    		automoveis.forEach((a) -> {
		    			if(a.getId() < 10) {
		    				System.out.println("ID - 0"+a.getId());
		    			}else {
		    				System.out.println("ID - "+a.getId());
		    			}
		    			System.out.println("MARCA - "+a.getModelo().getMarca().getNome());
			            System.out.println("MODELO - "+a.getModelo().getNome());
			            System.out.println("COR - "+a.getCor());
			            System.out.println("ANO DE FABRICAÇÃO - "+a.getAno_fabricacao());
			            System.out.println("ANO DO MODELO - "+a.getAno_modelo());
			            System.out.println("CHASSI - "+a.getChassi());
			            System.out.println("PLACA - "+a.getPlaca());
			            System.out.println("QUILOMETRAGEM - "+a.getKm());
			            System.out.println("VALOR - "+a.getValor());
			            System.out.println("----------------------------------");
		    		});
		    	}else {
		    		System.out.println("Não há automoveis cadastrados!");
		    	}
		    	System.out.println("\n");
		    	
				this.menuAutomovel();
		        break;
	        }
			
			case 3:{
				System.out.println("========== Buscar Automovel ==========");
				c2 = new Scanner(System.in);
				System.out.println("Informe o id do automóvel:");
		        int n = c2.nextInt();
		        Automovel a = new Automovel();
				a = this.findById(n);
				if(a != null) {
					if(a.getId() < 10) {
	    				System.out.println("ID - 0"+a.getId());
	    			}else {
	    				System.out.println("ID - "+a.getId());
	    			}
					System.out.println("MARCA - "+a.getModelo().getMarca().getNome());
		            System.out.println("MODELO - "+a.getModelo().getNome());
		            System.out.println("COR - "+a.getCor());
		            System.out.println("ANO DE FABRICAÇÃO - "+a.getAno_fabricacao());
		            System.out.println("ANO DO MODELO - "+a.getAno_modelo());
		            System.out.println("CHASSI - "+a.getChassi());
		            System.out.println("PLACA - "+a.getPlaca());
		            System.out.println("QUILOMETRAGEM - "+a.getKm());
		            System.out.println("VALOR - "+a.getValor());
				}
				else {
					System.out.println("Este código não existe!\n");
				}
				System.out.println("\n");
				this.menuAutomovel();
		        break;
	        }
			
			case 4:{
				System.out.println("========== Alterar Automovel ==========");
				c3 = new Scanner(System.in);
				System.out.println("Informe o id do Automovel:");
		        int n = c3.nextInt();
		        Automovel a = this.findById(n);
		        if(a == null) {
		        	System.out.println("Este código não existe!\n");
		        	this.menuAutomovel();
		        }
		        else {
		        	if(a.getId() < 10) {
	    				System.out.println("ID - 0"+a.getId());
	    			}else {
	    				System.out.println("ID - "+a.getId());
	    			}
		        	System.out.println("MARCA - "+a.getModelo().getMarca().getNome());
		            System.out.println("MODELO - "+a.getModelo().getNome());
		            System.out.println("COR - "+a.getCor());
		            System.out.println("ANO DE FABRICAÇÃO - "+a.getAno_fabricacao());
		            System.out.println("ANO DO MODELO - "+a.getAno_modelo());
		            System.out.println("CHASSI - "+a.getChassi());
		            System.out.println("PLACA - "+a.getPlaca());
		            System.out.println("QUILOMETRAGEM - "+a.getKm());
		            System.out.println("VALOR - "+a.getValor());
		        }
		        
		        System.out.println("Informe o id do novo Modelo deste Automóvel:");
		        
		        Modelo modelo = new Modelo();
		        modelo = this.findModelo(c3.nextInt());
		        
		        if(modelo == null) {
		        	System.out.println("Este modelo não existe!");
		        	this.menuAutomovel();
		        	break;
		        }
		        else {
		        	System.out.println("Modelo: "+modelo.getNome());
		        }
		        
		        a.setModelo(modelo);
		        
		        System.out.println("Informe a nova cor do Automóvel:");
		        a.setCor(c3.next());
		        
		        System.out.println("Informe o novo ano de fabricação do Automóvel:");
		        String dt = new String();
		        dt = c3.next();
				
		        DateFormat fmt = new SimpleDateFormat("yyyy");
		        java.sql.Date data;
				try {
					data = new java.sql.Date(fmt.parse(dt).getTime());
					a.setAno_fabricacao(data);
					System.out.println("Informe o novo ano do modelo do Automóvel:");
			        dt = c3.next();
			        data = new java.sql.Date(fmt.parse(dt).getTime());
			        a.setAno_fabricacao(data);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        System.out.println("Informe o novo número do chassi do Automóvel:");
		        a.setChassi(c3.next());
		               
		        System.out.println("Informe a nova quilometragem do Automóvel:");
		        a.setKm(c3.next());
		        
		        System.out.println("Informe o novo valor do Automóvel:");
		        a.setValor(c3.nextFloat());
		        
		        System.out.println("Informe a nova placa do Automóvel:");
		        a.setPlaca(c3.next());

		        boolean r = this.update(n, a);
		        if(r == true) {
		        	System.out.println("Automovel alterado com sucesso!");
		        }
		        else {
		        	System.out.println("O Automovel não foi alterado!");
		        }
		        
		        this.menuAutomovel();
		        break;
	        }
			
			case 5:{
				System.out.println("========== Excluir Automovel ==========");
				c4 = new Scanner(System.in);
				System.out.println("Informe o id da Automovel:");
		        int n = c4.nextInt();
		        boolean r = this.delete(n);
		        if(r == true) {
		        	System.out.println("Automovel excluído com sucesso!");
		        }
		        else {
		        	System.out.println("A Automovel não foi excluído ou não existe!");
		        }
		        
				this.menuAutomovel();
		        break;
	        }
			
			case 0:{
				break;
				
			}
    	}
    }


    public Automovel save(Automovel a){
    	return AutomovelController.save(a);
    }

    public boolean update(int id, Automovel a){
        return AutomovelController.update(id, a);
    }

    public boolean delete(int id){
    	return AutomovelController.delete(id);
    }
    
    public Modelo findModelo(int id) {
    	return AutomovelController.findModelo(id);
    }

    public List<Automovel> findAll(){
    	return AutomovelController.findAll();
    }

    public Automovel findById(int id){
    	return AutomovelController.findById(id);
    }
}