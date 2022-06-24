package excepciones;

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
