package es.unican.is2.practica5;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/*
 * WMC => 18
 * WMCn => 18/11
 * CCog => 7
 * DIT => 1
 * NOC => 0
 * CBO => 8 (Cuenta, Cliente, Crédito, Tarjeta, Debito, Movimiento, datoErroneoException, saldoInsuficienteException)
 */
public class CuentaAhorro extends Cuenta {

	private List<Movimiento> movimientos;
	//private LocalDate fechaDeCaducidadTarjetaDebito;
	//private LocalDate fechaDeCaducidadTarjetaCredito;
	private double limiteDebito;

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public CuentaAhorro(String numCuenta/*, LocalDate date, LocalDate date2*/) { // CC +1, CCog +0
		super(numCuenta);
		//this.fechaDeCaducidadTarjetaDebito = date;
		//this.fechaDeCaducidadTarjetaCredito = date2;
		movimientos = new LinkedList<Movimiento>();
		limiteDebito = 1000;
	}

	/*
	 * CC => 2
	 * CCog => 1
	 */
	public void ingresar(String concepto, double importe) throws DatoErroneoException { // CC +1, CCog +0
		if (importe <= 0) // CC +1, CCog +1
			throw new DatoErroneoException("No se puede ingresar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		if (concepto == null) {
			m.setConcepto("Ingreso en efectivo");
		} else {
			m.setConcepto(concepto);
		}
		m.setImporte(importe);
		this.movimientos.add(m);
	}
	
	/*
	 * CC => 3
	 * CCog => 2
	 */
	public void retirar(String concepto, double importe) throws SaldoInsuficienteException, DatoErroneoException { // CC +1, CCog +0
		if (getSaldo() < importe) // CC +1, CCog +1
			throw new SaldoInsuficienteException("Saldo insuficiente");
		if (importe <= 0) // CC +1, CCog +1
			throw new DatoErroneoException("No se puede retirar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		if (concepto == null) {
			m.setConcepto("Retirada de efectivo");
		} else {
			m.setConcepto(concepto);
		}
		m.setImporte(-importe);
		this.movimientos.add(m);
	}

	/*
	 * CC => 2
	 * CCog => 1
	 */
	public double getSaldo() { // CC +1, CCog +0
		double saldo = 0.0;
		for (int i = 0; i < this.movimientos.size(); i++) { // CC +1, CCog +1
			Movimiento m = (Movimiento) movimientos.get(i);
			saldo += m.getImporte();
		}
		return saldo;
	}

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public void addMovimiento(Movimiento m) { // CC +1, CCog +0
		movimientos.add(m);
	}

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public List<Movimiento> getMovimientos() { // CC +1, CCog +0
		return movimientos;
	}

	

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public double getLimiteDebito() { // CC +1, CCog +0
		return limiteDebito;
	}

}