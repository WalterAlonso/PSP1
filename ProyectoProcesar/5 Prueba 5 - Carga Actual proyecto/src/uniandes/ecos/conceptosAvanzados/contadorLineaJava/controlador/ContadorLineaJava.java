/**
 * Prop�sito:     Permite cargar un proyecto java para contar sus lineas e codigo
 * 				  segun un estandar de codificaci�n.
 * Autor(s):      Walter Alonso
 * Fecha creaci�n: 19/02/2017
 * Modificado por:  
 * �ltima modificaci�n: 
*/

package uniandes.ecos.conceptosAvanzados.contadorLineaJava.controlador;

import uniandes.ecos.conceptosAvanzados.contadorLineaJava.modelo.ProyectoJava;
import uniandes.ecos.conceptosAvanzados.contadorLineaJava.vista.ContadorVista;

/**
 * Inicia el programa Contador de linea Java.
 * @author Walter Alonso.
 *
 */
public class ContadorLineaJava {

	public static void main(String[] args) {
		ContadorVista vista = new ContadorVista();
		try	{		
			String ruta = "./ProyectoProcesar/";
			ProyectoJava proyectoJava = new ProyectoJava(ruta);
			proyectoJava.cargarProyectoJava();
			
			vista.asignarProyectoJava(proyectoJava);			
			vista.mostrarTamanioPrograma();
			vista.mostrarTamanioPartes();
		}
		catch(Exception ex) {				
			vista.mostrarError(ex.getMessage());
		}
	}
}
