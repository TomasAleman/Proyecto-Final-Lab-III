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

	// Constructores
	public Admin (String mail, String clave) {
		super(mail, clave);
		pelisAgregadas = 0;
		seriesAgregadas = 0;
		pelisModificadas = 0;
		seriesModificadas = 0;
	}
	
	public Admin () {
		super();
		pelisAgregadas = 0;
		seriesAgregadas = 0;
		pelisModificadas = 0;
		seriesModificadas = 0;
	}

	// Getters y Setters
	public int getPelisAgregadas() {
		return pelisAgregadas;
	}

	public void setPelisAgregadas() {
		this.pelisAgregadas = pelisAgregadas++;
	}

	public int getSeriesAgregadas() {
		return seriesAgregadas;
	}

	public void setSeriesAgregadas() {
		this.seriesAgregadas = seriesAgregadas++;
	}

	public int getPelisModificadas() {
		return pelisModificadas;
	}

	public void setPelisModificadas() {
		this.pelisModificadas = pelisModificadas++;
	}

	public int getSeriesModificadas() {
		return seriesModificadas;
	}

	public void setSeriesModificadas() {
		this.seriesModificadas = seriesModificadas++;
	}

	// ---------------------------------------- OVERRIDES
	@Override
	public String toString() {
		
		return "\n- Administrador - "+super.toString()+"\nPeliculas Agregadas: "+getPelisAgregadas()+ "\nSeries Agregadas: "+getSeriesAgregadas()+ "\nPeliculas modificadas: "+getPelisModificadas()+ "\nSeries modificadas: "+getSeriesModificadas();
	}
	
	// ---------------------------------------- MÉTODO JSON
	@Override
	public JSONObject usuarioToJSON() throws JSONException {
		JSONObject admin = new JSONObject();
		
		admin.put("Mail",getMail());
		admin.put("Clave",getClave());
		admin.put("# Películas agregadas", getPelisAgregadas());
		admin.put("# Modificaciones a Peliculas", getPelisModificadas());
		admin.put("# Series agregadas", getSeriesAgregadas());
		admin.put("# Modificaciones a Series", getSeriesModificadas());
		
		return admin;
	}
}
