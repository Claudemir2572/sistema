package estacionamento.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import estacionamento.estadia.Estadia;

public class Repositorio {
	public static Scanner scanner = new Scanner(System.in);
	public static final String FILENAME = "banco-de-dados.json";
	public static Map<String, Estadia> estadias = new HashMap<String, Estadia>();
	public static int opcao = 0;

	public static void le() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File(FILENAME))));
		Map<String, Estadia> estadiasBD = new Gson().fromJson(bf.readLine(), new TypeToken<Map<String, Estadia>>() {
		}.getType());
		if (estadiasBD != null)
			estadias = estadiasBD;
		bf.close();
	}

	public static void grava() throws IOException {
		PrintWriter pw = new PrintWriter(FILENAME);
		pw.append(new Gson().toJson(estadias));
		pw.close();
	}
}
