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




public class DialogoEliminarReposicion extends JDialog implements ActionListener 
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
private JTextField textCodigoProveedor;
private JTextField txtAlmacen;
private JLabel lblColor;
private JTextField textField;
// -----------------------------------------------
// Métodos
// -----------------------------------------------

/**
 * Crea el diálogo para agregar una caja
 * @param principal Ventana principal de la aplicación
 */
public DialogoEliminarReposicion( Principal pprincipal )
{
    super( pprincipal, true );

    this.principal = pprincipal;
    setTitle( "Eliminar Reposición" );
    grupo = new ButtonGroup( );
    
    btnNewButton = new JButton("Aceptar");
    btnNewButton.setActionCommand( AGREGAR );
    btnNewButton.addActionListener( this );
    JLabel lblReferencia = new JLabel("Referencia");
    lblReferencia.setHorizontalAlignment(SwingConstants.LEFT);
    
    textReft = new JTextField();
    textReft.setColumns(10);
    
    btnNewButton_1 = new JButton("Cancelar");
    btnNewButton_1.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) 
    	{
    		DialogoEliminarReposicion.this.setVisible( false );
            DialogoEliminarReposicion.this.dispose( );
    	}
    });
    
    JLabel lblciudadAlmacen = new JLabel("Cod. Proveedor");
    lblciudadAlmacen.setHorizontalAlignment(SwingConstants.LEFT);
    
    textCodigoProveedor = new JTextField();
    textCodigoProveedor.setColumns(10);
    
    JLabel lblAlmacen = new JLabel("Almacen");
    lblAlmacen.setHorizontalAlignment(SwingConstants.LEFT);
    
    txtAlmacen = new JTextField();
    txtAlmacen.setColumns(10);
    
    lblColor = new JLabel("Color");
    lblColor.setHorizontalAlignment(SwingConstants.LEFT);
    
    textField = new JTextField();
    textField.setColumns(10);
    GroupLayout groupLayout = new GroupLayout(getContentPane());
    groupLayout.setHorizontalGroup(
    	groupLayout.createParallelGroup(Alignment.LEADING)
    		.addGroup(groupLayout.createSequentialGroup()
    			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
    				.addGroup(groupLayout.createSequentialGroup()
    					.addContainerGap()
    					.addComponent(textReft, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
    				.addGroup(groupLayout.createSequentialGroup()
    					.addGap(33)
    					.addComponent(lblReferencia))
    				.addGroup(groupLayout.createSequentialGroup()
    					.addContainerGap()
    					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)))
    			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
    				.addGroup(groupLayout.createSequentialGroup()
    					.addGap(18)
    					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
    						.addComponent(textCodigoProveedor, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
    						.addComponent(lblciudadAlmacen, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
    					.addPreferredGap(ComponentPlacement.UNRELATED, 10, Short.MAX_VALUE)
    					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
    						.addComponent(txtAlmacen, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
    						.addGroup(groupLayout.createSequentialGroup()
    							.addComponent(lblAlmacen, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
    							.addGap(34)))
    					.addPreferredGap(ComponentPlacement.RELATED)
    					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
    						.addGroup(groupLayout.createSequentialGroup()
    							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
    							.addContainerGap())
    						.addGroup(groupLayout.createSequentialGroup()
    							.addComponent(lblColor, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
    							.addGap(26))))
    				.addGroup(groupLayout.createSequentialGroup()
    					.addPreferredGap(ComponentPlacement.RELATED)
    					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
    					.addContainerGap())))
    );
    groupLayout.setVerticalGroup(
    	groupLayout.createParallelGroup(Alignment.LEADING)
    		.addGroup(groupLayout.createSequentialGroup()
    			.addContainerGap()
    			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
    				.addGroup(groupLayout.createSequentialGroup()
    					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
    						.addComponent(lblReferencia)
    						.addComponent(lblciudadAlmacen)
    						.addComponent(lblAlmacen, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
    						.addComponent(lblColor))
    					.addGap(4)
    					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
    						.addComponent(textReft, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
    						.addComponent(textCodigoProveedor, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
    						.addComponent(txtAlmacen, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
    				.addGroup(groupLayout.createSequentialGroup()
    					.addGap(18)
    					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
    			.addPreferredGap(ComponentPlacement.RELATED)
    			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
    				.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
    				.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
    			.addContainerGap())
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
          String codigoProveedor = textCodigoProveedor.getText();
          String codigoAlmacen = txtAlmacen.getText();
          String color = textField.getText();

          
        principal.eliminarReposicion(referencia, codigoProveedor,codigoAlmacen,color);
           
            setVisible( false );
            dispose( );
        }
        catch( NumberFormatException e1 )
        {
            JOptionPane.showMessageDialog( this, "Error en el formulario", "Eliminar Reposición", JOptionPane.ERROR_MESSAGE );
        }
    }

}
}
