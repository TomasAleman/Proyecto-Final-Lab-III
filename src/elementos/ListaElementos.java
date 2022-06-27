package elementos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import Interfaces.I_RUD;
import contenedoresGenericos.GenericHashSet;
import excepciones.ExcepcionExistencia;
import peliculas.Pelicula;
import series.Serie;

public class ListaElementos implements I_RUD<Elemento>, Serializable {
	private GenericHashSet<Elemento> hashSetElementos;

	// Constructores
	public ListaElementos() {
		hashSetElementos = new GenericHashSet<Elemento>();
	}

	public ListaElementos(GenericHashSet<Elemento> hashSetElementos) {
		this.hashSetElementos = hashSetElementos;
	}

	// Getters y Setters
	public GenericHashSet<Elemento> getHashSetElementos() {
		return hashSetElementos;
	}

	public void setHashSetElementos(GenericHashSet<Elemento> hashSetElementos) {
		this.hashSetElementos = hashSetElementos;
	}

	// ---------------------------------------- MÉTODOS de LISTAELEMENTOS
	
	// Iterar
	public Iterator<Elemento> iterador() {
		return hashSetElementos.iterador();
	}

	public boolean verSiExiste(Elemento elemento) {
		boolean existe = hashSetElementos.contiene(elemento);
		return existe;
	}

	// Mostrar todos los Elementos
	public String mostrarTodo() {
		String contenido = "";
		Iterator<Elemento> iterador = hashSetElementos.iterador();
		while (iterador.hasNext()) {
			contenido += iterador.next().toString();
		}
		return contenido;
	}

	// Mostrar sólo Instancias de Película
	public String mostrarPeliculas() {
		String contenido = "";
		Iterator<Elemento> iterador = hashSetElementos.iterador();
		while (iterador.hasNext()) {
			Elemento elem = iterador.next();
			if(elem instanceof Pelicula)
			{
				contenido += elem.toString();
			}
		}
		return contenido;
	}
	
	// Mostrar sólo Instancias de Serie
	public String mostrarSeries() {
		String contenido = "";
		Iterator<Elemento> iterador = hashSetElementos.iterador();
		while (iterador.hasNext()) {
			Elemento elem = iterador.next();
			if(elem instanceof Serie)
			{
				contenido += elem.toString();
			}
		}
		return contenido;
	}
	
	// Filtrar Elementos por Género ingresado
	public String mostrarPorGenero(String genero, boolean infantil)
	{
		String contenido = "";
		Iterator<Elemento>iterador = hashSetElementos.iterador();
		Elemento actual =null;
		while (iterador.hasNext()) {
			actual = iterador.next();
			if(infantil) {
				if(actual.getGenero().equalsIgnoreCase(genero) && actual.getClasificacion().compareTo(Clasificacion.MAS7) ==0 )
				{
					contenido += actual.toString();
				}
			}else if (actual.getGenero().equalsIgnoreCase(genero))
			{
				contenido += actual.toString();
			}
		}
		if(contenido.equals("")) {
			contenido = "No encontramos peliculas de "+genero+" aptas para ti";
		}
		return contenido;
	}
	
	// Ordenar Elementos por Orden Alfabético Ascendiente
	public String mostrarPorOrdenAlfabetico(boolean infantil)
	{
		String contenido = "";
		ArrayList<Elemento> elementos = new ArrayList<>();
		Iterator<Elemento>iterador = hashSetElementos.iterador();
		Elemento actual =null;
		while(iterador.hasNext())
		{
			actual = iterador.next();
			if(infantil) {
				if(actual.getClasificacion().compareTo(Clasificacion.MAS7)== 0) {
					elementos.add(actual);
				}
			}else {
				elementos.add(actual);
			}
			
		}
		
		ordenarInsercion(elementos, elementos.size());
		
		for(int i = 0;i<elementos.size();i++)
		{
			contenido = contenido + elementos.get(i);
		}
		return contenido;
	}
	
	public void insertar(ArrayList<Elemento> elementos,int ultPosVal, Elemento aux)
	{
		int i = ultPosVal;
		
		while(i>=0 && elementos.get(i).getNombre().charAt(0) > aux.getNombre().charAt(0))
		{
			elementos.remove(i+1);
			elementos.add(i+1, elementos.get(i));
			i--;
		}
		elementos.remove(i+1);
		elementos.add(i+1, aux);
	}
	
	public void ordenarInsercion(ArrayList<Elemento> elementos,int validos)
	{
		int i =0 ; 
		while(i<validos-1)
		{
			insertar(elementos,i,elementos.get(i+1));
			i++;
		}
	}
	
	// Ordenar Elementos por Año de Estreno Descendiente
	public String mostrarPorFecha(boolean infantil)
	{
		String contenido = "";
		ArrayList<Elemento> elementos = new ArrayList<>();
		Iterator<Elemento>iterador = hashSetElementos.iterador();
		Elemento actual = null;
		while(iterador.hasNext())
		{
			actual = iterador.next();
			if(infantil) {
				if(actual.getClasificacion().compareTo(Clasificacion.MAS7)== 0) {
					elementos.add(actual);
				}
			}else {
				elementos.add(actual);
			}
		}
		ordenarInsercionFecha(elementos, elementos.size());
		for(int i = 0;i<elementos.size();i++)
		{
			contenido = contenido + elementos.get(i);
		}
		return contenido;
	}
	
	public void insertarFecha(ArrayList<Elemento> elementos,int ultPosVal, Elemento aux)
	{
		int i = ultPosVal;
		
		while(i>=0 && elementos.get(i).getAnioDeEstreno() < aux.getAnioDeEstreno())
		{
			elementos.remove(i+1);
			elementos.add(i+1, elementos.get(i));
			i--;
		}
		elementos.remove(i+1);
		elementos.add(i+1, aux);
	}
	
	public void ordenarInsercionFecha(ArrayList<Elemento> elementos,int validos)
	{
		int i =0 ; 
		while(i<validos-1)
		{
			insertarFecha(elementos,i,elementos.get(i+1));
			i++;
		}
	}
	
	// Ordenar Elementos por Puntaje Descendiente
	public String mostrarPorPuntaje(boolean infantil)
	{
		String contenido = "";
		ArrayList<Elemento> elementos = new ArrayList<>();
		Iterator<Elemento>iterador = hashSetElementos.iterador();
		Elemento actual =null;
		while(iterador.hasNext())
		{
			actual = iterador.next();
			if(infantil) {
				if(actual.getClasificacion().compareTo(Clasificacion.MAS7)== 0) {
					elementos.add(actual);
				}
			}else {
				elementos.add(actual);
			}
		}
		ordenarInsercionPuntaje(elementos, elementos.size());
		for(int i = 0;i<elementos.size();i++)
		{
			contenido = contenido + elementos.get(i);
		}
		return contenido;
	}
	
	public void insertarPuntaje(ArrayList<Elemento> elementos,int ultPosVal, Elemento aux)
	{
		int i = ultPosVal;
		
		while(i>=0 && elementos.get(i).getPuntaje() < aux.getPuntaje())
		{
			elementos.remove(i+1);
			elementos.add(i+1, elementos.get(i));
			i--;
		}
		elementos.remove(i+1);
		elementos.add(i+1, aux);
	}
	
	public void ordenarInsercionPuntaje(ArrayList<Elemento> elementos,int validos)
	{
		int i =0 ; 
		while(i<validos-1)
		{
			insertarPuntaje(elementos,i,elementos.get(i+1));
			i++;
		}
	}
	
	// Obtener tamaño de la lista
		public int tamanio() {
			return hashSetElementos.tamanio();
		}

	// Borrar un Elemento de la lista
	@Override
	public void borrar(Elemento o) {

		hashSetElementos.eliminar(o);
	}

	// Buscar un Elemento en la lista
	@Override
	public Elemento buscar(String nombre) {
		Iterator<Elemento> iterador = hashSetElementos.iterador();
		boolean existe = false;
		Elemento aRetornar = null;
		Elemento actual;

		while (iterador.hasNext() && existe == false) {
			actual = iterador.next();
			if (actual instanceof Pelicula) {

				if (nombre.equalsIgnoreCase(actual.getNombre())) {
					existe = true;
					aRetornar = actual;
				}
			} else if (actual instanceof Serie) {

				if (nombre.equalsIgnoreCase(actual.getNombre())) {
					existe = true;
					aRetornar = actual;
				}
			}
		}
		return aRetornar;
	}
	
	// Agregar un Elemento a la lista
		@Override
		public void agregar(Elemento o) {
			try {
				if (hashSetElementos.contiene(o) == false) {
					hashSetElementos.agregar(o);
				} else {
					throw new ExcepcionExistencia("\n> El elemento " + o.getNombre() + " ya existe");
				}
			} catch (ExcepcionExistencia e) {
				System.out.println(e.getMessage()); 
			}

		}

		@Override
		public boolean estaVacia() {
			return hashSetElementos.estaVacio();
		}
}
