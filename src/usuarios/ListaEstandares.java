package usuarios;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import contenedoresGenericos.GenericHashMap;
import excepciones.ExcepcionExistencia;
import Interfaces.I_RUD;

public class ListaEstandares implements I_RUD<Estandar>{

    private GenericHashMap<String,Estandar>hashmapUsuariosEstandar;

    // constructores
    public ListaEstandares()
    {
    	hashmapUsuariosEstandar = new GenericHashMap<String,Estandar>();
    }


    // métodos
    @Override
    public String mostrarTodo() {
		String contenido="";
		
		Iterator<Map.Entry<String,Estandar>> iterador = hashmapUsuariosEstandar.iterador();
		while(iterador.hasNext()) {
			contenido += iterador.next().toString();
		}
		return contenido; 
	}
    
    @Override
    public void agregar(Estandar o){
    	try
    	{
    	if(!hashmapUsuariosEstandar.contieneValue(o))
    	{
    		hashmapUsuariosEstandar.agregar(o.getMail(), o);
    	}
    	else
    	{
    		throw new ExcepcionExistencia("\n> El usuario "+o.getMail()+" ya existe");
    	}	
    	}
    	catch(ExcepcionExistencia e)
		{
			System.out.println(e.getMessage());
		}
    	
    	
    }

    @Override
    public void borrar(Estandar o) {
        hashmapUsuariosEstandar.eliminar(o.getMail());
    }

    @Override
    public Estandar buscar(String email) {
    	Iterator<Map.Entry<String, Estandar>> iterador = hashmapUsuariosEstandar.iterador();
		boolean existe = false;
		Estandar aRetornar = null;
		Estandar actual;
		
		while (iterador.hasNext() && existe == false) {
			Map.Entry<String, Estandar> entrada = iterador.next();
			actual = entrada.getValue();
			if (email.equalsIgnoreCase(actual.getMail())) {
				aRetornar = actual;
				existe = true;
			}
		}	
		
		return aRetornar;

   }

    // getters y setters
    public GenericHashMap<String, Estandar> getHashmapUsuariosEstandar() {
    		return hashmapUsuariosEstandar;
    }

    public void setHashmapUsuariosEstandar(GenericHashMap<String, Estandar> hashmapUsuariosEstandar) {
    		this.hashmapUsuariosEstandar = hashmapUsuariosEstandar;
    }
    


}
