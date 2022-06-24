package app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import elementos.Clasificacion;
import elementos.Elemento;
import usuarios.Usuario;
import elementos.ListaElementos;
import excepciones.ExcepcionCantidadPerfiles;
import excepciones.ExcepcionUsuarioIncorrecto;
import usuarios.Admin;
import usuarios.Estandar;
import usuarios.ListaAdmins;
import usuarios.ListaEstandares;
import usuarios.ListaUsuarios;
import usuarios.Perfil;

public class Nerflis {

	Scanner input = new Scanner(System.in);
	private ListaUsuarios usuarios;
	private ListaElementos elementos;
	private ListaAdmins admins;
	private ListaEstandares usersEstandar;

	public Nerflis() {
		usuarios = new ListaUsuarios();
		elementos = new ListaElementos();
		admins = new ListaAdmins();
		usersEstandar = new ListaEstandares();
	}

	public ListaAdmins getAdmins() {
		return admins;
	}

	public void setAdmins(ListaAdmins admins) {
		this.admins = admins;
	}

	public ListaEstandares getUsersEstandar() {
		return usersEstandar;
	}

	public void setUsersEstandar(ListaEstandares usersEstandar) {
		this.usersEstandar = usersEstandar;
	}

	public ListaUsuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ListaUsuarios usuarios) {
		this.usuarios = usuarios;
	}

	public ListaElementos getElementos() {
		return elementos;
	}

	public void setElementos(ListaElementos elementos) {
		this.elementos = elementos;
	}

	// Manipular Usuarios
	public Usuario buscarUsuario(Usuario user) {
		return usuarios.buscar(user.getMail());
	}

	public void quitarUsuario(Usuario user) {
		usuarios.borrar(user.getMail());
	}

	public void agregarUsuario(Usuario user) {
		usuarios.agregar(user);
	}

	// Manipular Elementos
	public Elemento buscarElemento(Elemento e) {
		return elementos.buscar(e.getNombre());
	}

	public void quitarElemento(Elemento e) {
		elementos.borrar(e);
	}

	public void agregarElemento(Elemento e) {
		elementos.agregar(e);
	}

	// Menú Inicio Sesión
	public void iniciarSesion() {
		System.out.println("> Ingrese su Email:");
		input.nextLine();
		String email = input.nextLine();
		System.out.println("> Ingrese su contraseña:");
		String clave = input.nextLine();
		try {
			if (validarUsuario(email, clave)) {
				Usuario user = usuarios.buscar(email);
				if (admins.contieneMail(email)) {
					menuPrincipalAdmins(user);
				} else {
					Estandar estandar = (Estandar) user;
					menuPerfiles(estandar);
				}
			} else {
				throw new ExcepcionUsuarioIncorrecto("Error: El Email o la Contraseña es incorrecta");
			}
		} catch (ExcepcionUsuarioIncorrecto e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean validarUsuario(String mail, String clave) {
		boolean existe = false;
		if (!usuarios.estaVacia()) {
			if (usuarios.contieneMail(mail)) {
				if (usuarios.buscar(mail).getClave().equals(clave)) {
					existe = true;
				}
			}
		}
		return existe;
	}

	// Menú Principal
	public void menuInicio() {
		int opcion = -1;
		boolean opcionIncorrecta = false;

		System.out.println(">> Bienvenido a Nerflis <<");
		do {
			System.out.println("> 1. Registrarse");
			System.out.println("> 2. Iniciar sesión");
			System.out.println("> 3. Salir");

			opcion = input.nextInt();

			switch (opcion) {
			case 1:
				registrarse();
				break;

			case 2:
				iniciarSesion();
				break;

			case 3:
				break;

			default:
				System.out.println("> Opción incorrecta");
				opcionIncorrecta = true;
				break;
			}
		} while (opcion != 3 && opcionIncorrecta == false);
	}

	// Menu registrarse

	public void registrarse() {
		String mail;
		String clave;
		System.out.println("Ingrese su email\n");
		input.nextLine();
		mail = input.nextLine();
		System.out.println("Ingrese una contraseña");
		clave = input.nextLine();
		Usuario aux = new Estandar(mail, clave);
		usuarios.agregar(aux);
		try {
			if (validarRegistro(mail)) {
				System.out.println("Se ha registrado correctamente\n");
			} else {
				throw new ExcepcionUsuarioIncorrecto("Error: El email que usted ingreso ya esta en uso\n");
			}

		} catch (ExcepcionUsuarioIncorrecto e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean validarRegistro(String email) {
		boolean retorno = false;
		if (!usuarios.estaVacia()) {
			if (usuarios.contieneMail(email)) {
				retorno = true;
			}

		}
		return retorno;
	}

	public void menuPerfiles(Estandar user) {
		int opcion = -1;
		boolean opcionIncorrecta = false;

		System.out.println("\b >> Menu de Perfiles <<");
		do {
			System.out.println("> 1. Crear un perfil");
			System.out.println("> 2. Entrar a un perfil");
			System.out.println("> 3. Cerrar sesion");

			opcion = input.nextInt();

			switch (opcion) {
			case 1:
				verificarPerfiles(user);
				break;

			case 2:
				entrarAUnPerfil(user);
				break;

			case 3:
				menuInicio();
				break;

			default:
				System.out.println("> Opción incorrecta");
				opcionIncorrecta = true;
				break;

			}
		} while (opcion != 3 && opcionIncorrecta == false);
	}


	public void verificarPerfiles(Estandar user) {
		int opcion = -1;
		if (user.getListaPerfiles().cantidadPerfiles() == user.getListaPerfiles().getMAX_PERFILES()) {
			do {
				System.out.println("Hay demasiados perfiles creados");
				System.out.println("Que desea hacer?");
				System.out.println("> 1. Eliminar perfil");
				System.out.println("> 2. Entrar a uno de los perfiles existentes");
				opcion = input.nextInt();
				switch (opcion) {
				case 1: eliminarPerfil(user);

					break;

				case 2: entrarAUnPerfil(user);

					break;
				default:
					System.out.println("> Opción incorrecta");
					break;

				}
			} while (opcion != 1 && opcion != 2);
		} 
		else
		{
			crearPerfil(user);
		}

		   
	}

	public void eliminarPerfil(Estandar user) {
		System.out.println("Cual perfil desea eliminar?");
		int opcion = 0;
		for(int i = 0;i<user.getListaPerfiles().getMAX_PERFILES();i++)
		{
			System.out.println(">"+(i+1)+". "+user.getListaPerfiles().mostrarNombrePorIndice(i));
		}
		input.nextLine();
		opcion = input.nextInt();
		user.getListaPerfiles().borrar(user.getListaPerfiles().retornar(opcion-1));
	}

	public void crearPerfil(Estandar user) {
		String nombre;
		int opcion = -1;
		boolean infantil = false;
		System.out.println("Ingrese el nombre del perfil");
		input.nextLine();
		nombre = input.nextLine();
		do {
			System.out.println("> 1. Adultos");
			System.out.println("> 2. Infantil");
			opcion = input.nextInt();
			switch (opcion) {
			case 1:
				infantil = false;
				break;
			case 2:
				infantil = true;
				break;
			default:
				System.out.println("> Opción incorrecta");
				break;

			}
		} while (opcion != 1 && opcion != 2);
		Perfil perfil = new Perfil(nombre, infantil);
		user.getListaPerfiles().agregar(perfil);
	}

	public void entrarAUnPerfil(Estandar user) {
		do {
			for (int i = 0; i < user.getListaPerfiles().cantidadPerfiles(); i++) {
				System.out.println((i + 1) + "> " + user.getListaPerfiles().mostrarPorIndice(i));
			}
			int perfil = input.nextInt();
			try {
				if (perfil <= user.getListaPerfiles().cantidadPerfiles()) {
					menuNerflis(user.getListaPerfiles().retornar(perfil - 1));
				} else {
					throw new ExcepcionCantidadPerfiles("El perfil al que quieres ingresar no existe");
				}
			} catch (ExcepcionCantidadPerfiles e) {
				System.out.println(e.getMessage());
			}
		} while (true);
	}

	public void menuNerflis(Perfil perfil) {
		int opcion = -1;
		boolean infantil = perfil.isInfantil();
		do {
			System.out.println("1 > Ver Peliculas");
			System.out.println("2 > Recomendaciones");
			System.out.println("3 > Mi Lista");
			System.out.println("4 > Buscar");
			opcion = input.nextInt();
			switch (opcion) {
			case 1:
				System.out.println(verPeliculas(infantil));
				break;
			case 2:
				// Recomendaciones
				break;
			case 3:
				// Mi Lista
				break;
			case 4:
				// Buscar
				break;
			default:
				System.out.println("> Opción incorrecta");
				break;
			}
		} while (opcion != 1 && opcion != 2);
	}

	public String verPeliculas(boolean infantil) {
		String contenido = "";
		if(infantil == false) {
			contenido = elementos.mostrarTodo();
		}else {
			Iterator<Elemento> it = elementos.iterador();
			while(it.hasNext()) {
				Elemento elem = it.next();
				if(elem.getClasificacion().compareTo(Clasificacion.MAS7) == 0) {
					contenido += elem.toString();
				}
			}
		}
		return contenido;
	}
	
	public void menuMiLista(Perfil perfil) {
		//para seguir
	}

	public void menuPrincipalAdmins(Usuario admin) {
		int opcionPrincipal = -1;
		boolean opcionIncorrecta = false;

		System.out.println("\b >> Menu de Admins <<");
		do {

			System.out.println("> 1. Menú de Usuarios");
			System.out.println("> 2. Menú de Películas / Series");
			System.out.println("> 3. Salir");

			switch (opcionPrincipal) {
			case 1:

				break;

			case 2:

				break;

			default:
				System.out.println("> Opción incorrecta");
				opcionIncorrecta = true;
				break;

			}
		} while (opcionPrincipal != 3 && opcionIncorrecta == false);
	}

}
