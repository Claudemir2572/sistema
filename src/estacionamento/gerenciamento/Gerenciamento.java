package estacionamento.gerenciamento;

import static java.time.temporal.ChronoUnit.SECONDS;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Map;

import estacionamento.entidade.Carro;
import estacionamento.estadia.Estadia;
import estacionamento.repository.Repositorio;

public class Gerenciamento extends Repositorio {

	public static void setEntrada() throws IOException {

		Estadia estadia = new Estadia();
		Carro carro = new Carro();

		System.out.println("Digite uma Placa");
		String placa = scanner.next();
		if (estadias.get(placa) != null && estadias.get(placa).getSaida() == null) {
			System.out.println("Carro já está estacionado");
		} else {
			carro.setPlaca(placa);
			System.out.println("Digite um Modelo");
			carro.setModelo(scanner.next());
			estadia.setEntrada(LocalTime.now());
			System.out.println(estadia.getEntrada());
			estadia.setCarro(carro);
			estadias.put(carro.getPlaca(), estadia);
			grava();

		}
	}

	public static void setSaida() throws IOException {
		System.out.println("Digite uma Placa");
		String placaDaSaida = scanner.next();
		if (estadias.get(placaDaSaida) != null) {
			Estadia estadiaDaSaida = estadias.get(placaDaSaida);
			estadiaDaSaida.setSaida(LocalTime.now());
			estadias.put(placaDaSaida, estadiaDaSaida);
			System.out.println(estadiaDaSaida.getSaida());
		} else {
			System.out.println("Placa não encontrada");
		}
		grava();
	}

	public static void getPermanencia() throws IOException {
		System.out.println("Digite uma Placa");
		String placaParaConsulta = scanner.next();
		Estadia estadiaDeConsulta = estadias.get(placaParaConsulta);
		try {
			System.out.println("Permanencia " + calculaPermanencia(estadiaDeConsulta));
		} catch (Exception e) {
			System.out.println("Não existe registro de entrada/saída para a placa informada");

		}
	}

	public static void getQuantidade() throws IOException {

		int quantidade = 0;
		for (Map.Entry<String, Estadia> entry : estadias.entrySet()) {
			if (entry.getValue().getSaida() == null && !entry.getValue().isRemovido()) {
				quantidade++;
			}
		}
		System.out.println("Você tem " + quantidade + " carro(s) estacionados");
	}

	public static void removeEstadia() throws IOException {

		System.out.println("Digite uma Placa");
		String placa = scanner.next();
		if (estadias.get(placa) != null) {
			Estadia estadia = estadias.get(placa);
			estadia.setRemovido(true);
			estadias.put(placa, estadia);
		} else {
			System.out.println("Placa não encontrada");
		}
		grava();
	}

	public static void listEstadia() throws IOException {
		for (Map.Entry<String, Estadia> entry : estadias.entrySet()) {

			if (!entry.getValue().isRemovido()) {
				StringBuilder mensagem = new StringBuilder();
				mensagem.append("O Carro Placa: [ " + entry.getKey());
				mensagem.append(" ] Modelo: [ " + entry.getValue().getCarro().getModelo());
				mensagem.append(" ] Estacionou: [ " + entry.getValue().getEntrada() + "] ");

				if (entry.getValue().getSaida() != null) {
					mensagem.append("Até: [ " + entry.getValue().getSaida() + " ] ");
					mensagem.append(" A Permanência Foi De: [ " + calculaPermanencia(entry.getValue()) + " ] ");
				}
				System.out.println(mensagem);
			}
		}
	}

	public static void debug() throws IOException {

		for (Map.Entry<String, Estadia> entry : estadias.entrySet()) {
			System.out.println("Placa: [ " + entry.getKey() + " ] - " + entry.getValue().toString());
		}
	}

	private static long calculaPermanencia(Estadia estadiaDeConsulta) throws IOException {
		return estadiaDeConsulta.getEntrada().until(estadiaDeConsulta.getSaida(), SECONDS);
	}

}
