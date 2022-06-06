package app;

public class Perfil {
	private String nombre;
	private boolean infantil; // true si es cuenta para menores
	private ListaElementos miLista; // lista de P/S que tiene cada perfil

	// constructores
	public Perfil() {

	}

	public Perfil(String nombre, boolean infantil) {
		this.nombre = nombre;
		this.infantil = infantil;
        miLista = new ListaElementos();
	}

	// getters y setters
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
}
