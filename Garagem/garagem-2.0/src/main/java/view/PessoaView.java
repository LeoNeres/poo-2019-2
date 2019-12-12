package view;

import controller.PessoaController;
import entity.Pessoa;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PessoaView {

    PessoaController pessoaController;
    
    ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

	private Scanner sc;

	private Scanner c;

	private Scanner c2;

	private Scanner d;

	private Scanner c3;

	private Scanner c4;

    public PessoaView() {
        this.pessoaController = new PessoaController();
    }

    public void menuPessoa(){
    	
    	int op = 0;
    	
    	System.out.println("========== Menu Pessoa ==========");
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
				System.out.println("========== Cadastrar Pessoa ==========");
		        Pessoa p = new Pessoa();
		        c = new Scanner(System.in);
		        
		        String result;
		        
		        System.out.println("Informe o nome da Pessoa:");
		        result = c.nextLine();
		        p.setNome(result);
		        
		        System.out.println("Informe o cpf da Pessoa:");
		        result = c.nextLine();
		        p.setCpf(result);
		        
		        System.out.println("Informe o endereco da Pessoa:");
		        result = c.nextLine();
		        p.setEndereco(result);
		        
		        System.out.println("Informe o telefone da Pessoa:");
		        result = c.nextLine();
		        p.setTelefone(result);
		        
		        System.out.println("Informe a data de nascimento da Pessoa:");
		        result = c.next();
		        DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		        
				try {
					java.util.Date dataUtil = new java.util.Date(fmt.parse(result).getTime());
					java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
					//data = new java.sql.Date(fmt.parse(dt).getTime());
					p.setDt_nascimento(dataSql);
				} catch (ParseException e) {
					e.printStackTrace();
				}
		        
		        Pessoa p2 = save(p);

		        if(p2 == null){
		            System.out.println("\n\nPessoa não foi cadastrada!\n\n");
		        } else {
		            System.out.println("\n\nPessoa cadastrada com sucesso!");
		        }
		        
		        this.menuPessoa();
		        break;
	        }
			
			case 2:{
				pessoas = (ArrayList<Pessoa>) this.findAll();
		    	System.out.println("========== Listar Pessoa ==========");
		    	if(pessoas != null) {
		    		pessoas.forEach((p) -> {
		    			if(p.getId() < 10) {
		    				System.out.println("ID - 0"+p.getId());
		    			}else {
		    				System.out.println("ID - "+p.getId());
		    			}
			            System.out.println("NOME - "+p.getNome());
			            System.out.println("CPF - "+p.getCpf());
			            System.out.println("ENDEREÇO - "+p.getEndereco());
			            System.out.println("TELEFONE - "+p.getTelefone());
			            System.out.println("DATA DE NASCIMENTO - "+p.getDt_nascimento());
			            System.out.println("----------------------------------");
		    		});
		    	}else {
		    		System.out.println("Não há pessoas cadastradas!");
		    	}
		    	System.out.println("\n");
		    	
				this.menuPessoa();
		        break;
	        }
			
			case 3:{
				System.out.println("========== Buscar Pessoa ==========");
				c2 = new Scanner(System.in);
				System.out.println("Informe o id da Pessoa:");
		        int n = c2.nextInt();
		        Pessoa p = new Pessoa();
				p = this.findById(n);
				if(p != null) {
					if(p.getId() < 10) {
	    				System.out.println("ID - 0"+p.getId());
	    			}else {
	    				System.out.println("ID - "+p.getId());
	    			}
					System.out.println("NOME - "+p.getNome());
		            System.out.println("CPF - "+p.getCpf());
		            System.out.println("ENDEREÇO - "+p.getEndereco());
		            System.out.println("TELEFONE - "+p.getTelefone());
		            System.out.println("DATA DE NASCIMENTO - "+p.getDt_nascimento());
				}
				else {
					System.out.println("Este código não existe!\n");
				}
				System.out.println("\n");
				this.menuPessoa();
		        break;
	        }
			
			case 4:{
				System.out.println("========== Alterar Pessoa ==========");
				c3 = new Scanner(System.in);
				d = new Scanner(System.in);
				System.out.println("Informe o id da Pessoa:");
		        int n = c3.nextInt();
		        Pessoa p = this.findById(n);
		        if(p == null) {
		        	System.out.println("Este código não existe!\n");
		        	this.menuPessoa();
		        }
		        else {
		        	if(p.getId() < 10) {
	    				System.out.println("ID - 0"+p.getId());
	    			}else {
	    				System.out.println("ID - "+p.getId());
	    			}
		        	System.out.println("NOME - "+p.getNome());
		            System.out.println("CPF - "+p.getCpf());
		            System.out.println("ENDEREÇO - "+p.getEndereco());
		            System.out.println("TELEFONE - "+p.getTelefone());
		            System.out.println("DATA DE NASCIMENTO - "+p.getDt_nascimento());
		        }
		        
		        String result;
		        
		        System.out.println("Informe o novo nome da Pessoa:");
		        result = c3.next();
		        p.setNome(result);
		        
		        System.out.println("Informe o novo cpf da Pessoa:");
		        result = c3.next();
		        p.setCpf(result);
		        
		        System.out.println("Informe o novo endereco da Pessoa:");
		        result = d.nextLine();
		        p.setEndereco(result);
		        
		        System.out.println("Informe a nova data de nascimento da Pessoa:");
		        result = c3.next();
		        DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		        
				try {
					java.util.Date dataUtil = new java.util.Date(fmt.parse(result).getTime());
					java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
					//data = new java.sql.Date(fmt.parse(dt).getTime());
					p.setDt_nascimento(dataSql);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				System.out.println("Informe o novo telefone da Pessoa:");
		        p.setTelefone(d.nextLine());

		        boolean r = this.update(n, p);
		        if(r == true) {
		        	System.out.println("Pessoa alterada com sucesso!");
		        }
		        else {
		        	System.out.println("A pessoa não foi alterada ou não existe!");
		        }
		        
		        this.menuPessoa();
		        break;
	        }
			
			case 5:{
				System.out.println("========== Excluir Pessoa ==========");
				c4 = new Scanner(System.in);
				System.out.println("Informe o id da Pessoa:");
		        int n = c4.nextInt();
		        boolean r = this.delete(n);
		        if(r == true) {
		        	System.out.println("Pessoa excluída com sucesso!");
		        }
		        else {
		        	System.out.println("A pessoa não foi excluída ou não existe!");
		        }
		        
				this.menuPessoa();
		        break;
	        }
			
			case 0:{
				break;
				
			}
    	}
    }


    public Pessoa save(Pessoa p){
    	return pessoaController.save(p);
    }

    public boolean update(int id, Pessoa p){
        return pessoaController.update(id, p);
    }

    public boolean delete(int id){
    	return pessoaController.delete(id);
    }

    public List<Pessoa> findAll(){
    	return pessoaController.findAll();
    }

    public Pessoa findById(int id){
    	return pessoaController.findById(id);
    }

}
