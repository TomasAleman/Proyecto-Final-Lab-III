package app;

import java.util.Iterator;
import java.util.Map;

public class ListaAdmins implements I_rud{

	private GenericHashMap<String, Admin> lista ;
	
	
	//Constructor
	public ListaAdmins () {
		lista = new GenericHashMap<String, Admin>();
	}

	//Getters y Setters
	public GenericHashMap<String, Admin> getLista() {
		return lista;
	}

	public void setLista(GenericHashMap<String, Admin> lista) {
		this.lista = lista;
	}

	//metodos
	
	@Override
	public void agregar(Object o) {
		Admin aAgregar = (Admin)o;
		lista.agregar(aAgregar.getMail(), aAgregar);
		
	}

	@Override
	public void borrar(Object o) {
		Admin aEliminar = (Admin)o;
		lista.eliminar(aEliminar.getMail());
	}

	@Override
	public Object buscar(String email) {
		Iterator<Map.Entry<String, Admin>> iterador = lista.iterador();
		boolean existe = false;
		Admin aRetornar = new Admin();
		while (iterador.hasNext() && existe == false) {
			Map.Entry<String, Admin> entrada = iterador.next();
			aRetornar = entrada.getValue();
			if (email.equalsIgnoreCase(aRetornar.getMail())) {
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
