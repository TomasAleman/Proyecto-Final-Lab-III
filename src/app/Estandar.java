package app;

import java.time.LocalDate;

public class Estandar extends Usuario {

	private Perfil listaPerfiles[];
	
	
	public Estandar (String mail, String clave) {
		super(mail, clave);
		listaPerfiles=new Perfil[4];	
	}
	
	public Estandar () {
		super();
		listaPerfiles=new Perfil[4];	
	}

	public Perfil[] getListaPerfiles() {
		return listaPerfiles;
	}

	public void setListaPerfiles(Perfil[] listaPerfiles) {
		this.listaPerfiles = listaPerfiles;
	}

	@Override
	public String toString() {
		
		return "\nEstandar:"+super.toString();
	}
	
	
}
