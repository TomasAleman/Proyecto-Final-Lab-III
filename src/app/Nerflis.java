package app;

import java.util.ArrayList;
import java.util.Iterator;
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
			if (usuarios.contieneMail(mail)) // comprueba que el mail esté registrado
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
			if (usuarios.contieneMail(email)) {
				retorno = true;
			}

		}
		return retorno;
	}

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
			perfiles += " (" + (i + 1) + ") " + user.getListaPerfiles().mostrarPorIndice(i);
		}

		return perfiles;
	}

	// ---------------------------------------- MÉTODOS de MENÚ MI LISTA

	public void agregarMiLista(Perfil perfil) {
		String opcionID = "";
		boolean flag = false;
		while (flag == false) {
			System.out.println(elementos.mostrarTodo());
			System.out.println("> Ingrese nombre del elemento a agregar:");
			input.hasNextLine();
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

	// ---------------------------------------- MÉTODOS de MENÚ de UN PERFIL

	public String verPeliculas(boolean infantil) {
		String contenido = "";
		if (infantil == false) {
			contenido = elementos.mostrarPeliculas();
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

	public String verSeries(boolean infantil) {
		String contenido = "";
		if (infantil == false) {
			contenido = elementos.mostrarSeries();
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

				System.out.println(elementos.mostrarPorGenero(aFiltrar));
				break;

			case 3:
				System.out.println(elementos.mostrarPorOrdenAlfabetico());
				break;

			case 4:
				System.out.println(elementos.mostrarPorFecha());
				break;

			case 5:
				System.out.println(elementos.mostrarPorPuntaje());
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

	// ---------------------------------------- LAUNCH MENU

	public void menuLaunch() {
		int opcion = -1;
		boolean opcionIncorrecta = false;
		boolean menuRegistroEjecutado = false;

		do {
			if (menuRegistroEjecutado == false) {
				System.out.println("\n>> Bienvenido a Nerflis << ");
				System.out.println("\n > 1. Registrarse");
				System.out.println(" > 2. Iniciar sesión");
				System.out.println(" > 3. Salir del sistema\n");
			} else {
				System.out.println("\n > 2. Iniciar sesión");
				System.out.println(" > 3. Salir del sistema\n");
			}

			opcion = input.nextInt();

			switch (opcion) {
			case 1:
				input.nextLine();
				if (menuRegistroEjecutado == false) {
					menuRegistroEjecutado = menuRegistro();
					System.out.println("> Registro exitoso!");
				} else {
					System.out.println("> Ya se encuentra registrado");
				}
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

	// ---------------------------------------- MENÚ de REGISTRO

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
					Usuario nuevo = new Estandar(mail, clave);
					usuarios.agregar(nuevo);

					reintentar = false;
					ejecucionExitosa = true;
				}

			} catch (ExcepcionUsuarioIncorrecto e) {
				System.out.println(e.getMessage());
			}

		} while (reintentar == true);

		return ejecucionExitosa;

	}

	// ---------------------------------------- MENÚ de INICIO DE SESIÓN

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
					menuPrincipalAdmins(aLogear);

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

	// ---------------------------------------- MENÚ de PERFILES

	public void menuPerfiles(Estandar user) {
		int opcion = -1;
		boolean opcionIncorrecta = false;

		do {
			System.out.println("\n >> Perfiles <<");
			System.out.println("\n> 1. Crear un perfil");
			System.out.println("> 2. Entrar a un perfil");
			System.out.println("> 3. Volver");

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
				break;

			default:
				System.out.println("> Opción incorrecta");
				opcionIncorrecta = true;
				break;

			}
		} while (opcion != 3 && opcionIncorrecta == false);
	}

	// ---------------------------------------- MENÚ de UN PERFIL

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
				System.out.println(sugerencias(perfil));
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

	public String sugerencias(Perfil perfil) {
		String aSugerir = "";
		String generoSugerencia = generoMasVisto(perfil.getMiLista());

		Iterator<Elemento> it = elementos.iterador();

		while (it.hasNext()) {
			Elemento actual = it.next();

			if (actual.getGenero().equalsIgnoreCase(generoSugerencia)) {
				aSugerir += "\n" + actual.toString();
			}
		}

		return aSugerir;
	}

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

	// ---------------------------------------- MENÚ de MI LISTA
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
					if (perfil.getMiLista().validos() != 0) {
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
				menuPerfil(perfil);
				break;

			default:
				System.out.println("> Opción incorrecta");
				opcionIncorrecta = true;
				break;
			}

		} while (opcion != 4 && opcionIncorrecta == false);

	}

	// ---------------------------------------- MENÚ de ADMINS
	public void menuPrincipalAdmins(Usuario admin) {
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
				menuElementos_forAdmins();
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

	// ---------------------------------------- MENÚ de USUARIOS para ADMINS

	public void menuUsuarios_forAdmins() {
		int opcion = -1;
		String emailUsu = "";
		boolean opcionIncorrecta = false;

		System.out.println("\n >> Gestión de Usuarios <<");
		do {

			System.out.println("\n > 1. Buscar un Usuario");
			System.out.println(" > 2. Alta o baja a un Usuario");
			System.out.println(" > 3. Volver");

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
				break;

			default:
				System.out.println("> Opción incorrecta");
				opcionIncorrecta = true;
				break;

			}
		} while (opcion != 3 && opcionIncorrecta == false);
	}

	// ---------------------------------------- MÉTODOS de MENÚ USUARIOS para ADMIN

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

	public Usuario verInfoUsuario(String mail) {
		Usuario aRetornar = usuarios.buscar(mail);
		return aRetornar;
	}

	// ---------------------------------------- MENÚ de ELEMENTOS para ADMINS

	public void menuElementos_forAdmins() {
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
				crearNuevoElemento();
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

	// ---------------------------------------- MÉTODOS para MENÚ ELEMENTOS de
	// ADMINS
	public void crearNuevoElemento() {
		int opcion = -1;
		boolean opcionIncorrecta = false;

		do {
			System.out.println("\n > 1. Agregar Película | 2. Agregar Serie | 3. Volver");
			opcion = input.nextInt();

			switch (opcion) {
			case 1:
				Pelicula nuevaPeli = ingresarDatosPelicula();
				elementos.agregar(nuevaPeli);
				System.out.println("\n > Película agregada exitosamente!");
				break;

			case 2:
				Serie nuevaSerie = ingresarDatosSerie();
				elementos.agregar(nuevaSerie);
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

	public Pelicula ingresarDatosPelicula() {
		boolean reiniciar = false;
		System.out.println("\n> Ingrese nombre de la Película: ");
		input.nextLine();
		String nombre = input.nextLine();
		String genero="";
		do {
			System.out.println("\n> Ingrese género | \n> 1. Acción \n> 2. Suspenso \n> 3. Terror \n> 4. Comedia \n> 5. Romance: ");
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
		}while(reiniciar == true);
		

		System.out.println("\n> Ingrese duración: ");
		input.nextLine();
		String duracion = input.nextLine();

		System.out.println("\n> Ingrese año de estreno: ");
		int anio = input.nextInt();

		System.out.println("\n> Ingrese puntaje: ");
		float puntaje = input.nextFloat();
		
		Clasificacion clasificacion=null;
		
		do{
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
		}while(reiniciar == true);


		System.out.println("\n> Ingrese descripción: ");
		input.nextLine();
		String descripcion = input.nextLine();

		System.out.println("\n> Ingrese elenco: ");
		String elenco = input.nextLine();

		Pelicula aAgregar = new Pelicula(nombre, puntaje, genero, clasificacion, descripcion, anio, elenco, duracion);
		return aAgregar;
	}

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

	public Temporada ingresarDatosTemporada(int numeroDeTemporadas) {
		System.out.println("\n> Ingrese número de capítulos de la Temporada: ");
		int nroCapitulos = input.nextInt();

		System.out.println("\n> Ingrese descripción de la Temporada: ");
		input.nextLine();
		String descripcionTemporada = input.nextLine();

		Temporada nuevaTemporada = new Temporada(descripcionTemporada, numeroDeTemporadas, nroCapitulos);
		return nuevaTemporada;
	}

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

	}

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

	public boolean eliminarUnElemento(String nombre) {
		boolean exito = false;
		Elemento aEliminar = elementos.buscar(nombre);

		if (aEliminar != null) {
			elementos.borrar(aEliminar);
			exito = true;
		}

		return exito;
	}

}
