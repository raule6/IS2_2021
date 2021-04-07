package p3_modelo;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import p3_controlador.Alarmas;

/**
 * Clase que implementa el estado Sonando
 * @author Pablo y Raúl
 *
 */
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

	/**
	 * Clase que implementa la tarea a ejecutar cuando el timer precise.
	 * @author Pablo y Raúl
	 *
	 */
	public class ExpiraTiempoTask extends TimerTask {

		// Atributos
		private Alarmas context;
		
		/**
		 * Constructor de la clase.
		 * @param c: Contexto de la aplicación.
		 */
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
