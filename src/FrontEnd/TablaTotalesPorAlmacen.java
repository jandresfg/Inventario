package FrontEnd;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import BackEnd.Almacen;
import BackEnd.Zapato;

public class TablaTotalesPorAlmacen extends AbstractTableModel {

    ArrayList<Object[]> data = new ArrayList<Object[]>();
    //String colNames[] = { "Almacen", "Proveedor", "Referencia", "Cantidad Total", "Precio Costo Total", "Precio Venta Total"  };
    String colNames[] = {"Almacen", "Proveedor", "Referencia", "Costo Unitario", "Venta Unitario", "Cantidad Total", "Costo Total", "Venta Total", "Dama", "Caballero", "Infantil"};

    Class<?> colClasses[] = {String.class, String.class, String.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, String.class, String.class, String.class};

    public TablaTotalesPorAlmacen(ArrayList<Object[]> totales) {
        data = totales;
    }

    public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        return colNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    public String getColumnName(int columnIndex) {
        return colNames[columnIndex];
    }

    public Class<?> getColumnClass(int columnIndex) {
        return colClasses[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//	    	Celdas no son editables
//	    	System.out.println("Almacenes setValue: "+aValue);
//	    	if (columnIndex == 0) {
//	            data.get(rowIndex).setCiudad((String) aValue);
//	        }
//	        if (columnIndex == 1) {
//	            data.get(rowIndex).setAlmacen((String) aValue);
//	        }
//	        if (columnIndex == 2) {
//	            data.get(rowIndex).setDireccion((String) aValue);
//	        }
//	        if (columnIndex == 3) {
//	            data.get(rowIndex).setTelefono((int) aValue);
//	        }
//	        if (columnIndex == 4) {
//	            data.get(rowIndex).setDireccion((String) aValue);
//	        }
//	        if (columnIndex == 5) {
//	            data.get(rowIndex).setNit((String) aValue);
//	        }
//	        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public ArrayList<Object[]> getData() {
        return data;
    }
}
