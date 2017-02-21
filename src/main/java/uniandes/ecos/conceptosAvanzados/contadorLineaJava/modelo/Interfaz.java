package uniandes.ecos.conceptosAvanzados.contadorLineaJava.modelo;

import java.util.ArrayList;

/**
 * Representa a una interfaz de POO.
 * @author Walter Alonso.
 *
 */
public class Interfaz extends TipoArchivo {

	/**
	 * Funciones
	 */
	private ArrayList<Funcion> funciones;
	
	/**
	 * Ctor de la interfaz
	 */
	public Interfaz(String nombre)	{
		super(TipoEstructura.Interfaz, nombre);
		this.funciones = new ArrayList<Funcion>();
	}
	
	/**
	 * Agrega la funcion
	 * @param funcion
	 */
	public void agergarFuncion(Funcion funcion) {
		this.funciones.add(funcion);
	}
	
	/**
	 * Agrega funciones
	 * @param funciones
	 */
	public void agergarFunciones(ArrayList<Funcion> funciones) {
		this.funciones.addAll(funciones);
	}
	
	/**
	 * Da funciones
	 */
	public ArrayList<Funcion> darFunciones() {
		return this.funciones;
	}
	
}
