// Leonardo Neres Gonçalves
import java.util.Scanner;

import view.AutomovelView;
import view.ClienteView;
import view.FuncionarioView;
import view.MarcaView;
import view.ModeloView;
import view.PessoaView;
import view.VendaView;

public class Principal {
	
    private Scanner sc;

	public static void main(String[] args){
    	
        Principal p = new Principal();
        p.menuPrincipal();
        
    }

    public void menuPrincipal(){
    	
    	int op = 0;
    	System.out.println("Menu Garagem");
    	System.out.println("1 - Marca");
    	System.out.println("2 - Modelo");
    	System.out.println("3 - Automóvel");
    	System.out.println("4 - Pessoa");
    	System.out.println("5 - Funcionário");
    	System.out.println("6 - Cliente");
    	System.out.println("7 - Venda");
    	
    	System.out.println("0 - Sair");
    	
    	sc = new Scanner(System.in);
    	
    	op = sc.nextInt();
    	
    	
    	switch (op) {
    		
    		case 1:{
		        MarcaView mv = new MarcaView();
		        mv.menuMarca();
		        this.menuPrincipal();
	        }
    		
    		case 2:{
		        ModeloView mv = new ModeloView();
		        mv.menuModelo();
		        this.menuPrincipal();
	        }
    		
    		case 3:{
		        AutomovelView av = new AutomovelView();
		        av.menuAutomovel();
		        this.menuPrincipal();
	        }
    		
    		case 4:{
		        PessoaView pv = new PessoaView();
		        pv.menuPessoa();
		        this.menuPrincipal();
	        }

    		case 5:{
		        FuncionarioView fv = new FuncionarioView();
		        fv.menuFuncionario();
		        this.menuPrincipal();
	        }
    		
    		case 6:{
		        ClienteView cv = new ClienteView();
		        cv.menuCliente();
		        this.menuPrincipal();
	        }
    		
    		case 7:{
		        VendaView vv = new VendaView();
		        vv.menuVenda();
		        this.menuPrincipal();
	        }
    		
    		case 0: default:{
    			System.exit(0);
		        break;
	        }
    		
    	}
    }

}
