package es.unican.is2.practica5;

/*
 * WMC => 2
 * WMCn => 2/2
 * CCog => 0
 * DIT => 0
 * NOC => 2
 * CBO => 4 (CuentaAhorro, CuentaValores, Cliente, Crédito)
 */
public abstract class Cuenta {
	
	private String numCuenta;
	
	/*
	 * CC => 1
	 * CCog => 0
	 */
	protected Cuenta(String numCuenta) { // CC +1, CCog +0
		this.numCuenta = numCuenta;
	}
	
	/*
	 * CC => 1
	 * CCog => 0
	 */
	public String getNumCuenta() { // CC +1, CCog +0
		return numCuenta;
	}
	
	
}
