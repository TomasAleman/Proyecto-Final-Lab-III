package app;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import elementos.Clasificacion;
import elementos.Elemento;
import usuarios.Usuario;
import elementos.ListaElementos;
import excepciones.ExcepcionCantidadPerfiles;
import excepciones.ExcepcionExistencia;
import excepciones.ExcepcionUsuarioIncorrecto;
import peliculas.Pelicula;
import series.Serie;
import series.Temporada;
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
	private ListaEstandares usuariosEstandar;

	public Nerflis() {
		usuarios = new ListaUsuarios();
		elementos = new ListaElementos();
		admins = new ListaAdmins();
		usuariosEstandar = new ListaEstandares();
	}

	// ---------------------------------------- GETTERS Y SETTERS
	public ListaAdmins getAdmins() {
		return admins;
	}

	public void setAdmins(ListaAdmins admins) {
		this.admins = admins;
	}

	public ListaEstandares getUsuariosEstandar() {
		return usuariosEstandar;
	}

	public void setUsuariosEstandar(ListaEstandares usuariosEstandar) {
		this.usuariosEstandar = usuariosEstandar;
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

	// ---------------------------------------- USUARIOS

	public void agregarUsuario(Usuario user) {
		usuarios.agregar(user);

		if (user instanceof Admin) {
			admins.agregar((Admin) user);
		} else {
			usuariosEstandar.agregar((Estandar) user);
		}
	}

	public Usuario buscarUsuario(Usuario user) {
		return usuarios.buscar(user.getMail());
	}

	public void quitarUsuario(Usuario user) {
		usuarios.borrar(user.getMail());
	}

	public boolean validarUsuario(String mail, String clave) {
		boolean correcto = false;

		if (!usuarios.estaVacia()) // comprueba que la lista de usuarios no esté vacía
		{
			if (usuarios.existeMail(mail)) // comprueba que el mail esté registrado
			{
				if (usuarios.buscar(mail).getClave().equals(clave)) // comprueba que la clave pertenezca a ese mail
				{
					correcto = true;
				}
			}
		}
		return correcto;
	}

	// ---------------------------------------- VALIDACIONES
	public boolean validarRegistro(String email) {
		boolean retorno = false;
		if (!usuarios.estaVacia()) {
			if (usuarios.existeMail(email)) {
				retorno = true;
			}

		}
		return retorno;
	}

	// ---------------------------------------- ELEMENTOS

	public void agregarElemento(Elemento e) {
		elementos.agregar(e);
	}

	public Elemento buscarElemento(Elemento e) {
		return elementos.buscar(e.getNombre());
	}

	public void quitarElemento(Elemento e) {
		elementos.borrar(e);
	}

	// ---------------------------------------- MÉTODOS de MENÚ PERFIL
	public void crearPerfil(Estandar user) {
		String nombre;
		int opcion = -1;
		boolean infantil = false;

		System.out.println("\n> Ingrese nombre del perfil: ");
		input.nextLine();
		nombre = input.nextLine();

		do {
			System.out.println("\n> 1. Adultos | 2. Infantil");
			opcion = input.nextInt();
			switch (opcion) {
			case 1:
				infantil = false;
				break;
			case 2:
				infantil = true;
				break;
			default:
				System.out.println("\n> Opción incorrecta");
				break;
			}
		} while (opcion != 1 && opcion != 2);

		Perfil perfil = new Perfil(nombre, infantil);
		user.getListaPerfiles().agregar(perfil);
	}

	public void eliminarPerfil(Estandar user) {
		int opcion = 0;
		System.out.println("> ¿Qué perfil desea eliminar?");

		do {

			System.out.println(mostrarPerfiles(user));

			input.nextLine();
			opcion = input.nextInt();

			switch (opcion) {
			case 1:
			case 2:
			case 3:
			case 4:
				user.getListaPerfiles().borrar(user.getListaPerfiles().retornar(opcion - 1));
				break;

			default:
				System.out.println("> Opción incorrecta");
				break;
			}
		} while (opcion != 1 && opcion != 2 && opcion != 3 && opcion != 4);

	}

	public String mostrarPerfiles(Estandar user) {
		String perfiles = "";

		for (int i = 0; i < user.getListaPerfiles().cantidadPerfiles(); i++) {
			perfiles += "\n (" + (i + 1) + ") " + user.getListaPerfiles().mostrarPorIndice(i);
		}

		return perfiles;
	}

	// ---------------------------------------- 1. LAUNCH MENU

	public void menuLaunch() {
		int opcion = -1;
		boolean opcionIncorrecta = false;

		do {
			System.out.println("\n>> Bienvenido a Nerflis << ");
			System.out.println("\n > 1. Registrarse");
			System.out.println(" > 2. Iniciar sesión");
			System.out.println(" > 3. Salir del sistema\n");

			opcion = input.nextInt();

			switch (opcion) {
			case 1:
				input.nextLine();
				menuRegistro();
				System.out.println("> Registro exitoso!");
				break;

			case 2:
				input.nextLine();
				menuInicioSesion();
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

	// ---------------------------------------- 1.1 MENÚ de REGISTRO

	public boolean menuRegistro() {
		String mail, clave;
		boolean reintentar = true;
		boolean ejecucionExitosa = false;

		do {
			try {
				System.out.println("\n> Ingrese su email");
				mail = input.nextLine();

				if (validarRegistro(mail) == true) {
					throw new ExcepcionUsuarioIncorrecto(
							"> Error: el email que ingresó ya está registrado, vuelva a intentar");

				} else {
					System.out.println("> Ingrese una contraseña");
					clave = input.nextLine();
					Estandar nuevo = new Estandar(mail, clave);
					usuarios.agregar(nuevo);
					usuariosEstandar.agregar(nuevo);
					reintentar = false;
					ejecucionExitosa = true;
				}

			} catch (ExcepcionUsuarioIncorrecto e) {
				System.out.println(e.getMessage());
			}

		} while (reintentar == true);

		return ejecucionExitosa;

	}

	// ---------------------------------------- 1.2 MENÚ de INICIO DE SESIÓN

	public void menuInicioSesion() {
		String email, clave;
		System.out.println("> Ingrese su email:");
		email = input.nextLine();
		System.out.println("> Ingrese su contraseña:");
		clave = input.nextLine();

		try {
			if (validarUsuario(email, clave)) {

				Usuario aLogear = usuarios.buscar(email);

				if (admins.contieneMail(email)) {
					Admin admin = (Admin) aLogear;
					menuPrincipalAdmins(admin);

				} else {
					Estandar estandar = (Estandar) aLogear;
					menuPerfiles(estandar);
				}

			} else {
				throw new ExcepcionUsuarioIncorrecto("> Error: email o contraseña incorrecta");
			}

		} catch (ExcepcionUsuarioIncorrecto e) {
			System.out.println(e.getMessage());
		}
	}

	// ---------------------------------------- 2. MENÚ de PERFILES

	public void menuPerfiles(Estandar user) {
		int opcion = -1;
		boolean opcionIncorrecta = false;

		do {
			System.out.println("\n >> Perfiles <<");
			System.out.println("\n> 1. Crear un perfil");
			System.out.println("> 2. Entrar a un perfil");
			System.out.println("> 3. Modificar mis datos");
			System.out.println("> 4. Volver");

			opcion = input.nextInt();

			switch (opcion) {
			case 1:
				verificarPerfiles(user);
				break;

			case 2:
				try {
					if (!user.getListaPerfiles().estaVacia()) {
						entrarAUnPerfil(user);
					} else {
						throw new ExcepcionCantidadPerfiles("\n > Error: no hay perfiles disponibles");
					}
				} catch (ExcepcionCantidadPerfiles e) {
					System.out.println(e.getMessage());
				}
				break;

			case 3:
				modificarDatos(user);
				break;
			case 4:
				break;

			default:
				System.out.println("> Opción incorrecta");
				opcionIncorrecta = true;
				break;

			}
		} while (opcion != 4 && opcionIncorrecta == false);
	}

	// ---------------------------------------- MODIFICAR ASPECTOS DE USUARIO

	public void modificarDatos(Estandar user) {
		int opcion = -1;
		boolean opcionIncorrecta = false;

		do {

			System.out.println("\n> 1. Modificar Mail");
			System.out.println("> 2. Modificar Contraseña");
			System.out.println("> 3. Volver");

			opcion = input.nextInt();

			switch (opcion) {
			case 1:
				modificarMail(user);
				break;
			case 2:
				modificarContrasenia(user);
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

	public void modificarContrasenia(Estandar user) {
		boolean salir = false;
		System.out.println("\n> Ingrese su nueva contraseña");
		input.nextLine();
		String nuevaClave = input.nextLine();

		do {
			System.out.println("\n> 1. Guardar cambios");
			System.out.println("> 2. Volver");
			int opcion = input.nextInt();
			switch (opcion) {
			case 1:
				input.nextLine();
				System.out.println(">\n Ingrese su anterior contraseña para guardar los cambios");
				String clave = input.nextLine();
				if (user.getClave().equals(clave)) {
					user.setClave(nuevaClave);
					System.out.println("\nContraseña modificada con exito!");
					salir = true;
				} else {
					System.out.println("\nContraseña incorrecta");
				}
				break;
			case 2:
				System.out.println("\nLos cambios no fueron guardados");
				salir = true;
				break;

			default:
				System.out.println("\nOpcion incorrecta");
				break;
			}

		} while (salir == false);
	}

	public void modificarMail(Estandar user) {
		boolean salir = false;
		System.out.println("\n> Ingrese su nuevo mail");
		input.nextLine();
		String nuevoMail = input.nextLine();

		do {
			System.out.println("\n> 1. Guardar cambios");
			System.out.println("> 2. Volver");
			int opcion = input.nextInt();
			switch (opcion) {
			case 1:
				input.nextLine();
				System.out.println("\n> Ingrese su contraseña para guardar los cambios");
				String clave = input.nextLine();
				if (user.getClave().equals(clave)) {
					user.setMail(nuevoMail);
					System.out.println("\nMail modificado con exito!");
					salir = true;
				} else {
					System.out.println("\nContraseña incorrecta");
				}
				break;
			case 2:
				System.out.println("\n Los cambios no fueron guardados");
				salir = true;
				break;

			default:
				System.out.println("\nOpcion incorrecta");
				break;
			}

		} while (salir == false);
	}

	// ---------------------------------------- 2.1. VERIFICACIÓN de PERFILES

	public void verificarPerfiles(Estandar user) {
		int opcion = -1;

		if (user.getListaPerfiles().cantidadPerfiles() == user.getListaPerfiles().getMAX_PERFILES()) {
			do {
				System.out.println("> Error: el límite de perfiles es 4 por cuenta - ¿Qué desea hacer?");
				System.out.println(" > 1. Eliminar un perfil");
				System.out.println(" > 2. Entrar a uno de los perfiles existentes");
				opcion = input.nextInt();

				switch (opcion) {
				case 1:
					eliminarPerfil(user);
					break;

				case 2:
					entrarAUnPerfil(user);

					break;
				default:
					System.out.println("\n> Opción incorrecta");
					break;

				}
			} while (opcion != 1 && opcion != 2);
		} else {
			crearPerfil(user);
		}

	}

	// ---------------------------------------- 2.2. ENTRAR a un PERFIL
	public void entrarAUnPerfil(Estandar user) {
		boolean salir = false;
		System.out.println("\n> Perfiles existentes: ");

		do {
			System.out.println(mostrarPerfiles(user));

			System.out
					.println("\n> Ingrese número del perfil al que desea entrar | 0 para volver al Menú de Perfiles:");
			int perfil = input.nextInt();

			try {
				if (perfil > 0 && perfil <= user.getListaPerfiles().cantidadPerfiles()) {
					menuPerfil(user.getListaPerfiles().retornar(perfil - 1));
					salir = true;
				} else if (perfil == 0) {
					salir = true;
				} else {
					throw new ExcepcionCantidadPerfiles("> Número de perfil incorrecto o no existente");
				}
			} catch (ExcepcionCantidadPerfiles e) {
				System.out.println(e.getMessage());
			}
		} while (salir == false);
	}

	// ---------------------------------------- 3. MENÚ de UN PERFIL

	public void menuPerfil(Perfil perfil) {
		int opcion = -1;
		boolean opcionIncorrecta = false;
		boolean infantil = perfil.isInfantil();

		do {
			System.out.println("\n> 1. Ver catálogo de Películas y Series");
			System.out.println("> 2. Ver catálogo de Películas");
			System.out.println("> 3. Ver catálogo de Series");
			System.out.println("> 4. Filtrar Películas y Series");
			System.out.println("> 5. Sugerencias para ti");
			System.out.println("> 6. Mi Lista");
			System.out.println("> 7. Volver");

			opcion = input.nextInt();

			switch (opcion) {
			case 1:
				System.out.println(verTodo(infantil));
				break;

			case 2:
				System.out.println(verPeliculas(infantil));
				break;

			case 3:
				System.out.println(verSeries(infantil));
				break;

			case 4:
				menuFiltrar(perfil);
				break;

			case 5:
				if (perfil.getMiLista().estaVacia()) {
					System.out.println("Tu lista esta vacia, no podemos darte recomendaciones");
				} else {
					System.out.println(sugerencias(perfil));
				}
				break;

			case 6:
				miLista(perfil);
				break;

			case 7:
				break;

			default:
				System.out.println("> Opción incorrecta");
				opcionIncorrecta = true;
				break;
			}

		} while (opcion != 7 && opcionIncorrecta == false);
	}

	// ---------------------------------------- 3.1. VER CATÁLOGO COMPLETO

	public String verTodo(boolean infantil) {
		String contenido = "";
		if (infantil == false) {
			contenido = elementos.mostrarTodo();
		} else {
			Iterator<Elemento> it = elementos.iterador();
			while (it.hasNext()) {
				Elemento elem = it.next();
				if (elem.getClasificacion().compareTo(Clasificacion.MAS7) == 0) {
					contenido += elem.toString();
				}
			}
		}
		return contenido;
	}

	// ---------------------------------------- 3.2. VER CATÁLOGO de PELÍCULAS

	public String verPeliculas(boolean infantil) {
		String contenido = "";
		if (infantil == false) {
			contenido = elementos.mostrarPeliculas();
		} else {
			Iterator<Elemento> it = elementos.iterador();
			while (it.hasNext()) {
				Elemento elem = it.next();
				if (elem.getClasificacion().compareTo(Clasificacion.MAS7) == 0 && elem instanceof Pelicula) {
					contenido += elem.toString();
				}
			}
		}
		if(contenido.equals("")) {
			contenido = "Las Peliculas disponibles no son aptas para ti";
		}
		return contenido;
	}

	// ---------------------------------------- 3.3. VER CATÁLOGO de SERIES

	public String verSeries(boolean infantil) {
		String contenido = "";
		if (infantil == false) {
			contenido = elementos.mostrarSeries();
		} else {
			Iterator<Elemento> it = elementos.iterador();
			while (it.hasNext()) {
				Elemento elem = it.next();
				if (elem.getClasificacion().compareTo(Clasificacion.MAS7) == 0 && elem instanceof Serie) {
					contenido += elem.toString();
				}
			}
		}
		if(contenido.equals("")) {
			contenido = "Las series disponibles no son aptas para ti";
		}
		return contenido;
	}

	// ---------------------------------------- 3.4. MENÚ FILTRAR

	public void menuFiltrar(Perfil perfil) {
		int opcion = -1;
		boolean opcionIncorrecta = false;
		String aFiltrar = "";

		do {

			System.out.println("\n> 1. Buscar una Película o Serie");
			System.out.println("> 2. Filtrar Películas y Series por género");
			System.out.println("> 3. Filtrar Películas y Series por orden alfabético");
			System.out.println("> 4. Filtrar Películas y Series por año de estreno");
			System.out.println("> 5. Filtrar Películas y Series por puntaje");
			System.out.println("> 6. Volver");

			// input.nextLine();
			opcion = input.nextInt();

			switch (opcion) {
			case 1:
				System.out.println("\n> Ingrese el título a buscar:");
				input.nextLine();
				aFiltrar = input.nextLine();
				Elemento encontrado = elementos.buscar(aFiltrar);
				if (encontrado == null) {
					System.out.println("> El elemento buscado no existe");
				} else {
					System.out.println(encontrado.toString());
				}
				break;

			case 2:
				System.out.println("> Filtros: Acción | Suspenso | Terror | Comedia | Romance");

				System.out.println("\n> Ingrese el género a buscar:");
				input.nextLine();
				aFiltrar = input.nextLine();

				System.out.println(elementos.mostrarPorGenero(aFiltrar,perfil.isInfantil()));
				break;

			case 3:
				System.out.println(elementos.mostrarPorOrdenAlfabetico(perfil.isInfantil()));
				break;

			case 4:
				System.out.println(elementos.mostrarPorFecha(perfil.isInfantil()));
				break;

			case 5:
				System.out.println(elementos.mostrarPorPuntaje(perfil.isInfantil()));
				break;

			case 6:
				break;
			default:
				System.out.println("> Opción incorrecta");
				opcionIncorrecta = true;
				break;
			}

		} while (opcion != 6 && opcionIncorrecta == false);
	}

	// ---------------------------------------- 3.5 SUGERENCIAS PARA TI

	public String sugerencias(Perfil perfil) {
		String aSugerir = "";
		String generoSugerencia = generoMasVisto(perfil.getMiLista());

		Iterator<Elemento> it = elementos.iterador();

		while (it.hasNext()) {
			Elemento actual = it.next();
			if (perfil.isInfantil()) {
				if (actual.getGenero().equalsIgnoreCase(generoSugerencia)
						&& actual.getClasificacion().compareTo(Clasificacion.MAS7) == 0) {
					aSugerir += "\n" + actual.toString();
				}
			} else if (actual.getGenero().equalsIgnoreCase(generoSugerencia)) {
				aSugerir += "\n" + actual.toString();
			}

		}
		if (aSugerir.equals("")) {
			aSugerir = "No hay recomendaciones para ti";
		}
		return aSugerir;
	}

	// ---------------------------------------- MÉTODOS de SUGERENCIAS PARA TI

	public String generoMasVisto(ListaElementos lista) {
		String a = "Accion";
		String s = "Suspenso";
		String t = "Terror";
		String c = "Comedia";
		String r = "Romance";
		String generoMasVisto = "";

		int accion = 0;
		int suspenso = 0;
		int terror = 0;
		int comedia = 0;
		int romance = 0;

		Iterator<Elemento> it = lista.iterador();
		while (it.hasNext()) {
			Elemento actual = it.next();

			if (actual.getGenero().equalsIgnoreCase(a)) {
				accion++;
			} else if (actual.getGenero().equalsIgnoreCase(s)) {
				suspenso++;
			} else if (actual.getGenero().equalsIgnoreCase(t)) {
				terror++;
			} else if (actual.getGenero().equalsIgnoreCase(c)) {
				comedia++;
			} else if (actual.getGenero().equalsIgnoreCase(r)) {
				romance++;
			}
		}

		int mayor = obtenerMayor(accion, suspenso, terror, comedia, romance);

		if (mayor == accion) {
			generoMasVisto += a;
		} else if (mayor == suspenso) {
			generoMasVisto += s;
		} else if (mayor == terror) {
			generoMasVisto += t;
		} else if (mayor == comedia) {
			generoMasVisto += c;
		} else if (mayor == romance) {
			generoMasVisto += r;
		}

		return generoMasVisto;
	}

	private int obtenerMayor(int a, int s, int t, int c, int r) {
		int mayor;

		if (a > s && a > t && a > c && a > r) {
			mayor = a;
		} else if (s > t && s > c && s > r) {
			mayor = s;
		} else if (t > c && t > r) {
			mayor = t;
		} else if (c > r) {
			mayor = c;
		} else {
			mayor = r;
		}

		return mayor;
	}

	// ---------------------------------------- 3.6 MENÚ de MI LISTA
	public void miLista(Perfil perfil) {
		int opcion = -1;
		boolean opcionIncorrecta = false;

		do {
			System.out.println("\n> 1. Ver Mi Lista");
			System.out.println("> 2. Agregar elemento a mi lista");
			System.out.println("> 3. Eliminar elemento de mi lista");
			System.out.println("> 4. Volver");
			opcion = input.nextInt();

			switch (opcion) {
			case 1:
				try {
					if (perfil.getMiLista().tamanio() != 0) {
						System.out.println(perfil.getMiLista().mostrarTodo());
					} else {
						throw new ExcepcionExistencia("\n> Error: no hay elementos en tu lista");
					}
				} catch (ExcepcionExistencia e) {
					System.out.println(e.getMessage());
				}

				break;

			case 2:
				agregarMiLista(perfil);
				break;

			case 3:
				eliminarMiLista(perfil);
				break;
			case 4:
				break;

			default:
				System.out.println("> Opción incorrecta");
				opcionIncorrecta = true;
				break;
			}

		} while (opcion != 4 && opcionIncorrecta == false);

	}

	// ---------------------------------------- MÉTODOS de MENÚ MI LISTA
	// 3.6.2
	public void agregarMiLista(Perfil perfil) {
		String opcionID = "";
		boolean flag = false;
		while (flag == false) {
			if (perfil.isInfantil() == true) {
				System.out.println(verTodo(perfil.isInfantil()));
			} else {
				System.out.println(elementos.mostrarTodo());
			}
			System.out.println("> Ingrese nombre del elemento a agregar:");
			input.nextLine();
			opcionID = input.nextLine();
			Elemento aAgregar = elementos.buscar(opcionID);

			if (aAgregar != null) {
				perfil.getMiLista().agregar(aAgregar);
				flag = true;
			} else {
				System.out.println("El nombre ingresado es incorrecto o no existe");
			}
		}

	}

	// 3.6.3
	public void eliminarMiLista(Perfil perfil) {
		String opcionID = "";
		boolean flag = false;
		System.out.println(perfil.getMiLista().mostrarTodo());

		while (flag == false) {
			System.out.println("> Ingrese nombre del elemento a eliminar:");
			input.nextLine();
			opcionID = input.nextLine();
			Elemento aEliminar = elementos.buscar(opcionID);

			if (aEliminar != null) {
				perfil.getMiLista().borrar(aEliminar);
				flag = true;
			} else {
				System.out.println("> El nombre ingresado es incorrecto o no existe ");
			}
		}
	}

	// ---------------------------------------- 4. MENÚ de ADMINS
	public void menuPrincipalAdmins(Admin admin) {
		int opcion = -1;
		boolean opcionIncorrecta = false;

		System.out.println("\n >> Menu de Admins <<");
		do {

			System.out.println("\n> 1. Menú de Usuarios");
			System.out.println("> 2. Menú de Películas / Series");
			System.out.println("> 3. Salir");

			opcion = input.nextInt();

			switch (opcion) {
			case 1:
				menuUsuarios_forAdmins();
				break;

			case 2:
				menuElementos_forAdmins(admin);
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

	// ---------------------------------------- 4.1. MENÚ de USUARIOS para ADMINS
	public void menuUsuarios_forAdmins() {
		int opcion = -1;
		String emailUsu = "";
		boolean opcionIncorrecta = false;

		System.out.println("\n >> Gestión de Usuarios <<");
		do {

			System.out.println("\n > 1. Buscar un Usuario");
			System.out.println(" > 2. Alta o baja a un Usuario");
			System.out.println(" > 3. Ver todos los usuarios");
			System.out.println(" > 4. Volver");

			opcion = input.nextInt();

			switch (opcion) {
			case 1:
				System.out.println("\n> Ingrese mail del usuario a buscar: ");
				input.nextLine();
				emailUsu = input.nextLine();

				Usuario aBuscar = verInfoUsuario(emailUsu);

				if (aBuscar == null) {
					System.out.println("\n> Error: el email ingresado es incorrecto o no pertenece a ningún Usuario");
				} else {
					System.out.println(aBuscar);
				}

				break;

			case 2:
				System.out.println("\n> Ingrese mail del usuario al que desea dar de baja/alta: ");
				input.nextLine();
				emailUsu = input.nextLine();

				boolean cambiado = cambiarEstadoUsuarioEstandar(emailUsu);

				if (cambiado == false) {
					System.out.println("\n> Error: el email ingresado es incorrecto o no pertenece a ningún Usuario");
				} else {
					System.out.println("\n> El usuario fue dado de baja/alta exitosamente: ");
					System.out.println(verInfoUsuario(emailUsu));
				}
				break;

			case 3:
				if (usuariosEstandar.estaVacia()) {
					System.out.println("No hay usuarios registrados aun");
				} else {
					System.out.println(usuariosEstandar.mostrarTodo());
				}
				break;
			case 4:
				break;

			default:
				System.out.println("> Opción incorrecta");
				opcionIncorrecta = true;
				break;

			}
		} while (opcion != 4 && opcionIncorrecta == false);
	}

	// ---------------------------------------- MÉTODOS de MENÚ USUARIOS para ADMIN
	// 4.1.1. Buscar un Usuario
	public Usuario verInfoUsuario(String mail) {
		Usuario aRetornar = usuarios.buscar(mail);
		return aRetornar;
	}

	// 4.1.2. Dar alta o baja a un Usuario
	public boolean cambiarEstadoUsuarioEstandar(String mail) {
		boolean exito = false;
		Usuario usu = usuarios.buscar(mail);

		if (usu != null) {
			if (usu.isEstado() == true) {
				usu.setEstado(false);
			} else {
				usu.setEstado(true);
			}
			exito = true;
		}

		return exito;
	}

	// ---------------------------------------- 4.2 MENÚ de ELEMENTOS para ADMINS
	public void menuElementos_forAdmins(Admin admin) {
		int opcion = -1;
		String titulo = "";
		boolean opcionIncorrecta = false;

		System.out.println("\n >> Gestión de Películas y Series <<");
		do {

			System.out.println("\n > 1. Buscar un título");
			System.out.println(" > 2. Ver los títulos disponibles");
			System.out.println(" > 3. Agregar un título");
			System.out.println(" > 4. Modificar un título");
			System.out.println(" > 5. Eliminar un título");
			System.out.println(" > 6. Volver");

			opcion = input.nextInt();

			switch (opcion) {
			case 1:
				System.out.println("\n> Ingrese título a buscar: ");
				input.nextLine();
				titulo = input.nextLine();

				Elemento aBuscar = elementos.buscar(titulo);
				if (aBuscar == null) {
					System.out.println("\n> Error: el título ingresado es incorrecto o no existe");
				} else {
					System.out.println(aBuscar.toString());
				}

				break;

			case 2:
				System.out.println(elementos.mostrarTodo());
				break;

			case 3:
				crearNuevoElemento(admin);
				break;

			case 4:
				System.out.println("\n> Ingrese el nombre del título a modificar: ");
				input.nextLine();
				titulo = input.nextLine();

				Elemento aModificar = elementos.buscar(titulo);

				if (aModificar == null) {
					System.out.println("\n> Error: el título ingresado es incorrecto o no existe");
				} else {
					modificarElemento(aModificar);
					if (aModificar instanceof Pelicula) {
						admin.setPelisModificadas();
					} else {
						admin.setSeriesModificadas();
					}
				}
				break;

			case 5:
				System.out.println("\n> Ingrese título a eliminar: ");
				input.nextLine();
				titulo = input.nextLine();
				if (eliminarUnElemento(titulo) == true) {
					System.out.println("\n> Elemento eliminado con éxito");
				} else {
					System.out.println("\n> Error: el título ingresado es incorrecto o no existe");
				}
				break;

			case 6:
				break;

			default:
				System.out.println("> Opción incorrecta");
				opcionIncorrecta = true;
				break;

			}
		} while (opcion != 6 && opcionIncorrecta == false);
	}

	// -------------------------------------- MÉTODOS para MENÚ ELEMENTOS de ADMINS
	// 4.2.3. Agregar un elemento
	public void crearNuevoElemento(Admin admin) {
		int opcion = -1;
		boolean opcionIncorrecta = false;

		do {
			System.out.println("\n > 1. Agregar Película | 2. Agregar Serie | 3. Volver");
			opcion = input.nextInt();

			switch (opcion) {
			case 1:
				Pelicula nuevaPeli = ingresarDatosPelicula();
				elementos.agregar(nuevaPeli);
				admin.setPelisAgregadas();
				System.out.println("\n > Película agregada exitosamente!");
				break;

			case 2:
				Serie nuevaSerie = ingresarDatosSerie();
				elementos.agregar(nuevaSerie);
				admin.setSeriesAgregadas();
				System.out.println("\n > Serie agregada exitosamente!");
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

	// 4.2.3.1. Agregar una película
	public Pelicula ingresarDatosPelicula() {
		boolean reiniciar = false;
		System.out.println("\n> Ingrese nombre de la Película: ");
		input.nextLine();
		String nombre = input.nextLine();
		String genero = "";
		do {
			System.out.println(
					"\n> Ingrese género | \n> 1. Acción \n> 2. Suspenso \n> 3. Terror \n> 4. Comedia \n> 5. Romance: ");
			int opcion = input.nextInt();
			reiniciar = false;
			switch (opcion) {

			case 1:
				genero = "Acción";
				break;

			case 2:
				genero = "Suspenso";
				break;

			case 3:
				genero = "Terror";
				break;

			case 4:
				genero = "Comedia";
				break;

			case 5:
				genero = "Romance";
				break;

			default:
				System.out.println("\n> Opción incorrecta");
				reiniciar = true;
				break;
			}
		} while (reiniciar == true);

		System.out.println("\n> Ingrese duración: ");
		input.nextLine();
		String duracion = input.nextLine();

		System.out.println("\n> Ingrese año de estreno: ");
		int anio = input.nextInt();

		System.out.println("\n> Ingrese puntaje: ");
		float puntaje = input.nextFloat();

		Clasificacion clasificacion = null;

		do {
			System.out.println("\n> Ingrese clasificación | [1]: +18 | [2]: +13 | [3]: +7");
			int opcion = input.nextInt();
			reiniciar = false;

			switch (opcion) {

			case 1:
				clasificacion = Clasificacion.values()[0];
				break;

			case 2:
				clasificacion = Clasificacion.values()[1];
				break;

			case 3:
				clasificacion = Clasificacion.values()[2];
				break;

			default:
				System.out.println("\n> Opción incorrecta");
				reiniciar = true;
				break;
			}
		} while (reiniciar == true);

		System.out.println("\n> Ingrese descripción: ");
		input.nextLine();
		String descripcion = input.nextLine();

		System.out.println("\n> Ingrese elenco: ");
		String elenco = input.nextLine();

		Pelicula aAgregar = new Pelicula(nombre, puntaje, genero, clasificacion, descripcion, anio, elenco, duracion);
		return aAgregar;
	}

	// 4.2.3.2. Agregar una serie
	public Serie ingresarDatosSerie() {
		int opcionTemporada = -1;
		ArrayList<Temporada> listaTemporadas = new ArrayList<>();

		System.out.println("\n> Ingrese nombre de la Serie: ");
		input.nextLine();
		String nombre = input.nextLine();

		System.out.println("\n> Ingrese género | Acción, Suspenso, Terror, Comedia, Romance: ");
		String genero = input.nextLine();

		System.out.println("\n> Ingrese año de estreno: ");
		int anio = input.nextInt();

		System.out.println("\n> Ingrese descripción general de la Serie: ");
		input.nextLine();
		String descripcion = input.nextLine();

		do {
			Temporada t = ingresarDatosTemporada(listaTemporadas.size() + 1);
			listaTemporadas.add(t);

			System.out.println("\n> ¿Desea agregar otra temporada a esta Serie? | 1. Si | 2. No");
			opcionTemporada = input.nextInt();

		} while (opcionTemporada != 2);

		System.out.println("\n> Ingrese puntaje: ");
		float puntaje = input.nextFloat();

		System.out.println("\n> Ingrese clasificación | [1]: +18 | [2]: +13 | [3]: +7");
		Clasificacion clasificacion = Clasificacion.values()[input.nextInt()];

		System.out.println("\n> Ingrese elenco: ");
		input.nextLine();
		String elenco = input.nextLine();

		Serie aAgregar = new Serie(nombre, puntaje, genero, clasificacion, descripcion, anio, elenco, 4,
				listaTemporadas);

		return aAgregar;
	}

	// Agregar la temporada de una serie
	public Temporada ingresarDatosTemporada(int numeroDeTemporadas) {
		System.out.println("\n> Ingrese número de capítulos de la Temporada: ");
		int nroCapitulos = input.nextInt();

		System.out.println("\n> Ingrese descripción de la Temporada: ");
		input.nextLine();
		String descripcionTemporada = input.nextLine();

		Temporada nuevaTemporada = new Temporada(descripcionTemporada, numeroDeTemporadas, nroCapitulos);
		return nuevaTemporada;
	}

	// 4.2.4. Modificar un elemento
	public void modificarElemento(Elemento e) {
		int opcion = -1;
		boolean opcionIncorrecta = false;

		do {
			System.out.println("\n > ¿Qué desea modificar de " + e.getNombre() + "?");
			System.out.println(
					"\n > 1. Nombre \n > 2. Género \n > 3. Año de estreno \n > 4. Puntaje \n > 5. Clasificación \n > 6. Elenco");

			if (e instanceof Serie) {
				System.out.println(" > 7. Aspectos de Serie");
			} else {
				System.out.println(" > 7. Descripción");
			}

			System.out.println(" > 8. Volver");

			opcion = input.nextInt();

			switch (opcion) {
			case 1:
				System.out.println("\n> Ingrese el nuevo nombre: ");
				input.nextLine();
				String nombre = input.nextLine();
				e.setNombre(nombre);
				break;

			case 2:
				System.out.println("\n> Ingrese el nuevo género: Accion | Suspenso | Terror | Comedia | Romance ");
				input.nextLine();
				String genero = input.nextLine();
				e.setGenero(genero);
				break;

			case 3:
				System.out.println("\n> Ingrese el nuevo año de estreno: ");
				int anio = input.nextInt();
				e.setAnioDeEstreno(anio);
				break;

			case 4:
				System.out.println("\n> Ingrese el nuevo puntaje: ");
				float puntaje = input.nextFloat();
				e.setPuntaje(puntaje);
				break;

			case 5:
				System.out.println("\n> Ingrese la nueva clasificación | [1]: +18 | [2]: +13 | [3]: +7");
				Clasificacion clasificacion = Clasificacion.values()[input.nextInt()];
				e.setClasificacion(clasificacion);
				break;

			case 6:
				System.out.println("\n> Ingrese el nuevo elenco: ");
				input.nextLine();
				String elenco = input.nextLine();
				e.setElenco(elenco);
				break;

			case 7:
				if (e instanceof Serie) {
					modificarSerie((Serie) e);
				} else {

					System.out.println("\n> Ingrese la nueva descripción: ");
					input.nextLine();
					String descripcion = input.nextLine();
					e.setDescripcion(descripcion);
				}

				break;

			case 8:
				break;

			default:
				System.out.println("\n> Opción incorrecta");
				opcionIncorrecta = true;
				break;

			}
		} while (opcion != 8 && opcionIncorrecta == false);

		actualizarCambios(e);
	}

	public void actualizarCambios(Elemento modificado) {
		Iterator<Map.Entry<String, Estandar>> it = usuariosEstandar.iterador();
		Estandar actual = null;
		Elemento elemActual = null;
		boolean encontrado = false;
		int i = 0;
		while (it.hasNext()) {
			i = 0;
			Map.Entry<String, Estandar> entrada = it.next();
			actual = entrada.getValue();
			if (!actual.getListaPerfiles().estaVacia()) {
				for (i = 0; i < actual.getListaPerfiles().cantidadPerfiles(); i++) {
					if (!actual.getListaPerfiles().retornar(i).getMiLista().estaVacia()) {
						Iterator<Elemento> itElem = actual.getListaPerfiles().retornar(i).getMiLista().iterador();
						while (itElem.hasNext() && encontrado == false) {
							elemActual = itElem.next();
							if (elemActual.getId() == modificado.getId()) {
								actual.getListaPerfiles().retornar(i).getMiLista().borrar(elemActual);
								actual.getListaPerfiles().retornar(i).getMiLista().agregar(modificado);
								encontrado = true;
							}
						}
					}
				}
			}
		}
	}

	// 4.2.4.7. Modificar aspectos de serie
	public void modificarSerie(Serie s) {

		int opcion = -1;
		boolean opcionIncorrecta = false;

		do {
			System.out.println("\n> ¿Qué desea modificar de la serie?");
			System.out.println("> 1. Cantidad de temporadas | 2. Información de una temporada | 3. Volver");

			opcion = input.nextInt();

			switch (opcion) {
			case 1:
				System.out.println("\n> Ingrese la nueva cantidad de temporadas: ");

				int cdadTemporadas = input.nextInt();
				s.setCantTemporadas(cdadTemporadas);

				System.out.println("\n> Cantidad de temporadas modificada exitosamente!");
				break;

			case 2:
				ArrayList<Temporada> temporadas = s.getListaTemporadas();

				System.out.println("\n> Ingrese número de la temporada a modificar: ");
				int nroTemporada = input.nextInt();

				Temporada aModificar = buscarTemporadaPorNumero(nroTemporada, temporadas);

				if (aModificar == null) {
					System.out.println("\n> Error: el número de temporada ingresada no existe en " + s.getNombre());
				} else {
					modificarTemporada(aModificar);
					System.out.println("\n> Temporada modificada exitosamente!");
				}
				break;

			case 3:
				break;

			default:
				System.out.println("\n> Opción incorrecta");
				opcionIncorrecta = true;
				break;

			}
		} while (opcion != 3 && opcionIncorrecta == false);

	}

	public Temporada buscarTemporadaPorNumero(int nro, ArrayList<Temporada> listaTemp) {
		Temporada aRetornar = null;

		for (int i = 0; i < listaTemp.size(); i++) {
			if (listaTemp.get(i).getNumeroTemporada() == nro) {
				aRetornar = listaTemp.get(i);
			}
		}

		return aRetornar;
	}

	// 4.2.4.7.2. Modificar información de una temporada
	public void modificarTemporada(Temporada t) {
		int opcion = -1;
		boolean opcionIncorrecta = false;

		do {
			System.out.println("\n> ¿Qué aspecto desea modificar de la temporada " + t.getNumeroTemporada() + "?");
			System.out.println("> 1. Cantidad de capítulos | 2. Descripción | 3. Volver");

			opcion = input.nextInt();

			switch (opcion) {
			case 1:
				System.out.println(
						"\n> Ingrese nuevo número de capítulos de la temporada " + t.getNumeroTemporada() + ": ");
				int nroCapitulos = input.nextInt();
				t.setCantCapitulos(nroCapitulos);
				break;

			case 2:
				System.out.println("\n> Ingrese nueva descripción de la temporada " + t.getNumeroTemporada() + ": ");
				input.nextLine();
				String descripcion = input.nextLine();
				t.setDescripcionTemporada(descripcion);

				break;

			case 3:
				break;

			default:
				System.out.println("\n> Opción incorrecta");
				opcionIncorrecta = true;
				break;
			}

		} while (opcion != 3 && opcionIncorrecta == false);
	}

	// 4.2.5. Eliminar un elemento
	public boolean eliminarUnElemento(String nombre) {
		boolean exito = false;
		Elemento aEliminar = elementos.buscar(nombre);

		if (aEliminar != null) {
			elementos.borrar(aEliminar);
			exito = true;
		}

		return exito;
	}

	public void guardarUsuarios() {
		FileOutputStream fileOutputStream;
		ObjectOutputStream objectOutputStream;
		try {
			fileOutputStream = new FileOutputStream("Usuarios.bin");
			try {
				objectOutputStream = new ObjectOutputStream(fileOutputStream);
				Iterator<Map.Entry<String, Usuario>> it = usuarios.iterador();
				Usuario aGuardar = null;
				while (it.hasNext()) {
					Map.Entry<String, Usuario> entrada = it.next();
					aGuardar = entrada.getValue();
					objectOutputStream.writeObject(aGuardar);
				}
				objectOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void leerArchivoUsuarios() {
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream("Usuarios.bin");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			Usuario aGuardar = null;
			int lectura = 1;
			while (lectura == 1) {
				aGuardar = (Usuario) objectInputStream.readObject();
				agregarUsuario(aGuardar);
			}
			objectInputStream.close();
		} catch (EOFException e) {
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void guardarElementos() {

		FileOutputStream fileOutputStream;
		try {
			fileOutputStream = new FileOutputStream("Elementos.bin");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			Iterator<Elemento> iterador = elementos.iterador();
			while (iterador.hasNext()) {
				Elemento elem = iterador.next();
				objectOutputStream.writeObject(elem);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void leerElementos() {

		FileInputStream fileInputStream;
		ObjectInputStream objectInputStream;
		try {
			fileInputStream = new FileInputStream("Elementos.bin");
			objectInputStream = new ObjectInputStream(fileInputStream);
			int lectura = 1;
			while (lectura == 1) {
				Elemento aLeer = (Elemento) objectInputStream.readObject();
				elementos.agregar(aLeer);
			}

			objectInputStream.close();
		} catch (EOFException e) {
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void guardarUsuariosEstandar() {

		FileOutputStream fileOutputStream;
		ObjectOutputStream objectOutputStream;

		try {
			fileOutputStream = new FileOutputStream("UsuariosEstandar.bin");
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			Iterator<Map.Entry<String, Estandar>> it = usuariosEstandar.getHashmapUsuariosEstandar().iterador();
			Estandar aGuardar = null;

			while (it.hasNext()) {
				Map.Entry<String, Estandar> actual = it.next();
				aGuardar = actual.getValue();
				objectOutputStream.writeObject(aGuardar);
			}

			objectOutputStream.close();

		} catch (FileNotFoundException fnf) {
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void leerArchivoUsuariosEstandar() {
		FileInputStream fileInputStream;
		ObjectInputStream objectInputStream;

		try {
			fileInputStream = new FileInputStream("UsuariosEstandar.bin");
			objectInputStream = new ObjectInputStream(fileInputStream);

			int lectura = -1;
			while (lectura == 1) {
				Estandar usu = (Estandar) objectInputStream.readObject();
			}

			objectInputStream.close();
		} catch (FileNotFoundException fnf) {
			System.out.println("Fin del archivo");
		} catch (EOFException eof) {
			System.out.println(eof.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void guardarUsuariosAdmins() {

		FileOutputStream fileOutputStream;
		ObjectOutputStream objectOutputStream;

		try {
			fileOutputStream = new FileOutputStream("UsuariosAdmin.bin");
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			Iterator<Map.Entry<String, Admin>> it = admins.getHashmapAdmins().iterador();
			Admin aGuardar = null;

			while (it.hasNext()) {
				Map.Entry<String, Admin> actual = it.next();
				aGuardar = actual.getValue();
				objectOutputStream.writeObject(aGuardar);
			}

			objectOutputStream.close();

		} catch (FileNotFoundException fnf) {
			System.out.println("Fin del archivo");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void leerArchivoUsuariosAdmins() {
		FileInputStream fileInputStream;
		ObjectInputStream objectInputStream;

		try {
			fileInputStream = new FileInputStream("UsuariosAdmin.bin");
			objectInputStream = new ObjectInputStream(fileInputStream);

			int lectura = -1;
			while (lectura == 1) {
				Admin usu = (Admin) objectInputStream.readObject();
			}

			objectInputStream.close();
		} catch (FileNotFoundException fnf) {
			System.out.println("Fin del archivo");
		} catch (EOFException eof) {
			System.out.println(eof.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
