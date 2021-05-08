package es.unican.is2.practica5;

import java.util.List;

/*
 * WMC => 3
 * WMCn => 3/3
 */
public class CuentaValores extends Cuenta {

	private List<Valor> valores;
	
	/*
	 * CC => 1
	 * CCog => 0
	 */
	public CuentaValores(String numCuenta, List<Valor> valores) { // CC +1, CCog +0
		super(numCuenta);
		this.valores = valores;
	}
	
	/*
	 * CC => 1
	 * CCog => 0
	 */
	public List<Valor> getValores() { // CC +1, CCog +0
		return valores;
	}
	
	/*
	 * CC => 1
	 * CCog => 0
	 */
	public void anhadeValor(Valor v) { // CC +1, CCog +0
		valores.add(v);
	}
	
	
}
