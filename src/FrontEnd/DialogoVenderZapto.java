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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

import BackEnd.Zapato;




public class DialogoVenderZapto extends JDialog implements ActionListener 
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
private JTextField textReft;
private JButton btnNewButton;
private JButton btnNewButton_1;
private JLabel lblCantidad;
private JTextField textCantidad;
// -----------------------------------------------
// Métodos
// -----------------------------------------------

/**
 * Crea el diálogo para agregar una caja
 * @param principal Ventana principal de la aplicación
 */
public DialogoVenderZapto( Principal pprincipal )
{
    super( pprincipal, true );

    this.principal = pprincipal;
    setTitle( "Agregar Zapato" );
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[]{161, 178, 0};
    gridBagLayout.rowHeights = new int[]{26, 26, 26, 0};
    gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
    gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
    getContentPane().setLayout(gridBagLayout);
    grupo = new ButtonGroup( );
    JLabel lblReferencia = new JLabel("Referencia");
    lblReferencia.setHorizontalAlignment(SwingConstants.LEFT);
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(0, 0, 5, 5);
    gbc.gridx = 0;
    gbc.gridy = 0;
    getContentPane().add(lblReferencia, gbc);
    
    textReft = new JTextField();
    GridBagConstraints gbc_1 = new GridBagConstraints();
    gbc_1.fill = GridBagConstraints.BOTH;
    gbc_1.insets = new Insets(0, 0, 5, 0);
    gbc_1.gridx = 1;
    gbc_1.gridy = 0;
    getContentPane().add(textReft, gbc_1);
    textReft.setColumns(10);
    
    btnNewButton = new JButton("Vender");
    btnNewButton.setActionCommand( AGREGAR );
    btnNewButton.addActionListener( this );
    
    lblCantidad = new JLabel("Cantidad");
    lblCantidad.setHorizontalAlignment(SwingConstants.LEFT);
    GridBagConstraints gbc_lblCantidad = new GridBagConstraints();
    gbc_lblCantidad.anchor = GridBagConstraints.WEST;
    gbc_lblCantidad.insets = new Insets(0, 0, 5, 5);
    gbc_lblCantidad.gridx = 0;
    gbc_lblCantidad.gridy = 1;
    getContentPane().add(lblCantidad, gbc_lblCantidad);
    
    textCantidad = new JTextField();
    textCantidad.setColumns(10);
    GridBagConstraints gbc_textCantidad = new GridBagConstraints();
    gbc_textCantidad.insets = new Insets(0, 0, 5, 0);
    gbc_textCantidad.fill = GridBagConstraints.HORIZONTAL;
    gbc_textCantidad.gridx = 1;
    gbc_textCantidad.gridy = 1;
    getContentPane().add(textCantidad, gbc_textCantidad);
    GridBagConstraints gbc_18 = new GridBagConstraints();
    gbc_18.fill = GridBagConstraints.BOTH;
    gbc_18.insets = new Insets(0, 0, 0, 5);
    gbc_18.gridx = 0;
    gbc_18.gridy = 2;
    getContentPane().add(btnNewButton, gbc_18);
    
    btnNewButton_1 = new JButton("Cancelar");
    btnNewButton_1.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) 
    	{
    		DialogoVenderZapto.this.setVisible( false );
            DialogoVenderZapto.this.dispose( );
    	}
    });
    GridBagConstraints gbc_19 = new GridBagConstraints();
    gbc_19.fill = GridBagConstraints.BOTH;
    gbc_19.gridx = 1;
    gbc_19.gridy = 2;
    getContentPane().add(btnNewButton_1, gbc_19);
    pack( );
    setLocationRelativeTo( null );
    setDefaultCloseOperation( DISPOSE_ON_CLOSE );

}

/**
 * Método que recoge las acciones sobre los objetos que está escuchando.
 * @param e Evento que se realizó.
 */
public void actionPerformed( ActionEvent e )
{
  if( e.getActionCommand( ).equals( AGREGAR ) )
    {
    	
    	/**
    	 * try
            {

                String transaccionSeleccionada;
                if( radioRetiro.isSelected( ) )
                    transaccionSeleccionada = Banco.RETIRO;
                else if( radioConsignacion.isSelected( ) )
                    transaccionSeleccionada = Banco.CONSIGNACION;
                else
                    transaccionSeleccionada = Banco.PAGO_SERVICIO;

                int id = Integer.valueOf( txtId.getText( ) ).intValue( );
                int montoInicial = Integer.valueOf( txtDineroInicial.getText( ) ).intValue( );
                principal.agregarCaja( id, transaccionSeleccionada, montoInicial );
                setVisible( false );
                dispose( );
            }
            catch( NumberFormatException e1 )
            {
                JOptionPane.showMessageDialog( this, "Error en el formato de la identificación", "Agregar Caja", JOptionPane.ERROR_MESSAGE );
            }
    	 */
        try
        {
        	
          String referencia = textReft.getText();
          int cantidad = Integer.parseInt(textCantidad.getText());
          
          
        principal.vender(referencia, cantidad);
           
            setVisible( false );
            dispose( );
        }
        catch( NumberFormatException e1 )
        {
            JOptionPane.showMessageDialog( this, "Error en el formulario", "Agregar Zapato", JOptionPane.ERROR_MESSAGE );
        }
    }

}

}
