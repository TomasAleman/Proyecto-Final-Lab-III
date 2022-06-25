package excepciones;

// Excepción que se lanza cuando no hay elementos en una lista
public class ExcepcionExistencia extends Exception{
	private String msg;

	public ExcepcionExistencia(String msg) {
		super(msg);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
	

}
