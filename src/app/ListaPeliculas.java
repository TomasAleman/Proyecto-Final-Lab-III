package app;

import java.util.Iterator;
import java.util.Map;

public class ListaPeliculas implements I_rud{
	
	private GenericHashMap<Integer, Pelicula> lista;
	
	public ListaPeliculas() {
		lista = new GenericHashMap<Integer, Pelicula>();
	}

	@Override
	public void agregar(Object o) {
		Pelicula aAgregar = (Pelicula)o;
		lista.agregar(aAgregar.getId(), aAgregar);
	}

	@Override
	public void borrar(Object o) {
		Pelicula aEliminar = (Pelicula)o;
		lista.eliminar(aEliminar.getId());
	}

	@Override
	public Object buscar(String nombre) {
	    Iterator<Map.Entry<Integer, Pelicula>> filas= lista.iterador();
		boolean existe = false;
		Pelicula aRetornar = new Pelicula();
		while (filas.hasNext() && existe == false) {
			Map.Entry<Integer, Pelicula> entrada = filas.next();
			aRetornar = entrada.getValue();
			if (nombre.equalsIgnoreCase(aRetornar.getNombre())) {
				existe = true;
				if (existe == true) {	
				}
			}
		}	
		if(existe == true) {
			return aRetornar;
		}else {
			return null;
		}
	}

}
