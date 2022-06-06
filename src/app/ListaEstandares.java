package app;

import java.util.Iterator;
import java.util.Map;

import Interfaces.I_rud;

public class ListaEstandares implements I_rud{

    private GenericHashMap<String,Estandar>hashmapUsuariosEstandar;

    // constructores
    public ListaEstandares()
    {
    }

    public ListaEstandares(GenericHashMap<String,Estandar>hashmapUsuariosEstandar)
    {
        this.hashmapUsuariosEstandar = hashmapUsuariosEstandar;
    }

    // métodos
    @Override
    public void agregar(Object o) {
    	Estandar aAgregar = (Estandar)o;
    	hashmapUsuariosEstandar.agregar(aAgregar.getMail(), aAgregar);
    }

    @Override
    public void borrar(Object o) {
    	Estandar aBorrar = (Estandar)o;
        hashmapUsuariosEstandar.eliminar(aBorrar.getMail());
    }

    @Override
    public Object buscar(String email) {
    	Iterator<Map.Entry<String, Estandar>> iterador = hashmapUsuariosEstandar.iterador();
		boolean existe = false;
		Estandar aRetornar = new Estandar();
		while (iterador.hasNext() && existe == false) {
			Map.Entry<String, Estandar> entrada = iterador.next();
			aRetornar = entrada.getValue();
			if (email.equalsIgnoreCase(aRetornar.getMail())) {
				existe = true;
			}
		}	
		if(existe == true) {
			return aRetornar;
		}else {
			return null;
		}


   }

    // getters y setters
    public GenericHashMap<String, Estandar> getHashmapUsuariosEstandar() {
    		return hashmapUsuariosEstandar;
    }

    public void setHashmapUsuariosEstandar(GenericHashMap<String, Estandar> hashmapUsuariosEstandar) {
    		this.hashmapUsuariosEstandar = hashmapUsuariosEstandar;
    }
    


}
