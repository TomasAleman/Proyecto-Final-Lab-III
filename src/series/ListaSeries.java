package series;

import java.util.Iterator;
import java.util.Map;

import contenedoresGenericos.GenericHashMap;
import contenedoresGenericos.GenericHashSet;
import excepciones.ExcepcionExistencia;
import Interfaces.I_RUD;

public class ListaSeries implements I_RUD<Serie> {
	
	private GenericHashSet<Serie>hashSetSeries;
	
	@Override
	public String mostrarTodo() {
		String contenido="";
		Iterator<Serie> iterador = hashSetSeries.iterador();
		while(iterador.hasNext()) {
			contenido += iterador.next().toString();
		}
		return contenido; 
	}

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

	@Override
	public void borrar(Serie o) {
		hashSetSeries.eliminar(o);
	}

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

}
