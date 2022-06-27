package usuarios;

import contenedoresGenericos.GenericArrayList;
import excepciones.ExcepcionCantidadPerfiles;

import java.io.Serializable;

import org.json.JSONArray;

import Interfaces.I_RUD;

public class ListaPerfiles implements I_RUD<Perfil>, Serializable{
	private final int MAX_PERFILES = 4;
	private GenericArrayList<Perfil> listaPerfiles;

	// Constructor
	public ListaPerfiles() {
		listaPerfiles = new GenericArrayList<>();
	}
	
	// Getters y Setters
	public int getMAX_PERFILES() {
		return MAX_PERFILES;
	}

	
	// ---------------------------------------- MÉTODOS
	
	// Mostrar todos los Perfiles
	public String mostrarTodo() {
		String contenido = "";

		for (int i = 0; i < listaPerfiles.tamanio(); i++) {
			contenido += listaPerfiles.devolver(i).toString();
		}

		return contenido;
	}

	// Mostrar el Perfil que esté en un índice
	public String mostrarPorIndice(int indice) {
		String contenido = listaPerfiles.devolver(indice).toString();
		return contenido;
	}
	
	// Mostrar el nombre del Perfil que esté en un índice
	public String mostrarNombrePorIndice(int indice)
	{
		String nombre = listaPerfiles.devolver(indice).getNombre();
		return nombre;
	}
	
	// Retornar el Perfil que esté en un índice
	public Perfil retornar(int indice) {
		return listaPerfiles.devolver(indice);
	}

	// Obtener cantidad de Perfiles en la lista
	public int cantidadPerfiles() {
		return listaPerfiles.tamanio();
	}
	
	// Ver si la lista de Perfiles está vacía
	@Override
	public boolean estaVacia() {
		return listaPerfiles.estaVacio();
	}
	
	// Buscar un Perfil en la lista
	public Perfil buscar(String nombre) {
		Perfil aRetornar = null;
		boolean existe = false;
		int i = 0;

		while (i < listaPerfiles.tamanio() && existe == false) {
			if (listaPerfiles.devolver(i).getNombre().equalsIgnoreCase(nombre)) {
				aRetornar = listaPerfiles.devolver(i);
				existe = true;
			}

			i++;
		}

		return aRetornar;
	}

	// Borrar un Perfil de la lista
	@Override
	public void borrar(Perfil p) {
		listaPerfiles.eliminarPerfil(p);
	}

	// Agregar un Perfil a la lista
	@Override
	public void agregar(Perfil p) {
		try {
			if (listaPerfiles.tamanio() < MAX_PERFILES) {
				listaPerfiles.agregar(p);
			} else {
				throw new ExcepcionCantidadPerfiles("Máximo 4 perfiles por usuario");
			}
		} catch (ExcepcionCantidadPerfiles e) {
			System.out.println(e.getMessage());
		}

	}
	
	
	public JSONArray perfilesToJSON()
	{
		JSONArray arrayPerfiles = new JSONArray();
		
		for (int i=0; i<listaPerfiles.tamanio(); i++)
		{
			arrayPerfiles.put(listaPerfiles.devolver(i).perfilToJSON());
		}
		
		return arrayPerfiles;
	}
}
