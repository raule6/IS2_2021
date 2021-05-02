package es.unican.is2.modelo;

import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.unican.is2.vista.SegurosGUI;

public class SegurosGUITest {

	private FrameFixture demo;

	@Before
	public void setUp() {
		SegurosGUI gui = new SegurosGUI();
		demo = new FrameFixture(gui);
		gui.setVisible(true);	
	}

	@After
	public void tearDown() {
		demo.cleanUp();
	}

	@Test
	public void testDatoEntradaIncorrecto() {		
		// Escribimos una potencia negativa
		demo.textBox("txtPotencia").deleteText();
		demo.textBox("txtPotencia").enterText("-10");
		// Pulsamos el bot�n de minusval�a
		demo.radioButton("btnMinusvalia").click();	
		// Escribimos una fecha v�lida
		demo.textBox("txtFechaUltimoSiniestro").setText("20/04/2021");
		// Seleccionamos la cobertura
		demo.comboBox("comboCobertura").selectItem(0);
		// Pulsamos el boton
		demo.button("btnCalcular").click();
		// Comprobamos la salida
		demo.textBox("txtPrecio").requireText("�Dato de entrada err�neo!");

		// Sleep para visualizar como se realiza el test
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFormatoFechaNoValido() {		
		// Escribimos una potencia negativa
		demo.textBox("txtPotencia").deleteText();
		demo.textBox("txtPotencia").enterText("115");
		// Escribimos una fecha v�lida
		demo.textBox("txtFechaUltimoSiniestro").setText("20/04/2021X");
		// Seleccionamos la cobertura
		demo.comboBox("comboCobertura").selectItem(2);
		// Pulsamos el boton
		demo.button("btnCalcular").click();
		// Comprobamos la salida
		demo.textBox("txtPrecio").requireText("Formato de fecha no v�lido");

		// Sleep para visualizar como se realiza el test
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPrecioCorrecto() {		
		// Escribimos una potencia negativa
		demo.textBox("txtPotencia").deleteText();
		demo.textBox("txtPotencia").enterText("200");
		// Pulsamos el bot�n de minusval�a
		demo.radioButton("btnMinusvalia").click();	
		// Escribimos una fecha v�lida
		demo.textBox("txtFechaUltimoSiniestro").setText("01/05/2016");
		// Seleccionamos la cobertura
		demo.comboBox("comboCobertura").selectItem(1);
		// Pulsamos el boton
		demo.button("btnCalcular").click();
		// Comprobamos la salida
		demo.textBox("txtPrecio").requireText("540.0");

		// Sleep para visualizar como se realiza el test
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

