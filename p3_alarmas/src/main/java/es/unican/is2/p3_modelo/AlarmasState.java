package es.unican.is2.p3_modelo;

import java.util.Date;

import es.unican.is2.p3_controlador.Alarmas;

/**
 * Clase abstracta de estados
 * @author Pablo y Ra�l
 *
 */
public abstract class AlarmasState {
	
	// Atributos
	protected static Desprogramado estadoDesprogramado = new Desprogramado();
	protected static Programado estadoProgramado = new Programado();
	protected static Sonando estadoSonando = new Sonando();
	
	/**
	 * M�todo inicializador.
	 * @param context: Contexto de la aplicaci�n.
	 * @return estadoDesprogramado.
	 */
	public static AlarmasState init(Alarmas context) {
		estadoDesprogramado.entryAction(context);
		return estadoDesprogramado;
	}
	
	/**
	 * M�todo para crear una nueva alarma.
	 * @param context: Contexto de la aplicaci�n.
	 * @param id: Nombre de la nueva alarma.
	 * @param hora: Fecha de la nueva alarma.
	 */
	public void NuevaAlarma(Alarmas context, String id, Date hora) {}
	
	/**
	 * M�todo para apagar una alarma.
	 * @param context: Contexto de la aplicaci�n.
	 */
	public void Apagar(Alarmas context) {}
	
	/**
	 * M�todo para desactivar una alarma.
	 * @param context: Contexto de la aplicaci�n.
	 * @param id: Nombre de la alarma a desactivar.
	 */
	public void AlarmaOff(Alarmas context, String id) {}
	
	/**
	 * M�todo para activar una alarma.
	 * @param context: Contexto de la aplicaci�n.
	 * @param id: Nombre de la alarma a activar.
	 */
	public void AlarmaOn(Alarmas context, String id) {}
	
	/**
	 * M�todo para borrar una alarma.
	 * @param context: Contexto de la aplicaci�n.
	 * @param id: Nombre de la alarma a borrar.
	 */
	public void BorraAlarma(Alarmas context, String id) {}
	
	/**
	 * M�todo llamado al entrar en un estado.
	 * @param context: Contexto de la aplicaci�n.
	 */
	public void entryAction(Alarmas context) {}
	
	/**
	 * M�todo llamado al salir en un estado.
	 * @param context: Contexto de la aplicaci�n.
	 */
	public void exitAction(Alarmas context) {}
	
	/**
	 * M�todo llamado para ejecutar una acci�n en un estado.
	 * @param context: Contexto de la aplicaci�n.
	 */
	public void doAction(Alarmas context) {}
	
}
