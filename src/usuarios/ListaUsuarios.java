package usuarios;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import contenedoresGenericos.GenericHashMap;
import excepciones.ExcepcionExistencia;


public class ListaUsuarios {
	private GenericHashMap<String,Usuario> MapUsuarios;
	
	// Constructores
		public ListaUsuarios() {
			MapUsuarios = new GenericHashMap<String,Usuario>();
		}

		public ListaUsuarios(GenericHashMap<String,Usuario> MapUsuarios) {
			this.MapUsuarios = MapUsuarios;
		}

		// getters y setters
		public GenericHashMap<String,Usuario> getHashsetUsuarios() {
			return MapUsuarios;
		}

		public void setHashSetElementos(GenericHashMap<String,Usuario> MapUsuarios) {
			this.MapUsuarios = MapUsuarios;
		}

		// Métodos
		
		public boolean verSiExiste(String mail) {
			boolean existe = MapUsuarios.contieneKey(mail);
			return existe;
		}
		

			public String mostrarTodo() {
				String contenido="";
				Iterator<Map.Entry<String,Usuario>> iterador = MapUsuarios.iterador();
				while(iterador.hasNext()) {
					contenido += iterador.next().toString();
				}
				return contenido; 
			}

		public void agregar(Usuario user){
			try
			{
				if(MapUsuarios.contieneKey(user.getMail()) == false)
				{
					MapUsuarios.agregar(user.getMail(),user);
				}
				else
				{
					throw new ExcepcionExistencia("\n> El email "+user.getMail()+" ya está registrado");
				}
			}
			catch(ExcepcionExistencia e)
			{
				System.out.println(e.getMessage()); 
			}
							
		}

		public void borrar(String mail) 
		{
			MapUsuarios.eliminar(mail);
		}

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

		public Iterator<Entry<String, Usuario>> iterador() {
			return MapUsuarios.iterador();
		}
		
		public boolean estaVacia() {
			return MapUsuarios.estaVacio();
		}
		
		public boolean contieneMail(String mail) {
			return MapUsuarios.contieneKey(mail);
		}

}
