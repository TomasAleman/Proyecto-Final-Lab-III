package usuarios;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

import contenedoresGenericos.GenericHashMap;
import excepciones.ExcepcionExistencia;
import Interfaces.I_RUD;

public class ListaAdmins implements I_RUD<Admin>, Serializable{

	private GenericHashMap<String, Admin> hashmapAdmins;
	
	
	// Constructor
	public ListaAdmins () {
		hashmapAdmins = new GenericHashMap<String, Admin>();
	}

	// Getters y Setters
	public GenericHashMap<String, Admin> getHashmapAdmins() {
		return hashmapAdmins;
	}

	public void setLista(GenericHashMap<String, Admin> hashmapAdmins) {
		this.hashmapAdmins = hashmapAdmins;
	}

	// ---------------------------------------- MÉTODOS

	// Buscar por Mail en la lista
	public boolean contieneMail(String mail)
	{
		return hashmapAdmins.contieneKey(mail);
	}
	
	// Mostrar todos los admins de la lista
	@Override
    public String mostrarTodo() {
		String contenido="";
		Iterator<Map.Entry<String,Admin>> iterador = hashmapAdmins.iterador();
		while(iterador.hasNext()) {
			contenido += iterador.next().toString();
		}
		return contenido; 
	}
	
	// Borrar un Admin de la lista
		@Override
		public void borrar(Admin o) {
			hashmapAdmins.eliminar(o.getMail());
		}

		// Buscar un Admin en la lista 
		@Override
		public Admin buscar(String email) {
			Iterator<Map.Entry<String, Admin>> iterador = hashmapAdmins.iterador();
			boolean existe = false;
			Admin aRetornar = null;
			Admin actual;
			
			while (iterador.hasNext() && existe == false) {
				Map.Entry<String, Admin> entrada = iterador.next();
				actual = entrada.getValue();
				if (email.equalsIgnoreCase(actual.getMail())) {
					existe = true;
					aRetornar = actual;
				}
			}	
			return aRetornar;
		}
	
	// Agregar un Admin a la lista
	@Override
	public void agregar(Admin o){
		try
		{
			if(!hashmapAdmins.contieneValue(o))
		{
			hashmapAdmins.agregar(o.getMail(), o);
		}
		else
		{
			throw new ExcepcionExistencia("\n> El admin "+o.getMail()+" ya existe");
		}
		}
		catch(ExcepcionExistencia e)
		{
			System.out.println(e.getMessage());
		}
	}
}
