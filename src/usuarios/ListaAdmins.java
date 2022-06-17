package usuarios;

import java.util.Iterator;
import java.util.Map;

import contenedoresGenericos.GenericHashMap;
import excepciones.ExcExistencia;
import Interfaces.I_RUD;

public class ListaAdmins implements I_RUD<Admin>{

	private GenericHashMap<String, Admin> hashmapAdmins;
	
	
	//Constructor
	public ListaAdmins () {
		hashmapAdmins = new GenericHashMap<String, Admin>();
	}

	//Getters y Setters
	public GenericHashMap<String, Admin> getLista() {
		return hashmapAdmins;
	}

	public void setLista(GenericHashMap<String, Admin> hashmapAdmins) {
		this.hashmapAdmins = hashmapAdmins;
	}

	//metodos
	
	@Override
    public String mostrarTodo() {
		String contenido="";
		Iterator<Map.Entry<String,Admin>> iterador = hashmapAdmins.iterador();
		while(iterador.hasNext()) {
			contenido += iterador.next().toString();
		}
		return contenido; 
	}
	
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
			throw new ExcExistencia("\n> El admin "+o.getMail()+" ya existe");
		}
		}
		catch(ExcExistencia e)
		{
			System.out.println(e.getMessage());
		}
		
		
		
	}

	@Override
	public void borrar(Admin o) {
		hashmapAdmins.eliminar(o.getMail());
	}

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
	
	
	
	
	
}
