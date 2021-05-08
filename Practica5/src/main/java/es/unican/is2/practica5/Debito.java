package es.unican.is2.practica5;

import java.time.LocalDate;

/*
 * WMC => 8
 * WMCn => 8/6
 */
public class Debito extends Tarjeta {
	
	private double saldoDiarioDisponible;

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public Debito(String numero, String titular, CuentaAhorro c) { // CC +1, CCog +0
		super(numero, titular, c);
	}
	
	/*
	 * CC => 2
	 * CCog => 1
	 */
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { // CC +1, CCog +0
		if (saldoDiarioDisponible<x) { // CC +1, CCog +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Retirada en cajero automático", x);
		saldoDiarioDisponible-=x;
	}
	
	/*
	 * CC => 2
	 * CCog => 1
	 */
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException { // CC +1, CCog +0
		if (saldoDiarioDisponible<x) { // CC +1, CCog +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Compra en : " + datos, x);
		saldoDiarioDisponible-=x;
	}
	
	/*
	 * CC => 1
	 * CCog => 0
	 */
	public LocalDate getCaducidadDebito() { // CC +1, CCog +0
		return this.mCuentaAsociada.getCaducidadDebito();
	}
	
	/*
	 * CC => 1
	 * CCog => 0
	 */
	/**
	 * Método invocado automáticamente a las 00:00 de cada día
	 */
	public void restableceSaldo() { // CC +1, CCog +0
		saldoDiarioDisponible = mCuentaAsociada.getLimiteDebito();
	}
	
	/*
	 * CC => 1
	 * CCog => 0
	 */
	public CuentaAhorro getCuentaAsociada() { // CC +1, CCog +0
		return mCuentaAsociada;
	}

}