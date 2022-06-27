package usuarios;

import java.io.Serializable;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import elementos.Elemento;
import elementos.ListaElementos;

public class Perfil implements Serializable{
	private String nombre;
	private boolean infantil; // true si es cuenta para menores
	private ListaElementos miLista; 

	// constructores
	public Perfil(String nombre, boolean infantil) {
		//setId();
		this.nombre = nombre;
		this.infantil = infantil;
        miLista = new ListaElementos();
	}

	// Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isInfantil() {
		return infantil;
	}

	public void setInfantil(boolean infantil) {
		this.infantil = infantil;
	}

	public ListaElementos getMiLista() {
		return miLista;
	}

	public void setMiLista(ListaElementos miLista) {
		this.miLista = miLista;
	}
	
	// ---------------------------------------- OVERRIDES
	public String toString() {
		String inf = "";
		
		if(isInfantil() == true)
		{
			inf = "Infantil";
		}
		else{
			inf = "Adulto";
		}
		
		return "Nombre: "+getNombre()+ " | Clasificacion: "+inf;
	}
	
	public JSONObject perfilToJSON()
	{
		JSONObject perfil = new JSONObject();
		
		try
		{
		perfil.put("Nombre",getNombre());
		perfil.put("Infantil",isInfantil());
		Iterator<Elemento>iterador = miLista.iterador();
		
		JSONArray arrayMiLista = new JSONArray();
		Elemento actual = null;
		
		while(iterador.hasNext())
		{
			actual = iterador.next();	
			arrayMiLista.put(actual.elementoToJSON());
		}
	
		perfil.put("Mi Lista",arrayMiLista);
		}
		catch(JSONException e)
		{
			e.printStackTrace();
		}
		
		return perfil;
	}
}

