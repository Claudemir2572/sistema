package estacionamento;

import java.io.IOException;

import estacionamento.gerenciamento.Gerenciamento;
import estacionamento.repository.Repositorio;

public class Estacionamento extends Repositorio {
	
	public static void main(String[] args) throws IOException {
		le();
		header();
		menu();
	}
		
	public static void menu() throws IOException {	
		do {
			System.out.println("Escolha uma opcao: [1] Entrada | [2] Saida [3] Permanencia [4] Qtd de carros [5] Remover [6] Listar [0] Sair");
			try {
				opcao = scanner.nextInt();
			}	catch (Exception e) {
				erro();
			}
			
			if (opcao==1) {
				Gerenciamento.setEntrada();
			} else if (opcao==2) {
				Gerenciamento.setSaida();
			} else if (opcao==3) {
				Gerenciamento.getPermanencia();
			} else if (opcao==4) {
				Gerenciamento.getQuantidade();
			} else if (opcao==5) {
				Gerenciamento.removeEstadia();
			} else if (opcao==6) {
				Gerenciamento.listEstadia();
			} else if (opcao==7) {
				Gerenciamento.grava();
			} else if (opcao==8) {
				Gerenciamento.le();
			} else if (opcao==99) {
				Gerenciamento.debug();
			}
			
		}while(opcao!=0);
		scanner.close();
	}
	
	
	private static void erro() throws IOException {
		System.out.println("Opcao invalida");
		menu();
	}
	
	private static void header() {
		System.out.println("");
		System.out.println("------------------------------------------------------------------------------------------------------------");
		System.out.println("                                BEM VINDO AO SISTEMA DE ESTACIONAMENTO                                       ");
		System.out.println("------------------------------------------------------------------------------------------------------------");
		System.out.println("");
	}
	
	
}
