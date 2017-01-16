package FrontEnd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import BackEnd.Almacen;
import BackEnd.Proveedor;
import BackEnd.Zapato;

public class TablaProveedor  extends AbstractTableModel 
{



	
	    List<Proveedor> data = new ArrayList<Proveedor>();
	    public List<Proveedor> getData() {
			return data;
		}


		String colNames[] = { "Codigo", "Nombre", "Fabrica", "Direccion", "Telefono", "Ciudad" };
	    Class<?> colClasses[] = { Integer.class, String.class, String.class , String.class, String.class, String.class};

	    public TablaProveedor(List<Proveedor> arr) 
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
	            return data.get(rowIndex).getCodigo();
	        }
	        if (columnIndex == 1) {
	            return data.get(rowIndex).getNombre();
	        }
	        if (columnIndex == 2) {
	            return data.get(rowIndex).getFabrica();
	        }
	        if (columnIndex == 3) {
	            return data.get(rowIndex).getDireccion();
	        }
	        if (columnIndex == 4) {
	            return data.get(rowIndex).getTelefono();
	        }
	        if (columnIndex == 5) {
	            return data.get(rowIndex).getCiudad();
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
	    	System.out.println("Proveedores setValue: "+aValue);
	    	if (columnIndex == 0) {
	            data.get(rowIndex).setCodigo((int) aValue);
	        }
	        if (columnIndex == 1) {
	            data.get(rowIndex).setNombre((String) aValue);
	        }
	        if (columnIndex == 2) {
	            data.get(rowIndex).setFabrica((String) aValue);
	        }
	        if (columnIndex == 3) {
	            data.get(rowIndex).setDireccion((String) aValue);
	        }
	        if (columnIndex == 4) {
	       if(aValue instanceof Integer)
	       {
	            data.get(rowIndex).setTelefono((Integer) aValue + "");

	       }
	       else
	       {
	    	   data.get(rowIndex).setTelefono((String) aValue);
	       }
	        
	        }
	        if (columnIndex == 5) {
	            data.get(rowIndex).setCiudad((String) aValue);
	        }
	        fireTableCellUpdated(rowIndex, columnIndex);
	    }
	}

	

