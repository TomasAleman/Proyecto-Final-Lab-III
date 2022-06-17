package peliculas;

import org.json.JSONException;
import org.json.JSONObject;

import elementos.Clasificacion;
import elementos.Elemento;
import elementos.Genero;

public class Pelicula extends Elemento {
	private String duracion;

	public Pelicula(String nombre, float puntaje, Genero genero, Clasificacion clasificacion, String descripcion,
			int anioDeEstreno,String elenco, String duracion) {
		super(nombre, puntaje, genero, clasificacion, descripcion, anioDeEstreno,elenco);
		this.duracion = duracion;
	}
	public Pelicula()
	{
		super();
		duracion="";
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	
	@Override
	public JSONObject devolverJsonObject() throws JSONException {
		JSONObject peli = new JSONObject();
		
		peli.put("\nID: ", getId());
		peli.put("\nNombre: ", getNombre());
		peli.put("\nDescripción: ", getDescripcion());
		peli.put("\nGénero: ",getGenero());
		peli.put("\nAño de estreno: ", getAnioDeEstreno());
		peli.put("\nClasificación: ", getClasificacion());
		peli.put("\nElenco: ", getElenco());
		peli.put("\nDuración: ", getDuracion());
		
		return peli;
	}

}
