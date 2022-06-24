package peliculas;

import java.util.Iterator;
import java.util.Map;

import contenedoresGenericos.GenericHashMap;
import contenedoresGenericos.GenericHashSet;
import excepciones.ExcepcionExistencia;
import Interfaces.I_RUD;

public class ListaPeliculas implements I_RUD<Pelicula>{
	
	private GenericHashSet <Pelicula> hashSetPeliculas;
	
	public ListaPeliculas() {
		hashSetPeliculas = new GenericHashSet<>();
	}
	
	@Override
    public String mostrarTodo() {
		String contenido="";
		Iterator<Pelicula> iterador = hashSetPeliculas.iterador();
		while(iterador.hasNext()) {
			contenido += iterador.next().toString();
		}
		return contenido;  
	}

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
	public void borrar(Pelicula o) 
	{
		hashSetPeliculas.eliminar(o);
	}

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

}
