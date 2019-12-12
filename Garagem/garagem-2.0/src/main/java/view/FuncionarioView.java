package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.FuncionarioController;
import entity.Badeco;
import entity.Funcionario;
import entity.Gerente;
import entity.Pessoa;

public class FuncionarioView {

    FuncionarioController funcionarioController;
    
    ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();

	private Scanner sc;

	private Scanner c;

	private Scanner c2;

	private Scanner c3;

	private Scanner c4;

    public FuncionarioView() {
        this.funcionarioController = new FuncionarioController();
    }

    public void menuFuncionario(){
    	
    	int op = 0;
    	
    	System.out.println("========== Menu Funcionario ==========");
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
				System.out.println("========== Cadastrar Funcionario ==========");
				boolean x = false;
				Pessoa pessoa = new Pessoa();
				Funcionario f = null;
		        c = new Scanner(System.in);
		        
		        System.out.println("Informe o tipo de Funcion�rio:");
		        System.out.println("1 - Normal");
		        System.out.println("2 - Gerente");
		        System.out.println("3 - Badeco");
		        
		        int o = c.nextInt();
		        while(o<1 || o>3) {
		        	System.out.println("Entrado inv�lida, tente novamente...");
		        	o = c.nextInt();
		        }
		        
		        System.out.println("Informe o id da Pessoa:");
		        int id = c.nextInt();
		        pessoa = findPessoa(id);
		        if(pessoa == null) {
		        	System.out.println("Esta pessoa n�o existe!");
		        	this.menuFuncionario();
		        	break;
		        }
		        else {
		        	System.out.println("Pessoa: "+pessoa.getNome());
		        }
		        
		        switch(o) {
		        	case 1:{
		        		f = new Funcionario();
		        		f.setPessoa(findPessoa(id));
		        		
		        		System.out.println("Informe o nome de usu�rio do Funcion�rio:");
		        		f.setUsuario(c.next());
		        		
		        		System.out.println("Informe a senha do Funcion�rio:");
		        		f.setSenha(c.next());
		        		
		        		System.out.println("Informe o sal�rio do Funcion�rio:");
		        		float salario = c.nextFloat();
		        		salario = f.calculaSalario(salario);
		        		System.out.println("Salario Calculado: R$" + salario);
		        		
		        		boolean r = verificaSalarioFuncionario(salario);
		        		
		        		if(r == true) {
		        			System.out.println("Um Funcion�rio n�o pode ter o sal�rio maior que o de um Gerente!");
		        			x = true;
				        	this.menuFuncionario();
		        		}
		        		else {
		        			f.setSalario(salario);
		        		}
		        		
		        		break;		        		
		        	}
		        	
		        	case 2:{
		        		f = new Gerente();
		        		f.setPessoa(findPessoa(id));

		        		System.out.println("Informe o nome de usu�rio do Funcion�rio:");
		        		f.setUsuario(c.next());
		        		
		        		System.out.println("Informe a senha do Funcion�rio:");
		        		f.setSenha(c.next());
		        		
		        		System.out.println("Informe o sal�rio do Funcion�rio:");
		        		float salario = c.nextFloat();
		        		salario = f.calculaSalario(salario);
		        		System.out.println("Salario Calculado: R$" + salario);
		        		
		        		System.out.println("Informe o departamento do Funcion�rio:");
		        		((Gerente) f).setDepartamento(c.next());
		        		break;
		        	}
		        	
		        	case 3:{
		        		f = new Badeco();
		        		f.setPessoa(findPessoa(id));
		        		
		        		System.out.println("Informe o nome de usu�rio do Funcion�rio:");
		        		f.setUsuario(c.next());
		        		
		        		System.out.println("Informe a senha do Funcion�rio:");
		        		f.setSenha(c.next());
		        		
		        		System.out.println("Informe o sal�rio do Funcion�rio:");
		        		float salario = c.nextFloat();
		        		salario = f.calculaSalario(salario);
		        		System.out.println("Salario Calculado: R$" + salario);
		        		
		        		boolean r = verificaSalarioBadeco(salario);
		        		
		        		if(r == true) {
		        			System.out.println("Um Badeco n�o pode ter o sal�rio maior que o de um Funcion�rio!");
		        			x = true;
				        	this.menuFuncionario();
		        		}
		        		else {
		        			f.setSalario(salario);
		        		}
		        		
		        		System.out.println("Informe a fun��o do Funcion�rio:");
		        		((Badeco) f).setFuncao(c.next());
		        		break;
		        	}
		        }
		       
		        if(x == false) {
		        	Funcionario f2 = this.save(f);

			        if(f2 == null){
			            System.out.println("\n\nFuncion�rio n�o foi cadastrado!\n\n");
			        } else {
			            System.out.println("\n\nFuncion�rio cadastrado com sucesso!");
			        }
			        
			        this.menuFuncionario();
			        break;
		        }
		        break;
	        }
			
			case 2:{
				funcionarios = (ArrayList<Funcionario>) this.findAll();
		    	System.out.println("========== Listar Funcionario ==========");
		    	if(funcionarios != null) {
		    		funcionarios.forEach((f) -> {
		    			if(f.getId() < 10) {
		    				System.out.println("ID - 0"+f.getId());
		    			}else {
		    				System.out.println("ID - "+f.getId());
		    			}
			            System.out.println("NOME - "+f.getPessoa().getNome());
			            if(f instanceof Gerente) {
			            	System.out.println("CARGO - Gerente");
			            	System.out.println("DEPARTAMENTO - "+((Gerente) f).getDepartamento());
			            }
			            else if(f instanceof Badeco) {
			            	System.out.println("CARGO - Badeco");
			            	System.out.println("FUN��O - "+((Badeco) f).getFuncao());
			            }else {
			            	System.out.println("CARGO - Funcion�rio");
			            }
			            System.out.println("SAL�RIO - "+f.getSalario());
			            System.out.println("USU�RIO - "+f.getUsuario());
			            System.out.println("SENHA - "+f.getSenha());
			            System.out.println("----------------------------------");
		    		});
		    	}else {
		    		System.out.println("N�o h� funcionarios cadastrados!");
		    	}
		    	System.out.println("\n");
		    	
				this.menuFuncionario();
		        break;
	        }
			
			case 3:{
				System.out.println("========== Buscar Funcionario ==========");
				c2 = new Scanner(System.in);
				System.out.println("Informe o id do Funcionario:");
		        int n = c2.nextInt();
		        Funcionario f = new Funcionario();
				f = this.findById(n);
				if(f != null) {
					if(f.getId() < 10) {
	    				System.out.println("ID - 0"+f.getId());
	    			}else {
	    				System.out.println("ID - "+f.getId());
	    			}
		            System.out.println("NOME - "+f.getPessoa().getNome());
		            if(f instanceof Gerente) {
		            	System.out.println("CARGO - Gerente");
		            	System.out.println("DEPARTAMENTO - "+((Gerente) f).getDepartamento());
		            }
		            else if(f instanceof Badeco) {
		            	System.out.println("CARGO - Badeco");
		            	System.out.println("FUN��O - "+((Badeco) f).getFuncao());
		            }else {
		            	System.out.println("CARGO - Funcion�rio");
		            }
		            System.out.println("SAL�RIO - "+f.getSalario());
		            System.out.println("USU�RIO - "+f.getUsuario());
		            System.out.println("SENHA - "+f.getSenha());
		            System.out.println("----------------------------------");
				}
				else {
					System.out.println("Este c�digo n�o existe!\n");
				}
				System.out.println("\n");
				this.menuFuncionario();
		        break;
	        }
			
			case 4:{
				System.out.println("========== Alterar Funcionario ==========");
				c3 = new Scanner(System.in);
				System.out.println("Informe o id do Funcionario:");
		        int n = c3.nextInt();
		        Funcionario f = this.findById(n);
		        if(f == null) {
		        	System.out.println("Este c�digo n�o existe!\n");
		        	this.menuFuncionario();
		        }
		        else {
		        	if(f.getId() < 10) {
	    				System.out.println("ID - 0"+f.getId());
	    			}else {
	    				System.out.println("ID - "+f.getId());
	    			}
		            System.out.println("NOME - "+f.getPessoa().getNome());
		            if(f instanceof Gerente) {
		            	System.out.println("CARGO - Gerente");
		            	System.out.println("DEPARTAMENTO - "+((Gerente) f).getDepartamento());
		            }
		            else if(f instanceof Badeco) {
		            	System.out.println("CARGO - Badeco");
		            	System.out.println("FUN��O - "+((Badeco) f).getFuncao());
		            }else {
		            	System.out.println("CARGO - Funcion�rio");
		            }
		            System.out.println("SAL�RIO - "+f.getSalario());
		            System.out.println("USU�RIO - "+f.getUsuario());
		            System.out.println("SENHA - "+f.getSenha());
		            System.out.println("----------------------------------");
		        }
		        
		        boolean x = false;
		        
		        System.out.println("Informe o novo nome de usu�rio do Funcion�rio:");
        		f.setUsuario(c3.next());
        		
        		System.out.println("Informe a nova senha do Funcion�rio:");
        		f.setSenha(c3.next());
        		
        		if(f instanceof Gerente) {
        			System.out.println("Informe o sal�rio do Funcion�rio:");
	        		float salario = c3.nextFloat();
	        		salario = f.calculaSalario(salario);
	        		System.out.println("Salario Calculado: R$" + salario);
        			f.setSalario(salario);

        			System.out.println("Informe o novo novo departamento do Funcion�rio:");
        			((Gerente)f).setDepartamento(c3.next());
	            }
        		else if(f instanceof Badeco) {
        			System.out.println("Informe o novo sal�rio do Funcion�rio:");
        			float salario = c3.nextFloat();
	        		salario = f.calculaSalario(salario);
	        		System.out.println("Salario Calculado: R$" + salario);
	        		
	        		boolean r = verificaSalarioBadeco(salario);
	        		
	        		if(r == true) {
	        			System.out.println("Um Badeco n�o pode ter o sal�rio maior que o de um Funcion�rio!");
	        			x = true;
	        			this.menuFuncionario();
			        	break;
	        		}
	        		else {
	        			f.setSalario(salario);
	        		}
        			System.out.println("Informe a nova fun��o do Funcion�rio:");
        			((Badeco)f).setFuncao(c3.next());
	            }
        		else {
            		System.out.println("Informe o novo sal�rio do Funcion�rio:");
            		float salario = c3.nextFloat();
	        		salario = f.calculaSalario(salario);
	        		System.out.println("Salario Calculado: R$" + salario);
	        		
	        		boolean r = verificaSalarioFuncionario(salario);
	        		
	        		if(r == true) {
	        			x = true;
	        			System.out.println("Um Funcion�rio n�o pode ter o sal�rio maior que o de um Gerente!");
			        	this.menuFuncionario();
			        	break;
	        		}
	        		else {
	        			f.setSalario(salario);
	        		}	        		
        		}
        		
        		if(x == false) {
        			boolean r = this.update(n, f);
    		        if(r == true) {
    		        	System.out.println("Funcionario alterado com sucesso!");
    		        	this.menuFuncionario();
    			        break;
    		        }
    		        else {
    		        	System.out.println("O funcionario n�o foi alterado!");
    		        	this.menuFuncionario();
    			        break;
    		        }
        		}
        		break;
	        }
			
			case 5:{
				System.out.println("========== Excluir Funcionario ==========");
				c4 = new Scanner(System.in);
				System.out.println("Informe o id do Funcionario:");
		        int n = c4.nextInt();
		        boolean r = this.delete(n);
		        if(r == true) {
		        	System.out.println("Funcionario exclu�do com sucesso!");
		        }
		        else {
		        	System.out.println("O funcionario n�o foi exclu�do ou n�o existe!");
		        }
		        
				this.menuFuncionario();
		        break;
	        }
			
			case 0:{
				break;
				
			}
    	}
    }


    public Funcionario save(Funcionario m){
    	return funcionarioController.save(m);
    }
    
    public Pessoa findPessoa(int id) {
    	return funcionarioController.findPessoa(id);
    }

    public boolean update(int id, Funcionario m){
        return funcionarioController.update(id, m);
    }

    public boolean delete(int id){
    	return funcionarioController.delete(id);
    }

    public List<Funcionario> findAll(){
    	return funcionarioController.findAll();
    }

    public Funcionario findById(int id){
    	return funcionarioController.findById(id);
    }
    
    public boolean verificaSalarioFuncionario(float salario){
    	return funcionarioController.verificaSalarioFuncionario(salario);
    }
    
    public boolean verificaSalarioBadeco(float salario){
    	return funcionarioController.verificaSalarioBadeco(salario);
    }

}
