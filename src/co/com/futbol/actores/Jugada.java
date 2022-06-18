package co.com.futbol.actores;

import java.util.ArrayList;
import java.util.HashMap;

public class Jugada implements Cloneable {

	private String nombre = "";
	private int probabilidad = 100;
	private Jugada[] JugadasSiguientes;
	private boolean cambioEquipo = false;
	


	public Jugada clone() throws CloneNotSupportedException {
		return (Jugada) super.clone();
	}

	public Jugada() {

	}

	public Jugada(String nombre) {
		this.nombre = nombre;
	}

	public Jugada(String nombre, boolean cambioEquipo) {
		this.cambioEquipo = cambioEquipo;
		this.nombre = nombre;
	}

	public Jugada siguienteJUgada() {

		System.out.println("---------------------");
		System.out.println("<<" + this.nombre + ">>");

		HashMap<Integer, Jugada> posibilidaes = new HashMap<>();
		int i = 1;
		int sumaTotal = 0;
		ArrayList<Integer> opciones = new ArrayList<Integer>();

		for (Jugada jugada : JugadasSiguientes) {

			posibilidaes.put(i, jugada);
			sumaTotal += jugada.getProbabilidad();
			 System.err.println(" i " + i + " " + jugada.getNombre());
			System.out.println("Probabilidad Jugada > " + jugada.getProbabilidad());

			for (int j = 0; j < jugada.getProbabilidad(); j++) {
				opciones.add(i);
			}

			i++;
		}

		System.out.println("Suma total probabilidades > " + sumaTotal);
		System.out.println(opciones.toString());
		System.out.println("Tamaño arreglo [" + opciones.size() + "]");

		return posibilidaes.get(opciones.get(randomId(opciones.size())));

	}

	public int randomId(int max) {
		int x = (int) (Math.random() * max);
		System.out.println("Valor aleatorio <" + x + ">");
		return x;
	}

	/*
	 * Alt: Alternaticas, jugadas posibles apartir de esta jugada
	 */
	public void alt(Jugada... jugadasSiguientes) {
		JugadasSiguientes = jugadasSiguientes;
	}
	
	public void alt(int afectacion , Jugada... jugadasSiguientes) {
		modificaProbabilidad(afectacion);
		JugadasSiguientes = jugadasSiguientes;
	}
	
	private void modificaProbabilidad(int afectacion) {
		setProbabilidad(getProbabilidad() + afectacion);
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public int getProbabilidad() {
		return probabilidad;
	}

	public Jugada[] getJugadasSiguientes() {
		return JugadasSiguientes;
	}

	public void setJugadasSiguientes(Jugada[] jugadasSiguientes) {
		JugadasSiguientes = jugadasSiguientes;
	}

	public void setProbabilidad(int probabilidad) {
		this.probabilidad = probabilidad;
	}

	/*
	 * sP: Set Posibilidades, Establece las posibilidades de cada alternativa
	 */
	public Jugada sP(int probabilidad) throws CloneNotSupportedException {
		setProbabilidad(probabilidad);
		return this.clone();
	}
	
	public boolean isCambioEquipo() {
		return cambioEquipo;
	}

	public void setCambioEquipo(boolean cambioEquipo) {
		this.cambioEquipo = cambioEquipo;
	}


	@Override
	public String toString() {

		String jugadas = "|";
		for (Jugada jugada : JugadasSiguientes) {
			jugadas = jugadas + jugada.nombre + "| ";
		}

		return "Jugada [nombre=" + nombre + ", probabilidad=" + probabilidad + ", JugadasSiguientes=" + jugadas;

	}
}
