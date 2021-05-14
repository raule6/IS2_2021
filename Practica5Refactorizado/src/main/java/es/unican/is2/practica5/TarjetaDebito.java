package es.unican.is2.practica5;

import java.time.LocalDate;

/*
 * WMC => 8
 * WMCn => 8/6
 * CCog => 2
 * DIT => 1
 * NOC => 0
 * CBO => 4 (Crédito, Tarjeta, datoErroneoException, saldoInsuficienteException)
 */
public class Debito extends Tarjeta {
	
	private double saldoDiarioDisponible;

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public Debito(String numero, String titular, CuentaAhorro cuentaAhorro, LocalDate fechaCaducidad) { // CC +1, CCog +0
		super(numero, titular, cuentaAhorro, fechaCaducidad);
	}
	
	/*
	 * CC => 2
	 * CCog => 1
	 */
	@Override
	public void retirar(double importe) throws SaldoInsuficienteException, DatoErroneoException { // CC +1, CCog +0
		if (saldoDiarioDisponible < importe) { // CC +1, CCog +1
			throw new SaldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Retirada en cajero automático", importe);
		saldoDiarioDisponible -= importe;
	}
	
	/*
	 * CC => 2
	 * CCog => 1
	 */
	@Override
	public void pagoEnEstablecimiento(String datos, double importe) throws SaldoInsuficienteException, DatoErroneoException { // CC +1, CCog +0
		if (saldoDiarioDisponible<importe) { // CC +1, CCog +1
			throw new SaldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Compra en : " + datos, importe);
		saldoDiarioDisponible -= importe;
	}
	
	/*
	 * CC => 1
	 * CCog => 0
	 */
	/**
	 * Método invocado automáticamente a las 00:00 de cada día
	 */
	public void restableceSaldo() { // CC +1, CCog +0
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito();
	}
}