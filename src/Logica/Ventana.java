package Logica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.*;
import java.net.URL;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

public class Ventana {

	private int largoVentana = 700;
	private int anchoVentana = 700;
	private Logica logica;
	private JFrame frmLaHorda;
	private JTable tablaRanking;
	private DefaultTableModel modelo;
	private JLayeredPane layeredPane;
	private Properties p;
	private JPanel panelNaves;
	private JComboBox elegirModo;
	
	/**
	 * @wbp.parser.entryPoint
	 */

	public Ventana(){
		//Creamos properties para leer las path de las imagenes
		p = new Properties();

		InputStream input = getClass().getResourceAsStream("/resources/config.properties");

		try {
			p.load(input);
			System.out.println(p.getProperty("naveAImg"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
	public void initialize() {
		
		frmLaHorda = new JFrame();
		
		frmLaHorda.setResizable(false);
		
		
		frmLaHorda.setBounds(100, 100, 579, 439);
		frmLaHorda.getContentPane().setBackground(new Color(0,0,0,0));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frmLaHorda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLaHorda.getContentPane().setLayout(null);
		JPanel panelBotonera = new JPanel();
		panelBotonera.setBackground(new Color(0,0,0,0));
		panelBotonera.setBounds(0, 0, frmLaHorda.getBounds().width, 67);
		frmLaHorda.getContentPane().add(panelBotonera);
		panelBotonera.setLayout(null);
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 67, frmLaHorda.getBounds().width, 354);
		frmLaHorda.getContentPane().add(layeredPane);
		
		JPanel panelAliens = new JPanel();
		
		panelAliens.setBounds(0, 0, frmLaHorda.getBounds().width, 354);
		layeredPane.add(panelAliens);
		panelAliens.setOpaque(false);
		panelAliens.setLayout(null);
		
		JLabel Alien = new JLabel("");
		Alien.setIcon(new ImageIcon(Ventana.class.getResource("/resources/satelite.gif")));
		Alien.setBounds(225, 158, 80, 50);
		panelAliens.add(Alien);
		
		
		panelNaves = new JPanel();
		
		panelNaves.setBounds(0, 0, frmLaHorda.getBounds().width, 354);
		layeredPane.add(panelNaves);
		panelNaves.setLayout(null);
		panelNaves.setOpaque(false);
		panelNaves.setBackground(null);




		JLabel Nave = new JLabel("");
		Nave.setIcon(new ImageIcon(Ventana.class.getResource(p.getProperty("naveAImg"))));
		Nave.setBounds(87, 47, 119, 84);
		panelNaves.add(Nave);
		JPanel panelFondo = new JPanel();
		//panelFondo.setBackground(new Color(255, 102, 255));
		panelFondo.setBounds(0, 0, frmLaHorda.getBounds().width, 354);
		layeredPane.add(panelFondo);
		panelFondo.setBackground(null);
		panelFondo.setOpaque(false);
		panelFondo.setLayout(null);

		JLabel fondo = new JLabel();
		fondo.setBounds(0, 0, 575, 343);
		Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/fondo1.png"));
		Image dimg = image.getScaledInstance(fondo.getBounds().width, fondo.getBounds().height, Image.SCALE_SMOOTH);
		ImageIcon fondito = new ImageIcon(dimg);
		panelFondo.setLayout(null);
		panelFondo.setBackground(null);
		fondo.setIcon(fondito);
		
		panelFondo.add(fondo);
		
		
		frmLaHorda.setVisible(true);

		logica = new Logica(this,p);
		logica.empezarJuego();
		
	}

	public void agregarObjeto(JLabel o){
		panelNaves.add(o);
	}

	public void sacarObjeto(JPanel o){
		panelNaves.remove(o);
	}
	
	public int elegirModoDeJuego() {
		int toReturn = -1;
		UIManager.put("OptionPane.background", Color.BLACK);
		UIManager.put("Panel.background", Color.BLACK); 
		UIManager.put("Button.background", Color.LIGHT_GRAY);
		elegirModo = new JComboBox();
		elegirModo.setModel(new DefaultComboBoxModel(new String[] {"ModoDia", "ModoNoche"}));
		elegirModo.setBounds(179, 183, 133, 22);
		
		JPanel ventanaModo = new JPanel(new FlowLayout());
		ventanaModo.setBackground(Color.BLACK);
		JLabel mensaje = new JLabel("Elija el modo de juego");
		mensaje.setForeground(Color.WHITE);
		ventanaModo.add(mensaje);
		ventanaModo.add(elegirModo);
		elegirModo.setSelectedIndex(0);
		int opcionElegida = JOptionPane.showConfirmDialog(frmLaHorda,ventanaModo,"Elija una opcion...",JOptionPane.OK_CANCEL_OPTION);
		if(opcionElegida==0 && elegirModo.getSelectedIndex()==0) toReturn=0;
		else if(opcionElegida==0 && elegirModo.getSelectedIndex()==1) toReturn=1;
		
		return toReturn;
	}
}