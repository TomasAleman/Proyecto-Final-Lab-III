package json;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
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
	            contenido = new String(Files.readAllBytes(Paths.get("nerflis.txt")));
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
            FileWriter file = new FileWriter("nerflis.txt");
            file.write(obj.toString());
            file.flush();
            file.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

	public JSONObject exportarToJSON(ListaUsuarios usu, ListaElementos ele)
	{
		JSONObject json = new JSONObject();
		
		try
		{
			JSONArray jsonArrayUsuarios = new JSONArray();
			JSONArray jsonArrayElementos = new JSONArray();
			Iterator entriesUsuarios = usu.iterador();
			Iterator entriesElementos = ele.iterador();
			
			while(entriesUsuarios.hasNext())
			{
				Usuario usuActual = (Usuario) entriesUsuarios.next();
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
