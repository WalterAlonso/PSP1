package uniandes.ecos.conceptosAvanzados.contadorLineaJava.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Representa un archivo Java, se encargara de las reglas para formar este archivo
 * de acuerdo a un archivo dado.
 * @author Walter Alonso.
 *
 */
public class ArchivoJava {

	/**
	 * Indica si en este momento hay una Funcion instanciada.
	 */
	private Funcion funcionActual;
	
	/**
	 * Indica si hay un paquete en el archivo.
	 */
	private Paquete paquete;
	
	/**
	 * Indica si el recorrido que se esta haciendo es sobre un comentario.
	 */	
	private boolean lineaComentarioAbierta; 
	
	/**
	 * Indica si el recorrido se esta haciendo sobre el contenido de un cuerpo de metodo o clase.
	 */	
	private boolean instruccionLogicaAbierta; 
	
	/**
	 * Indica si se esta haciendo el recorrido sobre una instruccion logica.
	 */
	private boolean instruccionCondicionalAbierta; 
	
	/**
	 * Indica la cantidad de lineas logicas recogidas hasta el momento.
	 */
	private int cantidadLineaLogica; 
	
	/**
	 * se almacenan los errores que se van encontrando.
	 */
	private String error; 
	
	/**
	 * Nombre del archivo.
	 */
	private String nombre;
	
	/**
	 * Indica el numero de la linea fisica en la cual se encuentra.
	 */
	private int numeroLineaFisica;
	
	/**
	 * Indica el tipo de archivo (sea clase, enum o interfaz).
	 */
	private TipoArchivo tipoArchivo; 
	
	/**
	 * Indica la ubicacion física del archivo.
	 */
	private String ubicacionFisica; 
	
	/**
	 * la lista de funciones
	 */
	private ArrayList<Funcion> funciones;
	
	/**
	 * Constructor de la clase
	 * @param ubicacionFisica
	 */
	public ArchivoJava(String ubicacionFisica, String nombreArhivo)	{
		this.ubicacionFisica = ubicacionFisica;
		this.nombre = nombreArhivo;
		this.cantidadLineaLogica = 0;
		this.numeroLineaFisica = 0;
		this.funciones = new ArrayList<Funcion>();
	}
	
	/**
	 * Retorna este  archivo consutruido 
	 * @return este archivo java
	 */
	public ArchivoJava darArchivo(File archivo) throws Exception {
		if(!elArchivoEstaVacio(archivo)) {	
			FileReader stream = new FileReader(archivo);
			BufferedReader reader = new BufferedReader( stream);
			try {
				procesarInstruccion(reader);
			}
			catch(Exception ex) {
				this.adicionarError(ex.getMessage());
			}			
		}		
		return this;
	}
		
	/**
	 * Retorna el nombre del archivo.
	 * @return
	 */
	public String darNombre() {
		return this.nombre;
	}
	
	/**
	 * Retorna el numero de lineas fisicas.
	 * @return
	 */
	public int darNumeroLineasFisicas() {
		return this.numeroLineaFisica;
	}
	
	/**
	 * Retorna el numero de lineas logicas.
	 * @return
	 */
	public int darNumeroLineasLogicas() {
		return this.cantidadLineaLogica;
	}
	
	/**
	 * Valida si el archivo tiene datos
	 * @return true si tiene datos, false si no tiene datos.
	 * @throws Exception 
	 */
	private boolean elArchivoEstaVacio(File archivo) throws Exception {		
		FileReader stream = new FileReader(archivo);
		BufferedReader reader = new BufferedReader( stream);
		String line = reader.readLine();
		boolean esVacio = false;
		if(line == null) {
			esVacio = true;
		}
		
		reader.close();
		stream.close();
		
		return esVacio;		
	}
	
	/**
	 * Se encarga de procesar linea a linea e archivo recursivamente.
	 * @param reader : los datos del archivo
	 * @return
	 * @throws Exception
	 */
	private boolean procesarInstruccion(BufferedReader reader) throws Exception{
		String linea = reader.readLine();
		if(linea == null) {
			return true;
		}
		if(linea != null) {
			sumarLineaFisica();
			linea = removerEspaciosInstruccion(linea);
			if(!esInstruccionVacia(linea)) {
				if (this.hayComentariosAbiertos())	{
					if(this.esComentarioMultilineaFinalizado(linea)) {
						this.asignarFinComentario();						
					}
				}
				else	{
					if(this.instruccionComienzaPorCaracteresValidos(linea)) {
						if(this.hayInstruccionLogicaAbierta()) {
							this.sumarInstruccionLogica();
							
							if(this.laInstruccionEsFinalizacionLogica(linea)) {	
								this.asignarFinLogica();								
							}
						}
						else {
							if (!this.laInstruccionEsComentarioSencillo(linea)){							
								if (this.instruccionEsCierreCorchete(linea)) {									
									if (this.seEstaProcesandoTipoArchivo()) {
										// Es una funcion la que se esta recorriendo
										if (this.seEstaProcesandoFuncion()) {											
											funciones.add(new Funcion(this.funcionActual.darNombre()));
											this.funcionActual = null;
										}
										else	{
											if (this.hayInstruccionCondicionalAbierta()) {
												this.asignarFinCondicional();
											}	
										}
									}
									else {
										adicionarError("Se encuentra un cierre sin haber iniciado instanciado la clase,");
									}
								}
								else {
									if (this.esComentarioMultilinea(linea)) {
										if (!this.esComentarioMultilineaFinalizado(linea))	{
											this.asignarInicioComentario();											
										}
									} 
									else {
										// Es enumerador
										if (this.tipoArchivo != null && this.tipoArchivo.darTipoEstructura() == TipoEstructura.Enum) {
											this.sumarInstruccionLogica();											
										}
										else {
											if (this.instruccionContieneTextoSalida(linea)) {
												this.sumarInstruccionLogica();
												if (!this.laInstruccionEsFinalizacionLogica(linea)) {
													this.asignarInicioLogica();													
												}												
											}
											else {
												if (this.laInstruccionEsPaquete(linea)) {
													if (this.paquete != null) {
														adicionarError("Se encuentra mas de un package en este archivo,");														
													} else {
														this.sumarInstruccionLogica();
														String nombrePaquete = this.darNombrePaquete(linea);													
														this.paquete = new Paquete(nombrePaquete);
													}
												}
												else {													
													 if (!instanciaTipoArchivo(linea)) {													
															this.sumarInstruccionLogica();
															if (this.laInstruccionTieneCorcheteAbrirYParentesis(linea)) {
																if (this.laInstruccionEsFuncion(linea)) {
																	this.funcionActual = new Funcion("Funcion : " + funciones.size());
																}
																else {
																	//es un condicional
																	this.asignarInicioCondicional();
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
					}
					else {
						adicionarError("Comienza por caracteres invalidos, ");
					}
				}					
			}
			
			
			
		}
		return procesarInstruccion(reader);		
	}
	
	/**
	 * Instancia el tipo de archivo, sea inetrfaz, enumerador o clase
	 * @param instruccion
	 * @return
	 * @throws Exception
	 */
	private boolean instanciaTipoArchivo(String instruccion) throws Exception {
		boolean esClase = this.laInstruccionEsUnaClase(instruccion);
		boolean esInterfaz = this.laInstruccionEsUnaInterfaz(instruccion);
		boolean esEnumeador = this.laInstruccionEsUnEnumerador(instruccion);
		
		if (esClase || esInterfaz || esEnumeador) {
			if (this.tipoArchivo != null) {			
				throw new Exception ("Se encuentra mas de un tipo de archivo (clase, interfaz, enum).");
			}
			
			this.sumarInstruccionLogica();															
			if (esClase) { 
				this.instanciarTipoArchivo(TipoEstructura.Clase, darNombreClase(instruccion));				
			}
			
			if (esInterfaz) {															
				this.instanciarTipoArchivo(TipoEstructura.Interfaz, darNombreInterfaz(instruccion));
			}
			
			if (esEnumeador) {															
				this.instanciarTipoArchivo(TipoEstructura.Enum, darNombreEnumerador(instruccion));
			}
			return true;
		}
		return false;
	}
	
	
	/**
	 * Inica si la instruccion es de finalizacion
	 * @param instruccion
	 * @return
	 */
	private boolean laInstruccionEsFinalizacionLogica(String instruccion) {
		if(instruccion.endsWith(";")) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Remueve los espacios que pueda tener la cadena
	 * @param linea
	 * @return
	 */
	private String removerEspaciosInstruccion(String linea) {
		return linea.trim();
	}
	
	/**
	 * Indica si la instruccion es una linea vacia
	 * @param linea
	 * @return
	 */
	private boolean esInstruccionVacia(String linea) {
		return linea.isEmpty();
	}
	
	/**
	 * Suma una linea fisica
	 */
	private void sumarLineaFisica() {
		this.numeroLineaFisica += 1;
	}
	
	/**
	 * Indica si el recorrido de lectura que se esta haciendo es de un comentario.
	 * @return
	 */
	private boolean hayComentariosAbiertos() {
		return this.lineaComentarioAbierta;
	}
	
	/**
	 * Valida si el caracter de inicio de la instruccion es valido.
	 * @return true si es caracter valido de inicio
	 */
	private boolean instruccionComienzaPorCaracteresValidos(String instuccion) {
		char caracterInicioInstruccion = instuccion.charAt(0);			
		String caracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ*/+}";
		for (int indice = 0; indice < caracteres.length(); indice++) {
			char caracterActual = caracteres.charAt(indice);
			if (caracterInicioInstruccion == caracterActual) {
				return true;
			}
		}
		return false;
	}
		
	/**
	 * Indica si en el recorrido hay una instruccion logica abierta 
	 * @return true si hay instruccion logica abierta
	 */
	private boolean hayInstruccionLogicaAbierta() {
		return this.instruccionLogicaAbierta;
	}
	
	/**
	 * Indica si se esta haciendo recorrido sobre una condicion.  
	 * @return true si hay instruccion condicional abierta
	 */
	private boolean hayInstruccionCondicionalAbierta() {
		return this.instruccionCondicionalAbierta;
	}
	
	/**
	 * Valida si la instruccion es un comentario sencillo "//"
	 * @param instruccion
	 * @return true si es comentario sencillo.
	 */
	private boolean laInstruccionEsComentarioSencillo(String instruccion) {
		if (instruccion.startsWith("//")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Valida si la instruccion es de cierre.
	 * @return true si es de cierre.
	 */
	private boolean instruccionEsCierreCorchete(String instruccion) {
		if (instruccion.startsWith("}")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Indica si hay una clase, interfaz, enum (tipo archivo) recorriendose
	 * @return true si si lo hay.
	 */
	private boolean seEstaProcesandoTipoArchivo() {
		if (this.tipoArchivo != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Valida si se esta recorriendo una funcion.
	 * @return
	 */
	private boolean seEstaProcesandoFuncion() {
		return this.funcionActual != null;
	}
	
	/**
	 * Indica si el comentario es multilinea
	 * @param instruccion
	 * @return true si es comentario multilinea
	 */
	private boolean esComentarioMultilinea(String instruccion) {
		if (instruccion.startsWith("/*")) {
			return true;
		}		
		return false;			
	}
	
	/**
	 * Indica cierre de comentario multilinea
	 * @param instruccion
	 * @return true si termina en esa linea, false no.
	 */
	private boolean esComentarioMultilineaFinalizado(String instruccion) {		
		if (instruccion.endsWith("*/")) {
			return true;
		}		
		return false;			
	}
		
	/**
	 * Indica el inicio de un cometario.
	 */
	private void asignarInicioComentario() {
		this.lineaComentarioAbierta = true;
	}
	
	/**
	 * Indica el fin de un cometario.
	 */
	private void asignarFinComentario() {
		this.lineaComentarioAbierta = false;
	}
	
	/**
	 * Indica el inicio de un condicional.
	 */
	private void asignarInicioCondicional() {
		this.instruccionCondicionalAbierta = true;
	}
	
	/**
	 * Indica el fin de un condicional.
	 */
	private void asignarFinCondicional() {
		this.instruccionCondicionalAbierta = false;
	}
	
	/**
	 * Indica el inicio de logica.
	 */
	private void asignarInicioLogica() {
		this.instruccionLogicaAbierta = true;
	}
	
	/**
	 * Indica el fin de logica.
	 */
	private void asignarFinLogica() {
		this.instruccionLogicaAbierta = false;
	}
	
		
	/**
	 * Indica si la instruccion contiene texto de salida.
	 * @param instruccion
	 * @return true si contiene texto de salida
	 */
	private boolean instruccionContieneTextoSalida(String instruccion) {
		if (instruccion.contains("\"") ||  instruccion.contains("'")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Suma 1 a la actual cantidad de linea lógica.
	 */
	private void sumarInstruccionLogica() {
		this.cantidadLineaLogica += 1;
	}
	
	/**
	 * Indica si la instruccion es de paquete
	 * @param instruccion
	 * @return
	 */
	private boolean laInstruccionEsPaquete(String instruccion) {
		String palabraPaquete = "package";
		if (instruccion.startsWith(palabraPaquete) || instruccion.contains(" "+palabraPaquete+" ")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Retorna el nombre del paquete
	 * @param instruccion
	 * @return
	 */
	private String darNombrePaquete(String instruccion) {
		String palabraPaquete = "package";
		int posicion = instruccion.indexOf(" "+palabraPaquete+" ");				
		String nombrePaquete = "";
		if (posicion == -1)	{
			posicion = instruccion.indexOf(palabraPaquete);		
		}
		
		String nuevaInstruccion = instruccion.substring(7);			
		while (nuevaInstruccion.charAt(0) == ' ' && nuevaInstruccion.length() > 0) {
			nuevaInstruccion = nuevaInstruccion.substring(1);
		}
		nombrePaquete = nuevaInstruccion.substring(0,nuevaInstruccion.length()-1);
		return nombrePaquete;
	}
	
	/**
	 * Retorna el nombre de la clase
	 * @param instruccion
	 * @return
	 */
	private String darNombreClase(String instruccion) {
		String palabraTipoArchivo = "class";
		String nombre = darNombreTipoArchivo(instruccion, palabraTipoArchivo);	
		return nombre;
	}
	
	/**
	 * Retorna el nombre de la interfaz
	 * @param instruccion
	 * @return
	 */
	private String darNombreInterfaz(String instruccion) {
		String palabraTipoArchivo = "interface";
		String nombre = darNombreTipoArchivo(instruccion, palabraTipoArchivo);	
		return nombre;
	}
	
	/**
	 * Retorna el nombre de la interfaz
	 * @param instruccion
	 * @return
	 */
	private String darNombreEnumerador(String instruccion) {
		String palabraTipoArchivo = "enum";
		String nombre = darNombreTipoArchivo(instruccion, palabraTipoArchivo);	
		return nombre;
	}
		
	/**
	 * Retorna el nomre de la clase, interfaz o enumerador.
	 * @param instruccion
	 * @param tipoArchivo
	 * @return
	 */
	private String darNombreTipoArchivo(String instruccion, String tipoArchivo) {
		int posicion = instruccion.indexOf(" "+tipoArchivo+" ");				
		String nombre = "";
		if (posicion == -1)	{
			posicion = instruccion.indexOf(tipoArchivo);		
		}
		
		String nuevaInstruccion = instruccion.substring(posicion+tipoArchivo.length()+1);			
		while (nuevaInstruccion.charAt(0) == ' ' && nuevaInstruccion.length() > 0) {
			nuevaInstruccion = nuevaInstruccion.substring(1);
		}
		//ahora al revez		
		int indice = 0;
		String nombreNuevo = ""; 
		while ((nuevaInstruccion.charAt(indice) != ' ' &&				 
				nuevaInstruccion.charAt(indice) != '{') || indice == nuevaInstruccion.length()) {
			nombreNuevo +=  nuevaInstruccion.charAt(indice);
			indice ++;			
		}
				
		return nombreNuevo;
	}
	
	/**
	 * Indica si la instruccion es una clase
	 * @param instruccion
	 * @return
	 */
	private boolean laInstruccionEsUnaClase(String instruccion) {
		String palabraClase = "class";
		if (instruccion.startsWith(palabraClase) || instruccion.contains(" "+palabraClase+" ")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Indica si la instruccion es una interfaz
	 * @param instruccion
	 * @return
	 */
	private boolean laInstruccionEsUnaInterfaz(String instruccion) {
		String palabraInterface = "interface";
		if (instruccion.startsWith(palabraInterface) || instruccion.contains(" "+palabraInterface+" ")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Indica si la instruccion es un enumerador
	 * @param instruccion
	 * @return
	 */
	private boolean laInstruccionEsUnEnumerador(String instruccion) {
		String palabraEnum = "enum";
		if (instruccion.startsWith(palabraEnum) || instruccion.contains(" "+palabraEnum+" ")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Indica si la instruccion tiene corchete y parentesis para identifcar si es un metodo 
	 * o un condicional.
	 * @param instruccion
	 * @return
	 */
	private boolean laInstruccionTieneCorcheteAbrirYParentesis(String instruccion) {
		if (instruccion.contains("{") && instruccion.contains("(")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Indica si la instruccion es una funcion
	 * @param instruccion
	 * @return
	 */
	private boolean laInstruccionEsFuncion(String instruccion) {		
		if (instruccion.startsWith("public") || instruccion.startsWith("private") || instruccion.startsWith("protected")) {
			return true;
		}
		return false;
	}
	
	/*private boolean laInstruccionEsCondicional() {
		if ()
	}*/
	/**
	 * Adiciona un error a la lista;
	 * @param error
	 */
	private void adicionarError(String error)	{
		this.error += error + "\n";
	}
	
	/**
	 * Retorna el error
	 * @return
	 */
	public String darError() {
		return this.error;
	}
	
	/**
	 * Retorna la ubicacion fisica del archivo.
	 * @return
	 */
	public String darUbicacionFisica() {
		return this.ubicacionFisica;				
	}
	
	/**
	 * Instancia el tipo de arhivo, sea clase, interfaz o enumerador.
	 * @param tipoEstructura
	 */
	private void instanciarTipoArchivo(TipoEstructura tipoEstructura, String nombre){
		if (TipoEstructura.Clase == tipoEstructura) {
			this.tipoArchivo = new Clase(nombre);
		}
		else if (TipoEstructura.Interfaz == tipoEstructura) {
			this.tipoArchivo = new Interfaz(nombre);
		}
		else if (TipoEstructura.Enum == tipoEstructura) {
			this.tipoArchivo = new Enumerador(nombre);
		}		
	}
	
	/**
	 * Se retora el paquete
	 * @return
	 */
	public Paquete darPaquete() {
		return this.paquete;
	}

	/**
	 * Retorna el tipo de archivo
	 * @return
	 */
	public TipoArchivo darTipoArchivo() {
		return this.tipoArchivo;
	}
	
	/**
	 * Retorna las funciones
	 * @return
	 */
	public ArrayList<Funcion> darFunciones() {
		return this.funciones;
	}
	
	
}

	