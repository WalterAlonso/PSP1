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
		System.out.printf("Total tamanio programa = %d", this.proyectoJava.darCantidadLineasLogicas());		
		System.out.println();
	}
	
	/**
	 * Muestra Tamanio de la parte e items 
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
			System.out.printf(" Tamanio = %s", paquete.darCantidadLineasLogicas());
			System.out.println();
			System.out.print("	  ------------- 2. Partes ---------------");
			mostrarTamanioClases(paquete);
			mostrarTamanioInterfaces(paquete);
			mostrarTamanioEnumeradores(paquete);
		}
	}
	
	/**
	 * Muestra los tamanios de las clases
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
	 * Muestra los tamanios de las interfaces
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
	 * Muestra los tamanios de los enumeradores
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
	 * Muestra tamanio de cada parte
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
		System.out.printf("		--- Tamanio:  %d ", tamanio);
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
