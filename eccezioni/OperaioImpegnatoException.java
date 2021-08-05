package eccezioni;

public class OperaioImpegnatoException extends RuntimeException{

	private static final long serialVersionUID = 7809582547981028692L;
	/**
	 * Lancia un eccezione se l'operaio è gia' impegnato
	 */
	public OperaioImpegnatoException() {
		super("l'operaio selezionato è gia impegnato");
	}

}
