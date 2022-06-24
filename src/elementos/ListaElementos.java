package elementos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import Interfaces.I_RUD;
import contenedoresGenericos.GenericHashSet;
import excepciones.ExcepcionExistencia;
import peliculas.Pelicula;
import series.Serie;

public class ListaElementos implements I_RUD<Elemento> {

	private GenericHashSet<Elemento> hashSetElementos;

	// Constructores
	public ListaElementos() {
		hashSetElementos = new GenericHashSet<Elemento>();
	}

	public ListaElementos(GenericHashSet<Elemento> hashSetElementos) {
		this.hashSetElementos = hashSetElementos;
	}

	// getters y setters
	public GenericHashSet<Elemento> getHashSetElementos() {
		return hashSetElementos;
	}

	public void setHashSetElementos(GenericHashSet<Elemento> hashSetElementos) {
		this.hashSetElementos = hashSetElementos;
	}

	// Métodos
	public Iterator<Elemento> iterador() {
		return hashSetElementos.iterador();
	}

	public boolean verSiExiste(Elemento elemento) {
		boolean existe = hashSetElementos.contiene(elemento);
		return existe;
	}

	public String mostrarTodo() {
		String contenido = "";
		Iterator<Elemento> iterador = hashSetElementos.iterador();
		while (iterador.hasNext()) {
			contenido += iterador.next().toString();
		}
		return contenido;
	}
	
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
	
	public String mostrarPorGenero(String genero)
	{
		String contenido = "";
		Iterator<Elemento>iterador = hashSetElementos.iterador();
		
		while (iterador.hasNext()) {
			if(iterador.next().getGenero().equalsIgnoreCase(genero))
			{
				contenido += iterador.next().toString();
			}
		}
		
		return contenido;
		
	}
	
	// ORDEN ALFABETICO --------------------------------------------------------------------------------
	public String mostrarPorOrdenAlfabetico()
	{
		String contenido = "";
		ArrayList<Elemento> elementos = new ArrayList<>();
		Iterator<Elemento>iterador = hashSetElementos.iterador();
		
		while(iterador.hasNext())
		{
			elementos.add(iterador.next());
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
	///  ORDENAR POR FECHA -----------------------------------------------------------------------------------------------------
	public String mostrarPorFecha()
	{
		String contenido = "";
		ArrayList<Elemento> elementos = new ArrayList<>();
		Iterator<Elemento>iterador = hashSetElementos.iterador();
		
		while(iterador.hasNext())
		{
			elementos.add(iterador.next());
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
	///  ORDENAR POR PUNTAJE -----------------------------------------------------------------------------------------------------
	public String mostrarPorPuntaje()
	{
		String contenido = "";
		ArrayList<Elemento> elementos = new ArrayList<>();
		Iterator<Elemento>iterador = hashSetElementos.iterador();
		
		while(iterador.hasNext())
		{
			elementos.add(iterador.next());
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
	public void borrar(Elemento o) {

		hashSetElementos.eliminar(o);
	}

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
	
	public int validos() {
		return hashSetElementos.tamanio();
	}

}
