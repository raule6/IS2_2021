package es.unican.is2.practica5;

import java.time.LocalDate;

/*
 * WMC => 3
 * WMCn => 3/3 = 1
 * CCog => 0
 * DIT => 0
 * NOC => 2
 * CBO => 6 (CuentaAhorro, Crédito, Debito, DatoErroneoException, SaldoInsuficienteException, Cuenta)
 */
public abstract class Tarjeta {
	protected String numero;
	protected String titular;		
	protected CuentaAhorro cuentaAsociada;
	private LocalDate fechaDeCaducidad;

	/*
	 * CC => 1
	 * CCog => 0
	 */
	protected Tarjeta(String numero, String titular, CuentaAhorro cuentaAhorro, LocalDate fechaCaducidad) { // CC +1, CCog +0
		this.numero = numero;
		this.titular = titular;
		this.cuentaAsociada = cuentaAhorro;
		this.fechaDeCaducidad = fechaCaducidad;
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
	
	/*
	 * CC => 1
	 * CCog => 0
	 */
	public Cuenta getCuentaAsociada() { // CC +1, CCog +0
		return cuentaAsociada;
	}
	
	/*
	 * CC => 1
	 * CCog => 0
	 */
	public LocalDate getCaducidad() { // CC +1, CCog +0
		return this.fechaDeCaducidad;
	}
}