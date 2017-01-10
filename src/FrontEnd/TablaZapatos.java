package FrontEnd;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import BackEnd.Almacen;
import BackEnd.Zapato;

public class TablaZapatos extends AbstractTableModel {

    List<Zapato> data = new ArrayList<Zapato>();

    //	String colNames[] = { "Referencia", "Planta", "Altura", "Color", "Material", "Proveedor", "Almacen", "Cantidad", "Precio Costo", "Precio Venta","Categoría","Fecha" };
    // Class<?> colClasses[] = { String.class, String.class, String.class , String.class, String.class, Object.class, Object.class, Integer.class, Integer.class, Integer.class, String.class, String.class};
    Class<?> colClasses[] = {String.class, Object.class, Object.class, String.class, String.class, Object.class, Object.class, String.class, String.class, Integer.class, Integer.class, Integer.class};
    String colNames[] = {"Fecha", "Proveedor", "Almacen", "Referencia", "Categoría", "Planta", "Altura", "Color", "Material", "Cantidad", "Precio Costo", "Precio Venta"};

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

        String colNames[] = {"Fecha", "Proveedor", "Almacen", "Referencia", "Categoría", "Planta", "Altura", "Color", "Material", "Cantidad", "Precio Costo", "Precio Venta"};

        if (columnIndex == 0) {
            return data.get(rowIndex).getStringFecha();
        }
        if (columnIndex == 1) {
            return data.get(rowIndex).getProveedoresString().replace('{', '\n');
        }
        if (columnIndex == 2) {
            return data.get(rowIndex).getAlmacenesString().replace('{', '\n');
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
            return data.get(rowIndex).getPrecioCosto();
        }
        if (columnIndex == 11) {
            return data.get(rowIndex).getPrecioVenta();
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
        if (columnIndex == 5 || columnIndex == 6) {
            return false;
        }
        return true;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        System.out.println("Zapatos setValue: " + aValue);
        if (columnIndex == 0) {
//	        	boolean yaExiste = false;
//	    		for(int i = 0; i<data.size() && !yaExiste; i++){
//	    			if(i != rowIndex && data.get(i).getReferencia().equals((String)aValue) && data.get(i).getAlmacenesString().equals(data.get(rowIndex).getAlmacenesString())){
//	    				yaExiste = true;
//	    			}
//	    		}
//	            if(!yaExiste){
            data.get(rowIndex).setReferencia((String) aValue);
//	            }else JOptionPane.showMessageDialog(null, "La referencia ingresada ya existe", "Referencia repetida", JOptionPane.ERROR_MESSAGE);
        }
        if (columnIndex == 1) {
            data.get(rowIndex).setPlanta((String) aValue);
        }
        if (columnIndex == 2) {
            data.get(rowIndex).setAltura((String) aValue);
        }
        if (columnIndex == 3) {
            data.get(rowIndex).setColor((String) aValue);
        }
        if (columnIndex == 4) {
            data.get(rowIndex).setMaterial((String) aValue);
        }
//	        if (columnIndex == 6) {
//	            data.get(rowIndex).setPrecioVenta((int) aValue);
//	        }
//	        if (columnIndex == 5) {
//	            data.get(rowIndex).setPrecioCompra((int) aValue);
//	        }
        if (columnIndex == 7) {
            data.get(rowIndex).setCantidad((int) aValue);
        }
        if (columnIndex == 8) {
            data.get(rowIndex).setPrecioCosto((int) aValue);
        }
        if (columnIndex == 9) {
            data.get(rowIndex).setPrecioVenta((int) aValue);
        }
        if (columnIndex == 10) {
            data.get(rowIndex).setCategoria((String) aValue);
        }
        if (columnIndex == 11) {
            try {
                data.get(rowIndex).setFecha(Zapato.getFechaFromString((String) aValue));
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, "Fecha inválida. El formato de fecha apropiado es " + Zapato.FORMATO_FECHA + "\nEjemplo: " + Zapato.cadenafechaEjemplo(), "ERROR", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
}
