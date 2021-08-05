package repartostatistiche;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * classe utilizzata per ordinare attraverso un criterio definito dall'oggetto comparator
 * @author iovino sorrentino zizzari
 *
 * @param <T> tipi di oggetti compatibili con T
 */
public class Ordinatore<T>{

	private Comparator<T> comp;
	
	/**
	 * istanzia un ordinatore
	 * @param comp comparatore che definisce il criterio
	 */
	public Ordinatore(Comparator<T> comp) {
		this.comp = comp;
	}
	
	/**
	 * metodo che ordina secondo il criterio
	 * @param list arraylist da ordinare
	 * @return l'arraylis ordinato
	 */
	public ArrayList<T> sort(ArrayList<T> list) {
		for(int i = 1; i<list.size();i++)
			for(int j = 0; j<list.size() - i;j++)
				if(comp.compare(list.get(j), list.get(j+1)) > 0) {
					T temp = list.get(j);
					list.set(j, list.get(j+1));
					list.set(j+1, temp);
				}
		return list;
	}
}
