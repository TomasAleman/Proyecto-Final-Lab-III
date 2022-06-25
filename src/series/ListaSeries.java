package series;

import java.io.Serializable;
import java.util.Iterator;

import contenedoresGenericos.GenericHashSet;
import excepciones.ExcepcionExistencia;
import Interfaces.I_RUD;

public class ListaSeries implements I_RUD<Serie>, Serializable {
	
	private GenericHashSet<Serie>hashSetSeries;
	
	// Constructor
	public ListaSeries() {
		hashSetSeries = new GenericHashSet<>();
	}
	
	// ---------------------------------------- MÉTODOS de LISTASERIES
	
	// Mostrar todas las Series de una lista
	@Override
	public String mostrarTodo() {
		String contenido="";
		Iterator<Serie> iterador = hashSetSeries.iterador();
		while(iterador.hasNext()) {
			contenido += iterador.next().toString();
		}
		return contenido; 
	}

	// Borrar una Serie de la lista
	@Override
	public void borrar(Serie o) {
		hashSetSeries.eliminar(o);
	}
	
	// Buscar una Pelicula en la lista
	@Override
	public Serie buscar(String nombre) {
	    	Iterator<Serie> iterador = hashSetSeries.iterador();
			boolean existe = false;
			Serie aRetornar = null;
			Serie actual;
			
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
		public void agregar(Serie o){
			try
			{
			if(!hashSetSeries.contiene(o))
			{
				hashSetSeries.agregar(o);
			}
			else
			{
				throw new ExcepcionExistencia("\n> La serie "+o.getNombre()+" ya existe");
			}	
			}
			catch(ExcepcionExistencia e)
			{
				System.out.println(e.getMessage());
			}
		}
}
