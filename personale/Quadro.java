package personale;

/**
 * classe che astrae il concetto di Quadro di un'impresa
 * @author iovino sorrentino zizzari
 *
 */
public final class Quadro extends Dipendente{

	private static final long serialVersionUID = -1021114948000982719L;
	
	private String potereDecisionale;
	private int oreStraordinari;
	private int costoOrario;
	private int stipendio;
	/**
	 * Crea un Quadro
	 * @param nome nome Quadro
	 * @param cognome cognome Quadro
	 * @param cf codice fiscale Quadro
	 * @param saldo saldo Quadro
	 * @param potereDecisionale potere decisionale Quadro
	 * @param costoOrario costo per ora Quadro
	 * @param stipendio stipendio Quadro
	 * @param o occupazione Quadro
	 * @param oreStraordinari ore straordinarie già effettuate
	 */
	public Quadro(String nome, String cognome, String cf, int saldo, String potereDecisionale, int costoOrario, int stipendio,  boolean o, int oreStraordinari) {
		super(nome, cognome, cf, saldo, o);
		this.potereDecisionale = potereDecisionale;
		this.oreStraordinari = 0;
		this.costoOrario = costoOrario;
		this.stipendio = stipendio;
		this.oreStraordinari = oreStraordinari;
	}
	
	public String getPotereDecisionale() {
		return potereDecisionale;
	}

	public int getOreStraordinari() {
		return oreStraordinari;
	}

	public int getCostoOrario() {
		return costoOrario;
	}

	public int getStipendio() {
		return stipendio;
	}

	//Metodi modica
	/**
	 * Imposta il numero di ore totali
	 * @param n numero ore svolte da un Quadro
	 */
	public void setOre(int n) {
		oreStraordinari = n;
	}
	//Sovrastrittura metodo paga
	/**
	 * Paga un Quadro
	 */
	public void paga() {
		super.incrementaSaldo(stipendio + (oreStraordinari*costoOrario));
		oreStraordinari = 0;
	}
	//Sovrastrittura metodo toString
	public String toString() {
		return super.toString() + "[potereDecisionale=" + potereDecisionale + ",oreStraordinari=" + oreStraordinari + ",costoOrario=" + costoOrario + ",stipendio=" + stipendio +"]";
	}
	//Sovrastrittura metodo equals
	public boolean equals(Object o) {
		if(!super.equals(o)) return false;
		Quadro op = (Quadro) o;
		return op.potereDecisionale.equalsIgnoreCase(this.potereDecisionale) &&
				op.stipendio == this.stipendio &&
				op.oreStraordinari == this.oreStraordinari &&
				op.costoOrario == this.costoOrario;
	}
	//Sovrastrittura metodo clone
	public Quadro clone() {
		
		return (Quadro) super.clone();
	}

}
