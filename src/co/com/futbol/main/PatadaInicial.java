package co.com.futbol.main;

import co.com.futbol.controller.Partido;

public class PatadaInicial {

	public static void main(String[] args) {

		inicia();

	}

	/*
	 * Punto de entrada al motor de jugadas 
	 * */
	
	private static void inicia() {

		new Partido();
	}

}
