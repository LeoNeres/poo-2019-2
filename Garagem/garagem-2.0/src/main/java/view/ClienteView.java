package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.ClienteController;
import entity.Cliente;
import entity.Pessoa;

public class ClienteView {

    ClienteController ClienteController;
    
    ArrayList<Cliente> clientes = new ArrayList<Cliente>();

	private Scanner sc;

	private Scanner sc2;

	private Scanner sc22;

	private Scanner sc23;

	private Scanner c;

    public ClienteView() {
        this.ClienteController = new ClienteController();
    }

    public void menuCliente(){
    	
    	int op = 0;
    	
    	System.out.println("========== Menu Cliente ==========");
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
				System.out.println("========== Cadastrar Cliente ==========");
		        Cliente c = new Cliente();
		        sc2 = new Scanner(System.in);
		        
		        System.out.println("Informe o id da Pessoa deste Cliente:");
		        Pessoa pessoa = findPessoa(sc2.nextInt());
		        
		        if(pessoa == null) {
		        	System.out.println("Esta pessoa não existe!");
		        	this.menuCliente();
		        	break;
		        }
		        else {
		        	System.out.println("Pessoa: "+pessoa.getNome());
		        }
		        
		        c.setPessoa(pessoa);
		        
		        System.out.println("Informe o código do Cliente:");
		        c.setCodigo(sc2.nextInt());
		        
		        Cliente c2 = this.save(c);

		        if(c2 == null){
		            System.out.println("\n\nCliente não foi cadastrado!\n\n");
		        } else {
		            System.out.println("\n\nCliente cadastrado com sucesso!");
		        }
		        
		        this.menuCliente();
		        break;
	        }
			
			case 2:{
		    	System.out.println("========== Listar Cliente ==========");
		    	clientes = (ArrayList<Cliente>) this.findAll();
		    	if(clientes != null) {
		    		clientes.forEach((c) -> {
		    			if(c.getId() < 10) {
		    				System.out.println("ID - 0"+c.getId());
		    			}else {
		    				System.out.println("ID - "+c.getId());
		    			}
			            System.out.println("NOME - "+c.getPessoa().getNome());
			            System.out.println("CODIGO - "+c.getCodigo());
			            System.out.println("----------------------------------");
		    		});
		    	}else {
		    		System.out.println("Não há clientes cadastrados!");
		    	}
		    	System.out.println("\n");
		    	
				this.menuCliente();
		        break;
	        }
			
			case 3:{
				System.out.println("========== Buscar Cliente ==========");
				sc22 = new Scanner(System.in);
				System.out.println("Informe o id do Cliente:");
		        int n = sc22.nextInt();
		        Cliente c = new Cliente();
				c = this.findById(n);
				if(c != null) {
					if(c.getId() < 10) {
	    				System.out.println("ID - 0"+c.getId());
	    			}else {
	    				System.out.println("ID - "+c.getId());
	    			}
					System.out.println("NOME - "+c.getPessoa().getNome());
		            System.out.println("CODIGO - "+c.getCodigo());
				}
				else {
					System.out.println("Este id não existe!\n");
				}
				System.out.println("\n");
				this.menuCliente();
		        break;
	        }
			
			case 4:{
				System.out.println("========== Alterar Cliente ==========");
				sc23 = new Scanner(System.in);
				System.out.println("Informe o id do Cliente:");
		        int n = sc23.nextInt();
		        Cliente c = this.findById(n);
		        if(c == null) {
		        	System.out.println("Este id não existe!\n");
		        	this.menuCliente();
		        	break;
		        }
		        else {
		        	if(c.getId() < 10) {
	    				System.out.println("ID - 0"+c.getId());
	    			}else {
	    				System.out.println("ID - "+c.getId());
	    			}
		        	System.out.println("NOME - "+c.getPessoa().getNome());
		            System.out.println("CODIGO - "+c.getCodigo());
		        }
		        
		        System.out.println("\nInforme o novo código do Cliente:");
		        c.setCodigo(sc23.nextInt());

		        boolean r = this.update(n, c);
		        if(r == true) {
		        	System.out.println("Cliente alterado com sucesso!");
		        }
		        else {
		        	System.out.println("O Cliente não foi alterado!");
		        }
		        
		        this.menuCliente();
		        break;
	        }
			
			case 5:{
				System.out.println("========== Excluir Cliente ==========");
				c = new Scanner(System.in);
				System.out.println("Informe o id do Cliente:");
		        int n = c.nextInt();
		        boolean r = this.delete(n);
		        if(r == true) {
		        	System.out.println("Cliente excluído com sucesso!");
		        }
		        else {
		        	System.out.println("O Cliente não foi excluído ou não existe!");
		        }
		        
				this.menuCliente();
		        break;
	        }
			
			case 0:{
				break;
				
			}
    	}
    }


    public Cliente save(Cliente c){
    	return ClienteController.save(c);
    }

    public boolean update(int id, Cliente c){
        return ClienteController.update(id, c);
    }

    public boolean delete(int id){
    	return ClienteController.delete(id);
    }
    
    public Pessoa findPessoa(int id) {
    	return ClienteController.findPessoa(id);
    }

    public List<Cliente> findAll(){
    	return ClienteController.findAll();
    }

    public Cliente findById(int id){
    	return ClienteController.findById(id);
    }

}
