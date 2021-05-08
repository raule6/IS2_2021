package es.unican.is2.practica5;

import java.util.LinkedList;
import java.util.List;

/*
 * WMC => 8
 * WMCn => 8/4 = 2
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
	public String calle;
	public String zip;
	public String localidad;
	public String telefono;
	public String dni;
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>();

    /*
	 * CC => 1
	 * CCog => 0
	 */
 	public Cliente(String titular, String calle, String zip, String localidad,  
 			String telefono, String dni) {  // CC +1, CCog +0
		this.nombre = titular;
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
		this.telefono = telefono;
		this.dni = dni;
	}
 	
 	/*
	 * CC => 1
	 * CCog => 0
	 */
	public void cambiaDireccion(String calle, String zip, String localidad) { // CC +1, CCog +0
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
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