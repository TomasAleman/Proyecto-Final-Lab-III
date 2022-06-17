package app;

import java.util.Iterator;

import Interfaces.I_rud;

public class ListaElementos implements I_rud {

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

	// M�todos
	public boolean verSiExiste(Elemento elemento) {
		boolean existe = hashSetElementos.contiene(elemento);
		return existe;
	}

	@Override
	public String listar() {
		String contenido="";
		Iterator<Elemento> iterador = hashSetElementos.iterador();
		while(iterador.hasNext()) {
			contenido += iterador.next().toString();
		}
		return contenido;
	}

	@Override
	public void agregar(Object o) {
		Elemento aAgregar = (Elemento) o;
		hashSetElementos.agregar(aAgregar);
	}

	@Override
	public void borrar(String nombre) {
		Elemento aBorrar = this.buscar(nombre);
		hashSetElementos.eliminar(aBorrar);
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
