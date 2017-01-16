package FrontEnd;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import BackEnd.Almacen;
import BackEnd.Zapato;

public class TablaZapatos extends AbstractTableModel {

    List<Zapato> data = new ArrayList<Zapato>();

    //	String colNames[] = { "Referencia", "Planta", "Altura", "Color", "Material", "Proveedor", "Almacen", "Cantidad", "Precio Costo", "Precio Venta","Categor�a","Fecha" };
    // Class<?> colClasses[] = { String.class, String.class, String.class , String.class, String.class, Object.class, Object.class, Integer.class, Integer.class, Integer.class, String.class, String.class};
    Class<?> colClasses[] = {Object.class, Object.class, String.class, String.class, String.class, Object.class, Object.class, String.class, String.class, Integer.class, String.class, String.class};
    String colNames[] = {"Almacen", "Proveedor", "Fecha", "Referencia", "Categoría", "Planta", "Altura", "Color", "Material", "Cantidad", "Precio Costo", "Precio Venta"};

    public TablaZapatos(List<Zapato> arr) {
        data = arr;
    }

    public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        return colNames.length;
    }

    public List<Zapato> getData() {
        return data;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {


        if (columnIndex == 0) {
            return data.get(rowIndex).getAlmacenesString().replace('{', '\n');
        }
        if (columnIndex == 1) {
            return data.get(rowIndex).getProveedoresString().replace('{', '\n');
        }
        if (columnIndex == 2) {
            return data.get(rowIndex).getStringFecha();
        }
        if (columnIndex == 3) {
            return data.get(rowIndex).getReferencia();
        }
        if (columnIndex == 4) {
            return data.get(rowIndex).getCategoria();
        }

        if (columnIndex == 5) {
            return data.get(rowIndex).getPlanta();
        }
        if (columnIndex == 6) {
            return data.get(rowIndex).getAltura();
        }
        if (columnIndex == 7) {
            return data.get(rowIndex).getColor();
        }
        if (columnIndex == 8) {
            return data.get(rowIndex).getMaterial();
        }

        if (columnIndex == 9) {
            return data.get(rowIndex).getCantidad();
        }
        if (columnIndex == 10) {
        	NumberFormat nf_ge = NumberFormat.getInstance(Locale.GERMAN);
        	String number_ge = nf_ge.format(data.get(rowIndex).getPrecioCosto());
            return number_ge;
        }
        if (columnIndex == 11) {
        	NumberFormat nf_ge = NumberFormat.getInstance(Locale.GERMAN);
        	String number_ge = nf_ge.format(data.get(rowIndex).getPrecioVenta());
            return number_ge;
        }

        return null;
    }

    public String getColumnName(int columnIndex) {
        return colNames[columnIndex];
    }

    public Class<?> getColumnClass(int columnIndex) {
        return colClasses[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0 || columnIndex == 1) {
            return false;
        }
        return true;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        System.out.println("Zapatos setValue: " + aValue);
        /*
        if (columnIndex == 0) {

        }
        if (columnIndex == 1) {

        }
        */
        if (columnIndex == 2) {
            try {
                data.get(rowIndex).setFecha(Zapato.getFechaFromString((String) aValue));
            } catch (ParseException e) {
            	JOptionPane.showMessageDialog(null, "Fecha inválida. El formato de fecha apropiado es " + Zapato.FORMATO_FECHA + "\nEjemplo: " + Zapato.cadenafechaEjemplo(), "ERROR", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
        if (columnIndex == 3) {
            data.get(rowIndex).setReferencia((String) aValue);
        }
        if (columnIndex == 4) {
            data.get(rowIndex).setCategoria((String) aValue);
        }
        if (columnIndex == 5) {
            data.get(rowIndex).setPlanta((String) aValue);
        }
        if (columnIndex == 6) {
            data.get(rowIndex).setAltura((String) aValue);
        }
        if (columnIndex == 7) {
            data.get(rowIndex).setColor((String) aValue);
        }
        if (columnIndex == 8) {
            data.get(rowIndex).setMaterial((String) aValue);
        }
        if (columnIndex == 9) {
            data.get(rowIndex).setCantidad((int) aValue);
        }
        if (columnIndex == 10) {
        	try {
        		String input = ((String) aValue).replaceAll("\\.", "");
        		System.out.println("Precio Costo: "+input);
				int output = Integer.parseInt(input);
				data.get(rowIndex).setPrecioCosto(output);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Precio Costo '"+aValue+"' inválido: por favor use sólo números.\nSi lo desea puede usar punto como separador de miles.", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
            
        }
        if (columnIndex == 11) {
        	try {
        		String input = ((String) aValue).replaceAll("\\.", "");
        		System.out.println("Precio Venta: "+input);
				int output = Integer.parseInt(input);
				data.get(rowIndex).setPrecioVenta(output);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Precio Venta '"+aValue+"' inválido: por favor use sólo números.\nSi lo desea puede usar punto como separador de miles.", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
}
