package estacionamento;
import static java.time.temporal.ChronoUnit.SECONDS;

import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import estacionamento.entidade.Carro;
import estacionamento.estadia.Estadia;

public class Estacionamento {
	public static Map < String, Estadia > estadias = new HashMap<String, Estadia>();

	public static void main(String[] args) {
		
		System.out.println("");
		System.out.println("------------------------------------------------------------------------------------------------------------");
		System.out.println("                                BEM VINDO AO SISTEMA DE ESTACIONAMENTO                                       ");
		System.out.println("------------------------------------------------------------------------------------------------------------");
		System.out.println("");

		
		programa();
		
	}

	private static void programa() {
		Scanner scanner = new Scanner(System.in);
		
		int opcao=0;
		do {
		
			System.out.println("Escolha uma opcao: [1] Entrada | [2] Saida [3] Permanencia [4] Qtd de carros [5] Remover [6] Listar [0] Sair");
			
			try {

				opcao = scanner.nextInt();
			
			}	catch (Exception e) {
			
				erro();
			}
			
			if(opcao==1) {
				
				Estadia estadia = new Estadia();
				Carro carro = new Carro();
				
				estadia.setEntrada(LocalTime.now());
				System.out.println("Digite uma Placa");
				carro.setPlaca(scanner.next());
				System.out.println(carro.getPlaca());
				
				System.out.println("Digite um Modelo");
				carro.setModelo(scanner.next());
				System.out.println(carro.getModelo());
				
				System.out.println(estadia.getEntrada());
				estadia.setCarro(carro);
				estadias.put(carro.getPlaca(), estadia); 
				
			}else if(opcao==2) {
				
				System.out.println("Digite uma Placa");
				
				String placaDaSaida = scanner.next(); 
				
				if( estadias.get(placaDaSaida) != null) { 

					Estadia estadiaDaSaida = estadias.get(placaDaSaida);
					estadiaDaSaida.setSaida(LocalTime.now());

					estadias.put(placaDaSaida, estadiaDaSaida); 
					System.out.println(estadiaDaSaida.getSaida());	
				
				} else {
					
					System.out.println("Placa não encontrada");
					
				}
				
			}else if (opcao==3) {
				
				System.out.println("Digite uma Placa");
				String placaParaConsulta = scanner.next();
				Estadia estadiaDeConsulta = estadias.get(placaParaConsulta);
				try {
					
					System.out.println("Permanencia " + calculaPermanencia(estadiaDeConsulta));
				
				} catch (Exception e) {
				
					System.out.println("Não existe registro de entrada/saída para a placa informada");
					programa();
					opcao=0;
				}
				
			}else if (opcao==4) {
				
				int quantidade = 0;
				for ( Map.Entry<String, Estadia> entry : estadias.entrySet() ) {
					if(entry.getValue().getSaida() == null ) {
						quantidade++;
					}
				}
				
				System.out.println("Você tem " + quantidade + " carro(s) estacionados");
				
			}else if(opcao==5) {
				
				System.out.println("Digite uma Placa");
				String placa = scanner.next();
				estadias.remove(placa);
				
			}else if(opcao==6) {
			
				for ( Map.Entry<String, Estadia> entry : estadias.entrySet() ) {
					StringBuilder mensagem = new StringBuilder();
					mensagem.append("O Carro Placa: [ " + entry.getKey());
					mensagem.append(" ] Modelo: [ " + entry.getValue().getCarro().getModelo());
					mensagem.append(" ] Estacionou: [ " + entry.getValue().getEntrada() + "] ");
					
					if(entry.getValue().getSaida()!=null) {
						mensagem.append("Até: [ " + entry.getValue().getSaida() + " ] ");
						mensagem.append(" A Permanência Foi De: [ " + calculaPermanencia(entry.getValue()) + " ] ");
					}
										
					System.out.println(mensagem);
				}
				
			} else if (opcao==7) {
				
								
			
				for ( Map.Entry<String, Estadia> entry : estadias.entrySet() ) {
					System.out.println(entry.getValue().getCarro().getPlaca() + " / " + " / " + entry.getValue().getCarro().getModelo() );
				}
			}
			
		}while(opcao!=0);
		scanner.close();
	}

	private static long calculaPermanencia(Estadia estadiaDeConsulta) {
		return estadiaDeConsulta.getEntrada().until(estadiaDeConsulta.getSaida(), SECONDS);
	}

	private static void erro() {
		System.out.println("Opcao invalida");
		programa();
	}
}
