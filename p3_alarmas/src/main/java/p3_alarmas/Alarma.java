package p3_alarmas;

import java.util.Date;

public class Alarma implements Comparable<Alarma> {
	// Atributos
	private String id;
	private Date hora;
	
	/**
	 * Constructor de la clase Alarma
	 * @param id
	 * @param hora
	 */
	public Alarma(String id, Date hora) {
		this.id = id;
		this.hora = hora;
	}
	
	// Getters
	
	/**
	 * Método que obtiene la id de la Alarma
	 * @return id
	 */
	public String getID() {
		return id;
	}
	
	/**
	 * Método que obtiene la hora de la Alarma
	 * @return hora
	 */
	public Date getHora() {
		return hora;
	}
	
	//Setters
	
	/**
	 * Método que establece una nueva hora
	 * @param nuevaHora
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
