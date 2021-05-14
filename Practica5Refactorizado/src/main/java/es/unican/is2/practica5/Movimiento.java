package es.unican.is2.practica5;

import java.time.LocalDateTime;

/*
 * WMC => 6
 * WMCn => 6/6 = 1
 * CCog => 0
 * DIT => 0
 * NOC => 0
 * CBO => 2 (Crédito, CuentaAhorro)
 */
public class Movimiento {
	private String concepto;
	private LocalDateTime fecha;
	private double importe;

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public double getImporte() { // CC +1, CCog +0
		return importe;
	}

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public String getConcepto() { // CC +1, CCog +0
		return concepto;
	}

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public void setConcepto(String newMConcepto) { // CC +1, CCog +0
		concepto = newMConcepto;
	}

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public LocalDateTime getFecha() { // CC +1, CCog +0
		return fecha;
	}

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public void setFecha(LocalDateTime newMFecha) { // CC +1, CCog +0
		fecha = newMFecha;
	}

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public void setImporte(double newMImporte) { // CC +1, CCog +0
		importe = newMImporte;
	}
}