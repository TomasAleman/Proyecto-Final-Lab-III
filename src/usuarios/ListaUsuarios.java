package usuarios;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import contenedoresGenericos.GenericHashMap;
import excepciones.ExcepcionExistencia;

public class ListaUsuarios implements Serializable {
	private GenericHashMap<String, Usuario> MapUsuarios;

	// Constructores
	public ListaUsuarios() {
		MapUsuarios = new GenericHashMap<String, Usuario>();
	}

	public ListaUsuarios(GenericHashMap<String, Usuario> MapUsuarios) {
		this.MapUsuarios = MapUsuarios;
	}

	// Getters y setters
	public GenericHashMap<String, Usuario> getHashsetUsuarios() {
		return MapUsuarios;
	}

	public void setHashSetElementos(GenericHashMap<String, Usuario> MapUsuarios) {
		this.MapUsuarios = MapUsuarios;
	}

	// ---------------------------------------- MÉTODOS
	
	// Mostrar todos los Usuarios de la lista
	public String mostrarTodo() {
		String contenido = "";
		Iterator<Map.Entry<String, Usuario>> iterador = MapUsuarios.iterador();
		while (iterador.hasNext()) {
			contenido += iterador.next().toString();
		}
		return contenido;
	}

	// Comprobar existencia de un Usuario en la lista
	public boolean contieneMail(String mail) {
		return MapUsuarios.contieneKey(mail);
	}
	
	// Iterar 
	public Iterator<Entry<String, Usuario>> iterador() {
		return MapUsuarios.iterador();
	}

	// Ver si la lista de Usuarios está vacía
	public boolean estaVacia() {
		return MapUsuarios.estaVacio();
	}
	
	// Borrar un Usuario de la lista
	public void borrar(String mail) {
		MapUsuarios.eliminar(mail);
	}

	// Buscar un Usuario en la lista
	public Usuario buscar(String mail) {
		Iterator<Map.Entry<String, Usuario>> iterador = MapUsuarios.iterador();
		boolean existe = false;
		Usuario aRetornar = null;
		Usuario actual;

		while (iterador.hasNext() && existe == false) {
			Map.Entry<String, Usuario> entrada = iterador.next();
			actual = entrada.getValue();
			if (actual.getMail().equals(mail)) {
				aRetornar = actual;
				existe = true;
			}
		}
		return aRetornar;
	}
	
	// Agregar un Usuario a la lista
	public void agregar(Usuario user) {
		try {
			if (MapUsuarios.contieneKey(user.getMail()) == false) {
				MapUsuarios.agregar(user.getMail(), user);
			} else {
				throw new ExcepcionExistencia("\n> El email " + user.getMail() + " ya está registrado");
			}
		} catch (ExcepcionExistencia e) {
			System.out.println(e.getMessage());
		}

	}
}
