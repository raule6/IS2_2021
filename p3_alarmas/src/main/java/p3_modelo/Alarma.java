package p3_modelo;

import java.util.Date;

/**
 * Clase alarma.
 * @author Raúl y Pablo
 *
 */
public class Alarma implements Comparable<Alarma> {
	
	// Atributos
	private String id;
	private Date hora;
	
	/**
	 * Constructor de la clase Alarma.
	 * @param id: Nombre de la nueva alarma.
	 * @param hora: Fecha de la nueva alarma.
	 */
	public Alarma(String id, Date hora) {
		this.id = id;
		this.hora = hora;
	}
	
	// Getters
	
	/**
	 * Método que obtiene la id de la Alarma.
	 * @return Id de la alarma.
	 */
	public String getID() {
		return id;
	}
	
	/**
	 * Método que obtiene la hora de la Alarma.
	 * @return Hora de la alarma.
	 */
	public Date getHora() {
		return hora;
	}
	
	//Setters
	
	/**
	 * Método que establece una nueva hora.
	 * @param nuevaHora: Nueva hora de la alarma.
	 */
	public void setHora(Date nuevaHora) {
		hora = nuevaHora;
	}
	
	@Override
    public int compareTo (Alarma a) {
        if (hora.before(a.hora)) {
            return -1;
        } else {
            return 1;
        }
    }
}
