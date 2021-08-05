package eccezioni;

public class InvalidQuadroException extends RuntimeException{

	private static final long serialVersionUID = -1228509042703364376L;
	/**
	 * Lancia un eccezione se il caposquadra selezionato non è un Quadro
	 */
	public InvalidQuadroException() {
		super("il caposquadra selezionato non è un quadro");
	}
}
