package usuarios;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import Interfaces.I_RUD;
import contenedoresGenericos.GenericHashMap;
import excepciones.ExcepcionExistencia;

public class ListaUsuarios implements I_RUD<Usuario>, Serializable {
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
	public boolean existeMail(String mail)
	{
		Iterator<Map.Entry<String, Usuario>> iterador = MapUsuarios.iterador();
		boolean esIgual =false;
		Usuario actual = null;
		while (iterador.hasNext() && esIgual==false)
		{
			Map.Entry<String, Usuario> entrada = iterador.next();
			actual = entrada.getValue();
			if(actual.getMail().equals(mail)) {
				esIgual =true;
			}
		}
		return esIgual;
	}

	// Comprobar existencia de un Usuario en la lista
	public boolean contieneKey(String mail) {
		return MapUsuarios.contieneKey(mail);
	}
	
	// Iterador 
	public Iterator<Entry<String, Usuario>> iterador() {
		return MapUsuarios.iterador();
	}

	// Ver si la lista de Usuarios está vacía
	@Override
	public boolean estaVacia() {
		return MapUsuarios.estaVacio();
	}
	
	// Borrar un Usuario de la lista
	public void borrar(String mail) {
		MapUsuarios.eliminar(mail);
	}

	// Buscar un Usuario en la lista
	@Override
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
	@Override
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

	@Override
	public void borrar(Usuario o) {
		MapUsuarios.eliminarUsuario(o);
	}
	
	/*
	public JSONArray usuariosToJSON()
	{
		JSONArray arrayUsuarios = new JSONArray();
		
		Iterator<Map.Entry<String,Usuario>> iterador = MapUsuarios.iterador();
		
		while(iterador.hasNext())
		{
			Map.Entry<String,Usuario> entry = iterador.next();
			Usuario actual = entry.getValue();
			arrayUsuarios.put(actual);
		}
		
		return arrayUsuarios;
	}
	*/


}
