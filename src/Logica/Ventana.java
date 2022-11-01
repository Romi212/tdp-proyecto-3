package Logica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLayeredPane;
import javax.swing.ImageIcon;

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
	
	/**
	 * @wbp.parser.entryPoint
	 */

	public Ventana(){
		//Creamos properties para leer las path de las imagenes
		p = new Properties();
		FileInputStream archivo;
		try {
			File f = new File(Ventana.class.getResource("/resources/config.properties").getPath());
			archivo = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		try {
			p.load(archivo);
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
}