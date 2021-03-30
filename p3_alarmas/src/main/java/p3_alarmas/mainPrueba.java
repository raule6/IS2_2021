package p3_alarmas;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.PriorityQueue;

public class mainPrueba {

	public static void main(String[] args) {
		Date date = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
        c.set(Calendar.MONTH, 11); // IMPORTANTE: ( 0 = Enero)
        c.set(Calendar.DATE, 05);
        c.set(Calendar.YEAR, 2022);
        c.set(Calendar.HOUR, 23);
        c.set(Calendar.MINUTE, 29);
        c.set(Calendar.SECOND, 22);
		date = c.getTime();
		PriorityQueue<Alarma> alarmasActivas = new PriorityQueue<Alarma>(10, new Comparator<Alarma>() {
			public int compare(Alarma a1, Alarma a2) {
				return a1.getHora().compareTo(a2.getHora());
			}
		});
		alarmasActivas.add(new Alarma("alarma de prueba", date));
		alarmasActivas.add(new Alarma("alarma bulebu", new Date()));
		
		for (int i=0; i <= alarmasActivas.size(); i++) {
			Alarma a = alarmasActivas.poll();
			System.out.println("Pos"+i+": " + a.getID() + ", " + a.getHora().toString());
		}
	}

}
