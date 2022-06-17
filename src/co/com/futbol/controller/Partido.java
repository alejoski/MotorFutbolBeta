package co.com.futbol.controller;

import co.com.futbol.actores.Equipo;
import co.com.futbol.actores.Jugada;


public class Partido {
	
	private ArbolJugadas arbolJugadasA = null;
	private ArbolJugadas arbolJugadasB = null;
	
	public Partido() {
		
		
		
		
		String nombreEquipoA = "Colombia";
		int porteroA = 20, defensaA= 30, medioA = 40, ataqueA= 45, tirosArcoA = 20, pasesA = 15, contraGolpeA = 35;		
		Equipo equipoA = new Equipo(nombreEquipoA,porteroA, defensaA, medioA, ataqueA,tirosArcoA, pasesA, contraGolpeA);
		
		
		String nombreEquipoB = "Peru";
		int porteroB = 30, defensaB= 40, medioB = 20, ataqueB= 35, tirosArcoB = 20, pasesB = 15, contraGolpeB = 35;		
		Equipo equipoB = new Equipo(nombreEquipoB, porteroB, defensaB, medioB, ataqueB, tirosArcoB, pasesB, contraGolpeB);
		
		arbolJugadasA = new ArbolJugadas(equipoA);
		arbolJugadasB = new ArbolJugadas(equipoB);
		
		//TODO ACA SE PUEDE DECIDIR QUE EQUIPO INICIA EL PARTIDO
		Jugada jugadasA = arbolJugadasA.iniciarPartido();
		Jugada jugadasB = arbolJugadasB.iniciarPartido();
		
		
		int i=0;
		char quienJuega = 'A'; 
			do {
				System.err.println("+++" + jugadasA.getNombre());
				
				if(quienJuega == 'A' ) {
					jugadasA = jugadasA.siguienteJUgada();
				
				}else {
					jugadasB = jugadasB.siguienteJUgada();
				}
				
				
				i++;
			}while(i<100); 
	
		
	}

}
