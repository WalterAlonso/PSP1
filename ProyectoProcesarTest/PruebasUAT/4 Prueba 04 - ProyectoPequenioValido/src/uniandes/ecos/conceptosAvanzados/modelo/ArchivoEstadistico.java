package uniandes.ecos.conceptosAvanzados.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * 
 * Clase que se encargara de validar y retornar los datos del archivo
 * @author Walter Alonso
 * 
 */
public class ArchivoEstadistico {
	
	/**
	 * Ruta del archivo a procesar
	 */
	private String rutaArchivo;
	
	/**
	 * Constructor que cargara el archivo.
	 * @param rutaArchivo
	 */
	public ArchivoEstadistico(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}
	
	/**
	 * Toma el archivo, lo valida y devuelve el conjunto de datos en el
	 * 
	 * @return Dato utilizando lista enlazada
	 * @throws Exception 
	 */
	public Dato darDatosArchivo() throws Exception {
		File archivo = new File(this.rutaArchivo);
		Dato dato = null;
		if(elArchivoExiste(archivo)) {
			if(elArchivoTieneElFormatoCorrecto(archivo)) {
				if(!elArchivoEstaVacio(archivo)) {
					FileReader stream = new FileReader(archivo);
					BufferedReader reader = new BufferedReader( stream);					
					try {
						dato = asignarDatoSiguiente(reader);						
					}
					catch(Exception ex) {
						throw new Exception("El archivo no tiene el formato esperado con solo numeros reales con salto de linea");
					}
					
					stream.close();
					reader.close();	
				}
				else {
					throw new Exception("El archivo esta vacio");
				}
			}
			else {
				throw new Exception("El archivo debe ser en formato txt");
			}
		}
		else {
			throw new Exception("No hay archivo que cargar");			
		}		
		
		return dato;
	}
	
	/**
	 * Asigna el dato siguiente por medio de recursividad
	 * @param reader : el reader de los datos
	 * @return el dato que sera la raiz
	 * @throws Exception
	 */
	private Dato asignarDatoSiguiente(BufferedReader reader) throws Exception {
		String linea = reader.readLine();
		Dato dato = null;
		if(linea != null) {
			dato = new Dato();
			dato.asignarValor(darValorReal(linea));
			dato.asignarDatoSiguiente(this.asignarDatoSiguiente(reader));
		}
		
		return dato;
	}
	
	/**
	 * valida que el archivo exista
	 * @return true si el archivo existe o false si no existe
	 */
	private boolean elArchivoExiste(File archivo) {		
		if(archivo.exists()) {
			return true;
		}		
		
		return false;			
	}
	
	/**
	 * Valida que el archivo se pueda leer y que sea un archivo plano txt
	 * @param archivo: el archivo que se va a cargar
	 * @return true si es valido o false si no es valido.
	 */
	private boolean elArchivoTieneElFormatoCorrecto(File archivo) {
		String nombreArchivo = archivo.getName();
		if(archivo.canRead()) {
			if(nombreArchivo.toLowerCase().endsWith(".txt")) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Valida si el archivo tiene datos
	 * @return true si tiene datos, false si no tiene datos.
	 * @throws Exception 
	 */
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
	
	/**
	 * Valida si el archivo tiene el formato correcto
	 * @return true si el dato es un numero real con el formato apropiado o false si no.
	 */
	private double darValorReal(String dato) throws Exception {
		return Double.parseDouble(dato);	
	}

}
