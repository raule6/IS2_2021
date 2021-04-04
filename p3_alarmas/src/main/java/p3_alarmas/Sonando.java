package p3_alarmas;

import java.util.Timer;
import java.util.TimerTask;

public class Sonando extends AlarmasState{

	private Timer timer = new Timer();
	private ExpiraTiempoTask expiraTiempoTask;

	@Override
	public void Apagar(Alarmas context) {
		expiraTiempoTask.cancel();
		this.exitAction(context);
		context.eliminaAlarma(context.alarmaMasProxima().getID());
		context.SetState(estadoProgramado);
		estadoProgramado.entryAction(context);
		estadoProgramado.doAction(context);
	}
	
	@Override
	public void entryAction(Alarmas context) {
		context.activarMelodia();
		expiraTiempoTask = new ExpiraTiempoTask(context);
		timer.schedule(expiraTiempoTask, context.getIntervalo());
	}

	@Override
	public void exitAction(Alarmas context) {
		context.desactivarMelodia();
	}

	@Override
	public void doAction(Alarmas context) {
	}
	
	public class ExpiraTiempoTask extends TimerTask {
		 private Alarmas context;
		 public ExpiraTiempoTask (Alarmas c) {
			 context = c;
		 }
		 // Tarea que se ejecuta cuando se alcanza el tiempo
		 public void run() {
			 estadoSonando.exitAction(context);
			 context.eliminaAlarma(context.alarmaMasProxima().getID());
			 estadoProgramado.entryAction(context);
			 estadoProgramado.doAction(context);
			 context.SetState(estadoProgramado);
		 }
	}
	
}
