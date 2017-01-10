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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import BackEnd.Proveedor;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class DialogoAgregrarProveedor extends JDialog implements ActionListener {

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
     * Grupo para colocar todos los radio buttons donde solo se puede escoger
     * uno
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
    private JTextField textNombre;
    private JLabel lblAltura;
    private JLabel lblColor;
    private JTextField textFabrica;
    private JLabel lblMaterial;
    private JLabel lblPrecioDeCompra;
    private JTextField textCiudad;
    private JButton btnNewButton;
    private JButton btnNewButton_1;
    private JTextField textTelefono;
    private JTextField textField_1;
    private JScrollPane scrollPane;
    private JTextArea textDireccion;
// -----------------------------------------------
// Métodos
// -----------------------------------------------

    /**
     * Crea el diálogo para agregar una caja
     *
     * @param principal Ventana principal de la aplicación
     */
    public DialogoAgregrarProveedor(Principal pprincipal) {
        super(pprincipal, true);

        this.principal = pprincipal;
        setTitle("Agregar Proveedor");
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{181, 233, 0};
        gridBagLayout.rowHeights = new int[]{26, 26, 26, 26, 26, 26, 26, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);

        JLabel lblReferencia = new JLabel("Codigo");

        lblReferencia.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(0, 0, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        getContentPane().add(lblReferencia, gbc);

        textField_1 = new JTextField();
        textField_1.setText(principal.darCodigo());
        textField_1.setEditable(true);
        GridBagConstraints gbc_textField_1 = new GridBagConstraints();
        gbc_textField_1.insets = new Insets(0, 0, 5, 0);
        gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_1.gridx = 1;
        gbc_textField_1.gridy = 0;
        getContentPane().add(textField_1, gbc_textField_1);
        textField_1.setColumns(10);

        JLabel lblPlanta = new JLabel("Nombre");
        lblPlanta.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_2 = new GridBagConstraints();
        gbc_2.fill = GridBagConstraints.BOTH;
        gbc_2.insets = new Insets(0, 0, 5, 5);
        gbc_2.gridx = 0;
        gbc_2.gridy = 1;
        getContentPane().add(lblPlanta, gbc_2);

        textNombre = new JTextField();
        GridBagConstraints gbc_textNombre = new GridBagConstraints();
        gbc_textNombre.fill = GridBagConstraints.BOTH;
        gbc_textNombre.insets = new Insets(0, 0, 5, 0);
        gbc_textNombre.gridx = 1;
        gbc_textNombre.gridy = 1;
        getContentPane().add(textNombre, gbc_textNombre);
        textNombre.setColumns(10);

        lblAltura = new JLabel("Direccion");
        GridBagConstraints gbc_4 = new GridBagConstraints();
        gbc_4.anchor = GridBagConstraints.WEST;
        gbc_4.fill = GridBagConstraints.VERTICAL;
        gbc_4.insets = new Insets(0, 0, 5, 5);
        gbc_4.gridx = 0;
        gbc_4.gridy = 2;
        getContentPane().add(lblAltura, gbc_4);

        scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 1;
        gbc_scrollPane.gridy = 2;
        getContentPane().add(scrollPane, gbc_scrollPane);

        textDireccion = new JTextArea();
        scrollPane.setViewportView(textDireccion);

        lblColor = new JLabel("Fabrica");
        GridBagConstraints gbc_6 = new GridBagConstraints();
        gbc_6.fill = GridBagConstraints.BOTH;
        gbc_6.insets = new Insets(0, 0, 5, 5);
        gbc_6.gridx = 0;
        gbc_6.gridy = 3;
        getContentPane().add(lblColor, gbc_6);

        textFabrica = new JTextField();
        GridBagConstraints gbc_textFabrica = new GridBagConstraints();
        gbc_textFabrica.fill = GridBagConstraints.BOTH;
        gbc_textFabrica.insets = new Insets(0, 0, 5, 0);
        gbc_textFabrica.gridx = 1;
        gbc_textFabrica.gridy = 3;
        getContentPane().add(textFabrica, gbc_textFabrica);
        textFabrica.setColumns(10);

        lblMaterial = new JLabel("Telefono");
        GridBagConstraints gbc_8 = new GridBagConstraints();
        gbc_8.anchor = GridBagConstraints.WEST;
        gbc_8.fill = GridBagConstraints.VERTICAL;
        gbc_8.insets = new Insets(0, 0, 5, 5);
        gbc_8.gridx = 0;
        gbc_8.gridy = 4;
        getContentPane().add(lblMaterial, gbc_8);

        textTelefono = new JTextField();
        GridBagConstraints gbc_textTelefono = new GridBagConstraints();
        gbc_textTelefono.insets = new Insets(0, 0, 5, 0);
        gbc_textTelefono.fill = GridBagConstraints.HORIZONTAL;
        gbc_textTelefono.gridx = 1;
        gbc_textTelefono.gridy = 4;
        getContentPane().add(textTelefono, gbc_textTelefono);
        textTelefono.setColumns(10);

        lblPrecioDeCompra = new JLabel("Ciudad");
        GridBagConstraints gbc_10 = new GridBagConstraints();
        gbc_10.fill = GridBagConstraints.BOTH;
        gbc_10.insets = new Insets(0, 0, 5, 5);
        gbc_10.gridx = 0;
        gbc_10.gridy = 5;
        getContentPane().add(lblPrecioDeCompra, gbc_10);

        textCiudad = new JTextField();
        GridBagConstraints gbc_textCiudad = new GridBagConstraints();
        gbc_textCiudad.fill = GridBagConstraints.BOTH;
        gbc_textCiudad.insets = new Insets(0, 0, 5, 0);
        gbc_textCiudad.gridx = 1;
        gbc_textCiudad.gridy = 5;
        getContentPane().add(textCiudad, gbc_textCiudad);
        textCiudad.setColumns(10);

        btnNewButton = new JButton("Agregar");
        btnNewButton.setActionCommand(AGREGAR);
        btnNewButton.addActionListener(this);
        GridBagConstraints gbc_18 = new GridBagConstraints();
        gbc_18.fill = GridBagConstraints.BOTH;
        gbc_18.insets = new Insets(0, 0, 0, 5);
        gbc_18.gridx = 0;
        gbc_18.gridy = 6;
        getContentPane().add(btnNewButton, gbc_18);

        btnNewButton_1 = new JButton("Cancelar");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DialogoAgregrarProveedor.this.setVisible(false);
                DialogoAgregrarProveedor.this.dispose();
            }
        });
        GridBagConstraints gbc_19 = new GridBagConstraints();
        gbc_19.fill = GridBagConstraints.BOTH;
        gbc_19.gridx = 1;
        gbc_19.gridy = 6;
        getContentPane().add(btnNewButton_1, gbc_19);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(AGREGAR)) {
            try {
                String ciudad = textCiudad.getText();
                String direccion = textDireccion.getText();
                String fabrica = textFabrica.getText();
                int codigo = Integer.parseInt(textField_1.getText());
                String nombre = textNombre.getText();
                String telefono = textTelefono.getText();
                Proveedor prov = new Proveedor(codigo, nombre, fabrica, direccion, telefono, ciudad);
                String flag = principal.verificarCodigoProveedor(prov);

                principal.agregarProveedor(prov, flag);

                setVisible(false);
                dispose();
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(this, "Error en el formulario", "Agregar Proveedor", JOptionPane.ERROR_MESSAGE);
            } catch (Exception asd) {
                JOptionPane.showMessageDialog(this, "El codigo " + textField_1.getText() + " ya se encuentra en uso", "Agregar Proveedor", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

}
