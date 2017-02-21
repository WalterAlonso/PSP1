package uniandes.ecos.conceptosAvanzados.test;

import uniandes.ecos.conceptosAvanzados.modelo.*;
import junit.framework.TestCase;

/**
 *  Clase que testea la clase Media
 *  @author Walter Alonso
 *  
 */
public class MediaTest  extends TestCase {
	
	/**
	 * La propiedad media
	 */
	private Media media;
	
	/**
     * Incializa un calculo de datos para la media.
     */
    private void setupInicializarMedia( ) {
        	Dato datoSiguienteNueve = new Dato();
        	datoSiguienteNueve.asignarValor(1503);        	
        	Dato datoSiguienteOcho = new Dato();
        	datoSiguienteOcho.asignarValor(624);
        	datoSiguienteOcho.asignarDatoSiguiente(datoSiguienteNueve);
        	Dato datoSiguienteSiete = new Dato();
        	datoSiguienteSiete.asignarValor(1657);
        	datoSiguienteSiete.asignarDatoSiguiente(datoSiguienteOcho);
        	Dato datoSiguienteSeis = new Dato();
        	datoSiguienteSeis.asignarValor(128);
        	datoSiguienteSeis.asignarDatoSiguiente(datoSiguienteSiete);
        	Dato datoSiguienteCinco = new Dato();
        	datoSiguienteCinco.asignarValor(270);
        	datoSiguienteCinco.asignarDatoSiguiente(datoSiguienteSeis);
        	Dato datoSiguienteCuatro = new Dato();
        	datoSiguienteCuatro.asignarValor(230);
        	datoSiguienteCuatro.asignarDatoSiguiente(datoSiguienteCinco);
        	Dato datoSiguienteTres = new Dato();
        	datoSiguienteTres.asignarValor(229);
        	datoSiguienteTres.asignarDatoSiguiente(datoSiguienteCuatro);
        	Dato datoSiguienteDos = new Dato();
        	datoSiguienteDos.asignarValor(114);
        	datoSiguienteDos.asignarDatoSiguiente(datoSiguienteTres);
        	Dato datoSiguienteUno = new Dato();
        	datoSiguienteUno.asignarValor(591);
        	datoSiguienteUno.asignarDatoSiguiente(datoSiguienteDos);
        	Dato datoRaiz = new Dato();
        	datoRaiz.asignarValor(160);
        	datoRaiz.asignarDatoSiguiente(datoSiguienteUno);        	
        	
        	media = new Media(datoRaiz);         
    }
	
    /**
     * Este método se encarga de verificar la media
     * Se calcula la media, se espera que de 550.6
     */
    public void testDarCalculo() {
    	setupInicializarMedia( );
        assertEquals( "La media deberia ser 550.6", 550.6, media.darCalculo());
    }	
}
