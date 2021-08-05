package amministrativo;

import java.io.Serializable;

/**
 * Oggetto che astrae il concetto di reparto amministrativo, suddiviso in interazione interna e quella esterna
 * @author iovino,sorrentino,zizzari
 *
 */
public final class RepartoAmministrativo implements Serializable{

	private static final long serialVersionUID = 2284452217122191798L;
	private InterazioneEsterno esterno;
	private InterazioneInterna interno;
	
	/**
	 * Istanzia un oggetto di tipo RepartoAmministrativo inizializzando le due interazioni(int. e est.)
	 */
	public RepartoAmministrativo() {
		esterno = new InterazioneEsterno();
		interno = new InterazioneInterna();
	}
	
	//Metodi accesso
	/**
	 * Restituisce l'interazione interna
	 * @return InterazioneInterna
	 */
	public InterazioneInterna getInterno() {
		return interno;
	}
	/**
	 * Restituisce l'interazione esterna
	 * @return InterazioneEsterna
	 */
	public InterazioneEsterno getEsterno() {
		return esterno;
	}

}
