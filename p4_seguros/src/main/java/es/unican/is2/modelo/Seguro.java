package es.unican.is2.modelo;

import java.time.LocalDate;

public class Seguro {

	private LocalDate fechaUltimoSiniestro;
	private int potenciaCV;
	private Cliente tomadorSeguro;
	private Cobertura cobertura;

	public Seguro(int potencia, Cliente cliente, Cobertura cobertura) throws DatoIncorrectoException {
		if (potencia <= 0) {
			throw new DatoIncorrectoException("Potencia no puede ser negativa");
		} 
		if (cliente == null) {
			throw new DatoIncorrectoException("Cliente no puede ser null");
		}
		if (cobertura == null) {
			throw new DatoIncorrectoException("Cobertura no puede ser null");
		}
		this.potenciaCV = potencia;
		this.tomadorSeguro = cliente;
		this.cobertura = cobertura;
		fechaUltimoSiniestro = null;
	}

	public double precio() {
		double precioBase = 0;
		double precioTotal = 0;
		
		//Cobertura
		switch (cobertura) {
		case TERCEROS:
			precioBase = 400;
			break;
		case TODO_RIESGO:
			precioBase = 1000;
			break;
		case TERCEROS_LUNAS:
			precioBase = 600;
			break;
		}
		
		//Potencia
		if (potenciaCV >= 90 && potenciaCV <= 110) {
			precioTotal = precioBase + precioBase * 0.05;
		} else if (potenciaCV > 110) {
			precioTotal = precioBase + precioBase * 0.2;
		} else {
			precioTotal = precioBase;
		}
		
		//Siniestralidad
		if (fechaUltimoSiniestro != null && fechaUltimoSiniestro.isAfter(LocalDate.now().minusYears(1).minusDays(1))) {
			precioTotal += 200;
		} else if (fechaUltimoSiniestro != null && fechaUltimoSiniestro.isAfter(LocalDate.now().minusYears(3).minusDays(1)) && fechaUltimoSiniestro.isBefore(LocalDate.now().minusYears(1))) {
			precioTotal += 50;
		}
		
		//Minusvalia
		if (tomadorSeguro.getMinusvalia()) {
			precioTotal = precioTotal - precioTotal * 0.25;
		}
		
		return precioTotal;
	}

	public void setFechaUltimoSiniestro(LocalDate fechaUltimoSiniestro) throws DatoIncorrectoException {
		// Si es null, significa que nunca habrá tenido un accidente
		if (fechaUltimoSiniestro != null && fechaUltimoSiniestro.isAfter(LocalDate.now())) {
			throw new DatoIncorrectoException("La fecha del último siniestro no puede ser posterior a hoy");
		}
		this.fechaUltimoSiniestro = fechaUltimoSiniestro;
	}
}
