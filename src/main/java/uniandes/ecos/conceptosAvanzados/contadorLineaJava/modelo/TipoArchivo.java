package uniandes.ecos.conceptosAvanzados.contadorLineaJava.modelo;

/**
 * Indica el tipo de archivo de ArchivoJava, sea Clase, Interfaz o enumerador.
 * @author Walter Alonso.
 *
 */
public class TipoArchivo {
		
	/**
	 * Indica el tipo de archivo
	 */
	private TipoEstructura tipoEstructura;
	
	/**
	 * El nombre dado a la clase, interfaz o enumerador
	 */
	private String nombre;
	
	private int cantidadLineaLogica;
	
	private int cantidadLineaFisica;
		
	/**
	 * Ctor TipoArchivo
	 * @param tipoEstructura
	 * @param nombre
	 */
	public TipoArchivo(TipoEstructura tipoEstructura, String nombre) {
		this.tipoEstructura = tipoEstructura;
		this.nombre = nombre;
	}
	
	/**
	 * Retorna el tipo de estructura
	 * @return
	 */
	public TipoEstructura darTipoEstructura() {
		return this.tipoEstructura;
	}
	
	/**
	 * Retorna el nombre
	 * @return
	 */
	public String darNombre() {
		return this.nombre;
	}
	
	/**
	 * Asigna la cantidad de lineas logicas
	 * @param cantidadLineaLogica
	 */
	public void asignarCantidadLineaLogica(int cantidadLineaLogica) {
		this.cantidadLineaLogica = cantidadLineaLogica;
	}
	
	/**
	 * Da la cantidad de lineas logicas
	 * @return
	 */
	public int darCantidadLineaLogica() {
		return this.cantidadLineaLogica;
	}
	
	/**
	 * Asigna la cantidad de lineas fisicas
	 * @param cantidadLineaFisica
	 */
	public void asignarCantidadLineaFisica(int cantidadLineaFisica) {
		this.cantidadLineaFisica = cantidadLineaFisica;
	}
	
	/**
	 * Da la cantidad de lineas fisicas
	 * @return
	 */
	public int darCantidadLineaFisica() {
		return this.cantidadLineaFisica;
	}
	
}
