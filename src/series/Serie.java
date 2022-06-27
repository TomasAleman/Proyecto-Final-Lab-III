package series;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import elementos.Clasificacion;
import elementos.Elemento;

public class Serie extends Elemento implements Serializable {
	private ArrayList<Temporada> listaTemporadas;
	private int cantTemporadas;

	// Constructores
	public Serie(String nombre, float puntaje, String genero, Clasificacion clasificacion, String descripcion,
			int anioDeEstreno, String elenco, int cantTemporadas, ArrayList<Temporada> listaTemporadas) {
		super(nombre, puntaje, genero, clasificacion, descripcion, anioDeEstreno, elenco);
		this.cantTemporadas = cantTemporadas;
		this.listaTemporadas = listaTemporadas;

	}

	public Serie() {
		super();
		listaTemporadas = new ArrayList<Temporada>();
		cantTemporadas = 0;
	}

	// Getters y Setters


	public ArrayList<Temporada> getListaTemporadas() {
		return listaTemporadas;
	}

	public int getCantTemporadas() {
		return cantTemporadas;
	}

	public void setCantTemporadas(int cantTemporadas) {
		this.cantTemporadas = cantTemporadas;
	}

	// ---------------------------------------- MÉTODOS

	// Mostrar las Temporadas de una Serie
	public String listarTemporadas() {
		String contenido = "";
		for (int i = 0; i < listaTemporadas.size(); i++) {
			contenido += listaTemporadas.get(i).toString();
		}
		return contenido;
	}

	// ---------------------------------------- OVERRIDES
	@Override
	public String toString() {

		return super.toString() + "\n * Cantidad de temporadas: " + getCantTemporadas() + listarTemporadas();
	}

    // ---------------------------------------- MÉTODO JSON
	@Override
	public JSONObject elementoToJSON() throws JSONException {
		JSONObject serie = new JSONObject();

		serie.put("ID", getId());
		serie.put("Nombre", getNombre());
		serie.put("Descripción", getDescripcion());
		serie.put("Género", getGenero());
		serie.put("Año de estreno", getAnioDeEstreno());
		serie.put("Clasificación", getClasificacion());
		serie.put("Elenco", getElenco());
		
		JSONArray arrayTemporadas = new JSONArray();
		
		for(int i = 0; i<listaTemporadas.size(); i++)
		{
			arrayTemporadas.put(listaTemporadas.get(i).temporadaToJSON());
		}
		
		serie.put("Temporada",arrayTemporadas);

		return serie;
	}
}