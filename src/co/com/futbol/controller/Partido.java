package co.com.futbol.controller;

import java.time.Duration;

import co.com.futbol.actores.Equipo;
import co.com.futbol.actores.Jugada;

public class Partido {

	private ArbolJugadas arbolJugadasA = null;
	private ArbolJugadas arbolJugadasB = null;
	private char quienJuega = 'A';
	private char quienInicio = 'A';
	private final String PRIMER_TIEMPO = "PrimerTiempo";
	private final String SEGUNDO_TIEMPO = "SegundoTiempo";
	private Jugada JugadaGenerica = null;
	private int GolesA = 0;
	private int GolesB = 0;
	private Equipo equipoA = null;
	private Equipo equipoB = null;
	private String tiempoJuego = PRIMER_TIEMPO;

	private Duration duration = Duration.ofMinutes(0L);
	private Duration durationStandar = Duration.ofMinutes(45L);

	public Partido() {

		// Inicializa equipo A
		String nombreEquipoA = "Colombia";
		int porteroA = 50, defensaA = 60, medioA = 50, ataqueA = 65, tirosArcoA = 55, pasesA = 45, contraGolpeA = 50;
		equipoA = new Equipo(nombreEquipoA, porteroA, defensaA, medioA, ataqueA, tirosArcoA, pasesA, contraGolpeA);

		// Inicializa equipo B
		String nombreEquipoB = "Peru";
		int porteroB = 48, defensaB = 57, medioB = 58, ataqueB = 62, tirosArcoB = 48, pasesB = 42, contraGolpeB = 48;
		equipoB = new Equipo(nombreEquipoB, porteroB, defensaB, medioB, ataqueB, tirosArcoB, pasesB, contraGolpeB);

		arbolJugadasA = new ArbolJugadas(equipoA, equipoB);
		arbolJugadasB = new ArbolJugadas(equipoB, equipoA);

		// TODO ACA SE PUEDE DECIDIR QUE EQUIPO INICIA EL PARTIDO

		/**
		 * Numero Aleatorio Si sale 0 Juega equip A Si sale 1 juewga equipo B
		 */
		Jugada jugadasA = arbolJugadasA.iniciarPartido();
		Jugada jugadasB = arbolJugadasB.iniciarPartido();

		// Seleccion aleatorea de quien inicia el partido, simula el lanzamiento de
		// moneda

		int moneda = (int) (Math.random() * 10) + 1;
		System.out.println("moneda " + moneda);

		if (moneda <= 5) {
			JugadaGenerica = jugadasA;
			quienJuega = 'A';
			System.out.println("INICIA COLOMBIA");
		} else if (moneda > 5) {
			JugadaGenerica = jugadasB;
			quienJuega = 'B';
			System.out.println("INICIA PERU");
		}

		// El saque inicial lo realiza quien inicia jugando
		quienInicio = quienJuega;

		enJuego();
	}

	public boolean enJuego() {

		// Determina quien esta jugando en ese momento
		// TODO
		// Aca tiene que sacar la equivalente a Jugada Generica pero del otro equipo
		// para que haga cambio de probabilidade
		/// Y no seiga por la misma linea
		if (quienJuega == 'A') {
			JugadaGenerica = arbolJugadasA.siguienteJugada(JugadaGenerica);
		} else {
			JugadaGenerica = arbolJugadasB.siguienteJugada(JugadaGenerica);
		}

		// Cuando una Jugada implica cambio de equipo, cambia el arbol de jugadas
		if (JugadaGenerica.isCambioEquipo()) {

			if (JugadaGenerica.getNombre().equals("Gol")) {

				if (quienJuega == 'A') {
					// Aumenta cuenta de goles
					GolesA += 1;
					// Cambia de posesion el balon
					// quienJuega = 'B';

				} else if (quienJuega == 'B') {
					GolesB += 1;
				}

			}

			quienJuega = (quienJuega == 'A' ? 'B' : 'A');

		}

		// Monitorea el tiempo de Juego (45 Minutos)
		aumentaDuracion(JugadaGenerica.getDuracionJugada());

		if (!terminaTiempo())
			return enJuego();
		else {

			// Marcador al finalizar cada tiempo
			System.out.println("MARCADOR  >> " + tiempoJuego + " [" + equipoA.getNombre() + " " + GolesA + " "
					+ equipoB.getNombre() + " " + GolesB + "]");

			if (tiempoJuego.equals(PRIMER_TIEMPO)) {

				// Se resetea el cronometro
				duration = Duration.ofMinutes(0L);

				// Se establece el segundo tiempo de juego
				tiempoJuego = SEGUNDO_TIEMPO;

				// Se determina quien iniciará el segundo tiempo
				if (quienInicio == 'A') {
					quienJuega = 'B';
					JugadaGenerica = arbolJugadasB.iniciarSegundoTiempo();
				} else {
					quienJuega = 'A';
					JugadaGenerica = arbolJugadasA.iniciarSegundoTiempo();
				}

				return enJuego();

			} else {
				return false;
			}
		}

	}

	public boolean terminaTiempo() {
		if (duration.getSeconds() < durationStandar.getSeconds())
			return false;

		return true;
	}

	private void aumentaDuracion(Long t) {

		duration = duration.plusSeconds(t);

		// System.out.println("{{{{Tiempo}}}} " + duration.toMinutes());

	}

}