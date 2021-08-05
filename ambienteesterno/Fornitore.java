package ambienteesterno;
import java.io.Serializable;

/**
 * Classe che astrae il concetto di Fornitore
 * @author iovino,sorrrentino,zizzari
 *
 */
public final class Fornitore implements Cloneable,Serializable{

	private static final long serialVersionUID = -295615055769749694L;
	private String nome;
	private String partitaIva;
	private String indirizzo;
	
	/**
	 * Istanzia un Fornitore
	 * @param nome nome del Fornitore
	 * @param partitaIva partita Iva del Fornitore
	 * @param indirizzo indirizzo del Fornitore
	 */
	public Fornitore(String nome, String partitaIva, String indirizzo) {
		this.nome = nome;
		this.partitaIva = partitaIva;
		this.indirizzo = indirizzo;
	}
	//Metodi accesso
	/**
	 * Restituisce il nome del Fornitore
	 * @return nome Fornitore in formato String
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * Restituisce la partita Iva del Fornitore
	 * @return partita Iva del Fornitore in formato String
	 */
	public String getPartitaIva() {
		return partitaIva;
	}
	/**
	 * Restituisce l'indirizzo del Fornitore
	 * @return indirizzo del fornitore in formato String
	 */
	public String getIndirizzo() {
		return indirizzo;
	}
	
	//Overriding metodi di object
	public Fornitore clone() {
		try {
			return (Fornitore) super.clone();
		}
		catch(CloneNotSupportedException e){
			return null;
		}
	}
	
	public String toString() {
		return getClass().getName() + "[nome=" + nome + ",partita iva=" + partitaIva + ",indirizzo=" + indirizzo + "]";
	}
	
}
