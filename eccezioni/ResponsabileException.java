package eccezioni;

public class ResponsabileException extends Exception{

	private static final long serialVersionUID = 822762351774009523L;
	/**
	 * Lancia un eccezione se il Dipendente inserito non è un Responsabile.
	 */
	public ResponsabileException() {
		super("Responsabile inserito non competente");
	}
}
