package p3_alarmas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;

public class Alarmas {
	// Constantes
	private static final int INTERVALO_SONAR = 10000;
	
	// Atributos
	private PriorityQueue<Alarma> alarmasActivas;
	private List<Alarma> alarmasDesactivadas;
	private AlarmasState state;
	
	private VentanaPrincipal vp;
	
	/**
	 * Contructor del controlador de alarmas
	 */
	public Alarmas(VentanaPrincipal vp) {
		alarmasActivas = new PriorityQueue<Alarma>(10, new Comparator<Alarma>() {
			public int compare(Alarma a1, Alarma a2) {
				return a1.getHora().compareTo(a2.getHora());
			}
		});
		alarmasDesactivadas = new ArrayList<Alarma>();
		state = AlarmasState.init(this);
		this.vp = vp;
	}
	
	/**
	 * Metodo para buscar una alarma por su id (tanto en las activadas como en las desactivadas).
	 * @param id Id de la alarma a buscar.
	 * @return Devuelve la alarma en el caso de que se encuntre, y null en caso contrario.
	 */
	public Alarma alarma(String id) {
		Alarma alarmaActiva = buscaAlarmaActiva(id);
		if (alarmaActiva != null) {
			return alarmaActiva;
		}
		Alarma alarmaDesactiada = buscaAlarmaDesactivada(id);
		if (alarmaDesactiada != null) {
			return alarmaDesactiada;
		}
		return null;
	}
	
	/**
	 * Metodo para añadir una nueva alarma a la cola de alarmas.
	 * @param a Alarma a añadir
	 * @return Devuelve true si se ha conseguido añadir una alarma con exito, 
     * y false en caso contrario.
	 */
	public boolean anhadeAlarma(Alarma a) {
		if (buscaAlarmaActiva(a.getID()) != null || buscaAlarmaDesactivada(a.getID()) != null) {
			return false;
		}
		alarmasActivas.add(a);
		return true;
	}
	
	/**
	 * Metodo para elminar una alarma a la cola de alarmas.
	 * @param a Alarma a eliminar
	 * @return Devuelve true si se ha conseguido eliminar una alarma con exito, 
     * y false en caso contrario.
	 */
	public boolean eliminaAlarma(String id) {
		Alarma alarmaActiva = buscaAlarmaActiva(id);
		if (alarmaActiva != null) {
			alarmasActivas.remove(alarmaActiva);
			vp.updateLists();
			return true;
		}
		Alarma alarmaDesactivada = buscaAlarmaDesactivada(id);
		if (alarmaDesactivada != null) {
			alarmasDesactivadas.remove(alarmaDesactivada);
			vp.updateLists();
			return true;
		}
		return false;
	}
	
	
	public Alarma alarmaMasProxima() {
		return alarmasActivas.peek(); // Sólo devuelve
	}
	
	/**
	 * Metodo para desactivar una alarma activa
	 * @param a Alarma a eliminar
	 * @return Devuelve true si se ha conseguido eliminar una alarma con exito, 
     * y false en caso contrario.
	 */
	public void desactivaAlarma(String id) {
		Alarma alarmaActiva = buscaAlarmaActiva(id);
		if (alarmaActiva != null) {
			alarmasActivas.remove(alarmaActiva);
			alarmasDesactivadas.add(alarmaActiva);
		}
	}
	
	public void activaAlarma(String id) {
		Alarma alarmaActiva = buscaAlarmaDesactivada(id);
		if (alarmaActiva != null) {
			alarmasDesactivadas.remove(alarmaActiva);
			anhadeAlarma(alarmaActiva);
		}
	}
	
	/**
	 * Metodo para devolver las alarmas activas actuales.
	 * @return Devuelve una lista de alarmas activas.
	 */
	public PriorityQueue<Alarma> alarmasActivas() {
		return alarmasActivas;
	}
	
	/**
	 * Metodo para devolver las alarmas desactivadas actuales.
	 * @return Devuelve una lista de alarmas desactivadas.
	 */
	public List<Alarma> alarmasDesactivadas() {
		return alarmasDesactivadas;
	}
	
	/**
	 * Metodo par activar la melodia de una alarma.
	 */
	public void activarMelodia() {
		System.out.println("Melodía activa.");
		vp.sonandoAlarma();
	}
	
	/**
	 * Metodo par desactivar la melodia de una alarma.
	 */
	public void desactivarMelodia() {
		System.out.println("Melodía desactivada.");
		vp.apagandoSonidoAlarma();
	}
	
	// SEÑALES
	public void NuevaAlarma(String id, Date hora) {
		state.NuevaAlarma(this, id, hora);
	}
	
	public void Apagar() {
		state.Apagar(this);
	}
	
	public void AlarmaOff(String id) {
		state.AlarmaOff(this, id);
	}
	
	public void AlarmaOn(String id) {
		state.AlarmaOn(this, id);
	}
	
	public void BorraAlarma(String id) {
		state.BorraAlarma(this, id);
	}
	
	public void SetState(AlarmasState state) {
		this.state = state;
	};
	
	
	public int getIntervalo() {
		return INTERVALO_SONAR;
	}
	
	/**
	 * Metodo secundario para buscar una alarma activa por su id.
	 * @param id Id de la alarma a buscar.
	 * @return Devuelve la alarma en el caso de que se encuntre, y null en caso contrario.
	 */
	private Alarma buscaAlarmaActiva(String id) {
		for (Alarma a: alarmasActivas) {
			if (a.getID().equals(id)) {
				return a;
			}
		}
		return null;
	}
	
	/**
	 * Metodo secundario para buscar una alarma desactivada por su id.
	 * @param id Id de la alarma a buscar.
	 * @return Devuelve la alarma en el caso de que se encuntre, y null en caso contrario.
	 */
	private Alarma buscaAlarmaDesactivada(String id) {
		for (Alarma a: alarmasDesactivadas) {
			if (a.getID().equals(id)) {
				return a;
			}
		}
		return null;
	}
}
