package Logica;

import Logica.Entidades.Aliens.Alien;
import Logica.Entidades.ObjetoGrafico;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.Properties;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Ventana {

	private int largoVentana = 700;
	private int anchoVentana = 700;
	private Logica logica;
	private JFrame frmLaHorda;
	private JTable tablaRanking;
	private DefaultTableModel modelo;
	private JLayeredPane layeredPane;
	private Properties p;
	private JPanel panelObjetos;
	private JComboBox elegirModo;

	private static final int MODO_DIA=0;
	private static final int MODO_NOCHE=1;

	private int alturaBotonera = 100;

	private int size = 50;

	private Musica player;
	
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

		//Creamos el reproductor de musica
		player = new Musica();

	}
	public void initialize() {
		
		frmLaHorda = new JFrame();
		
		frmLaHorda.setResizable(true);
		
		
		frmLaHorda.setBounds(100, 80, 1016, 623);
		frmLaHorda.getContentPane().setBackground(new Color(0,0,0,0));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frmLaHorda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLaHorda.getContentPane().setLayout(null);



		//Paneles
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, frmLaHorda.getBounds().width,  frmLaHorda.getBounds().height);
		frmLaHorda.getContentPane().add(layeredPane);

		//Panel de la botonera
		JPanel panelBotonera = new JPanel();
		panelBotonera.setBackground(new Color(0,0,0,0));
		panelBotonera.setBounds(0, 0, frmLaHorda.getBounds().width, alturaBotonera);
		//frmLaHorda.getContentPane().add(panelBotonera);
		panelBotonera.setLayout(null);
		layeredPane.add(panelBotonera);
		JLabel Nave2 = new JLabel("");
		Nave2.setIcon(new ImageIcon(Ventana.class.getResource(p.getProperty("naveAImg"))));
		Nave2.setBounds(87, 27, 119, 84);
		panelBotonera.add(Nave2);
		panelBotonera.setLayout(null);
		panelBotonera.setOpaque(false);
		panelBotonera.setBackground(null);

		ImageIcon iconoPausa = new ImageIcon("src/resources/pausa.png");
		JButton Bmusica = new JButton(iconoPausa);
		Bmusica.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if( player.estaReproduciendo() ){
					player.pausar();
					ImageIcon iconoPlay = new ImageIcon("src/resources/play.png");
					Bmusica.setIcon(iconoPlay);
				}
				else{
					player.restart();
					ImageIcon iconoPausa = new ImageIcon("src/resources/pausa.png");
					Bmusica.setIcon(iconoPausa);
				}
			}
		});

		panelBotonera.add(Bmusica);
		player.play();

		//Panel de Objetos
		panelObjetos = new JPanel();
		
		panelObjetos.setBounds(0, alturaBotonera, frmLaHorda.getBounds().width, frmLaHorda.getBounds().height-alturaBotonera);
		layeredPane.add(panelObjetos);
		panelObjetos.setLayout(null);
		panelObjetos.setOpaque(false);
		panelObjetos.setBackground(null);




		JLabel Nave = new JLabel("");
		Nave.setIcon(new ImageIcon(Ventana.class.getResource(p.getProperty("naveAImg"))));
		Nave.setBounds(87, 47, 119, 84);
		//agregarObjeto(Nave);
		panelObjetos.add(Nave);
		JPanel panelFondo = new JPanel();
		//panelFondo.setBackground(new Color(255, 102, 255));
		panelFondo.setBounds(0, 0, 1016,623);
		layeredPane.add(panelFondo);
		panelFondo.setBackground(null);
		panelFondo.setOpaque(false);
		panelFondo.setLayout(null);

		//Panel de fondo
		JLabel fondo = new JLabel();
		fondo.setBounds(0, 0, 1000, 600);
		Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/fondo1.png"));
		Image dimg = image.getScaledInstance(fondo.getBounds().width, fondo.getBounds().height, Image.SCALE_SMOOTH);
		ImageIcon fondito = new ImageIcon(dimg);
		panelFondo.setLayout(null);
		panelFondo.setBackground(null);
		fondo.setIcon(fondito);
		
		panelFondo.add(fondo);

		frmLaHorda.setVisible(true);

		logica = new Logica(this, p , alturaBotonera+5, alturaBotonera, size);
		logica.empezarJuego();

	}

	public void agregarObjeto(ObjetoGrafico o){
		String ref = o.getRefImagen();
		//String ref = "naveAImg";
		ImageIcon ic = new ImageIcon(getClass().getResource(p.getProperty(ref)));
		//Image image = ic.getImage();

		//Image newimg = image.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH);
		//ic = new ImageIcon(newimg);

		o.setIcon(ic);



		panelObjetos.add(o);
		//o.repaint();
	}

	public void sacarObjeto(ObjetoGrafico o){
		panelObjetos.remove(o);
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
		if(opcionElegida==0 && elegirModo.getSelectedIndex()==0) toReturn=MODO_DIA;
		else if(opcionElegida==0 && elegirModo.getSelectedIndex()==1) toReturn=MODO_NOCHE;
		
		return toReturn;
	}

	public void agregarAlien(Alien a){
		String ref = a.getAlienG().getClave();
		ImageIcon ic = new ImageIcon(getClass().getResource(p.getProperty(ref)));

		a.getAlienG().setIcon(ic);
		panelObjetos.add(a.getAlienG());
		a.getAlienG().repaint();


	}
}