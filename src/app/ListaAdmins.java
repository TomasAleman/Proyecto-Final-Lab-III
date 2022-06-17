package app;

import java.util.Iterator;
import java.util.Map;

import Interfaces.I_rud;

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
	public void borrar(String nombre) {
		Admin aEliminar =this.buscar(nombre);
		lista.eliminar(aEliminar.getMail());
	}

	@Override
	public Admin buscar(String email) {
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

	@Override
	public String listar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
}
