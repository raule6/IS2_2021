package es.unican.is2.practica5;

/*
 * CBO => 4 (Crédito, Débito, Tarjeta, CuentaAhorro)
 */
@SuppressWarnings("serial")
public class saldoInsuficienteException extends RuntimeException {

	public saldoInsuficienteException (String mensaje) { // CC +1, CCog +0
		super(mensaje);
	}
}
