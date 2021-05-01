package es.unican.is2.modelo;

@SuppressWarnings("serial")
public class DatoIncorrectoException extends Exception {
	
	public DatoIncorrectoException(String errorMessage) {
        super(errorMessage);
    }
	
}
