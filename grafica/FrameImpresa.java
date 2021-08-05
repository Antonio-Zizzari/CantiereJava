package grafica;
import impresa.Impresa;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * Frame iniziale per la gestione di un'impresa edile
 * @author iovino sorrentino zizzari
 *
 */
public class FrameImpresa extends JFrame{
	private static final long serialVersionUID = 142569429272544820L;
	
	private JPanel mainPanel;
	private Impresa impresa;
	
	/**
	 * Costruttore frame iniziale
	 */
	public FrameImpresa() {
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(menubar());
		setTitle("Gestore impresa edile");
		mainPanel = mainpanel();
		add(mainPanel);
	}
	
	private JPanel mainpanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 1));
		panel.add(messaggio());
		panel.add(centralPanel());
		panel.add(operativo());
		panel.add(statistiche());
		return panel;
	}
	
	private JPanel centralPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(amministrativo());
		panel.add(amministrativoEsterno());
		return panel;
	}
	
	private JLabel messaggio() {
		JLabel l = new JLabel();
		l.setText("benvenuti, selezionare il reparto interessato");
		l.setFont(new Font("ciao", Font.ITALIC + Font.BOLD, 30));
		return l;
	}
	
	private JButton amministrativo() {
		JButton b = new JButton("Amministrativo interno");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AmministrativoInternoFrame amministrativoFrame = new AmministrativoInternoFrame(impresa);
				amministrativoFrame.setVisible(true);
			}
		});
		return b;
	}
	
	private JButton amministrativoEsterno() {
		JButton b = new JButton("Amministrativo esterno");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AmministrativoEsternoFrame amministrativoEsternoFrame = new AmministrativoEsternoFrame(impresa);
				amministrativoEsternoFrame.setVisible(true);
			}
		});
		return b;
	}
	
	private JButton operativo() {
		JButton b = new JButton("Operativo");
		b.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				FrameOperativo op = new FrameOperativo(impresa);
				op.setVisible(true);
			}
		});
		return b;
	}
	
	private JButton statistiche() {
		JButton b = new JButton("Statistiche");
		b.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				FrameStatistiche f = new FrameStatistiche(impresa);
				f.setVisible(true);				
			}
		});
		return b;
	}
	
	private JMenuBar menubar() {
		JMenuBar menubar = new JMenuBar();
		menubar.add(jmenu());
		return menubar;
	}
	
	private JMenu jmenu() {
		JMenu menu = new JMenu();
		menu.setText("File");
		menu.add(loadData());
		menu.add(saveData());
		menu.add(newData());
		return menu;
	}
	
	private JMenuItem saveData() {
		JMenuItem m = new JMenuItem("salva impresa su file");
		ActionListener l = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try{
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("impresa.dat"));
					out.writeObject(impresa);
					out.close();
				}
				catch(IOException ioe) {
					ioe.printStackTrace();
				}
			}
		};
		m.addActionListener(l);
		return m;
	}

	private JMenuItem loadData() {
		JMenuItem m = new JMenuItem("carica impresa da file");
		ActionListener l = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File selectedFile = chooser.getSelectedFile();
					try{
						ObjectInputStream in = new ObjectInputStream(new FileInputStream(selectedFile.getAbsolutePath()));
						impresa = (Impresa) in.readObject();
						in.close();
					}
					catch(IOException ioe) {
						ioe.printStackTrace();
					}
					catch(ClassNotFoundException cnfe) {
						cnfe.printStackTrace();
					}
				};
			}
		};
		m.addActionListener(l);
		return m;
	}
	
	private JMenuItem newData() {
		JMenuItem m = new JMenuItem("crea nuova impresa");
		ActionListener l = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				impresa = new Impresa();
			}
		};
		m.addActionListener(l);
		return m;
	}
	
	/* diversa implementazione
	private JMenuItem loadData2() {
		JMenuItem m = new JMenuItem("carica impresa da file");
		ActionListener l = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JFrame loadFrame = new JFrame();
				loadFrame.setTitle("Frame per inserire pathname");
				loadFrame.setSize(700, 120);
				JPanel panel = new JPanel();
				panel.add(new JLabel("inserire pathname: "));
				fieldLoadData = new JTextField(50);
				panel.add(fieldLoadData);
				
				JButton button = new JButton("fatto");
				
				ActionListener l = new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						try{
							ObjectInputStream in = new ObjectInputStream(new FileInputStream(fieldLoadData.getText()));
							impresa = (Impresa) in.readObject();
							in.close();
						}
						catch(IOException ioe) {
							ioe.printStackTrace();
						}
						catch(ClassNotFoundException cnfe) {
							cnfe.printStackTrace();
						}
					}
				};
				
				button.addActionListener(l);
				panel.add(button);
				loadFrame.add(panel);
				loadFrame.setVisible(true);
				loadFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		};
		m.addActionListener(l);
		return m;
	}*/
	
	
	
	
}
