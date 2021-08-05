package personale;

import java.io.Serializable;

/**
 * classe astratta che astrae il concetto di dipendente
 * @author davide
 *
 */
public abstract class Dipendente implements Cloneable,Serializable{

	private static final long serialVersionUID = -2807468659911363072L;
	
	private String nome;
	private String cognome;
	private String cf;
	private int saldo;
	private boolean occupazione;
	/**
	 * Istanzia un Dipendente
	 * @param nome nome del Dipendente
	 * @param cognome cognome del Dipendente
	 * @param cf codice fiscale del Dipendente
	 * @param saldo saldo del Dipendente
	 * @param occupazione se è occupato in una Squadra
	 */
	public Dipendente(String nome, String cognome, String cf, int saldo, boolean occupazione) {
		this.nome = nome;
		this.cognome = cognome;
		this.cf = cf;
		this.saldo = saldo;
		this.occupazione = occupazione;
	}
	//Metodi accesso
	/**
	 * Restituisce il nome del Dipendente
	 * @return nome del Dipendente
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * Restituisce il cognome del Dipendente
	 * @return cognome del Dipendente
	 */
	public String getCognome() {
		return cognome;
	}
	/**
	 * Restituisce il codice fiscale del Dipendente
	 * @return codice fiscale del Dipendente
	 */
	public String getCf() {
		return cf;
	}
	/**
	 * Restituisce il saldo del Dipendente
	 * @return saldo del Dipendente
	 */
	public int getSaldo() {
		return saldo;
	}
	/**
	 * Restituisce l'occupazione del Dipendente
	 * @return true se occupato in una Squadra, false altrimenti
	 */
	public boolean getOccupazione() {
		return occupazione;
	}

	/**
	 * Cambia l'occupazione del Dipendente
	 * @param o nuova occupazione del Dipendente
	 */
	public void setOccupazione(boolean o) {
		this.occupazione = o;
	}
	/**
	 * Aumenta il saldo del Dipendente
	 * @param n soldi da aggiungere al saldo
	 */
	public void incrementaSaldo(int n) {
		this.saldo+=n;
	}
	
	/**
	 * Metodo pagamento
	 */
	public abstract void paga();
	
	//Sovrastittura metodi toString
	public String toString() {
		return getClass().getName() + "[nome=" + nome + ",cognome=" + cognome + ",cf=" + cf + ",saldo=" + saldo + ",occupazione=" + occupazione + "]";
	}
	
	//Sovrastrittura metodo equals
	public boolean equals(Object o) {
		if(o == null)	return false;
		if(o.getClass() != getClass()) return false;
		Dipendente d = (Dipendente) o;
		return d.cf.equalsIgnoreCase(this.cf) &&
				d.cognome.equalsIgnoreCase(this.cognome) &&
				d.nome.equalsIgnoreCase(this.nome) &&
				d.saldo == this.saldo &&
				d.occupazione == this.occupazione;
	}
	
	//Sovrastrittura metodo clone
	public Dipendente clone() {
		try {
			return (Dipendente) super.clone();
		}
		catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
}
