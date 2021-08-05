package grafica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
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
import eccezioni.ResponsabileException;
import impresa.Impresa;

/**
 * Frame utilizzato per la gestione del reparto operativo
 * @author iovino sorrentino zizzari
 *
 */
public class FrameOperativo extends JFrame{
	
	private static final long serialVersionUID = 3337591892406365530L;
	private JRadioButton rb1;
	private JRadioButton rb2;
	private JTextField fieldNomeSearch;
	private JTextArea area;
	private JTextArea areaDipendenti;
	private Impresa impresa;
	private JTextField fieldValore;
	private JTextField fieldCapo;
	private JTextField fieldNome;
	private JTextField fieldNomeModifica;
	
	/**
	 * Costruttore del frame relativo al reparto operativo
	 * @param i Impresa su cui operare
	 */
	public FrameOperativo(Impresa i) {
		setSize(800, 650);
		impresa = i;
		setTitle("Gestione operativa");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		add(mainPanel());		
	}
	
	private JPanel mainPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,1));
		panel.add(cantierePanel());
		panel.add(editPanel());
		return panel;
	}
	
	private JPanel cantierePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(createPanelCantiere());
		panel.add(areaPanel());
		return panel;
	}
	
	private JPanel editPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(button1Panel());
		panel.add(button2Panel());
		return panel;
	}
	
	private JPanel button1Panel() {
		JPanel panel = new JPanel();
		fieldNome = new JTextField(20);
		fieldCapo= new JTextField(20);
		fieldValore= new JTextField(20);
		panel.setLayout(new GridLayout(4,2));
		panel.setBorder(new TitledBorder(new EtchedBorder(),"creazione cantieri"));
		JButton button = new JButton("crea cantiere");
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e){
				try{
					Cantiere c=new Cantiere(fieldNome.getText(),Integer.parseInt(fieldValore.getText()), impresa.getAmministrativo().getInterno().getDipendente(fieldCapo.getText()));
					impresa.getAmministrativo().getInterno().getDipendente(fieldCapo.getText()).setOccupazione(true);
					impresa.getOperativo().nuovoCantiere(c);
					area.setText(impresa.getOperativo().getStringCantieri());
				}
				catch(ResponsabileException r) {
					JFrame frame = new JFrame();
					frame.setSize(600, 200);
					frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					frame.setTitle("Frame di errore");
					frame.add(new JLabel("Responsabile inserito non corrisponde al valore del cantiere"));
					frame.setVisible(true);
					r.printStackTrace();
				}
				areaDipendenti.setText(impresa.getAmministrativo().getInterno().StringArrayDipendenti());
			}
		});
		panel.add(new JLabel("Inserire nome cantiere"));
		panel.add(fieldNome);		
		panel.add(new JLabel("Inserire codice fiscale Capo-Cantiere"));
		panel.add(fieldCapo);
		panel.add(new JLabel("Inserire valore Cantiere"));
		panel.add(fieldValore);
		panel.add(button);
		return panel;
	}
	
	private JPanel button2Panel() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(),"area visualizzazione dipendenti"));
		areaDipendenti = new JTextArea(15, 30);
		areaDipendenti.setEditable(false);
		areaDipendenti.setText(impresa.getAmministrativo().getInterno().StringArrayDipendenti());
		JScrollPane scroll = new JScrollPane(areaDipendenti);
		panel.add(scroll);
		return panel;
	}
	
	private JPanel modificheCantiere() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Gestione cantieri"));
		rb1 = new JRadioButton("apri cantiere");
		rb2 = new JRadioButton("chiudi cantiere");
		ButtonGroup bg = new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
		panel.add(rb1);
		panel.add(rb2);
		fieldNomeSearch = new JTextField(10);
		panel.add(fieldNomeSearch);
		JButton button = new JButton("effettua modifiche");
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(rb1.isSelected()) {
					impresa.getOperativo().apriCantiere(fieldNomeSearch.getText());
					area.setText(impresa.getOperativo().getStringCantieri());
				}
				if(rb2.isSelected()) {
					impresa.getOperativo().chiudiCantiere(fieldNomeSearch.getText());
					area.setText(impresa.getOperativo().getStringCantieri());
				}
			}
		});
		panel.add(button);
		return panel;
	}
	
	private JPanel areaPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(),"area visualizzazione cantiere"));
		area = new JTextArea(15, 30);
		area.setEditable(false);
		area.setText(impresa.getOperativo().getStringCantieri());
		JScrollPane scroll = new JScrollPane(area);
		panel.add(scroll);
		return panel;
	}
	private JPanel modificheSquadreCantiere() {
		JPanel r = new JPanel();
		r.setBorder(new TitledBorder(new EtchedBorder(), "modifica Squadre cantiere"));
		r.add(new JLabel("inserire nome"));
		fieldNomeModifica = new JTextField(10);
		JButton b1 = new JButton("modifica");
		r.add(fieldNomeModifica);
		r.add(b1);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrameCantiere fcant = new FrameCantiere(impresa, fieldNomeModifica.getText());
				fcant.setVisible(true);
			}
		});
		return r;
	}
	private JPanel createPanelCantiere() {
		JPanel r = new JPanel();
		r.setLayout(new GridLayout(2,1));
		r.add(modificheCantiere());
		r.add(modificheSquadreCantiere());
		return r;
	}

}
