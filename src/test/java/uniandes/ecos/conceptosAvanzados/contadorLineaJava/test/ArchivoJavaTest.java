package uniandes.ecos.conceptosAvanzados.contadorLineaJava.test;

import java.io.File;

import junit.framework.TestCase;
import uniandes.ecos.conceptosAvanzados.contadorLineaJava.modelo.ArchivoJava;


/**
 * Tests para la clase ArchivoJava
 * @author Walter Alonso.
 *
 */
public class ArchivoJavaTest extends TestCase {
	
	/**
	 * Clase ArchivoEstadistico a testear
	 */
	private ArchivoJava archivoJava;
	
	/**
	 * Constante donde estan los archivos a testear
	 */
	public static final String rutaArchivo = "./ProyectoProcesarTest/ArchivoJavaTest/";
		
	/**
     * Este método se encarga de verificar si el archivo tiene extension erronea
     * 
     */
    public void testArchivoEsVacioNoCuentaLineas() {
    	try {
    		String archivo = rutaArchivo + "1 ArchivoVacio.java";
    		archivoJava = new ArchivoJava(archivo, "1 ArchivoVacio.java");
    		File file = new File(archivo);
    		archivoJava.darArchivo(file);    		
    		assertEquals( "El archivo debe estar vacio con 0 lineas fisicas", 0, archivoJava.darNumeroLineasFisicas());
    		assertEquals( "El archivo debe estar vacio con 0 lineas logicas", 0, archivoJava.darNumeroLineasLogicas());
    	}
    	catch(Exception ex) { 		
    		fail("Se genero excepcion "+ ex.getMessage());
    	}
    }
    
    /**
     * Este método se encarga de verificar si el archivo tiene extension erronea
     * 
     */
    public void testArchivoComienzaLineasInvalidasIgnoraLinea() {
    	try {
    		String archivo = rutaArchivo + "2 ArchivoCaracteresInvalidos.java";
    		archivoJava = new ArchivoJava(archivo, "2 ArchivoCaracteresInvalidos.java");
    		File file = new File(archivo);
    		archivoJava.darArchivo(file);    		
    		assertEquals( "El archivo debe estar vacio con 0 lineas fisicas", 2, archivoJava.darNumeroLineasFisicas());
    		assertEquals( "El archivo debe estar vacio con 0 lineas logicas", 1, archivoJava.darNumeroLineasLogicas());
    	}
    	catch(Exception ex) { 		
    		fail("Se genero excepcion "+ ex.getMessage());
    	}
    }
      
    /**
     * Cuenta archivo simple que tiene paquete, imports y clase
     * 
     */
    public void testArchivoSimpleValidoCuentaLineas() {
    	try {
    		String archivo = rutaArchivo + "3 Archivo_simple_Valido.java";
    		archivoJava = new ArchivoJava(archivo, "3 Archivo_simple_Valido.java");
    		File file = new File(archivo);
    		archivoJava.darArchivo(file);    		
    		assertEquals( "El archivo debe estar vacio con 12 lineas fisicas", 12, archivoJava.darNumeroLineasFisicas());
    		assertEquals( "El archivo debe estar vacio con 5 lineas logicas", 5, archivoJava.darNumeroLineasLogicas());
    	}
    	catch(Exception ex) { 		
    		fail("Se genero excepcion "+ ex.getMessage());
    	}
    }
    
    
    /**
     * Cuenta las lineas de archivo teniendo en cuenta funciones y condicionales
     * 
     */
    public void testArchivoFuncionesCondicionalValidoCuentaLineas() {
    	try {
    		String archivo = rutaArchivo + "4 Archivo_simple_Valido_funciones.java";
    		archivoJava = new ArchivoJava(archivo, "4 Archivo_simple_Valido_funciones.java");
    		File file = new File(archivo);
    		archivoJava.darArchivo(file);    		
    		assertEquals( "El archivo debe estar vacio con 24 lineas fisicas", 24, archivoJava.darNumeroLineasFisicas());
    		assertEquals( "El archivo debe estar vacio con 15 lineas logicas", 15, archivoJava.darNumeroLineasLogicas());
    	}
    	catch(Exception ex) { 		
    		fail("Se genero excepcion "+ ex.getMessage());
    	}
    }
    
    
    /**
     * Cuenta lineas ignorndo comentarios
     * 
     */
    public void testArchivoConComentariosValidoCuentaLineas() {
    	try {
    		String archivo = rutaArchivo + "5 Archivo_simple_Valido_Comentarios.java";
    		archivoJava = new ArchivoJava(archivo, "5 Archivo_simple_Valido_Comentarios.java");
    		File file = new File(archivo);
    		archivoJava.darArchivo(file);    		
    		assertEquals( "El archivo debe estar vacio con 62 lineas fisicas", 62, archivoJava.darNumeroLineasFisicas());
    		assertEquals( "El archivo debe estar vacio con 31 lineas logicas", 31, archivoJava.darNumeroLineasLogicas());
    	}
    	catch(Exception ex) { 		
    		fail("Se genero excepcion "+ ex.getMessage());
    	}
    }
    
    /**
     * Archivo con solo comentarios, no debe contarlos
     * 
     */
    public void testArchivoConComentariosNoCuentaLineas() {
    	try {
    		String archivo = rutaArchivo + "6 Archivo_simple_Comentarios.java";
    		archivoJava = new ArchivoJava(archivo, "6 Archivo_simple_Comentarios.java");
    		File file = new File(archivo);
    		archivoJava.darArchivo(file);    		
    		assertEquals( "El archivo debe estar vacio con 16 lineas fisicas", 16, archivoJava.darNumeroLineasFisicas());
    		assertEquals( "El archivo debe estar vacio con 0 lineas logicas", 0, archivoJava.darNumeroLineasLogicas());
    	}
    	catch(Exception ex) { 		
    		fail("Se genero excepcion "+ ex.getMessage());
    	}
    }
    
    /**
     * Cuenta las lineas de archivo con una forma de doble concatenacion 
     * 
     */
    public void testArchivoConDobleLineaLogicaCuentaLineas() {
    	try {
    		String archivo = rutaArchivo + "7 Archivo_simple_doble_InstruccionLogica.java";
    		archivoJava = new ArchivoJava(archivo, "7 Archivo_simple_doble_InstruccionLogica.java");
    		File file = new File(archivo);
    		archivoJava.darArchivo(file);    		
    		assertEquals( "El archivo debe estar vacio con 3 lineas fisicas", 3, archivoJava.darNumeroLineasFisicas());
    		assertEquals( "El archivo debe estar vacio con 3 lineas logicas", 3, archivoJava.darNumeroLineasLogicas());
    	}
    	catch(Exception ex) { 		
    		fail("Se genero excepcion "+ ex.getMessage());
    	}
    }
    
    
    /**
     * Cuenta las lineas de archivo fisicas y logicas.
     * se tiene una clase normal
     * 
     */
    public void testArchivoCompletoValidoCuentaLineas() {
    	try {
    		String archivo = rutaArchivo + "8 Archivo_grande_Valido.java";
    		archivoJava = new ArchivoJava(archivo, "8 Archivo_grande_Valido.java");
    		File file = new File(archivo);
    		archivoJava.darArchivo(file);    		
    		assertEquals( "El archivo debe estar vacio con 102 lineas fisicas", 102, archivoJava.darNumeroLineasFisicas());
    		assertEquals( "El archivo debe estar vacio con 32 lineas logicas", 32, archivoJava.darNumeroLineasLogicas());
    	}
    	catch(Exception ex) { 		
    		fail("Se genero excepcion "+ ex.getMessage());
    	}
    }
    
    /**
     * Cuenta las lineas del archivo con diferentes concatenaciones de doble linea.
     * 
     */
    public void testArchivoConcatenacionesValidoCuentaLineas() {
    	try {
    		String archivo = rutaArchivo + "9 Archivo_simple_Valido_concatenaciones.java";
    		archivoJava = new ArchivoJava(archivo, "9 Archivo_simple_Valido_concatenaciones.java");
    		File file = new File(archivo);
    		archivoJava.darArchivo(file);    		
    		assertEquals( "El archivo debe estar vacio con 31 lineas fisicas", 31, archivoJava.darNumeroLineasFisicas());
    		assertEquals( "El archivo debe estar vacio con 13 lineas logicas", 13, archivoJava.darNumeroLineasLogicas());
    	}
    	catch(Exception ex) { 		
    		fail("Se genero excepcion "+ ex.getMessage());
    	}
    }
    
    /**
     * Cuenta las lineas del archivo de interface
     * 
     */
    public void testArchivoInterfaceValidoCuentaLineas() {
    	try {
    		String archivo = rutaArchivo + "10 Archivo_simple_Valido_interfaz.java";
    		archivoJava = new ArchivoJava(archivo, "10 Archivo_simple_Valido_interfaz.java");
    		File file = new File(archivo);
    		archivoJava.darArchivo(file);    		
    		assertEquals( "El archivo debe estar vacio con 17 lineas fisicas", 17, archivoJava.darNumeroLineasFisicas());
    		assertEquals( "El archivo debe estar vacio con 3 lineas logicas", 3, archivoJava.darNumeroLineasLogicas());
    	}
    	catch(Exception ex) { 		
    		fail("Se genero excepcion "+ ex.getMessage());
    	}
    }
    
    /**
     * Cuenta las lineas del archivo de enum
     * 
     */
    public void testArchivoEnumeradorValidoCuentaLineas() {
    	try {
    		String archivo = rutaArchivo + "11 Archivo_simple_Valido_enumerador.java";
    		archivoJava = new ArchivoJava(archivo, "11 Archivo_simple_Valido_enumerador.java");
    		File file = new File(archivo);
    		archivoJava.darArchivo(file);    		
    		assertEquals( "El archivo debe estar vacio con 12 lineas fisicas", 12, archivoJava.darNumeroLineasFisicas());
    		assertEquals( "El archivo debe estar vacio con 5 lineas logicas", 5, archivoJava.darNumeroLineasLogicas());
    	}
    	catch(Exception ex) { 		
    		fail("Se genero excepcion "+ ex.getMessage());
    	}
    }
    
    
}
