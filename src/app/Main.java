package app;

import java.util.ArrayList;

import org.json.JSONObject;

import elementos.Clasificacion;
import elementos.ListaElementos;
import json.JsonUtiles;
import peliculas.Pelicula;
import series.Serie;
import series.Temporada;
import usuarios.Admin;
import usuarios.Estandar;


public class Main {

	public static void main(String[] args) {
		
		
		Nerflis app = new Nerflis();
		
		Pelicula pelicula1 = new Pelicula("Titanic", 5, "Romance", Clasificacion.MAS13, "Jack es un joven artista que gana un pasaje para viajar a América\n en el Titanic, el transatlántico más grande y seguro jamás construido.", 1997, "Kate Winslet, Leonardo DiCaprio, Billy Zane", "2:46");
		Pelicula pelicula2 = new Pelicula("Rescatando al soldado Ryan", 4, "Accion", Clasificacion.MAS18, "Luego del Dia D, el Capitan John Miller y su grupo de soldados\n se adentran en territorio enemigo para rescatar a un paracaidista que ha perdido a tres hermanos en combate", 1998, "Tom Hanks, Tom Sizemore, Edward Burns", "2:50");
		Pelicula pelicula3 = new Pelicula("Eterno resplandor de una mente sin recuerdos", 5, "Romance", Clasificacion.MAS13, "Parecían la pareja ideal, su primer encuentro fue mágico, pero con el paso del tiempo ella deseó nunca haberlo conocido. Su anhelo se hace realidad gracias a un polémico y radical invento. Sin embargo descubrirán que el destino no se puede controlar.", 2004, "Kate Winslet, Jim Carrey", "1:47");
		Pelicula pelicula4 = new Pelicula("John Wick 3: Parabellum", 4, "Accion", Clasificacion.MAS18, "John Wick regresa de nuevo pero con una recompensa sobre su cabeza que persigue unos mercenarios. Tras asesinar a uno de los miembros de su gremio, Wick es expulsado y se convierte en el foco de atención de todos los sicarios de la organización.", 2019, "Keanu Reeves, Halle Berry, Laurence Fishburne", "2:10");
		Temporada STTempo1 = new Temporada("La primera temporada comienza en noviembre de 1983, cuando Will Byers es secuestrado por \nuna criatura del Otro Lado (demogorgon). Su madre, Joyce, y el jefe de policía del pueblo,\nJim Hopper, buscan a Will. Al mismo tiempo, una joven psicoquinética\nllamada \"Once\" escapa del laboratorio y ayuda a los amigos de Will que son Mike, Dustin y\nLucas, en sus propios esfuerzos por encontrar a Will.", 1, 8);
		Temporada STTempo2 = new Temporada("Otoño de 1984. Con Once aparentemente desaparecida, Joyce y Hopper, junto con Nancy, \nJonathan y Steve, así como también Mike, Dustin y Lucas, en compañía con la recién llegada \ncaliforniana Max, deberán unir sus fuerzas para ayudar nuevamente a Will \ndespués de que se convierte en el objetivo del Otro Lado cuando una gran entidad con \ntentáculos, llamada el Desuellamentes, intenta habitar su cuerpo", 2, 9);
		ArrayList<Temporada> temposST =new ArrayList();
		temposST.add(STTempo1);
		temposST.add(STTempo2);
		Serie serie1 = new Serie("Stranger Things", 2, "Suspenso", Clasificacion.MAS13, "Cuando un niño desaparece, sus amigos, la familia y la policía se ven envueltos en una serie de \neventos misteriosos al tratar de encontrarlo.", 2016, "Mike Wheeler, Millie Bobby Brown, Noah Schnapp", 4, temposST);
		Pelicula pelicula5 = new Pelicula("Corre", 3, "Terror", Clasificacion.MAS13, "Dicen que el amor de una madre es eterno pero para Chloe no es un consuelo, sino una amenaza. \nHay algo extraño, incluso siniestro en la relación de ella y su madre, Diane.", 2021, "Sarah Paulson, Kiera Allen, Pat Healy", "1:29");
		Pelicula pelicula6 = new Pelicula("Terror en Silent Hill", 4, "Terror", Clasificacion.MAS13, "Sin poder aceptar el hecho de que su hija se está muriendo, Rose decide llevar a la niña con un curandero. \nDurante el camino, las dos viajan a través de un portal que las conduce a un misterioso pueblo llamado Silent Hill.", 2006, "Radha Mitchell, Sean Bean, Laurie Holden", "2:05");
		Pelicula pelicula7 = new Pelicula("Ted", 3, "Comedia", Clasificacion.MAS13, "Cuando John Bennett era un niño pequeño, pidió el deseo de que Ted, su querido oso de peluche, cobrara vida. Treinta años más tarde, Ted continúa siendo el compañero de John, ante el disgusto de Lori, la novia de John.", 2012, "Mark Wahlberg, Mila Kunis, Seth MacFarlane", "1:46");
		Pelicula pelicula8 = new Pelicula("La Mascara", 5, "Comedia", Clasificacion.MAS7, "Un aburrido empleado de banca encuentra una máscara que representa a Loki, un dios de la malicia y la travesura, la cual le transformará por completo.", 1994, "Jim Carrey, Cameron Diaz,Peter Riegert", "1:41");
		Pelicula pelicula9 = new Pelicula("La mujer en la ventana", 3, "Comedia", Clasificacion.MAS18, "La Dra. Anna Fox pasa sus días encerrada en su casa de Nueva York, bebiendo vino mientras ve viejas películas y espía a sus vecinos. Un día, mientras mira por la ventana, ve algo que sucede enfrente de su casa, en el hogar de los Russell, una familia a la que todo el barrio considera ejemplar.", 2021, "Amy Adams, Gary Oldman, Anthony Mackie", "1:41");
		Pelicula pelicula10 = new Pelicula("Fractura", 2, "Comedia", Clasificacion.MAS13, "Ray y su mujer viajan con su hija por el país, pero en una parada en una gasolinera, la pequeña se tropieza y fractura el brazo. Tras varias horas de espera en el hospital, la niña es atendida y Ray, exhausto, se queda dormido. Al despertar, no hay nadie con él, ni noticias de su mujer e hija.", 2019, "Sam Worthington, Lily Rabe, Stephen Tobolowsky", "1:40");
		
		app.agregarElemento(pelicula1);
		app.agregarElemento(pelicula2);
		app.agregarElemento(pelicula3);
		app.agregarElemento(pelicula4);
		app.agregarElemento(serie1);
		app.agregarElemento(pelicula5);
		app.agregarElemento(pelicula6);
		app.agregarElemento(pelicula7);
		app.agregarElemento(pelicula8);
		app.agregarElemento(pelicula9);
		app.agregarElemento(pelicula10);
		
		Admin admin1 = new Admin("admin@gmail.com","1234");
		app.agregarUsuario(admin1);

		
		/*app.leerArchivoUsuarios();
		app.leerArchivoUsuariosEstandar();
		app.leerElementos();
		app.leerArchivoUsuariosAdmins();*/
		
		app.menuLaunch();
		
		JsonUtiles json = new JsonUtiles();
		JSONObject datos = json.exportarToJSON(app.getUsuarios(),app.getElementos());
		json.grabarJSON(datos);
		String contenidoJSON = JsonUtiles.leer();
		System.out.println(contenidoJSON);
		
		/*app.guardarElementos();
		app.guardarUsuarios();
		app.guardarUsuariosEstandar();
		app.guardarUsuariosAdmins();*/
		
		
	}
	
	
	
}
