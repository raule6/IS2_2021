package es.unican.is2.p3_modelo;

import java.util.Date;

import es.unican.is2.p3_controlador.Alarmas;

/**
 * Clase abstracta de estados
 * @author Pablo y Raúl
 *
 */
public abstract class AlarmasState {
	
	// Atributos
	protected static Desprogramado estadoDesprogramado = new Desprogramado();
	protected static Programado estadoProgramado = new Programado();
	protected static Sonando estadoSonando = new Sonando();
	
	/**
	 * Método inicializador.
	 * @param context: Contexto de la aplicación.
	 * @return estadoDesprogramado.
	 */
	public static AlarmasState init(Alarmas context) {
		estadoDesprogramado.entryAction(context);
		return estadoDesprogramado;
	}
	
	/**
	 * Método para crear una nueva alarma.
	 * @param context: Contexto de la aplicación.
	 * @param id: Nombre de la nueva alarma.
	 * @param hora: Fecha de la nueva alarma.
	 */
	public void NuevaAlarma(Alarmas context, String id, Date hora) {}
	
	/**
	 * Método para apagar una alarma.
	 * @param context: Contexto de la aplicación.
	 */
	public void Apagar(Alarmas context) {}
	
	/**
	 * Método para desactivar una alarma.
	 * @param context: Contexto de la aplicación.
	 * @param id: Nombre de la alarma a desactivar.
	 */
	public void AlarmaOff(Alarmas context, String id) {}
	
	/**
	 * Método para activar una alarma.
	 * @param context: Contexto de la aplicación.
	 * @param id: Nombre de la alarma a activar.
	 */
	public void AlarmaOn(Alarmas context, String id) {}
	
	/**
	 * Método para borrar una alarma.
	 * @param context: Contexto de la aplicación.
	 * @param id: Nombre de la alarma a borrar.
	 */
	public void BorraAlarma(Alarmas context, String id) {}
	
	/**
	 * Método llamado al entrar en un estado.
	 * @param context: Contexto de la aplicación.
	 */
	public void entryAction(Alarmas context) {}
	
	/**
	 * Método llamado al salir en un estado.
	 * @param context: Contexto de la aplicación.
	 */
	public void exitAction(Alarmas context) {}
	
	/**
	 * Método llamado para ejecutar una acción en un estado.
	 * @param context: Contexto de la aplicación.
	 */
	public void doAction(Alarmas context) {}
	
}
