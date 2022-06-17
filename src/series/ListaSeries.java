package series;

import java.util.Iterator;
import java.util.Map;

import contenedoresGenericos.GenericHashMap;
import excepciones.ExcExistencia;
import Interfaces.I_RUD;

public class ListaSeries implements I_RUD<Serie>{
	private GenericHashMap<Integer, Serie>hashmapSeries;
	
	@Override
	public String mostrarTodo() {
		String contenido="";
		Iterator<Map.Entry<Integer,Serie>> iterador = hashmapSeries.iterador();
		while(iterador.hasNext()) {
			contenido += iterador.next().toString();
		}
		return contenido; 
	}

	@Override
	public void agregar(Serie o){
		try
		{
		if(!hashmapSeries.contieneValue(o))
		{
			hashmapSeries.agregar(o.getId(), o);
		}
		else
		{
			throw new ExcExistencia("\n> La serie "+o.getNombre()+" ya existe");
		}	
		}
		catch(ExcExistencia e)
		{
			System.out.println(e.getMessage());
		}
		
		
	}

	@Override
	public void borrar(Serie o) {
		hashmapSeries.eliminar(o.getId());
		
	}

	@Override
	public Serie buscar(String nombre) {
	    	Iterator<Map.Entry<Integer, Serie>> iterador = hashmapSeries.iterador();
			boolean existe = false;
			Serie aRetornar = null;
			Serie actual;
			
			while (iterador.hasNext() && existe == false) {
				Map.Entry<Integer, Serie> entrada = iterador.next();
				actual = entrada.getValue();
				if (nombre.equalsIgnoreCase(actual.getNombre())) {
					aRetornar = actual;
					existe = true;
				}
			}	
			
			return aRetornar;

	   
	}

}
