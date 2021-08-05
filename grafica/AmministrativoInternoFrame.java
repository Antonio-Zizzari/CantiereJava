package grafica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import eccezioni.LicenziamentoException;
import impresa.Impresa;

/**
 * Classe utilizzabile per la rappresentazione grafica per la gestione del reparto amministrativo lato interno
 * @author iovino sorrentino zizzari
 *
 */
public class AmministrativoInternoFrame extends JFrame{
	private Impresa impresa;
	private static final long serialVersionUID = 2840342710984800139L;
	private JTextArea area;
	private JTextField field;
	private JRadioButton rb1;
	private JRadioButton rb2;

	/**
	 * Costruttore frame per la gestione dei dipendenti(pagamenti, licenziamenti, assunzioni..)
	 * @param impresa Impresa su cui operare
	 */
	public AmministrativoInternoFrame(Impresa impresa) {
		this.impresa = impresa;
		field = new JTextField(20);
		setSize(800, 600);
		setTitle("Frame gestione amministrativa");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(2,1));
		mainPanel.add(topPanel());
		mainPanel.add(bottomPanel());
		add(mainPanel);
		setJMenuBar(menuBar());
	}
	
	private JMenuBar menuBar() {
		JMenuBar menu = new JMenuBar();
		JMenuItem item = new JMenuItem("refresh");
		item.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				area.setText(impresa.getAmministrativo().getInterno().StringArrayDipendenti());
			}
		});
		menu.add(item);
		return menu;
	}
	
	private JPanel topPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		JComboBox<String> cb = new JComboBox<String>();
		panel.setBorder(new TitledBorder(new EtchedBorder(),"assunzione dipendenti"));
		cb.addItem("Dirigente");
		cb.addItem("Impiegato");
		cb.addItem("Operaio");
		cb.addItem("Quadro");
		cb.setSelectedIndex(2);
		panel.add(cb);
		
		JButton button = new JButton("Fatto");
		
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				FrameDipendenti fd = new FrameDipendenti((String)cb.getSelectedItem(), impresa);
				fd.setVisible(true);
			}
		});
		panel.add(button);
		return panel;
	}
	
	private JPanel bottomPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(bottomLeftPanel());
		panel.add(bottomRightPanel());
		return panel;
	}
	
	private JPanel bottomRightPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(),"area informazioni dipendenti"));
		area = new JTextArea(13, 32);
		area.setEditable(false);
		area.append(impresa.getAmministrativo().getInterno().StringArrayDipendenti());
		JScrollPane scroll = new JScrollPane(area);
		panel.add(scroll);
		return panel;
	}
	
	private JPanel bottomLeftPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));
		panel.add(bottomLeftNorthPanel());
		panel.add(bottomLeftCenterPanel());
		JButton button = new JButton("FINITO");
		
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(rb1.isSelected())
					impresa.getAmministrativo().getInterno().pagaDipendente(field.getText());
				else if(rb2.isSelected()) {
					try {
						impresa.getAmministrativo().getInterno().licenziaDipendente(field.getText());
					}
					catch (LicenziamentoException l) {
						JFrame frame = new JFrame();
						frame.setSize(600, 200);
						frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						frame.setTitle("Frame di errore");
						frame.add(new JLabel("Licenziamento non possibile poichè dipendente impegnato in un lavoro."));
						frame.setVisible(true);
						l.printStackTrace();
					}
					
				}
				area.setText(impresa.getAmministrativo().getInterno().StringArrayDipendenti());
			}
		});
		
		panel.add(button);		
		return panel;
	}
	
	private JPanel bottomLeftNorthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.setBorder(new TitledBorder(new EtchedBorder(),"scelta operazione"));
		rb1 = new JRadioButton("paga");
		rb2 = new JRadioButton("licenzia");
		ButtonGroup bg = new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);		
		panel.add(rb1);
		panel.add(rb2);
		return panel;
	}
	
	private JPanel bottomLeftCenterPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(),"inserimento cf"));
		field = new JTextField(30);
		panel.add(new JLabel("inserire cf: "));
		panel.add(field);
		return panel;
	}
}
