package uniandes.ecos.conceptosAvanzados.modelo;

/**
 * Interface que declara el metodo y proiedades que debe tener todo 
 * metodo estadistico
 * @author Walter Alonso
 *
 */
public interface ICalcular {
			
	/**
	 * Devuelve el calculo respectivo del metodo
	 * @return el numero real del calculo realizado
	 * @throws Exception 
	 */
	double darCalculo() throws Exception;		
}