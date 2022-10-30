import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLayeredPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Ventana {

	private int largoVentana = 700;
	private int anchoVentana = 700;
	private Logica logica;
	private JFrame frmLaHorda;
	private JTable tablaRanking;
	private DefaultTableModel modelo;
	private JLayeredPane layeredPane;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void initialize() {
		
		frmLaHorda = new JFrame();
		
		frmLaHorda.setResizable(false);
		
		
		frmLaHorda.setBounds(100, 100, 585, 461);
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
		panelAliens.setBackground(new Color(0,0,0,0));
		panelAliens.setBounds(0, 0, frmLaHorda.getBounds().width, 354);
		layeredPane.add(panelAliens);
		panelAliens.setLayout(null);
		
		JLabel Alien = new JLabel();
		Alien.setIcon(new ImageIcon(Ventana.class.getResource("/resources/satelite.gif")));
		Alien.setBounds(365, 54, 70, 70);
		
		
		panelAliens.add(Alien);
		JPanel panelNaves = new JPanel();
		panelNaves.setBackground(new Color(0,0,0,0));
		panelNaves.setBounds(0, 0, frmLaHorda.getBounds().width, 354);
		layeredPane.add(panelNaves);
		panelNaves.setLayout(null);
		
		JLabel Nave = new JLabel("Nave");
		Nave.setBounds(87, 47, 32, 22);
		panelNaves.add(Nave);
		JPanel panelFondo = new JPanel();
		panelFondo.setBackground(new Color(0,0,0,0));
		panelFondo.setBounds(0, 0, frmLaHorda.getBounds().width, 354);
		layeredPane.add(panelFondo);
		JLabel fondo = new JLabel();
		fondo.setBounds(panelFondo.getBounds());
		Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/fondo1.png"));
		Image dimg = image.getScaledInstance(fondo.getBounds().width, fondo.getBounds().height, Image.SCALE_SMOOTH);
		ImageIcon cancer = new ImageIcon(dimg);
		fondo.setIcon(cancer);
		
		panelFondo.add(fondo);
		
		
		frmLaHorda.setVisible(true);
		
	}
}