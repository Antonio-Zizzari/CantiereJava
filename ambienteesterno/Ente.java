package ambienteesterno;

import java.io.Serializable;

/**
 * Classe che astrae il concetto di ente
 * @author iovino,sorrrentino,zizzari
 *
 */
public final class Ente implements Cloneable,Serializable{

	private static final long serialVersionUID = 1593087362907294432L;
	private String nome;
	private String partitaIva;
	private String territorio;
	
	/**
	 * Istanzia un Ente
	 * @param nome nome dell'Ente
	 * @param partitaIva partita Iva dell'Ente
	 * @param territorio territorio in cui opera l'Ente
	 */
	public Ente(String nome, String partitaIva, String territorio) {
		this.nome = nome;
		this.partitaIva = partitaIva;
		this.territorio = territorio;
	}
	
	//Metodi accesso
	/**
	 * Restituisce il nome dell'Ente
	 * @return nome dell'Ente in formato String
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * Restituisce la partita Iva dell'Ente
	 * @return la partita Iva dell'Ente in formato String
	 */
	public String getPartitaIva() {
		return partitaIva;
	}
	/**
	 * Restituisce il territorio dell'Ente
	 * @return territorio dell'Ente in formato String
	 */
	public String getTerritorio() {
		return territorio;
	}
	
	//Overriding metodi di object
	public Ente clone() {
		try {
			return (Ente) super.clone();
		}
		catch(CloneNotSupportedException e){
			return null;
		}
	}
	
	public String toString() {
		return getClass().getName() + "[nome=" + nome + ",partita iva=" + partitaIva + ",territorio=" + territorio + "]";
	}

}
