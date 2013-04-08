/**
 * 
 */
package org.colswe.junitlab.logica;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.colswe.junitlab.logica.imp.ControlFestivo;
import org.colswe.junitlab.modelo.Festivo;

import junit.framework.TestCase;

/**
 * @author juanmanuelmartinezromero
 *
 */
public class ControlFestivoContarHorasTest extends TestCase {

	public ControlFestivoContarHorasTest(String name) {
		super(name);
	}
	
	public void testFechasInvalidas(){
		ControlFestivo cf = new ControlFestivo();
		
		Date desdeNull = null;
		Date hastaNull = null;
		
		Calendar desde = Calendar.getInstance();
		Calendar hasta = Calendar.getInstance();
		desde = Calendar.getInstance();
		hasta = Calendar.getInstance();
		desde.setTimeInMillis(0);
		hasta.setTimeInMillis(0);
		desde.set(Calendar.YEAR, 2013);
		hasta.set(Calendar.YEAR, 2012);
		
		assertEquals(null, cf.contarHoras(desdeNull, hastaNull));
		assertEquals(null, cf.contarHoras(desdeNull, hasta.getTime()));
		assertEquals(null, cf.contarHoras(desde.getTime(), hastaNull));
		assertEquals(null, cf.contarHoras(desde.getTime(), hasta.getTime()));

	}

	public void testFechasIguales() {
		ControlFestivo cf = new ControlFestivo();
		
		Calendar desde = Calendar.getInstance();
		Calendar hasta = Calendar.getInstance();
		desde.getTimeInMillis();
		desde.getTimeInMillis();
		
		//Festivo No Laborable
		Festivo festivo = produceFestivo(Calendar.JANUARY, 4, false);
		desde.setTimeInMillis(0);
		desde.set(Calendar.DATE, 4);
		hasta = desde;	
		assertEquals(new Integer(0), cf.contarHoras(desde.getTime(), hasta.getTime()));
		
		//Festivo Laborable
		Festivo festivo1 = produceFestivo(Calendar.JANUARY, 8, true);
		desde.setTimeInMillis(0);
		desde.set(Calendar.DATE, 8);
		hasta = desde;	
		assertEquals(new Integer(16), cf.contarHoras(desde.getTime(), hasta.getTime()));
	}
	
	public void testFuncionalidadEsperada() {
		
	}
	
	private Festivo produceFestivo(int month, int date, boolean laborable) {
		Festivo festivo = new Festivo();
		Calendar fecha = Calendar.getInstance();
		fecha.setTimeInMillis(0);
		fecha.set(Calendar.YEAR, 2012);
		fecha.set(Calendar.MONTH, month);
		fecha.set(Calendar.DATE, date);
		festivo.setFecha(fecha.getTime());
		festivo.setLaborable(laborable);
		return festivo;
	}
}
