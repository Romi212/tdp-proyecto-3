package Logica;

//import Logica.Entidades.Aliens.Alien;
import Logica.Entidades.ObjetoGrafico;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
//import java.net.URL;
//import java.util.LinkedList;
import java.util.Properties;

import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import javax.swing.table.DefaultTableModel;

//import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER;

public class Ventana  {

	//private int largoVentana = 700;
	//private int anchoVentana = 700;
	private Logica logica;
	private JFrame frmLaHorda;
	private JLayeredPane layeredPane;
	private Properties p;
	private JPanel panelObjetos;
	private JComboBox elegirModo;

	private static final int MODO_DIA = 0;
	private static final int MODO_NOCHE = 1;
	private int alturaBotonera = 70;

	//private int inicioTableroX = 165;
	//private int inicioTableroY = -25;

	private int size = 74;

	private Musica player;
	private JToggleButton botonNave1;
	private JToggleButton botonNave2;
	private JToggleButton botonNave4;
	private MouseListener mouseListener;
	private Rectangle[][] tablero;

	private JMenuBar menuBotonera;
	/**
	 * @wbp.parser.entryPoint
	 */

	public Ventana(){
		//Creamos properties para leer las path de las imagenes
		p = new Properties();

		InputStream input = getClass().getResourceAsStream("/resources/archivos/config.properties");

		try {
			p.load(input);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		tablero = new Rectangle[6][9];

		for(int i = 0; i<6 ; i++){
			for(int j = 0; j<9;j++){
				Rectangle r = new Rectangle(344 + (size*j),230+(size*i), size,size);
				tablero[i][j] = r;
			}
		}

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
		panelBotonera.setBackground(Color.BLACK);
		panelBotonera.setBounds(0, 0, frmLaHorda.getBounds().width, alturaBotonera);
		panelBotonera.setLayout(null);
		panelBotonera.setOpaque(false);
		panelBotonera.setBackground(null);
		//frmLaHorda.getContentPane().add(panelBotonera);
		layeredPane.add(panelBotonera);



		JScrollPane scrollBotonera = new JScrollPane();
		scrollBotonera.setBorder(null);
		scrollBotonera.setBounds(250, 11, 400, 60);
		scrollBotonera.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
		scrollBotonera.setBackground(Color.BLACK);
		panelBotonera.add(scrollBotonera);

		menuBotonera = new JMenuBar();
		menuBotonera.setBackground(Color.BLACK);
		GridBagConstraints  gbc = new GridBagConstraints ();
		gbc.weightx = 0.005;
		botonNave1 = new JToggleButton();
		menuBotonera.setLayout(new GridBagLayout());

		//Los botones
		botonNave1.setBounds(250,10, size,size);
		botonNave1.addActionListener(e -> elegirDondeNave(botonNave1));
		botonNave1.setBorder(null);

		botonNave2 = new JToggleButton();
		botonNave2.setBounds(350,10, size,size + 30);
		botonNave2.addActionListener(e -> elegirDondeNave(botonNave2));
		botonNave2.setBorder(null);

		botonNave4 = new JToggleButton();
		botonNave4.setBounds(450,10, size,size + 30);
		botonNave4.addActionListener(e -> elegirDondeNave(botonNave4));
		botonNave4.setBorder(null);

		menuBotonera.add(botonNave1, gbc);
		menuBotonera.add(botonNave2, gbc);
		menuBotonera.add(botonNave4, gbc);
		scrollBotonera.setViewportView(menuBotonera);
		//panelBotonera.add(botonNave1);
		//panelBotonera.add(botonNave2);

		JButton Bmusica = new JButton();
		Bmusica.setBackground(Color.green);
		Bmusica.setOpaque(true);
		Bmusica.setIcon(new ImageIcon(Ventana.class.getResource(p.getProperty("iconoPlay"))));
		Bmusica.setBounds(0,0,50,50);
		Bmusica.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if( player.estaReproduciendo() ){
					player.pausar();
					Bmusica.setIcon(new ImageIcon(Ventana.class.getResource(p.getProperty("iconoPausa"))));
				}
				else{
					player.restart();
					Bmusica.setIcon(new ImageIcon(Ventana.class.getResource(p.getProperty("iconoPlay"))));
				}
			}
		});
		panelBotonera.add(Bmusica);

		//Panel de Objetos
		panelObjetos = new JPanel();
		
		panelObjetos.setBounds(0, alturaBotonera, frmLaHorda.getBounds().width, frmLaHorda.getBounds().height-alturaBotonera);
		layeredPane.add(panelObjetos);
		panelObjetos.setLayout(null);
		panelObjetos.setOpaque(false);
		panelObjetos.setBackground(null);





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
		//Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("fondo"));
		Image image = Toolkit.getDefaultToolkit().getImage(Ventana.class.getResource(p.getProperty("fondo")));
		Image dimg = image.getScaledInstance(fondo.getBounds().width, fondo.getBounds().height-15, Image.SCALE_SMOOTH);
		ImageIcon fondito = new ImageIcon(dimg);
		panelFondo.setLayout(null);
		panelFondo.setBackground(null);
		fondo.setIcon(fondito);
		
		panelFondo.add(fondo);




		mouseListener = new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PointerInfo a = MouseInfo.getPointerInfo();
				Point b = a.getLocation();
				int x = (int) b.getX();
				int y = (int) b.getY();
			//	System.out.println("El mouse esta en "+x+" , "+y);
				agregarNave(x,y);
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		};

		frmLaHorda.setVisible(true);

		logica = new Logica(this, p , alturaBotonera, alturaBotonera, size);
		logica.empezarJuego();

	}

	public void agregarObjeto(ObjetoGrafico o){
		String ref = o.getRefImagen();
	 //	System.out.println("La referencia es: " + ref);
		//String ref = "naveAImg";
		ImageIcon ic = new ImageIcon(getClass().getResource(p.getProperty(ref)));
		Image img = ic.getImage();

		Image newImg = img.getScaledInstance(o.getBounds().width, o.getBounds().height, Image.SCALE_DEFAULT);
		ic = new ImageIcon(newImg);
		o.setIcon(ic);

		//Image image = ic.getImage();

		//Image newimg = image.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH);
		//ic = new ImageIcon(newimg);

		//o.setIcon(ic);



		panelObjetos.add(o);
		o.repaint();
	}

	public void sacarObjeto(ObjetoGrafico o){
		panelObjetos.remove(o);
	}

	/* Muestra un dialogo con una comboBox para elegir el modo de juego, inicializa la constante MODO.
	 incializa el reproductor segun corresponda y reproduce la musica */
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
		if(opcionElegida==0 && elegirModo.getSelectedIndex()==0) {
			toReturn=MODO_DIA;
			player = new Musica(p.getProperty("musicaDia"));
			//CAMBIAR BOTONES A FOTO Nave DIA
			ponerFotoNave(botonNave1, "botonNave1");
			ponerFotoNave(botonNave2, "botonNave2");
			ponerFotoNave(botonNave4, "botonNave4");

		}
		else if(opcionElegida==0 && elegirModo.getSelectedIndex()==1){
			toReturn=MODO_NOCHE;
			player = new Musica(p.getProperty("musicaNoche"));
		}

		player.play();
		return toReturn;
	}

	private void ponerFotoNave(JToggleButton botonNave, String clave){
		ImageIcon ic = new ImageIcon(getClass().getResource(p.getProperty(clave)));
		Image img = ic.getImage();

		Image newImg = img.getScaledInstance(menuBotonera.getHeight(), menuBotonera.getHeight(), Image.SCALE_SMOOTH);
		ic = new ImageIcon(newImg);

		botonNave.setIcon(ic);
		ImageIcon ic2 = new ImageIcon(getClass().getResource(p.getProperty(clave+"S")));
		Image img2 = ic2.getImage();

		Image newImg2 = img2.getScaledInstance(menuBotonera.getHeight(), menuBotonera.getHeight(), Image.SCALE_SMOOTH);
		ic2 = new ImageIcon(newImg2);

		botonNave.setSelectedIcon(ic2);
		//botonNave.setSelectedIcon(new ImageIcon(getClass().getResource(p.getProperty("botonNaveS"))));
	}

	private void elegirDondeNave(JToggleButton b){
		if(b.isSelected()){
			panelObjetos.addMouseListener(mouseListener);
		}
		else {
			panelObjetos.removeMouseListener(mouseListener);
		}
	}
	private void agregarNave(int x, int y) {

		int fila = -1;
		int columna = -1;
		int realX = x;
		int realY = y;
		boolean encontro = false;
		int tipo = 0;
		for (int i = 0; i < 6 && !encontro; i++) {
			for (int j = 0; j < 9 && !encontro; j++) {
				if (tablero[i][j].contains(x, y)) {
				//	System.out.println("Se detecto en la fila " + i + " eln col " + j);
					fila = i;
					columna = j;
					encontro = true;
					realX = tablero[i][j].getLocation().x;
					realY = tablero[i][j].getLocation().y;
				}
			}
		}

		if (fila>-1 && columna >-1 && !logica.isCeldaOcupada(fila, columna)) {

			int precio = 0;
			if (botonNave1.isSelected()) {
				botonNave1.setSelected(false);
				tipo = 1;
				//Precio = ALGO
			}else if ((botonNave2.isSelected())) {
				tipo = 2;
				botonNave2.setSelected(false);
			} else if ((botonNave4.isSelected())) {
				tipo = 4;
				botonNave4.setSelected(false);
			}

		}
	//	System.out.println("Tipo: " + tipo);

		//FIJARSE SI ALCANZA LA PLATA Y RESTARLA
		logica.agregarNave(realX - 107, realY - 178, fila, columna, tipo);
		panelObjetos.removeMouseListener(mouseListener);

	}
}




