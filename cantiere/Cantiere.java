package cantiere;
import personale.*;

import java.io.Serializable;
import java.util.ArrayList;

import eccezioni.ResponsabileException;

/**
 * classe che astrae il concetto di cantiere
 * @author iovino,sorrentino,zizzari
 *
 */
public class Cantiere implements Serializable{

	private static final long serialVersionUID = 44717235101934757L;
	private int valore;
	private boolean chiuso;
	private Dipendente responsabile;
	private String nomeCantiere;
	private ArrayList<Squadra> squadre;
	int numeroSquadre;
	
	/**
	 * Istanzia un nuovo Cantiere avente un codice univoco
	 * @param nome nome del cantiere
	 * @param valore valore economico del Cantiere espresso in Euro
	 * @param responsabile responsabile del Cantiere, assegnare una Oggetto Dirigente oppure Quadro. Se il valore � maggiore 500.000 assegnare un Dirigente
	 * @throws ResponsabileException lanciata se il responsabile inserito non soddisfa i requisiti
	 */
	public Cantiere(String nome, int valore, Dipendente responsabile) throws ResponsabileException{
		this.valore = valore;
		if(valore>500000 && responsabile instanceof Dirigente)
			this.responsabile = responsabile;
		else if (valore < 500000 && (responsabile instanceof Dirigente || responsabile instanceof Quadro))
			this.responsabile = responsabile;
		else
			throw new ResponsabileException();
		chiuso = false;
		squadre = new ArrayList<Squadra>();
		responsabile.setOccupazione(true);
		nomeCantiere = nome;
		numeroSquadre = 0;
	}
	//Metodi accesso
	/**
	 * Restituisce il nome del cantiere
	 * @return nome del Cantiere
	 */
	public String getNome() {
		return nomeCantiere;
	}
	/**
	 * Restituisce il valore del Cantiere
	 * @return valore del Cantiere
	 */
	public int getValore() {
		return valore;
	}
	/**
	 * Restituisce il responsabile del Cantiere
	 * @return responsabile Cantiere, o una classe Quadro oppure Dirigente
	 */
	public Dipendente getResponsabile() {
		return responsabile;
	}
	/**
	 * Restituisce stato del Cantiere
	 * @return true se � chiuso, false altrimenti
	 */
	public boolean getChiuso() {
		return chiuso;
	}
	/**
	 * Restituisce una Squadra del Cantiere tramite il codice univoco
	 * @param cod codice Squadra
	 * @return Squadra ricercata, null altrimenti
	 */
	public Squadra cercaSquadra(int cod) {
		for(Squadra s: squadre)
			if(s.getCodice() == cod) {;
				return s;
			}
		return null;
	}
	
	//Metodi modifica
	/**
	 * Aggiunge una classe Squadra al Cantiere
	 * @param s Squadra del Cantiere
	 */
	public void aggiungiSquadra(Squadra s) {
		squadre.add(s);
		numeroSquadre++;
	}
	public int getNSquadre() {
		return numeroSquadre;
	} 
	/**
	 * Modifica valore Cantiere
	 * @param valore valore del Cantiere
	 */
	public void setValore(int valore) {
		this.valore = valore;
	}
	
	/**
	 * Chiude il Cantiere
	 */
	public void chiudiCantiere() {
		chiuso = true;
	}
	/**
	 * Apre il Cantiere
	 */
	public void apriCantiere() {
		chiuso = false;
	}
	/**
	 * Rimuove una Squadra nella lista del Cantiere tramite il codice
	 * @param cod codice univoco della Squadra
	 */
	public void rimuoviSquadra(int cod) {
		for(Squadra s: squadre)
			if(s.getCodice() == cod) {
				s.liberaSquadra();
				squadre.remove(s);
				return;
			}
	}
	
	/**
	 * metodo utile per la grafica, che restituisce tutte le squadre che lavorano al cantiere
	 * @return una stringa contenente tutte le squadre che lavorano al cantiere
	 */
	public String StringArraySquadre() {
		String s = "";
		for(Squadra y: squadre)
			s += y.toString() + "\n";
		
		return s;
	}
	
	public String toString() {
		return getClass().getName() + "[nomeCantiere=" + nomeCantiere + ",chiuso="+ chiuso + ",responsabile=" + responsabile + ",valore=" + valore + ",numeroSquadre=" + numeroSquadre + ",squadre=" + squadre + "]";
	}
	
}
