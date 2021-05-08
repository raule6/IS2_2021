package es.unican.is2.p4_seguros.containers;

import static org.junit.Assert.*;

import org.junit.Test;


public class ListaOrdenadaTest {

	private ListaOrdenada<Integer> lista;

	@Test
	public void testSize() {

		// V�lido
		try {
			lista = new ListaOrdenada<Integer>();
			assertTrue(lista.size() == 0);
		} catch (Exception e) {
			fail(e.toString());
		}

		// V�lido
		try {
			lista.add(4);
			assertTrue(lista.size() == 1);
		} catch (Exception e) {
			fail(e.toString());
		}

		// V�lido
		try {
			lista.add(7);
			lista.add(12);
			assertTrue(lista.size() == 3);
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void testClear() {
		// V�lido
		try {
			lista = new ListaOrdenada<Integer>();
			lista.clear();
			assertTrue(lista.size() == 0);
		} catch (Exception e) {
			fail(e.toString());
		}

		// V�lido
		try {
			lista.add(4);
			lista.clear();
			assertTrue(lista.size() == 0);
		} catch (Exception e) {
			fail(e.toString());
		}

		// V�lido
		try {
			lista.add(4);
			lista.add(7);
			lista.add(12);
			lista.clear();
			assertTrue(lista.size() == 0);
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void testAddGet() {

		// V�lido
		try {
			lista = new ListaOrdenada<Integer>();
			lista.add(5);
			assertTrue(lista.get(0) == 5);
		} catch (Exception e) {
			fail(e.toString());
		}

		// V�lido
		try {
			lista.add(2);
			assertTrue(lista.get(0) == 2);
		} catch (Exception e) {
			fail(e.toString());
		}

		// V�lido
		try {
			lista.add(4);
			assertTrue(lista.get(1) == 4);
		} catch (Exception e) {
			fail(e.toString());
		}

		// No V�lido add(null) en [2,4,5]
		try {
			lista.add(null);
			fail("No se ha detectado la excepci�n");
		} catch (NullPointerException e) {}

		// No V�lido get(2) en []
		try {
			lista = new ListaOrdenada<Integer>();
			lista.get(2);
			fail("No se ha detectado la excepci�n");
		} catch (IndexOutOfBoundsException e) {}

		// No V�lido get(-2) en [2,3,5]
		try {
			lista = new ListaOrdenada<Integer>();
			lista.add(2);
			lista.add(3);
			lista.add(5);
			lista.get(-2);
			fail("No se ha detectado la excepci�n");
		} catch (IndexOutOfBoundsException e) {}

	}

	@Test
	public void testRemove() {
		// V�lido
		try {
			lista = new ListaOrdenada<Integer>();
			lista.add(4);
			int elem = lista.remove(0);
			assertTrue(elem == 4);
			assertTrue(lista.size() == 0);
		} catch (Exception e) {
			fail(e.toString());
		}

		// V�lido
		try {
			lista = new ListaOrdenada<Integer>();
			lista.add(2);
			lista.add(5);
			lista.add(9);
			int elem = lista.remove(1);
			assertTrue(elem == 5);
			assertTrue(lista.size() == 2);
		} catch (Exception e) {
			fail(e.toString());
		}
		
		// No V�lido remove(4) en []
		try {
			lista = new ListaOrdenada<Integer>();
			lista.remove(4);
			fail("No se ha detectado la excepci�n");
		} catch (IndexOutOfBoundsException e) {}
		
		// No V�lido remove(null) en [2,5,9]
		try {
			lista = new ListaOrdenada<Integer>();
			lista.add(2);
			lista.add(5);
			lista.add(9);
			lista.remove(-2);
			fail("No se ha detectado la excepci�n");
		} catch (Exception e) {}
		
	}





}
