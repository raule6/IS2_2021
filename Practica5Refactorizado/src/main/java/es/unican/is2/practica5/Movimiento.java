package es.unican.is2.practica5;

import java.time.LocalDateTime;

/*
 * WMC => 6
 * WMCn => 6/6
 * CCog => 0
 * DIT => 0
 * NOC => 0
 * CBO => 2 (Crédito, CuentaAhorro)
 */
public class Movimiento {
	private String mConcepto;
	private LocalDateTime mFecha;
	private double mImporte;

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public double getI() { // CC +1, CCog +0
		return mImporte;
	}

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public String getC() { // CC +1, CCog +0
		return mConcepto;
	}

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public void setC(String newMConcepto) { // CC +1, CCog +0
		mConcepto = newMConcepto;
	}

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public LocalDateTime getF() { // CC +1, CCog +0
		return mFecha;
	}

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public void setF(LocalDateTime newMFecha) { // CC +1, CCog +0
		mFecha = newMFecha;
	}

	/*
	 * CC => 1
	 * CCog => 0
	 */
	public void setI(double newMImporte) { // CC +1, CCog +0
		mImporte = newMImporte;
	}
}