package operativo;
import cantiere.Cantiere;
import eccezioni.ResponsabileException;
import personale.Dirigente;

public class OperativoTester {

	public static void main(String[] args) throws ResponsabileException{
		RepartoOperativo ro = new RepartoOperativo();
		Dirigente d = new Dirigente("dirigente", "prova", "0000", 0, 0, 0, false);
		Cantiere c = new Cantiere("Tester", 300000, d);
		System.out.println("Testing reparto operativo\n");
		
		System.out.println("Aggiunta cantiere di test");
		ro.nuovoCantiere(c);
		
		System.out.println("\nchiusura cantiere Tester");
		ro.chiudiCantiere(c.getNome());
		System.out.println(c.toString());
		
		System.out.println("\napertura cantiere Tester");
		ro.apriCantiere(c.getNome());
		System.out.println(c.toString());

		System.out.println("\nricerca cantiere Tester");
		System.out.println(ro.searchCantiere(c.getNome()));
		
		
		
	}

}
