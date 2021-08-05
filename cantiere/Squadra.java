package cantiere;
import personale.*;
import java.io.Serializable;
import java.util.ArrayList;
import eccezioni.InvalidQuadroException;
import eccezioni.OperaioImpegnatoException;

/**
 * classse che astrae il concetto di Squadra a cui appartengono operai ed un capo-squadra
 * @author iovino, sorrentino, zizzari
 *
 */
public class Squadra implements Serializable{

	private static final long serialVersionUID = 6964215388596345129L;
	private Quadro caposquadra;
	private ArrayList<Operaio> operai;
	private int codice;
	
	/**
     * Crea una squadra di Operai avente un codice 
     * @param q Quadro che diverra' capo-squadra
     * @param cod codice della squadra
     */
	public Squadra(Quadro q, int cod){
		if(q instanceof Quadro)
			this.caposquadra = q;
		else throw new InvalidQuadroException();
		operai = new ArrayList<Operaio>();
		codice = cod;
	}
	
	//Metodi accesso
	/**
	 * Restituisce il codice della Squadra del cantiere selezionato
	 * @return codice
	 */
	public int getCodice() {
		return codice;
	}
	/**
	 * Ritorna l'elenco degli operai e il Quadro che li gestisce
	 * @return le informazioni sul caposquadra e tutti gli operai apartenenti alla squadra
	 */
	public String getArraySquadra() {
		String s =caposquadra.toString() + "\n";
		for(Operaio temp: operai)
			s += temp.toString() + "\n";
		return s;
	}
	/**
	 * Restituice il caposquadra
	 * @return caposquadra della Squadra
	 */
	public Quadro getCaposquadra() {
		return caposquadra;
	}
	
	//Metodi modifica
	/**
	 * Aggiunge un Operaio alla Squadra
	 * @param e Operaio da aggiungere
	 * @throws OperaioImpegnatoException Lancia l'eccezione se l'Operaio che proviamo ad inserire � gi� occupato in un'altra Squadra
	 */
	public void aggiungiOperaio(Operaio e) throws OperaioImpegnatoException{
		if(!e.getOccupazione()) {
			operai.add(e);
			e.setOccupazione(true);
		}
		else throw new OperaioImpegnatoException();
	}
	/**
	 * Rimuove un Operaio da una squadra tramite il codice fiscale e lo restituisce
	 * @param cf codice fiscale dell'Operaio
	 * @return Operaio rimosso, null altrimenti
	 */
	public Operaio removeOperaio(String cf) {
		int i = 0;
		for(Operaio temp: operai) {
			if(temp.getCf().equalsIgnoreCase(cf)) {
				temp.setOccupazione(false);
				return operai.remove(i);
			}
			i++;
		}
		return null;
	}
	/**
	 * Imposta tutti i dipendenti della squadra liberi usarla solo se si vuole eliminare la Squadra
	 */
	public void liberaSquadra(){
		caposquadra.setOccupazione(false);
		for(Operaio temp: operai)
			temp.setOccupazione(false);
	}
	
	public String toString() {
		return getClass().getName() + "[codice=" + codice + ",caposquadra=" + caposquadra + ",operai=" + operai + "]";
	}

}
