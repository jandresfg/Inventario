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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;




public class DialogoEliminarProveedor extends JDialog implements ActionListener 
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
// -----------------------------------------------
// Métodos
// -----------------------------------------------

/**
 * Crea el diálogo para agregar una caja
 * @param principal Ventana principal de la aplicación
 */
public DialogoEliminarProveedor( Principal pprincipal )
{
    super( pprincipal, true );

    this.principal = pprincipal;
    setTitle( "Eliminar Proveedor" );
    grupo = new ButtonGroup( );
    
    btnNewButton = new JButton("Aceptar");
    btnNewButton.setActionCommand( AGREGAR );
    btnNewButton.addActionListener( this );
    JLabel lblReferencia = new JLabel("Nombre");
    lblReferencia.setHorizontalAlignment(SwingConstants.LEFT);
    
    textReft = new JTextField();
    textReft.setColumns(10);
    
    btnNewButton_1 = new JButton("Cancelar");
    btnNewButton_1.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) 
    	{
    		DialogoEliminarProveedor.this.setVisible( false );
            DialogoEliminarProveedor.this.dispose( );
    	}
    });
    GroupLayout groupLayout = new GroupLayout(getContentPane());
    groupLayout.setHorizontalGroup(
    	groupLayout.createParallelGroup(Alignment.LEADING)
    		.addGroup(groupLayout.createSequentialGroup()
    			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    			.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
    			.addPreferredGap(ComponentPlacement.UNRELATED)
    			.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
    			.addContainerGap())
    		.addGroup(groupLayout.createSequentialGroup()
    			.addContainerGap(67, Short.MAX_VALUE)
    			.addComponent(textReft, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
    			.addGap(58))
    		.addGroup(groupLayout.createSequentialGroup()
    			.addGap(105)
    			.addComponent(lblReferencia)
    			.addContainerGap(104, Short.MAX_VALUE))
    );
    groupLayout.setVerticalGroup(
    	groupLayout.createParallelGroup(Alignment.LEADING)
    		.addGroup(groupLayout.createSequentialGroup()
    			.addGap(9)
    			.addComponent(lblReferencia)
    			.addPreferredGap(ComponentPlacement.RELATED)
    			.addComponent(textReft, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
    			.addPreferredGap(ComponentPlacement.RELATED)
    			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
    				.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
    				.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
    );
    getContentPane().setLayout(groupLayout);
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
          
          
        principal.eliminarProveedor(referencia);
           
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
