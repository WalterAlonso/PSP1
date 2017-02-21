/**
 * Propósito:     			Calcular la media y desviación estandar de un
 * 				 			conjunto de datos.
 * Autor(s):   	 			Walter Javier Alonso Roa.
 * Fecha creación:	 		Febrero 4 de 2017.
 * Modificado por: 			Walter Javier Alonso Roa. 
 * Última modificación:  	Fecbrero 18 de 2017.
*/

package uniandes.ecos.conceptosAvanzados.controlador;

import uniandes.ecos.conceptosAvanzados.modelo.*;
import uniandes.ecos.conceptosAvanzados.vista.EstadisticaVista;
/**
 * 
 * Clase principal del programa. (controlador)
 * @author Walter Alonso
 * 
 */
public class EstadisticaControlador {
	
	/**
	 * Metodo principal, la peticion inicial del usuario 
	 */
	public static void main(String[] args) {	
		EstadisticaVista vista = new EstadisticaVista();
		try
		{		
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
