package usuarios;

import contenedoresGenericos.GenericArrayList;
import excepciones.ExcepcionCantidadPerfiles;
import Interfaces.I_RUD;

public class ListaPerfiles implements I_RUD<Perfil> {
	private final int MAX_PERFILES = 4;
	private GenericArrayList<Perfil> listaPerfiles;

	public ListaPerfiles() {
		listaPerfiles = new GenericArrayList<>();
	}

	public String mostrarTodo() {
		String contenido = "";

		for (int i = 0; i < listaPerfiles.tamanio(); i++) {
			contenido += listaPerfiles.devolver(i).toString();
		}

		return contenido;
	}
	

	public int getMAX_PERFILES() {
		return MAX_PERFILES;
	}

	public String mostrarPorIndice(int indice) {
		String contenido = listaPerfiles.devolver(indice).toString();
		return contenido;
	}
	public String mostrarNombrePorIndice(int indice)
	{
		String nombre = listaPerfiles.devolver(indice).getNombre();
		return nombre;
	}
	
	public Perfil retornar(int indice) {
		return listaPerfiles.devolver(indice);
	}

	public int cantidadPerfiles() {
		return listaPerfiles.tamanio();
	}
	
	public Perfil buscar(String nombre) {
		Perfil aRetornar = null;
		boolean existe = false;
		int i = 0;

		while (i < listaPerfiles.tamanio() && existe == false) {
			if (listaPerfiles.devolver(i).getNombre().equalsIgnoreCase(nombre)) {
				aRetornar = listaPerfiles.devolver(i);
				existe = true;
			}

			i++;
		}

		return aRetornar;
	}

	@Override
	public void borrar(Perfil p) {
		listaPerfiles.eliminarPerfil(p);
	}

	@Override
	public void agregar(Perfil p) {
		try {
			if (listaPerfiles.tamanio() < MAX_PERFILES) {
				listaPerfiles.agregar(p);
			} else {
				throw new ExcepcionCantidadPerfiles("Máximo 4 perfiles por usuario");
			}
		} catch (ExcepcionCantidadPerfiles e) {
			System.out.println(e.getMessage());
		}

	}
	
	public boolean estaVacia() {
		return listaPerfiles.estaVacio();
	}

}
