package uniandes.ecos.conceptosAvanzados.modelo;

/**
 * Maneja los calculos de la media
 * @author Walter Alonso
 *
 */
public class Media implements ICalcular {
	
	/**
	 * dato raiz
	 */
	private Dato datoRaiz;

	/**
	 * Metodo contructor de la media
	 * @param dato
	 */
	public Media(Dato dato) {
		this.datoRaiz = dato;
	}
		
	/**
	 * Preondicion: el dato raiz tiene minimo un datoSiguiente. 
	 * Devuelve el calculo respectivo del metodo
	 * @return el numero real de la media del conjunto de datos
	 */
	public double darCalculo() {
		double sumaValores = this.datoRaiz.darValor();
		int cantidadElementos = 1;
		Dato datoSiguiente = this.datoRaiz.darDatoSiguiente();
		while(datoSiguiente != null) {			
			sumaValores += datoSiguiente.darValor();
			cantidadElementos +=1;
			datoSiguiente = datoSiguiente.darDatoSiguiente();
		}
		
		return sumaValores/cantidadElementos;
	}
}