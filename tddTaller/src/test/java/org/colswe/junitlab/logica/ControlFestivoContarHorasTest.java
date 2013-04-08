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
		
	}
	
	public void testFuncionalidadEsperada() {
		List<Festivo> festivos = new ArrayList<Festivo>();
		//Festivos No Laborables
		Festivo festivoNoLab1 = produceFestivo(Calendar.APRIL, 2, false);
		Festivo festivoNoLab2 = produceFestivo(Calendar.APRIL, 3, false);
		Festivo festivoNoLab3 = produceFestivo(Calendar.APRIL, 29, false);
		Festivo festivoNoLab4 = produceFestivo(Calendar.APRIL, 30, false);
		Festivo festivoNoLab5 = produceFestivo(Calendar.MAY, 1, false);
		festivos.add(festivoNoLab1);
		festivos.add(festivoNoLab2);
		festivos.add(festivoNoLab3);
		festivos.add(festivoNoLab4);
		festivos.add(festivoNoLab5);
		
		//Festivos Laborables
		Festivo festivoLab1 = produceFestivo(Calendar.APRIL, 9, true);
		Festivo festivoLab2 = produceFestivo(Calendar.APRIL, 10, true);
		Festivo festivoLab3 = produceFestivo(Calendar.APRIL, 16, true);
		Festivo festivoLab4 = produceFestivo(Calendar.APRIL, 17, true);
		Festivo festivoLab5 = produceFestivo(Calendar.APRIL, 18, true);
		Festivo festivoLab6 = produceFestivo(Calendar.APRIL, 19, true);
		festivos.add(festivoLab1);
		festivos.add(festivoLab2);
		festivos.add(festivoLab3);
		festivos.add(festivoLab4);
		festivos.add(festivoLab5);
		festivos.add(festivoLab6);
		
		//Sabados Festivos No Laborables
		Festivo festivoSabadoNoLab = produceFestivo(Calendar.APRIL, 13, false);
		festivos.add(festivoSabadoNoLab);
		
		//Sabados Festivos Laborables
		Festivo festivoSabadoLab = produceFestivo(Calendar.APRIL, 6, true);
		festivos.add(festivoSabadoLab);
		
		//Domingos Festivos Laborables
		Festivo festivoDomingoLab1 = produceFestivo(Calendar.APRIL, 7, true);
		Festivo festivoDomingoLab2 = produceFestivo(Calendar.APRIL, 14, true);
		festivos.add(festivoDomingoLab1);
		festivos.add(festivoDomingoLab2);

		ControlFestivo cf = new ControlFestivo();
		cf.sistema.setEntidades(festivos);

		Calendar hasta = Calendar.getInstance();
		Calendar desde = Calendar.getInstance();
		hasta.setTimeInMillis(0);
		desde.setTimeInMillis(0);
		

		//Dos festivos no laborables seguidos
		desde.set(Calendar.YEAR, 2013);
		desde.set(Calendar.MONTH, Calendar.APRIL);
		desde.set(Calendar.DATE, 1);
		hasta.set(Calendar.YEAR, 2013);
		hasta.set(Calendar.MONTH, Calendar.APRIL);
		hasta.set(Calendar.DATE, 4);
		assertEquals(new Integer(16), cf.contarHoras(desde.getTime(), hasta.getTime()));


		//Dos festivos laborables seguidos
		desde.set(Calendar.YEAR, 2013);
		desde.set(Calendar.MONTH, Calendar.APRIL);
		desde.set(Calendar.DATE, 8);
		hasta.set(Calendar.YEAR, 2013);
		hasta.set(Calendar.MONTH, Calendar.APRIL);
		hasta.set(Calendar.DATE, 11);
		assertEquals(new Integer(48), cf.contarHoras(desde.getTime(), hasta.getTime()));
		

		//Fechas limites festivos no laborables
		desde.set(Calendar.YEAR, 2013);
		desde.set(Calendar.MONTH, Calendar.APRIL);
		desde.set(Calendar.DATE, 2);
		hasta.set(Calendar.YEAR, 2013);
		hasta.set(Calendar.MONTH, Calendar.APRIL);
		hasta.set(Calendar.DATE, 3);
		assertEquals(new Integer(0), cf.contarHoras(desde.getTime(), hasta.getTime()));
		
		//Fechas limites festivos laborables
		desde.set(Calendar.YEAR, 2013);
		desde.set(Calendar.MONTH, Calendar.APRIL);
		desde.set(Calendar.DATE, 1);
		hasta.set(Calendar.YEAR, 2013);
		hasta.set(Calendar.MONTH, Calendar.APRIL);
		hasta.set(Calendar.DATE, 4);
		assertEquals(new Integer(32), cf.contarHoras(desde.getTime(), hasta.getTime()));

		//Rango que incluye un día sábado festivo laborable y un domingo festivo laborable
		desde.set(Calendar.YEAR, 2013);
		desde.set(Calendar.MONTH, Calendar.APRIL);
		desde.set(Calendar.DATE, 5);
		hasta.set(Calendar.YEAR, 2013);
		hasta.set(Calendar.MONTH, Calendar.APRIL);
		hasta.set(Calendar.DATE, 8);
		assertEquals(new Integer(40), cf.contarHoras(desde.getTime(), hasta.getTime()));

		//Rango que incluye un día sábado festivo no laborable y un domingo festivo laborable
		desde.set(Calendar.YEAR, 2013);
		desde.set(Calendar.MONTH, Calendar.APRIL);
		desde.set(Calendar.DATE, 12);
		hasta.set(Calendar.YEAR, 2013);
		hasta.set(Calendar.MONTH, Calendar.APRIL);
		hasta.set(Calendar.DATE, 15);
		assertEquals(new Integer(24), cf.contarHoras(desde.getTime(), hasta.getTime()));
		
		//Rango con solo fechas festivas laborables
		desde.set(Calendar.YEAR, 2013);
		desde.set(Calendar.MONTH, Calendar.APRIL);
		desde.set(Calendar.DATE, 16);
		hasta.set(Calendar.YEAR, 2013);
		hasta.set(Calendar.MONTH, Calendar.APRIL);
		hasta.set(Calendar.DATE, 19);
		assertEquals(new Integer(64), cf.contarHoras(desde.getTime(), hasta.getTime()));
		
		//Rango con solo fechas festivas no laborables
		desde.set(Calendar.YEAR, 2013);
		desde.set(Calendar.MONTH, Calendar.APRIL);
		desde.set(Calendar.DATE, 29);
		hasta.set(Calendar.YEAR, 2013);
		hasta.set(Calendar.MONTH, Calendar.MAY);
		hasta.set(Calendar.DATE, 1);
		assertEquals(new Integer(0), cf.contarHoras(desde.getTime(), hasta.getTime()));
		
		//Rango con solo fechas comunes
		desde.set(Calendar.YEAR, 2013);
		desde.set(Calendar.MONTH, Calendar.APRIL);
		desde.set(Calendar.DATE, 22);
		hasta.set(Calendar.YEAR, 2013);
		hasta.set(Calendar.MONTH, Calendar.APRIL);
		hasta.set(Calendar.DATE, 25);
		assertEquals(new Integer(32), cf.contarHoras(desde.getTime(), hasta.getTime()));
		
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
