package uniandes.ecos.conceptosAvanzados.contadorLineaJava.modelo;

/**
 * Representa a una función.
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
