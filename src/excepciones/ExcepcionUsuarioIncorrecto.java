package excepciones;

// Excepci�n que se lanza cuando el usuario ingresado ya est� registrado o cuando mail/pass son incorrecto/s
public class ExcepcionUsuarioIncorrecto extends Exception{
	
	public ExcepcionUsuarioIncorrecto(String mensaje){
		super(mensaje);
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}

}
