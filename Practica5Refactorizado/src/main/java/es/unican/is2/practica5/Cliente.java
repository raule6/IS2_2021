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
	/*
	 * CC suma método, condición o bucle
	 * CCog no suma método y lo demás según su anidamiento +1, +2, +3 etc
	 * 
	 * La CC es nº de condiciones +1
	 * La CCog varía según tipo
	 * if (a & b) CC +2 CCog +2
	 * if (a & b | c) CC +3, CCog +3
	 * if (a & b & c) CC +3, CCog +2
	 */
	
	public String nombre;
	public Direccion direccion;
	public String telefono;
	public String dni;
	
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>();

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
		for (Cuenta c: Cuentas) {  // CC +1, Ccog +1
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
		Cuentas.add(c);
	}
	
}