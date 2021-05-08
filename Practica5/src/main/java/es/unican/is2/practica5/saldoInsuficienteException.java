package es.unican.is2.practica5;

/*
 * WMC => 1
 * WMCn => 1/1
 */
@SuppressWarnings("serial")
public class saldoInsuficienteException extends RuntimeException {

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public saldoInsuficienteException (String mensaje) { // CC +1, CCog +0
		super(mensaje);
	}
}
