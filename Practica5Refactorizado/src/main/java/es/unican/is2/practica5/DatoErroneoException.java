package es.unican.is2.practica5;

/*
 * CBO => 4 (CuentaAhorro, Credito, Debito, Tarjeta)
 */
@SuppressWarnings("serial")
public class DatoErroneoException extends RuntimeException {
	
	public DatoErroneoException (String mensaje) { // CC +1, CCog +0
		super(mensaje);
	}

}
