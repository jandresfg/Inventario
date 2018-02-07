package FrontEnd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.Button;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import java.sql.Timestamp;

import BackEnd.Almacen;
import BackEnd.Mundo;
import BackEnd.Proveedor;
import BackEnd.Zapato;
import static BackEnd.Zapato.FORMATO_FECHA;

import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.EventObject;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import javax.xml.ws.handler.MessageContext;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.Color;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.ItemSelectable;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Graphics;
import static java.awt.print.Printable.PAGE_EXISTS;

import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import javax.swing.SwingConstants;

public class Principal extends JFrame implements ActionListener {

	private JFrame frmInventario;
	private Mundo mundo;
	private JTable table;
	private Button button;
	private Button buttonReposiciones;
	private Button button_1;
	private Button button_2;
	private Button button_3;
	private Button button_4;

	private TableCellRenderer tcr;
	private JTextField filterText;
	private TableRowSorter sorter;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private JRadioButton rdbtnGlobal = new JRadioButton("Global");
	private JRadioButton rdbtnDama = new JRadioButton("Dama");
	private JRadioButton rdbtnCaballero = new JRadioButton("Caballero");
	private JRadioButton rdbtnInfatil = new JRadioButton("Infatil");
	private boolean estoyEnTotales = false;
	private JPanel panel_filter;
	private JLabel lblTextoDeFiltro;
	private JPanel  panel_filterReposiciones;
	private JCheckBox checkBoxREP ;
	private JCheckBox   checkBoxPedidos;
	private JCheckBox checkBox_2Repo ;
	private JCheckBox checkBox_3Pedidos;
//	private JComboBox<String> //comboBox_1;
	private ItemListener itemListener;
	private static final String FORMATO_FECHA = "dd-MMM-yyyy";
	private boolean agregado;
	private static String actualDate = "";
	private boolean inicial ;
	private boolean maximo ;
	private JDatePickerImpl datePicker;
	private JDatePickerImpl datePicker2;
	private  JPanel panel_fechas;
	private boolean estoyAlmacenes ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmInventario.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() 
	{
		mundo = new Mundo();

		initialize();

		////////El mismo metodo de cuando se oprime el boton "Zapatos"
		setModelToZapatos();
		////////

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		inicial=true;
		maximo=false;
		agregado=false;
		frmInventario = new JFrame();
		frmInventario.setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/resources/icon.jpg")));
		frmInventario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmInventario.setTitle("Inventario");
		frmInventario.getContentPane().setBackground(UIManager.getColor("MenuBar.background"));
		frmInventario.setBackground(Color.GRAY);
		frmInventario.setBounds(100, 100, 1280, 768);
		frmInventario.setResizable(false);
		Principal.this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JFrame.setDefaultLookAndFeelDecorated(true);
		frmInventario.getContentPane().setLayout(null);
		estoyAlmacenes = false;

		UtilDateModel model = new UtilDateModel();
		//model.setDate(20,04,2014);
		// Need this...
		Properties p = new Properties();
		p.put("text.today", "Hoy");
		p.put("text.month", "Mes");
		p.put("text.year", "AÃ±o");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);


		UtilDateModel modela = new UtilDateModel();
		//model.setDate(20,04,2014);
		// Need this...
		Properties pp = new Properties();
		pp.put("text.today", "Hoy");
		pp.put("text.month", "Mes");
		pp.put("text.year", "AÃ±o");
		JDatePanelImpl datePanel2 = new JDatePanelImpl(modela, pp);

		panel_1 = new JPanel();
		panel_1.setBounds(1090, 335, 184, 176);
		frmInventario.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblFiltros = new JLabel("Filtros");
		lblFiltros.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFiltros.setBounds(71, 0, 46, 14);
		panel_1.add(lblFiltros);




		rdbtnDama.setBounds(33, 75, 109, 23);
		rdbtnDama.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				// Filtar modelo para ver solo dama
				rdbtnGlobal.setSelected(false);

				if (rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
					setModelToGrandesTotalesFiltrados("DAMA");

				}
				else if (rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
					//DAMA && CABALLERO
					setModelToGrandesTotalesFiltradosDoble("DAMA","CABALLERO");

				}
				else if (rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
					//DAMA && CABALLERO
					setModelToGrandesTotalesFiltradosDoble("DAMA","INFANTIL");

				}
				else 		if (!rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
					setModelToGrandesTotalesFiltrados("CABALLERO");

				}

				else if (!rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
					//DAMA && CABALLERO
					setModelToGrandesTotalesFiltradosDoble("CABALLERO","INFANTIL");

				}
				else 	if (!rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
					setModelToGrandesTotalesFiltrados("INFANTIL");

				}
				else if (rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
					//DAMA && CABALLERO
					setModelToGrandesTotalesFiltradosDoble("DAMA","INFANTIL");

				}
				else if (!rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
					//DAMA && CABALLERO
					setModelToGrandesTotalesFiltradosDoble("CABALLERO","INFANTIL");

				}

				else if (rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
					//DAMA && CABALLERO
					setModelToGrandesTotalesFiltradosTriple();

				}
				else 
				{
					setModelToGrandesTotales();

				}
			}
		});
		panel_1.add(rdbtnDama);

		rdbtnCaballero.setBounds(33, 101, 109, 23);
		rdbtnCaballero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				// Filtar modelo para ver solo dama
				rdbtnGlobal.setSelected(false);


				if (!rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
					setModelToGrandesTotalesFiltrados("CABALLERO");

				}
				else if (rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
					//DAMA && CABALLERO
					setModelToGrandesTotalesFiltradosDoble("DAMA","CABALLERO");

				}
				else if (!rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
					//DAMA && CABALLERO
					setModelToGrandesTotalesFiltradosDoble("CABALLERO","INFANTIL");

				}
				else if (rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
					//DAMA && CABALLERO
					setModelToGrandesTotalesFiltradosTriple();

				}
				else 	if (!rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
					setModelToGrandesTotalesFiltrados("INFANTIL");

				}
				else if (rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
					//DAMA && CABALLERO
					setModelToGrandesTotalesFiltradosDoble("DAMA","INFANTIL");

				}
				else if (!rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
					//DAMA && CABALLERO
					setModelToGrandesTotalesFiltradosDoble("CABALLERO","INFANTIL");

				}
				else if (rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
					setModelToGrandesTotalesFiltrados("DAMA");

				}
				else if (rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
					//DAMA && CABALLERO
					setModelToGrandesTotalesFiltradosDoble("DAMA","CABALLERO");

				}
				else if (rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
					//DAMA && CABALLERO
					setModelToGrandesTotalesFiltradosDoble("DAMA","INFANTIL");

				}

				else 
				{
					setModelToGrandesTotales();

				}
			}
		});
		panel_1.add(rdbtnCaballero);

		rdbtnInfatil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				// Filtar modelo para ver solo dama
				rdbtnGlobal.setSelected(false);



				if (!rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
					setModelToGrandesTotalesFiltrados("INFANTIL");

				}
				else if (rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
					//DAMA && CABALLERO
					setModelToGrandesTotalesFiltradosDoble("DAMA","INFANTIL");

				}
				else if (!rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
					//DAMA && CABALLERO
					setModelToGrandesTotalesFiltradosDoble("CABALLERO","INFANTIL");

				}

				else  if (rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
					setModelToGrandesTotalesFiltrados("DAMA");

				}
				else if (rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
					//DAMA && CABALLERO
					setModelToGrandesTotalesFiltradosDoble("DAMA","CABALLERO");

				}
				else if (rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
					//DAMA && CABALLERO
					setModelToGrandesTotalesFiltradosDoble("DAMA","INFANTIL");

				}

				else if (!rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
					setModelToGrandesTotalesFiltrados("CABALLERO");

				}
				else if (rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
					//DAMA && CABALLERO
					setModelToGrandesTotalesFiltradosDoble("DAMA","CABALLERO");

				}
				else if (!rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
					//DAMA && CABALLERO
					setModelToGrandesTotalesFiltradosDoble("DAMA","INFANTIL");

				}



				else if (rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
					//DAMA && CABALLERO
					setModelToGrandesTotalesFiltradosTriple();

				}
				else 
				{
					setModelToGrandesTotales();

				}
			}
		});
		rdbtnInfatil.setBounds(33, 127, 109, 23);
		panel_1.add(rdbtnInfatil);



		rdbtnGlobal.setBounds(33, 153, 109, 23);
		rdbtnGlobal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				rdbtnInfatil.setSelected(false);
				rdbtnCaballero.setSelected(false);
				rdbtnDama.setSelected(false);
				// Filtar modelo para ver solo dama
				setModelToGrandesTotales();
			}
		});
		panel_1.add(rdbtnGlobal);

		checkBox_2Repo = new JCheckBox("Reposiciones");
		checkBox_2Repo.setBounds(33, 21, 109, 23);
		checkBox_2Repo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{

				if(checkBox_3Pedidos.isSelected())
				{
					checkBox_2Repo.setSelected(true);
					checkBox_3Pedidos.setSelected(false);
					if (!rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
						setModelToGrandesTotalesFiltrados("INFANTIL");

					}
					else if (rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
						//DAMA && CABALLERO
						setModelToGrandesTotalesFiltradosDoble("DAMA","INFANTIL");

					}
					else if (!rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
						//DAMA && CABALLERO
						setModelToGrandesTotalesFiltradosDoble("CABALLERO","INFANTIL");

					}

					else  if (rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
						setModelToGrandesTotalesFiltrados("DAMA");

					}
					else if (rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
						//DAMA && CABALLERO
						setModelToGrandesTotalesFiltradosDoble("DAMA","CABALLERO");

					}
					else if (rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
						//DAMA && CABALLERO
						setModelToGrandesTotalesFiltradosDoble("DAMA","INFANTIL");

					}

					else if (!rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
						setModelToGrandesTotalesFiltrados("CABALLERO");

					}
					else if (rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
						//DAMA && CABALLERO
						setModelToGrandesTotalesFiltradosDoble("DAMA","CABALLERO");

					}
					else if (!rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
						//DAMA && CABALLERO
						setModelToGrandesTotalesFiltradosDoble("DAMA","INFANTIL");

					}



					else if (rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
						//DAMA && CABALLERO
						setModelToGrandesTotalesFiltradosTriple();

					}
					else
					{
						setModelToGrandesTotales();

					}



				}
				else
				{
					checkBox_2Repo.setSelected(true);


				}
			}
		});

		panel_1.add(checkBox_2Repo);

		checkBox_3Pedidos = new JCheckBox("Pedidos");
		checkBox_3Pedidos.setBounds(33, 47, 109, 23);
		checkBox_3Pedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{

				if(checkBox_2Repo.isSelected())
				{
					checkBox_3Pedidos.setSelected(true);
					checkBox_2Repo.setSelected(false);
					if (!rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
						setModelToGrandesTotalesFiltrados("INFANTIL");

					}
					else if (rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
						//DAMA && CABALLERO
						setModelToGrandesTotalesFiltradosDoble("DAMA","INFANTIL");

					}
					else if (!rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
						//DAMA && CABALLERO
						setModelToGrandesTotalesFiltradosDoble("CABALLERO","INFANTIL");

					}

					else  if (rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
						setModelToGrandesTotalesFiltrados("DAMA");

					}
					else if (rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
						//DAMA && CABALLERO
						setModelToGrandesTotalesFiltradosDoble("DAMA","CABALLERO");

					}
					else if (rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
						//DAMA && CABALLERO
						setModelToGrandesTotalesFiltradosDoble("DAMA","INFANTIL");

					}

					else if (!rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
						setModelToGrandesTotalesFiltrados("CABALLERO");

					}
					else if (rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
						//DAMA && CABALLERO
						setModelToGrandesTotalesFiltradosDoble("DAMA","CABALLERO");

					}
					else if (!rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
						//DAMA && CABALLERO
						setModelToGrandesTotalesFiltradosDoble("DAMA","INFANTIL");

					}



					else if (rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
						//DAMA && CABALLERO
						setModelToGrandesTotalesFiltradosTriple();

					}

					else 
					{
						setModelToGrandesTotales();

					}

				}
				else
				{
					checkBox_3Pedidos.setSelected(true);



				}

			}
		});






		panel_1.add(checkBox_3Pedidos);




		JSeparator separator = new JSeparator();
		separator.setBounds(0, 70, 184, 20);
		Dimension d = separator.getPreferredSize();
		d.height = panel_1.getPreferredSize().height;
		separator.setPreferredSize(d);

		panel_1.add(separator);


		panel_fechas = new JPanel();
		panel_fechas.setBounds(1090, 513, 184, 189);
		panel_fechas.setLayout(null);
		frmInventario.getContentPane().add(panel_fechas);


		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(5, 15, 0, 2);
		separator_1.setPreferredSize(new Dimension(0, 2));
		panel_fechas.add(separator_1);

		JLabel label_1 = new JLabel("Fecha Final");
		label_1.setBounds(63, 58, 65, 15);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_fechas.add(label_1);

		JButton button_5 = new JButton("Filtrar por Fecha");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String fech = datePicker.getJFormattedTextField().getText();
				String fech2 = datePicker2.getJFormattedTextField().getText();
				System.out.println(fech);
				System.out.println(fech2);

				Date fecha = null ;
				Date fecha2 = null;
				try {
					fecha=	 new SimpleDateFormat(FORMATO_FECHA).parse(fech);
					fecha2=	 new SimpleDateFormat(FORMATO_FECHA).parse(fech2);
				} catch (ParseException e) 
				{
					JOptionPane.showMessageDialog(Principal.this, "Error en el campo Fecha", "Filtrar por fecha", JOptionPane.ERROR_MESSAGE);
				}


				if(estoyEnTotales)
				{
					if(fecha.equals(fecha2))
					{
						String papitas =  new SimpleDateFormat(FORMATO_FECHA, new Locale("es", "ES")).format(fecha);
						papitas =papitas.toLowerCase();
						if (!filterText.getText().equals("")&&!filterText.getText().equals(" ") && filterText.getText().length()>0)
						{

							setModelToTotalesPorAlmacenFabulosoConFecha(papitas,filterText.getText());  			    

						}
						else
						{

//							//comboBox_1.removeAllItems();
	//						//comboBox_1.setVisible(false);
							inicial=true;
							setModelToTotalesPorAlmacenConFecha(fecha);  		
						}
					}
					else if (fecha.before(fecha2))
					{
						String papitas =  new SimpleDateFormat(FORMATO_FECHA, new Locale("es", "ES")).format(fecha);
						String roscones =  new SimpleDateFormat(FORMATO_FECHA, new Locale("es", "ES")).format(fecha2);
						roscones= roscones.toLowerCase();
						papitas =papitas.toLowerCase();
						if (!filterText.getText().equals("")&&!filterText.getText().equals(" ") && filterText.getText().length()>0)
						{

							setModelToTotalesPorAlmacenFabulosoConDosFechas(fecha,fecha2,filterText.getText(),papitas + " - "+ roscones);  			    

						}
						else
						{

	//						//comboBox_1.removeAllItems();
		//					//comboBox_1.setVisible(false);
							inicial=true;
							setModelToTotalesPorAlmacenConDosFechas(fecha,fecha2);  		

						}

					}
					else
					{
						JOptionPane.showMessageDialog(Principal.this, "La fecha final tiene que ser mayor que la fecha inicial", "Filtrar por fecha", JOptionPane.ERROR_MESSAGE);

					}

				}
				else
				{
					if(fecha.equals(fecha2))
					{
						//MISMO DIA PUEDE SER UN SOLO DIA
						String papitas =  new SimpleDateFormat(FORMATO_FECHA, new Locale("es", "ES")).format(fecha);
						if (!rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
							setModelToGrandesTotalesFiltradosUnaFecha("INFANTIL",papitas);
						}
						else if (rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
							//DAMA && CABALLERO
							setModelToGrandesTotalesFiltradosDobleUnaFechas("DAMA","INFANTIL",papitas);
						}
						else if (!rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
							//DAMA && CABALLERO
							setModelToGrandesTotalesFiltradosDobleUnaFechas("CABALLERO","INFANTIL",papitas);
						}
						else  if (rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
							setModelToGrandesTotalesFiltradosUnaFecha("DAMA",papitas);
						}
						else if (rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
							//DAMA && CABALLERO
							setModelToGrandesTotalesFiltradosDobleUnaFechas("DAMA","CABALLERO",papitas);
						}
						else if (rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
							//DAMA && CABALLERO
							setModelToGrandesTotalesFiltradosDobleUnaFechas("DAMA","INFANTIL",papitas);
						}

						else if (!rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
							setModelToGrandesTotalesFiltradosUnaFecha("CABALLERO",papitas);
						}
						else if (rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
							//DAMA && CABALLERO
							setModelToGrandesTotalesFiltradosDobleUnaFechas("DAMA","CABALLERO",papitas);
						}
						else if (!rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
							//DAMA && CABALLERO
							setModelToGrandesTotalesFiltradosDobleUnaFechas("DAMA","INFANTIL",papitas);
						}
						else if (rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
							//DAMA && CABALLERO
							setModelToGrandesTotalesFiltradosTripleUnaFecha(papitas);
						}
						else
						{
							setModelToGrandesTotalesUnaFecha(papitas);
						}
					}
					else if (fecha.before(fecha2))
					{

						if (!rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
							setModelToGrandesTotalesFiltradosDosFechas("INFANTIL",fecha,fecha2);
						}
						else if (rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
							//DAMA && CABALLERO
							setModelToGrandesTotalesFiltradosDobleDosFechas("DAMA","INFANTIL",fecha,fecha2);
						}
						else if (!rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
							//DAMA && CABALLERO
							setModelToGrandesTotalesFiltradosDobleDosFechas("CABALLERO","INFANTIL",fecha,fecha2);
						}

						else  if (rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
							setModelToGrandesTotalesFiltradosDosFechas("DAMA",fecha,fecha2);
						}
						else if (rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
							//DAMA && CABALLERO
							setModelToGrandesTotalesFiltradosDobleDosFechas("DAMA","CABALLERO",fecha,fecha2);
						}
						else if (rdbtnDama.isSelected() && !rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
							//DAMA && CABALLERO
							setModelToGrandesTotalesFiltradosDobleDosFechas("DAMA","INFANTIL",fecha,fecha2);
						}
						else if (!rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
							setModelToGrandesTotalesFiltradosDosFechas("CABALLERO",fecha,fecha2);
						}
						else if (rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && !rdbtnInfatil.isSelected()) {
							//DAMA && CABALLERO
							setModelToGrandesTotalesFiltradosDobleDosFechas("DAMA","CABALLERO",fecha,fecha2);
						}
						else if (!rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
							//DAMA && CABALLERO
							setModelToGrandesTotalesFiltradosDobleDosFechas("DAMA","INFANTIL",fecha,fecha2);
						}
						else if (rdbtnDama.isSelected() && rdbtnCaballero.isSelected() && rdbtnInfatil.isSelected()) {
							//DAMA && CABALLERO
							setModelToGrandesTotalesFiltradosTripleDosFechas(fecha,fecha2);
						}
						else
						{
							// DOS FECHAS 
							setModelToGrandesTotalesDosFechas(fecha,fecha2);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(Principal.this, "La fecha final tiene que ser mayor que la fecha inicial", "Filtrar por fecha", JOptionPane.ERROR_MESSAGE);

					}
				}

			}


		});
		button_5.setBounds(0, 130, 183, 23);
		panel_fechas.add(button_5);

		JButton button_6 = new JButton("Todo");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(estoyEnTotales)
				{
					datePicker2.getModel().setValue(null);
					datePicker.getModel().setValue(null);

					if (checkBoxREP.isSelected())
					{
						setModelToTotalesPorAlmacenResposicion();
					}
					else
					{
						setModelToTotalesPorAlmacen();

					}

				}
				else
				{
					rdbtnDama.setSelected(false);
					rdbtnCaballero.setSelected(false);
					rdbtnInfatil.setSelected(false);
					setModelToGrandesTotales();

				}
			}
		});
		button_6.setBounds(0, 164, 183, 23);
		panel_fechas.add(button_6);

		JLabel label = new JLabel("Fecha Inicial");
		label.setBounds(63, 2, 65, 15);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_fechas.add(label);


		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setSize(183, 23);
		datePicker.setLocation(0, 24);

		datePicker.setTextEditable(false);
		panel_fechas.add(datePicker);

		datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
		datePicker2.setSize(183, 23);
		datePicker2.setLocation(0, 84);
		datePicker2.setTextEditable(false);
		
		panel_fechas.add(datePicker2);


		panel_1.setVisible(false);







		panel_filterReposiciones = new JPanel();
		panel_filterReposiciones.setBounds(1090, 335, 184, 183);
		frmInventario.getContentPane().add(panel_filterReposiciones);
		panel_filterReposiciones.setLayout(null);

		JLabel lblFil = new JLabel("Filtros");
		lblFil.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFil.setBounds(71, 0, 46, 14);
		panel_filterReposiciones.add(lblFil);

		checkBoxREP = new JCheckBox("Reposiciones");
		checkBoxREP.setBounds(32, 21, 122, 23);
		checkBoxREP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{

				if(checkBoxPedidos.isSelected())
				{
					checkBoxREP.setSelected(true);
					checkBoxPedidos.setSelected(false);
					setModelToTotalesPorAlmacenResposicion();

				}
				else
				{
					checkBoxREP.setSelected(true);
					setModelToTotalesPorAlmacenResposicion();


				}
			}
		});
		panel_filterReposiciones.add( checkBoxREP);




		checkBoxPedidos= new JCheckBox("Pedidos");
		checkBoxPedidos.setBounds(32, 59, 122, 23);

		checkBoxPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{

				if(checkBoxREP.isSelected())
				{
					checkBoxPedidos.setSelected(true);
					setModelToTotalesPorAlmacen();
					checkBoxREP.setSelected(false);

				}
				else
				{
					checkBoxPedidos.setSelected(true);
					setModelToTotalesPorAlmacen();



				}

			}
		});


		panel_filterReposiciones.add(checkBoxPedidos);

		panel_filterReposiciones.setVisible(false);

		panel = new JPanel();
		panel.setBorder(UIManager.getBorder("List.focusCellHighlightBorder"));
		panel.setBounds(0, 32, 1093, 670);
		frmInventario.getContentPane().add(panel);
		panel.setLayout(null);

		table = new JTable();
		tcr = table.getDefaultRenderer(Object.class);

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 1091, 670);
		panel.add(scrollPane);
		try {
			Image i = ImageIO.read(getClass().getResource("/resources/add_shoe.png"));
			ImageIcon ii = new ImageIcon(i);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Error al obtener add_shoe");
			e1.printStackTrace();
		}

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 1093, 32);
		frmInventario.getContentPane().add(panel_2);

		button = new Button("Zapatos");
		panel_2.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setModelToZapatos();
				panel_filterReposiciones.setVisible(false);
			}
		});

		button.setBounds(10, 0, 70, 22);

		buttonReposiciones = new Button("Reposiciones");
		panel_2.add(buttonReposiciones);
		buttonReposiciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setModelToReposiciones();
				panel_filterReposiciones.setVisible(false);
			}
		});


		button_1 = new Button("Almacenes");
		panel_2.add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				setModelToAlmacenes();
				panel_filterReposiciones.setVisible(false);

			}
		});
		button_1.setBounds(108, 0, 70, 22);

		button_2 = new Button("Proveedores");
		panel_2.add(button_2);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setModelToProveedores();
				panel_filterReposiciones.setVisible(false);

			}
		});
		button_2.setBounds(201, 0, 70, 22);

		button_3 = new Button("Totales por Almacen");
		panel_2.add(button_3);

		button_4 = new Button("Totales");
		panel_2.add(button_4);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				datePicker2.getModel().setValue(null);
				datePicker.getModel().setValue(null);
				setModelToGrandesTotales();
				rdbtnGlobal.setSelected(true);
				panel_filterReposiciones.setVisible(false);
				checkBox_3Pedidos.setSelected(true);
				checkBox_2Repo.setSelected(false);
				panel_filter.setVisible(false);
	//			//comboBox_1.setVisible(false);

			}
		});
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				datePicker2.getModel().setValue(null);
				datePicker.getModel().setValue(null);
				setModelToTotalesPorAlmacen();
				panel_filter.setVisible(true);
				panel_filterReposiciones.setVisible(true);



				checkBoxPedidos.setSelected(true);		
				checkBoxREP.setSelected(false);

			}
		});

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(1090, 0, 184, 342);
		frmInventario.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JLabel lblOpciones = new JLabel("Opciones");
		lblOpciones.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblOpciones.setBounds(65, 11, 72, 14);
		panel_3.add(lblOpciones);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(2, 30, 175, 301);
		panel_3.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 5, 5));

		JButton btnNewButton = new JButton();
		panel_5.add(btnNewButton);
		btnNewButton.setToolTipText("Agregar Zapato");
		btnNewButton.setIcon(new ImageIcon(Principal.class.getResource("/resources/add_shoe.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{

				DialogoAgregarZapto daam = new DialogoAgregarZapto(Principal.this);
				daam.setVisible(true);

			}
		});

		btnNewButton.setBounds(0, 296, 135, 31);


		JButton btnEliminarZapato = new JButton("");
		btnEliminarZapato.setIcon(new ImageIcon(Principal.class.getResource("/resources/del_shoe.png")));
		btnEliminarZapato.setToolTipText("Eliminar Zapato");
		panel_5.add(btnEliminarZapato);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(Principal.class.getResource("/resources/add_store.png")));
		btnNewButton_1.setToolTipText("Agregar almacen");
		panel_5.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{

				DialogoAgregrarAlmacen daam = new DialogoAgregrarAlmacen(Principal.this);
				daam.setVisible(true);

			}
		});
		btnNewButton_1.setBounds(145, 296, 135, 31);

		JButton btnEliminarAlmacen = new JButton("");
		btnEliminarAlmacen.setIcon(new ImageIcon(Principal.class.getResource("/resources/del_store.png")));
		btnEliminarAlmacen.setToolTipText("Eliminar Almacen");
		panel_5.add(btnEliminarAlmacen);

		btnEliminarAlmacen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				DialogoEliminarAlmacen asd= new DialogoEliminarAlmacen(Principal.this);
				asd.setVisible(true);
			}
		});

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon(Principal.class.getResource("/resources/add_supplier.png")));
		btnNewButton_2.setToolTipText("Agregar proveedor");
		panel_5.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				DialogoAgregrarProveedor dap = new  DialogoAgregrarProveedor(Principal.this);
				dap.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(290, 296, 166, 31);



		JButton btnEliminarProveedor = new JButton("");
		btnEliminarProveedor.setIcon(new ImageIcon(Principal.class.getResource("/resources/del_supplier.png")));
		btnEliminarProveedor.setToolTipText("Eliminar Proveedor");
		panel_5.add(btnEliminarProveedor);
		btnEliminarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				DialogoEliminarProveedor  dez= new DialogoEliminarProveedor(Principal.this);
				dez.setVisible(true);
			}
		});

		JButton btnAgregarReposicin = new JButton("");
		btnAgregarReposicin.setIcon(new ImageIcon(Principal.class.getResource("/resources/add_replacement.png")));
		btnAgregarReposicin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogoAgregarReposicion dar = new DialogoAgregarReposicion(Principal.this);
				dar.setVisible(true);
			}
		});
		btnAgregarReposicin.setToolTipText("Agregar reposici\u00F3n");
		panel_5.add(btnAgregarReposicin);

		JButton btnEliminarReposicion = new JButton("");
		btnEliminarReposicion.setIcon(new ImageIcon(Principal.class.getResource("/resources/del_replacement.png")));
		btnEliminarReposicion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogoEliminarReposicion der = new DialogoEliminarReposicion(Principal.this);
				der.setVisible(true);
			}
		});
		btnEliminarReposicion.setToolTipText("Eliminar Reposicion");
		panel_5.add(btnEliminarReposicion);

		JButton mntmGuardar = new JButton("");
		mntmGuardar.setIcon(new ImageIcon(Principal.class.getResource("/resources/save.png")));
		mntmGuardar.setToolTipText("Guardar");
		panel_5.add(mntmGuardar);
		mntmGuardar.setActionCommand( "Guardar" );
		mntmGuardar.addActionListener( this );

		JButton mntmImprimir = new JButton("");
		mntmImprimir.setIcon(new ImageIcon(Principal.class.getResource("/resources/printer1.png")));
		mntmImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mntmImprimir.setToolTipText("Imprimir");
		mntmImprimir.setActionCommand("PRINT");
		mntmImprimir.addActionListener(this);
		panel_5.add(mntmImprimir);
		btnEliminarZapato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				DialogoEliminarZapto dez= new DialogoEliminarZapto(Principal.this);
				dez.setVisible(true);
			}
		});

		agregarCombobox();

		panel_filter = new JPanel();
		panel_filter.setLayout(null);
		panel_filter.setBounds(0, 707, 1037, 32);
		frmInventario.getContentPane().add(panel_filter);

		lblTextoDeFiltro = new JLabel("Filtrar por Almac\u00E9n:");
		lblTextoDeFiltro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTextoDeFiltro.setBounds(10, 0, 150, 26);
		panel_filter.add(lblTextoDeFiltro);

		filterText = new JTextField();
		filterText.setColumns(10);
		filterText.setBounds(117, 4, 910, 22);
		filterText.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				newFilter();						
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				newFilter();						
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				newFilter();						
			}
		});
		filterText.setVisible(false);
		panel_filter.add(filterText);
		panel_filter.setVisible(false);

	//	//comboBox_1 = new JComboBox();
		////comboBox_1.setBounds(1040, 710, 235, 22);
	//	frmInventario.getContentPane().add(//comboBox_1);
		////comboBox_1.setVisible(false);
		itemListener = new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				int state = itemEvent.getStateChange();
				if(state == ItemEvent.SELECTED&& !itemEvent.getItem().equals("TODO")&&!inicial&&!maximo)
				{
					System.out.println("FECHACAMBIO");	
					actualDate=(String) itemEvent.getItem();
					System.out.println("A:" + actualDate);	

					setModelToTotalesPorAlmacenFabulosoConFecha(itemEvent.getItem(),filterText.getText());

				}
				else if(state == ItemEvent.SELECTED&& itemEvent.getItem().equals("TODO")&&inicial)
				{

					System.out.println("INICIAL");	
					//setModelToTotalesPorAlmacenFabuloso(filterText.getText());
					inicial=false;
				}         
				else if(state == ItemEvent.SELECTED&& itemEvent.getItem().equals("TODO")&&!maximo)
				{
					System.out.println("ESPICHOOOO TODO");	
					maximo=true;
					setModelToTotalesPorAlmacenFabuloso(filterText.getText());

				}

			}
		};

	//	//comboBox_1.addItemListener(itemListener);


		actualizarTotales("Seleccione una referencia");
		table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);




	}





	private void agregarCombobox() {

	}

	protected void actualizarTotales(String referencia) {
		if (referencia.equals("Seleccione una referencia")) {
		}else{
			int[] res = mundo.darTotalesReferencia(referencia);
		}
	}

	/**
	 * Manejo de los eventos de los botones
	 * @param e Acción que generó el evento.
	 */
	public void actionPerformed( ActionEvent e )
	{
		String accion = e.getActionCommand( );
		if( accion.equals( "PRINT" ) ){
			System.out.println("imprimir");
			try{
				//boolean success = table.print();
				MessageFormat headerFormat = new MessageFormat( actualDate );
				MessageFormat pageNumber ;

				if ( estoyAlmacenes )
				{

					StringBuilder builder = new StringBuilder();
					builder.append("CALLE 124 N° 15 - 47 BOGOTA.         TELEFONOS: 2153326/22 - 2154818         FAX: 21587799 - 7183159 ");



					pageNumber = new MessageFormat(builder.toString());                         
				}
				else
				{
					pageNumber = new MessageFormat("- {0} -");
				}

				boolean success = table.print(PrintMode.FIT_WIDTH,headerFormat, pageNumber,
						true, null,
						true, null);

				if(success){
					JOptionPane.showMessageDialog(this, "Impresión exitosa", "Información", JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(this, "Impresión Fallida", "Información", JOptionPane.INFORMATION_MESSAGE);
				}
			}catch (PrinterException e2) {
				JOptionPane.showMessageDialog(this, "Erorr al intentar imprimir", "Error", JOptionPane.ERROR_MESSAGE);
			}

		}
		else if( accion.equals( "EXPO" ) )
		{
			try
			{
				String rutaDestino = null;
				boolean confirmado = false;
				while (!confirmado) {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("Seleccione el directorio destino");
					fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					fileChooser.showOpenDialog(this);
					rutaDestino = fileChooser.getSelectedFile().getAbsolutePath();

					confirmado = JOptionPane.showConfirmDialog(this, "¿Guardar export en '"+rutaDestino+"'?")==JOptionPane.YES_OPTION;
				}
				mundo.exportarExcel(rutaDestino);
				JOptionPane.showMessageDialog(this, "Datos exportados exitosamente", "Export existoso", JOptionPane.INFORMATION_MESSAGE);
			}
			catch (Exception x)
			{
				JOptionPane.showMessageDialog(this, "No se ha podido cargar el archivo", "ERROR", JOptionPane.ERROR_MESSAGE);
				x.printStackTrace();
			}
			setModelToZapatos();

		}
		else if(accion.equals( "Provedor" ))
		{
			try
			{
				JFileChooser file=new JFileChooser();
				file.showOpenDialog(this);
				/**abrimos el archivo seleccionado*/
				FileInputStream excel = new FileInputStream( file.getSelectedFile());
				mundo.llenarProveedores(excel);
			}
			catch (Exception x)
			{
				JOptionPane.showMessageDialog(this, "No se ha podido cargar el archivo", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			setModelToProveedores();
		}
		else if(accion.equals( "Guardar Como" ))
		{
			try
			{
				JFileChooser chooser = new JFileChooser( "./data" );
				int returnVal = chooser.showSaveDialog( this );
				if( returnVal == JFileChooser.APPROVE_OPTION )
				{
					String pathArchivo = chooser.getSelectedFile( ).getParent( );
					System.out.println("asd");

					try
					{
						File directorioReporte = new File( pathArchivo );
						if( !directorioReporte.exists( ) )
						{
							directorioReporte.mkdirs( );
						}
						File arch = new File( directorioReporte, "zapatos.txt" );
						File cop = new File("zapatos.txt");
						if(cop.exists())
							Files.copy( cop.toPath(), arch.toPath() );

						File archivoReporte = new File( directorioReporte, "almacenes.txt" );
						File copa =  new File("almacenes.txt");
						if(copa.exists())
							Files.copy(copa.toPath(), archivoReporte.toPath() );


						File arr = new File( directorioReporte, "proveedores.txt" );
						File copas = new File("proveedores.txt");
						if(copas.exists())
							Files.copy( copas.toPath(), arr.toPath() );


						JOptionPane.showMessageDialog(this, "Se ha guardado correctamente");
					}
					catch( Exception ee)
					{
						JOptionPane.showMessageDialog( this, "Problemas al crear el archivo del reporte", "Error", JOptionPane.ERROR_MESSAGE );
					}
				}
			}
			catch (Exception x)
			{
				JOptionPane.showMessageDialog(this, "No se ha podido cargar el archivo", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			setModelToAlmacenes();
		}
		else if(accion.equals( "Guardar" ))
		{
			mundo.guardar();
			agregarCombobox();
		}
		else if(accion.equals( "CARGAR" ))
		{
			try 
			{
				mundo.cargarProveedores();

			} catch (Exception e2) 
			{
				JOptionPane.showMessageDialog(this, "No se pudo cargar los datos asociados a los proveedores", "ERROR", JOptionPane.ERROR_MESSAGE);

				// TODO: handle exception
			}
			try 
			{
				mundo.cargarZapatos();

			} catch (Exception e2) 
			{

				JOptionPane.showMessageDialog(this, "No se pudo cargar los datos asociados a los zapatos", "ERROR", JOptionPane.ERROR_MESSAGE);

				// TODO: handle exception
			}

			try 
			{
				System.out.println("asd");

				mundo.cargarAlmacenes();

			} catch (Exception e2) 
			{

				e2.getStackTrace();
				e2.getMessage();
				JOptionPane.showMessageDialog(this, "No se pudo cargar los datos asociados a los almacenes", "ERROR", JOptionPane.ERROR_MESSAGE);

				// TODO: handle exception
			}
		}




	}

	public void agregarZapato(Zapato zap, ArrayList<String> provs, ArrayList<String> alms, boolean anuncio, String mensaje) 
	{


		zap.setProveedores(mundo.darProveedores(provs));
		zap.setAlmacenes(mundo.darAlmacenes(alms));
		String resultado = mundo.agregarZapato(zap);
		setModelToZapatos();

		if (anuncio){
			JOptionPane.showMessageDialog(this, mensaje);
		}
	}

	public void agregarReposicion(Zapato zap, ArrayList<String> provs, ArrayList<String> alms, boolean anuncio, String mensaje) 
	{


		zap.setProveedores(mundo.darProveedores(provs));
		zap.setAlmacenes(mundo.darAlmacenes(alms));
		String resultado = mundo.agregarReposicion(zap);
		setModelToReposiciones();

		if (anuncio){
			JOptionPane.showMessageDialog(this, mensaje);
		}
	}

	public void agregarAlmacen(Almacen alma) 
	{
		String resultado = mundo.agregarAlmacen(alma);
		setModelToAlmacenes();


		JOptionPane.showMessageDialog(this, resultado);
		// TODO Auto-generated method stub

	}

	public String darCodigo()
	{

		// TODO Auto-generated method stub
		return mundo.darCodigo();
	}

	public void agregarProveedor(Proveedor prov, String flag)
	{
		String resultado = mundo.agregarProovedores(prov,flag);
		setModelToProveedores();
		JOptionPane.showMessageDialog(this, resultado);
		// TODO Auto-generated method stub

	}
	public String verificarCodigoProveedor(Proveedor prov) throws Exception
	{
		String resultado = mundo.verificarCodigoProveedor(prov);
		if(resultado.equals("no") )
		{
			throw new Exception();
		}
		else
		{
			return resultado;
		}
		// TODO Auto-generated method stub

	}



	public void vender(String referencia, int cantidad) 
	{
		mundo.vender(referencia, cantidad);
		setModelToZapatos();
		// TODO Auto-generated method stub

	}

	public void dispose( )
	{
		try
		{
			System.out.println("asdasd");
			mundo.guardar();
			agregarCombobox();
			super.dispose( );
		}
		catch( Exception e )
		{
			setVisible( true );
			int respuesta = JOptionPane.showConfirmDialog( this, "Problemas salvando la información: \n" + e.getMessage( ) + "\n¿Quiere cerrar el programa sin salvar?", "Error", JOptionPane.YES_NO_OPTION );
			if( respuesta == JOptionPane.YES_OPTION )
			{
				super.dispose( );
			}
		}
	}

	public void eliminar(String referencia, String codigoProveedor, String codigoAlmacen, String color,String categoria,String numeracion, int precioVenta)
	{
		String res = mundo.eliminarZapato(referencia, codigoProveedor,codigoAlmacen, color,categoria,numeracion,  precioVenta);
		setModelToZapatos();
		JOptionPane.showMessageDialog(this, res);
		mundo.guardar();
		agregarCombobox();

	}
	public void eliminarProveedor(String codigo) throws Exception
	{
		mundo.eliminarProveedor(Integer.parseInt(codigo));
		setModelToProveedores();
		JOptionPane.showMessageDialog(this, "Se ha eliminado exitosamente el proveedor con código: " + codigo);

		// TODO Auto-generated method stub

	}

	public void eliminarAlmacen(String referencia) throws Exception
	{
		mundo.eliminarAlmacen(referencia);
		setModelToAlmacenes();
		JOptionPane.showMessageDialog(this, "Se ha eliminado exitosamente el almacén de ciudad: " + referencia);

	}

	public void setModelToZapatos(){
		TablaZapatos sol = new TablaZapatos(mundo.darZapatos(), mundo);
		sol.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				mundo.setZapatos(sol.getData(),e.getFirstRow());
                                				mundo.guardar();
				agregarCombobox();
			}
		});
		table.setModel(sol);
		table.setDefaultRenderer(Object.class, new MultipleLines());
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Proveedor")).setPreferredWidth(100);

		JComboBox comboBox = new JComboBox();
		for(Proveedor p:mundo.darProveedores()){
			comboBox.addItem(p.toString());
		}
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Proveedor")).setCellEditor(new DefaultCellEditor(comboBox));
		//table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Almacen")).setPreferredWidth(180);
		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment(JLabel.LEFT);
		leftRenderer.setVerticalAlignment(JLabel.NORTH);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Cantidad")).setCellRenderer(leftRenderer);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		centerRenderer.setVerticalAlignment(JLabel.NORTH);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Fecha de Llegada")).setCellRenderer(centerRenderer);
		table.setFont(new Font("default", Font.LAYOUT_LEFT_TO_RIGHT, 14));


		refrescar();
		button.requestFocus();
		panel.setSize(panel.getWidth(), 670);
		scrollPane.setSize(scrollPane.getWidth(), 670);
		agregarCombobox();
		panel_1.setVisible(false);

		//vaina para que el filtro no influya en este model
		table.setRowSorter(new TableRowSorter<>(table.getModel()));
		rdbtnDama.setSelected(false);
		rdbtnCaballero.setSelected(false);	

		rdbtnGlobal.setSelected(false);
		rdbtnInfatil.setSelected(false);
		estoyEnTotales = false;
		panel_filter.setVisible(false);
		//comboBox_1.setVisible(false);
		actualDate = "ZAPATOS";
		panel_fechas.setVisible(false);



	}

	public void setModelToReposiciones(){
		TablaReposiciones sol = new TablaReposiciones(mundo.darReposiciones(),mundo);
		sol.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				System.out.println("table changed");
				mundo.setReposiciones(sol.getData());
				mundo.guardar();
				agregarCombobox();
			}
		});
		table.setModel(sol);
		table.setDefaultRenderer(Object.class, new MultipleLines());
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Proveedor")).setPreferredWidth(100);
		//table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Almacen")).setPreferredWidth(180);
		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment(JLabel.LEFT);
		leftRenderer.setVerticalAlignment(JLabel.NORTH);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Cantidad")).setCellRenderer(leftRenderer);
		table.setFont(new Font("default", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		centerRenderer.setVerticalAlignment(JLabel.NORTH);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Fecha de Llegada")).setCellRenderer(centerRenderer);
		refrescar();
		buttonReposiciones.requestFocus();
		panel.setSize(panel.getWidth(), 670);
		scrollPane.setSize(scrollPane.getWidth(), 670);
		agregarCombobox();
		panel_1.setVisible(false);

		//vaina para que el filtro no influya en este model
		table.setRowSorter(new TableRowSorter<>(table.getModel()));
		rdbtnDama.setSelected(false);
		rdbtnCaballero.setSelected(false);	
		panel_filter.setVisible(false);

		rdbtnGlobal.setSelected(false);
		rdbtnInfatil.setSelected(false);
		estoyEnTotales = false;
		//comboBox_1.setVisible(false);
		actualDate = "RESPOSICIONES";
		panel_fechas.setVisible(false);

	}

	public void setModelToAlmacenes(){
		TablaAlmacen sol = new TablaAlmacen(mundo.darAlmacenes());
		sol.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				System.out.println("table changed");
				mundo.setAlmacenes(sol.getData());
				mundo.actualizarAlmacenesEnZapatosYReferencias();
				mundo.guardar();
				agregarCombobox();
			}
		});
		table.setModel(sol);
		table.setDefaultRenderer(Object.class, tcr);


		table.setFont(new Font("default", Font.LAYOUT_LEFT_TO_RIGHT, 14));

		button_1.requestFocus();
		panel_1.setVisible(false);

		//vaina para que el filtro no influya en este model
		table.setRowSorter(new TableRowSorter<>(table.getModel()));
		rdbtnDama.setSelected(false);
		rdbtnCaballero.setSelected(false);	
		rdbtnGlobal.setSelected(false);
		panel_filter.setVisible(false);


		rdbtnInfatil.setSelected(false);
		estoyEnTotales = false;
		//comboBox_1.setVisible(false);
		actualDate = "ALMACENES";
		estoyAlmacenes = true;
		panel_fechas.setVisible(false);

	}

	public void setModelToProveedores(){
		TablaProveedor sol = new TablaProveedor(mundo.darProveedores());
		sol.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				System.out.println("table changed");
				mundo.setProveedores(sol.getData());
				mundo.actualizarProveedoresEnZapatosYReferencias();
				mundo.guardar();
				agregarCombobox();
			}
		});
		table.setModel(sol);
		sorter = new TableRowSorter<TablaProveedor>(sol);
		table.setRowSorter(sorter);

		table.setDefaultRenderer(Object.class, tcr);

		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment(JLabel.LEFT);
		leftRenderer.setVerticalAlignment(JLabel.NORTH);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Codigo")).setCellRenderer(leftRenderer);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Codigo")).setMaxWidth(50);
		table.setFont(new Font("default", Font.LAYOUT_LEFT_TO_RIGHT, 14));

		button_2.requestFocus();
		//agrega el panel_filter
		panel_filter.setVisible(true);
		filterText.setVisible(true);
		filterText.setText("");
		lblTextoDeFiltro.setText("Buscar Proveedor:");
		//

		//vaina para que el filtro no influya en este model
		rdbtnDama.setSelected(false);
		rdbtnCaballero.setSelected(false);	
		rdbtnGlobal.setSelected(false);
		rdbtnInfatil.setSelected(false);
		estoyEnTotales = false;
		panel_1.setVisible(false);
		//comboBox_1.setVisible(false);
		panel_fechas.setVisible(false);
		actualDate = "PROVEEDORES";


	}

	public void setModelToTotalesPorAlmacen(){

		TablaTotalesPorAlmacen sol = new TablaTotalesPorAlmacen(mundo.darTotales());
		sorter = new TableRowSorter<TablaTotalesPorAlmacen>(sol);
		table.setModel(sol);
		filterText.setVisible(true);
		lblTextoDeFiltro.setText("Filtrar por Almac\u00E9n:");

		table.setDefaultRenderer(Object.class, tcr);

		button_3.requestFocus();
		panel_1.setVisible(false);
		table.setRowSorter(sorter);
		filterText.setText("");
		estoyEnTotales = true;
		rdbtnDama.setSelected(false);
		panel_filter.setVisible(true);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Fecha")).setMinWidth(70);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Dama")).setMaxWidth(47);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Caballero")).setMaxWidth(55);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Infantil")).setMaxWidth(50);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("CTO UN")).setMaxWidth(55);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("CDN T")).setMaxWidth(55);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("VTA UN")).setMaxWidth(55);
                 table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("REF")).setMaxWidth(65);

		table.setFont(new Font("default", Font.LAYOUT_LEFT_TO_RIGHT, 14));

		rdbtnCaballero.setSelected(false);	
		rdbtnGlobal.setSelected(false);
		rdbtnInfatil.setSelected(false);
		actualDate = "PEDIDOS";
		panel_fechas.setVisible(true);


	}

	public void setModelToTotalesPorAlmacenResposicion(){
		TablaTotalesPorAlmacen sol = new TablaTotalesPorAlmacen(mundo.darTotalesResposicion());
		sorter = new TableRowSorter<TablaTotalesPorAlmacen>(sol);
		table.setModel(sol);
		filterText.setVisible(true);
		lblTextoDeFiltro.setText("Filtrar por Almac\u00E9n:");

		table.setDefaultRenderer(Object.class, tcr);
		button_3.requestFocus();
		panel_1.setVisible(false);
		table.setRowSorter(sorter);
		filterText.setText("");
		estoyEnTotales = true;
		rdbtnDama.setSelected(false);
		panel_filter.setVisible(true);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Fecha")).setMinWidth(76);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Fecha")).setMinWidth(76);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Dama")).setMaxWidth(47);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Caballero")).setMaxWidth(55);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Infantil")).setMaxWidth(50);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("CTO UN")).setMaxWidth(55);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("CDN T")).setMaxWidth(55);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("VTA UN")).setMaxWidth(55);
                 table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("REF")).setMaxWidth(65);

		table.setFont(new Font("default", Font.LAYOUT_LEFT_TO_RIGHT, 14));

		rdbtnCaballero.setSelected(false);	

		rdbtnGlobal.setSelected(false);
		rdbtnInfatil.setSelected(false);

		actualDate = "RESPOSICIONES";
		panel_fechas.setVisible(true);


	}
	public void setModelToTotalesPorAlmacenConFecha(Date fecha)
	{

		TablaTotalesPorAlmacen sol = new TablaTotalesPorAlmacen(mundo.darTotalesFabulososConFecha(fecha,checkBoxREP.isSelected()));
		sorter = new TableRowSorter<TablaTotalesPorAlmacen>(sol);
		table.setModel(sol);
		filterText.setVisible(true);
		lblTextoDeFiltro.setText("Filtrar por Almac\u00E9n:");

		table.setDefaultRenderer(Object.class, tcr);
		button_3.requestFocus();
		panel_1.setVisible(false);
		table.setRowSorter(sorter);
		filterText.setText("");
		estoyEnTotales = true;
		rdbtnDama.setSelected(false);
		panel_filter.setVisible(true);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Fecha")).setMinWidth(76);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Fecha")).setMinWidth(76);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Dama")).setMaxWidth(47);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Caballero")).setMaxWidth(55);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Infantil")).setMaxWidth(50);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("CTO UN")).setMaxWidth(55);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("CDN T")).setMaxWidth(55);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("VTA UN")).setMaxWidth(55);
                table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("REF")).setMaxWidth(65);
                table.setFont(new Font("default", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus, int row, int col) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

				String status = (String)table.getModel().getValueAt(row, 0);
				if ("TOTALES".equals(status) ) {
					setBackground(Color.BLACK);
					setForeground(Color.WHITE);
					setFont(new Font("default", Font.BOLD, 16));

				} else {
					setBackground(table.getBackground());
					setForeground(table.getForeground());
					setFont(new Font("default", Font.LAYOUT_LEFT_TO_RIGHT, 14));

				}       
				return this;
			}   
		});	


		rdbtnCaballero.setSelected(false);	
		rdbtnGlobal.setSelected(false);
		rdbtnInfatil.setSelected(false);
		if (checkBoxREP.isSelected())
		{
			actualDate = "RESPOSICIONES";

		}
		else
		{
			actualDate = "PEDIDOS";

		}
		panel_fechas.setVisible(true);



	}

	public void setModelToTotalesPorAlmacenConDosFechas(Date fecha,Date fehca2)
	{

		TablaTotalesPorAlmacen sol = new TablaTotalesPorAlmacen(mundo.darGrandiososTotalesConDosFecha(fecha,fehca2,checkBoxREP.isSelected()));
		sorter = new TableRowSorter<TablaTotalesPorAlmacen>(sol);
		table.setModel(sol);
		filterText.setVisible(true);
		lblTextoDeFiltro.setText("Filtrar por Almac\u00E9n:");

		table.setDefaultRenderer(Object.class, tcr);
		button_3.requestFocus();
		panel_1.setVisible(false);
		table.setRowSorter(sorter);
		filterText.setText("");
		estoyEnTotales = true;
		rdbtnDama.setSelected(false);
		panel_filter.setVisible(true);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Fecha")).setMinWidth(76);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Fecha")).setMinWidth(76);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Dama")).setMaxWidth(47);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Caballero")).setMaxWidth(55);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Infantil")).setMaxWidth(50);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("CTO UN")).setMaxWidth(55);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("CDN T")).setMaxWidth(55);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("VTA UN")).setMaxWidth(55);
                table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("REF")).setMaxWidth(65);

		table.setFont(new Font("default", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus, int row, int col) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

				String status = (String)table.getModel().getValueAt(row, 0);
				if ("TOTALES".equals(status) ) {
					setBackground(Color.BLACK);
					setForeground(Color.WHITE);
					setFont(new Font("default", Font.BOLD, 16));

				} else {
					setBackground(table.getBackground());
					setForeground(table.getForeground());
					setFont(new Font("default", Font.LAYOUT_LEFT_TO_RIGHT, 14));

				}       
				return this;
			}   
		});	
		rdbtnCaballero.setSelected(false);	
		rdbtnGlobal.setSelected(false);
		rdbtnInfatil.setSelected(false);

		if (checkBoxREP.isSelected())
		{
			actualDate = "RESPOSICIONES";

		}
		else
		{
			actualDate = "PEDIDOS";

		}	
		panel_fechas.setVisible(true);

	}

	public void setModelToGrandesTotales(){
		System.out.println("GRANDES TOTALES");

		TablaGrandesTotales sol = new TablaGrandesTotales(mundo.darGrandesTotales(checkBox_2Repo.isSelected()));
		sorter = new TableRowSorter<TablaGrandesTotales>(sol);

		table.setModel(sol);

		table.setDefaultRenderer(Object.class, tcr);
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus, int row, int col) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

				String status = (String)table.getModel().getValueAt(row, 0);
				if ("TOTAL".equals(status) ) {
					setFont(new Font("default", Font.BOLD, 16));
					setBackground(table.getBackground());
					setForeground(table.getForeground());		        }
				else if ("GASOLINA EXTRA".equals(status))
				{		          
					setFont(new Font("default", Font.BOLD, 16));
					setBackground(table.getBackground());
					setForeground(table.getForeground());           }

				else {
					setBackground(table.getBackground());
					setForeground(table.getForeground());
					setFont(new Font("default", Font.LAYOUT_LEFT_TO_RIGHT, 14));

				}       
				return this;
			}   
		});		
		button_4.requestFocus();
		panel_1.setVisible(true);
		table.setRowSorter(sorter);
		estoyEnTotales = false;
		panel_filter.setVisible(false);
		actualDate = "Pedidos:";
		estoyAlmacenes = false;
		panel_fechas.setVisible(true);


	}

	public void	setModelToGrandesTotalesUnaFecha(String fecha)
	{
		TablaGrandesTotales sol = new TablaGrandesTotales(mundo.darGrandesTotalesUnaFecha( checkBox_2Repo.isSelected(),fecha));
		sorter = new TableRowSorter<TablaGrandesTotales>(sol);

		table.setModel(sol);

		table.setDefaultRenderer(Object.class, tcr);
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus, int row, int col) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

				String status = (String)table.getModel().getValueAt(row, 0);
				if ("TOTAL".equals(status) ) {
					setFont(new Font("default", Font.BOLD, 15));
					setBackground(table.getBackground());
					setForeground(table.getForeground());		        }
				else if ("GASOLINA EXTRA".equals(status))
				{		          
					setFont(new Font("default", Font.BOLD, 15));
					setBackground(table.getBackground());
					setForeground(table.getForeground());           }

				else {
					setBackground(table.getBackground());
					setForeground(table.getForeground());
				}       
				return this;
			}   
		});

		button_4.requestFocus();
		panel_1.setVisible(true);
		table.setRowSorter(sorter);
		estoyEnTotales = false;
		panel_filter.setVisible(false);
		//comboBox_1.setVisible(false);
		actualDate = "Pedidos: " + fecha;
		panel_fechas.setVisible(true);

	}

	public void setModelToGrandesTotalesDosFechas(Date fecha,Date fecha2)
	{
		TablaGrandesTotales sol = new TablaGrandesTotales(mundo.darGrandesTotalesDosFechas( checkBox_2Repo.isSelected(),fecha,fecha2));
		//	TablaGrandesTotales sol = new TablaGrandesTotales(mundo.setModelToHiperDuperTotal( checkBox_2Repo.isSelected()));

		sorter = new TableRowSorter<TablaGrandesTotales>(sol);

		table.setModel(sol);

		table.setDefaultRenderer(Object.class, tcr);
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus, int row, int col) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

				String status = (String)table.getModel().getValueAt(row, 0);
				if ("TOTAL".equals(status) ) {
					setFont(new Font("default", Font.BOLD, 15));
					setBackground(table.getBackground());
					setForeground(table.getForeground());		        }
				else if ("GASOLINA EXTRA".equals(status))
				{		          
					setFont(new Font("default", Font.BOLD, 15));
					setBackground(table.getBackground());
					setForeground(table.getForeground());           }

				else {
					setBackground(table.getBackground());
					setForeground(table.getForeground());
				}       
				return this;
			}   
		});
		button_4.requestFocus();
		panel_1.setVisible(true);
		table.setRowSorter(sorter);
		estoyEnTotales = false;
		panel_filter.setVisible(false);
		//comboBox_1.setVisible(false);

		String fechas1=       new SimpleDateFormat(FORMATO_FECHA, new Locale("es", "ES")).format(fecha);
		String fechas2=       new SimpleDateFormat(FORMATO_FECHA, new Locale("es", "ES")).format(fecha2);


		actualDate = "Pedidos: "+ fechas1 + " - " +fechas2 ;
		estoyAlmacenes = false;
		panel_fechas.setVisible(true);

	}



	public void setModelToGrandesTotalesFiltrados(String filtro){
		System.out.println(filtro);
		TablaGrandesTotales sol = new TablaGrandesTotales(mundo.darGrandesTotalesFiltrado(filtro, checkBox_2Repo.isSelected()));
		sorter = new TableRowSorter<TablaGrandesTotales>(sol);

		table.setModel(sol);

		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus, int row, int col) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

				String status = (String)table.getModel().getValueAt(row, 0);
				if ("TOTAL".equals(status))
				{		          
					setFont(new Font("default", Font.BOLD, 15));
					setBackground(table.getBackground());
					setForeground(table.getForeground());    
				} 


				else {
					setBackground(table.getBackground());
					setForeground(table.getForeground());
				}       
				return this;
			}   
		});		button_4.requestFocus();
		panel_1.setVisible(true);
		table.setRowSorter(sorter);
		estoyEnTotales = false;
		panel_filter.setVisible(false);
		//comboBox_1.setVisible(false);
		actualDate = "Pedidos";

		estoyAlmacenes = false;
		panel_fechas.setVisible(true);

	}

	public void setModelToGrandesTotalesFiltradosDosFechas(String filtro,Date fecha1, Date fecha2){
		System.out.println(filtro);
		TablaGrandesTotales sol = new TablaGrandesTotales(mundo.darGrandesTotalesFiltradoFecha(filtro, checkBox_2Repo.isSelected(),fecha1,fecha2));
		sorter = new TableRowSorter<TablaGrandesTotales>(sol);

		table.setModel(sol);

		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus, int row, int col) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

				String status = (String)table.getModel().getValueAt(row, 0);
				if ("TOTAL".equals(status))
				{		          
					setFont(new Font("default", Font.BOLD, 15));
					setBackground(table.getBackground());
					setForeground(table.getForeground());    
				} 


				else {
					setBackground(table.getBackground());
					setForeground(table.getForeground());
				}       
				return this;
			}   
		});		button_4.requestFocus();
		panel_1.setVisible(true);
		table.setRowSorter(sorter);
		estoyEnTotales = false;
		panel_filter.setVisible(false);
		//comboBox_1.setVisible(false);

		String fechas1=       new SimpleDateFormat(FORMATO_FECHA, new Locale("es", "ES")).format(fecha1);
		String fechas2=       new SimpleDateFormat(FORMATO_FECHA, new Locale("es", "ES")).format(fecha2);


		actualDate = "Pedidos: "+ fechas1 + " - " +fechas2 ;
		estoyAlmacenes = false;
		panel_fechas.setVisible(true);

	}

	public void	setModelToGrandesTotalesFiltradosUnaFecha(String filtro,String fecha1){
		System.out.println(filtro);
		TablaGrandesTotales sol = new TablaGrandesTotales(mundo.darGrandesTotalesFiltradoFechaUnica(filtro, checkBox_2Repo.isSelected(),fecha1));
		sorter = new TableRowSorter<TablaGrandesTotales>(sol);

		table.setModel(sol);

		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus, int row, int col) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

				String status = (String)table.getModel().getValueAt(row, 0);
				if ("TOTAL".equals(status))
				{		          
					setFont(new Font("default", Font.BOLD, 15));
					setBackground(table.getBackground());
					setForeground(table.getForeground());    
				} 


				else {
					setBackground(table.getBackground());
					setForeground(table.getForeground());
				}       
				return this;
			}   
		});		button_4.requestFocus();
		panel_1.setVisible(true);
		table.setRowSorter(sorter);
		estoyEnTotales = false;
		panel_filter.setVisible(false);
		//comboBox_1.setVisible(false);

		actualDate = "Pedidos: "+ fecha1;
		estoyAlmacenes = false;
		panel_fechas.setVisible(true);

	}



	public void setModelToGrandesTotalesFiltradosDoble(String filtroA, String filtroB){
		TablaGrandesTotales sol = new TablaGrandesTotales(mundo.darGrandesTotalesFiltradoDoble(filtroA,filtroB,checkBox_2Repo.isSelected()));
		sorter = new TableRowSorter<TablaGrandesTotales>(sol);

		table.setModel(sol);

		//		table.setDefaultRenderer(Object.class, tcr);
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus, int row, int col) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

				String status = (String)table.getModel().getValueAt(row, 0);
				if ("DAMA".equals(status) ||"CABALLERO".equals(status)|| "INFANTIL".equals(status)
						|| "DAMA + INFANTIL".equals(status)|| "CABALLERO + INFANTIL".equals(status) 
						|| "DAMA + CABALLERO".equals(status) ) {
					setFont(new Font("default", Font.BOLD, 15));
					setBackground(table.getBackground());
					setForeground(table.getForeground());    
				} 
				else if ("TOTAL".equals(status))
				{		          
					setFont(new Font("default", Font.BOLD, 15));
					setBackground(table.getBackground());
					setForeground(table.getForeground());    
				} 


				else {
					setBackground(table.getBackground());
					setForeground(table.getForeground());
				}       
				return this;
			}   
		});

		button_4.requestFocus();
		panel_1.setVisible(true);
		table.setRowSorter(sorter);
		estoyEnTotales = false;
		panel_filter.setVisible(false);
		//comboBox_1.setVisible(false);
		estoyAlmacenes = false;
		panel_fechas.setVisible(true);

	}

	public void setModelToGrandesTotalesFiltradosDobleDosFechas(String filtroA, String filtroB,Date fecha1, Date fecha2){
		TablaGrandesTotales sol = new TablaGrandesTotales(mundo.darGrandesTotalesFiltradoDobleDosFechas(filtroA,filtroB,checkBox_2Repo.isSelected(), fecha1,  fecha2));
		sorter = new TableRowSorter<TablaGrandesTotales>(sol);

		table.setModel(sol);

		//		table.setDefaultRenderer(Object.class, tcr);
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus, int row, int col) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

				String status = (String)table.getModel().getValueAt(row, 0);
				if ("DAMA".equals(status) ||"CABALLERO".equals(status)|| "INFANTIL".equals(status)
						|| "DAMA + INFANTIL".equals(status)|| "CABALLERO + INFANTIL".equals(status) 
						|| "DAMA + CABALLERO".equals(status) ) {
					setFont(new Font("default", Font.BOLD, 15));
					setBackground(table.getBackground());
					setForeground(table.getForeground());    
				} 
				else if ("TOTAL".equals(status))
				{		          
					setFont(new Font("default", Font.BOLD, 15));
					setBackground(table.getBackground());
					setForeground(table.getForeground());    
				} 


				else {
					setBackground(table.getBackground());
					setForeground(table.getForeground());
				}       
				return this;
			}   
		});

		button_4.requestFocus();
		panel_1.setVisible(true);
		table.setRowSorter(sorter);
		estoyEnTotales = false;
		panel_filter.setVisible(false);
		//comboBox_1.setVisible(false);
		String fechas1=       new SimpleDateFormat(FORMATO_FECHA, new Locale("es", "ES")).format(fecha1);
		String fechas2=       new SimpleDateFormat(FORMATO_FECHA, new Locale("es", "ES")).format(fecha2);       
		actualDate = "Pedidos: "+ fechas1 + " - " +fechas2 ;
		estoyAlmacenes = false;
		panel_fechas.setVisible(true);

	}

	public void setModelToGrandesTotalesFiltradosDobleUnaFechas(String filtroA, String filtroB,String fecha1){
		TablaGrandesTotales sol = new TablaGrandesTotales(mundo.darGrandesTotalesFiltradoDobleUnaFechas(filtroA,filtroB,checkBox_2Repo.isSelected(), fecha1));
		sorter = new TableRowSorter<TablaGrandesTotales>(sol);

		table.setModel(sol);

		//		table.setDefaultRenderer(Object.class, tcr);
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus, int row, int col) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

				String status = (String)table.getModel().getValueAt(row, 0);
				if ("DAMA".equals(status) ||"CABALLERO".equals(status)|| "INFANTIL".equals(status)
						|| "DAMA + INFANTIL".equals(status)|| "CABALLERO + INFANTIL".equals(status) 
						|| "DAMA + CABALLERO".equals(status) ) {
					setFont(new Font("default", Font.BOLD, 15));
					setBackground(table.getBackground());
					setForeground(table.getForeground());    
				} 
				else if ("TOTAL".equals(status))
				{		          
					setFont(new Font("default", Font.BOLD, 15));
					setBackground(table.getBackground());
					setForeground(table.getForeground());    
				} 


				else {
					setBackground(table.getBackground());
					setForeground(table.getForeground());
				}       
				return this;
			}   
		});

		button_4.requestFocus();
		panel_1.setVisible(true);
		table.setRowSorter(sorter);
		estoyEnTotales = false;
		panel_filter.setVisible(false);
		//comboBox_1.setVisible(false);
		actualDate = "Pedidos: "+ fecha1;
		estoyAlmacenes = false;
		panel_fechas.setVisible(true);

	}

	public void setModelToGrandesTotalesFiltradosTriple(){

		TablaGrandesTotales sol = new TablaGrandesTotales(mundo.darGrandesTotalesFiltradoTriple("DAMA","CABALLERO","INFANTIL",checkBox_2Repo.isSelected()));
		System.out.println( "TOTAL" +sol.getRowCount());
		sorter = new TableRowSorter<TablaGrandesTotales>(sol);

		table.setModel(sol);



		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus, int row, int col) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

				String status = (String)table.getModel().getValueAt(row, 0);
				if ("DAMA".equals(status) ||"CABALLERO".equals(status)|| "INFANTIL".equals(status) ||  "GASOLINA EXTRA".equals(status)) {
					setFont(new Font("default", Font.BOLD, 15));
					setBackground(table.getBackground());
					setForeground(table.getForeground());    
				} 
				else   if ("TOTAL".equals(status))
				{		          
					setFont(new Font("default", Font.BOLD, 15));
					setBackground(table.getBackground());
					setForeground(table.getForeground());    
				} 



				else {
					setBackground(table.getBackground());
					setForeground(table.getForeground());
				}       
				return this;
			}   
		});		
		button_4.requestFocus();
		table.setRowSorter(sorter);


		panel_1.setVisible(true);
		estoyEnTotales = false;
		//comboBox_1.setVisible(false);

		panel_filter.setVisible(false);
		estoyAlmacenes = false;
		panel_fechas.setVisible(true);

	}

	public void setModelToGrandesTotalesFiltradosTripleUnaFecha(String fecha1){

		TablaGrandesTotales sol = new TablaGrandesTotales(mundo.darGrandesTotalesFiltradoTripleUnaFecha("DAMA","CABALLERO","INFANTIL",checkBox_2Repo.isSelected(),fecha1));
		System.out.println( "TOTAL" +sol.getRowCount());
		sorter = new TableRowSorter<TablaGrandesTotales>(sol);

		table.setModel(sol);



		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus, int row, int col) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

				String status = (String)table.getModel().getValueAt(row, 0);
				if ("DAMA".equals(status) ||"CABALLERO".equals(status)|| "INFANTIL".equals(status) ||  "GASOLINA EXTRA".equals(status)) {
					setFont(new Font("default", Font.BOLD, 15));
					setBackground(table.getBackground());
					setForeground(table.getForeground());    
				} 
				else   if ("TOTAL".equals(status))
				{		          
					setFont(new Font("default", Font.BOLD, 15));
					setBackground(table.getBackground());
					setForeground(table.getForeground());    
				} 



				else {
					setBackground(table.getBackground());
					setForeground(table.getForeground());
				}       
				return this;
			}   
		});		
		button_4.requestFocus();
		table.setRowSorter(sorter);


		panel_1.setVisible(true);
		estoyEnTotales = false;
		//comboBox_1.setVisible(false);

		panel_filter.setVisible(false);
		actualDate = "Pedidos: "+ fecha1;
		estoyAlmacenes = false;
		panel_fechas.setVisible(true);

	}


	public void setModelToGrandesTotalesFiltradosTripleDosFechas(Date fecha1, Date fecha2){

		TablaGrandesTotales sol = new TablaGrandesTotales(mundo.darGrandesTotalesFiltradoTripleDosFechas("DAMA","CABALLERO","INFANTIL",checkBox_2Repo.isSelected(),fecha1,fecha2));
		System.out.println( "TOTAL" +sol.getRowCount());
		sorter = new TableRowSorter<TablaGrandesTotales>(sol);

		table.setModel(sol);



		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus, int row, int col) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

				String status = (String)table.getModel().getValueAt(row, 0);
				if ("DAMA".equals(status) ||"CABALLERO".equals(status)|| "INFANTIL".equals(status) ||  "GASOLINA EXTRA".equals(status)) {
					setFont(new Font("default", Font.BOLD, 15));
					setBackground(table.getBackground());
					setForeground(table.getForeground());    
				} 
				else   if ("TOTAL".equals(status))
				{		          
					setFont(new Font("default", Font.BOLD, 15));
					setBackground(table.getBackground());
					setForeground(table.getForeground());    
				} 



				else {
					setBackground(table.getBackground());
					setForeground(table.getForeground());
				}       
				return this;
			}   
		});		
		button_4.requestFocus();
		table.setRowSorter(sorter);


		panel_1.setVisible(true);
		estoyEnTotales = false;
		//comboBox_1.setVisible(false);

		panel_filter.setVisible(false);
		String fechas1=       new SimpleDateFormat(FORMATO_FECHA, new Locale("es", "ES")).format(fecha1);
		String fechas2=       new SimpleDateFormat(FORMATO_FECHA, new Locale("es", "ES")).format(fecha2);

		estoyAlmacenes = false;
		actualDate = "Pedidos: "+ fechas1 + " - " +fechas2 ;
		panel_fechas.setVisible(true);

	}
	public void setModelToHiperDuperTotal(){

		TablaGrandesTotales sol = new TablaGrandesTotales(mundo.setModelToHiperDuperTotal( checkBox_2Repo.isSelected()));
		sorter = new TableRowSorter<TablaGrandesTotales>(sol);

		table.setModel(sol);

		table.setDefaultRenderer(Object.class, tcr);
		button_4.requestFocus();
		panel_1.setVisible(true);
		table.setRowSorter(sorter);
		estoyEnTotales = false;
		panel_filter.setVisible(false);
		//comboBox_1.setVisible(false);
		actualDate = "Pedidos";
		estoyAlmacenes = false;
		panel_fechas.setVisible(true);

	}





	private void newFilter(){

		if(estoyEnTotales)
		{

			RowFilter<TablaTotalesPorAlmacen, Object> rf = null;
			//If current expression doesn't parse, don't update.
			try {
				rf = RowFilter.regexFilter(filterText.getText(), 0);
			} catch (java.util.regex.PatternSyntaxException e) {
				return;
			}

			System.out.println("TEXO:"  + filterText.getText());	

			if (!filterText.getText().equals("")&&!filterText.getText().equals(" ") && filterText.getText().length()>0)
			{

				setModelToTotalesPorAlmacenFabuloso(filterText.getText());
			}
			else
			{
				//comboBox_1.removeAllItems();
				//comboBox_1.setVisible(false);
				inicial=true;

				if(checkBoxREP.isSelected())
				{
					setModelToTotalesPorAlmacenResposicion();

				}
				else
				{
					setModelToTotalesPorAlmacen();

				}
			}



		}
		else
		{
			RowFilter<TablaProveedor, Object> rf = null;
			try {

				rf = RowFilter.regexFilter(filterText.getText(),1);
			} catch (java.util.regex.PatternSyntaxException e) {
				return;
			}


			sorter.setRowFilter(rf);

			table.setRowSorter(sorter);
			if(table.getRowCount() == 0 )
			{
				rf = null;
				try {

					rf = RowFilter.regexFilter(filterText.getText(),2);
				} catch (java.util.regex.PatternSyntaxException e) {
					return;
				}


				sorter.setRowFilter(rf);

				table.setRowSorter(sorter);

			}
		}
	}

	public void setModelToTotalesPorAlmacenFabuloso(String prefix){

		ArrayList<Object[]> lista = mundo.darGrandiososTotalesCasoRaro(prefix, checkBoxREP.isSelected());
			int cantidadTotal;
			long costoTotal;
			long VentaTotal;

		if(lista.size()>0)
		{
			cantidadTotal= (int) lista.get(lista.size()-1)[8];
			costoTotal= (long) lista.get(lista.size()-1)[9];
			VentaTotal= (long) lista.get(lista.size()-1)[10];
		}
		else
		{
			cantidadTotal=0;
			costoTotal=0;
			VentaTotal=0;
		}
		NumberFormat nf_ge = NumberFormat.getInstance(Locale.GERMAN);
		String number_ge = nf_ge.format(cantidadTotal);

		System.out.println(number_ge);

		TablaTotalesPorAlmacen sol = new TablaTotalesPorAlmacen(lista);
		sorter = new TableRowSorter<TablaTotalesPorAlmacen>(sol);
		table.setModel(sol);
		filterText.setVisible(true);
		//filterText.setCursor(new Cursor(Cursor.TEXT_CURSOR));filterText.requestFocus();

		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Fecha")).setMinWidth(76);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Fecha")).setMinWidth(76);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Dama")).setMaxWidth(47);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Caballero")).setMaxWidth(55);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Infantil")).setMaxWidth(50);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("CTO UN")).setMaxWidth(55);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("CDN T")).setMaxWidth(55);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("VTA UN")).setMaxWidth(55);
                 table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("REF")).setMaxWidth(65);


		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus, int row, int col) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

				String status = (String)table.getModel().getValueAt(row, 0);

				/**        if(row == table.getRowCount()-1 && row == 6)
		        {
				   if(table.getModel().getValueAt(row, row) instanceof Integer){
						setFont(new Font("default", Font.BOLD, 16));
						System.out.println(status);

						}
		        }
				 */

				//        int statusb = (int)table.getModel().getValueAt(row, 6);

				if ("TOTALES".equals(status) ) {
					setBackground(Color.BLACK);
					setForeground(Color.WHITE);
					setFont(new Font("default", Font.LAYOUT_LEFT_TO_RIGHT, 16));
				}

				else {
					setBackground(table.getBackground());
					setForeground(table.getForeground());
					setFont(new Font("default", Font.LAYOUT_LEFT_TO_RIGHT, 14));

				}       
				return this;
			}   
		});			button_3.requestFocus();
		table.setRowSorter(sorter);

		//comboBox_1.removeAllItems();
		//comboBox_1.setVisible(true);
		List<String> fechitas= null;
		if(!checkBoxREP.isSelected())
		{
			fechitas = mundo.getFechas();
		}	
		else
		{
			fechitas = mundo.getFechasRepo();

		}


		for (int i = 0; i < fechitas.size(); i++) {
			String x = (String) fechitas.get(i);
			//comboBox_1.addItem(x);			
		}
		//System.out.println("SELECCIONADO: "+//comboBox_1.getSelectedItem());

		//comboBox_1.addItem("TODO");
		// SELECCIONO TODO

		if(inicial || maximo)
		{
			//comboBox_1.setSelectedIndex(fechitas.size());


			maximo=false;
		}


		panel_1.setVisible(false);
		estoyEnTotales = true;
		rdbtnDama.setSelected(false);
		rdbtnCaballero.setSelected(false);	
		rdbtnGlobal.setSelected(false);
		rdbtnInfatil.setSelected(false);
		filterText.requestFocusInWindow();
		actualDate = "Pedidos";
		estoyAlmacenes = false;
		panel_fechas.setVisible(true);

	}



	public void setModelToTotalesPorAlmacenFabulosoConFecha(Object item,String prefix) {
		TablaTotalesPorAlmacen sol = new TablaTotalesPorAlmacen(mundo.darGrandiososTotalesCasoRaroConFecha( item,prefix, checkBoxREP.isSelected()));
		sorter = new TableRowSorter<TablaTotalesPorAlmacen>(sol);
		table.setModel(sol);
		filterText.setVisible(true);
		//filterText.setCursor(new Cursor(Cursor.TEXT_CURSOR));filterText.requestFocus();

		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus, int row, int col) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

				String status = (String)table.getModel().getValueAt(row, 0);
				if ("TOTALES".equals(status) ) {
					setBackground(Color.BLACK);
					setForeground(Color.WHITE);
					setFont(new Font("default", Font.BOLD, 16));

				} else {
					setBackground(table.getBackground());
					setForeground(table.getForeground());
					setFont(new Font("default", Font.LAYOUT_LEFT_TO_RIGHT, 14));

				}       
				return this;
			}   
		});			button_3.requestFocus();
		table.setRowSorter(sorter);


		panel_1.setVisible(false);
		estoyEnTotales = true;
		rdbtnDama.setSelected(false);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Fecha")).setMinWidth(76);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Dama")).setMaxWidth(47);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Caballero")).setMaxWidth(55);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Infantil")).setMaxWidth(50);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("CTO UN")).setMaxWidth(55);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("CDN T")).setMaxWidth(55);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("VTA UN")).setMaxWidth(55);
		                 table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("REF")).setMaxWidth(65);

                rdbtnCaballero.setSelected(false);	

		rdbtnGlobal.setSelected(false);
		rdbtnInfatil.setSelected(false);

		filterText.requestFocusInWindow();
		actualDate = "Pedidos:" +item;
		estoyAlmacenes = false;
		panel_fechas.setVisible(true);

	}

	public void setModelToTotalesPorAlmacenFabulosoConDosFechas(Date fecha1,Date fecha2 ,String prefix,String item){
		TablaTotalesPorAlmacen sol = new TablaTotalesPorAlmacen(mundo.darGrandiososTotalesCasoRaroConDosFecha(fecha1,fecha2, prefix, checkBoxREP.isSelected()));
		sorter = new TableRowSorter<TablaTotalesPorAlmacen>(sol);
		table.setModel(sol);
		filterText.setVisible(true);
		//filterText.setCursor(new Cursor(Cursor.TEXT_CURSOR));filterText.requestFocus();

		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus, int row, int col) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

				String status = (String)table.getModel().getValueAt(row, 0);
				if ("TOTALES".equals(status) ) {
					setBackground(Color.BLACK);
					setForeground(Color.WHITE);
					setFont(new Font("default", Font.BOLD, 16));

				} else {
					setBackground(table.getBackground());
					setForeground(table.getForeground());
					setFont(new Font("default", Font.LAYOUT_LEFT_TO_RIGHT, 14));

				}       
				return this;
			}   
		});			button_3.requestFocus();
		table.setRowSorter(sorter);


		panel_1.setVisible(false);
		estoyEnTotales = true;
		rdbtnDama.setSelected(false);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Fecha")).setMinWidth(76);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Dama")).setMaxWidth(47);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Caballero")).setMaxWidth(55);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Infantil")).setMaxWidth(50);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("CTO UN")).setMaxWidth(55);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("CDN T")).setMaxWidth(55);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("VTA UN")).setMaxWidth(55);
		rdbtnCaballero.setSelected(false);	

		rdbtnGlobal.setSelected(false);
		rdbtnInfatil.setSelected(false);

		filterText.requestFocusInWindow();
		actualDate = "Pedidos:" +item;
		estoyAlmacenes = false;
		panel_fechas.setVisible(true);

	}




	public void refrescar()
	{
		actualizarTotales("Seleccione una referencia");


	}

	public List<Proveedor> getProveedores() {
		return mundo.darProveedores();
	}

	public List<Almacen> getAlmacenes() {
		return mundo.darAlmacenes();
	}

	public void eliminarReposicion(String referencia, String codigoProveedor, String codigoAlmacen, String color,String categoria,String numeracion,int precioVenta) {
		String res = mundo.eliminarReposicion(referencia, codigoProveedor,codigoAlmacen, color,categoria,numeracion,precioVenta);
		setModelToReposiciones();
		JOptionPane.showMessageDialog(this, res);
		mundo.guardar();
		agregarCombobox();		
	}
	private static class Title implements Printable {

		@Override
		public int print(Graphics graphics, PageFormat arg1, int arg2) throws PrinterException {
			graphics.drawString(actualDate, 100, 100);

			return PAGE_EXISTS;


		}

	}
}