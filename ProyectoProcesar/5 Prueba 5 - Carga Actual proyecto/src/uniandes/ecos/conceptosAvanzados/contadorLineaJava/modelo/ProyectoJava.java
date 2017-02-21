/**
 * Propósito:     Representa un proyecto Java
 * Autor(s):      Walter Alonso
 * Fecha creación: 19/02/2017
 * Modificado por:  
 * Última modificación: 
*/


package uniandes.ecos.conceptosAvanzados.contadorLineaJava.modelo;

import java.io.File;
import java.util.ArrayList;

/**
 * Representa a un proyecto java, encapulando paquetes, clases y demas en POO.
 * @author Walter Alonso.
 *
 */
public class ProyectoJava {

	private String ruta;		
	private Paquete noPaquete;
	private ArrayList<ArchivoJava> archivosJava;	
	private ArrayList<Paquete> paquetes;
	
	
	public ProyectoJava(String ruta) {
		this.ruta =ruta;
		this.paquetes = new ArrayList<Paquete>();
		
		this.noPaquete = new Paquete("No paquete");
		this.paquetes.add(this.noPaquete);		
	}
	
	/**
	 * Retorna los paquetes.
	 * @return
	 */
	public ArrayList<Paquete> darPaquetes() {
		return this.paquetes;
	}
	
	public void cargarProyectoJava() throws Exception {
		
		File archivo = new File(this.ruta);		
		if(!laRutaExiste(archivo)) {
			throw new Exception("No se encontro la ruta dada.");
		}
		
		archivosJava = obetenerArchivosJava(archivo);	
		if (archivosJava.size() == 0) {
			throw new Exception("No se encontro ningún archivo para procesar.");
		}
		//se envian a estructurar como todo un proyecto
		this.crearEstructuraProyecto();
	}
	
	/**
	 * retorna la cantidad de lineas logicas
	 * @return
	 */
	public int darCantidadLineasLogicas() {
		int suma = 0;
		for (int indice = 0; indice < paquetes.size(); indice ++) {
			suma += paquetes.get(indice).darCantidadLineasLogicas();
		}
		return suma;
	}
	
	/**
	 * retorna la cantidad de lineas fisicas
	 * @return
	 */
	public int darCantidadLineasFisicas() {
		int suma = 0;
		for (int indice = 0; indice < paquetes.size(); indice ++) {
			suma += paquetes.get(indice).darCantidadLineasFisicas();
		}
		return suma;
	}
	
	
	public ArrayList<ArchivoJava>  obetenerArchivosJava(File file) throws Exception {
		ArrayList<ArchivoJava> listaArchivos = new ArrayList<ArchivoJava>();
		File[] ficheros = file.listFiles();
		for (int indice=0;indice<ficheros.length;indice++){
			
			String nombre = ficheros[indice].getName();
			String ruta = this.ruta + "/" + nombre;
		    
			ArchivoJava nuevoArchivo  = null;
			if (ficheros[indice].isDirectory()) {
				ArrayList<ArchivoJava> listaArchivosJava = obetenerArchivosJava(ficheros[indice]);
				if(listaArchivosJava.size() > 0) {
					listaArchivos.addAll(listaArchivosJava);
				}
				continue;
			}
			if (!elArchivoEsJava(ficheros[indice]))
			{
				continue;				
			}
								
			nuevoArchivo = new ArchivoJava(ruta, nombre);			
			listaArchivos.add(nuevoArchivo.darArchivo(ficheros[indice]));
		}
		return listaArchivos;
	}
	
	/**
	 * Retorna el paquete para el archivo java
	 * @param archivoJavaProcesar
	 * @return
	 */
	private Paquete darPaqueteArchivoJava(ArchivoJava archivoJavaProcesar) {
		Paquete paqueteArchivo = archivoJavaProcesar.darPaquete();
		
		Paquete PaqueteEnProyectoJava = null;
		if (paqueteArchivo != null) {
			if (!existePaquete(paqueteArchivo)) {
				PaqueteEnProyectoJava = paqueteArchivo;
				paquetes.add(PaqueteEnProyectoJava);
			}
			else {
				//existe.. busquelo
				PaqueteEnProyectoJava = buscarPaquetePorNombre(paqueteArchivo.darNombre());								
			}				
		}
		else {
			//puede ser que el archivo no estaba asociado a ningun paquete, se creo uno por defecto para esto.
			PaqueteEnProyectoJava = this.noPaquete;
		}
		return PaqueteEnProyectoJava;
	}
	
	/**
	 * Agrega las funciones al tipo de archivo
	 * @param tipoArchivo
	 * @param archivoJavaProcesar
	 * @return
	 */
	private TipoArchivo asignarFuncionesTipoArchivo(ArchivoJava archivoJavaProcesar) {
		TipoArchivo tipoArchivo = archivoJavaProcesar.darTipoArchivo();
		// Puede ser que sea codigo vacio sin tipo de archivo
		if (tipoArchivo == null) {
			tipoArchivo = new Clase("No tipoArchivo " + archivoJavaProcesar.darNombre());
			((Clase)tipoArchivo).agergarFunciones(archivoJavaProcesar.darFunciones());
		}
		else {
			//si se sabe su tipo
			TipoEstructura tipoEstructura = tipoArchivo.darTipoEstructura();
			if (tipoEstructura == TipoEstructura.Clase) {
				((Clase)tipoArchivo).agergarFunciones(archivoJavaProcesar.darFunciones());
			}
			if (tipoEstructura == TipoEstructura.Interfaz) {
				((Interfaz)tipoArchivo).agergarFunciones(archivoJavaProcesar.darFunciones());
			}
		}
		
		return tipoArchivo;
	}
	
	
	private void crearEstructuraProyecto() {
		//Se deben obtener los paquetes
		for (int indice=0; indice < archivosJava.size(); indice ++) {			
			ArchivoJava archivoJavaProcesar = archivosJava.get(indice);			
			TipoArchivo tipoArchivo = asignarFuncionesTipoArchivo(archivoJavaProcesar);
			Paquete paqueteArchivoJava = darPaqueteArchivoJava(archivoJavaProcesar);	
			
			tipoArchivo.asignarCantidadLineaFisica(archivoJavaProcesar.darNumeroLineasFisicas());
			tipoArchivo.asignarCantidadLineaLogica(archivoJavaProcesar.darNumeroLineasLogicas());
			paqueteArchivoJava.agregarTipoArchivo(tipoArchivo);			
		}
		
		//se valida si el paquete "no paquete" tiene partes asociadas, sino se procede a eliminar.
		if (this.noPaquete.darClases().size() == 0)		{
			paquetes.remove(this.noPaquete);
		}
	}
	
	private Paquete buscarPaquetePorNombre(String nombre) {
		for (int indice=0; indice < paquetes.size(); indice ++) {	
			Paquete paqueteBuscado = paquetes.get(indice);
			if  (nombre.equals(paqueteBuscado.darNombre())) {
				return paqueteBuscado;
			}
		}
		return null;
	}
	
	/**
	 * Valida si el paquete existe
	 * @param paquete
	 * @return
	 */
	private boolean existePaquete(Paquete paquete) {
		for (int indice=0; indice < paquetes.size(); indice ++) {		
			if  (paquete.darNombre().equals(paquetes.get(indice).darNombre())) {
				return true;
			}
		}
		return false;
	}
	
	private boolean laRutaExiste(File archivo) {		
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
	private boolean elArchivoEsJava(File archivo) {
		String nombreArchivo = archivo.getName();
		if(archivo.canRead()) {
			if(nombreArchivo.toLowerCase().endsWith(".java")) {
				return true;
			}
		}
		
		return false;
	}
}
