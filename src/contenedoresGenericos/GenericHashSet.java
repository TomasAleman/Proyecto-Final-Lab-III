package contenedoresGenericos;

import java.util.HashSet;
import java.util.Iterator;

public class GenericHashSet<T> {

	HashSet<T> hashSet;

	public GenericHashSet() {
		hashSet = new HashSet<>();
	}

	public void setHashSet(HashSet<T> hashSet) {
		this.hashSet = hashSet;
	}

	public HashSet<T> getHashSet() {
		return hashSet;
	}
	
	public void agregar(T elem) {
		hashSet.add(elem);
	}
	
	public int tamanio() {
		return hashSet.size();
	}
	
	public void eliminarTodo() {
		hashSet.clear();
	}
	
	public boolean estaVacio() {
		return hashSet.isEmpty();
	}
	
	public void eliminar(T elem) {
		hashSet.remove(elem);
	}
	
	public Iterator<T> iterador() {
		return hashSet.iterator();
	}
	
	public boolean contiene (T elem) {
		return hashSet.contains(elem);
	}

}
