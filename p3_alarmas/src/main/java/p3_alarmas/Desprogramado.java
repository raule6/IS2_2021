package p3_alarmas;

import java.util.Date;

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
	
	/** 
		this.exitAction(context);
		context.AlarmaOn(id);
		context.SetState(estadoProgramado);
		estadoProgramado.entryAction(context);
		estadoProgramado.doAction(context);
	 */
	
}
