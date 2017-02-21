package uniandes.ecos.conceptosAvanzados.contadorLineaJava.modelo;

/**
 * Representa a una funcion.
 * @author Walter Alonso.
 *
 */
public class Funcion {
	
	private String nombre;
			
	public Funcion(String nombre){
		this.nombre = nombre;
	}
		
	public String darNombre()	{
		return this.nombre;
	}
}
