package Interfaces;


public interface I_RUD <T>{

	public void agregar(T o);
	
	public void borrar(T o);
	
	public T buscar (String nombre); 
	
	public String mostrarTodo();
	
	
}
