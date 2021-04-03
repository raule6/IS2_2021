package p3_alarmas;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Programado extends AlarmasState {

	private Timer timer = new Timer();
	private ExpiraTiempoTask expiraTiempoTask;

	@Override
	public void NuevaAlarma(Alarmas context, String id, Date hora) {
		this.exitAction(context);
		context.anhadeAlarma(new Alarma(id, hora));
		this.entryAction(context);
	}

	@Override
	public void AlarmaOff(Alarmas context, String id) {
		this.exitAction(context);
		context.desactivaAlarma(id);
		this.entryAction(context);
	}
	
	@Override
	public void AlarmaOn(Alarmas context, String id) {
		this.exitAction(context);
		context.activaAlarma(id);
		this.entryAction(context);
	}
	
	@Override
	public void BorraAlarma(Alarmas context, String id) {
		this.exitAction(context);
		context.eliminaAlarma(id);
		this.entryAction(context);
	}

	@Override
	public void entryAction(Alarmas context) {
		if (context.alarmasActivas().isEmpty()) {
			context.SetState(estadoDesprogramado);
			estadoDesprogramado.entryAction(context);
			return;
		}
		
		expiraTiempoTask = new ExpiraTiempoTask(context);
		timer.schedule(expiraTiempoTask, context.alarmaMasProxima().getHora()/*getWhen(context)*/);
	}
	/*
	private Date getWhen(Alarmas context) {
		// Fecha en la que quieres que suene.
		Date date = context.alarmaMasProxima().getHora();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int h = c.get(Calendar.HOUR);
		int min = c.get(Calendar.MINUTE);
		
		// Fecha actual.
		Date d = new Date();
		c = Calendar.getInstance();
		c.setTime(d);
		int ah = c.get(Calendar.HOUR);
		int amin = c.get(Calendar.MINUTE);
		
		Date when = new Date();
		c = Calendar.getInstance();
		c.setTime(when);
		c.set(Calendar.HOUR, h);
		c.set(Calendar.MINUTE, min);
		c.set(Calendar.SECOND, 0);
		
		if (60 * ah + amin > 60 * h + min) {
			c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 1);
		}
		
		when = c.getTime();
		System.out.println(when);
		return when;
	}*/
	
	@Override
	public void exitAction(Alarmas context) {
		expiraTiempoTask.cancel();
	}
	
	public class ExpiraTiempoTask extends TimerTask {
		 private Alarmas context;
		 public ExpiraTiempoTask (Alarmas c) {
			 context = c;
		 }
		 // Tarea que se ejecuta cuando se alcanza el tiempo
		 public void run() {
			 estadoProgramado.exitAction(context);
			 estadoSonando.entryAction(context);
			 estadoSonando.doAction(context);
			 context.SetState(estadoSonando);
		 }
	}
}
