package usuarios;

import org.json.JSONException;
import org.json.JSONObject;
import peliculas.ListaPeliculas;
import series.ListaSeries;

public class Admin extends Usuario{

	private Integer pelisAgregadas;
	private Integer seriesAgregadas;
	private ListaPeliculas pelisModificadas;
	private ListaSeries seriesModificadas;

	// constructores
	public Admin (String mail, String clave) {
		super(mail,clave);
		pelisAgregadas=0;
		seriesAgregadas=0;
	}
	
	public Admin () {
		super();
		pelisAgregadas=0;
		seriesAgregadas=0;
	}

	// métodos de interfaz
	public void agregar()
	{
		
	}



	// setters y getters
	public Integer getPelisAgregadas() {
		return pelisAgregadas;
	}

	public void setPelisAgregadas(Integer pelisAgregadas) {
		this.pelisAgregadas = pelisAgregadas;
	}

	public Integer getSeriesAgregadas() {
		return seriesAgregadas;
	}

	public void setSeriesAgregadas(Integer seriesAgregadas) {
		this.seriesAgregadas = seriesAgregadas;
	}

	public ListaPeliculas getPelisModificadas() {
		return pelisModificadas;
	}

	public void setPelisModificadas(ListaPeliculas pelisModificadas) {
		this.pelisModificadas = pelisModificadas;
	}

	public ListaSeries getSeriesModificadas() {
		return seriesModificadas;
	}

	public void setSeriesModificadas(ListaSeries seriesModificadas) {
		this.seriesModificadas = seriesModificadas;
	}

	
	@Override
	public String toString() {
		
		return "\n- Administrador - "+super.toString()+"\nPeliculas Agregadas: "+getPelisAgregadas()+ "\nSeries Agregadas: "+getSeriesAgregadas()+ "\nPeliculas modificadas: "+getPelisModificadas()+ "\nSeries modificadas: "+getSeriesModificadas();
	}
	
	@Override
	public JSONObject devolverJsonObject() throws JSONException {
		JSONObject admin = new JSONObject();
		
		admin.put("\nMail: ",getMail());
		admin.put("\nClave: ",getClave());
		admin.put("\n# Películas agregadas: ", getPelisAgregadas());
		admin.put("\n# Películas modificadas: ", getPelisModificadas());
		admin.put("\n# Series agregadas: ", getSeriesAgregadas());
		admin.put("\n# Series modificadas: ", getSeriesModificadas());
		
		return admin;
	}
}
