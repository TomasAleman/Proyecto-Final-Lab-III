package excepciones;

// Excepci�n que se lanza cuando no hay perfiles en una cuenta o cuando sobrepasa el l�mite (4)
public class ExcepcionCantidadPerfiles extends Exception{
		private String msg;

	public ExcepcionCantidadPerfiles(String msg) {
		super(msg);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
	

}
