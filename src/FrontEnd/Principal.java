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

import BackEnd.Almacen;
import BackEnd.Mundo;
import BackEnd.Proveedor;
import BackEnd.Zapato;

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
	private 				JRadioButton rdbtnCaballero = new JRadioButton("Caballero");
	private JRadioButton rdbtnInfatil = new JRadioButton("Infatil");
	private boolean estoyEnTotales = false;
    private JPanel panel_filter;
    private JLabel lblTextoDeFiltro;
    private JPanel  panel_filterReposiciones;
    private JCheckBox checkBoxREP ;
    private JCheckBox   checkBoxPedidos;
	private JCheckBox checkBox_2Repo ;
	private JCheckBox checkBox_3Pedidos;
	private JComboBox<String> comboBox_1;
	private ItemListener itemListener;
	private static final String FORMATO_FECHA = "dd-MMM-yyyy";
	private boolean agregado;
	private static String actualDate = "";
	private boolean inicial ;
	private boolean maximo ;
    private JDatePickerImpl datePicker;
    private JDatePickerImpl datePicker2;

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
		frmInventario.setBounds(100, 100, 1162, 751);
		frmInventario.setResizable(false);
		Principal.this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JFrame.setDefaultLookAndFeelDecorated(true);
		frmInventario.getContentPane().setLayout(null);
		
	    UtilDateModel model = new UtilDateModel();
        //model.setDate(20,04,2014);
        // Need this...
        Properties p = new Properties();
        p.put("text.today", "Hoy");
        p.put("text.month", "Mes");
        p.put("text.year", "AÃ±o");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		 
		  panel_1 = new JPanel();
		  panel_1.setBounds(972, 335, 184, 359);
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
		  
		  JSeparator separator_1 = new JSeparator();
		  separator_1.setPreferredSize(new Dimension(0, 2));
		  separator_1.setBounds(0, 183, 184, 20);
		  panel_1.add(separator_1);
		  
		  datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		  datePicker.setSize(184, 23);
		  datePicker.setLocation(0, 209);
		  
		  datePicker.setTextEditable(false);
		  panel_1.add(datePicker);

		  datePicker2 = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		  datePicker2.setSize(184, 23);
		  datePicker2.setLocation(0, 258);
		  datePicker2.setTextEditable(false);
		  panel_1.add(datePicker2);

		  JButton btnFiltrarPorFecha = new JButton("Filtrar por Fecha");
		  btnFiltrarPorFecha.setBounds(24, 292, 150, 23);
		  panel_1.add(btnFiltrarPorFecha);
		  
		  JButton button_5 = new JButton("Todo");
		  button_5.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  	}
		  });
		  button_5.setBounds(24, 327, 150, 23);
		  panel_1.add(button_5);
		  
		  JLabel label = new JLabel("Fecha Inicial");
		  label.setHorizontalAlignment(SwingConstants.CENTER);
		  label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  label.setBounds(0, 189, 184, 14);
		  panel_1.add(label);
		  
		  JLabel label_1 = new JLabel("Fecha Final");
		  label_1.setHorizontalAlignment(SwingConstants.CENTER);
		  label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  label_1.setBounds(0, 239, 184, 14);
		  panel_1.add(label_1);

		  
		panel_1.setVisible(false);
		 

		  
		 
		 
		 
		 
		 panel_filterReposiciones = new JPanel();
		 panel_filterReposiciones.setBounds(972, 335, 184, 183);
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
		panel.setBounds(0, 32, 973, 656);
		frmInventario.getContentPane().add(panel);
		panel.setLayout(null);

		table = new JTable();
		tcr = table.getDefaultRenderer(Object.class);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 973, 656);
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
		panel_2.setBounds(0, 0, 973, 32);
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
				setModelToGrandesTotales();
				rdbtnGlobal.setSelected(true);
				panel_filterReposiciones.setVisible(false);
				checkBox_3Pedidos.setSelected(true);
                                checkBox_2Repo.setSelected(false);

			}
		});
	button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setModelToTotalesPorAlmacen();
                panel_filter.setVisible(true);
				panel_filterReposiciones.setVisible(true);

				

					checkBoxPedidos.setSelected(true);		
                                checkBoxREP.setSelected(false);

			}
		});

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(972, 0, 184, 342);
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
				panel_filter.setBounds(0, 690, 911, 32);
				frmInventario.getContentPane().add(panel_filter);
				
				lblTextoDeFiltro = new JLabel("Filtrar por Almac\u00E9n:");
				lblTextoDeFiltro.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblTextoDeFiltro.setBounds(10, 0, 150, 26);
				panel_filter.add(lblTextoDeFiltro);
				
				filterText = new JTextField();
				filterText.setColumns(10);
				filterText.setBounds(117, 4, 794, 22);
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

				comboBox_1 = new JComboBox();
				comboBox_1.setBounds(921, 693, 235, 22);
				frmInventario.getContentPane().add(comboBox_1);
				comboBox_1.setVisible(false);
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

			          comboBox_1.addItemListener(itemListener);

				
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
				 MessageFormat headerFormat = new MessageFormat("Pedidos "+ actualDate );
			     MessageFormat pageNumber = new MessageFormat("- {0} -");

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

	public void eliminar(String referencia, String codigoProveedor, String codigoAlmacen, String color)
	{
		String res = mundo.eliminarZapato(referencia, codigoProveedor,codigoAlmacen, color);
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
		JOptionPane.showMessageDialog(this, "Se ha eliminado exitosamente el almacÃ©n de ciudad: " + referencia);

	}
	
	public void setModelToZapatos(){
		TablaZapatos sol = new TablaZapatos(mundo.darZapatos(), mundo);
		sol.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				System.out.println("table changed");
				mundo.setZapatos(sol.getData());
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
		refrescar();
		button.requestFocus();
		panel.setSize(panel.getWidth(), 656);
		scrollPane.setSize(scrollPane.getWidth(), 656);
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
		comboBox_1.setVisible(false);


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
		refrescar();
		buttonReposiciones.requestFocus();
		panel.setSize(panel.getWidth(), 656);
		scrollPane.setSize(scrollPane.getWidth(), 656);
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
		comboBox_1.setVisible(false);

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
		
		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment(JLabel.LEFT);
		leftRenderer.setVerticalAlignment(JLabel.NORTH);
		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Telefono")).setCellRenderer(leftRenderer);
		
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
		comboBox_1.setVisible(false);

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
		comboBox_1.setVisible(false);

		
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
        		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Fecha")).setMinWidth(76);
        		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Dama")).setMaxWidth(47);
        		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Caballero")).setMaxWidth(55);
        		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Infantil")).setMaxWidth(50);

		rdbtnCaballero.setSelected(false);	
		
		rdbtnGlobal.setSelected(false);
		rdbtnInfatil.setSelected(false);


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
		
		rdbtnCaballero.setSelected(false);	
		
		rdbtnGlobal.setSelected(false);
		rdbtnInfatil.setSelected(false);


	}
	
	
	
	public void setModelToGrandesTotales(){
		
		
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
		});		button_4.requestFocus();
		panel_1.setVisible(true);
                table.setRowSorter(sorter);
        		estoyEnTotales = false;
                        panel_filter.setVisible(false);

		

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
                		comboBox_1.setVisible(false);


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
        comboBox_1.setVisible(false);

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
        comboBox_1.setVisible(false);

                        panel_filter.setVisible(false);

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
                        comboBox_1.setVisible(false);


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
    comboBox_1.removeAllItems();
	comboBox_1.setVisible(false);
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
		TablaTotalesPorAlmacen sol = new TablaTotalesPorAlmacen(mundo.darGrandiososTotalesCasoRaro(prefix, checkBoxREP.isSelected()));
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
		        } else {
		            setBackground(table.getBackground());
		            setForeground(table.getForeground());
		        }       
		        return this;
		    }   
		});			button_3.requestFocus();
        table.setRowSorter(sorter);

        comboBox_1.removeAllItems();
		comboBox_1.setVisible(true);
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
			comboBox_1.addItem(x);			
		}
		//System.out.println("SELECCIONADO: "+comboBox_1.getSelectedItem());
		
		comboBox_1.addItem("TODO");
		// SELECCIONO TODO

		if(inicial || maximo)
		{
			comboBox_1.setSelectedIndex(fechitas.size());

			System.out.println("SELECCIONADO: "+comboBox_1.getSelectedItem());
			
			System.out.println("COUNT: "+comboBox_1.getItemCount());
		maximo=false;
		}

		
		panel_1.setVisible(false);
                estoyEnTotales = true;
                rdbtnDama.setSelected(false);
        		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Fecha")).setMinWidth(76);
        		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Dama")).setMaxWidth(47);
        		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Caballero")).setMaxWidth(55);
        		table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Infantil")).setMaxWidth(50);
		rdbtnCaballero.setSelected(false);	
		
		rdbtnGlobal.setSelected(false);
		rdbtnInfatil.setSelected(false);

		filterText.requestFocusInWindow();

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
		        } else {
		            setBackground(table.getBackground());
		            setForeground(table.getForeground());
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
		rdbtnCaballero.setSelected(false);	
		
		rdbtnGlobal.setSelected(false);
		rdbtnInfatil.setSelected(false);

		filterText.requestFocusInWindow();

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

	public void eliminarReposicion(String referencia, String codigoProveedor, String codigoAlmacen, String color) {
		String res = mundo.eliminarReposicion(referencia, codigoProveedor,codigoAlmacen, color);
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