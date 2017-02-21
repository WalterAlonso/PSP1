/**
 * Propósito:     Representa a los enum
 * Autor(s):      Walter Alonso
 * Fecha creación: 19/02/2017
 * Modificado por:  
 * Última modificación: 
*/

package uniandes.ecos.conceptosAvanzados.contadorLineaJava.modelo;

/**
 * Representa a un enumerador.
 * @author Walter Alonso.
 *
 */
public class Enumerador extends TipoArchivo {

	/**
	 * Ctr de enumerador
	 * @param nombre
	 */
	public Enumerador(String nombre) {
		super(TipoEstructura.Interfaz, nombre);		
	}
}
