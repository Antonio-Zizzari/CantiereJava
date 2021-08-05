package amministrativo;
import java.util.ArrayList;

import ambienteesterno.Cliente;
import ambienteesterno.Ente;
import ambienteesterno.Fornitore;
import eccezioni.LicenziamentoException;
import personale.Dipendente;
import personale.Quadro;

public class AmministrativoTester {

	public static void main(String[] args) throws LicenziamentoException{
		ArrayList<Dipendente> arr;
		RepartoAmministrativo ra = new RepartoAmministrativo();
		
		System.out.println("\nTest interazione interna.");
		Quadro q = new Quadro("pasquale", "menna", "123", 0, "nessuno", 3, 150, false, 200);
		ra.getInterno().assumiDipendente(q);
		
		System.out.println("\ntest per aggiunta/rimozione dipendenti, stampa arraylist ");
		arr = ra.getInterno().getDipendenti();
		for(Dipendente temp: arr)
			System.out.println(temp.toString());
		
		System.out.println("\ninserimento del quadro 'test test'");
		Quadro q1 = new Quadro("test", "test", "000", 0, "nessuno", 3, 150, false, 44);
		ra.getInterno().assumiDipendente(q1);
		arr = ra.getInterno().getDipendenti();
		for(Dipendente temp: arr)
			System.out.println(temp.toString());
		
		System.out.println("\nrimozione del quadro 'test test'");
		ra.getInterno().licenziaDipendente(q1.getCf());
		arr = ra.getInterno().getDipendenti();
		for(Dipendente temp: arr)
			System.out.println(temp.toString());
		
		System.out.println("\npagamento del quadro 'pasquale menna'");
		ra.getInterno().pagaDipendente(q.getCf());
		arr = ra.getInterno().getDipendenti();
		for(Dipendente temp: arr)
			System.out.println(temp.toString());
		
		System.out.println("\nTest interazione interna concluso\nTest interazione esterna.");
		
		Cliente c = new Cliente("me", "stesso", "12345");
		Ente e = new Ente("enel", "131", "salerno");
		Fornitore f = new Fornitore("fornitore", "tester", "via prova");
		
		ra.getEsterno().addCliente(c);
		ra.getEsterno().addEnte(e);
		ra.getEsterno().addFornitore(f);
		
		System.out.println("stampa dei clienti\n" + ra.getEsterno().StringArrayCliente());
		System.out.println("stampa degli enti\n" + ra.getEsterno().StringArrayEnti());
		System.out.println("stampa dei fornitori\n" + ra.getEsterno().StringArrayFornitori());
		
		System.out.println("\naggiunta di un cliente, un ente ed un fornitore");
		
		Cliente c1 = new Cliente("cleinte", "prova", "00000");
		Ente e1 = new Ente("ente", "prova", "citta");
		Fornitore f1 = new Fornitore("fornitore", "prova", "test");
		
		ra.getEsterno().addCliente(c1);
		ra.getEsterno().addEnte(e1);
		ra.getEsterno().addFornitore(f1);
		
		System.out.println("stampa dopo le aggiunte:\n");
		System.out.println("stampa dei clienti\n" + ra.getEsterno().StringArrayCliente());
		System.out.println("stampa degli enti\n" + ra.getEsterno().StringArrayEnti());
		System.out.println("stampa dei fornitori\n" + ra.getEsterno().StringArrayFornitori());
		
		System.out.println("\nrimozione di un ente,cliente,fornitore");
		ra.getEsterno().removeCliente(c1.getCf());
		ra.getEsterno().removeEnte(e1.getPartitaIva());
		ra.getEsterno().removeFornitore(f1.getPartitaIva());
		
		System.out.println("stampa dopo le rimozioni:\n");
		System.out.println("stampa dei clienti\n" + ra.getEsterno().StringArrayCliente());
		System.out.println("stampa degli enti\n" + ra.getEsterno().StringArrayEnti());
		System.out.println("stampa dei fornitori\n" + ra.getEsterno().StringArrayFornitori());
		
	}

}
