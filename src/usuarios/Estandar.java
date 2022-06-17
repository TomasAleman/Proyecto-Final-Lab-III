package usuarios;

import java.time.LocalDate;

import org.json.JSONException;
import org.json.JSONObject;

public class Estandar extends Usuario {

	private Perfil listaPerfiles[];
	
	
	public Estandar (String mail, String clave) {
		super(mail, clave);
		listaPerfiles=new Perfil[4];	
	}
	
	public Estandar () {
		super();
		listaPerfiles=new Perfil[4];	
	}

	public Perfil[] getListaPerfiles() {
		return listaPerfiles;
	}

	public void setListaPerfiles(Perfil[] listaPerfiles) {
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
