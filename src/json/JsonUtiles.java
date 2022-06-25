package json;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import elementos.Elemento;
import elementos.ListaElementos;
import usuarios.ListaUsuarios;
import usuarios.Usuario;

public class JsonUtiles {
	
	 public static String leer()
	    {
	        String contenido = "";
	        try{
	            contenido = new String(Files.readAllBytes(Paths.get("nerflis.json")));
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
	        return contenido;
	    }

	
	public void grabarJSON(JSONObject obj)
    {
        try {
            FileWriter file = new FileWriter("nerflis.json");
            file.write(obj.toString());
            file.flush();
            file.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

	// Método que escribe los datos (Usuarios, Perfiles y Elementos) a un json
	public JSONObject exportarToJSON(ListaUsuarios usu, ListaElementos ele)
	{
		JSONObject json = new JSONObject();
		
		try
		{
			JSONArray jsonArrayUsuarios = new JSONArray();
			JSONArray jsonArrayElementos = new JSONArray();
			Iterator<Map.Entry<String, Usuario>> entriesUsuarios = usu.iterador();
			Iterator<Elemento> entriesElementos = ele.iterador();
			
			while(entriesUsuarios.hasNext())
			{
				Map.Entry<String, Usuario> entryActual = entriesUsuarios.next();
				Usuario usuActual = (Usuario) entryActual.getValue();
				jsonArrayUsuarios.put(usuActual.devolverJsonObject());
			}
			
			while(entriesElementos.hasNext())
			{
				Elemento eleActual = (Elemento)entriesElementos.next();
				jsonArrayElementos.put(eleActual.devolverJsonObject());
			}
			
			json.put("Usuarios",jsonArrayUsuarios);
			json.put("Elementos", jsonArrayElementos);
		}
		catch(JSONException e)
		{
			e.getMessage();
		}
		
		return json;
	}
}
