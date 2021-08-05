package amministrativo;
import ambienteesterno.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe che permette l'interazione con l'ambiente esterno (clienti,enti e fornitori)
 * @author iovino,sorrrentino,zizzari
 *
 */
public final class InterazioneEsterno implements Serializable{

	private static final long serialVersionUID = -79870606751691841L;
	private ArrayList<Cliente> clienti;
	private ArrayList<Ente> enti;
	private ArrayList<Fornitore> fornitori;
	
	/**
	 * Istanzia un oggetto di tipo InterazioneEsterno inizializzando i 3 arraylist utilizzati per la gestione dell'ambiente
	 */
	public InterazioneEsterno() {
		clienti = new ArrayList<Cliente>();
		enti = new ArrayList<Ente>();
		fornitori = new ArrayList<Fornitore>(); 
	}
	
	//metodi di aggiunta
	
	/**
	 * Aggiunge un Cliente al sistema
	 * @param c Cliente da aggiungere
	 */
	public void addCliente(Cliente c) {
		for(Cliente temp: clienti)
			if(temp.getCf().equalsIgnoreCase(c.getCf()))
				return;
		clienti.add(c);
	}
	
	/**
	 * Aggiunge un Ente al sistema
	 * @param e Ente da aggiungere
	 */
	public void addEnte(Ente e) {
		for(Ente temp: enti)
			if(temp.getPartitaIva().equalsIgnoreCase(e.getPartitaIva()))
				return;
		enti.add(e);
	}
	
	/**
	 * Aggiunge un Fornitore al sistema
	 * @param f Fornitore da aggiungere
	 */
	public void addFornitore(Fornitore f) {
		for(Fornitore temp: fornitori)
			if(temp.getPartitaIva().equalsIgnoreCase(f.getPartitaIva()))
				return;
		fornitori.add(f);
	}
	
	//Metodi rimozione
	/**
	 * Rimuove un Cliente cercandolo tramite codice fiscale e
	 * restituisce il Cliente eliminato
	 * @param cf codice fiscale del Cliente
	 * @return il Cliente eliminato, null altrimenti
	 */
	public Cliente removeCliente(String cf) {
		int i = 0;
		for(Cliente temp: clienti) {
			if(temp.getCf().equalsIgnoreCase(cf))
				return clienti.remove(i);
			i++;
		}
		return null;
	}
	/**
	 * Rimuove un Ente cercandolo tramite partita Iva e
	 * restituisce l'Ente eliminato
	 * @param pIva partita Iva dell'Ente
	 * @return l'Ente eliminato, null altrimenti
	 */	
	public Ente removeEnte(String pIva) {
		int i = 0;
		for(Ente temp: enti) {
			if(temp.getPartitaIva().equalsIgnoreCase(pIva))
				return enti.remove(i);
			i++;
		}
		return null;
	}
	/**
	 * Rimuove un Fornitore cercandolo tramite partita Iva e
	 * restituisce il Fornitore eliminato
	 * @param pIva partita Iva del Fornitore
	 * @return il Fornitore eliminato, null altrimenti
	 */
	public Fornitore removeFornitore(String pIva) {
		int i = 0;
		for(Fornitore temp: fornitori) {
			if(temp.getPartitaIva().equalsIgnoreCase(pIva))
				return fornitori.remove(i);
			i++;
		}
		return null;
	}
	
	//Metodi accesso
	/**
	 * Restituisce la lista dei Clienti
	 * @return ArrayList dei Clienti
	 */
	public ArrayList<Cliente> getClienti(){
		return clienti;
	}
	/**
	 * Restituisce la lista degli Enti
	 * @return ArrayList degli Enti
	 */
	public ArrayList<Ente> getEnti(){
		return enti;
	}
	/**
	 * Restituisce la lista dei Fornitori
	 * @return ArrayList dei Fornitori
	 */
	public ArrayList<Fornitore> getFornitori(){
		return fornitori;
	}
	
	//Metodi utili per la grafica
	
	/**
	 * Ritorna una stringa contenente le informazioni principali dei Fornitori (tramite toString)
	 * @return una String formattata: "Nome partitaIva indirizzo\n", altrimenti ""
	 */
	public String StringArrayFornitori() {
		String s = "";
		for(Fornitore temp: fornitori)
			s += temp.toString() + "\n";
		return s;
	}
	/**
	 * Ritorna una stringa contenente le informazioni principali dei Clienti (tramite toString)
	 * @return una String formattata: "Nome Cognome codiceFiscale\n" ,altrimenti ""
	 */
	public String StringArrayCliente() {
		String s = "";
		for(Cliente temp: clienti)
			s += temp.toString() + "\n";
		return s;
	}
	/**
	 * Ritorna una stringa delle contenente le informazioni principali degli Enti (tramite toString)
	 * @return una String formattata: "Nome partitaIva territorio\n", altrimenti ""
	 */
	public String StringArrayEnti() {
		String s = "";
		for(Ente temp: enti)
			s += temp.toString() + "\n";
		return s;
	}
 
}
