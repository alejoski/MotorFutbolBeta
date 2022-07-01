package co.com.futbol.controller;

import java.time.Duration;

import co.com.futbol.actores.Equipo;
import co.com.futbol.actores.Jugada;

public class Partido {

	private ArbolJugadas arbolJugadasA = null;
	private ArbolJugadas arbolJugadasB = null;
	private char quienJuega = 'A';
	private Jugada JugadaGenerica = null;
	private int tiempo = 0;
	private int GolesA = 0;
	private int GolesB = 0;
	Equipo equipoA = null;
	Equipo equipoB = null;
	
	Duration duration = Duration.ofMinutes( 0L );
	Duration durationStandar = Duration.ofMinutes( 45L );
	

	public Partido() {
		

		
		
		
		
		// Inicializa equipo A
		String nombreEquipoA = "Colombia";
		int porteroA = 40, defensaA = 55, medioA = 45, ataqueA = 56, tirosArcoA = 40, pasesA = 100, contraGolpeA = 40;
		equipoA = new Equipo(nombreEquipoA, porteroA, defensaA, medioA, ataqueA, tirosArcoA, pasesA,
				contraGolpeA);

		// Inicializa equipo B
		String nombreEquipoB = "Peru";
		int porteroB = 30, defensaB = 40, medioB = 20, ataqueB = 35, tirosArcoB = 20, pasesB = 15, contraGolpeB = 35;
		equipoB = new Equipo(nombreEquipoB, porteroB, defensaB, medioB, ataqueB, tirosArcoB, pasesB,
				contraGolpeB);

		arbolJugadasA = new ArbolJugadas(equipoA);
		arbolJugadasB = new ArbolJugadas(equipoB);

		// TODO ACA SE PUEDE DECIDIR QUE EQUIPO INICIA EL PARTIDO

		/**
		 * Numero Aleatorio Si sale 0 Juega equip A Si sale 1 juewga equipo B
		 */
		Jugada jugadasA = arbolJugadasA.iniciarPartido();
		Jugada jugadasB = arbolJugadasB.iniciarPartido();

		//Seleccion aleatorea de quien inicia el partido, simula el lanzamiento de moneda
		if ((int) (Math.random() * 2) == 0)
			JugadaGenerica = jugadasA;
		else
			JugadaGenerica = jugadasB;
		
		enJuego();
	}
	
	public boolean enJuego() {
		
		//Determina quien esta jugando en ese momento
		if (quienJuega == 'A') 
			JugadaGenerica = arbolJugadasA.siguienteJugada(JugadaGenerica);
		 else 
			JugadaGenerica = arbolJugadasB.siguienteJugada(JugadaGenerica);
		
		// Cuando una Jugada implica cambio de equipo, cambia el arbol de jugadas
		if (JugadaGenerica.isCambioEquipo()) { 
			
			if(JugadaGenerica.getNombre().equals("Gol")) {
				
				if(quienJuega == 'A') {
					//Aumenta cuenta de goles
					GolesA += 1;
					//Cambia de posesion el balon
					quienJuega = 'B';
				
				}else if (quienJuega == 'B') {
					GolesB += 1;
					
				}
					
				
			}
			
			quienJuega = (quienJuega == 'A'?'B':'A');
		
			
		}

		//Monitorea el tiempo de Juego (45 Minutos)
		aumentaDuracion(JugadaGenerica.getDuracionJugada());
		
		if(!terminaTiempo())
			return enJuego();
		else {
			System.out.println(equipoA.getNombre() + " "+ GolesA + " "+ equipoB.getNombre() + " "+ GolesB);
			return false;
		}
				
		
	}
	
	public boolean terminaTiempo() {
		if(duration.getSeconds() < durationStandar.getSeconds()) 	
			return false;
		
		return true;		
	}
	
	
	private void aumentaDuracion(Long t) {
		
		duration = duration.plusSeconds(t);		
		
		System.out.println("{{{{Tiempo}}}} " + duration.toMinutes());	
		
	}

}

//EL ANTERIOR RECORDANDO

//RECURSIVIDAD
//HASHMAP
