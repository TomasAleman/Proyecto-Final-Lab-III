package usuarios;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class Estandar extends Usuario implements Serializable{
	private ListaPerfiles listaPerfiles;
	
	
	public Estandar (String mail, String clave) {
		super(mail, clave);
		listaPerfiles = new ListaPerfiles();
	}
	
	public Estandar () {
		super();
		listaPerfiles = new ListaPerfiles();	
	}

	public ListaPerfiles getListaPerfiles() {
		return listaPerfiles;
	}

	public void setListaPerfiles(ListaPerfiles listaPerfiles) {
		this.listaPerfiles = listaPerfiles;
	}
	
	@Override
	public String toString() {
		
		return "\n- Estándar -"+super.toString();
	}
	
	@Override
	public JSONObject devolverJsonObject() throws JSONException {
		JSONObject usu = new JSONObject();
		usu.put("Estado", isEstado());
		usu.put("Mail",getMail());
		usu.put("Clave",getClave());
		usu.put("Perfiles",getListaPerfiles());
		
		return usu;
	}
	
	
}
