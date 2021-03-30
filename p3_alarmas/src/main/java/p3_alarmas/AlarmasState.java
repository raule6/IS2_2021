package p3_alarmas;

import java.util.Date;

public abstract class AlarmasState {
	
	protected static Desprogramado estadoDesprogramado = new Desprogramado();
	protected static Programado estadoProgramado = new Programado();
	protected static Sonando estadoSonando = new Sonando();
	
	public void NuevaAlarma(Alarmas context, String id, Date hora) {}
	
	public void Apagar(Alarmas context) {}
	
	public void AlarmaOff(Alarmas context, String id) {}
	
	public void AlarmaOn(Alarmas context, String id) {}
	
	public void BorraAlarma(Alarmas context, String id) {}
	
	public void entryAction(Alarmas context) {}
	
	public void exitAction(Alarmas context) {}
	
	public void doAction(Alarmas context) {}
	
}
