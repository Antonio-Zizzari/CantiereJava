package personale;

/**
 * classe che astrae il concetto di Impiegato di un'impresa
 * @author iovino sorrentino zizzari
 *
 */
public final class Impiegato extends Dipendente{

	private static final long serialVersionUID = -8704436756142243915L;
	
	private String ruolo;
	private int stipendio;
	/**
	 * Crea un Impiegato
	 * @param nome nome dell'Impiegato
	 * @param cognome cognome dell'Impiegato
	 * @param cf codice fiscale Impiegato
	 * @param saldo saldo Impiegato
	 * @param ruolo ruovo Impiegato
	 * @param stipendio Stipendio Impiegato
	 * @param o occupazione Impiegato
	 */
	public Impiegato(String nome, String cognome, String cf, int saldo, String ruolo, int stipendio, boolean o) {
		super(nome, cognome, cf, saldo, o);
		this.stipendio = stipendio;
		this.ruolo = ruolo;
	}
	
	public String getRuolo() {
		return ruolo;
	}


	public int getStipendio() {
		return stipendio;
	}

	//Sovrastrittura metodo paga
	/**
	 * Paga l'Impiegato
	 */
	public void paga() {
		super.incrementaSaldo(stipendio);
	}
	//Sovrastrittura metodo toString
	public String toString() {
		return super.toString() + "[ruolo=" + ruolo + ",stipendio=" + stipendio + "]";
	}
	//Sovrastrittura metodo equals
	public boolean equals(Object o) {
		if(!super.equals(o)) return false;
		Impiegato op = (Impiegato) o;
		return op.ruolo.equalsIgnoreCase(this.ruolo) &&
				op.stipendio == this.stipendio ;
	}
	//Sovrastrittura metodo clone
	public Impiegato clone() {
		
		return (Impiegato) super.clone();
	}
	

}
