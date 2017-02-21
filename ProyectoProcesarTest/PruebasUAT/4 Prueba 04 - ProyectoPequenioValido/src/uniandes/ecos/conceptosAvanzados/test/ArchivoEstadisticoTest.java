package uniandes.ecos.conceptosAvanzados.test;

import junit.framework.TestCase;
import uniandes.ecos.conceptosAvanzados.modelo.ArchivoEstadistico;
import uniandes.ecos.conceptosAvanzados.modelo.Dato;

/**
 * 
 * Clase que maneja los archivos
 * @author Walter Alonso
 * 
 */
public class ArchivoEstadisticoTest  extends TestCase {
	
	/**
	 * Clase ArchivoEstadistico a testear
	 */
	private ArchivoEstadistico archivoEstadistico;
	
	/**
	 * Constante donde estan los archivos a testear
	 */
	public static final String rutaArchivo = "./ArchivoProcesarTest/";
		
	/**
     * Este método se encarga de verificar si el archivo tiene extension erronea
     * 
     */
    public void testArchivoConExtensionErronea() {
    	try {
    		String archivo = rutaArchivo + "ArchivoExtensionErronea.dat";
    		archivoEstadistico = new ArchivoEstadistico(archivo);
    		archivoEstadistico.darDatosArchivo();
    		fail("Debio fallar: No valido el formato del archivo");
    	}
    	catch(Exception ex) { 		
    		assertEquals( "El mensaje debe coincidir: El archivo debe ser en formato txt", "El archivo debe ser en formato txt", ex.getMessage());
    	}
    }
    
	/**
     * Este método se encarga de verificar si el archivo esta vacio
     * 
     */
    public void testArchivoVacio() {
    	try {
    		String archivo = rutaArchivo + "ArchivoVacio.txt";
    		archivoEstadistico = new ArchivoEstadistico(archivo);
    		archivoEstadistico.darDatosArchivo();
    		fail("Debio fallar: No valido si el archivo estaba vacio");
    	}
    	catch(Exception ex) {  		
    		assertEquals( "El mensaje debe coincidir: El archivo esta vacio", "El archivo esta vacio", ex.getMessage());
    	}
    }
	
    /**
     * Este método se encarga de verificar si el archivo tiene caracteres
     * 
     */
    public void testArchivoConCaracteres() {
    	try {
    		String archivo = rutaArchivo + "ArchivoConCaracteres.txt";
    		archivoEstadistico = new ArchivoEstadistico(archivo);
    		archivoEstadistico.darDatosArchivo();
    		fail("Debio fallar: archivo con caracteres");
    	}
    	catch(Exception ex) {    	    		
    		assertEquals( "El mensaje debe coincidir: El archivo no tiene el formato esperado con solo numeros reales con salto de linea", 
    				"El archivo no tiene el formato esperado con solo numeros reales con salto de linea", ex.getMessage());
    	}
    }
    
    /**
     * Este método se encarga de verificar si el archivo tiene solo un dato, dado que arrojaria un error
     * para el calculo de la desviacion estandar
     * @throws Exception 
     * 
     */
    public void testArchivoValido() {
    	try {
	    	String archivo = rutaArchivo + "ArchivoValido.txt";
	    	archivoEstadistico = new ArchivoEstadistico(archivo);
	    	Dato datoRaiz = archivoEstadistico.darDatosArchivo();
	    	
	    	assertEquals( "El primer dato debe ser 160.0", 160.0, datoRaiz.darValor());
	    	Dato datoSiguiente = datoRaiz.darDatoSiguiente();
	    	assertEquals( "El segundo dato debe ser 591.1", 591.1, datoSiguiente.darValor());
	    	datoSiguiente = datoSiguiente.darDatoSiguiente();
	    	assertEquals( "El tercer dato debe ser 114.0", 114.0, datoSiguiente.darValor());
	    	datoSiguiente = datoSiguiente.darDatoSiguiente();
	    	assertEquals( "El cuarto dato debe ser 229.0", 229.0, datoSiguiente.darValor());
	    	datoSiguiente = datoSiguiente.darDatoSiguiente();
	    	assertEquals( "El quinto dato debe ser 230.0", 230.0, datoSiguiente.darValor());
	    	datoSiguiente = datoSiguiente.darDatoSiguiente();
	    	assertEquals( "El sexto dato debe ser 270.0", 270.0, datoSiguiente.darValor());
	    	datoSiguiente = datoSiguiente.darDatoSiguiente();
	    	assertEquals( "El septimo dato debe ser 128.0", 128.0, datoSiguiente.darValor());
	    	datoSiguiente = datoSiguiente.darDatoSiguiente();
	    	assertEquals( "El octavo dato debe ser 1657.0", 1657.0, datoSiguiente.darValor());
	    	datoSiguiente = datoSiguiente.darDatoSiguiente();
	    	assertEquals( "El noveno dato debe ser 624.0", 624.0, datoSiguiente.darValor());
	    	datoSiguiente = datoSiguiente.darDatoSiguiente();
	    	assertEquals( "El decimo dato debe ser 1503.0", 1503.0, datoSiguiente.darValor());
    	}
    	catch(Exception ex) {
    		ex.printStackTrace();
    		fail("Error :" + ex.getMessage());
    	}
    }	
}

