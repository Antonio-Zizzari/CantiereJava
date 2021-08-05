package amministrativo;
import java.io.Serializable;
import java.util.ArrayList;

import eccezioni.LicenziamentoException;
import personale.*;

/**
 * Classe che permette l'interazione con l'ambiente interno (dipendenti)
 * @author iovino,sorrrentino,zizzari
 *
 */
public final class InterazioneInterna implements Serializable{

	private static final long serialVersionUID = 8554893535723387584L;
	
	private ArrayList<Dipendente> dipendenti;
	
	/**
	 * Istanzia un oggetto di tipo InterazioneInterna inizializzando l'arraylist per contenere i dipendenti
	 */
	public InterazioneInterna() {
		dipendenti = new ArrayList<Dipendente>();
	}
	
	//Metodi accesso
	/**
	 * Restituisce un Dipendente cercandolo tramite codice fiscale e
	 * restituisce il Dipendente se lo trova, null altrimenti
	 * @param cf codice fiscale del Dipendente
	 * @return il Dipendente, null altrimenti
	 */
	public Dipendente getDipendente(String cf) {
		for(Dipendente x: dipendenti)
			if(x.getCf().equalsIgnoreCase(cf))
				return x;
		return null;
	}
	/**
	 * Restituisce la lista dei Dipendenti
	 * @return ArrayList Dipendenti
	 */
	public ArrayList<Dipendente> getDipendenti() {
		return dipendenti;
	}
	
	/**
	 * Aggiunge un dipendente al sistema
	 * @param d Dipendente da aggiungere
	 */
	public void assumiDipendente(Dipendente d) {
		for(Dipendente temp: dipendenti)
			if(temp.getCf().equalsIgnoreCase(d.getCf()))
				return;
		dipendenti.add(d);
	}
	
	/**
	 * Rimuove un Dipendente cercandolo tramite codice fiscale e
	 * restituisce il DIpendente eliminato
	 * @param cf codice fiscale del Dipendente
	 * @return il Dipendente eliminato, null altrimenti
	 */
	public Dipendente licenziaDipendente(String cf) throws LicenziamentoException{
		int i = 0;
		for(Dipendente temp: dipendenti) {
			if(temp.getCf().equalsIgnoreCase(cf)) {
				if(temp.getOccupazione())
					throw new LicenziamentoException();
				else
					return dipendenti.remove(i);
			}
			i++;
		}
		return null;
	}
	
	//Metodo paga
	/**
	 * Paga il Dipendente e restituisce l'esito del pagamento
	 * @param cf codice fiscale Dipendente
	 * @return true se trovo il Dipendente, false altrimenti
	 */
	public boolean pagaDipendente(String cf) {
		for(Dipendente temp: dipendenti)
			if(temp.getCf().equalsIgnoreCase(cf)) {
				temp.paga();
				return true;
			}
		return false;
	}
	
	/**
	 * Ritorna una stringa contenente la toString di tutti i Dipendenti
	 * @return una String formattata, altrimenti ""
	 */
	public String StringArrayDipendenti() {
		String s = "";
		for(Dipendente temp: dipendenti)
			s += temp.toString() + "\n";
		return s;
	}
	
	/**
	 * Ritorna una stringa contenente la toString di tutti i Dipendenti LIBERI
	 * @return una String formattata, altrimenti ""
	 */
	public String StringArrayDipendentiLiberi() {
		String s = "";
		for(Dipendente temp: dipendenti)
			if(!temp.getOccupazione())
				s += temp.toString() + "\n";
		return s;
		
	}
}
