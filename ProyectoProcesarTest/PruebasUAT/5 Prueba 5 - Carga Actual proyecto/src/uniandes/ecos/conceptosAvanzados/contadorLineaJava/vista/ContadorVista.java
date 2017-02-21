/**
 * Propósito:     Representa la vista, la cual desplegara
 * 				  el tamaño total del programa, el tamaño total de cada parte
 * 				  y el numero de items en cada parte.
 * Autor(s):      Walter Alonso
 * Fecha creación: 19/02/2017
 * Modificado por:  
 * Última modificación: 
*/

package uniandes.ecos.conceptosAvanzados.contadorLineaJava.vista;

import java.util.ArrayList;

import uniandes.ecos.conceptosAvanzados.contadorLineaJava.modelo.Clase;
import uniandes.ecos.conceptosAvanzados.contadorLineaJava.modelo.Enumerador;
import uniandes.ecos.conceptosAvanzados.contadorLineaJava.modelo.Interfaz;
import uniandes.ecos.conceptosAvanzados.contadorLineaJava.modelo.Paquete;
import uniandes.ecos.conceptosAvanzados.contadorLineaJava.modelo.ProyectoJava;

/**
 * Se encargara de mostrar la informacion de LOC encontrado en el proyecto.
 * @author Walter Alonso.
 *
 */
public class ContadorVista {
	
	/**
	 * proyecto java
	 */
	private ProyectoJava proyectoJava;
	
	/**
	 * Ctor contador vista
	 */
	public ContadorVista()	{		
	}
		
	/**
	 * Asigna el proyecto
	 * @param 
	 */
	public void asignarProyectoJava(ProyectoJava proyectoJava) {
		this.proyectoJava = proyectoJava;
	}
		
	/**
	 * Muestra el tamanio del programa
	 */
	public void mostrarTamanioPrograma() {
		System.out.println();
		System.out.printf("Total tamaño programa = %d", this.proyectoJava.darCantidadLineasLogicas());		
		System.out.println();
	}
	
	/**
	 * Muestra Tamaño de la parte e items 
	 */
	public void mostrarTamanioPartes() {
		ArrayList<Paquete> paquetes = this.proyectoJava.darPaquetes();
		for (int indice=0; indice < paquetes.size(); indice ++) {
			Paquete paquete = paquetes.get(indice);
			System.out.println();
			System.out.println();
			System.out.print("------------------ 1. Paquete ------------------- ");
			System.out.println();
			System.out.printf(" Nombre = %s", paquete.darNombre());
			System.out.println();
			System.out.printf(" Tamaño = %s", paquete.darCantidadLineasLogicas());
			System.out.println();
			System.out.print("	  ------------- 2. Partes ---------------");
			mostrarTamanioClases(paquete);
			mostrarTamanioInterfaces(paquete);
			mostrarTamanioEnumeradores(paquete);
		}
	}
	
	/**
	 * Muestra los tamaños de las clases
	 * @param paquete
	 */
	private void mostrarTamanioClases(Paquete paquete) {
		ArrayList<Clase> clases = paquete.darClases();
		for (int indiceClase = 0; indiceClase < clases.size(); indiceClase ++) {
			Clase clase = clases.get(indiceClase);
			mostrarTamanioCadaParte("Clase", clase.darNombre(), clase.darFunciones().size(), clase.darCantidadLineaLogica());
		}
	}
	
	/**
	 * Muestra los tamaños de las inetrfaces
	 * @param paquete
	 */
	private void mostrarTamanioInterfaces(Paquete paquete) {
		ArrayList<Interfaz> interfaces = paquete.darInterfaces();
		for (int indiceInterfaz = 0; indiceInterfaz < interfaces.size(); indiceInterfaz ++) {
			Interfaz interfaz = interfaces.get(indiceInterfaz);
			mostrarTamanioCadaParte("Interfaz", interfaz.darNombre(), interfaz.darFunciones().size(), interfaz.darCantidadLineaLogica());			
		}
	}
	
	/**
	 * Muestra los tamaños de los enumeradores
	 * @param paquete
	 */
	private void mostrarTamanioEnumeradores(Paquete paquete) {
		ArrayList<Enumerador> enumeradores = paquete.darEnumerador();
		for (int indiceEnumerador = 0; indiceEnumerador < enumeradores.size(); indiceEnumerador ++) {
			Enumerador enumerador = enumeradores.get(indiceEnumerador);
			mostrarTamanioCadaParte("Enumerador", enumerador.darNombre(), 0, enumerador.darCantidadLineaLogica());
		
		}
	}
	
	/**
	 * Muestra tamaño de cada parte
	 * @param tipo
	 * @param nombre
	 * @param cantidad
	 * @param tamanio
	 */
	private void mostrarTamanioCadaParte(String tipo, String nombre, int cantidad, int tamanio) {
		System.out.println();
		System.out.printf("		--- Tipo : %s ", tipo);
		System.out.println();		
		System.out.printf("		--- Nombre:  %s ", nombre);
		System.out.println();		
		System.out.printf("		--- Tamaño:  %d ", tamanio);
		System.out.println();
		System.out.printf(" 	  	--- Cantidad de Items: %d ", cantidad);
		System.out.println();
		System.out.println();
	}
	
	/**
	 * Metodo que muestra error
	 */
	public void mostrarError(String error) {
		System.out.println("Error = " + error);
	}
}
