package peliculas;

import java.util.Iterator;
import java.util.Map;

import contenedoresGenericos.GenericHashMap;
import excepciones.ExcExistencia;
import Interfaces.I_RUD;

public class ListaPeliculas implements I_RUD<Pelicula>{
	
	private GenericHashMap<Integer, Pelicula> hashmapPeliculas;
	
	public ListaPeliculas() {
		hashmapPeliculas = new GenericHashMap<Integer, Pelicula>();
	}
	
	@Override
    public String mostrarTodo() {
		String contenido="";
		Iterator<Map.Entry<Integer,Pelicula>> iterador = hashmapPeliculas.iterador();
		while(iterador.hasNext()) {
			contenido += iterador.next().toString();
		}
		return contenido; 
	}

	@Override
	public void agregar(Pelicula o){
		try
		{
		if(!hashmapPeliculas.contieneValue(o))
		{
		hashmapPeliculas.agregar(o.getId(), o);
		}
		else
		{
			throw new ExcExistencia("\n> La pelicula "+o.getNombre()+" ya existe");
		}	
		}
		catch(ExcExistencia e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void borrar(Pelicula o) 
	{
		hashmapPeliculas.eliminar(o.getId());
	}

	@Override
	public Pelicula buscar(String nombre) {
	    Iterator<Map.Entry<Integer, Pelicula>> iterador = hashmapPeliculas.iterador();
		boolean existe = false;
		Pelicula aRetornar = null;
		Pelicula actual;
		
		while (iterador.hasNext() && existe == false) {
			Map.Entry<Integer, Pelicula> entrada = iterador.next();
			actual = entrada.getValue();
			if (nombre.equalsIgnoreCase(actual.getNombre())) {
				aRetornar = actual;
				existe = true;
			}
		}	
		
		return aRetornar;
	}

}
