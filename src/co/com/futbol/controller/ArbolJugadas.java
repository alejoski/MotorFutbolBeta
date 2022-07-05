package co.com.futbol.controller;

import java.util.ArrayList;
import java.util.HashMap;

import co.com.futbol.actores.Equipo;
import co.com.futbol.actores.Jugada;

public class ArbolJugadas {

	HashMap<Integer, Jugada> jugadasMap = new HashMap<>();

	private Jugada inicio = new Jugada(1, "<Inico>", 0L, false);

	private Jugada inicioSegundoTiempo = new Jugada(2, "<Inico Segundo Tiempo>", 0L, false);

	private Jugada cambioPosesion = new Jugada(3, "Cambio Posesion", 15L, false);

	private Jugada saquePortero = new Jugada(4, "El portero despeja el balon", 15L, false);

	private Jugada gol = new Jugada(5, "Gol", 15L, true);

	private Jugada palo = new Jugada(6, "Palazo!!!", 15L, false);

	private Jugada PorFuera = new Jugada(7, "El tiro sale desviado", 15L, true);

	private Jugada TiroAlArco = new Jugada(8, "Tiro al arco", 15L, false);

	private Jugada paseAlPortero = new Jugada(9, "Pasan el balon Al portero", 15L, false);

	private Jugada paseAtras = new Jugada(10, "Pasan el balon hacia Atras", 15L, false);

	private Jugada paseAdelante = new Jugada(11, "Pasan el balon hacia adelante", 15L, false);

	private Jugada jugadaInicial = new Jugada(12, "El Referí indica el inicio", 15L, false);

	private Jugada saqueMitadCancha = new Jugada(13, "El Referí reinica el partido", 15L, false);

	//JUGADA CON LOGICA CONTRARIA
	private Jugada porteroDetieneBalon = new Jugada(14, "El portero detiene el balon", 15L, false); 

	private Jugada pierdenBalonPase = new Jugada(15, "El equipo contrario recupera el balon", 15L, true);

	/*
	 * Se deben definir variables o algunmecanismo que diferencia si es un pase
	 * adelante en area contraria o aria propia
	 */

	// Equipo equipo;
	int MI_ATAQUE;
	int MI_DEFENSA;
	int MI_MEDIO;
	int MI_PORTERO;
	int MI_TIROS;
	int MI_PASES;
	int MI_CONTRAGOLPE;
	String NOMBRE_EQUIPO;
	int CO_ATAQUE;
	int CO_DEFENSA;
	int CO_MEDIO;
	int CO_PORTERO;
	int CO_TIROS;
	int CO_PASES;
	int CO_CONTRAGOLPE;

	public ArbolJugadas(Equipo equipo, Equipo contrincante) {

		MI_ATAQUE = equipo.getAtaque();//
		MI_DEFENSA = equipo.getDefensa();//
		MI_MEDIO = equipo.getMedio();
		MI_PORTERO = equipo.getPortero();//
		NOMBRE_EQUIPO = equipo.getNombre();//
		MI_TIROS = equipo.getEfectividadTiros();
		MI_PASES = equipo.getEfectividadPases();
		MI_CONTRAGOLPE = equipo.getEfectividadContragolpe();

		CO_ATAQUE = contrincante.getAtaque();
		CO_DEFENSA = contrincante.getDefensa();
		CO_MEDIO = contrincante.getMedio();
		CO_PORTERO = contrincante.getPortero();
		CO_TIROS = contrincante.getEfectividadTiros();
		CO_PASES = contrincante.getEfectividadPases();
		CO_CONTRAGOLPE = contrincante.getEfectividadContragolpe();

		try {
			System.out.println("############# " + NOMBRE_EQUIPO + " ############# ");

			// Establece las alternativas de cada jugadas

			inicio.alternatives(jugadaInicial);
			inicioSegundoTiempo.alternatives(jugadaInicial);
			jugadaInicial.alternatives(TiroAlArco, paseAlPortero, paseAtras, paseAdelante);
			saqueMitadCancha.alternatives(TiroAlArco, paseAlPortero, paseAtras, paseAdelante);
			paseAdelante.alternatives((MI_ATAQUE - CO_DEFENSA), TiroAlArco, paseAlPortero, paseAtras, paseAdelante);
			paseAtras.alternatives(MI_DEFENSA, TiroAlArco, paseAlPortero, paseAtras, paseAdelante);
			paseAlPortero.alternatives(MI_DEFENSA, paseAdelante);
			TiroAlArco.alternatives((MI_ATAQUE - CO_DEFENSA), gol, palo, PorFuera, porteroDetieneBalon); // TODO
			PorFuera.alternatives(cambioPosesion);
			palo.alternatives(cambioPosesion);
			gol.alternatives((MI_TIROS - CO_PORTERO), saqueMitadCancha);
			cambioPosesion.alternatives(saquePortero);
			saquePortero.alternatives(paseAdelante, TiroAlArco);
			porteroDetieneBalon.alternatives((CO_PORTERO - MI_TIROS), saquePortero);
			pierdenBalonPase.alternatives((-MI_PASES+ CO_CONTRAGOLPE), paseAdelante, paseAtras, TiroAlArco);

			/* AVANZA CON EL BALON */

			jugadaInicial.alternatives(TiroAlArco.setProb(2), paseAlPortero.setProb(20), paseAtras.setProb(40),paseAdelante.setProb(38));
			saqueMitadCancha.alternatives(TiroAlArco.setProb(2), paseAlPortero.setProb(20), paseAtras.setProb(40),paseAdelante.setProb(38));
			paseAdelante.alternatives(TiroAlArco.setProb(10), paseAlPortero.setProb(2), paseAtras.setProb(20),paseAdelante.setProb(45),pierdenBalonPase.setProb(25));
			paseAtras.alternatives(TiroAlArco.setProb(5), paseAlPortero.setProb(10), paseAtras.setProb(25),paseAdelante.setProb(40), pierdenBalonPase.setProb(25));
			paseAlPortero.alternatives(paseAdelante);
			TiroAlArco.alternatives(gol.setProb(20), palo.setProb(5), PorFuera.setProb(40),porteroDetieneBalon.setProb(35));
			PorFuera.alternatives(cambioPosesion);
			palo.alternatives(cambioPosesion);
			gol.alternatives(saqueMitadCancha);
			cambioPosesion.alternatives(saquePortero);
			saquePortero.alternatives(paseAdelante.setProb(90), TiroAlArco.setProb(10));
			porteroDetieneBalon.alternatives(saquePortero);
			pierdenBalonPase.alternatives(paseAdelante.setProb(35), paseAtras.setProb(35), TiroAlArco.setProb(30));

			jugadasMap.put(inicio.getId(), inicio);
			jugadasMap.put(inicioSegundoTiempo.getId(), inicioSegundoTiempo);
			jugadasMap.put(cambioPosesion.getId(), cambioPosesion);
			jugadasMap.put(saquePortero.getId(), saquePortero);
			jugadasMap.put(gol.getId(), gol);
			jugadasMap.put(palo.getId(), palo);
			jugadasMap.put(PorFuera.getId(), PorFuera);
			jugadasMap.put(TiroAlArco.getId(), TiroAlArco);
			jugadasMap.put(paseAlPortero.getId(), paseAlPortero);
			jugadasMap.put(paseAtras.getId(), paseAtras);
			jugadasMap.put(paseAdelante.getId(), paseAdelante);
			jugadasMap.put(jugadaInicial.getId(), jugadaInicial);
			jugadasMap.put(saqueMitadCancha.getId(), saqueMitadCancha);
			jugadasMap.put(porteroDetieneBalon.getId(), porteroDetieneBalon);
			jugadasMap.put(pierdenBalonPase.getId(), pierdenBalonPase);
			
			
			jugadasMap.forEach((k,v) ->{ 
				System.out.println(v.getNombre());				
				for (Jugada jugada : v.getJugadasSiguientes()) {					
					System.out.println("       " + " " + jugada.getNombre() + " Prob > " + jugada.getProbabilidad());
				}
				System.out.println("");
			});
			

		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Jugada iniciarPartido() {

		Jugada siguienteJ = inicio.siguienteJUgada();

		System.out.println("+- " + NOMBRE_EQUIPO + " -+ " + siguienteJ.getNombre() + " -+");

		return siguienteJ;
	}

	public Jugada iniciarSegundoTiempo() {

		Jugada siguienteJ = inicioSegundoTiempo.siguienteJUgada();

		System.out.println("+- " + NOMBRE_EQUIPO + " -+ " + siguienteJ.getNombre() + " -+");

		return siguienteJ;
	}

	public Jugada siguienteJugada(Jugada jugada) {

		Jugada siguienteJ = jugadasMap.get(jugada.getId()).siguienteJUgada();
				
				//jugada.siguienteJUgada(NOMBRE_EQUIPO);

		 System.out.println("-+ " +NOMBRE_EQUIPO + " +- " + siguienteJ.getNombre() + "+-");
		return siguienteJ;
	}

	public Jugada getGol() {
		return this.gol;
	}

	// TODO [X] Definir el cambio de balon o cambio de jugada como va a ser.
	// TODO [] Que los tiempos no sean exactos y que se ve el minuto y el segundo
	// TODO [] Validar en el word las condiciones, mejorar el arbol de jugadas
	

	// TODO [X] cambio posesion
	// TODO [X] PROBAR EL CAMBIO DE JUGADA QUE TENGA COHERENCIA
	// TODO [X] Hacer que valgan los parametros de juegos de cada equipo
	// TODO [X] definir tiempo probar un partido
	// TODO [X] Probar la jugada porteroDetieneBalon, poner porcentajes
	// TODO [X] Afectar las probabilidades segun las caracteristicas
	// TODO [X] llevar conteo de goles
	// TODO [X] La afectacion noquedo bien REVISAR

}
