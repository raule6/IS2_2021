package es.unican.is2.practica5;

/*
 * WMC => 6
 * WMCn => 6/6
 */
public class Valor {
	
	private String entidad;	
	private int numValores;
	private double cotizacionActual;
	
	/*
	 * CC => 1
	 * CCog => 0
	 */
	public Valor(String entidad, int numValores, double cotizacionActual) { // CC +1, CCog +0
		super();
		this.entidad = entidad;
		this.numValores = numValores;
		this.cotizacionActual = cotizacionActual;
	}
	
	/*
	 * CC => 1
	 * CCog => 0
	 */
	public int getNumValores() { // CC +1, CCog +0
		return numValores;
	}

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public void setNumValores(int numValores) { // CC +1, CCog +0
		this.numValores = numValores;
	}

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public double getCotizacionActual() { // CC +1, CCog +0
		return cotizacionActual;
	}

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public void setCotizacionActual(double cotizacionActual) { // CC +1, CCog +0
		this.cotizacionActual = cotizacionActual;
	}

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public String getEntidad() { // CC +1, CCog +0
		return entidad;
	}


}