package personale;

/**
 * classe che astrae il concetto di Operaio di un'impresa
 * @author iovino sorrentino zizzari
 *
 */
public final class Operaio extends Dipendente{

	private static final long serialVersionUID = -5525454094298412594L;
	
	private String caratteristica;
	private int numOre;
	private int costoOrario;
	/**
	 * Crea un nuovo Operaio
	 * @param nome nome Operaio
	 * @param cognome cognome Operario
	 * @param cf codice fiscale Operaio
	 * @param caratteristica caratteristica Operaio
	 * @param saldo saldo Operaio
	 * @param costoOrario costo per ora Operaio
	 * @param o occupazione Operaio
	 */
	public Operaio(String nome, String cognome, String cf, String caratteristica, int saldo, int costoOrario, boolean o) {
		super(nome, cognome, cf, saldo, o);
		this.caratteristica = caratteristica;
		this.costoOrario = costoOrario;
		numOre = 0;
	}
	
	public String getCaratteristica() {
		return caratteristica;
	}


	public int getCostoOrario() {
		return costoOrario;
	}

	public int getNumOre() {
		return numOre;
	}

	//Metodi accesso
	/**
	 * Imposta il numero ore totali
	 * @param numOre numero ore Operaio
	 */
	public void setNumOre(int numOre) {
		this.numOre = numOre;
	}
	//Sovrastrittura metodo paga
	/**
	 * Paga Operaio
	 */
	public void paga() {
		int ammontare = numOre * costoOrario;
		super.incrementaSaldo(ammontare);
		numOre = 0;
	}
	//Sovrastrittura metodo toString
	public String toString() {
		return super.toString() + "[caratteristica=" + caratteristica + ",numOre=" + numOre + ",costoOrario" + costoOrario + "]";
	}
	//Sovrastrittura metodo equals
	public boolean equals(Object o) {
		if(!super.equals(o)) return false;
		Operaio op = (Operaio) o;
		return op.caratteristica.equalsIgnoreCase(this.caratteristica) &&
				op.costoOrario == this.costoOrario &&
				op.numOre == this.numOre;
	}
	//Sovrastrittura metodo clone
	public Operaio clone() {
		
		return (Operaio) super.clone();
	}

}
