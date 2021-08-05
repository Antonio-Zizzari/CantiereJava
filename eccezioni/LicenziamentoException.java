package eccezioni;

public class LicenziamentoException extends Exception{

	private static final long serialVersionUID = -1763859232290206988L;

	public LicenziamentoException() {
		super("Dipendente che si vuole licenziare impegnato in un lavoro.");
	}
}
