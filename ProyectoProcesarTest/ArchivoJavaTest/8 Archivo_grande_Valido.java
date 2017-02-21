package uniandes.ecos.conceptosAvanzados.contadorLineaJava.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Representa un archivo Java, se encargara de las reglas para formar este archivo
 * de acuerdo a un archivo dado.
 * @author Walter Alonso.
 *
 */
public class ArchivoJava {

	/**
	 * Indica si en este momento hay una Funcion instanciada.
	 */
	private Funcion funcionActual;
	
	/**
	 * Indica si hay un paquete en el archivo.
	 */
	private Paquete paquete;
	
	/**
	 * Indica si el recorrido que se esta haciendo es sobre un comentario.
	 */	
	private boolean lineaComentarioAbierta; 
	
	/**
	 * Indica si el recorrido se esta haciendo sobre el contenido de un cuerpo de metodo o clase.
	 */	
	private boolean instruccionLogicaAbierta; 
	
	/**
	 * Indica si se esta haciendo el recorrido sobre una instruccion logica.
	 */
	private boolean instruccionCondicionalAbierta; 
	
	/**
	 * Indica la cantidad de lineas logicas recogidas hasta el momento.
	 */
	private int cantidadLineaLogica; 
	
	/**
	 * se almacenan los errores que se van encontrando.
	 */
	private String error; 
	
	/**
	 * Nombre del archivo.
	 */
	private String nombre;
	
	/**
	 * Indica el numero de la linea fisica en la cual se encuentra.
	 */
	private int numeroLineaFisica;
	
	/**
	 * Indica el tipo de archivo (sea clase, enum o interfaz).
	 */
	private TipoArchivo tipoArchivo; 
	
	/**
	 * Indica la ubicacion física del archivo.
	 */
	private String ubicacionFisica; 
	
	private ArrayList<Funcion> funciones;
	
	/**
	 * Constructor de la clase
	 * @param ubicacionFisica
	 */
	public ArchivoJava(String ubicacionFisica, String nombreArhivo)	{
		this.ubicacionFisica = ubicacionFisica;
		this.nombre = nombreArhivo;
		this.cantidadLineaLogica = 0;
		this.numeroLineaFisica = 0;
	}
	
	/**
	 * Retorna este  archivo consutruido 
	 * @return este archivo java
	 */
	public ArchivoJava darArchivo(File archivo) throws Exception {
		if(!elArchivoEstaVacio(archivo))
		{	
			FileReader stream = new FileReader(archivo);
			BufferedReader reader = new BufferedReader( stream);
			try {
				procesarInstruccion(reader);
			}
			catch(Exception ex) {
				this.adicionarError(ex.getMessage());
			}			
		}		
		return this;
	}
}