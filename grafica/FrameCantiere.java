package grafica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import cantiere.Cantiere;
import cantiere.Squadra;
import impresa.Impresa;
import personale.*;

/**
 * Frame utilizzato per la gestione di un singolo cantiere
 * @author iovino sorrentino zizzari
 *
 */
public class FrameCantiere extends JFrame{
	
	private static final long serialVersionUID = -1115749912600499134L;
	private Impresa impresa;
	private Cantiere cantiereSelezionato;
	private String nomeCant;
	private JTextArea areaDipendentiLiberi, areaSquadre, areaDipendentiSquadraSelezionata;
	private JRadioButton rb1, rb2;
	private JTextField field,fieldDip;
	private JTextField fieldCfSquadra,fieldCodSquadra;
	
	/**
	 * Costruttore del frame
	 * @param i Impresa su cui operare
	 * @param nomeCant Nome del cantiere su cui operare
	 */
	public FrameCantiere(Impresa i, String nomeCant) {
		setSize(800, 650);
		impresa = i;
		ArrayList<Cantiere> c = impresa.getOperativo().getCantieri();
		for(Cantiere temp: c)
			if(temp.getNome().equalsIgnoreCase(nomeCant))
				cantiereSelezionato = temp;
		this.nomeCant = nomeCant;
		setTitle("Gestione cantiere");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(2, 1));
		add(mainPanel());
		add(bottomPanel());
	}
	
	private JPanel mainPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(gestPanel());
		panel.add(areaLiberiPanel());
		return panel;
	}
	
	private JPanel bottomPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(gestSquadre());
		panel.add(areaSquadrePanel());
		return panel;
	}

	private JPanel gestSquadre() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(),"Modifiche squadre"));
		panel.setLayout(new GridLayout(2,1));
		fieldCfSquadra = new JTextField(16);
		fieldCodSquadra = new JTextField(16);
		panel.add(codSquadra());
		panel.add(cfSquadra());
		return panel;
	}
	
	private JPanel codSquadra() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("inserire codice squadra"));
		panel.add(fieldCodSquadra);
		JButton button = new JButton("seleziona");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int codice = Integer.parseInt(fieldCodSquadra.getText());
				String s = impresa.getOperativo().searchCantiere(nomeCant).cercaSquadra(codice).getArraySquadra();
				areaDipendentiSquadraSelezionata.setText(s);
			}
		});
		panel.add(button);
		return panel;
	}
	
	private JPanel cfSquadra() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,2));
		panel.add(new JLabel("inserire codice fiscale"));
		panel.add(fieldCfSquadra);
		JButton button = new JButton("Fatto");
		JComboBox<String> cb = new JComboBox<String>();
		cb.addItem("aggiungi");
		cb.addItem("rimuovi");
		panel.add(cb);
		try {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(cb.getSelectedIndex() == 0) {
						Squadra s = impresa.getOperativo().searchCantiere(nomeCant).cercaSquadra(Integer.parseInt(fieldCodSquadra.getText()));
						Operaio o = (Operaio) impresa.getAmministrativo().getInterno().getDipendente(fieldCfSquadra.getText());
						s.aggiungiOperaio(o);
					}
					else if(cb.getSelectedIndex() == 1) {
						Squadra s = impresa.getOperativo().searchCantiere(nomeCant).cercaSquadra(Integer.parseInt(fieldCodSquadra.getText()));
						s.removeOperaio(fieldCfSquadra.getText());
					}
					areaDipendentiLiberi.setText(impresa.getAmministrativo().getInterno().StringArrayDipendentiLiberi());
					areaSquadre.setText(cantiereSelezionato.StringArraySquadre());
					int codice = Integer.parseInt(fieldCodSquadra.getText());
					areaDipendentiSquadraSelezionata.setText(impresa.getOperativo().searchCantiere(nomeCant).cercaSquadra(codice).getArraySquadra());
				}
			});
		}
		catch (ClassCastException e) {
			e.printStackTrace();
		}
		panel.add(button);
		return panel;
	}

	private JPanel areaLiberiPanel() {
		JPanel p = new JPanel();
		p.setBorder(new TitledBorder(new EtchedBorder(), "dipendenti liberi"));
		areaDipendentiLiberi = new JTextArea(12,30);
		areaDipendentiLiberi.setEditable(false);
		areaDipendentiLiberi.setText(impresa.getAmministrativo().getInterno().StringArrayDipendentiLiberi());
		JScrollPane scroll = new JScrollPane(areaDipendentiLiberi);
		p.add(scroll);
		return p;
	}
	
	private JPanel areaSquadrePanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(2, 1));
		
		JPanel p1 = new JPanel();
		p1.setBorder(new TitledBorder(new EtchedBorder(), "elenco squadre"));
		areaSquadre = new JTextArea(7,30);
		areaSquadre.setEditable(false);
		JScrollPane scroll1 = new JScrollPane(areaSquadre);
		areaSquadre.setText(cantiereSelezionato.StringArraySquadre());
		p1.add(scroll1);
		
		JPanel p2 = new JPanel();
		p2.setBorder(new TitledBorder(new EtchedBorder(), "squadra selezionata"));
		areaDipendentiSquadraSelezionata = new JTextArea(7,30);
		areaDipendentiSquadraSelezionata.setEditable(false);
		JScrollPane scroll2 = new JScrollPane(areaDipendentiSquadraSelezionata);
		p2.add(scroll2);
		
		mainPanel.add(p1);
		mainPanel.add(p2);
		return mainPanel;
	}
	
	private JPanel gestPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4,1));
		
		JPanel r1 = new JPanel();
		r1.setLayout(new GridLayout(1,2));
		p.setBorder(new TitledBorder(new EtchedBorder(), "Gestione squadre"));
		field = new JTextField(10);
		r1.add(new JLabel("inserisci codice squadra"));
		field.setText("usare per rimuovere squadra");
		r1.add(field);
		p.add(r1);
		
		JPanel r4 = new JPanel();
		r4.setLayout(new GridLayout(1,2));
		fieldDip = new JTextField(10);
		fieldDip.setText("usare per creare squadra");
		r4.add(new JLabel("inserisci cf caposquadra"));
		r4.add(fieldDip);
		p.add(r4);
		
		JPanel r2 = new JPanel();
		r2.setLayout(new GridLayout(1,2));
		rb1 = new JRadioButton("crea squadra");
		rb2 = new JRadioButton("rimuovi squadra");
		r2.add(rb1);
		r2.add(rb2);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
		p.add(r2);
		
		JButton button = new JButton("esegui");
		
		p.add(button);
		
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if(rb1.isSelected()) {
					try {
						Quadro q = (Quadro) impresa.getAmministrativo().getInterno().getDipendente(fieldDip.getText());
						q.setOccupazione(true);
						int n = impresa.getOperativo().searchCantiere(nomeCant).getNSquadre();
						Squadra s = new Squadra(q, n);
						impresa.getOperativo().searchCantiere(nomeCant).aggiungiSquadra(s);
					}
					catch(ClassCastException e) {
						JFrame frame = new JFrame();
						frame.setSize(600, 200);
						frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						frame.setTitle("Frame di errore");
						frame.add(new JLabel("Il caposquadra non e' un quadro"));
						frame.setVisible(true);
						e.printStackTrace();
					}
				}	
				else if(rb2.isSelected()){
					impresa.getOperativo().searchCantiere(nomeCant).rimuoviSquadra(Integer.parseInt(field.getText()));
					areaSquadre.setText(cantiereSelezionato.StringArraySquadre());
				}
				areaSquadre.setText(cantiereSelezionato.StringArraySquadre());
			}
		});
		return p;
	}
	
}
