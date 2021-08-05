package impresa;
import java.io.Serializable;
import amministrativo.*;
import operativo.*;
import repartostatistiche.*;

/**
 * classe ch astrae il concetto di impresa, costituita da 3 reparti: amministrativo, operativo e statistiche
 * @author iovino sorrentino zizzari
 *
 */
public final class Impresa implements Serializable{

	private static final long serialVersionUID = 8084589716889251794L;
	private RepartoOperativo operativo;
	private RepartoAmministrativo amministrativo;
	private RepartoStatistiche statistiche;
	/**
	 * Istanzia un'impresa
	 */
	public Impresa() {
		operativo = new RepartoOperativo();
		amministrativo = new RepartoAmministrativo();
		statistiche = new RepartoStatistiche();
	}
	//Metodi accesso
	/**
	 * Restituisce il Reparto Operativo
	 * @return Reparto Operativo dell'Impresa
	 */
	public RepartoOperativo getOperativo() {
		return operativo;
	}
	/**
	 * Restituisce il Reparto Amministrativo
	 * @return Reparto Amministrativo dell'Impresa
	 */
	public RepartoAmministrativo getAmministrativo() {
		return amministrativo;
	}
	/**
	 * Restituisce il Reparto Statistica
	 * @return Reparto Statistica dell'Impresa
	 */
	public RepartoStatistiche getStatistiche() {
		return statistiche;
	}
}
