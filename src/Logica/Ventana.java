package Logica;

//import Logica.Entidades.Aliens.Alien;
import Logica.Entidades.ObjetoGrafico;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.*;
//import java.net.URL;
//import java.util.LinkedList;
import java.util.LinkedList;
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

	private int inicioTableroX = 238;
	private int inicioTableroY = 52;

	private int size = 74;

	private Musica player;

	private LinkedList<JToggleButton> botonera;

	private MouseListener mouseListener;
	private Casilla[][] tablero;

	private JLabel soles;

	private JMenuBar menuBotonera;
	/**
	 * @wbp.parser.entryPoint
	 */

	public Ventana(){
		//Creamos properties para leer las path de las imagenes
		p = new Properties();

		InputStream input = getClass().getResourceAsStream("/resources/archivos/configDia.properties");


		try {
			p.load(input);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		tablero = new Casilla[6][9];

		botonera = new LinkedList<>();

	}
	public void initialize() {
		
		frmLaHorda = new JFrame();
		frmLaHorda.setResizable(false);
		frmLaHorda.setBounds(100, 80, 1016, 623);
		frmLaHorda.getContentPane().setBackground(new Color(0,0,0,0));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frmLaHorda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLaHorda.getContentPane().setLayout(null);
		frmLaHorda.setBackground(Color.BLACK);

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
		layeredPane.add(panelBotonera);

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

		JScrollPane scrollBotonera = new JScrollPane();
		scrollBotonera.setBorder(null);
		scrollBotonera.setBounds(250, 11, 400, 60);
		scrollBotonera.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
		scrollBotonera.setBackground(Color.BLACK);

	    menuBotonera = new JMenuBar();
		menuBotonera.setBackground(Color.BLACK);
		GridBagConstraints  gbc = new GridBagConstraints ();
		gbc.weightx = 0.005;
		menuBotonera.setLayout(new GridBagLayout());

		ButtonGroup bg = new ButtonGroup();
		for(int i = 0; i<4; i++){
			JToggleButton botonNave = new JToggleButton();
			botonNave.setBounds(250,10, size,size);
			botonNave.addActionListener(e -> elegirDondeNave(botonNave));
			botonNave.setBorder(null);

			bg.add(botonNave);
			menuBotonera.add(botonNave);
			botonera.add(botonNave);
		}

		scrollBotonera.setViewportView(menuBotonera);
		panelBotonera.add(scrollBotonera);

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

		soles = new JLabel(p.getProperty("labelRecolectados"));
		panelBotonera.add(soles);
		soles.setBounds(100,20,100,20);
		soles.setForeground(Color.white);

		for(int i = 0; i<6 ; i++){
			for(int j = 0; j<9;j++){

				Casilla c = new Casilla(i,j);
				c.addActionListener(e -> agregarNave(c));
				c.setEnabled(false);
				c.setBounds( inicioTableroX+ (size*j),inicioTableroY+(size*i), size,size);
				panelObjetos.add(c);
				tablero[i][j] = c;
			}
		}

		frmLaHorda.setVisible(true);

		logica = new Logica(this, p , alturaBotonera, alturaBotonera, size);
		logica.empezarJuego();

	}

	public void agregarObjeto(ObjetoGrafico o){
		String ref = o.getRefImagen();
		ImageIcon ic = new ImageIcon(getClass().getResource(p.getProperty(ref)));
		Image img = ic.getImage();

		Image newImg = img.getScaledInstance(o.getBounds().width, o.getBounds().height, Image.SCALE_DEFAULT);
		ic = new ImageIcon(newImg);
		o.setIcon(ic);

		panelObjetos.add(o);
		o.repaint();
	}

	public void sacarObjeto(ObjetoGrafico o){
		panelObjetos.remove(o);
		panelObjetos.revalidate();
		panelObjetos.repaint();
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

		if(opcionElegida==0 && elegirModo.getSelectedIndex()==0) {  toReturn=MODO_DIA;  }
		else if(opcionElegida==0 && elegirModo.getSelectedIndex()==1){  toReturn=MODO_NOCHE; }

		organizarVentana(toReturn);
		return toReturn;
	}

    /* Inicializa Properties segun el modo que elige el usuario, inserta las imagenes de los botones e inicia el reoductor de musica */
	private void organizarVentana(int modo){
		InputStream input;
		if(modo == MODO_DIA){
			input = getClass().getResourceAsStream("/resources/archivos/configDia.properties");
		} else{
			input = getClass().getResourceAsStream("/resources/archivos/configNoche.properties");
		}
		try {
			p.load(input);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		//CAMBIAR BOTONES A FOTO Nave DIA
		for(int i =0; i<botonera.size();i++){
			ponerFotoNave(botonera.get(i), i+1);
		}

		//Se inicializa el reproductor y comienza la musica
		player = new Musica(p.getProperty("musica"));
		player.play();

	}
	private void ponerFotoNave(JToggleButton botonNave, int nro){
		ImageIcon ic = new ImageIcon(getClass().getResource(p.getProperty("botonNave"+nro)));
		Image img = ic.getImage();

		Image newImg = img.getScaledInstance(menuBotonera.getHeight(), menuBotonera.getHeight(), Image.SCALE_SMOOTH);
		ic = new ImageIcon(newImg);

		botonNave.setIcon(ic);
		ImageIcon ic2 = new ImageIcon(getClass().getResource(p.getProperty("botonNave"+nro+"S")));
		Image img2 = ic2.getImage();

		Image newImg2 = img2.getScaledInstance(menuBotonera.getHeight(), menuBotonera.getHeight(), Image.SCALE_SMOOTH);
		ic2 = new ImageIcon(newImg2);

		botonNave.setSelectedIcon(ic2);
		botonNave.setToolTipText(p.getProperty("tooltip"+nro));
		//botonNave.setSelectedIcon(new ImageIcon(getClass().getResource(p.getProperty("botonNaveS"))));
	}

	private void elegirDondeNave(JToggleButton b){

		if(b.isSelected()){
			for(int i = 0; i<6 ; i++){
				for(int j = 0; j<9;j++){


					tablero[i][j].setEnabled(true);
					tablero[i][j].setBackground(Color.BLACK);

				}
			}
		}
		else {
			for(int i = 0; i<6 ; i++){
				for(int j = 0; j<9;j++){


					tablero[i][j].setEnabled(false);

				}
			}
		}
	}
	private void agregarNave(Casilla c) {

		int fila = c.getFila();
		int columna = c.getColumna();
		int realX = c.getBounds().x;
		int realY = c.getBounds().y;
		int tipo =0;

		if (fila>-1 && columna >-1 && !logica.isCeldaOcupada(fila, columna)) {

			int precio = 0;
			if (botonera.get(0).isSelected()) {
				botonera.get(0).setSelected(false);
				tipo = 1;
				//Precio = ALGO
			}else if ((botonera.get(1).isSelected())) {
				tipo = 2;
				botonera.get(1).setSelected(false);
			} else if ((botonera.get(2).isSelected())) {
				tipo = 4;
				botonera.get(2).setSelected(false);
			}
			//FIJARSE SI ALCANZA LA PLATA Y RESTARLA
			logica.agregarNave(realX, realY, fila, columna, tipo);
			panelObjetos.removeMouseListener(mouseListener);
		}
	//	System.out.println("Tipo: " + tipo);

	}

	public void actualizarSoles(int cant){
		soles.setText(""+cant);
	}
}




