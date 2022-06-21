package co.com.futbol.controller;

import java.util.ArrayList;

import co.com.futbol.actores.Equipo;
import co.com.futbol.actores.Jugada;


public class ArbolJugadas {

	Jugada inicio = new Jugada("<Inico>");
	
	Jugada cambioPosesion = new Jugada("Cambio Posesion");
	
	Jugada saquePortero  = new Jugada("El portero despeja el balon");
	
	Jugada gol           = new Jugada("Gol", true);

	Jugada palo          = new Jugada("Palazo!!!");

	Jugada PorFuera      = new Jugada("El tiro sale desviado", true);

	Jugada TiroAlArco    = new Jugada("Tiro al arco");

	Jugada paseAlPortero = new Jugada("Pasan el balon Al portero");
	
	Jugada paseAtras     = new Jugada("Pasan el balon hacia Atras");

	Jugada paseAdelante  = new Jugada("Pasan el balon hacia adelante");

	Jugada jugadaInicial = new Jugada("El Referí indica el inicio");
	
	Jugada saqueMitadCancha= new Jugada("El Referí reinica el partido");

/*
 * Se deben definir variables o algunmecanismo que diferencia si es un pase adelante en area contraria o aria propia
 * */
	
	
	Equipo equipo;
	int ATAQUE  ;
	int DEFENSA ;
	int MEDIO   ;
	int PORTERO ;
	int TIROS ;
	int PASES;
	int CONTRAGOLPE;
	String NOMBRE_EQUIPO;	
	

	public ArbolJugadas(Equipo equipo) {
		
		
		ATAQUE  = equipo.getAtaque();
		DEFENSA = equipo.getDefensa();
		MEDIO   = equipo.getMedio();
		PORTERO = equipo.getPortero();
		NOMBRE_EQUIPO = equipo.getNombre();
		TIROS = equipo.getEfectividadTiros();
		PASES = equipo.getEfectividadPases();
		CONTRAGOLPE = equipo.getEfectividadContragolpe();
		
		
		try {
			
			//Establece las alternativas de cada jugadas
			inicio.alteratives(jugadaInicial);
			jugadaInicial.alteratives(TiroAlArco, paseAlPortero, paseAtras, paseAdelante);	
			saqueMitadCancha.alteratives(TiroAlArco, paseAlPortero, paseAtras, paseAdelante);
			paseAdelante.alteratives(TiroAlArco, paseAlPortero, paseAtras, paseAdelante);
			paseAtras.alteratives(TiroAlArco, paseAlPortero, paseAtras, paseAdelante);
			paseAlPortero.alteratives(paseAdelante);
			TiroAlArco.alternatives(TIROS, gol, palo,PorFuera); // TODO CORREGIR
			PorFuera.alteratives(cambioPosesion);
			palo.alteratives(cambioPosesion);
			gol.alteratives(saqueMitadCancha);
			cambioPosesion.alteratives(saquePortero);
			saquePortero.alteratives(paseAdelante, TiroAlArco);
			
			
			jugadaInicial.alteratives(TiroAlArco.setProb(2), paseAlPortero.setProb(20), paseAtras.setProb(40), paseAdelante.setProb(38));	
			saqueMitadCancha.alteratives(TiroAlArco.setProb(2), paseAlPortero.setProb(20), paseAtras.setProb(40), paseAdelante.setProb(38));
			paseAdelante.alteratives(TiroAlArco.setProb(10), paseAlPortero.setProb(2), paseAtras.setProb(30), paseAdelante.setProb(53));
			paseAtras.alteratives(TiroAlArco.setProb(4), paseAlPortero.setProb(10), paseAtras.setProb(30), paseAdelante.setProb(56));
			paseAlPortero.alteratives(paseAdelante);
			TiroAlArco.alteratives(gol.setProb(20), palo.setProb(10),PorFuera.setProb(70));
			PorFuera.alteratives(cambioPosesion);
			palo.alteratives(cambioPosesion);
			gol.alteratives(saqueMitadCancha);
			cambioPosesion.alteratives(saquePortero);
			saquePortero.alteratives(paseAdelante.setProb(90), TiroAlArco.setProb(10));
		
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Se carga el arbol de jugadas");
	}
	
	public Jugada iniciarPartido() {	
		
		Jugada siguienteJ = inicio.siguienteJUgada();
		System.err.println(siguienteJ.getNombre());
		System.err.println(NOMBRE_EQUIPO);
		
		return siguienteJ;	
	}
	
	public Jugada siguienteJugada(Jugada jugada) {	
		
		Jugada siguienteJ = jugada.siguienteJUgada();
		System.err.println(siguienteJ.getNombre());
		System.err.println(NOMBRE_EQUIPO);
		
		return siguienteJ;	
	}
	
	
	
	//TODO [X] Definir el cambio de balon o cambio de jugada como va a ser.
	//TODO [X] cambio posesion   
	//TODO [] definir  tiempo probar un partido
	//TODO [] PROBAR EL CAMBIO DE JUGADA QUE TENGA COHERENCIA
	//TODO [] Afectar las probabilidades segun las caracteristicas
	//TODO [] llevar conteo de goles
	
	//TODO [X] La afectacion noquedo bien REVISAR


}
