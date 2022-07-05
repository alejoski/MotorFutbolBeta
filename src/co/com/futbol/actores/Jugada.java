package co.com.futbol.actores;

import java.util.ArrayList;
import java.util.HashMap;

public class Jugada implements Cloneable {

	private int id;
	private String nombre = "";
	private int probabilidad = 100;
	private Jugada[] JugadasSiguientes = new Jugada[] {} ;
	private boolean cambioEquipo = false;
	private int afectacion;
	private Long duracionJugada;
	


	public Jugada clone() throws CloneNotSupportedException {
		return (Jugada) super.clone();
	}

	public Jugada() {

	}

	
	public Jugada(int id, String nombre, Long duracionJugada, boolean cambioEquipo) {
		this.id = id;
		this.duracionJugada = duracionJugada;		
		this.cambioEquipo = cambioEquipo;
		this.nombre = nombre;
	}

	public Jugada(String nombre, boolean cambioEquipo) {
		this.cambioEquipo = cambioEquipo;
		this.nombre = nombre;
	}

	public Jugada siguienteJUgada() {

		System.out.println("<<" + this.nombre + ">>");

		HashMap<Integer, Jugada> posibilidaes = new HashMap<>();
		int i = 1;
		int sumaTotal = 0;
		ArrayList<Integer> opciones = new ArrayList<Integer>();

		for (Jugada jugada : JugadasSiguientes) {

			posibilidaes.put(i, jugada);
			sumaTotal += jugada.getProbabilidad();
			System.out.println("      *i " + i + " " + jugada.getNombre() + " Prob > " + jugada.getProbabilidad());

			for (int j = 0; j < jugada.getProbabilidad(); j++) {
				opciones.add(i);
			}

			i++;
		}

		/*System.out.println("Suma total probabilidades > " + sumaTotal);
		System.out.println(opciones.toString());
		System.out.println("Tamaño arreglo [" + opciones.size() + "]");*/

		return posibilidaes.get(opciones.get(randomId(opciones.size())));

	}

	public int randomId(int max) {
		int x = (int) (Math.random() * max);
		//System.out.println("Valor aleatorio <" + x + ">");
		return x;
	}

	/*
	 * Alt: Alternativas, jugadas posibles apartir de esta jugada
	 */
	public void alternatives(Jugada... jugadasSiguientes) {
		JugadasSiguientes = jugadasSiguientes;
		imprimeInicializacion();
	}
	
	public void alternatives(int afectacion , Jugada... jugadasSiguientes) {
		setAfectacion(afectacion);
		modificaProbabilidad();
		JugadasSiguientes = jugadasSiguientes;
		//imprimeInicializacion();
	}
	
	public void alternatives(int afectacion , Jugada jugadasSiguientes) {
		setAfectacion(afectacion);
		modificaProbabilidad();
		JugadasSiguientes = new Jugada[] {jugadasSiguientes};
		
		//imprimeInicializacion();
	}
	
	public void imprimeInicializacion() {
		
		//System.out.println(nombre + " Probabilidad: " + probabilidad + " | afectacion: " + afectacion);
	}

	private void modificaProbabilidad() {
		//imprimeInicializacion();

		//Si la afectacion es negativa y la diferencia entra la 
		//Afactacion y la probabilidad es neggativa se deja en 5 la probabilidad
		if ((afectacion + probabilidad) <= 0) 			
			setProbabilidad(7);
		else
			setProbabilidad(this.probabilidad + this.afectacion);		
		//imprimeInicializacion();
		
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

	public int getAfectacion() {
		return afectacion;
	}

	public void setAfectacion(int afectacion) {
		this.afectacion = afectacion;
	}

	/*
	 * sP: Set Posibilidades, Establece las posibilidades de cada alternativa,
	 * asegurandose que se creen instancias diferentes de cada jugada (Clone)
	 */
	public Jugada setProb(int probabilidad) throws CloneNotSupportedException {
		setProbabilidad(probabilidad);
		modificaProbabilidad();
		return this.clone();
	}
	
	public boolean isCambioEquipo() {
		return cambioEquipo;
	}

	public void setCambioEquipo(boolean cambioEquipo) {
		this.cambioEquipo = cambioEquipo;
	}


	public Long getDuracionJugada() {
		return duracionJugada;
	}

	public void setDuracionJugada(Long duracionJugada) {
		this.duracionJugada = duracionJugada;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
