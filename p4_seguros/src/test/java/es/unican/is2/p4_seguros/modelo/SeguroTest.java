package es.unican.is2.p4_seguros.modelo;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class SeguroTest {

	private Cliente clienteConMinusvalia, clienteSinMinusvalia;
	private Seguro sut;

	@Before
	public void initialize() {
		clienteConMinusvalia = new Cliente("Pedro", "123456789X", true);
		clienteSinMinusvalia = new Cliente("Alejandro", "123456789X", false);
	}

	@Test
	public void testSeguro() {

		//Válido
		try {
			sut = new Seguro(1, clienteConMinusvalia, Cobertura.TERCEROS_LUNAS);
			assertTrue(sut!=null);
		} catch (DatoIncorrectoException e) {
			fail(e.toString());
		}

		//Válido
		try {
			sut = new Seguro(1000, clienteConMinusvalia, Cobertura.TERCEROS);
			assertTrue(sut!=null);
		} catch (DatoIncorrectoException e) {
			fail(e.toString());
		}

		//Válido
		try {
			sut = new Seguro(1000, clienteSinMinusvalia, Cobertura.TODO_RIESGO);
			assertTrue(sut!=null);
		} catch (DatoIncorrectoException e) {
			fail(e.toString());
		}

		//No válido cobertura null
		try {
			sut = new Seguro(1000, clienteConMinusvalia, null);
			fail("No se ha lanzado la excepción");
		} catch (DatoIncorrectoException e) {}

		//No válido potencia 0
		try {
			sut = new Seguro(0, clienteConMinusvalia, Cobertura.TERCEROS);
			fail("No se ha lanzado la excepción");
		} catch (DatoIncorrectoException e) {}

		//No válido potencia negativa
		try {
			sut = new Seguro(-65, clienteConMinusvalia, Cobertura.TERCEROS);
			fail("No se ha lanzado la excepción");
		} catch (DatoIncorrectoException e) {}

		//No válido cliente null
		try {
			sut = new Seguro(200, null, Cobertura.TERCEROS);
			fail("No se ha lanzado la excepción");
		} catch (DatoIncorrectoException e) {}
	}

	@Test
	public void testSetFechaUltimoSiniestro() {
		//Válido
		try {
			sut = new Seguro(200, clienteConMinusvalia, Cobertura.TERCEROS_LUNAS);
			sut.setFechaUltimoSiniestro(LocalDate.now());
		} catch (DatoIncorrectoException e) {
			fail(e.toString());
		}

		//Válido
		try {
			sut = new Seguro(40, clienteConMinusvalia, Cobertura.TERCEROS);
			sut.setFechaUltimoSiniestro(LocalDate.now().minusYears(1).minusDays(1));
		} catch (DatoIncorrectoException e) {
			fail(e.toString());
		}
		
		//No válido fecha > hoy
		try {
			sut = new Seguro(1000, clienteSinMinusvalia, Cobertura.TODO_RIESGO);
			sut.setFechaUltimoSiniestro(LocalDate.now().plusDays(1));
			fail("No se ha lanzado la excepción");
		} catch (DatoIncorrectoException e) {}

		//No válido fecha > hoy
		try {
			sut = new Seguro(95, clienteConMinusvalia, Cobertura.TODO_RIESGO);
			sut.setFechaUltimoSiniestro(LocalDate.now().plusYears(2).plusDays(20));
			fail("No se ha lanzado la excepción");
		} catch (DatoIncorrectoException e) {}
	}

	@Test
	public void testPrecio() {

		//Válido
		try {
			sut = new Seguro(1, clienteConMinusvalia, Cobertura.TERCEROS_LUNAS);
			sut.setFechaUltimoSiniestro(LocalDate.now());
			assertTrue(sut.precio()==600);
		} catch (DatoIncorrectoException e) {
			fail(e.toString());
		}

		//Válido
		try {
			sut = new Seguro(89, clienteSinMinusvalia, Cobertura.TERCEROS);
			sut.setFechaUltimoSiniestro(LocalDate.now().minusDays(2));
			assertTrue(sut.precio()==600);
		} catch (DatoIncorrectoException e) {
			fail(e.toString());
		}

		//Válido
		try {
			sut = new Seguro(20, clienteSinMinusvalia, Cobertura.TODO_RIESGO);
			sut.setFechaUltimoSiniestro(LocalDate.now().minusYears(1));
			assertTrue(sut.precio()==1200);
		} catch (DatoIncorrectoException e) {
			fail(e.toString());
		}

		//Válido
		try {
			sut = new Seguro(90, clienteSinMinusvalia, Cobertura.TODO_RIESGO);
			sut.setFechaUltimoSiniestro(LocalDate.now().minusYears(1).minusDays(1));
			assertTrue(sut.precio()==1100);
		} catch (DatoIncorrectoException e) {
			fail(e.toString());
		}

		//Válido
		try {
			sut = new Seguro(110, clienteSinMinusvalia, Cobertura.TODO_RIESGO);
			sut.setFechaUltimoSiniestro(LocalDate.now().minusYears(2));
			assertTrue(sut.precio()==1100);
		} catch (DatoIncorrectoException e) {
			fail(e.toString());
		}

		//Válido
		try {
			sut = new Seguro(95, clienteConMinusvalia, Cobertura.TODO_RIESGO);
			sut.setFechaUltimoSiniestro(LocalDate.now().minusYears(3));
			assertTrue(sut.precio()==825);
		} catch (DatoIncorrectoException e) {
			fail(e.toString());
		}

		//Válido
		try {
			sut = new Seguro(111, clienteSinMinusvalia, Cobertura.TERCEROS);
			sut.setFechaUltimoSiniestro(LocalDate.now().minusYears(3).minusDays(1));
			assertTrue(sut.precio()==480);
		} catch (DatoIncorrectoException e) {
			fail(e.toString());
		}

		//Válido
		try {
			sut = new Seguro(200, clienteConMinusvalia, Cobertura.TERCEROS_LUNAS);
			sut.setFechaUltimoSiniestro(LocalDate.now().minusYears(5));
			assertTrue(sut.precio()==540);
		} catch (DatoIncorrectoException e) {
			fail(e.toString());
		}

		//Válido
		try {
			sut = new Seguro(1000, clienteSinMinusvalia, Cobertura.TODO_RIESGO);
			sut.setFechaUltimoSiniestro(null); //No ha tenido un accidente nunca
			assertTrue(sut.precio()==1200);
		} catch (DatoIncorrectoException e) {
			fail(e.toString());
		}
	}

}
