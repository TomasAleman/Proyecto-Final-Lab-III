package excepciones;

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
