package personale;

/**
 * classe che astrae il concetto di Dirigente di un'impresa
 * @author iovino sorrentino zizzari
 *
 */
public final class Dirigente extends Dipendente{

	private static final long serialVersionUID = 1939363495317638456L;
	
	private int anniEsperienza;
	private int stipendio;
	/**
	 * Crea un Dirigente
	 * @param nome nome del Dirigente
	 * @param cognome cognome del Dirigente
	 * @param cf codice fiscale del Dirigente
	 * @param saldo saldo del Dirigente
	 * @param anniEsperienza anni esperienza del Dirigente
	 * @param stipendio stipendio del Dirigente
	 * @param o occupazione Dirigente
	 */
	public Dirigente(String nome, String cognome, String cf, int saldo, int anniEsperienza, int stipendio, boolean o) {
		super(nome, cognome, cf, saldo, o);
		this.anniEsperienza = anniEsperienza;
		this.stipendio = stipendio;
	}
	
	public int getAnniEsperienza() {
		return anniEsperienza;
	}
	
	public int getStipendio() {
		return stipendio;
	}
	//Sovrastrittura metodo paga
	/**
	 * Pagamento Dirigente
	 */
	public void paga() {
		int bonus = stipendio * (anniEsperienza/100); 
		super.incrementaSaldo(stipendio + bonus);
	}
	//Sovrastrittura metodo toString
	public String toString() {
		return super.toString() + "[anniEsperienza=" + anniEsperienza + ",stipendio=" + stipendio + "]";
	}
	//Sovrastrittura metodo equals
	public boolean equals(Object o) {
		if(!super.equals(o)) return false;
		Dirigente op = (Dirigente) o;
		return op.anniEsperienza == this.anniEsperienza &&
				op.stipendio == this.stipendio ;
	}
	//Sovrastrittura metodo clone
	public Dirigente clone() {
		
		return (Dirigente) super.clone();
	}
	
}
