package usuarios;

import elementos.ListaElementos;

public class Perfil {
	//private static int cdadPerfiles = 0;
	//private int id;
	private String nombre;
	private boolean infantil; // true si es cuenta para menores
	private ListaElementos miLista; // lista de P/S que tiene cada perfil

	// constructores
	public Perfil() {

	}

	public Perfil(String nombre, boolean infantil) {
		//setId();
		this.nombre = nombre;
		this.infantil = infantil;
        miLista = new ListaElementos();
	}

	// getters y setters
	/*public int getId() {
		return cdadPerfiles;
	}

	public void setId() {
		id = cdadPerfiles + 1;
		cdadPerfiles++;
	}
	
	public int getCdadPerfiles() {
		return cdadPerfiles;
	}*/
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isInfantil() {
		return infantil;
	}

	public void setInfantil(boolean infantil) {
		this.infantil = infantil;
	}

	public ListaElementos getMiLista() {
		return miLista;
	}

	public void setMiLista(ListaElementos miLista) {
		this.miLista = miLista;
	}
	
	public String toString() {
		String inf = "";
		
		if(isInfantil() == true)
		{
			inf = "Infantil";
		}
		else{
			inf = "Adulto";
		}
		
		// falta toString de miLista
		return "Nombre: "+getNombre()+ "\nClasificacion: "+inf;
	}
}
