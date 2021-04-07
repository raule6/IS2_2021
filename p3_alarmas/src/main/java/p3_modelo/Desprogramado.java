package p3_modelo;

import java.util.Date;

import p3_controlador.Alarmas;

/**
 * Clase que implementa el estado Desprogramado.
 * @author Pablo y Raúl
 *
 */
public class Desprogramado extends AlarmasState {

	@Override
	public void NuevaAlarma(Alarmas context, String id, Date hora) {
		context.anhadeAlarma(new Alarma(id, hora));
		context.SetState(estadoProgramado);
		estadoProgramado.entryAction(context);
		estadoProgramado.doAction(context);
	}

	@Override
	public void AlarmaOn(Alarmas context, String id) {
		context.activaAlarma(id);
		context.SetState(estadoProgramado);
		estadoProgramado.entryAction(context);
		estadoProgramado.doAction(context);
	}
	
	@Override
	public void BorraAlarma(Alarmas context, String id) {
		this.exitAction(context);
		context.eliminaAlarma(id);
		this.entryAction(context);
		this.doAction(context);
	}
	
}
