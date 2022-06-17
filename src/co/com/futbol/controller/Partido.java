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
		

		/**
		 * Numero Aleatorio 
		 * Si sale 0 Juega equip A
		 * Si sale 1 juewga equipo B
		 */		
		Jugada jugadasA = null;
		Jugada jugadasB = null;
		Jugada JugadaGenerica = null;
		
		jugadasA = arbolJugadasA.iniciarPartido();
		jugadasB = arbolJugadasB.iniciarPartido();
		
		if ((int) (Math.random() * 2) == 0) 
			JugadaGenerica = jugadasA;		
		else 
			JugadaGenerica = jugadasB;
			
		
		
		
		int i=0;
		
		char quienJuega = 'A'; 
		
			do {
				//JugadaGenerica.getNombre() ;
				
				if(quienJuega == 'A' ) {
					JugadaGenerica = arbolJugadasA.siguienteJugada(JugadaGenerica);
				
				}else {
					JugadaGenerica = arbolJugadasB.siguienteJugada(JugadaGenerica);
				}
				
				
				//Cambia de equipo
				if(JugadaGenerica.isCambioEquipo()) {	
					
					System.out.println("     ************(CAMBIO)*************    ");
					
					
					if(quienJuega == 'A') {
						quienJuega = 'B';
					}else {
						quienJuega = 'A';
					}
				}
				
				i++;
			}while(i<100); 
	
		
	}

}



//EL ANTERIOR RECORDANDO

//RECURSIVIDAD
//HASHMAP
