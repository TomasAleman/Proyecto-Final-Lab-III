package app;

public class Pelicula extends Elemento {
	private String duracion;

	public Pelicula(String nombre, float puntaje, Genero genero, Clasificacion clasificacion, String descripcion,
			int anioDeEstreno,String elenco, String duracion) {
		super(nombre, puntaje, genero, clasificacion, descripcion, anioDeEstreno,elenco);
		this.duracion = duracion;
	}
	public Pelicula()
	{
		super();
		duracion="";
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	

}
