package es.unican.is2.practica5;

/*
 * WMC => 7
 * WMCn => 7/7 = 1
 * CCog => 0
 * DIT => 0
 * NOC => 0
 * CBO => 1 (Cliente)
 */
public class Direccion {
	private String calle;
	private String zip;
	private String localidad;

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public Direccion(String calle, String zip, String localidad) { // CC +1, CCog +0
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
	}

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public String getCalle() { // CC +1, CCog +0
		return calle;
	}

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public void setCalle(String calle) { // CC +1, CCog +0
		this.calle = calle;
	}

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public String getZip() { // CC +1, CCog +0
		return zip;
	}

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public void setZip(String zip) { // CC +1, CCog +0
		this.zip = zip;
	}

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public String getLocalidad() { // CC +1, CCog +0
		return localidad;
	}

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public void setLocalidad(String localidad) { // CC +1, CCog +0
		this.localidad = localidad;
	}
}