package operativo;
import java.io.Serializable;
import java.util.ArrayList;
import cantiere.Cantiere;

/**
 * classe che astrae il concetto di reparto operativo utilizzato per la gestione dei cantieri
 * @author iovino sorrentino zizzari
 *
 */
public final class RepartoOperativo implements Serializable{

	private static final long serialVersionUID = 6795205445581985309L;
	private ArrayList<Cantiere> cantieri;
	/**
	 * Istanzia un oggetto di tipo RepartoOperativo inizializzando un arraylist di Cantieri
	 */
	public RepartoOperativo() {
		cantieri = new ArrayList<Cantiere>();
	}
	//Metodi accesso
	/**
	 * Ritorna la lista dei Cantieri
	 * @return ArrayList di Cantiere
	 */
	public ArrayList<Cantiere> getCantieri(){
		return cantieri;
	}
	/**
	 * Cerca un Cantiere tramite il codice univoco
	 * @param nome del Cantiere
	 * @return Il Cantiere se lo trova, null altrimenti
	 */
	public Cantiere searchCantiere(String nome) {
		for(Cantiere temp: cantieri)
			if(temp.getNome().equalsIgnoreCase(nome))
				return temp;
		return null;
	}
	//Metodi modifica
	/**
	 * Aggiunge un Cantiere alla lista
	 * @param c Cantiere da aggiungere
	 */
	public void nuovoCantiere(Cantiere c) {
		for(Cantiere temp: cantieri)
			if(c.getNome().equalsIgnoreCase(temp.getNome()))
				return;
		cantieri.add(c);
	}
	/**
	 * Chiude un Cantiere
	 * @param nome nome del Cantiere
	 */
	public void chiudiCantiere(String nome) {
		for(Cantiere temp: cantieri)
			if(temp.getNome().equalsIgnoreCase(nome)) {
				temp.chiudiCantiere();
				return;
			}
	}
	/**
	 * Apre un Cantiere
	 * @param nome del Cantiere
	 */
	public void apriCantiere(String nome) {
		for(Cantiere temp: cantieri)
			if(temp.getNome().equalsIgnoreCase(nome)) {
				temp.apriCantiere();
				return;
			}
	}
	
	/**
	 * Metodo utile per la grafica che restituisce le informazioni per ogni cantiere in sistema
	 * @return toString dei cantieri
	 */
	public String getStringCantieri() {
		String s = "";
		for(Cantiere x: cantieri)
			s += x.toString() + "\n";
		return s;
	}
	
}
