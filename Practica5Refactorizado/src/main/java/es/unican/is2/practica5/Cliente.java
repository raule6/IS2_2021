package es.unican.is2.practica5;

import java.util.LinkedList;
import java.util.List;

/*
 * WMC => 8
 * WMCn => 8/4 = 2
 * CCog => 10
 * DIT => 0
 * NOC => 0
 * CBO => 5 (Cuenta, Valor, CuentaValores, CuentaAhorro, Direccion)
 */
public class Cliente {
	
	protected String nombre;
	private Direccion direccion;
	protected String telefono;
	protected String dni;
	
    private List<Cuenta> cuentas = new LinkedList<Cuenta>();

    /*
	 * CC => 1
	 * CCog => 0
	 */
 	public Cliente(String titular, Direccion direccion, String telefono, String dni) {  // CC +1, CCog +0
		this.nombre = titular;
		this.direccion = direccion;
		this.telefono = telefono;
		this.dni = dni;
	}
 	
 	/*
	 * CC => 1
	 * CCog => 0
	 */
	public void cambiaDireccion(String calle, String zip, String localidad) { // CC +1, CCog +0
		this.direccion.setCalle(calle);
		this.direccion.setZip(zip);
		this.direccion.setLocalidad(localidad);
	}
	
	/*
	 * CC => 5
	 * CCog => 10
	 */
	public double getSaldoTotal() { // CC +1, CCog +0
		double total = 0.0;
		for (Cuenta c: cuentas) {  // CC +1, Ccog +1
			if (c instanceof CuentaAhorro) { // CC +1, Ccog +2
				total += ((CuentaAhorro) c).getSaldo();
			} else if (c instanceof CuentaValores)  { // CC +1, Ccog +3 (el if está anidado)
				for (Valor v: ((CuentaValores) c).getValores()) { // CC +1, Ccog +4
					total += v.getCotizacionActual()*v.getNumValores();
				}
			}
		}
		return total;
	}
	
	/*
	 * CC => 1
	 * CCog => 0
	 */
	public void anhadeCuenta(Cuenta c) { // CC +1, CCog +0
		cuentas.add(c);
	}
	
}