package repartostatistiche;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * classe utilizzata per la generazione dei report
 * @author iovino sorrentino zizzari
 *
 */
public class RepartoStatistiche implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5358580153944061610L;

	/**
	 * metodo generico utilizzato per selezionare elementi secondo un criterio ed ordinarli secondo un altro criterio
	 * @param <T> tipo di oggetto compatibile su cui operare
	 * @param src arraylist da elaborare
	 * @param comp criterio per l'ordinamento
	 * @param sel criterio per il selezionamento
	 * @return arraylist rielaborato
	 */
	public <T> ArrayList<T> selezionaElementi(ArrayList<T> src, Comparator<T> comp,Selectable<T> sel){
		ArrayList<T> temp = new ArrayList<T>();
		for(T x: src) {
			if(sel.select(x))
				temp.add(x);
		}
		Ordinatore<T> or = new Ordinatore<T>(comp); 
		or.sort(temp);
		return temp;
	}
}
