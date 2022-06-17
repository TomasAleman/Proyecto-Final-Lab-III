package series;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import elementos.Clasificacion;
import elementos.Elemento;
import elementos.Genero;

public class Serie extends Elemento {
	private ArrayList<Temporada> listaTemporadas;
	private int cantTemporadas;

	//constructores
	public Serie(String nombre, float puntaje, Genero genero, Clasificacion clasificacion, String descripcion,
			int anioDeEstreno, String elenco, int cantTemporadas, ArrayList<Temporada> listaTemporadas) {
		super(nombre, puntaje, genero, clasificacion, descripcion, anioDeEstreno, elenco);
		this.cantTemporadas = cantTemporadas;
		this.listaTemporadas = listaTemporadas;
		
	}
	
	public Serie()
	{
		super();
		listaTemporadas = new ArrayList<Temporada>();
		cantTemporadas=0;
	}
	
	//Getters y Setters
	public ArrayList<Temporada> getListaTemporadas() {
		return listaTemporadas;
	}

	public void setListaTemporadas(ArrayList<Temporada> listaTemporadas) {
		this.listaTemporadas = listaTemporadas;
	}

	public int getCantTemporadas() {
		return cantTemporadas;
	}

	public void setCantTemporadas(int cantTemporadas) {
		this.cantTemporadas = cantTemporadas;
	}
	
	//metodos
    public String listarTemporadas()
    {
		String contenido = "";
		for(int i = 0; i<listaTemporadas.size(); i++)
		{
			contenido += listaTemporadas.get(i).toString();
		}
		return contenido;	
    }
    
   @Override
public String toString() {
	
	return super.toString()+ listarTemporadas();
}
   
   @Override
	public JSONObject devolverJsonObject() throws JSONException {
		JSONObject serie = new JSONObject();
		
		serie.put("\nID: ", getId());
		serie.put("\nNombre: ", getNombre());
		serie.put("\nDescripción: ", getDescripcion());
		serie.put("\nGénero: ",getGenero());
		serie.put("\nAño de estreno: ", getAnioDeEstreno());
		serie.put("\nClasificación: ", getClasificacion());
		serie.put("\nElenco: ", getElenco());
		serie.put("\n# Temporadas: ", getCantTemporadas());
		serie.put("\nDescripción Temporadas: ", getListaTemporadas());
		
		return serie;
	}
	
	

}
