package co.com.futbol.controller;

import co.com.futbol.actores.Equipo;
import co.com.futbol.actores.Jugada;

public class Partido {

	private ArbolJugadas arbolJugadasA = null;
	private ArbolJugadas arbolJugadasB = null;
	private char quienJuega = 'A';
	private Jugada JugadaGenerica = null;
	private int tiempo = 0;

	public Partido() {
		
		
		// Inicializa equipo A
		String nombreEquipoA = "Colombia";
		int porteroA = 20, defensaA = 30, medioA = 40, ataqueA = 45, tirosArcoA = 20, pasesA = 15, contraGolpeA = 35;
		Equipo equipoA = new Equipo(nombreEquipoA, porteroA, defensaA, medioA, ataqueA, tirosArcoA, pasesA,
				contraGolpeA);

		// Inicializa equipo B
		String nombreEquipoB = "Peru";
		int porteroB = 30, defensaB = 40, medioB = 20, ataqueB = 35, tirosArcoB = 20, pasesB = 15, contraGolpeB = 35;
		Equipo equipoB = new Equipo(nombreEquipoB, porteroB, defensaB, medioB, ataqueB, tirosArcoB, pasesB,
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
		if (JugadaGenerica.isCambioEquipo()) 
			quienJuega = (quienJuega == 'A'?'B':'A');

		//Monitorea el tiempo de Juego (45 Minutos)
		if(!terminaTiempo())
			return enJuego();
		else 
			return false;
				
		
	}
	
	public boolean terminaTiempo() {
		tiempo++;
		
		if(tiempo < 100) 	
			return false;
		
		return true;		
	}

}

//EL ANTERIOR RECORDANDO

//RECURSIVIDAD
//HASHMAP
