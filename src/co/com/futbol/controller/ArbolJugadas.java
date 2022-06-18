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
			
			inicio.alt(jugadaInicial);
			jugadaInicial.alt(TiroAlArco, paseAlPortero, paseAtras, paseAdelante);	
			saqueMitadCancha.alt(TiroAlArco, paseAlPortero, paseAtras, paseAdelante);
			paseAdelante.alt(TiroAlArco, paseAlPortero, paseAtras, paseAdelante);
			paseAtras.alt(TiroAlArco, paseAlPortero, paseAtras, paseAdelante);
			paseAlPortero.alt(paseAdelante);
			TiroAlArco.alt(TIROS, gol, palo,PorFuera); // TODO CORREGIR
			PorFuera.alt(cambioPosesion);
			palo.alt(cambioPosesion);
			gol.alt(saqueMitadCancha);
			cambioPosesion.alt(saquePortero);
			saquePortero.alt(paseAdelante, TiroAlArco);
			
			
			jugadaInicial.alt(TiroAlArco.sP(2), paseAlPortero.sP(20), paseAtras.sP(40), paseAdelante.sP(38));	
			saqueMitadCancha.alt(TiroAlArco.sP(2), paseAlPortero.sP(20), paseAtras.sP(40), paseAdelante.sP(38));
			paseAdelante.alt(TiroAlArco.sP(10), paseAlPortero.sP(2), paseAtras.sP(30), paseAdelante.sP(53));
			paseAtras.alt(TiroAlArco.sP(4), paseAlPortero.sP(10), paseAtras.sP(30), paseAdelante.sP(56));
			paseAlPortero.alt(paseAdelante);
			TiroAlArco.alt(gol.sP(20), palo.sP(10),PorFuera.sP(70));
			PorFuera.alt(cambioPosesion);
			palo.alt(cambioPosesion);
			gol.alt(saqueMitadCancha);
			cambioPosesion.alt(saquePortero);
			saquePortero.alt(paseAdelante.sP(90), TiroAlArco.sP(10));
		
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
	
	//TODO [] La afectacion noquedo bien REVISAR


}
