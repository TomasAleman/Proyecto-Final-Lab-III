package contenedoresGenericos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class GenericArrayList<T> implements Serializable {
	
	public ArrayList<T> lista;

	public GenericArrayList() {
		lista= new ArrayList<T>();
	}
	
	public void agregar(T elem) {
		lista.add(elem);
	}
	
	public int tamanio() {
		return lista.size();
	}
	
	public void eliminarTodo() {
		lista.clear();
	}
	
	public boolean estaVacio() {
		return lista.isEmpty();
	}
	
	public void eliminarPorIndice(int indice) {
		lista.remove(indice);
	}
	
	public void eliminarPerfil(T elem) {
		lista.remove(elem);
	}
	
	public Iterator<T> iterador() {
		return lista.iterator();
	}
	
	public boolean contiene (T elem) {
		return lista.contains(elem);
	}
	
	public T devolver (int indice) {
		return lista.get(indice);
	}

}
