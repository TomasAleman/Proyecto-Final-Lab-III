package usuarios;

import java.time.LocalDate;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class Usuario {

	private String mail;
	private String clave;
	private LocalDate fechaDeInicio;

	// constructores
	public Usuario(String mail, String clave) {
		this.mail = mail;
		this.clave = clave;
		fechaDeInicio= LocalDate.now();
	}
	
	// constructores
		public Usuario() {
			this.mail = "";
			this.clave = "";
			fechaDeInicio= null;
		}

	// getters y setters
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public LocalDate getFechaDeInicio() {
		return fechaDeInicio;
	}

	public void setFechaDeInicio(LocalDate fechaDeInicio) {
		this.fechaDeInicio = fechaDeInicio;
	}
	
	@Override
	public String toString() {
		return "\nMail: "+ getMail() +"\nClave: "+getClave() +"\nFecha de Inicio: "+getFechaDeInicio();
	}
	

	public abstract JSONObject devolverJsonObject() throws JSONException;
	
}
