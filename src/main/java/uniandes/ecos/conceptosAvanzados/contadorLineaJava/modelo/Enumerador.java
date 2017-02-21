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
		super(TipoEstructura.Enum, nombre);		
	}
}
