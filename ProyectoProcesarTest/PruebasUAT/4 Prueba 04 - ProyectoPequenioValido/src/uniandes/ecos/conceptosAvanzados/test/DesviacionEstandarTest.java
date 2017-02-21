package uniandes.ecos.conceptosAvanzados.test;

import junit.framework.TestCase;
import uniandes.ecos.conceptosAvanzados.modelo.Dato;
import uniandes.ecos.conceptosAvanzados.modelo.DesviacionEstandar;
import uniandes.ecos.conceptosAvanzados.modelo.Media;

/**
 *  Clase que testea la clase DesviacionEstandar
 *  @author Walter Alonso
 *  
 */
public class DesviacionEstandarTest extends TestCase {
	
	/**
	 * La propiedad desviacion estandar
	 */
	private DesviacionEstandar desviacionEstandar;
	
	/**
     * Incializa un calculo de datos para la desviacion estandar.
     */
    private void setupInicializarDesviacionEstandar( ) {
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
        	
        	Media media = new Media(datoRaiz);
        	desviacionEstandar = new DesviacionEstandar(datoRaiz, media);         
    }
	
    /**
     * Este método se encarga de verificar la media
     * Se calcula la media, se espera que de 550.6
     */
    public void testDarCalculo() {
    	setupInicializarDesviacionEstandar();
        try {
			assertEquals( "La media deberia ser 572.026844746915", 572.026844746915, desviacionEstandar.darCalculo());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }	
}

