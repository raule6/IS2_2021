package es.unican.is2.p4_seguros.modelo;

public class Cliente {
	
	private String nombre;
	private String dni;
	private boolean minusvalia;
	
	public Cliente(String nombre, String dni, boolean minusvalia) {
		this.nombre = nombre;
		this.dni = dni;
		this.minusvalia = minusvalia;
	}

	public boolean getMinusvalia() {
		return minusvalia;
	}

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}
	
}
