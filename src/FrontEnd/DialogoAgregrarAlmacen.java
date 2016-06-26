package FrontEnd;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import BackEnd.Almacen;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class DialogoAgregrarAlmacen  extends JDialog implements ActionListener
{

/**
 * Comando para el botón Agregar
 */
private static final String AGREGAR = "Agregar";

/**
 * Comando para el botón Cancelar
 */
private static final String CANCELAR = "Cancelar";

// -----------------------------------------------------------------
// Atributos de la Interfaz
// -----------------------------------------------------------------

/**
 * Etiqueta Nombre
 */
private JLabel lblId;

/**
 * Campo de texto donde se ingresa la identificación de la caja
 */
private JTextField txtId;

/**
 * Etiqueta Dinero inicial
 */
private JLabel lblDineroInicial;

/**
 * Campo de texto donde se ingresa la cantidad de dinero inicial de la caja
 */
private JTextField txtDineroInicial;

/**
 * Botón usado para agregar la caja
 */
private JButton botonAgregar;

/**
 * Botón para cancelar
 */
private JButton botonCancelar;

/**
 * Ventana principal de la aplicación
 */
private Principal principal;

/**
 * Grupo para colocar todos los radio buttons donde solo se puede escoger uno
 */
private ButtonGroup grupo;

/**
 * Radio Button para indicar una transacción de retiro
 */
private JRadioButton radioRetiro;

/**
 * Radio Button para indicar una transacción de consignación
 */
private JRadioButton radioConsignacion;

/**
 * Radio Button para indicar una transacción de pago de servicio
 */
private JRadioButton radioServicio;
private JTextField textCiudad;
private JTextField textAlmacen;
private JLabel lblAltura;
private JLabel lblColor;
private JTextField textTelefono;
private JLabel lblMaterial;
private JLabel lblPrecioDeCompra;
private JTextField textNIT;
private JButton btnNewButton;
private JButton btnNewButton_1;
private JScrollPane scrollPane;
private JTextArea textRazonSocial;
private JScrollPane scrollPane_1;
private JTextArea textDireccion;
// -----------------------------------------------
// Métodos
// -----------------------------------------------

/**
 * Crea el diálogo para agregar una caja
 * @param principal Ventana principal de la aplicación
 */
public DialogoAgregrarAlmacen( Principal pprincipal )
{
    super( pprincipal, true );

    this.principal = pprincipal;
    setTitle( "Agregar Almacen" );
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[]{181, 233, 0};
    gridBagLayout.rowHeights = new int[]{26, 26, 34, 26, 69, 26, 26, 0};
    gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
    gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
    getContentPane().setLayout(gridBagLayout);
    
    JLabel lblReferencia = new JLabel("Ciudad");
    lblReferencia.setHorizontalAlignment(SwingConstants.LEFT);
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(0, 0, 5, 5);
    gbc.gridx = 0;
    gbc.gridy = 0;
    getContentPane().add(lblReferencia, gbc);
    
    textCiudad = new JTextField();
    GridBagConstraints gbc_textCiudad = new GridBagConstraints();
    gbc_textCiudad.fill = GridBagConstraints.BOTH;
    gbc_textCiudad.insets = new Insets(0, 0, 5, 0);
    gbc_textCiudad.gridx = 1;
    gbc_textCiudad.gridy = 0;
    getContentPane().add(textCiudad, gbc_textCiudad);
    textCiudad.setColumns(10);
    
    JLabel lblPlanta = new JLabel("Almacen");
    lblPlanta.setHorizontalAlignment(SwingConstants.LEFT);
    GridBagConstraints gbc_2 = new GridBagConstraints();
    gbc_2.fill = GridBagConstraints.BOTH;
    gbc_2.insets = new Insets(0, 0, 5, 5);
    gbc_2.gridx = 0;
    gbc_2.gridy = 1;
    getContentPane().add(lblPlanta, gbc_2);
    
    textAlmacen = new JTextField();
    GridBagConstraints gbc_textAlmacen = new GridBagConstraints();
    gbc_textAlmacen.fill = GridBagConstraints.BOTH;
    gbc_textAlmacen.insets = new Insets(0, 0, 5, 0);
    gbc_textAlmacen.gridx = 1;
    gbc_textAlmacen.gridy = 1;
    getContentPane().add(textAlmacen, gbc_textAlmacen);
    textAlmacen.setColumns(10);
    
    lblAltura = new JLabel("Direccion");
    GridBagConstraints gbc_4 = new GridBagConstraints();
    gbc_4.fill = GridBagConstraints.BOTH;
    gbc_4.insets = new Insets(0, 0, 5, 5);
    gbc_4.gridx = 0;
    gbc_4.gridy = 2;
    getContentPane().add(lblAltura, gbc_4);
    
    scrollPane_1 = new JScrollPane();
    GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
    gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
    gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
    gbc_scrollPane_1.gridx = 1;
    gbc_scrollPane_1.gridy = 2;
    getContentPane().add(scrollPane_1, gbc_scrollPane_1);
    
    textDireccion = new JTextArea();
    scrollPane_1.setViewportView(textDireccion);
    
    lblColor = new JLabel("Telefono");
    GridBagConstraints gbc_6 = new GridBagConstraints();
    gbc_6.fill = GridBagConstraints.BOTH;
    gbc_6.insets = new Insets(0, 0, 5, 5);
    gbc_6.gridx = 0;
    gbc_6.gridy = 3;
    getContentPane().add(lblColor, gbc_6);
    
    textTelefono = new JTextField();
    GridBagConstraints gbc_textTelefono = new GridBagConstraints();
    gbc_textTelefono.fill = GridBagConstraints.BOTH;
    gbc_textTelefono.insets = new Insets(0, 0, 5, 0);
    gbc_textTelefono.gridx = 1;
    gbc_textTelefono.gridy = 3;
    getContentPane().add(textTelefono, gbc_textTelefono);
    textTelefono.setColumns(10);
    
    lblMaterial = new JLabel("Razon social");
    GridBagConstraints gbc_8 = new GridBagConstraints();
    gbc_8.fill = GridBagConstraints.BOTH;
    gbc_8.insets = new Insets(0, 0, 5, 5);
    gbc_8.gridx = 0;
    gbc_8.gridy = 4;
    getContentPane().add(lblMaterial, gbc_8);
    
    scrollPane = new JScrollPane();
    GridBagConstraints gbc_scrollPane = new GridBagConstraints();
    gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
    gbc_scrollPane.fill = GridBagConstraints.BOTH;
    gbc_scrollPane.gridx = 1;
    gbc_scrollPane.gridy = 4;
    getContentPane().add(scrollPane, gbc_scrollPane);
    
    textRazonSocial = new JTextArea();
    scrollPane.setViewportView(textRazonSocial);
    
    lblPrecioDeCompra = new JLabel("NIT");
    GridBagConstraints gbc_10 = new GridBagConstraints();
    gbc_10.fill = GridBagConstraints.BOTH;
    gbc_10.insets = new Insets(0, 0, 5, 5);
    gbc_10.gridx = 0;
    gbc_10.gridy = 5;
    getContentPane().add(lblPrecioDeCompra, gbc_10);
    
    textNIT = new JTextField();
    GridBagConstraints gbc_textNIT = new GridBagConstraints();
    gbc_textNIT.fill = GridBagConstraints.BOTH;
    gbc_textNIT.insets = new Insets(0, 0, 5, 0);
    gbc_textNIT.gridx = 1;
    gbc_textNIT.gridy = 5;
    getContentPane().add(textNIT, gbc_textNIT);
    textNIT.setColumns(10);
    
    btnNewButton = new JButton("Agregar");
    btnNewButton.setActionCommand( AGREGAR );
    btnNewButton.addActionListener( this );
    GridBagConstraints gbc_18 = new GridBagConstraints();
    gbc_18.fill = GridBagConstraints.BOTH;
    gbc_18.insets = new Insets(0, 0, 0, 5);
    gbc_18.gridx = 0;
    gbc_18.gridy = 6;
    getContentPane().add(btnNewButton, gbc_18);
    
    btnNewButton_1 = new JButton("Cancelar");
    btnNewButton_1.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) 
    	{
    		DialogoAgregrarAlmacen.this.setVisible( false );
    		DialogoAgregrarAlmacen.this.dispose( );
    	}
    });
    GridBagConstraints gbc_19 = new GridBagConstraints();
    gbc_19.fill = GridBagConstraints.BOTH;
    gbc_19.gridx = 1;
    gbc_19.gridy = 6;
    getContentPane().add(btnNewButton_1, gbc_19);
    pack( );
    setLocationRelativeTo( null );
    setDefaultCloseOperation( DISPOSE_ON_CLOSE );

}

public void actionPerformed( ActionEvent e )
{
 if( e.getActionCommand( ).equals( AGREGAR ) )
    {
        try
        {
        	
        	String almacen =textAlmacen.getText();
        	String ciudad = textCiudad.getText();
        	String direccion =textDireccion.getText();
            String NIT = textNIT.getText( );

        	String razonSocial =textRazonSocial.getText();
            String telefono = textTelefono.getText( );

        	Almacen alma = new Almacen(ciudad, almacen, direccion, telefono, razonSocial, NIT);
        	
        	principal.agregarAlmacen(alma);
            
            setVisible( false );
            dispose( );
        }
        catch( NumberFormatException e1 )
        {
            JOptionPane.showMessageDialog( this, "Error en el formulario", "Agregar Almacen", JOptionPane.ERROR_MESSAGE );
        }
    }

}




}
