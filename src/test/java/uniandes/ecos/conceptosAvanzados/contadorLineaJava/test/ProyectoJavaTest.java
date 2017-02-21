package uniandes.ecos.conceptosAvanzados.contadorLineaJava.test;

import java.util.ArrayList;

import uniandes.ecos.conceptosAvanzados.contadorLineaJava.modelo.Clase;
import uniandes.ecos.conceptosAvanzados.contadorLineaJava.modelo.Paquete;
import uniandes.ecos.conceptosAvanzados.contadorLineaJava.modelo.ProyectoJava;
import junit.framework.TestCase;

/**
 * Test a proyecto Java.
 * @author Walter Alonso.
 *
 */
public class ProyectoJavaTest extends TestCase {
	
	/**
	 * Clase ArchivoEstadistico a testear
	 */
	private ProyectoJava proyectoJava;
	
	/**
	 * Constante donde estan los archivos a testear
	 */
	public static final String rutaArchivo = "./ProyectoProcesarTest/ProyectoJavaTest/";
	
	/**
     * Si el proyecto es vacio retorna 0 en lineas fisicas y logicas
     * 
     */
    public void testProyectoVacio() {
    	try {
    		String archivo = rutaArchivo + "ProyectoVacio";
    		proyectoJava = new ProyectoJava(archivo);
    		proyectoJava.cargarProyectoJava();
    		   		
    		assertEquals( "El protecto debe estar vacio con 0 lineas fisicas", 0, proyectoJava.darCantidadLineasFisicas());
    		assertEquals( "El protecto debe estar vacio con 0 lineas logicas", 0, proyectoJava.darCantidadLineasLogicas());    		
    		fail("Se esperaba excepcion de que no hay archivos que procesar.  ");    		
    	}
    	catch(Exception ex) { 		
    		assertEquals( "El mensaje debe coincidir: No se encontro ningun archivo para procesar.", "No se encontro ningun archivo para procesar.", ex.getMessage());
    	}
    }
    
    /**
     * Si el proyecto es vacio retorna 0 en lineas fisicas y logicas
     * 
     */
    public void testProyectoUnArchivo() {
    	try {
    		String archivo = rutaArchivo + "proyectoUnArchivo";
    		proyectoJava = new ProyectoJava(archivo);
    		proyectoJava.cargarProyectoJava();
    		   		
    		assertEquals( "El proyecto debe tener 102 lineas fisicas", 102, proyectoJava.darCantidadLineasFisicas());
    		assertEquals( "El proyecto debe tener 32 lineas logicas", 32, proyectoJava.darCantidadLineasLogicas());
    		assertEquals( "La cantidad de items es 2 ",
    				2, proyectoJava.darPaquetes().get(0).darClases().get(0).darFunciones().size());    		   
    		assertEquals( "El protecto tiene solo 1 paquete", 1, proyectoJava.darPaquetes().size());    		
    		assertEquals( "El paquete del proyecto es uniandes.ecos.conceptosAvanzados.contadorLineaJava.modelo","uniandes.ecos.conceptosAvanzados.contadorLineaJava.modelo",proyectoJava.darPaquetes().get(0).darNombre());
    		
    		
    	}
    	catch(Exception ex) { 		
    		fail("Se genero excepcion "+ ex.getMessage());
    	}
    }
    
    
    /**
     * Se valida: recursividad de recorrido en directorios y el corrceto conteo
     * 
     */
    public void testProyectoRecursivo() {
    	try {
    		String archivo = rutaArchivo + "ProyectoValido";
    		proyectoJava = new ProyectoJava(archivo);
    		proyectoJava.cargarProyectoJava();
    		   		
    		assertEquals( "El proyecto debe estar vacio con 207 lineas fisicas", 207, proyectoJava.darCantidadLineasFisicas());
    		assertEquals( "El proyecto debe estar vacio con 60 lineas logicas", 60, proyectoJava.darCantidadLineasLogicas());
    		assertEquals( "El proyecto debe tener 3 paquetes", 3, proyectoJava.darPaquetes().size());
    		boolean encontradoControlador = false;
    		boolean encontradoModelo = false;    
    		boolean encontradoVacio = false;
    		ArrayList<Paquete> paquetes = proyectoJava.darPaquetes();
    		
    		for (int indice = 0; indice < paquetes.size(); indice ++) {
    			Paquete paquete = paquetes.get(indice);
    			if (paquete.darNombre().equals("uniandes.ecos.conceptosAvanzados.modelo")) {
    				assertEquals( "El paquete modelo debe tener 108 lineas fisicas", 108, paquete.darCantidadLineasFisicas());
    				assertEquals( "El paquete modelo debe tener 29 lineas logicas", 29, paquete.darCantidadLineasLogicas());
    				assertEquals( "El paquete modelo debe tener 29 lineas logicas", 29, paquete.darCantidadLineasLogicas());
    				encontradoModelo = true;
    				
    				ArrayList<Clase> clases = paquete.darClases();
    				for (int indiceClase = 0; indiceClase < clases.size(); indiceClase ++) {
    					Clase clase = clases.get(indiceClase);
    					if (clases.get(indiceClase).darNombre() == "DatoDos") { //4
    						assertEquals( "El numero de funciones es: 4 ", 4, clase.darFunciones().size());
    					}    					    						
    					if (clases.get(indiceClase).darNombre() == "Dato") { //4
    						assertEquals( "El numero de funciones es: 4 ", 4, clase.darFunciones().size());
    	    			}
    					if (clases.get(indiceClase).darNombre() == "Media") { //4
    						assertEquals( "El numero de funciones es: 1 ", 1, clase.darFunciones().size());
    	    			}
    				}    				
    			}
    			if (paquete.darNombre().equals("uniandes.ecos.conceptosAvanzados.controlador")) {
    				assertEquals( "El paquete controlador debe tener 49 lineas fisicas", 49, paquete.darCantidadLineasFisicas());
    				assertEquals( "El paquete controlador debe tener 20 lineas logicas", 20, paquete.darCantidadLineasLogicas());    				
    				Clase clase = paquete.darClases().get(0);    
    				assertEquals( "El nombre es: EstadisticaControlador ", "EstadisticaControlador", clase.darNombre());
    				assertEquals( "El numero de funciones es: 1 ", 1, clase.darFunciones().size());
    				
    				encontradoControlador = true;
    			}
    			if (paquete.darNombre().equals("No paquete")) {
    				assertEquals( "El No paquete debe tener 50 lineas fisicas", 50, paquete.darCantidadLineasFisicas());
    				assertEquals( "El No paquete debe tener 11 lineas logicas", 11, paquete.darCantidadLineasLogicas());
    				encontradoVacio = true;
    			}
    		}
    		
    		assertTrue("debe existir el paquete controlador", encontradoControlador);
    		assertTrue("debe existir el paquete modelo", encontradoModelo);    		
    		assertTrue("debe existir el no paquete", encontradoVacio);   
    		
    	}
    	catch(Exception ex) { 		
    		fail("Se genero excepcion "+ ex.getMessage());
    	}
    }
	
}
