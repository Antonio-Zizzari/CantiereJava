package grafica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import impresa.Impresa;
import personale.*;

/**
 * Frame per la gestione dei dipendenti
 * @author iovino sorrentino zizzari
 *
 */
public class FrameDipendenti extends JFrame{
	
	private static final long serialVersionUID = -7043178912628191155L;
	private String tipoDipendente;
	private Impresa impresa;
	private JButton button;
	private JTextField campoNome, campoCognome, campoCF, campoSaldo;
	private JCheckBox campoOccupazione;
	private JPanel panel;
	
	/**
	 * Costruttore frame per assunzione dipendente
	 * @param tipo Tipo (formato String) di dipendente da assumere
	 * @param im Impresa su cui operare
	 */
	public FrameDipendenti(String tipo, Impresa im) {
		tipoDipendente = tipo;
		impresa = im;
		setSize(800, 600);
		setTitle("assunzione dipendente");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		panel = new JPanel();
		
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Inserimento campi"));
		add(panel);
		
		setFrameDipendente();
		switch(tipoDipendente) {
			case "Quadro":
				panel.setLayout(new GridLayout(10,2));
				setFrameQuadro();
				break;
			case "Impiegato":
				panel.setLayout(new GridLayout(8,2));
				setFrameImpiegato();
				break;
			case "Operaio":
				panel.setLayout(new GridLayout(9,2));
				setFrameOperaio();
				break;
			case "Dirigente":
				panel.setLayout(new GridLayout(8,2));
				setFrameDirigente();
				break;
			default: 
				break;
		}
	}

	private void setFrameDipendente() {
		campoNome = new JTextField(15);
		campoCognome = new JTextField(15);
		campoCF = new JTextField(15); 
		campoSaldo = new JTextField(15);
		campoOccupazione = new JCheckBox("occupato");
		
		panel.add(new JLabel("inserisci nome"));
		panel.add(campoNome);
		panel.add(new JLabel("inserisci cognome"));
		panel.add(campoCognome);
		panel.add(new JLabel("inserisci codice fiscale"));
		panel.add(campoCF);
		panel.add(new JLabel("inserisci saldo"));
		panel.add(campoSaldo);
		panel.add(new JLabel("spuntare se occupato"));
		panel.add(campoOccupazione);
		button = new JButton("Invia");
		
	}

	private void setFrameDirigente() {
		JTextField campoAnni = new JTextField(15);
		JTextField campoStipendio = new JTextField(15);
		
		panel.add(new JLabel("inserisci anni di esperienza"));
		panel.add(campoAnni);
		panel.add(new JLabel("inserisci stipendio"));
		panel.add(campoStipendio);
		panel.add(button);
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				boolean b = false;
				if(campoOccupazione.isSelected())
					b = true;
				Dirigente d = new Dirigente(campoNome.getText(),campoCognome.getText(), campoCF.getText(), Integer.parseInt(campoSaldo.getText()), Integer.parseInt(campoAnni.getText()), Integer.parseInt(campoStipendio.getText()), b);
				impresa.getAmministrativo().getInterno().assumiDipendente(d);
			}
		});
		
	}

	private void setFrameOperaio() {
		JTextField campoCar = new JTextField(15);
		JTextField campoCostoOre = new JTextField(15);
		
		panel.add(new JLabel("inserisci caratteristiche"));
		panel.add(campoCar);
		panel.add(new JLabel("inserisci costo orario"));
		panel.add(campoCostoOre);
		panel.add(button);
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				boolean b = false;
				if(campoOccupazione.isSelected())
					b = true;
				Operaio d = new Operaio(campoNome.getText(),campoCognome.getText(), campoCF.getText(), campoCar.getText(), Integer.parseInt(campoSaldo.getText()),Integer.parseInt(campoCostoOre.getText()) ,b);
				impresa.getAmministrativo().getInterno().assumiDipendente(d);
			}
		});
		
		
	}

	private void setFrameImpiegato() {
		JTextField campoRuolo = new JTextField(15);
		JTextField campoStipendio = new JTextField(15);
		
		panel.add(new JLabel("inserisci ruolo "));
		panel.add(campoRuolo);
		panel.add(new JLabel("inserisci stipendio"));
		panel.add(campoStipendio);
		panel.add(button);
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				boolean b = false;
				if(campoOccupazione.isSelected())
					b = true;
				Impiegato d = new Impiegato(campoNome.getText(),campoCognome.getText(), campoCF.getText(), Integer.parseInt(campoSaldo.getText()),campoRuolo.getText(),Integer.parseInt(campoStipendio.getText()) ,b);
				impresa.getAmministrativo().getInterno().assumiDipendente(d);
			}
		});
		
	}

	private void setFrameQuadro() {
		JTextField campoDecisione = new JTextField(15);
		JTextField campoOreStraord = new JTextField(15);
		JTextField campoCostoOre = new JTextField(15);
		JTextField campoStipendio = new JTextField(15);
		
		panel.add(new JLabel("inserisci campo decisionale"));
		panel.add(campoDecisione);
		panel.add(new JLabel("inserisci numero di ore straordinarie"));
		panel.add(campoOreStraord);
		panel.add(new JLabel("inserisci costo orario"));
		panel.add(campoCostoOre);
		panel.add(new JLabel("inserisci stipendio"));
		panel.add(campoStipendio);
		panel.add(button);
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				boolean b = false;
				if(campoOccupazione.isSelected())
					b = true;
				Quadro d = new Quadro(campoNome.getText(),campoCognome.getText(), campoCF.getText(), Integer.parseInt(campoSaldo.getText()),campoDecisione.getText(),Integer.parseInt(campoCostoOre.getText()),Integer.parseInt(campoStipendio.getText()) ,b, Integer.parseInt(campoOreStraord.getText()));
				impresa.getAmministrativo().getInterno().assumiDipendente(d);
			}
		});
		
	}
	
	
}
