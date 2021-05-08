package es.unican.is2.p4_seguros.modelo;

@SuppressWarnings("serial")
public class DatoIncorrectoException extends Exception {
	
	public DatoIncorrectoException(String errorMessage) {
        super(errorMessage);
    }
	
}
