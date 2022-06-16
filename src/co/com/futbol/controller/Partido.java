package co.com.futbol.controller;

import co.com.futbol.actores.Equipo;
import co.com.futbol.actores.Jugada;


public class Partido {
	
	private ArbolJugadas arbolJugadas = new ArbolJugadas();
	
	public Partido() {
		
		
		
		
		String nombreEquipoA = "Colombia";
		int porteroA = 20, defensaA= 30, medioA = 40, ataqueA= 45;
		
		Equipo equipoA = new Equipo(nombreEquipoA,porteroA, defensaA, medioA, ataqueA);
		
		
		String nombreEquipoB = "Peru";
		int porteroB = 30, defensaB= 40, medioB = 20, ataqueB= 35;
		
		Equipo equipoB = new Equipo(nombreEquipoB, porteroA, defensaA, medioA, ataqueA);
		
		Jugada jugada = arbolJugadas.iniciarPartido();
		
		int i=0;
			do {
				System.err.println("+++" + jugada.getNombre());
				jugada = jugada.siguienteJUgada();
				
				
				i++;
			}while(i<100); 
	
		
	}

}
