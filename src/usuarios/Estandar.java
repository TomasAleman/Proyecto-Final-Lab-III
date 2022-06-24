package usuarios;

import java.time.LocalDate;

import org.json.JSONException;
import org.json.JSONObject;

public class Estandar extends Usuario {
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
		
		usu.put("\nMail: ",getMail());
		usu.put("\nClave: ",getClave());
		usu.put("\nPerfiles: ",getListaPerfiles());
		
		return usu;
	}
	
	
}
