package grafica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import ambienteesterno.*;
import impresa.Impresa;

/**
 * Classe utilizzabile per la rappresentazione grafica per la gestione del reparto amministrativo lato esterno
 * @author iovino sorrentino zizzari
 *
 */
public class AmministrativoEsternoFrame extends JFrame{
	
	private static final long serialVersionUID = -4452342526992156023L;
		private Impresa impresa;
		
		private JTextArea areaCliente;
		private JTextArea areaFornitore;
		private JTextArea areaEnte;
		
		private JTextField fieldCliente;
		private JTextField fieldCliente1;
		private JTextField fieldCliente2;
		private JTextField fieldCliente3;
		
		private JTextField fieldFornitore;
		private JTextField fieldFornitore1;
		private JTextField fieldFornitore2;
		private JTextField fieldFornitore3;
		
		private JTextField fieldEnte;
		private JTextField fieldEnte1;
		private JTextField fieldEnte2;
		private JTextField fieldEnte3;
		
		/**
		 * Costruttore frame suddiviso in 3 pannelli(1 per riga), utilizzati uno ciascuno per la gestione di clienti, fornitori, enti
		 * @param impresa, parametro che va a selezionare l'impresa su cui agire
		 */
		public AmministrativoEsternoFrame(Impresa impresa) {
			this.impresa = impresa;
			setSize(1000, 700);
			setTitle("Frame gestione amministrativa");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new GridLayout(3,1));
			mainPanel.add(topPanel());
			mainPanel.add(centerPanel());
			mainPanel.add(bottomPanel());
			add(mainPanel);
			setJMenuBar(menuBar());
		}
		
		private JMenuBar menuBar() {
			JMenuBar menu = new JMenuBar();
			JMenuItem item = new JMenuItem("refresh");
			item.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					areaCliente.setText(impresa.getAmministrativo().getEsterno().StringArrayCliente());
					areaFornitore.setText(impresa.getAmministrativo().getEsterno().StringArrayFornitori());
					areaEnte.setText(impresa.getAmministrativo().getEsterno().StringArrayEnti());
				}
			});
			menu.add(item);
			return menu;
		}
		
		/**
		 * pannello da utilizzare per la gestione dei clienti, suddiviso in 2 colonne, a destra le informazioni dei clienti,a sinistra la possibilit� di gestirli
		 * @return pannello costruito
		 */
		private JPanel topPanel() {
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(), "gestione clienti"));
			panel.setLayout(new GridLayout(1,2));
			panel.add(topLeftPanel());
			panel.add(topRightPanel());
			return panel;
		}
		
		private JPanel topLeftPanel() {
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(4, 1));
			panel.add(bottoniCliente());
			fieldCliente1 = new JTextField(30);
			fieldCliente2 = new JTextField(30);
			fieldCliente3 = new JTextField(30);
			panel.add(primoCampoCliente());
			panel.add(secondoCampoCliente());
			panel.add(terzoCampoCliente());
			return panel;
		}
		
		private JPanel primoCampoCliente() {
			JPanel panel = new JPanel();
			panel.add(new JLabel("inserire nome"));
			fieldCliente1 = new JTextField(15);
			panel.add(fieldCliente1);			
			return panel;
		}
		
		private JPanel secondoCampoCliente() {
			JPanel panel = new JPanel();
			panel.add(new JLabel("inserire cognome"));
			fieldCliente2 = new JTextField(15);
			panel.add(fieldCliente2);			
			return panel;
		}
		
		private JPanel terzoCampoCliente() {
			JPanel panel = new JPanel();
			panel.add(new JLabel("inserire cf"));
			fieldCliente3 = new JTextField(17);
			panel.add(fieldCliente3);
			
			JButton button = new JButton("aggiungi");
			button.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					String s1 = fieldCliente1.getText();
					String s2 = fieldCliente2.getText();
					String s3 = fieldCliente3.getText();
					Cliente nuovo = new Cliente(s1, s2, s3);
					impresa.getAmministrativo().getEsterno().addCliente(nuovo);
				}
			});
			panel.add(button);
			return panel;
		}
		
		private JPanel bottoniCliente() {
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(),"rimozione cliente"));
			panel.add(new JLabel("inserire cf: "));
			fieldCliente = new JTextField(20);
			panel.add(fieldCliente);
			JButton button = new JButton("Rimuovi");
			button.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					impresa.getAmministrativo().getEsterno().removeCliente(fieldCliente.getText());
				}
			});
			panel.add(button);
			return panel;
		}
		
		private JPanel topRightPanel() {
			JPanel panel = new JPanel();
			areaCliente = new JTextArea(10,30);
			areaCliente.setEditable(false);
			JScrollPane scroll = new JScrollPane(areaCliente);
			panel.add(scroll);
			return panel;
		}
		
		private JPanel centerPanel() {
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(), "gestione fornitori"));
			panel.setLayout(new GridLayout(1,2));
			panel.add(centerLeftPanel());
			panel.add(centerRightPanel());
			return panel;
		}
		
		private JPanel centerRightPanel() {
			JPanel panel = new JPanel();
			areaFornitore = new JTextArea(10,30);
			areaFornitore.setEditable(false);
			JScrollPane scroll = new JScrollPane(areaFornitore);
			panel.add(scroll);
			return panel;
		}
		
		private JPanel centerLeftPanel() {
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(4, 1));
			panel.add(bottoniFornitore());
			fieldFornitore1 = new JTextField(30);
			fieldFornitore2 = new JTextField(30);
			fieldFornitore3 = new JTextField(30);
			panel.add(primoCampoFornitore());
			panel.add(secondoCampoFornitore());
			panel.add(terzoCampoFornitore());
			return panel;
		}
		
		private JPanel bottoniFornitore() {
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(),"rimozione fornitore"));
			panel.add(new JLabel("inserire p.iva: "));
			fieldFornitore = new JTextField(15);
			panel.add(fieldFornitore);
			JButton button = new JButton("Rimuovi");
			button.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					impresa.getAmministrativo().getEsterno().removeFornitore(fieldFornitore.getText());
				}
			});
			panel.add(button);
			return panel;
		}
		
		private JPanel primoCampoFornitore() {
			JPanel panel = new JPanel();
			panel.add(new JLabel("inserire nome"));
			fieldFornitore1 = new JTextField(15);
			panel.add(fieldFornitore1);			
			return panel;
		}
		
		private JPanel secondoCampoFornitore() {
			JPanel panel = new JPanel();
			panel.add(new JLabel("inserire p.iva"));
			fieldFornitore2 = new JTextField(15);
			panel.add(fieldFornitore2);			
			return panel;
		}
		
		private JPanel terzoCampoFornitore() {
			JPanel panel = new JPanel();
			panel.add(new JLabel("inserire indirizzo"));
			fieldFornitore3 = new JTextField(17);
			panel.add(fieldFornitore3);
			
			JButton button = new JButton("aggiungi");
			button.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					String s1 = fieldFornitore1.getText();
					String s2 = fieldFornitore2.getText();
					String s3 = fieldFornitore3.getText();
					Fornitore nuovo = new Fornitore(s1, s2, s3);
					impresa.getAmministrativo().getEsterno().addFornitore(nuovo);
				}
			});
			panel.add(button);
			return panel;
		}
		
		private JPanel bottomPanel() {
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(), "gestione enti"));
			panel.setLayout(new GridLayout(1,2));
			panel.add(bottomLeftPanel());
			panel.add(bottomRightPanel());
			return panel;
		}
		
		private JPanel bottomRightPanel() {
			JPanel panel = new JPanel();
			areaEnte = new JTextArea(10,30);
			areaEnte.setEditable(false);
			JScrollPane scroll = new JScrollPane(areaEnte);
			panel.add(scroll);
			return panel;
		}
		
		private JPanel bottomLeftPanel() {
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(4, 1));
			panel.add(bottoniEnte());
			fieldEnte1 = new JTextField(30);
			fieldEnte2 = new JTextField(30);
			fieldEnte3 = new JTextField(30);
			panel.add(primoCampoEnte());
			panel.add(secondoCampoEnte());
			panel.add(terzoCampoEnte());
			return panel;
		}
		
		private JPanel bottoniEnte() {
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(),"rimozione ente"));
			panel.add(new JLabel("inserire p.iva: "));
			fieldEnte = new JTextField(15);
			panel.add(fieldEnte);
			JButton button = new JButton("Rimuovi");
			button.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					impresa.getAmministrativo().getEsterno().removeEnte(fieldEnte.getText());
				}
			});
			panel.add(button);
			return panel;
		}
		
		private JPanel primoCampoEnte() {
			JPanel panel = new JPanel();
			panel.add(new JLabel("inserire nome"));
			fieldEnte1 = new JTextField(15);
			panel.add(fieldEnte1);			
			return panel;
		}
		
		private JPanel secondoCampoEnte() {
			JPanel panel = new JPanel();
			panel.add(new JLabel("inserire p.iva"));
			fieldEnte2 = new JTextField(15);
			panel.add(fieldEnte2);			
			return panel;
		}
		
		private JPanel terzoCampoEnte() {
			JPanel panel = new JPanel();
			panel.add(new JLabel("inserire territorio"));
			fieldEnte3 = new JTextField(17);
			panel.add(fieldEnte3);
			
			JButton button = new JButton("aggiungi");
			button.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					String s1 = fieldEnte1.getText();
					String s2 = fieldEnte2.getText();
					String s3 = fieldEnte3.getText();
					Ente nuovo = new Ente(s1, s2, s3);
					impresa.getAmministrativo().getEsterno().addEnte(nuovo);
				}
			});
			panel.add(button);
			return panel;
		}

}
