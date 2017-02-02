package FrontEnd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import BackEnd.Almacen;
import BackEnd.Zapato;

public class TablaAlmacen  extends AbstractTableModel 
{
	


	
	    List<Almacen> data = new ArrayList<Almacen>();
	    String colNames[] = { "Ciudad", "Almacen", "Direccion", "Telefono", "Razon Social", "NIT" };
	    Class<?> colClasses[] = { String.class, String.class, String.class , String.class, String.class, String.class};

	    public TablaAlmacen(List<Almacen> arr) 
	    {
	        data = arr;
	        Collections.sort(data);

	    }

	    public int getRowCount() {
	        return data.size();
	    }

	    public int getColumnCount() {
	        return colNames.length;
	    }
	
	    public Object getValueAt(int rowIndex, int columnIndex) {
	        if (columnIndex == 0) {
	            return data.get(rowIndex).getCiudad();
	        }
	        if (columnIndex == 1) {
	            return data.get(rowIndex).getAlmacen();
	        }
	        if (columnIndex == 2) {
	            return data.get(rowIndex).getDireccion();
	        }
	        if (columnIndex == 3) {
	            return data.get(rowIndex).getTelefono();
	        }
	        if (columnIndex == 4) {
	            return data.get(rowIndex).getRazonSocial();
	        }
	        if (columnIndex == 5) {
	            return data.get(rowIndex).getNit();
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
	        return true;
	    }

	    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	    	System.out.println("Almacenes setValue: "+aValue);
	    	if (columnIndex == 0) {
	            data.get(rowIndex).setCiudad((String) aValue);
	        }
	        if (columnIndex == 1) {
	            data.get(rowIndex).setAlmacen((String) aValue);
	        }
	        if (columnIndex == 2) {
	            data.get(rowIndex).setDireccion((String) aValue);
	        }
	        if (columnIndex == 3) {
	            data.get(rowIndex).setTelefono((String) aValue);
	        }
	        if (columnIndex == 4) {
	            data.get(rowIndex).setRazonSocial((String) aValue);
	        }
	        if (columnIndex == 5) {
	            data.get(rowIndex).setNit((String) aValue);
	        }
	        fireTableCellUpdated(rowIndex, columnIndex);
	    }

		public List<Almacen> getData() {
			return data;
		}
	}

	

