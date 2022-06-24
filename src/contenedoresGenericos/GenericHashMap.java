package contenedoresGenericos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class GenericHashMap<K,V> {
	
	private HashMap<K,V> hashMap;
	
	public GenericHashMap() {
		hashMap =new HashMap<>();
	}
	
	public void agregar(K key, V value) {
		hashMap.put(key, value);
	}
	
	public int tamanio() {
		return hashMap.size();
	}
	
	public void eliminarTodo() {
		hashMap.clear();
	}
	
	public boolean estaVacio() {
		return hashMap.isEmpty();
	}
	
	public void eliminar(K key) {
		hashMap.remove(key);
	}
	
	public boolean contieneKey (K key) {
		return hashMap.containsKey(key);
	}
	
	public boolean contieneValue (V value) {
		return hashMap.containsValue(value);
	}
	
	public Iterator<Entry<K, V>> iterador () {
		return hashMap.entrySet().iterator();
	}

}
