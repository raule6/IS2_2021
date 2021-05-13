package es.unican.is2.practica5;

/*
 * WMC => 1
 * WMCn => 1/1
 * CCog => 0
 * DIT => 0
 * NOC => 2
 * CBO => 5 (CuentaAhorro, Crédito, Debito, datoErroneoException, saldoInsuficienteException)
 */
public abstract class Tarjeta {
	protected String numero, titular;		
	protected CuentaAhorro cuentaAsociada;

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public Tarjeta(String numero, String titular, CuentaAhorro cuentaAhorro) { // CC +1, CCog +0
		this.numero = numero;
		this.titular = titular;
		this.cuentaAsociada = cuentaAhorro;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param importe Cantidad a retirar. 
	 * @throws SaldoInsuficienteException
	 * @throws DatoErroneoException
	 */
	public abstract void retirar(double importe) throws SaldoInsuficienteException, DatoErroneoException;

	/**
	 * Pago en establecimiento con la tarjeta
	 * @param datos Concepto del pago
	 * @param importe Cantidada a pagar
	 * @throws SaldoInsuficienteException
	 * @throws DatoErroneoException
	 */
	public abstract void pagoEnEstablecimiento(String datos, double importe)
			throws SaldoInsuficienteException, DatoErroneoException;

	public Cuenta getCuentaAsociada() { // CC +1, CCog +0
		return cuentaAsociada;
	}
	
}