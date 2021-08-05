package ambienteesterno;

import java.io.Serializable;

/**
 * Classe che astrae il concetto di cliente
 * @author iovino,sorrrentino,zizzari
 *
 */
public final class Cliente implements Cloneable,Serializable{

	private static final long serialVersionUID = 4215994697224693499L;
	private String nome;
	private String cognome;
	private String cf;
	
	/**
	 * Istanzia un Cliente
	 * @param nome nome del Cliente
	 * @param cognome cognome del Cliente
	 * @param cf codice fiscale costituito da 16 caratteri
	 */
	public Cliente(String nome, String cognome, String cf) {
		this.nome = nome;
		this.cognome = cognome;
		this.cf = cf;
	}
	
	//Metodi accesso
	/**
	 * Fornisce il nome di un Cliente
	 * @return il nome del Cliente, tipo String
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Fornisce il cognome di un Cliente
	 * @return il cognome del Cliente, tipo String
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * Fornisce il codice fiscale di un Cliente
	 * @return il codice fiscale del Cliente, tipo String 
	 */
	public String getCf() {
		return cf;
	}
	
	//overriding metodi object
	public Cliente clone() {
		try {
			return (Cliente) super.clone();
		}
		catch(CloneNotSupportedException e) {
			return null;
		}
	}
	
	public String toString() {
		return getClass().getName() + "[nome=" + nome + ",cognome=" + cognome + ",cf=" + cf + "]";
	}
	
}
