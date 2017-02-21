package uniandes.ecos.conceptosAvanzados.contadorLineaJava.modelo;

import java.util.ArrayList;

/**
 * Representa a una clase de POO.
 * @author Walter Alonso.
 *
 */
public class Clase extends TipoArchivo{

	/**
	 * Funciones
	 */
	private ArrayList<Funcion> funciones;
	
	/**
	 * Ctor de la clase
	 */
	public Clase(String nombre)	{
		super(TipoEstructura.Clase, nombre);
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
