package uniandes.ecos.conceptosAvanzados.contadorLineaJava.modelo;
import java.util.ArrayList;

/**
 * Representa a un paquete de POO.
 * @author Walter Alonso.
 *
 */
public class Paquete {

	/**
	 * el nombre del paquete
	 */
	private String nombre;
	
	/**
	 * Lista de interfaces
	 */
	private ArrayList<Interfaz> interfaces;
	
	/**
	 * Losta de clases
	 */
	private ArrayList<Clase> clases;
	
	/**
	 * propiedad de enumerador
	 */
	private ArrayList<Enumerador> enumeradores;
		
	/**
	 * Constructor del paquete
	 * @param nombre
	 */
	public Paquete (String nombre) {
		this.nombre = nombre;
		this.interfaces = new ArrayList<Interfaz>();
		this.clases = new ArrayList<Clase>();
		this.enumeradores = new ArrayList<Enumerador>();
	}
	
	/**
	 * Retorna el nombre
	 * @return
	 */
	public String darNombre()	{
		return this.nombre;
	}
	
	/**
	 * Agrega el tipo de archivo sea clase, enumerador o interfaz
	 * @param tipoArchivo
	 */
	public void agregarTipoArchivo(TipoArchivo tipoArchivo) {
		if (TipoEstructura.Clase == tipoArchivo.darTipoEstructura()) {
			this.agregarClase((Clase)tipoArchivo);
		}
		else if (TipoEstructura.Interfaz == tipoArchivo.darTipoEstructura()) {
			this.agregarInterfaz((Interfaz)tipoArchivo);
		}
		else if (TipoEstructura.Enum == tipoArchivo.darTipoEstructura()) {
			this.agregarEnumerador((Enumerador)tipoArchivo);
		}		
		
	}
	
	/**
	 * Agrega una clase al paquete 
	 * @param clase
	 */
	private void agregarClase(Clase clase) {
		this.clases.add(clase);
	}
	
	/**
	 * Agrega una interfaz al paquete 
	 * @param clase
	 */
	private void agregarInterfaz(Interfaz interfaz) {
		this.interfaces.add(interfaz);
	}
	
	/**
	 * Agrega un enumerador al paquete 
	 * @param clase
	 */
	private void agregarEnumerador(Enumerador enumerador) {
		this.enumeradores.add(enumerador);
	}
	
	/**
	 * Retorna las clases que posee
	 * @return
	 */
	public ArrayList<Clase> darClases() {
		return this.clases;
	}
	
	/**
	 * Retorna las inetrfacez que posee
	 * @return
	 */
	public ArrayList<Interfaz> darInterfaces() {
		return this.interfaces;
	}
	
	/**
	 * Retorna los enumeradores que posee
	 * @return
	 */
	public ArrayList<Enumerador> darEnumerador() {
		return this.enumeradores;
	}
			
	/**
	 * retorna la cantidad de lineas fisicas
	 * @param cantidadLineasFisicas
	 * @return
	 */
	public int darCantidadLineasFisicas() {
		int suma = darCantidadLineasFisicasClases() +
				darCantidadLineasFisicasInterfaz() +
				darCantidadLineasFisicasEnumerador();
		return suma;
	}
	
	/**
	 * Retorna cantidad de lineas logicas
	 * @param cantidadLineasLogicas
	 * @return
	 */
	public int darCantidadLineasLogicas() {
		int suma = darCantidadLineasLogicasClases() +
				darCantidadLineasLogicasInterfaz() +
				darCantidadLineasLogicasEnumerador();
		return suma;
	}
	
	/**
	 * Retorna la catidad de lineas fisicas para clases
	 */
	private int darCantidadLineasFisicasClases() {
		int sumaLineas = 0;
		for (int indice = 0; indice < this.clases.size(); indice++) {
			sumaLineas += this.clases.get(indice).darCantidadLineaFisica();
		}
		return sumaLineas;
	}
	
	/**
	 * Retorna la cantidad de lineas logcas para clases
	 */
	private int darCantidadLineasLogicasClases() {
		int sumaLineas = 0;
		for (int indice = 0; indice < this.clases.size(); indice++) {
			sumaLineas += this.clases.get(indice).darCantidadLineaLogica();
		}
		return sumaLineas;
	}
	
	/**
	 * Retorna la cantidad de lineas fisicas de interfaz
	 */
	private int darCantidadLineasFisicasInterfaz() {
		int sumaLineas = 0;
		for (int indice = 0; indice < this.interfaces.size(); indice++) {
			sumaLineas += this.interfaces.get(indice).darCantidadLineaFisica();
		}
		return sumaLineas;
	}
	
	/**
	 * Retorna la cantidad de lineas logcas para interfaz
	 */
	private int darCantidadLineasLogicasInterfaz() {
		int sumaLineas = 0;
		for (int indice = 0; indice < this.interfaces.size(); indice++) {
			sumaLineas += this.interfaces.get(indice).darCantidadLineaLogica();
		}
		return sumaLineas;
	}
	
	/**
	 * Retorna la cantidad de lineas fisicas para enums
	 */
	private int darCantidadLineasFisicasEnumerador() {
		int sumaLineas = 0;
		for (int indice = 0; indice < this.enumeradores.size(); indice++) {
			sumaLineas += this.enumeradores.get(indice).darCantidadLineaFisica();
		}
		return sumaLineas;
	}
	
	/**
	 * Retorna la cantidad de lineas logicas para enums
	 */
	private int darCantidadLineasLogicasEnumerador() {
		int sumaLineas = 0;
		for (int indice = 0; indice < this.enumeradores.size(); indice++) {
			sumaLineas += this.enumeradores.get(indice).darCantidadLineaLogica();
		}
		return sumaLineas;
	}
		
	
}
