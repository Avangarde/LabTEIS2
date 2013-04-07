/**
 * 
 */
package org.colswe.junitlab.logica;

import java.util.ArrayList;
import java.util.Calendar;
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
	
//	public void testFechasInvalidas(){
//		List<Festivo> festivos = new ArrayList<Festivo>();
//		Festivo festivo = produceFestivo(Calendar.JANUARY, 4, false);
//		Festivo festivo1 = produceFestivo(Calendar.JANUARY, 11, true);
//		festivos.add(festivo);
//		festivos.add(festivo1);
//		
//		ControlFestivo cf = new ControlFestivo();
//		cf.sistema.setEntidades(festivos);
//
//		Calendar desde = Calendar.getInstance();
//		Calendar hasta = Calendar.getInstance();
//		desde = Calendar.getInstance();
//		hasta = Calendar.getInstance();
//		desde.setTimeInMillis(0);
//		hasta.setTimeInMillis(0);
//		desde.set(Calendar.YEAR, 292278994);
//		hasta.set(Calendar.YEAR, 292278994);
//		desde.set(Calendar.MONTH, 1);
//		hasta.set(Calendar.MONTH, 1);
//		desde.set(Calendar.DATE, 17);
//		hasta.set(Calendar.DATE, 18);
//		System.out.println(desde.get(hasta.DAY_OF_WEEK));
//	}

	public void test1() {
		
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
