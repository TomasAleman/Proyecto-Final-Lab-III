package usuarios;

import java.util.Iterator;

import contenedoresGenericos.GenericHashSet;
import elementos.Elemento;
import excepciones.ExcExistencia;
import interfaces.I_RUD;
import peliculas.Pelicula;
import series.Serie;

public class ListaUsuarios implements I_RUD<Usuario>{
	private GenericHashSet<Usuario> hashsetUsuarios;
	
	// Constructores
		public ListaUsuarios() {
			hashsetUsuarios = new GenericHashSet<Usuario>();
		}

		public ListaUsuarios(GenericHashSet<Usuario> hashsetUsuarios) {
			this.hashsetUsuarios = hashsetUsuarios;
		}

		// getters y setters
		public GenericHashSet<Usuario> getHashsetUsuarios() {
			return hashsetUsuarios;
		}

		public void setHashSetElementos(GenericHashSet<Usuario> hashsetUsuarios) {
			this.hashsetUsuarios = hashsetUsuarios;
		}

		// Métodos
		public Iterator iterador()
		{
			return hashsetUsuarios.iterador();
		}
		
		public boolean verSiExiste(Usuario u) {
			boolean existe = hashsetUsuarios.contiene(u);
			return existe;
		}
		

		public String mostrarTodo() {
			String contenido="";
			Iterator<Usuario> iterador = hashsetUsuarios.iterador();
			while(iterador.hasNext()) {
				contenido += iterador.next().toString();
			}
			return contenido;
		}

		@Override
		public void agregar(Usuario u){
			try
			{
				if(hashsetUsuarios.contiene(u) == false)
				{
					hashsetUsuarios.agregar(u);
				}
				else
				{
					throw new ExcExistencia("\n> El usuario "+u.getMail()+" ya existe");
				}
			}
			catch(ExcExistencia e)
			{
				System.out.println(e.getMessage()); 
			}
							
		}

		@Override
		public void borrar(Usuario u) {
			
			hashsetUsuarios.eliminar(u);
		}

		@Override
		public Usuario buscar(String mail) {
			Iterator<Usuario> iterador = hashsetUsuarios.iterador();
			boolean existe = false;
			Usuario aRetornar = null;
			Usuario actual;		
			
			while (iterador.hasNext() && existe == false) {
				actual = iterador.next();
					
					if (mail.equalsIgnoreCase(actual.getMail())) {
						existe = true;
							aRetornar = actual;
					}
				
			}
		return aRetornar;
		}

}
