package co.com.futbol.actores;

import java.util.ArrayList;
import java.util.HashMap;

public class Jugada implements Cloneable {

	private int id;
	private String nombre = "";
	private int probabilidad = 100;
	private int probPersonalizada = 100;
	//private int probCalculada = 100;
	
	private Jugada[] JugadasSiguientes = new Jugada[] {} ;
	private boolean cambioEquipo = false;
	private int afectacion=0;
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

		//System.out.println("<< " + this.nombre + " Prob " + this.probabilidad+ " Afectacion " + afectacion +    ">>");

		HashMap<Integer, Jugada> posibilidaes = new HashMap<>();
		int i = 1;
		int sumaTotal = 0;
		ArrayList<Integer> opciones = new ArrayList<Integer>();

		for (Jugada jugada : JugadasSiguientes) {

			posibilidaes.put(i, jugada);
			sumaTotal += jugada.getProbabilidad();
			//System.out.println("      *i " + i + " " + jugada.getNombre() + " Prob > " + jugada.getProbabilidad() + " Afec > "+ jugada.getAfectacion());

			for (int j = 0; j < jugada.getProbabilidad(); j++) {
				opciones.add(i);
			}

			i++;
		}

		/*System.out.println("Suma total probabilidades > " + sumaTotal);
		System.out.println(opciones.toString());
		System.out.println("Tamaño arreglo [" + opciones.size() + "]");*/
		
		int random = randomId(opciones.size());
		int indice = opciones.get(random);
		return posibilidaes.get(indice);

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
		System.out.println("--------------***" + nombre + "***---------------------");
			
		JugadasSiguientes = jugadasSiguientes;
		//modificaProbabilidad(); No se modifica probabilidad porque no se recibe aun el parametro de afectavcion y se puede llegar a modificar la probabilidad 
		imprimeInicializacion();
	}
	
	public void alternatives(int afectacion , Jugada... jugadasSiguientes) {
		setAfectacion(afectacion);
		//modificaProbabilidad();
		JugadasSiguientes = jugadasSiguientes;
		//imprimeInicializacion();
	}
	
	/*
	 * sP: Set Posibilidades, Establece las posibilidades de cada alternativa,
	 * asegurandose que se creen instancias diferentes de cada jugada (Clone)
	 */
	public Jugada setProb(int probabilidad) throws CloneNotSupportedException {
		this.probPersonalizada = probabilidad;
		modificaProbabilidad();
		return this.clone();
	}
	
	public Jugada setProb() throws CloneNotSupportedException {
		setProbabilidad(probabilidad);//
		modificaProbabilidad();
		return this.clone();
	}
	

	
	public void imprimeInicializacion() {
		
		//System.out.println(nombre + " Probabilidad: " + probabilidad + " | afectacion: " + afectacion);
	}

	private void modificaProbabilidad() {
		//imprimeInicializacion();

		//Si la afectacion es negativa y la diferencia entra la 
		//Afactacion y la probabilidad es neggativa se deja en 5 la probabilidad
		if ((afectacion + this.probPersonalizada) <= 0) { 			
			System.out.println(nombre  + " afectacion " + afectacion  + " Probabiliad " + this.probPersonalizada + " Resultado " + 7);
			setProbabilidad(7);
			
		}else {
			System.out.println(nombre  + " afectacion " + afectacion  + " Probabiliad " + this.probPersonalizada + " RESLTADO " + (this.probPersonalizada + this.afectacion));
			setProbabilidad(this.probPersonalizada + this.afectacion);
			
		}
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
