package Interfaces;

// Interfaz de agregado, borrado, búsqueda e impresión de objetos a las listas
public interface I_RUD <T>{

	public void agregar(T o);
	
	public void borrar(T o);
	
	public T buscar (String nombre); 
	
	public String mostrarTodo();
	
	public boolean estaVacia();
	
	
}
