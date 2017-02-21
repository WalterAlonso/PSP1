package uniandes.ecos.conceptosAvanzados.vista;

/**
 * Clase que muestra los datos obtenidos
 * @author Walter Alonso
 *
 */
public class EstadisticaVista {

	/**
	 * El valor de la media
	 */
	private double media;
	
	/**
	 * El valor de la desviacion estandar
	 */
	private double desviacionEstandar;
	
	public EstadisticaVista()	{		
	}
		
	/**
	 * Asigna el valor de la media
	 * @param media valor de la media a mostrar
	 */
	public void asignarMedia(double media) {
		this.media = media;
	}
	
	/**
	 * Asigna el valor de la media
	 * @param media valor de la media a mostrar
	 */
	public void asignarDesviacionEstandar(double desviacionEstandar) {
		this.desviacionEstandar = desviacionEstandar;
	}
	
	
	/**
	 * Muestra la media
	 */
	public void mostrarMedia() {
		System.out.printf("Media = %.9f", media);		
		System.out.println("");
	}
	
	/**
	 * Muestra la desviacion estandar
	 */
	public void mostrarDesviacionEstandar() {
		System.out.printf("Desviacion estandar = %.9f", desviacionEstandar);		
	}

	/**
	 * Metodo que muestra error
	 */
	public void mostrarError(String error) {
		System.out.println("Error = " + error);
	}
}
