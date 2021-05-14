package es.unican.is2.practica5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/*
 * WMC => 16
 * WMCn => 16/9
 * CCog => 7
 * DIT => 1
 * NOC => 0
 * CBO => 6 (Tarjeta, Movimiento, Cuenta, CuentaAhorro, saldoInsuficienteException, datoErroneoException)
 */
public class Credito extends Tarjeta {
	
	private static final double COMISION = 0.05;
	private double credito;
	private List<Movimiento> movimientosMensuales;
	private List<Movimiento> historicoMovimientos;
	
	/*
	 * CC => 1
	 * CCog => 0
	 */
	public Credito(String numero, String titular, CuentaAhorro cuentaAhorro, double credito, LocalDate fechaCaducidad) { // CC +1, CCog +0
		super(numero, titular, cuentaAhorro, fechaCaducidad);
		this.credito = credito;
		movimientosMensuales = new LinkedList<Movimiento>();
		historicoMovimientos = new LinkedList<Movimiento>();
	}

	/*
	 * CC => 3
	 * CCog => 2
	 */
	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param importe Cantidad a retirar. Se aplica una comisi�n del 5%.
	 * @throws SaldoInsuficienteException
	 * @throws DatoErroneoException
	 */
	@Override
	public void retirar(double importe) throws SaldoInsuficienteException, DatoErroneoException { // CC +1, CCog +0
		if (importe<0) // CC +1, CCog +1
			throw new DatoErroneoException("No se puede retirar una cantidad negativa");
		
		Movimiento movimiento = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		movimiento.setFecha(now);
		movimiento.setConcepto("Retirada en cajero autom�tico");
		importe += importe * COMISION; // A�adimos una comisi�n de un 5%
		movimiento.setImporte(-importe);
		
		if (getGastosAcumulados() + importe > credito) // CC +1, CCog +1
			throw new SaldoInsuficienteException("Cr�dito insuficiente");
		else {
			movimientosMensuales.add(movimiento);
		}
	}

	/*
	 * CC => 3
	 * CCog => 2
	 */
	@Override
	public void pagoEnEstablecimiento(String datos, double importe) 
			throws SaldoInsuficienteException, DatoErroneoException { // CC +1, CCog +0
		if (importe<0) // CC +1, CCog +1
			throw new DatoErroneoException("No se puede retirar una cantidad negativa");
		
		if (getGastosAcumulados() + importe > credito) // CC +1, CCog +1
			throw new SaldoInsuficienteException("Saldo insuficiente");
		
		Movimiento movimiento = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		movimiento.setFecha(now);
		movimiento.setConcepto("Compra a cr�dito en: " + datos);
		movimiento.setImporte(-importe);
		movimientosMensuales.add(movimiento);
	}
	
	/*
	 * CC => 2
	 * CCog => 1
	 */
    public double getGastosAcumulados() { // CC +1, CCog +0
		double gastosAcumulados = 0.0;
		for (int i = 0; i < this.movimientosMensuales.size(); i++) { // CC +1, CCog +1
			Movimiento movimiento = (Movimiento) movimientosMensuales.get(i);
			gastosAcumulados += movimiento.getImporte();
		}
		return -gastosAcumulados;
	}

	/*
	 * CC => 3
	 * CCog => 2
	 */
	/**
	 * M�todo que se invoca autom�ticamente el d�a 1 de cada mes
	 */
	public void liquidar() { // CC +1, CCog +0
		Movimiento movimiento = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		movimiento.setFecha(now);
		movimiento.setConcepto("Liquidaci�n de operaciones tarjeta cr�dito");
		double importe = 0.0;
		for (int i = 0; i < this.movimientosMensuales.size(); i++) { // CC +1, CCog +1
			Movimiento m = (Movimiento) movimientosMensuales.get(i);
			importe += m.getImporte();
		}
		movimiento.setImporte(importe);
	
		if (importe != 0) // CC +1, CCog +1
			cuentaAsociada.addMovimiento(movimiento);
		
		historicoMovimientos.addAll(movimientosMensuales);
		movimientosMensuales.clear();
	}

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public List<Movimiento> getMovimientosUltimoMes() { // CC +1, CCog +0
		return movimientosMensuales;
	}
	
	/*
	 * CC => 1
	 * CCog => 0
	 */
	public List<Movimiento> getMovimientos() { // CC +1, CCog +0
		return historicoMovimientos;
	}

}