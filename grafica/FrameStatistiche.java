package grafica;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import ambienteesterno.*;
import cantiere.Cantiere;
import impresa.Impresa;
import personale.Dipendente;
import repartostatistiche.RepartoStatistiche;
import repartostatistiche.Selectable;

/**
 * Frame utilizzato per la generazione di report
 * @author iovino sorrentino zizzari
 *
 */
public class FrameStatistiche extends JFrame{
	private static final long serialVersionUID = -1226226449276520637L;
	private Impresa i;
	JComboBox<String> cb;
	JTextField field1, field2;
	JTextArea area;
	JRadioButton rb1,rb2;
	JCheckBox occupazioneDipendenti, valoreCantiere, c;
	
	/**
	 * Costruttore del frame
	 * @param i Impresa su cui operare
	 */
	public FrameStatistiche(Impresa i) {
		this.i = i;
		setSize(1000,600);
		setTitle("Frame visualizzazione statistiche e report");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		add(mainPanel());
	}
	
	private JPanel mainPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,1));
		panel.add(topPanel());
		panel.add(bottomPanel());
		return panel;
	}
	
	private JPanel topPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,3));
		panel.add(comboPanel());
		panel.add(fieldPanel());
		panel.add(checkPanel());
		return panel;
	}
	
	private JPanel checkPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,1));
		panel.setBorder(new TitledBorder(new EtchedBorder(),"Scelta caratteristiche elementi"));
		valoreCantiere = new JCheckBox("cantieri con valore >= 500.000");
		occupazioneDipendenti = new JCheckBox("dipendenti non occupati");
		panel.add(valoreCantiere);
		panel.add(occupazioneDipendenti);
		JButton button = new JButton("GENERA REPORT");
		panel.add(button);
		RepartoStatistiche rs = new RepartoStatistiche();
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				area.setText("");
				if(cb.getSelectedIndex() == 0) {
					area.setText("");
					Comparator<Dipendente> comp = new Comparator<Dipendente>() {
						public int compare(Dipendente d1,Dipendente d2) {
							if(rb1.isSelected())
								return d1.getCognome().compareToIgnoreCase(d2.getCognome());
							else
								return d2.getCognome().compareToIgnoreCase(d1.getCognome());
						}
					};
					Selectable<Dipendente> sel = new Selectable<Dipendente>() {
						public boolean select(Dipendente d) {
							if(c.isSelected()) {
								if(d.getCognome().compareToIgnoreCase(field1.getText()) >= 0 &&  d.getCognome().compareToIgnoreCase(field2.getText())<=0) {
									if(occupazioneDipendenti.isSelected())
										return !d.getOccupazione();
									else 
										return true;
								}
								else 
									return false;
							}
							else {
								if(occupazioneDipendenti.isSelected())
									return !d.getOccupazione();
								else 
									return true;
							}
						}
					};
					
					ArrayList<Dipendente> risultato = rs.selezionaElementi(i.getAmministrativo().getInterno().getDipendenti(), comp, sel);
					
					for (Dipendente temp: risultato)
						area.append(temp.toString() + "\n");
				}
				else if(cb.getSelectedIndex() == 1) {
					area.setText("");
					Comparator<Cantiere> comp = new Comparator<Cantiere>() {
						public int compare(Cantiere d1,Cantiere d2) {
							if(rb1.isSelected())
								return d1.getValore() - d2.getValore();
							else
								return d2.getValore() - d1.getValore();
						}
					};
					Selectable<Cantiere> sel = new Selectable<Cantiere>() {
						public boolean select(Cantiere d) {
							if(c.isSelected()) {
								if(d.getValore()>=Integer.parseInt(field1.getText()) && d.getValore()<=Integer.parseInt(field2.getText())) {
									if(valoreCantiere.isSelected())
										return d.getValore() >= 500000;
									else {
										return true;
									}
								}
								else
									return false;
								
							}
							else {
								if(valoreCantiere.isSelected())
									return d.getValore() >= 500000;
								else
									return true;
							}
						}
					};
					
					ArrayList<Cantiere> risultato = rs.selezionaElementi(i.getOperativo().getCantieri(), comp, sel);
					for(Cantiere temp: risultato)
						area.append(temp.toString() + "\n");
				}
				else if(cb.getSelectedIndex() == 2) {
					area.setText("");
					Comparator<Fornitore> comp = new Comparator<Fornitore>() {
						public int compare(Fornitore d1,Fornitore d2) {
							if(rb1.isSelected())
								return d1.getNome().compareToIgnoreCase(d2.getNome());
							else
								return d2.getNome().compareToIgnoreCase(d1.getNome());
						}
					};
					Selectable<Fornitore> sel = new Selectable<Fornitore>() {
						public boolean select(Fornitore d) {
							if(c.isSelected()) {
								if(d.getNome().compareToIgnoreCase(field1.getText()) >= 0 && d.getNome().compareToIgnoreCase(field2.getText()) <= 0)
									return true;
								else
									return false;								
							}
							else
								return true;
						}
					};
					
					ArrayList<Fornitore> risultato = rs.selezionaElementi(i.getAmministrativo().getEsterno().getFornitori(), comp, sel);
					
					for(Fornitore temp: risultato)
						area.append(temp.toString() + "\n");
					
				}
				else if(cb.getSelectedIndex() == 3) {
					area.setText("");
					Comparator<Cliente> comp = new Comparator<Cliente>() {
						public int compare(Cliente d1,Cliente d2) {
							if(rb1.isSelected())
								return d1.getCognome().compareToIgnoreCase(d2.getCognome());
							else
								return d2.getCognome().compareToIgnoreCase(d1.getCognome());
						}
					};
					Selectable<Cliente> sel = new Selectable<Cliente>() {
						public boolean select(Cliente d) {
							if(c.isSelected()) {
								if(d.getCognome().compareToIgnoreCase(field1.getText()) >= 0 && d.getCognome().compareToIgnoreCase(field2.getText()) <= 0)
									return true;
								else
									return false;								
							}
							else
								return true;
						}
					};
					
					ArrayList<Cliente> risultato = rs.selezionaElementi(i.getAmministrativo().getEsterno().getClienti(), comp, sel);
					
					for(Cliente temp: risultato)
						area.append(temp.toString() + "\n");
					
				}
				else if(cb.getSelectedIndex() == 4) {
					area.setText("");
					Comparator<Ente> comp = new Comparator<Ente>() {
						public int compare(Ente d1,Ente d2) {
							if(rb1.isSelected())
								return d1.getNome().compareToIgnoreCase(d2.getNome());
							else
								return d2.getNome().compareToIgnoreCase(d1.getNome());
						}
					};
					Selectable<Ente> sel = new Selectable<Ente>() {
						public boolean select(Ente d) {
							if(c.isSelected()) {
								if(d.getNome().compareToIgnoreCase(field1.getText()) >= 0 && d.getNome().compareToIgnoreCase(field2.getText()) <= 0)
									return true;
								else
									return false;								
							}
							else
								return true;
						}
					};
					
					ArrayList<Ente> risultato = rs.selezionaElementi(i.getAmministrativo().getEsterno().getEnti(), comp, sel);
					
					for(Ente temp: risultato)
						area.append(temp.toString() + "\n");					
				}	
			}
		});
		
		return panel;
	}
	
	private JPanel comboPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,1));
		panel.setBorder(new TitledBorder(new EtchedBorder(),"Scelta lista di elementi"));
		cb = new JComboBox<String>();
		cb.addItem("dipendenti");
		cb.addItem("cantieri");
		cb.addItem("fornitori");
		cb.addItem("clienti");
		cb.addItem("enti");
		panel.add(cb);
		panel.add(radioPanel());
		return panel;
	}
	
	private JPanel radioPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(),"Seleziona ordine"));
		rb1 = new JRadioButton("crescente");
		rb2 = new JRadioButton("decrescente");
		ButtonGroup bg = new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
		panel.add(rb1);
		panel.add(rb2);
		return panel;
	}
	
	private JPanel fieldPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(),"Pannello valori compresi"));
		panel.setLayout(new GridLayout(3,1));
		c = new JCheckBox("spuntare se si vuole elementi compresi fra i 2 valori");
		panel.add(c);
		field1 = new JTextField(10);
		field2 = new JTextField(10);
		field1.setText("min");
		field2.setText("max");
		panel.add(field1);
		panel.add(field2);
		return panel;
	}
	
	private JPanel bottomPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(),"Report generati"));
		area = new JTextArea(15,90);
		JScrollPane scroll = new JScrollPane(area);
		area.setEditable(false);
		panel.add(scroll);
		return panel;
	}
}
