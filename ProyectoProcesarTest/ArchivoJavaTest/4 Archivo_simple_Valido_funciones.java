package uniandes.ecos.conceptosAvanzados.contadorLineaJava.test;

import java.io.File;
import java.util.ArrayList;

import junit.framework.TestCase;

public class ArchivoJavaTest extends TestCase {

	private boolean elArchivoEstaVacio(File archivo) throws Exception {		
		FileReader stream = new FileReader(archivo);
		BufferedReader reader = new BufferedReader( stream);
		String line = reader.readLine();
		boolean esVacio = false;
		if(line == null) {
			esVacio = true;
		}
		
		reader.close();
		stream.close();
		
		return esVacio;		
	}	
}