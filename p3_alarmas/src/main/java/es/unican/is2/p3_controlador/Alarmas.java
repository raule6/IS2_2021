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
 * @author Raúl y Pablo
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
	 * @param vp: clase de la interfaz gráfica
	 */
	public Alarmas(VentanaPrincipal vp) {
		alarmasActivas = new PriorityQueue<Alarma>();
		alarmasDesactivadas = new ArrayList<Alarma>();
		state = AlarmasState.init(this);
		interfaz = vp;
	}

	/**
	 * Método para buscar una alarma por su id (tanto en las activadas como en las desactivadas).
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
	 * Método para añadir una nueva alarma a la cola de alarmas.
	 * @param a Alarma a añadir
	 * @return Devuelve true si se ha conseguido añadir una alarma con exito, 
	 * y false en caso contrario.
	 */
	public boolean anhadeAlarma(Alarma a) {
		if (buscaAlarmaActiva(a.getID()) != null || 
				buscaAlarmaDesactivada(a.getID()) != null) return false;

		alarmasActivas.add(a);
		return true;
	}

	/**
	 * Método para elminar una alarma a la cola de alarmas.
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
	 * Método para retornar la alarma mas proxima en sonar.
	 * @return Devuelve la alarma o null.
	 */
	public Alarma alarmaMasProxima() {
		return alarmasActivas.peek(); // Sólo devuelve
	}

	/**
	 * Método para desactivar una alarma activa
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
	 * Método para activar una alarma desactivada
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
	 * Método para devolver las alarmas activas actuales.
	 * @return Devuelve una lista de alarmas activas.
	 */
	public PriorityQueue<Alarma> alarmasActivas() {
		return alarmasActivas;
	}

	/**
	 * Método para devolver las alarmas desactivadas actuales.
	 * @return Devuelve una lista de alarmas desactivadas.
	 */
	public List<Alarma> alarmasDesactivadas() {
		return alarmasDesactivadas;
	}

	/**
	 * Método par activar la melodia de una alarma.
	 */
	public void activarMelodia() {
		interfaz.suenaAlarma();
	}

	/**
	 * Método par desactivar la melodia de una alarma.
	 */
	public void desactivarMelodia() {
		interfaz.apagaAlarma();
	}

	// SEÑALES
	
	/**
	 * Señal para crear una nueva alarma.
	 * @param id: nombre de la alarma
	 * @param hora: fecha de la alarma
	 */
	public void NuevaAlarma(String id, Date hora) {
		state.NuevaAlarma(this, id, hora);
	}

	/**
	 * Señal para apagar una alarma.
	 */
	public void Apagar() {
		state.Apagar(this);
	}

	/**
	 * Señal para desactivar una alarma.
	 * @param id: nombre de la alarma
	 */
	public void AlarmaOff(String id) {
		state.AlarmaOff(this, id);
	}

	/**
	 * Señal para activar una alarma.
	 * @param id: nombre de la alarma
	 */
	public void AlarmaOn(String id) {
		state.AlarmaOn(this, id);
	}

	/**
	 * Señal para eliminar una alarma.
	 * @param id: nombre de la alarma
	 */
	public void BorraAlarma(String id) {
		state.BorraAlarma(this, id);
	}

	/**
	 * Señal para actualizar el estado.
	 * @param state: estado
	 */
	public void SetState(AlarmasState state) {
		this.state = state;
	};

	// Métodos auxiliares
	
	/**
	 * Método para retornar el intervalo que debe sonar cada alarma.
	 * @return INTERVALO_SONAR valor de la constante.
	 */
	public int getIntervalo() {
		return INTERVALO_SONAR;
	}
	
	/**
	 * Método secundario para buscar una alarma activa por su id.
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
	 * Método secundario para buscar una alarma desactivada por su id.
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
