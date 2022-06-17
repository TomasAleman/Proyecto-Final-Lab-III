package elementos;

import java.util.Iterator;
import Interfaces.I_RUD;
import contenedoresGenericos.GenericHashSet;
import excepciones.ExcExistencia;
import peliculas.Pelicula;
import series.Serie;

public class ListaElementos implements I_RUD<Elemento> {

	private GenericHashSet<Elemento> hashSetElementos;

	// Constructores
	public ListaElementos() {
		hashSetElementos = new GenericHashSet<Elemento>();
	}

	public ListaElementos(GenericHashSet<Elemento> hashSetElementos) {
		this.hashSetElementos = hashSetElementos;
	}

	// getters y setters
	public GenericHashSet<Elemento> getHashSetElementos() {
		return hashSetElementos;
	}

	public void setHashSetElementos(GenericHashSet<Elemento> hashSetElementos) {
		this.hashSetElementos = hashSetElementos;
	}

	// Métodos
	public Iterator iterador()
	{
		return hashSetElementos.iterador();
	}
	
	public boolean verSiExiste(Elemento elemento) {
		boolean existe = hashSetElementos.contiene(elemento);
		return existe;
	}
	

	public String mostrarTodo() {
		String contenido="";
		Iterator<Elemento> iterador = hashSetElementos.iterador();
		while(iterador.hasNext()) {
			contenido += iterador.next().toString();
		}
		return contenido;
	}

	@Override
	public void agregar(Elemento o){
		try
		{
			if(hashSetElementos.contiene(o) == false)
			{
				hashSetElementos.agregar(o);
			}
			else
			{
				throw new ExcExistencia("\n> El elemento "+o.getNombre()+" ya existe");
			}
		}
		catch(ExcExistencia e)
		{
			System.out.println(e.getMessage()); // no se me ocurre a dónde mandar el sout, y si se maneja con throw te corta la ejecución al repetir un elemento
		}
						
	}

	@Override
	public void borrar(Elemento o) {
		
		hashSetElementos.eliminar(o);
	}

	@Override
	public Elemento buscar(String nombre) {
		Iterator<Elemento> iterador = hashSetElementos.iterador();
		boolean existe = false;
		Elemento aRetornar = null;
		Elemento actual;		
		
		while (iterador.hasNext() && existe == false) {
			actual = iterador.next();
			if (actual instanceof Pelicula) {
				
				if (nombre.equalsIgnoreCase(actual.getNombre())) {
					existe = true;
						aRetornar = actual;
				}
			} else if (actual instanceof Serie){
				
				if (nombre.equalsIgnoreCase(actual.getNombre())) {
					existe = true;
						aRetornar = actual;
				}
			}
		}
	return aRetornar;
	}
	

}
