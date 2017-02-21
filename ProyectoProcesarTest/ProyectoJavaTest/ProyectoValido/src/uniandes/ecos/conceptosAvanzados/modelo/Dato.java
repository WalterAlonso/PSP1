package uniandes.ecos.conceptosAvanzados.modelo;

/**
 * Clase que contendra los datos a ser analizados
 * @author Walter Alonso
 *
 */
public class Dato {
	
	/**
	 * Valor de dato
	 */
	private double valor;
	
	/**
	 * Dato siguiente
	 */
	private Dato datoSiguiente;
	
	/**
	 * retorna el valor
	 * @return el valor
	 */
	public double darValor() {
		return valor;
	}
	
	/**
	 * Retorna valor siguiente
	 * @return el valor siguiente
	 */
	public Dato darDatoSiguiente() {
		return datoSiguiente;
	}
	
	/**
	 * Asigna el valor del dato
	 * @param valor : numero real a asignar
	 */
	public void asignarValor(double valor) {
		this.valor = valor; 
	}
	
	/**
	 * Asigna el dato siguiente (por medio de auntador) 
	 * @param datoSiguiente : el dato siguiente.
	 */
	public void asignarDatoSiguiente(Dato datoSiguiente) {
		this.datoSiguiente = datoSiguiente;
	}
}