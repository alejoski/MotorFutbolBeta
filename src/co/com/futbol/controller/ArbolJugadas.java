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
	
	
	

	public ArbolJugadas(Equipo equipo) {
		
		
		int ATAQUE  = equipo.getAtaque();
		int DEFENSA = equipo.getDefensa();
		int MEDIO   = equipo.getMedio();
		int PORTERO = equipo.getPortero();
		
		try {
			
			inicio.alt(jugadaInicial);
			jugadaInicial.alt(TiroAlArco, paseAlPortero, paseAtras, paseAdelante);	
			saqueMitadCancha.alt(TiroAlArco, paseAlPortero, paseAtras, paseAdelante);
			paseAdelante.alt(TiroAlArco, paseAlPortero, paseAtras, paseAdelante);
			paseAtras.alt(TiroAlArco, paseAlPortero, paseAtras, paseAdelante);
			paseAlPortero.alt(paseAdelante);
			TiroAlArco.alt(gol, palo,PorFuera);
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
		
		return inicio.siguienteJUgada();	
	}
	
	
	
	// TODO Definir el cambio de balon o cambio de jugada como va a ser.
	//TODO cambio posesion definir tiempo probar un partido


}
