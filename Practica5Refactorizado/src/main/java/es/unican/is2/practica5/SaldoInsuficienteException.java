package es.unican.is2.practica5;

/*
 * CBO => 4 (Cr�dito, D�bito, Tarjeta, CuentaAhorro)
 */
@SuppressWarnings("serial")
public class SaldoInsuficienteException extends RuntimeException {

	public SaldoInsuficienteException (String mensaje) { // CC +1, CCog +0
		super(mensaje);
	}
}
