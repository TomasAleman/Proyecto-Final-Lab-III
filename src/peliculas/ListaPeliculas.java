package peliculas;

import java.io.Serializable;
import java.util.Iterator;

import contenedoresGenericos.GenericHashSet;
import excepciones.ExcepcionExistencia;
import Interfaces.I_RUD;

public class ListaPeliculas implements I_RUD<Pelicula>, Serializable{
	
	private GenericHashSet <Pelicula> hashSetPeliculas;
	
	// Constructor
	public ListaPeliculas() {
		hashSetPeliculas = new GenericHashSet<>();
	}
	
	// ---------------------------------------- M?TODOS de LISTAELEMENTOS
	
	// Mostrar todas las Peliculas de una lista
	@Override
    public String mostrarTodo() {
		String contenido="";
		Iterator<Pelicula> iterador = hashSetPeliculas.iterador();
		while(iterador.hasNext()) {
			contenido += iterador.next().toString();
		}
		return contenido;  
	}
	
	// Borrar una Pelicula de la lista
	@Override
	public void borrar(Pelicula o) 
	{
		hashSetPeliculas.eliminar(o);
	}
	
	// Buscar una Pelicula en la lista
	@Override
	public Pelicula buscar(String nombre) {
	  Iterator<Pelicula> iterador = hashSetPeliculas.iterador();
			boolean existe = false;
			Pelicula aRetornar = null;
			Pelicula actual;
			
			while (iterador.hasNext() && existe == false) {
				actual = iterador.next();
				if (nombre.equalsIgnoreCase(actual.getNombre())) {
					aRetornar = actual;
					existe = true;
				}
			}	
			
			return aRetornar;
	}

	// Agregar una Pelicula a la lista
	@Override
	public void agregar(Pelicula o){
		try
		{
		if(!hashSetPeliculas.contiene(o))
		{
		hashSetPeliculas.agregar(o);;
		}
		else
		{
			throw new ExcepcionExistencia("\n> La pelicula "+o.getNombre()+" ya existe");
		}	
		}
		catch(ExcepcionExistencia e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public boolean estaVacia() {
		return hashSetPeliculas.estaVacio();
	}
}
