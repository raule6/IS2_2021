package p3_modelo;

import java.util.Timer;
import java.util.TimerTask;

import p3_controlador.Alarmas;

/**
 * Clase que implementa el estado Sonando
 * @author Pablo y Raúl
 *
 */
public class Sonando extends AlarmasState {

	// Atributos.
	private Timer timer = new Timer();
	private ExpiraTiempoTask expiraTiempoTask;

	// Métodos

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

	/**
	 * Clase que implementa la tarea a ejecutar cuando el timer precise.
	 * @author Raúl y Pablo
	 *
	 */
	public class ExpiraTiempoTask extends TimerTask {

		//	Atributos
		private Alarmas context;
		
		/**
		 * Constructor de la clase ExpiraTiempoTask
		 * @param c: contexto
		 */
		public ExpiraTiempoTask (Alarmas c) {
			context = c;
		}
		
		/**
		 * Tarea que se ejecuta cuando se alcanza el tiempo
		 */
		public void run() {
			estadoSonando.exitAction(context);
			context.eliminaAlarma(context.alarmaMasProxima().getID());
			estadoProgramado.entryAction(context);
			estadoProgramado.doAction(context);
			context.SetState(estadoProgramado);
		}
	}

}
