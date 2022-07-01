package co.com.futbol.controller;

import java.util.ArrayList;

import co.com.futbol.actores.Equipo;
import co.com.futbol.actores.Jugada;


public class ArbolJugadas {

	Jugada inicio = new Jugada("<Inico>");
	
	Jugada cambioPosesion = new Jugada("Cambio Posesion", 15L);
	
	Jugada saquePortero  = new Jugada("El portero despeja el balon", 15L);
	
	Jugada gol           = new Jugada("Gol", 15L, true);

	Jugada palo          = new Jugada("Palazo!!!", 15L);

	Jugada PorFuera      = new Jugada("El tiro sale desviado", 15L,  true);

	Jugada TiroAlArco    = new Jugada("Tiro al arco", 15L);

	Jugada paseAlPortero = new Jugada("Pasan el balon Al portero", 15L);
	
	Jugada paseAtras     = new Jugada("Pasan el balon hacia Atras", 15L);

	Jugada paseAdelante  = new Jugada("Pasan el balon hacia adelante", 15L);

	Jugada jugadaInicial = new Jugada("El Referí indica el inicio", 15L);
	
	Jugada saqueMitadCancha= new Jugada("El Referí reinica el partido", 15L);
	
	Jugada porteroDetieneBalon = new Jugada("El Portero detiene el balon", 15L);
	
	Jugada pierdenBalonPase = new Jugada("El equipo contrario recupera el balon", 15L, true);

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
		
		
		ATAQUE  = equipo.getAtaque();//
		DEFENSA = equipo.getDefensa();//
		MEDIO   = equipo.getMedio();
		PORTERO = equipo.getPortero();//
		NOMBRE_EQUIPO = equipo.getNombre();//
		TIROS = equipo.getEfectividadTiros();
		PASES = equipo.getEfectividadPases();
		CONTRAGOLPE = equipo.getEfectividadContragolpe();
		
		
		try {
			System.out.println("############# " + NOMBRE_EQUIPO +  " ############# ");
			
			//Establece las alternativas de cada jugadas
			
			inicio.alternatives(jugadaInicial);
			jugadaInicial.alternatives(TiroAlArco, paseAlPortero, paseAtras, paseAdelante);	
			saqueMitadCancha.alternatives(TiroAlArco, paseAlPortero, paseAtras, paseAdelante);
			paseAdelante.alternatives(ATAQUE, TiroAlArco, paseAlPortero, paseAtras, paseAdelante);
			paseAtras.alternatives(DEFENSA, TiroAlArco, paseAlPortero, paseAtras, paseAdelante);
			paseAlPortero.alternatives(DEFENSA, paseAdelante);
			TiroAlArco.alternatives(ATAQUE, gol, palo,PorFuera, porteroDetieneBalon); // TODO CORREGIR
			PorFuera.alternatives(cambioPosesion);
			palo.alternatives(cambioPosesion);
			gol.alternatives(TIROS, saqueMitadCancha);
			cambioPosesion.alternatives(saquePortero);
			saquePortero.alternatives(paseAdelante, TiroAlArco);					
			porteroDetieneBalon.alternatives(PORTERO, saquePortero);
			pierdenBalonPase.alternatives(-PASES, paseAdelante, paseAtras, TiroAlArco);
			
			/* AVANZA CON EL BALON*/
			

			
			jugadaInicial.alternatives(TiroAlArco.setProb(2), paseAlPortero.setProb(20), paseAtras.setProb(40), paseAdelante.setProb(38));	
			saqueMitadCancha.alternatives(TiroAlArco.setProb(2), paseAlPortero.setProb(20), paseAtras.setProb(40), paseAdelante.setProb(38));
			paseAdelante.alternatives(TiroAlArco.setProb(10), paseAlPortero.setProb(2), paseAtras.setProb(30), paseAdelante.setProb(53));
			paseAtras.alternatives(TiroAlArco.setProb(5), paseAlPortero.setProb(10), paseAtras.setProb(20), paseAdelante.setProb(40), pierdenBalonPase.setProb(25));
			paseAlPortero.alternatives(paseAdelante);
			TiroAlArco.alternatives(gol.setProb(10), palo.setProb(5),PorFuera.setProb(45), porteroDetieneBalon.setProb(35));
			PorFuera.alternatives(cambioPosesion);
			palo.alternatives(cambioPosesion);
			gol.alternatives(saqueMitadCancha);
			cambioPosesion.alternatives(saquePortero);
			saquePortero.alternatives(paseAdelante.setProb(90), TiroAlArco.setProb(10));
			porteroDetieneBalon.alternatives(saquePortero);
			pierdenBalonPase.alternatives(paseAdelante.setProb(35), paseAtras.setProb(35), TiroAlArco.setProb(30));
			
			

		
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Se carga el arbol de jugadas");
	}
	
	public Jugada iniciarPartido() {	
		
		Jugada siguienteJ = inicio.siguienteJUgada();
		
		System.out.println("+- " + NOMBRE_EQUIPO + " -+ " + siguienteJ.getNombre() + " -+");
		
		return siguienteJ;	
	}
	
	public Jugada siguienteJugada(Jugada jugada) {	
		
		Jugada siguienteJ = jugada.siguienteJUgada();
		
		System.out.println("-+ " +NOMBRE_EQUIPO + " +- " + siguienteJ.getNombre() + " +-");
		return siguienteJ;	
	}
	
	
	
	//TODO [X] Definir el cambio de balon o cambio de jugada como va a ser.
	//TODO [X] cambio posesion   
	//TODO [] definir  tiempo probar un partido
	//TODO [] PROBAR EL CAMBIO DE JUGADA QUE TENGA COHERENCIA
	//TODO [] Configiurar push
	//TODO [] Probar la jugada porteroDetieneBalon, poner porcentajes
	

	//TODO [X] Afectar las probabilidades segun las caracteristicas
	//TODO [X] llevar conteo de goles	
	//TODO [X] La afectacion noquedo bien REVISAR


}
