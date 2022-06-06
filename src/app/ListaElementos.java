package app;

import java.util.Iterator;

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

	// Métodos
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
	public void agregar(Object o) {
		Elemento aAgregar = (Elemento) o;
		hashSetElementos.agregar(aAgregar);
	}

	@Override
	public void borrar(Object o) {
		Elemento aBorrar = (Elemento) o;
		hashSetElementos.eliminar(aBorrar);
	}

	@Override
	public Object buscar(String nombre) {
		Iterator<Elemento> iterador = hashSetElementos.iterador();
		boolean existe = false;
		Elemento aRetornarPeli = new Pelicula();
		Elemento aRetornarSerie = new Serie();
		int i =0;
		while (iterador.hasNext() && existe == false) {
			System.out.println("vuelta "+i);
			i++;
			if (iterador.next() instanceof Pelicula) {
				aRetornarPeli = iterador.next();
				if (nombre.equalsIgnoreCase(aRetornarPeli.getNombre())) {
					existe = true;
					if (existe == true) {
						return aRetornarPeli;
					}
				}
			} else if (iterador.next() instanceof Serie){
				aRetornarSerie = iterador.next();
				if (nombre.equalsIgnoreCase(aRetornarSerie.getNombre())) {
					existe = true;
					if (existe == true) {
						return aRetornarSerie;
					}
				}
			}
		}
		return null;
	}
	

}
