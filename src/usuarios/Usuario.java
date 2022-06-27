package usuarios;

import java.io.Serializable;
import java.time.LocalDate;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class Usuario implements Serializable {
	private boolean estado = true; // true: activo, false: inactivo
	private String mail;
	private String clave;
	private LocalDate fechaDeRegistro;

	// Constructores
	public Usuario(String mail, String clave) {
		isEstado();
		this.mail = mail;
		this.clave = clave;
		fechaDeRegistro = LocalDate.now();
	}

	public Usuario() {
		isEstado();
		mail = "";
		clave = "";
		fechaDeRegistro = null;
	}

	// Getters y Setters
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

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

	public LocalDate getFechaDeRegistro() {
		return fechaDeRegistro;
	}

	public void setFechaDeRegistro(LocalDate fechaDeRegistro) {
		this.fechaDeRegistro = fechaDeRegistro;
	}

	// ---------------------------------------- OVERRIDES
	@Override
	public String toString() {
		String estado = "";

		if (isEstado() == true) {
			estado = "activo";
		} else {
			estado = "dado de baja";
		}
		return "\n * Estado: " + estado + "\n * Mail: " + getMail() + "\n * Clave: " + getClave()
				+ "\n * Fecha de Registro: " + getFechaDeRegistro() + "\n";
	}

	// ---------------------------------------- FIRMA del MÉTODO JSON
	public abstract JSONObject usuarioToJSON() throws JSONException;

}
