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

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JTable;

import BackEnd.Almacen;
import BackEnd.Mundo;
import BackEnd.Proveedor;
import BackEnd.Zapato;

import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
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
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import javax.xml.ws.handler.MessageContext;

import java.awt.Color;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;

public class Principal extends JFrame implements ActionListener {

	private JFrame frmInventario;
	private Mundo mundo;
	private JTable table;
	private Button button;
	private Button button_1;
	private Button button_2;
	private Button button_3;
	private Button button_4;

	private TableCellRenderer tcr;
	private JTextField filterText;
	private TableRowSorter sorter;
	private JPanel panel;
	private JScrollPane scrollPane;
	
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
		frmInventario = new JFrame();
		frmInventario.setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/resources/icon.jpg")));
		frmInventario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmInventario.setTitle("Inventario");
		frmInventario.getContentPane().setBackground(UIManager.getColor("MenuBar.background"));
		frmInventario.setBackground(Color.GRAY);
		frmInventario.setBounds(100, 100, 1162, 527);
		frmInventario.setResizable(false);
		Principal.this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JFrame.setDefaultLookAndFeelDecorated(true);
		frmInventario.getContentPane().setLayout(null);



		panel = new JPanel();
		panel.setBorder(UIManager.getBorder("List.focusCellHighlightBorder"));
		panel.setBounds(0, 32, 973, 468);
		frmInventario.getContentPane().add(panel);
		panel.setLayout(null);

		table = new JTable();
		tcr = table.getDefaultRenderer(Object.class);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 973, 468);
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
			}
		});

		button.setBounds(10, 0, 70, 22);

		button_1 = new Button("Almacenes");
		panel_2.add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				setModelToAlmacenes();
			}
		});
		button_1.setBounds(108, 0, 70, 22);

		button_2 = new Button("Proveedores");
		panel_2.add(button_2);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setModelToProveedores();
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
			}
		});
		
		
		
		
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setModelToTotalesPorAlmacen();
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
						
								JButton mntmGuardar = new JButton("");
								mntmGuardar.setIcon(new ImageIcon(Principal.class.getResource("/resources/save.png")));
								mntmGuardar.setToolTipText("Guardar");
								panel_5.add(mntmGuardar);
								mntmGuardar.setActionCommand( "Guardar" );
								mntmGuardar.addActionListener( this );
								
								JButton mntmExportar = new JButton("");
								mntmExportar.setIcon(new ImageIcon(Principal.class.getResource("/resources/excel.png")));
								mntmExportar.setToolTipText("Exportar a Excel");
								panel_5.add(mntmExportar);
								mntmExportar.setActionCommand( "EXPO" );
								
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
								mntmExportar.addActionListener( this );
		btnEliminarZapato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				DialogoEliminarZapto dez= new DialogoEliminarZapto(Principal.this);
				dez.setVisible(true);
			}
		});
		
		agregarCombobox();
				
				JPanel panel_filter = new JPanel();
				panel_filter.setLayout(null);
				panel_filter.setBounds(0, 468, 911, 32);
				frmInventario.getContentPane().add(panel_filter);
				
				JLabel lblTextoDeFiltro = new JLabel("Filtrar por Almac\u00E9n:");
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
				boolean success = table.print();
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

	public void eliminar(String referencia, String codigoProveedor, String codigoAlmacen)
	{
		String res = mundo.eliminarZapato(referencia, codigoProveedor,codigoAlmacen);
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
		JOptionPane.showMessageDialog(this, "Se ha eliminado exitosamente el almacen de Nit: " + referencia);

	}
	
	public void setModelToZapatos(){
		TablaZapatos sol = new TablaZapatos(mundo.darZapatos());
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
		//table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Almacen")).setPreferredWidth(180);
		refrescar();
		button.requestFocus();
		panel.setSize(panel.getWidth(), 468);
		scrollPane.setSize(scrollPane.getWidth(), 468);
		agregarCombobox();
	}
	
	public void setModelToAlmacenes(){
		TablaAlmacen sol = new TablaAlmacen(mundo.darAlmacenes());
		sol.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				System.out.println("table changed");
				mundo.setAlmacenes(sol.getData());
				if(e.getColumn() == sol.findColumn("NIT")) mundo.actualizarNITsAlmacenes();
				if(e.getColumn() == sol.findColumn("Almacen")) mundo.actualizarAlmacenesAlmacenes();
				mundo.guardar();
				agregarCombobox();
			}
		});
		table.setModel(sol);
		table.setDefaultRenderer(Object.class, tcr);
		button_1.requestFocus();
		panel.setSize(panel.getWidth(), 468);
		scrollPane.setSize(scrollPane.getWidth(), 468);
	}
	
	public void setModelToProveedores(){
		TablaProveedor sol = new TablaProveedor(mundo.darProveedores());
		sol.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				System.out.println("table changed");
				mundo.setProveedores(sol.getData());
				if(e.getColumn() == sol.findColumn("Codigo")) mundo.actualizarCodigosProveedores();
				if(e.getColumn() == sol.findColumn("Nombre")) mundo.actualizarNombresProveedores();
				mundo.guardar();
				agregarCombobox();
			}
		});
		table.setModel(sol);
		
		table.setDefaultRenderer(Object.class, tcr);
		button_2.requestFocus();
		panel.setSize(panel.getWidth(), 468);
		scrollPane.setSize(scrollPane.getWidth(), 468);
	}
	
	public void setModelToTotalesPorAlmacen(){
		TablaTotalesPorAlmacen sol = new TablaTotalesPorAlmacen(mundo.darTotales());
		sorter = new TableRowSorter<TablaTotalesPorAlmacen>(sol);
//		La tabla de totales no es editable
//		sol.addTableModelListener(new TableModelListener() {
//
//			@Override
//			public void tableChanged(TableModelEvent e) {
//				System.out.println("table changed");
//				mundo.setAlmacenes(sol.getData());
//				if(e.getColumn() == sol.findColumn("NIT")) mundo.actualizarNITsAlmacenes();
//				if(e.getColumn() == sol.findColumn("Almacen")) mundo.actualizarAlmacenesAlmacenes();
//				mundo.guardar();
//			}
//			
//		});
		table.setModel(sol);
		filterText.setVisible(true);
		panel.setSize(panel.getWidth(), 435);
		scrollPane.setSize(scrollPane.getWidth(), 435);
		table.setDefaultRenderer(Object.class, tcr);
		button_3.requestFocus();
	}
	public void setModelToGrandesTotales(){
		TablaGrandesTotales sol = new TablaGrandesTotales(mundo.darGrandesTotales());
		sorter = new TableRowSorter<TablaGrandesTotales>(sol);

		table.setModel(sol);
		filterText.setVisible(true);
		panel.setSize(panel.getWidth(), 435);
		scrollPane.setSize(scrollPane.getWidth(), 435);
		table.setDefaultRenderer(Object.class, tcr);
		button_4.requestFocus();
	}
	
	private void newFilter(){
		RowFilter<TablaTotalesPorAlmacen, Object> rf = null;
	    //If current expression doesn't parse, don't update.
	    try {
	        rf = RowFilter.regexFilter(filterText.getText(), 0);
	    } catch (java.util.regex.PatternSyntaxException e) {
	        return;
	    }
	    sorter.setRowFilter(rf);

		table.setRowSorter(sorter);

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
}
