	package elementos;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class Elemento implements Serializable{
	private Integer id;
	private static Integer contador=0;
	private String nombre;
	private float puntaje;
	private String genero; // ACCION, SUSPENSO, TERROR, COMEDIA, ROMANCE;
	private Clasificacion clasificacion;
	private String descripcion;
	private int anioDeEstreno;
	private String elenco;

	public Elemento(String nombre, float puntaje, String genero, Clasificacion clasificacion, String descripcion,
			int anioDeEstreno, String elenco) {
		this.id=contador;
		contador++;
		this.nombre = nombre;
		this.puntaje = puntaje;
		this.genero = genero;
		this.clasificacion = clasificacion;
		this.descripcion = descripcion;
		this.anioDeEstreno = anioDeEstreno;
		this.elenco = elenco;
	}
	
	public Elemento() {
		this.nombre = null;
		this.puntaje = 0;
		this.genero = "";
		this.clasificacion = null;
		this.descripcion = null;
		this.anioDeEstreno = 0;
		this.elenco = null;
	}
	
	

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public static Integer getContador() {
		return contador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(float puntaje) {
		this.puntaje = puntaje;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Clasificacion getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(Clasificacion clasificacion) {
		this.clasificacion = clasificacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getAnioDeEstreno() {
		return anioDeEstreno;
	}

	public void setAnioDeEstreno(int anioDeEstreno) {
		this.anioDeEstreno = anioDeEstreno;
	}

	public String getElenco() {
		return elenco;
	}

	public void setElenco(String elenco) {
		this.elenco = elenco;
	}

	@Override
	public String toString() {
		return "\n\n ------------------ #"+id+" | " + nombre.toUpperCase() + "\n * Género: " + genero + "\n * Puntaje: " + puntaje + "\n * Clasificación: " + clasificacion + "\n * Descripción: " + descripcion + "\n * Año de Estreno: "
				+ anioDeEstreno + "\n * Elenco: " + elenco;
	}

	@Override
	public int hashCode() {
		return 1;
	}

	@Override
	public boolean equals(Object otroElemento) {
		boolean iguales = false;
		if (otroElemento != null) {
			if (otroElemento instanceof Elemento) {
				Elemento aComparar = (Elemento) otroElemento;
				if (aComparar.nombre.equalsIgnoreCase(this.nombre)) {
					iguales = true;
				}
			}
		}
		return iguales;
	}
	

	public abstract JSONObject devolverJsonObject() throws JSONException;

}
