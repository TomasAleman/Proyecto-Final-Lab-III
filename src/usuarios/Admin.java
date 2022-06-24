package usuarios;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;
import peliculas.ListaPeliculas;
import series.ListaSeries;

public class Admin extends Usuario implements Serializable{

	private int pelisAgregadas;
	private int seriesAgregadas;
	private int pelisModificadas;
	private int seriesModificadas;

	// constructores
	public Admin (String mail, String clave) {
		super(mail, clave);
		pelisAgregadas = 0;
		seriesAgregadas = 0;
	}
	
	public Admin () {
		super();
		pelisAgregadas = 0;
		seriesAgregadas = 0;
	}

	// GETTERS Y SETTERS
	public int getPelisAgregadas() {
		return pelisAgregadas;
	}

	public void setPelisAgregadas(int pelisAgregadas) {
		this.pelisAgregadas = pelisAgregadas;
	}

	public int getSeriesAgregadas() {
		return seriesAgregadas;
	}

	public void setSeriesAgregadas(int seriesAgregadas) {
		this.seriesAgregadas = seriesAgregadas;
	}

	public int getPelisModificadas() {
		return pelisModificadas;
	}

	public void setPelisModificadas(int pelisModificadas) {
		this.pelisModificadas = pelisModificadas;
	}

	public int getSeriesModificadas() {
		return seriesModificadas;
	}

	public void setSeriesModificadas(int seriesModificadas) {
		this.seriesModificadas = seriesModificadas;
	}

	
	@Override
	public String toString() {
		
		return "\n- Administrador - "+super.toString()+"\nPeliculas Agregadas: "+getPelisAgregadas()+ "\nSeries Agregadas: "+getSeriesAgregadas()+ "\nPeliculas modificadas: "+getPelisModificadas()+ "\nSeries modificadas: "+getSeriesModificadas();
	}
	
	@Override
	public JSONObject devolverJsonObject() throws JSONException {
		JSONObject admin = new JSONObject();
		
		admin.put("Mail",getMail());
		admin.put("Clave",getClave());
		admin.put("# Películas agregadas", getPelisAgregadas());
		admin.put("# Películas modificadas", getPelisModificadas());
		admin.put("# Series agregadas", getSeriesAgregadas());
		admin.put("# Series modificadas", getSeriesModificadas());
		
		return admin;
	}
}
