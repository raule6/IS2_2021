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
		timer.schedule(expiraTiempoTask, context.alarmaMasProxima().getHora());
	}

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
