package uniandes.ecos.conceptosAvanzados.modelo;

/**
 * 
 * Clase que maneja la desviacon estandar
 * @author Walter Alonso
 *
 **/
public class DesviacionEstandar implements ICalcular {
	
	/**
	 * dato raiz
	 */
	private Dato datoRaiz;

	/**
	 * Media de los datos
	 */
	private Media media;
	
	/**
	 * Metodo contructor de la media
	 * @param dato, el dato raiz 
	 * @param media, clase que se encarga de calcular la media
	 */
	public DesviacionEstandar(Dato dato, Media media) {
		this.datoRaiz = dato;
		this.media = media;
	}
		
	/**
	 * Devuelve el calculo respectivo del metodo
	 * @return el numero real de la media del conjunto de datos
	 * @throws Exception 
	 */
	public double darCalculo() throws Exception {	
		double media = this.media.darCalculo();		
		double sumaValores = 0;
		int cantidadElementos = 0;
		Dato datoActual = this.datoRaiz;
		
		while(datoActual != null) {
			double factorDiferenciaConMedia = Math.pow(datoActual.darValor() - media, 2);
			sumaValores += factorDiferenciaConMedia;
			cantidadElementos +=1;
			datoActual = datoActual.darDatoSiguiente();			
		}
		
		if(cantidadElementos < 2) {
			throw new Exception("Debe haber mas de un numero real para calcular la desviacion estandar.");
		}
		
		double desviacionEstandar = Math.sqrt(sumaValores/(cantidadElementos - 1));		
		return desviacionEstandar;
	}
}

