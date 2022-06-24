package archivos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Archivo {

	
	public static void guardar() {
		FileInputStream fileInputStreamn ;
		ObjectInputStream objectInputStream ;
		try {
			fileInputStreamn = new FileInputStream("Usuarios.bin");
			try {
				objectInputStream = new ObjectInputStream(fileInputStreamn);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
