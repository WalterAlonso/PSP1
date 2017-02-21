package uniandes.ecos.conceptosAvanzados.contadorLineaJava.test;

import java.io.File;
import java.util.ArrayList;

import junit.framework.TestCase;

/**
 * Inicia el programa Contador de linea Java.
 * @author Walter Alonso.
 *
 */
public class ArchivoJavaTest extends TestCase {

	/**
	 * Metodo principal, la peticion inicial del usuario 
	 */
	private boolean elArchivoEstaVacio(File archivo) throws Exception {		
		FileReader stream = new FileReader(archivo);
		BufferedReader reader = new BufferedReader( stream);
		String line = reader.readLine();
		boolean esVacio = false;
		if(line == null) {
			esVacio = true;
		}
		
		/*
		este es un comentario
		doble linea
		*/

		reader.close();
		stream.close();
		
		return esVacio;		
	}	

	public static void main(String[] args) {	
		EstadisticaVista vista = new EstadisticaVista();
		//Soy comentario sencillo
		try {		
			if(args.length < 1) {
				throw new Exception("No tiene argumentos");
			}
			
			String archivo = "./ArchivoProcesar/" + args[0];
			ArchivoEstadistico archivoEstadistico = new ArchivoEstadistico(archivo);
			Dato datoRaiz = archivoEstadistico.darDatosArchivo();
			
			Media media = new Media(datoRaiz);
			vista.asignarMedia(media.darCalculo());
			vista.mostrarMedia();
			
			DesviacionEstandar desviacionEstandar = new DesviacionEstandar(datoRaiz, media);
			vista.asignarDesviacionEstandar(desviacionEstandar.darCalculo());			
			vista.mostrarDesviacionEstandar();
		}
		catch(Exception ex) {				
			vista.mostrarError(ex.getMessage());
		}
	}
}