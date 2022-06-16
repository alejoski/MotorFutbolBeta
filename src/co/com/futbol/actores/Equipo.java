package co.com.futbol.actores;

public class Equipo {
	
	private String nombre;
	private int portero;
	private int defensa;
	private int medio;
	private int ataque;
	
	public Equipo() {
		
	}

	public Equipo(String nombre, int portero, int defensa, int medio, int ataque) {
		super();
		this.nombre = nombre;
		this.portero = portero;
		this.defensa = defensa;
		this.medio = medio;
		this.ataque = ataque;
		
		System.out.println("Inicializando... " + toString());
	}
	
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPortero() {
		return portero;
	}

	public void setPortero(int portero) {
		this.portero = portero;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	public int getMedio() {
		return medio;
	}

	public void setMedio(int medio) {
		this.medio = medio;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + ", portero=" + portero + ", defensa=" + defensa + ", medio=" + medio
				+ ", ataque=" + ataque + "]";
	}


	

}
