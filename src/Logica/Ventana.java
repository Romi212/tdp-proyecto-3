package Logica;

import Logica.Entidades.ObjetoGrafico;
import Logica.Entidades.SolGrafico;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.Properties;

import javax.sound.sampled.*;
import javax.swing.*;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER;

public class Ventana  {
	private  JLabel fondo;
	private int width = 1016;
	private int height = 623;
	private Logica logica;
	private JFrame frmLaHorda;
	private JLayeredPane layeredPane;
	private Properties p;
	private JPanel panelObjetos;

	private static final int MODO_NORMAL = 0;
	private static final int MODO_EXPERTO = 1;

	private static final int NIVEL_0 = 0;

	private static final int NIVEL_1 = 1;

	private int size = 74;

	private Musica player;

	private LinkedList<JToggleButton> botonera;

	private Casilla[][] tablero;

	private JLabel soles;

	private JPanel panelBotonera;

	private JPanel panelFondo;

	private Font fuente;

	private int modoDeJuego;

	/**
	 * @wbp.parser.entryPoint
	 */

	public Ventana(){
		//Creamos properties para leer las path de las imagenes
		p = new Properties();

		InputStream input = getClass().getResourceAsStream("/resources/archivos/configNivel1.properties");

		try {
			p.load(input);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		tablero = new Casilla[6][9];

		//Cargamos la fuente para el texto
		try {
			String path = p.getProperty("fuente");
			fuente = Font.createFont(Font.TRUETYPE_FONT, new File(path));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(fuente);
		} catch (IOException|FontFormatException e) {
			e.printStackTrace();
		}

	}

	/* Inicializa el frame principal del juego de modo que sea independiente al reinicio del juego */
	public void initialize() {

		frmLaHorda = new JFrame();
		frmLaHorda.setResizable(false);
		frmLaHorda.setSize(width, height);
		frmLaHorda.setLocationRelativeTo(null);  //Centra la ventana en la pantalla
		frmLaHorda.getContentPane().setBackground(new Color(0,0,0,0));
		frmLaHorda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLaHorda.getContentPane().setLayout(null);
		frmLaHorda.setBackground(Color.BLACK);

		iniciarJuego(0);
	}

	/* Inicializa los paneles asi como tambien los componentes de los mismos.
	*  Crea el tablero que permite al usuario colocar una nave desde la botonera.
	*  Inicializa logica y comienza el juego. */
	private void iniciarJuego(int estado){
		int alturaBotonera = 70;
		int inicioTableroX = 238;
		int inicioTableroY = 42;

		botonera = new LinkedList<>();

		//Paneles
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, frmLaHorda.getBounds().width,  frmLaHorda.getBounds().height);
		frmLaHorda.getContentPane().add(layeredPane);
		layeredPane.setVisible(true);

		//Panel de la botonera
		panelBotonera = new JPanel();

		panelBotonera.setBackground(Color.BLACK);
		panelBotonera.setBounds(0, -10, frmLaHorda.getBounds().width, size*2-60);
		panelBotonera.setLayout(null);
		panelBotonera.setOpaque(false);
		panelBotonera.setBackground(null);
		layeredPane.add(panelBotonera);
		panelBotonera.setVisible(false);

		JButton Bmusica = new JButton();
		Bmusica.setBackground(Color.green);
		Bmusica.setOpaque(true);
		Bmusica.setIcon(new ImageIcon(Ventana.class.getResource(p.getProperty("iconoPlay"))));
		Bmusica.setBounds(0,5,50,50);
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
		scrollBotonera.setBounds(250, 11, 400, size*4);
		scrollBotonera.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
		scrollBotonera.setBackground(Color.BLACK);

		JMenuBar menuBotonera = new JMenuBar();
		menuBotonera.setBackground(Color.BLACK);
		GridBagConstraints  gbc = new GridBagConstraints ();
		gbc.weightx = 0.005;
		menuBotonera.setLayout(new GridBagLayout());

		for(int i = 0; i<4; i++){
			JToggleButton botonNave = new JToggleButton();
			botonNave.setBounds(250,10, size, size);
			botonNave.addActionListener(e -> elegirDondeNave(botonNave));
			botonNave.setBorder(null);

			menuBotonera.add(botonNave);
			botonera.add(botonNave);
		}

		scrollBotonera.setViewportView(menuBotonera);
		scrollBotonera.setBounds(300,10, size*5, size+10);
		panelBotonera.add(scrollBotonera);

		//Panel de Objetos
		panelObjetos = new JPanel();

		panelObjetos.setBounds(0, alturaBotonera, frmLaHorda.getBounds().width, frmLaHorda.getBounds().height-alturaBotonera);
		layeredPane.add(panelObjetos);
		panelObjetos.setLayout(null);
		panelObjetos.setOpaque(false);
		panelObjetos.setBackground(null);

		panelFondo = new JPanel();
		panelFondo.setBounds(0, 0, width,height);
		layeredPane.add(panelFondo);
		panelFondo.setBackground(null);
		panelFondo.setOpaque(false);
		panelFondo.setLayout(null);



		//Panel de fondo
		fondo = new JLabel();
		fondo.setBounds(0, -10, 1000, 600);

		ponerFondo(estado);

		panelFondo.add(fondo);


		JLabel solesText = new JLabel(p.getProperty("labelRecolectados"));
		solesText.setFont(fuente.deriveFont(15f));
		solesText.setForeground(Color.white);
		solesText.setBounds(100, 20, 100, 20);

		panelBotonera.add(solesText);
		soles = new JLabel(p.getProperty("recolectadosInicial"));
		soles.setFont(fuente.deriveFont(15f));
		soles.setBounds(190,20,100,20);
		soles.setForeground(Color.white);

		panelBotonera.add(soles);

		//Tablero de casillas
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
		panelBotonera.setVisible(true);
	}

	/* Permite agregar un objeto grafico a la pantalla */
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

	/* Permite eliminar un objeto grafico de la pantalla */
	public void sacarObjeto(ObjetoGrafico o){
		panelObjetos.remove(o);
		panelObjetos.revalidate();
		panelObjetos.repaint();
	}

	/* Actualiza el objeto grafico en pantalla pasado por parametro.
	* Se utiliza cuando un proyectil se mueve, un alien se mueve o cambia de estado */
	public void actualizarGrafico(ObjetoGrafico o){
		if(o.getCambio()){
			String ref = o.getRefImagen();

			ImageIcon ic = new ImageIcon(getClass().getResource(p.getProperty(ref)));
			Image img = ic.getImage();

			Image newImg = img.getScaledInstance(o.getBounds().width, o.getBounds().height, Image.SCALE_DEFAULT);
			ic = new ImageIcon(newImg);
			o.setIcon(ic);
			o.setCambio(false);
		}
		o.repaint();
	}

	/* Cambia la imagen de fondo del panel del fondo segun el estado pasado por parametro (0 fondo inicial, 1 se gano el juego o 2 se perdio el juego). */
	synchronized private void ponerFondo(int estado){
		String clave = switch (estado) {
			case 0 -> "inicioFondo";
			case 1 -> "ganasteFondo";
			case 2 -> "perdisteFondo";
			default -> null;
		};

		Image image = Toolkit.getDefaultToolkit().getImage(Ventana.class.getResource(p.getProperty(clave)));
		Image dimg = image.getScaledInstance(fondo.getBounds().width, fondo.getBounds().height-15, Image.SCALE_SMOOTH);
		ImageIcon fondito = new ImageIcon(dimg);
		panelFondo.setLayout(null);
		panelFondo.setBackground( Color.BLACK);
	/*	JLabel tapa = new JLabel();
		tapa.setBounds(0,-20,width,40);
		tapa.setBackground(Color.BLACK);
		tapa.setOpaque(true);
		panelFondo.add(tapa); */
		fondo.setIcon(fondito);
	}

	/* Muestra una pantalla inicial de tal forma que el usuario pueda elegir el modo que desea jugar, retorna dicho modo. */
	public int elegirModoDeJuego() {
		int toReturn = MODO_NORMAL;
		UIManager.put("OptionPane.background", Color.BLACK);
		UIManager.put("Panel.background", Color.BLACK); 
		UIManager.put("Button.background", Color.LIGHT_GRAY);

		JPanel ventanaModo = new JPanel(new FlowLayout());
		JLabel mensaje = new JLabel("Elija el modo de juego\n");
		mensaje.setFont(fuente.deriveFont(10f));
		mensaje.setForeground(Color.WHITE);
		ventanaModo.add(mensaje);


		JPanel modo = new JPanel();
		modo.setBackground(Color.BLACK);
		modo.setBounds(179,250,133,22);
		ButtonGroup group = new ButtonGroup();

		JRadioButton dia = new JRadioButton("Modo normal");
		dia.setBackground(Color.BLACK);
		dia.setForeground(Color.WHITE);
		dia.setFont(fuente.deriveFont(8f));
		dia.setSelected(true);
		JRadioButton colum = new JRadioButton("Modo experto");
		colum.setBackground(Color.BLACK);
		colum.setForeground(Color.WHITE);
		colum.setFont(fuente.deriveFont(8f));
		group.add(dia);
		group.add(colum);

		modo.add(dia);
		modo.add(colum);

		ventanaModo.add(modo);

		int opcionElegida = JOptionPane.showConfirmDialog(frmLaHorda,ventanaModo,"Elija una opcion...",JOptionPane.DEFAULT_OPTION);

		if(opcionElegida==0 && colum.isSelected()) {  toReturn=MODO_EXPERTO;  }

		modoDeJuego = toReturn;
		organizarVentana(NIVEL_0);


		return toReturn;
	}


    /* Inicializa Properties segun el nivel actual, inserta las imagenes de los botones e inicia el reoductor de musica */
	public void organizarVentana(int nivel){
		InputStream input;
		if(nivel == NIVEL_0){
			input = getClass().getResourceAsStream("/resources/archivos/configNivel1.properties");
		} else{
			input = getClass().getResourceAsStream("/resources/archivos/configNivel2.properties");

		}
		try {
			p.load(input);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		Image image = Toolkit.getDefaultToolkit().getImage(Ventana.class.getResource(p.getProperty("fondo")));
		Image dimg = image.getScaledInstance(fondo.getBounds().width, fondo.getBounds().height-15, Image.SCALE_SMOOTH);
		ImageIcon fondito = new ImageIcon(dimg);
		fondo.setIcon(fondito);
		//Muestra las naves en la botonera
		for(int i =0; i<botonera.size();i++){
			ponerFotoNave(botonera.get(i), i+1);
		}

		//Se inicializa el reproductor y comienza la musica
		player = new Musica(p.getProperty("musica"));
		player.play();
		if(modoDeJuego == MODO_EXPERTO){

			for(int i = 0; i<2; i++){
				System.out.println("ASKHDKAJHDKJSAHDKJAHSD");
				JLabel aste = new JLabel();
				ImageIcon ic = new ImageIcon(getClass().getResource(p.getProperty("debris")));
				Image img = ic.getImage();

				Image newImg = img.getScaledInstance(size, size*6, Image.SCALE_DEFAULT);
				ic = new ImageIcon(newImg);
				aste.setIcon(ic);
				aste.setBounds(235+(i*size), 40, size,size*6);
				panelObjetos.add(aste);

			}
		}

	}

	public void pausarMusica(){
		player.pausar();
	}

	/* Recupera las imagenes del archivo de configuracion para cuando los botones estan seleccionados o no, agrega los tooltip */
	private void ponerFotoNave(JToggleButton botonNave, int nro){
		ImageIcon ic = new ImageIcon(getClass().getResource(p.getProperty("botonNave"+nro)));
		Image img = ic.getImage();

		//Image newImg = img.getScaledInstance(menuBotonera.getHeight(), menuBotonera.getHeight(), Image.SCALE_SMOOTH);
		Image newImg = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
		ic = new ImageIcon(newImg);

		botonNave.setIcon(ic);
		ImageIcon ic2 = new ImageIcon(getClass().getResource(p.getProperty("botonNave"+nro+"S")));
		Image img2 = ic2.getImage();

		//Image newImg2 = img2.getScaledInstance(menuBotonera.getHeight(), menuBotonera.getHeight(), Image.SCALE_SMOOTH);
		Image newImg2 = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
		ic2 = new ImageIcon(newImg2);

		botonNave.setSelectedIcon(ic2);
		String tooltip = p.getProperty("tooltip" + nro);
		botonNave.setToolTipText(tooltip);
	}

	/* Activa el tablero que permite al usuario colocar la nave desde la botonera */
	private void elegirDondeNave(JToggleButton b){

		if(b.isSelected()){
			if(modoDeJuego == MODO_NORMAL){
				for(int i = 0; i<6 ; i++){
					for(int j = 0; j<9;j++){

						tablero[i][j].setEnabled(true);
						tablero[i][j].setBackground(Color.BLACK);

					}
				}
			}
			else{
				for(int i = 0; i<6 ; i++){
					for(int j = 2; j<9;j++){

						tablero[i][j].setEnabled(true);
						tablero[i][j].setBackground(Color.BLACK);

					}
				}
			}
			for(JToggleButton bot : botonera){
				if(bot != b) bot.setEnabled(false);
			}
		}
		else {
			terminoModoPonerPlanta();
		}
	}

	/* Segun la nave que selecciona el usuario escoge el tipo y su precio, que resta los soles acumulados por el jugador.
	*  Le comunica a la logica que cree la nave y la agregue a su fila correspondiente. */
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
				tipo = 4;
				precio = 50;

			}else if ((botonera.get(1).isSelected())) {
				tipo = 1;
				botonera.get(1).setSelected(false);
				precio = 100;
			} else if ((botonera.get(2).isSelected())) {
				tipo = 2;
				botonera.get(2).setSelected(false);
				precio = 200;
			}
			else if ((botonera.get(3).isSelected())) {
				tipo = 3;
				botonera.get(3).setSelected(false);
				precio = 300;
			}
			actualizarSoles((-1)*precio);

			logica.agregarNave(realX, realY, fila, columna, tipo);
			terminoModoPonerPlanta();

		}

	}

	/* Establece que el tablero en el que el usuario selecciona la posicion de la nave en el juego ya no se pueda utilizar */
	private void terminoModoPonerPlanta(){
		for(int i = 0; i<6 ; i++){
			for(int j = 0; j<9;j++){
				tablero[i][j].setEnabled(false);
			}
		}
		habilitarBotones();
	}

	/* Actualiza en la pantalla la cantidad de soles que recolecto el jugador */
	private void actualizarSoles(int cant){

		int cantActual =  Integer.parseInt(soles.getText());
		cantActual+= cant;
		soles.setText(""+cantActual);

		habilitarBotones();

	}

	/* Muestra en pantalla los soles con los que comienza el juegador */
	public void solesIniciales(int cant){
		soles.setText(""+cant);
		habilitarBotones();
	}

	/* Habilita la botonera segun la cantidad de soles con la que cuente el usuario */
	private void habilitarBotones(){
		int cant =  Integer.parseInt(soles.getText());
		botonera.get(0).setEnabled(cant>=50);
		botonera.get(1).setEnabled(cant>=100);
		botonera.get(2).setEnabled(cant>=200);
		botonera.get(3).setEnabled(cant>=300);
	}

	/* Agrega un sol en la pantalla, se utiliza un metodo particular ya que se debe utilizar un actionListener para que el sol se elimine de la pantalla
	* y se sume la cantidad de soles del jugador. */
	public void agregarSol(SolGrafico s){
		agregarObjeto(s);
		s.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				actualizarSoles(s.getCantSol());
				sacarObjeto(s);


				String soundName = "yourSound.wav";
				AudioInputStream audioInputStream = null;
				File m = new File(p.getProperty("ruidito"));
				try {
					audioInputStream = AudioSystem.getAudioInputStream(m);
				} catch (UnsupportedAudioFileException ex) {
					throw new RuntimeException(ex);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
				Clip clip = null;
				try {
					clip = AudioSystem.getClip();
				} catch (LineUnavailableException ex) {
					throw new RuntimeException(ex);
				}
				try {
					clip.open(audioInputStream);
				} catch (LineUnavailableException ex) {
					throw new RuntimeException(ex);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
				clip.start();
			}

		});
		panelObjetos.setComponentZOrder(s,0);
	}

	/* Notifica al usuario que se acerca una horda de aliens */
	public void cartelHorda(){
		JLabel cartel = new JLabel();
		ImageIcon ic = new ImageIcon(getClass().getResource(p.getProperty("notificarHorda")));
		cartel.setIcon(ic);
		cartel.setSize(288,144);
		panelObjetos.add(cartel);
		cartel.setLocation(frmLaHorda.getContentPane().getX()+350,frmLaHorda.getContentPane().getY()+150);
		panelObjetos.setComponentZOrder(cartel,0);

		long start_time = System.currentTimeMillis();
		long current_time = System.currentTimeMillis();
		long time_limit = 1500;
		cartel.setVisible(true);
		while (current_time-start_time<time_limit){
			current_time = System.currentTimeMillis();
		}
		cartel.setVisible(false);
		panelObjetos.remove(cartel);
	}

	/* Notifica al usuario que paso de nivel */
															//CUANDO SE IMPLEMENTE ESTO AGREGARLO AL DIAGRAMA
	public void cartelNivel(int nivel){

	}


	/* Remueve todos los componentes del frame principal, detiene los hilos de ejecucion y reinicia el juego */
	public void finDelJuego(int gane){
		logica.detenerHilos();
		frmLaHorda.remove(layeredPane);
		layeredPane = null;
		player.pausar();
		frmLaHorda.repaint();

		iniciarJuego(gane);
	}

}




