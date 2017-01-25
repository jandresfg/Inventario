package FrontEnd;

import java.awt.Checkbox;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import BackEnd.Almacen;
import BackEnd.Proveedor;
import BackEnd.Zapato;
import java.awt.Window.Type;
import javax.swing.JCheckBox;
import javax.swing.border.BevelBorder;
import javax.swing.ScrollPaneConstants;

public class DialogoAgregarReposicion extends JDialog implements ActionListener {

    /**
     * Comando para el bot贸n Agregar
     */
    private static final String AGREGAR = "Agregar";

    /**
     * Comando para el bot贸n Cancelar
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
     * Campo de texto donde se ingresa la identificaci贸n de la caja
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
     * Bot贸n usado para agregar la caja
     */
    private JButton botonAgregar;

    /**
     * Bot贸n para cancelar
     */
    private JButton botonCancelar;

    /**
     * Ventana principal de la aplicaci贸n
     */
    private Principal principal;

    /**
     * Grupo para colocar todos los radio buttons donde solo se puede escoger
     * uno
     */
    private ButtonGroup grupo;

    /**
     * Radio Button para indicar una transacci贸n de retiro
     */
    private JRadioButton radioRetiro;

    /**
     * Radio Button para indicar una transacci贸n de consignaci贸n
     */
    private JRadioButton radioConsignacion;

    /**
     * Radio Button para indicar una transacci贸n de pago de servicio
     */
    private JRadioButton radioServicio;
    private JTextField textReft;
    private JTextField textPlanta;
    private JLabel lblAltura;
    private JTextField textAltura;
    private JLabel lblColor;
    private JTextField textFColor;
    private JLabel lblMaterial;
    private JTextField textMaterial;
    private JLabel lblPrecioDeCompra;
    private JTextField textPC;
    private JLabel lblCantidad;
    private JTextField textCantidad;
    private JButton btnNewButton;
    private JButton btnNewButton_1;
    private JLabel lblSexo;
    private JRadioButton rdbtnHombre;
    private JRadioButton rdbtnMujer;
    private JRadioButton rdbtnInfantil;
    private JLabel lblDatosGenerales;
    private JLabel lblFecha;
    private JDatePickerImpl datePicker;
    private JLabel lblProveedores;
    private JLabel lblAlmacenes;
    private List<JCheckBox> checkboxesProveedores;
    private List<JCheckBox> checkboxesAlmacenes;
    private JTextField textPV;
    private JLabel lblPrecioDeVenta;

    // -----------------------------------------------
    // M茅todos
    // -----------------------------------------------
    /**
     * Crea el di谩logo para agregar una caja
     *
     * @param principal Ventana principal de la aplicaci贸n
     */
    public DialogoAgregarReposicion(Principal pprincipal) {
        super(pprincipal, true);
        setResizable(false);

        this.principal = pprincipal;
        setTitle("Agregar Reposici\u00F3n");
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{130, 146, 260, 260, 0};
        gridBagLayout.rowHeights = new int[]{30, 26, 26, 26, 26, 26, 25, 26, 0, 26, 26, 0, 27, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        grupo = new ButtonGroup();

        lblDatosGenerales = new JLabel("Datos Generales");
        lblDatosGenerales.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblDatosGenerales = new GridBagConstraints();
        gbc_lblDatosGenerales.insets = new Insets(0, 0, 5, 5);
        gbc_lblDatosGenerales.gridx = 0;
        gbc_lblDatosGenerales.gridy = 0;
        gbc_lblDatosGenerales.gridwidth = 2;
        getContentPane().add(lblDatosGenerales, gbc_lblDatosGenerales);

        //PROVEEDORES///////////////////////////////////////////////////////////////////
        lblProveedores = new JLabel("Proveedores");
        lblProveedores.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblProveedores = new GridBagConstraints();
        gbc_lblProveedores.insets = new Insets(0, 0, 5, 5);
        gbc_lblProveedores.gridx = 2;
        gbc_lblProveedores.gridy = 0;
        getContentPane().add(lblProveedores, gbc_lblProveedores);

        JPanel panelProveedores = new JPanel(new GridBagLayout());
        List<Proveedor> proveedores = principal.getProveedores();
        checkboxesProveedores = new ArrayList<JCheckBox>();
        for (int i = 0; i < proveedores.size(); i++) {
            Proveedor x = proveedores.get(i);

            JCheckBox chckbxProveedor = new JCheckBox(x.toString());
            GridBagConstraints gbc_chckbxProveedor = new GridBagConstraints();
            gbc_chckbxProveedor.anchor = GridBagConstraints.WEST;
            gbc_chckbxProveedor.insets = new Insets(0, 0, 5, 0);
            gbc_chckbxProveedor.gridx = 0;
            gbc_chckbxProveedor.gridy = i;
            checkboxesProveedores.add(chckbxProveedor);
            panelProveedores.add(chckbxProveedor, gbc_chckbxProveedor);

        }

        JScrollPane scrollPanel = new JScrollPane(panelProveedores);
        scrollPanel.setPreferredSize(scrollPanel.getMinimumSize());
        GridBagConstraints gbc_panelProveedores = new GridBagConstraints();
        gbc_panelProveedores.fill = GridBagConstraints.BOTH;
        gbc_panelProveedores.insets = new Insets(0, 0, 5, 5);
        gbc_panelProveedores.gridx = 2;
        gbc_panelProveedores.gridy = 1;
        gbc_panelProveedores.gridheight = 12;
        getContentPane().add(scrollPanel, gbc_panelProveedores);

        //ALMACENES///////////////////////////////////////////////////////////////////
        lblAlmacenes = new JLabel("Almacenes");
        lblAlmacenes.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblAlmacenes = new GridBagConstraints();
        gbc_lblAlmacenes.insets = new Insets(0, 0, 5, 0);
        gbc_lblAlmacenes.gridx = 3;
        gbc_lblAlmacenes.gridy = 0;
        getContentPane().add(lblAlmacenes, gbc_lblAlmacenes);

        JPanel panelAlamcenes = new JPanel(new GridBagLayout());
        List<Almacen> almacenes = principal.getAlmacenes();
        checkboxesAlmacenes = new ArrayList<JCheckBox>();
        for (int i = 0; i < almacenes.size(); i++) {
            Almacen x = almacenes.get(i);

            JCheckBox chckbxAlmacen = new JCheckBox(x.toString());
            GridBagConstraints gbc_chckbxAlmacen = new GridBagConstraints();
            gbc_chckbxAlmacen.anchor = GridBagConstraints.WEST;
            gbc_chckbxAlmacen.insets = new Insets(0, 0, 5, 0);
            gbc_chckbxAlmacen.gridx = 0;
            gbc_chckbxAlmacen.gridy = i;
            checkboxesAlmacenes.add(chckbxAlmacen);
            panelAlamcenes.add(chckbxAlmacen, gbc_chckbxAlmacen);

        }

        JScrollPane scrollPanelAlmacenes = new JScrollPane(panelAlamcenes);
        scrollPanelAlmacenes.setPreferredSize(scrollPanelAlmacenes.getMinimumSize());
        GridBagConstraints gbc_panelAlmacenes = new GridBagConstraints();
        gbc_panelAlmacenes.anchor = GridBagConstraints.NORTH;
        gbc_panelAlmacenes.fill = GridBagConstraints.BOTH;
        gbc_panelAlmacenes.insets = new Insets(0, 0, 5, 0);
        gbc_panelAlmacenes.gridx = 3;
        gbc_panelAlmacenes.gridy = 1;
        gbc_panelAlmacenes.gridheight = 12;
        getContentPane().add(scrollPanelAlmacenes, gbc_panelAlmacenes);

        //DATOS GENERALES///////////////////////////////////////////////////////////////////
        JLabel lblReferencia = new JLabel("Referencia");
        lblReferencia.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(0, 0, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        getContentPane().add(lblReferencia, gbc);

        textReft = new JTextField();
        GridBagConstraints gbc_1 = new GridBagConstraints();
        gbc_1.fill = GridBagConstraints.BOTH;
        gbc_1.insets = new Insets(0, 0, 5, 5);
        gbc_1.gridx = 1;
        gbc_1.gridy = 1;
        getContentPane().add(textReft, gbc_1);
        textReft.setColumns(10);

        JLabel lblPlanta = new JLabel("Planta");
        lblPlanta.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_2 = new GridBagConstraints();
        gbc_2.fill = GridBagConstraints.VERTICAL;
        gbc_2.insets = new Insets(0, 0, 5, 5);
        gbc_2.gridx = 0;
        gbc_2.gridy = 2;
        getContentPane().add(lblPlanta, gbc_2);

        textPlanta = new JTextField();
        GridBagConstraints gbc_3 = new GridBagConstraints();
        gbc_3.fill = GridBagConstraints.BOTH;
        gbc_3.insets = new Insets(0, 0, 5, 5);
        gbc_3.gridx = 1;
        gbc_3.gridy = 2;
        getContentPane().add(textPlanta, gbc_3);
        textPlanta.setColumns(10);

        lblAltura = new JLabel("Altura");
        GridBagConstraints gbc_4 = new GridBagConstraints();
        gbc_4.fill = GridBagConstraints.VERTICAL;
        gbc_4.insets = new Insets(0, 0, 5, 5);
        gbc_4.gridx = 0;
        gbc_4.gridy = 3;
        getContentPane().add(lblAltura, gbc_4);

        textAltura = new JTextField();
        GridBagConstraints gbc_textAltura = new GridBagConstraints();
        gbc_textAltura.fill = GridBagConstraints.BOTH;
        gbc_textAltura.insets = new Insets(0, 0, 5, 5);
        gbc_textAltura.gridx = 1;
        gbc_textAltura.gridy = 3;
        getContentPane().add(textAltura, gbc_textAltura);
        textAltura.setColumns(10);

        lblColor = new JLabel("Color");
        GridBagConstraints gbc_6 = new GridBagConstraints();
        gbc_6.fill = GridBagConstraints.VERTICAL;
        gbc_6.insets = new Insets(0, 0, 5, 5);
        gbc_6.gridx = 0;
        gbc_6.gridy = 4;
        getContentPane().add(lblColor, gbc_6);

        textFColor = new JTextField();
        GridBagConstraints gbc_7 = new GridBagConstraints();
        gbc_7.fill = GridBagConstraints.BOTH;
        gbc_7.insets = new Insets(0, 0, 5, 5);
        gbc_7.gridx = 1;
        gbc_7.gridy = 4;
        getContentPane().add(textFColor, gbc_7);
        textFColor.setColumns(10);

        lblMaterial = new JLabel("Material");
        GridBagConstraints gbc_8 = new GridBagConstraints();
        gbc_8.fill = GridBagConstraints.VERTICAL;
        gbc_8.insets = new Insets(0, 0, 5, 5);
        gbc_8.gridx = 0;
        gbc_8.gridy = 5;
        getContentPane().add(lblMaterial, gbc_8);

        textMaterial = new JTextField();
        GridBagConstraints gbc_9 = new GridBagConstraints();
        gbc_9.fill = GridBagConstraints.BOTH;
        gbc_9.insets = new Insets(0, 0, 5, 5);
        gbc_9.gridx = 1;
        gbc_9.gridy = 5;
        getContentPane().add(textMaterial, gbc_9);
        textMaterial.setColumns(10);

        lblFecha = new JLabel("Fecha");
        GridBagConstraints gbc_lblFecha = new GridBagConstraints();
        gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
        gbc_lblFecha.gridx = 0;
        gbc_lblFecha.gridy = 6;
        getContentPane().add(lblFecha, gbc_lblFecha);

        UtilDateModel model = new UtilDateModel();
        //model.setDate(20,04,2014);
        // Need this...
        Properties p = new Properties();
        p.put("text.today", "Hoy");
        p.put("text.month", "Mes");
        p.put("text.year", "A帽o");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        // Don't know about the formatter, but there it is...
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setTextEditable(false);

        GridBagConstraints gbc_datePicker = new GridBagConstraints();
        gbc_datePicker.insets = new Insets(0, 0, 5, 5);
        gbc_datePicker.gridx = 1;
        gbc_datePicker.gridy = 6;
        getContentPane().add(datePicker, gbc_datePicker);

        lblPrecioDeCompra = new JLabel("Precio de Costo");
        GridBagConstraints gbc_10 = new GridBagConstraints();
        gbc_10.fill = GridBagConstraints.VERTICAL;
        gbc_10.insets = new Insets(0, 0, 5, 5);
        gbc_10.gridx = 0;
        gbc_10.gridy = 7;
        getContentPane().add(lblPrecioDeCompra, gbc_10);

        textPC = new JTextField();
        textPC.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try {
					JTextField textField = (JTextField) e.getSource();
					String input = textField.getText();
					int inputInt = Integer.parseInt(input.replaceAll("\\.", ""));
					NumberFormat nf_ge = NumberFormat.getInstance(Locale.GERMAN);
					String number_ge = nf_ge.format(inputInt);
					textField.setText(number_ge);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Precio Costo inv醠ido: por favor use s髄o n鷐eros.\nSi lo desea puede usar punto como separador de miles.", "ERROR", JOptionPane.ERROR_MESSAGE);
					JTextField textField = (JTextField) e.getSource();
					if(!textField.getText().isEmpty())textField.setText(textField.getText().substring(0, textField.getText().length()-1));
					
				}
            }

            public void keyTyped(KeyEvent e) {
                // TODO: Do something for the keyTyped event
            }

            public void keyPressed(KeyEvent e) {
                // TODO: Do something for the keyPressed event
            }
        });
        GridBagConstraints gbc_11 = new GridBagConstraints();
        gbc_11.fill = GridBagConstraints.BOTH;
        gbc_11.insets = new Insets(0, 0, 5, 5);
        gbc_11.gridx = 1;
        gbc_11.gridy = 7;
        getContentPane().add(textPC, gbc_11);
        textPC.setColumns(10);

        lblPrecioDeVenta = new JLabel("Precio de Venta");
        GridBagConstraints gbc_lblPrecioDeVenta = new GridBagConstraints();
        gbc_lblPrecioDeVenta.insets = new Insets(0, 0, 5, 5);
        gbc_lblPrecioDeVenta.gridx = 0;
        gbc_lblPrecioDeVenta.gridy = 8;
        getContentPane().add(lblPrecioDeVenta, gbc_lblPrecioDeVenta);

        textPV = new JTextField();
        textPV.setColumns(10);
        textPV.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try {
					JTextField textField = (JTextField) e.getSource();
					String input = textField.getText();
					int inputInt = Integer.parseInt(input.replaceAll("\\.", ""));
					NumberFormat nf_ge = NumberFormat.getInstance(Locale.GERMAN);
					String number_ge = nf_ge.format(inputInt);
					textField.setText(number_ge);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Precio Venta inv醠ido: por favor use s髄o n鷐eros.\nSi lo desea puede usar punto como separador de miles.", "ERROR", JOptionPane.ERROR_MESSAGE);
					JTextField textField = (JTextField) e.getSource();
					if(!textField.getText().isEmpty())textField.setText(textField.getText().substring(0, textField.getText().length()-1));
				}
            }

            public void keyTyped(KeyEvent e) {
                // TODO: Do something for the keyTyped event
            }

            public void keyPressed(KeyEvent e) {
                // TODO: Do something for the keyPressed event
            }
        });
        GridBagConstraints gbc_textPV = new GridBagConstraints();
        gbc_textPV.fill = GridBagConstraints.HORIZONTAL;
        gbc_textPV.insets = new Insets(0, 0, 5, 5);
        gbc_textPV.gridx = 1;
        gbc_textPV.gridy = 8;
        getContentPane().add(textPV, gbc_textPV);

        lblCantidad = new JLabel("Cantidad");
        GridBagConstraints gbc_14 = new GridBagConstraints();
        gbc_14.fill = GridBagConstraints.VERTICAL;
        gbc_14.insets = new Insets(0, 0, 5, 5);
        gbc_14.gridx = 0;
        gbc_14.gridy = 9;
        getContentPane().add(lblCantidad, gbc_14);

        textCantidad = new JTextField();
        GridBagConstraints gbc_textCantidad = new GridBagConstraints();
        gbc_textCantidad.fill = GridBagConstraints.BOTH;
        gbc_textCantidad.insets = new Insets(0, 0, 5, 5);
        gbc_textCantidad.gridx = 1;
        gbc_textCantidad.gridy = 9;
        getContentPane().add(textCantidad, gbc_textCantidad);
        textCantidad.setColumns(10);

        rdbtnMujer = new JRadioButton("Dama");
        GridBagConstraints gbc_rdbtnMujer = new GridBagConstraints();
        gbc_rdbtnMujer.anchor = GridBagConstraints.WEST;
        gbc_rdbtnMujer.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtnMujer.gridx = 1;
        gbc_rdbtnMujer.gridy = 10;
        grupo.add(rdbtnMujer);
        getContentPane().add(rdbtnMujer, gbc_rdbtnMujer);

        lblSexo = new JLabel("Categor\u00EDa");
        lblSexo.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblSexo = new GridBagConstraints();
        gbc_lblSexo.insets = new Insets(0, 0, 5, 5);
        gbc_lblSexo.gridx = 0;
        gbc_lblSexo.gridy = 11;
        getContentPane().add(lblSexo, gbc_lblSexo);

        btnNewButton_1 = new JButton("Cancelar");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DialogoAgregarReposicion.this.setVisible(false);
                DialogoAgregarReposicion.this.dispose();
            }
        });

        btnNewButton = new JButton("Agregar");
        btnNewButton.setActionCommand(AGREGAR);
        btnNewButton.addActionListener(this);

        rdbtnHombre = new JRadioButton("Caballero");
        GridBagConstraints gbc_rdbtnHombre = new GridBagConstraints();
        gbc_rdbtnHombre.anchor = GridBagConstraints.WEST;
        gbc_rdbtnHombre.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtnHombre.gridx = 1;
        gbc_rdbtnHombre.gridy = 11;
        grupo.add(rdbtnHombre);

        getContentPane().add(rdbtnHombre, gbc_rdbtnHombre);

        rdbtnInfantil = new JRadioButton("Infantil");
        GridBagConstraints gbc_rdbtnInfantil = new GridBagConstraints();
        gbc_rdbtnInfantil.anchor = GridBagConstraints.WEST;
        gbc_rdbtnInfantil.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtnInfantil.gridx = 1;
        gbc_rdbtnInfantil.gridy = 12;
        grupo.add(rdbtnInfantil);
        getContentPane().add(rdbtnInfantil, gbc_rdbtnInfantil);
        GridBagConstraints gbc_18 = new GridBagConstraints();
        gbc_18.insets = new Insets(0, 0, 0, 5);
        gbc_18.fill = GridBagConstraints.BOTH;
        gbc_18.gridx = 0;
        gbc_18.gridy = 13;
        gbc_18.gridwidth = 3;
        getContentPane().add(btnNewButton, gbc_18);
        GridBagConstraints gbc_19 = new GridBagConstraints();
        gbc_19.fill = GridBagConstraints.BOTH;
        gbc_19.gridx = 3;
        gbc_19.gridy = 13;
        getContentPane().add(btnNewButton_1, gbc_19);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    /**
     * M茅todo que recoge las acciones sobre los objetos que est谩 escuchando.
     *
     * @param e Evento que se realiz贸.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(AGREGAR)) {
            try {
                String categoria;

                if (rdbtnHombre.isSelected()) {
                    categoria = "CABALLERO";
                } else if (rdbtnMujer.isSelected()) {
                    categoria = "DAMA";

                } else if (rdbtnInfantil.isSelected()) {
                    categoria = "INFANTIL";

                } else {
                    categoria = "NO ESPECIFICADO";

                }
                String altura = textAltura.getText();
                int precioCosto = -1, precioVenta = -1, cantidad = -1;
                try {
                    precioCosto = Integer.valueOf(textPC.getText().replaceAll("\\.", "")).intValue();
                    if (precioCosto == -1) {
                        JOptionPane.showMessageDialog(this, "Precio de Costo ingresado inv醠ido", "Agregar Reposici髇", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(this, "Precio de Costo ingresado inv醠ido", "Agregar Reposici髇", JOptionPane.ERROR_MESSAGE);
                }

                try {
                    precioVenta = Integer.valueOf(textPV.getText().replaceAll("\\.", "")).intValue();
                    if (precioVenta == -1) {
                        JOptionPane.showMessageDialog(this, "Precio de Venta ingresado inv醠ido", "Agregar Reposici髇", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(this, "Precio de Venta ingresado inv醠ido", "Agregar Reposici髇", JOptionPane.ERROR_MESSAGE);
                }

                try {
                    cantidad = Integer.valueOf(textCantidad.getText()).intValue();
                    if (cantidad == -1) {
                        JOptionPane.showMessageDialog(this, "Cantidad ingresada inv醠ida", "Agregar Reposici髇", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(this, "Cantidad ingresada inv醠ida", "Agregar Reposici髇", JOptionPane.ERROR_MESSAGE);
                }

                if (precioCosto != -1 && precioVenta != -1 && cantidad != -1) {
                    String color = textFColor.getText();
                    String material = textMaterial.getText();
                    String referencia = textReft.getText();
                    String planta = textPlanta.getText();
                    String fecha = datePicker.getJFormattedTextField().getText();

                    Zapato zap = new Zapato(referencia, planta, altura, color, material, precioCosto, precioVenta, cantidad, categoria, 0, Zapato.getFechaFromString(fecha));

                    ArrayList<String> provs = new ArrayList<String>();
                    for (int i = 0; i < checkboxesProveedores.size(); i++) {
                        JCheckBox x = checkboxesProveedores.get(i);
                        if (x.isSelected()) {
                            provs.add(x.getText());
                        }
                    }
                    if (provs.size() > 1) {
                        throw new Exception("S髄o puede seleccionar un Proveedor a la vez. Hay " + provs.size() + " seleccionados.");
                    }
                    if (provs.size() == 0) {
                        throw new Exception("No se ha seleecionado ningun Proveedor");
                    }

                    ArrayList<String> alms = new ArrayList<String>();
                    int cantidadAlmacenes = 0;
                    for (int i = 0; i < checkboxesAlmacenes.size(); i++) {
                        JCheckBox x = checkboxesAlmacenes.get(i);

                        if (x.isSelected()) {
                            cantidadAlmacenes++;
                        }
                    }

                    if (cantidadAlmacenes == 0) {
                        throw new Exception("No se ha seleecionado ningun Almacen");
                    } else {
                        String mensaje = "Se ha agregado exitosamente el zapato de Referencia: \" " + zap.getReferencia() + "\" \n A los Almacenes:\n";
                        for (int i = 0; i < checkboxesAlmacenes.size(); i++) {
                            JCheckBox x = checkboxesAlmacenes.get(i);
                            if (x.isSelected()) {

                                Zapato z = new Zapato(zap.getReferencia(), zap.getPlanta(), zap.getAltura(), zap.getColor(), zap.getMaterial(), zap.getPrecioCosto(), zap.getPrecioVenta(), zap.getCantidad(), zap.getCategoria(), 0, Zapato.getFechaFromString(fecha), true);
                                ArrayList<String> temp = new ArrayList<String>();
                                temp.add(x.getText());
                                cantidadAlmacenes--;
                                mensaje += " \"" + x.getText() + "\"  \n";
                                if (cantidadAlmacenes == 0) {

                                    principal.agregarReposicion(z, provs, temp, true, mensaje);
                                } else {
                                    principal.agregarReposicion(z, provs, temp, false, " ");

                                }
                            }
                        }
                    }

                    setVisible(false);
                    dispose();
                }

            } catch (ParseException e1) {
                JOptionPane.showMessageDialog(this, "Error en el campo Fecha", "Agregar Reposici髇", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(this, e1.getMessage(), "Agregar Reposici髇", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        }

    }

}
