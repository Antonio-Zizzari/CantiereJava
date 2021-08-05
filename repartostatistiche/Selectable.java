package repartostatistiche;

/**
 * interfaccia di smistamento dove verrà implementato il criterio per selezionare gli oggetti di tipo T
 * @author iovino sorrentino zizzari
 *
 * @param <T> tipo di oggetti su cui operare
 */
public interface Selectable <T>{

	public boolean select(T x);
	
}
