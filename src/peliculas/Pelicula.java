package peliculas;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import elementos.Clasificacion;
import elementos.Elemento;

public class Pelicula extends Elemento implements Serializable{
	private String duracion;

	// Constructores
	public Pelicula(String nombre, float puntaje, String genero, Clasificacion clasificacion, String descripcion,
			int anioDeEstreno,String elenco, String duracion) {
		super(nombre, puntaje, genero, clasificacion, descripcion, anioDeEstreno,elenco);
		this.duracion = duracion;
	}
	public Pelicula()
	{
		super();
		duracion="";
	}

	// Getters y Setters
	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	
	// ---------------------------------------- OVERRIDES
	@Override
	public String toString() {
		return super.toString()+"\n * Duracion: "+getDuracion()+" hs";
	}
	
	// ---------------------------------------- MÉTODO JSON
	@Override
	public JSONObject elementoToJSON() throws JSONException {
		JSONObject peli = new JSONObject();
		
		peli.put("ID", getId());
		peli.put("Nombre", getNombre());
		peli.put("Descripción", getDescripcion());
		peli.put("Género",getGenero());
		peli.put("Año de estreno", getAnioDeEstreno());
		peli.put("Clasificación", getClasificacion());
		peli.put("Elenco", getElenco());
		peli.put("Duración", getDuracion());
		
		return peli;
	}
	


}
