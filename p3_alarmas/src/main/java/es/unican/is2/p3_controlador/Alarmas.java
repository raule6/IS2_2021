package es.unican.is2.p3_controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;

import es.unican.is2.p3_modelo.Alarma;
import es.unican.is2.p3_modelo.AlarmasState;
import es.unican.is2.p3_vista.VentanaPrincipal;

/**
 * Clase controlador de alarmas.
 * @author Ra�l y Pablo
 *
 */
public class Alarmas {
	
	// Constantes
	private static final int INTERVALO_SONAR = 8000;

	// Atributos
	private PriorityQueue<Alarma> alarmasActivas;
	private List<Alarma> alarmasDesactivadas;
	private AlarmasState state;
	private VentanaPrincipal interfaz;

	/**
	 * Contructor del controlador de alarmas
	 * @param vp: clase de la interfaz gr�fica
	 */
	public Alarmas(VentanaPrincipal vp) {
		alarmasActivas = new PriorityQueue<Alarma>();
		alarmasDesactivadas = new ArrayList<Alarma>();
		state = AlarmasState.init(this);
		interfaz = vp;
	}

	/**
	 * M�todo para buscar una alarma por su id (tanto en las activadas como en las desactivadas).
	 * @param id Id de la alarma a buscar.
	 * @return Devuelve la alarma en el caso de que se encuntre, y null en caso contrario.
	 */
	public Alarma alarma(String id) {
		Alarma alarmaActiva = buscaAlarmaActiva(id);
		if (alarmaActiva != null) return alarmaActiva;

		Alarma alarmaDesactiada = buscaAlarmaDesactivada(id);
		if (alarmaDesactiada != null) return alarmaDesactiada;
		return null;
	}

	/**
	 * M�todo para a�adir una nueva alarma a la cola de alarmas.
	 * @param a Alarma a a�adir
	 * @return Devuelve true si se ha conseguido a�adir una alarma con exito, 
	 * y false en caso contrario.
	 */
	public boolean anhadeAlarma(Alarma a) {
		if (buscaAlarmaActiva(a.getID()) != null || 
				buscaAlarmaDesactivada(a.getID()) != null) return false;

		alarmasActivas.add(a);
		return true;
	}

	/**
	 * M�todo para elminar una alarma a la cola de alarmas.
	 * @param a Alarma a eliminar
	 * @return Devuelve true si se ha conseguido eliminar una alarma con exito, 
	 * y false en caso contrario.
	 */
	public boolean eliminaAlarma(String id) {
		Alarma alarmaActiva = buscaAlarmaActiva(id);
		if (alarmaActiva != null) {
			alarmasActivas.remove(alarmaActiva);
			interfaz.updateLists();
			return true;
		}
		Alarma alarmaDesactivada = buscaAlarmaDesactivada(id);
		if (alarmaDesactivada != null) {
			alarmasDesactivadas.remove(alarmaDesactivada);
			interfaz.updateLists();
			return true;
		}
		return false;
	}

	/**
	 * M�todo para retornar la alarma mas proxima en sonar.
	 * @return Devuelve la alarma o null.
	 */
	public Alarma alarmaMasProxima() {
		return alarmasActivas.peek(); // S�lo devuelve
	}

	/**
	 * M�todo para desactivar una alarma activa
	 * @param a Alarma a desactivar.
	 */
	public void desactivaAlarma(String id) {
		Alarma alarmaActiva = buscaAlarmaActiva(id);
		if (alarmaActiva != null) {
			alarmasActivas.remove(alarmaActiva);
			alarmasDesactivadas.add(alarmaActiva);
		}
	}

	/**
	 * M�todo para activar una alarma desactivada
	 * @param a Alarma a activar.
	 */
	public void activaAlarma(String id) {
		Alarma alarmaActiva = buscaAlarmaDesactivada(id);
		if (alarmaActiva != null) {
			alarmasDesactivadas.remove(alarmaActiva);
			anhadeAlarma(alarmaActiva);
		}
	}

	/**
	 * M�todo para devolver las alarmas activas actuales.
	 * @return Devuelve una lista de alarmas activas.
	 */
	public PriorityQueue<Alarma> alarmasActivas() {
		return alarmasActivas;
	}

	/**
	 * M�todo para devolver las alarmas desactivadas actuales.
	 * @return Devuelve una lista de alarmas desactivadas.
	 */
	public List<Alarma> alarmasDesactivadas() {
		return alarmasDesactivadas;
	}

	/**
	 * M�todo par activar la melodia de una alarma.
	 */
	public void activarMelodia() {
		interfaz.suenaAlarma();
	}

	/**
	 * M�todo par desactivar la melodia de una alarma.
	 */
	public void desactivarMelodia() {
		interfaz.apagaAlarma();
	}

	// SE�ALES
	
	/**
	 * Se�al para crear una nueva alarma.
	 * @param id: nombre de la alarma
	 * @param hora: fecha de la alarma
	 */
	public void NuevaAlarma(String id, Date hora) {
		state.NuevaAlarma(this, id, hora);
	}

	/**
	 * Se�al para apagar una alarma.
	 */
	public void Apagar() {
		state.Apagar(this);
	}

	/**
	 * Se�al para desactivar una alarma.
	 * @param id: nombre de la alarma
	 */
	public void AlarmaOff(String id) {
		state.AlarmaOff(this, id);
	}

	/**
	 * Se�al para activar una alarma.
	 * @param id: nombre de la alarma
	 */
	public void AlarmaOn(String id) {
		state.AlarmaOn(this, id);
	}

	/**
	 * Se�al para eliminar una alarma.
	 * @param id: nombre de la alarma
	 */
	public void BorraAlarma(String id) {
		state.BorraAlarma(this, id);
	}

	/**
	 * Se�al para actualizar el estado.
	 * @param state: estado
	 */
	public void SetState(AlarmasState state) {
		this.state = state;
	};

	// M�todos auxiliares
	
	/**
	 * M�todo para retornar el intervalo que debe sonar cada alarma.
	 * @return INTERVALO_SONAR valor de la constante.
	 */
	public int getIntervalo() {
		return INTERVALO_SONAR;
	}
	
	/**
	 * M�todo secundario para buscar una alarma activa por su id.
	 * @param id Id de la alarma a buscar.
	 * @return Devuelve la alarma en el caso de que se encuntre, y null en caso contrario.
	 */
	public Alarma buscaAlarmaActiva(String id) {
		for (Alarma a: alarmasActivas) {
			if (a.getID().equals(id)) return a;
		}
		return null;
	}

	/**
	 * M�todo secundario para buscar una alarma desactivada por su id.
	 * @param id Id de la alarma a buscar.
	 * @return Devuelve la alarma en el caso de que se encuntre, y null en caso contrario.
	 */
	public Alarma buscaAlarmaDesactivada(String id) {
		for (Alarma a: alarmasDesactivadas) {
			if (a.getID().equals(id)) return a;
		}
		return null;
	}
}
