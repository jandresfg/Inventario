package BackEnd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.ComboBoxModel;

import org.apache.commons.collections.list.SetUniqueList;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Mundo {

    private List<Almacen> almacenes;
    private List<Proveedor> proveedores;
    private List<Zapato> zapatos;
    private List<String>fechas;
    private List<String> fechasRepo;
	public static final String FORMATO_FECHA = "dd-MMM-yyyy";

    public Mundo() {

        almacenes = new ArrayList<Almacen>();

        proveedores = new ArrayList<Proveedor>();
        zapatos = new ArrayList<Zapato>();
        fechas = new ArrayList<String>();
        fechasRepo= new ArrayList<String>();
        //primero se cargan los proveedores y almacenes, luego los Zapatos.
        //para que no apunten a proveedores/almacenes no cargados aun
        try {
            cargarProveedores();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            cargarAlmacenes();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            cargarZapatos();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void addDateToARRNORMAL(String fecha) {

    	boolean centinela = false;
    	for (int i = 0; i < fechas.size(); i++) {
    		String x = fechas.get(i);
    		if(x.equals(fecha))
    		{
    			centinela =true;
    			break;
    		}
			
		}
        if(!centinela)
        {
			fechas.add(fecha);

        }
    	
	}
    private void addDateToARRREPO(String fecha) {

    	boolean centinela = false;
    	for (int i = 0; i < fechasRepo.size(); i++) {
    		String x = fechasRepo.get(i);
    		if(x.equals(fecha))
    		{
    			centinela =true;
    			break;
    		}
			
		}
        if(!centinela)
        {
        	fechasRepo.add(fecha);

        }
    	
	}

    public String agregarZapato(Zapato pZapato) {
        zapatos.add(pZapato);
        guardar();

        return "Se ha agregado exitosamente el zapato de Referencia: \"" + pZapato.getReferencia() + "\" y Almacén: \"" + pZapato.getAlmacenesString() + "\"";
    }

    public String agregarReposicion(Zapato pZapato) {
        zapatos.add(pZapato);

        guardar();

        return "Se ha agregado exitosamente la reposición de Referencia: \"" + pZapato.getReferencia() + "\" y Almacén: \"" + pZapato.getAlmacenesString() + "\"";
    }

    public String agregarProovedores(Proveedor pProveedor, String flag) {
        if (flag.equals("facil")) {
            proveedores.add(pProveedor);
            guardar();
        } else {
            proveedores.remove(pProveedor.getCodigo() - 1);
            proveedores.add(pProveedor);
            guardar();
        }

        return "Se ha agregado exitosamente el proveedor " + pProveedor.getNombre();

    }

    public String agregarAlmacen(Almacen pAlmacen) {
        almacenes.add(pAlmacen);
        guardar();

        return "Se ha agregado exitosamente el almancen " + pAlmacen.getAlmacen();

    }

    public void cargarZapatos() throws Exception {
        String cadena;
        FileReader f = new FileReader("zapatos.txt");
        BufferedReader b = new BufferedReader(f);
        while ((cadena = b.readLine()) != null) {

            String[] arr = cadena.split("\\}");
            
            for(int i = 0;i<arr.length; i++){
            	if(i==14)
            	{
            		if(arr[i].equals("0"))
            		{
            			arr[i]= " ";
            		}
            		else
            		{
                    	arr[i] = arr[i].trim();

            		}
            	}
            	else
            	{
            	arr[i] = arr[i].trim();}
            }


            Zapato zap = new Zapato(arr[0], arr[1], arr[2], arr[3], arr[4], Integer.parseInt(arr[5]), Integer.parseInt(arr[6]), Integer.parseInt(arr[7]), arr[8], Integer.parseInt(arr[9]), Zapato.getFechaFromString(arr[10]), Boolean.parseBoolean(arr[13]),arr[14]);
            zap.setProveedores(darProveedores(arr[11]));
            zap.setAlmacenes(darAlmacenes(arr[12]));
            try {
            	zap.setFechaLlegada(zap.getFechaFromString(arr[15]));
			} catch (IndexOutOfBoundsException e) {
				//nada que hacer acá, significa que no hay fecha de llegada, 
				//se deja que quede null, como queda por defecto
			}
            zapatos.add(zap);

        }
        b.close();
    }

    public void cargarAlmacenes() throws Exception {
        String alma;

        FileReader ff = new FileReader("almacenes.txt");
        BufferedReader bb = new BufferedReader(ff);

        while ((alma = bb.readLine()) != null) {
            String[] arr = alma.split("\\,", -1);
            for(int i = 0;i<arr.length; i++){arr[i] = arr[i].trim();}

            Almacen almacen = new Almacen(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
            almacenes.add(almacen);

        }
        bb.close();

    }

    public void cargarProveedores() throws Exception {

        String prov;
        FileReader fff = new FileReader("proveedores.txt");
        BufferedReader bbb = new BufferedReader(fff);

        while ((prov = bbb.readLine()) != null) {
            String[] arr = prov.split("\\,", -1);
            for(int i = 0;i<arr.length; i++){arr[i] = arr[i].trim();}
            Proveedor provee = new Proveedor(Integer.parseInt(arr[0]), arr[1], arr[2], arr[3], arr[4], arr[5]);
            proveedores.add(provee);
        }
        bbb.close();

    }

    public List<Zapato> darZapatos() {
        List<Zapato> zapatosSinReposiciones = new ArrayList<Zapato>();
        for (Zapato x : zapatos) {
            if (!x.esReposicion()) {
                zapatosSinReposiciones.add(x);
            }
        }
        if (zapatosSinReposiciones.isEmpty()) {
            return zapatosSinReposiciones;
        } else {
            return quickSortZapatos(zapatosSinReposiciones, 0, zapatosSinReposiciones.size() - 1);
        }
    }

    public List<Zapato> darReposiciones() {
        List<Zapato> soloReposiciones = new ArrayList<Zapato>();
        for (Zapato x : zapatos) {
            if (x.esReposicion()) {
                soloReposiciones.add(x);
            }
        }
        if (soloReposiciones.isEmpty()) {
            return soloReposiciones;
        } else {
            return quickSortZapatos(soloReposiciones, 0, soloReposiciones.size() - 1);
        }
    }

    public List<Almacen> darAlmacenes() {
        return almacenes;

    }

    public List<Proveedor> darProveedores() {
Collections.sort(proveedores, new Comparator<Proveedor>(){
    public int compare(Proveedor s1, Proveedor s2) {
        return s1.compareTo(s2);
    }
});
        return proveedores;

    }

    /**
     * Detecta codigos que no se han usado en la lista de proveedores
     *
     * @return el primer codigo encontrado
     */
    public String darCodigo() {

        //se crea una lista del tamano de la lista de proveedores con numeros ordenados asendentes empezando en 1
        ArrayList listaIdealDeCodigos = new ArrayList();
        for (int i = 0; i < proveedores.size(); i++) {
            listaIdealDeCodigos.add(i, i + 1);
        }

        //luego se recorre esa lista y se retorna el primer numero que no sea codigo de ningun proveedor
        for (int i = 0; i < listaIdealDeCodigos.size(); i++) {
            int codigoAct = (int) listaIdealDeCodigos.get(i);
            boolean codigoEncontrado = false;
            for (int j = 0; j < proveedores.size() && !codigoEncontrado; j++) {
                Proveedor x = proveedores.get(j);
                if (codigoAct == x.getCodigo()) {
                    codigoEncontrado = true;
                    if (x.getFabrica().equals("") || x.getFabrica().equals(" ")) {
                        return "" + codigoAct;
                    }
                }
            }
            if (!codigoEncontrado) {
                return "" + codigoAct;
            }
        }

        /*
        Si no se encuentra ningun numero que no sea codigo de ningun proveedor, 
        significa que la lista de proveedores es igual a la lista ideal.
        Entonces se retorna simplemente el siguiente numero
         */
        return "" + (listaIdealDeCodigos.size() + 1);

        /*for (int i = 0; i < proveedores.size(); i++) {
            Proveedor z = proveedores.get(i);
            int numeroActual = z.getCodigo();
            if (z.getFabrica().equals(" ") || z.getFabrica().equals("")) {

                System.out.println(z.getFabrica());
                return i + 1 + "";
            }

        }
        System.out.println("FINALIZO");

        return (proveedores.size() + 1) + "";*/
    }

    public void exportarExcel(String rutaDestino) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet sheetZapatos = workbook.createSheet("Zapatos");

        int rownum = 0;
        Row row = sheetZapatos.createRow(rownum++);
        row.createCell(0).setCellValue("Almacen");
        row.createCell(1).setCellValue("Proveedor");
        row.createCell(2).setCellValue("Referencia");
        row.createCell(3).setCellValue("Color");
        row.createCell(4).setCellValue("Material");
        row.createCell(5).setCellValue("Planta");
        row.createCell(6).setCellValue("Altura");
        row.createCell(7).setCellValue("Cantidad");
        row.createCell(8).setCellValue("Precio Venta");
        row.createCell(9).setCellValue("Precio Costo");
        row.createCell(10).setCellValue("Categoria");
        row.createCell(11).setCellValue("Fecha");
        row.createCell(12).setCellValue("Fecha de Llegada");

        for (Zapato z : zapatos) {
            row = sheetZapatos.createRow(rownum++);

            Cell cellAlmacenes = row.createCell(0);
            cellAlmacenes.setCellValue(z.getAlmacenesString().replace("{", "\n"));
            CellStyle cs = workbook.createCellStyle();
            cs.setWrapText(true);
            cellAlmacenes.setCellStyle(cs);
            sheetZapatos.autoSizeColumn(0);

            Cell cellProveedores = row.createCell(1);
            cellProveedores.setCellValue(z.getProveedoresString().replace("{", "\n"));
            cellProveedores.setCellStyle(cs);
            sheetZapatos.autoSizeColumn(1);

            row.createCell(2).setCellValue(z.getReferencia());
            sheetZapatos.autoSizeColumn(2);

            row.createCell(3).setCellValue(z.getColor());
            sheetZapatos.autoSizeColumn(3);

            row.createCell(4).setCellValue(z.getMaterial());
            sheetZapatos.autoSizeColumn(4);

            row.createCell(5).setCellValue(z.getPlanta());
            sheetZapatos.autoSizeColumn(5);

            row.createCell(6).setCellValue(z.getAltura());
            sheetZapatos.autoSizeColumn(6);

            row.createCell(7).setCellValue(z.getCantidad());
            sheetZapatos.autoSizeColumn(7);

            row.createCell(8).setCellValue(z.getPrecioVenta());
            sheetZapatos.autoSizeColumn(8);

            row.createCell(9).setCellValue(z.getPrecioCosto());
            sheetZapatos.autoSizeColumn(9);

            row.createCell(10).setCellValue(z.getCategoria());
            sheetZapatos.autoSizeColumn(10);

            row.createCell(11).setCellValue(z.getStringFecha(z.getFecha()));
            sheetZapatos.autoSizeColumn(11);
            
            row.createCell(12).setCellValue(z.getStringFecha(z.getFechaLlegada()));
            sheetZapatos.autoSizeColumn(12);

        }

        XSSFSheet sheetAlmacenes = workbook.createSheet("Almacenes");

        rownum = 0;
        row = sheetAlmacenes.createRow(rownum++);
        row.createCell(0).setCellValue("Ciudad");
        row.createCell(1).setCellValue("Almacen");
        row.createCell(2).setCellValue("Direccion");
        row.createCell(3).setCellValue("Telefono");
        row.createCell(4).setCellValue("Razon Social");
        row.createCell(5).setCellValue("NIT");

        for (Almacen a : almacenes) {
            row = sheetAlmacenes.createRow(rownum++);

            row.createCell(0).setCellValue(a.getCiudad());
            sheetAlmacenes.autoSizeColumn(0);

            row.createCell(1).setCellValue(a.getAlmacen());
            sheetAlmacenes.autoSizeColumn(1);

            row.createCell(2).setCellValue(a.getDireccion());
            sheetAlmacenes.autoSizeColumn(2);

            row.createCell(3).setCellValue(a.getTelefono());
            sheetAlmacenes.autoSizeColumn(3);

            row.createCell(4).setCellValue(a.getRazonSocial());
            sheetAlmacenes.autoSizeColumn(4);

            row.createCell(5).setCellValue(a.getNit());
            sheetAlmacenes.autoSizeColumn(5);
        }

        XSSFSheet sheetProveedores = workbook.createSheet("Proveedores");

        rownum = 0;
        row = sheetProveedores.createRow(rownum++);
        row.createCell(0).setCellValue("Codigo");
        row.createCell(1).setCellValue("Nombre");
        row.createCell(2).setCellValue("Fabrica");
        row.createCell(3).setCellValue("Direccion");
        row.createCell(4).setCellValue("Telefono");
        row.createCell(5).setCellValue("Ciudad");

        for (Proveedor p : proveedores) {
            row = sheetProveedores.createRow(rownum++);

            row.createCell(0).setCellValue(p.getCodigo());
            sheetProveedores.autoSizeColumn(0);

            row.createCell(1).setCellValue(p.getNombre());
            sheetProveedores.autoSizeColumn(1);

            row.createCell(2).setCellValue(p.getFabrica());
            sheetProveedores.autoSizeColumn(2);

            row.createCell(3).setCellValue(p.getDireccion());
            sheetProveedores.autoSizeColumn(3);

            row.createCell(4).setCellValue(p.getTelefono());
            sheetProveedores.autoSizeColumn(4);

            row.createCell(5).setCellValue(p.getCiudad());
            sheetProveedores.autoSizeColumn(5);

        }

        XSSFSheet sheetTotales = workbook.createSheet("Totales");
        rownum = 0;
        row = sheetTotales.createRow(rownum++);
        row.createCell(0).setCellValue("Almacen");
        row.createCell(1).setCellValue("Proveedor");
        row.createCell(2).setCellValue("Referencia");
        row.createCell(3).setCellValue("Cantidad Total");
        row.createCell(4).setCellValue("Precio Venta Total");
        row.createCell(5).setCellValue("Precio Costo Total");

        for (Object[] o : darTotales()) {
            row = sheetTotales.createRow(rownum++);

            row.createCell(0).setCellValue((String) o[0]);
            sheetTotales.autoSizeColumn(0);

            row.createCell(1).setCellValue((String) o[2]);
            sheetTotales.autoSizeColumn(1);

            row.createCell(2).setCellValue((String) o[1]);
            sheetTotales.autoSizeColumn(2);

            row.createCell(3).setCellValue((Integer) o[3]);
            sheetTotales.autoSizeColumn(3);

            row.createCell(4).setCellValue((Integer) o[5]);
            sheetTotales.autoSizeColumn(4);

            row.createCell(5).setCellValue((Integer) o[4]);
            sheetTotales.autoSizeColumn(5);
        }

        try {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(rutaDestino + "\\inventario_export_" + new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date()) + ".xlsx"));
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void llenarProveedores(FileInputStream excel) {
        // TODO Auto-generated method stub

    }

    public void llenarAlmacenes(FileInputStream excel) {
        // TODO Auto-generated method stub

    }

    public void vender(String referencia, int cantidad) {
        int indiceInteresante = 0;
        for (int i = 0; i < zapatos.size(); i++) {
            Zapato x = zapatos.get(i);
            x.vender(cantidad);
            if (x.getReferencia().equals(referencia)) {
                zapatos.remove(i);
                zapatos.add(x);
                break;
            }

        }

    }

    public String eliminarZapato(String referencia, String codigoProveedor, String codigoAlmacen, String color) {
        for (int i = 0; i < zapatos.size(); i++) {
            Zapato x = zapatos.get(i);
            if (!x.esReposicion()) {
                if (x.getReferencia().equals(referencia) && x.getColor().equalsIgnoreCase(color)) {
                    for (int j = 0; j < x.getProveedores().size(); j++) {
                        Proveedor y = x.getProveedores().get(j);
                        if (y.getCodigo() == Integer.parseInt(codigoProveedor)) {

                            for (int k = 0; k < x.getAlamacenes().size(); k++) {
                                Almacen alm = x.getAlamacenes().get(k);

                                if (alm.getCiudad().equals(codigoAlmacen)) {
                                    zapatos.remove(i);
                                    return "Se ha eliminado exitosamente el zapato de referencia: " + referencia
                                            + ", color "+color+" y proveedor relacionado con código: " + codigoProveedor;
                                }
                            }
                        }
                    }
                }
            }
        }
        return "No se ha encontrado el Zapato especificado para eliminar.";
    }

    public void guardar() {
        try {
            if (proveedores.size() > 0) {
                File fw = new File("proveedores.txt");

                PrintWriter out = new PrintWriter(fw);

                for (int i = 0; i < proveedores.size(); i++) {
                    Proveedor pProveedor = proveedores.get(i);
                    out.println(pProveedor.getCodigo() + "," + pProveedor.getNombre() + "," + pProveedor.getFabrica() + "," + pProveedor.getDireccion() + "," + pProveedor.getTelefono() + "," + pProveedor.getCiudad());
                }
                out.close();
            } else {
                File fw = new File("proveedores.txt");
                fw.delete();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (almacenes.size() > 0) {
                File fw = new File("almacenes.txt");

                PrintWriter out = new PrintWriter(fw);

                for (int i = 0; i < almacenes.size(); i++) {

                    Almacen pAlmacen = almacenes.get(i);
                    out.println(pAlmacen.getCiudad() + "," + pAlmacen.getAlmacen() + "," + pAlmacen.getDireccion() + "," + pAlmacen.getTelefono() + "," + pAlmacen.getRazonSocial() + "," + pAlmacen.getNit());
                }
                out.close();
            } else {
                File fw = new File("almacenes.txt");
                fw.delete();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (zapatos.size() > 0) {
                File fw = new File("zapatos.txt");

                PrintWriter out = new PrintWriter(fw);

                for (int i = 0; i < zapatos.size(); i++) {

                    Zapato pZapato = zapatos.get(i);
                    out.println(pZapato.getReferencia() + "}" + pZapato.getPlanta() + "}" + pZapato.getAltura() + "}" + pZapato.getColor() + "}" + pZapato.getMaterial() + "}" + pZapato.getPrecioCosto() + "}" + pZapato.getPrecioVenta() + "}" + pZapato.getCantidad() + "}" + pZapato.getCategoria() + "}" + pZapato.getVendidos() + "}" + pZapato.getStringFecha(pZapato.getFecha()) + "}" + pZapato.getProveedoresString() + "}" + pZapato.getAlmacenesString() + "}" + pZapato.esReposicion()+"}" + pZapato.getNumeracion() + "}" + pZapato.getStringFecha(pZapato.getFechaLlegada()));

                }
                out.close();
            } else {
                File fw = new File("zapatos.txt");
                fw.delete();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void eliminarProveedor(int codigoAborrar) throws Exception {
        boolean borrado = false;
        for (int i = 0; i < proveedores.size() && !borrado; i++) {
            Proveedor x = proveedores.get(i);
            if (x.getCodigo() == codigoAborrar) {
                if (!proveedorEstaRelacionadoConZapato(codigoAborrar)) {
                    Proveedor prov = new Proveedor(x.getCodigo(), "", "", "", "", "");
                    proveedores.remove(i);
                    proveedores.add(prov);
                    borrado = true;
                    guardar();
                } else {
                    throw new Exception("El Proveedor con código '" + codigoAborrar + "' está vinculado a al menos una referencia de Zapato y no puede ser borrado");
                }
            }

        }
        if (!borrado) {
            throw new Exception("El Proveedor con código '" + codigoAborrar + "' no fue encontrado");
        }
    }

    /**
     *
     * @param codigoProveedor
     * @return true si el Proveedor con codigo dado por parametro se encuentra
     * en la lista de proveedores de cualquier zapato, false de lo contrario
     */
    private boolean proveedorEstaRelacionadoConZapato(int codigoProveedor) {
        for (Zapato z : zapatos) {
            for (Proveedor p : z.getProveedores()) {
                if (p.getCodigo() == codigoProveedor) {
                    return true;
                }
            }
        }
        return false;
    }

    public void eliminarAlmacen(String ciudad) throws Exception {
    	boolean encontro = false;
        for (int i = 0; i < almacenes.size() && !encontro; i++) {
            Almacen x = almacenes.get(i);
            if (x.getCiudad().equalsIgnoreCase(ciudad)) {
                if (!almacenEstaRelacionadoConZapato(ciudad)) {
                	almacenes.remove(i);
                    guardar();
                    encontro = true;
                } else {
                    throw new Exception("El Almac�n de ciudad '" + ciudad + "' est� vinculado a al menos una referencia de Zapato y no puede ser borrado");
                }
            }

        }
        if (!encontro) throw new Exception("No se ha encontrado el Almac�n con ciudad '"+ ciudad +"'");
    }

    /**
     *
     * @param ciudad
     * @return true si el Almacen con ciudad dada por parametro se encuentra en
     * la lista de almacenes de cualquier zapato, false de lo contrario
     */
    private boolean almacenEstaRelacionadoConZapato(String ciudad) {
        for (Zapato z : zapatos) {
            for (Almacen a : z.getAlamacenes()) {
                if (a.getCiudad().equals(ciudad)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setAlmacenes(List<Almacen> almacenes) {
        this.almacenes = almacenes;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public void setZapatos(List<Zapato> zapatos) {
        //mierda para identificar zapato y actualizarlo
        for (int i = 0; i < zapatos.size(); i++) {//el zapato que llega desde la JTable
            Zapato x = zapatos.get(i);

            for (int j = 0; j < this.zapatos.size(); j++) {//el zapato que esta ya en el mundo; el que hay que reemplazar
                Zapato z = this.zapatos.get(j);

                if (!z.esReposicion()
                        && z.getReferencia().equals(x.getReferencia())
                                                 && z.getColor().equals(x.getColor())
                        && x.getProveedoresString().equalsIgnoreCase(z.getProveedoresString())
                        && x.getAlmacenesString().equalsIgnoreCase(z.getAlmacenesString())) {

                    this.zapatos.set(j, x);

                }
            }
        }
    }

    public void setReposiciones(List<Zapato> reposiciones) {
        //mierda para identificar referencia y actualizarla
        for (int i = 0; i < zapatos.size(); i++) {//el zapato que llega desde la JTable
            Zapato x = zapatos.get(i);

            for (int j = 0; j < this.zapatos.size(); j++) {//el zapato que esta ya en el mundo; el que hay que reemplazar
                Zapato z = this.zapatos.get(j);

                if (z.esReposicion()
                        && z.getReferencia().equals(x.getReferencia())
                         && z.getColor().equals(x.getColor())
                        && x.getProveedoresString().equalsIgnoreCase(z.getProveedoresString())
                        && x.getAlmacenesString().equalsIgnoreCase(z.getAlmacenesString())) {

                    this.zapatos.set(j, x);

                }
            }
        }
    }

    public List<Proveedor> darProveedores(ArrayList<String> provs) {
        ArrayList<Proveedor> resp = new ArrayList<Proveedor>();
        for (Proveedor p : proveedores) {
            for (String s : provs) {
                if (String.valueOf(p.getCodigo()).equals(s.split(" - ")[0]) && p.getFabrica().equals(s.split(" - ")[1])) {
                    resp.add(p);
                }
            }
        }
        return resp;
    }

    public List<Almacen> darAlmacenes(ArrayList<String> alms) {
        ArrayList<Almacen> resp = new ArrayList<Almacen>();
        for (Almacen a : almacenes) {
            for (String s : alms) {
                if (a.getCiudad().equals(s)) {
                    resp.add(a);
                }
            }
        }
        return resp;
    }

    public List<Proveedor> darProveedores(String provs) {
        ArrayList<Proveedor> resp = new ArrayList<Proveedor>();
        String[] provsArr = provs.split("\\{", -1);
        for (Proveedor p : proveedores) {
            for (String s : provsArr) {
                if (String.valueOf(p.getCodigo()).equals(s.split(" - ")[0]) && p.getFabrica().equals(s.split(" - ")[1])) {
                    resp.add(p);
                }
            }
        }
        return resp;
    }

    public List<Almacen> darAlmacenes(String alms) {
        ArrayList<Almacen> resp = new ArrayList<Almacen>();
        String[] almsArr = alms.split("\\{");
        for (Almacen a : almacenes) {
            for (String s : almsArr) {
                if (a.getCiudad().equalsIgnoreCase(s)) {
                    resp.add(a);
                }
            }
        }
        return resp;
    }

//    /**
//     * Actualiza los NIT de los Almacenes relacionados a todos los Zapatos, por
//     * medio de comparacion del atributo "Ciudad"
//     */
//    public void actualizarNITsAlmacenes() {
//        for (Zapato z : zapatos) {
//            for (Almacen aTemp : z.getAlamacenes()) {
//                for (Almacen a : almacenes) {
//                    if (aTemp.getCiudad().equals(a.getCiudad())) {
//                        aTemp.setNit(a.getNit());//tal vez hay que borrarlo y voverlo a insertar
//                    }
//                }
//            }
//        }
//    }

//    /**
//     * Actualiza los "Alamcen" de los Almacenes relacionados a todos los
//     * Zapatos, por medio de comparacion del NIT
//     */
//    public void actualizarAlmacenesAlmacenes() {
//        for (Zapato z : zapatos) {
//            for (Almacen aTemp : z.getAlamacenes()) {
//                for (Almacen a : almacenes) {
//                    if (aTemp.getNit().equals(a.getNit())) {
//                        aTemp.setAlmacen((a.getAlmacen()));
//                    }
//                }
//            }
//        }
//    }

//    /**
//     * Actualiza los codigos de los Proveedores relacionados a todos los
//     * Zapatos, por medio de comparacion del Nombre
//     */
//    public void actualizarCodigosProveedores() {
//        for (Zapato z : zapatos) {
//            for (Proveedor pTemp : z.getProveedores()) {
//                for (Proveedor p : proveedores) {
//                    if (pTemp.getNombre().equals(p.getNombre())) {
//                        pTemp.setCodigo((p.getCodigo()));
//                    }
//                }
//            }
//        }
//    }

//    /**
//     * Actualiza los nombres de los Proveedores relacionados a todos los
//     * Zapatos, por medio de comparacion del codigo
//     */
//    public void actualizarNombresProveedores() {
//        for (Zapato z : zapatos) {
//            for (Proveedor pTemp : z.getProveedores()) {
//                for (Proveedor p : proveedores) {
//                    if (pTemp.getCodigo() == p.getCodigo()) {
//                        pTemp.setNombre((p.getNombre()));
//                    }
//                }
//            }
//        }
//    }

    public ArrayList<String> darListadoReferenciasZapatos() {
        ArrayList<String> res = new ArrayList<String>();
        res.add("Seleccione una referencia");
        for (Zapato z : zapatos) {
            if (!res.contains(z.getReferencia())) {
                res.add(z.getReferencia());
            }
        }

        return res;
    }

    public int[] darTotalesReferencia(String referencia) {
        int[] res = new int[3];

        for (Zapato z : zapatos) {
            if (z.getReferencia().equals(referencia)) {
                res[0] += z.getCantidad();
                res[1] += z.getCantidad() * z.getPrecioCosto();
                res[2] += z.getCantidad() * z.getPrecioVenta();
            }
        }

        return res;
    }

    public ArrayList<Object[]> darTotales() {
        ArrayList<Object[]> arr = new ArrayList<Object[]>();
        for (int i = 0; i < almacenes.size(); i++) {
            for (int j = 0; j < zapatos.size(); j++) {
                Object[] res = new Object[16];
                Almacen a = almacenes.get(i);
                Zapato z = zapatos.get(j);
                int sumaCantidad = 0;
                int sumaPrecioCosto = 0;
                int sumaPrecioVenta = 0;
                Almacen ka = z.getAlamacenes().get(0);
                if (ka.toString().equals(a.toString()) && !z.esReposicion()) {
                    sumaCantidad += z.getCantidad();
                    sumaPrecioCosto += z.getPrecioCosto() * z.getCantidad();
                    sumaPrecioVenta += z.getPrecioVenta() * z.getCantidad();

                }
                res[0] = a.toString();
                res[1] = z.getProveedores().get(0).toString();
                res[2] = z.getReferencia();
                res[3] = z.getColor();
                
                res[4] = z.getPlanta();
                res[5] = z.getAltura();

                
                res[6] = z.getPrecioCosto();
                res[7] = z.getPrecioVenta();
                res[8] = sumaCantidad;
                res[9] = sumaPrecioCosto;
                res[10] = sumaPrecioVenta;
                res[15] = z.getStringFecha(z.getFecha());  
                if (z.getCategoria().equals("CABALLERO")) {
                    res[12] = "X";
                    res[11] = " ";
                    res[13] = " ";
                } else if (z.getCategoria().equals("DAMA")) {
                    res[11] = "X";
                    res[12] = " ";
                    res[13] = " ";
                } else if (z.getCategoria().equals("INFANTIL")) {
                    res[13] = "X";
                    res[11] = " ";
                    res[12] = " ";

                }
                String numeracion = z.getNumeracion();
                if(numeracion.equals("0"))
                {
                    res[14] = "";

                }
                else
                {
                res[14] = numeracion;
                }

                if ((int) res[8] > 0) {

                    arr.add(res);
                }
            }
        }
        return quickSort(arr, 0, (arr.size()) - 1);
    }



	public ArrayList<Object[]> darGrandesTotales(boolean selecionado) {

        ArrayList<Object[]> arr = new ArrayList<Object[]>();

        ArrayList<Almacen> almas = new ArrayList<Almacen>();

        for (int j = 0; j < zapatos.size(); j++) {
            Zapato z = zapatos.get(j);

            Almacen ka = z.getAlamacenes().get(0);
            if (z.getCantidad() > 0 && z.esReposicion() == selecionado) {
                boolean flag = false;
                for (int k = 0; k < almas.size() && !flag; k++) {
                    Almacen papitas = almas.get(k);
                    if (papitas.getCiudad().equals(ka.getCiudad())) {

                        flag = true;
                        papitas.setTotalCosto(z.getPrecioCosto() * z.getCantidad());

                        ka.setTotalPares(z.getCantidad());

                        papitas.setTotalVenta(z.getPrecioVenta() * z.getCantidad());

                    }
                }
                if (!flag) {
                    ka.setTotalCosto(z.getPrecioCosto() * z.getCantidad());

                    ka.setTotalPares(z.getCantidad());

                    ka.setTotalVenta(z.getPrecioVenta() * z.getCantidad());

                    almas.add(ka);

                }
            }
        }
        Object[] res = new Object[4];

        for (int i = 0; i < almas.size(); i++) {
            Almacen x = almas.get(i);

            res[0] = x.getCiudad();
            res[1] = x.getTotalCosto();

            res[2] = x.getTotalVenta();
            res[3] = x.getTotalPares();

            arr.add(res);
            x.volverCero();
            res = new Object[4];

        }

        Object[] ras = new Object[4];

        ras[0] = "TOTAL";
        ras[1] = "";

        ras[2] = "";
        ras[3] = "";
        arr.add(ras);

        ArrayList<Object[]> papitas = setModelToHiperDuperTotal(selecionado);
        arr.addAll(papitas);

        return arr;
    }

	
	public ArrayList<Object[]>darGrandesTotalesUnaFecha(boolean selecionado, String fecha) {

        ArrayList<Object[]> arr = new ArrayList<Object[]>();

        ArrayList<Almacen> almas = new ArrayList<Almacen>();

        for (int j = 0; j < zapatos.size(); j++) {
            Zapato z = zapatos.get(j);

            Almacen ka = z.getAlamacenes().get(0);
            if (z.getCantidad() > 0 && z.esReposicion() == selecionado && z.getStringFecha().equals(fecha)) {
                boolean flag = false;
                for (int k = 0; k < almas.size() && !flag; k++) {
                    Almacen papitas = almas.get(k);
                    if (papitas.getCiudad().equals(ka.getCiudad())) {

                        flag = true;
                        papitas.setTotalCosto(z.getPrecioCosto() * z.getCantidad());

                        ka.setTotalPares(z.getCantidad());

                        papitas.setTotalVenta(z.getPrecioVenta() * z.getCantidad());

                    }
                }
                if (!flag) {
                    ka.setTotalCosto(z.getPrecioCosto() * z.getCantidad());

                    ka.setTotalPares(z.getCantidad());

                    ka.setTotalVenta(z.getPrecioVenta() * z.getCantidad());

                    almas.add(ka);

                }
            }
        }
        Object[] res = new Object[4];

        for (int i = 0; i < almas.size(); i++) {
            Almacen x = almas.get(i);

            res[0] = x.getCiudad();
            res[1] = x.getTotalCosto();

            res[2] = x.getTotalVenta();
            res[3] = x.getTotalPares();

            arr.add(res);
            x.volverCero();
            res = new Object[4];

        }

        Object[] ras = new Object[4];

        ras[0] = "TOTAL";
        ras[1] = "";

        ras[2] = "";
        ras[3] = "";
        arr.add(ras);

        ArrayList<Object[]> papitas = setModelToHiperDuperTotalConFecha(selecionado,fecha);
        arr.addAll(papitas);

        return arr;
    }
	
	public ArrayList<Object[]>darGrandesTotalesDosFechas(boolean selecionado, Date fecha, Date fecha2) {

        ArrayList<Object[]> arr = new ArrayList<Object[]>();

        ArrayList<Almacen> almas = new ArrayList<Almacen>();

        for (int j = 0; j < zapatos.size(); j++) {
            Zapato z = zapatos.get(j);

            Almacen ka = z.getAlamacenes().get(0);
            if (z.getCantidad() > 0 && z.esReposicion() == selecionado && z.getFecha().after(fecha)&&z.getFecha().before(fecha2)) {
                boolean flag = false;
                for (int k = 0; k < almas.size() && !flag; k++) {
                    Almacen papitas = almas.get(k);
                    if (papitas.getCiudad().equals(ka.getCiudad())) {

                        flag = true;
                        papitas.setTotalCosto(z.getPrecioCosto() * z.getCantidad());

                        ka.setTotalPares(z.getCantidad());

                        papitas.setTotalVenta(z.getPrecioVenta() * z.getCantidad());

                    }
                }
                if (!flag) {
                    ka.setTotalCosto(z.getPrecioCosto() * z.getCantidad());

                    ka.setTotalPares(z.getCantidad());

                    ka.setTotalVenta(z.getPrecioVenta() * z.getCantidad());

                    almas.add(ka);

                }
            }
        }
        Object[] res = new Object[4];

        for (int i = 0; i < almas.size(); i++) {
            Almacen x = almas.get(i);

            res[0] = x.getCiudad();
            res[1] = x.getTotalCosto();

            res[2] = x.getTotalVenta();
            res[3] = x.getTotalPares();

            arr.add(res);
            x.volverCero();
            res = new Object[4];

        }

        Object[] ras = new Object[4];

        ras[0] = "TOTAL";
        ras[1] = "";

        ras[2] = "";
        ras[3] = "";
        arr.add(ras);

        ArrayList<Object[]> papitas = setModelToHiperDuperTotalConFechas(selecionado,fecha,fecha2);
        arr.addAll(papitas);

        return arr;
    }
	
	
	
    public String verificarCodigoProveedor(Proveedor prov) {

        boolean codigoUsado = false;
        for (int i = 0; i < proveedores.size() && !codigoUsado; i++) {
            if (prov.getCodigo() == proveedores.get(i).getCodigo()) {
                codigoUsado = true;
            }
        }

        if (!codigoUsado || prov.getCodigo() > proveedores.size()) {
            return "facil";

        } else if (proveedores.get(prov.getCodigo() - 1).getFabrica().equals("")) {
            return "posicion";

        } else {
            return "no";

        }

    }

    public ArrayList<Object[]> darGrandesTotalesFiltrado(String filtro, boolean selecionado) {

        ArrayList<Object[]> arr = new ArrayList<Object[]>();

        ArrayList<Almacen> almas = new ArrayList<Almacen>();

        for (int j = 0; j < zapatos.size(); j++) {
            Zapato z = zapatos.get(j);

            Almacen ka = z.getAlamacenes().get(0);
            if (z.getCantidad() > 0 && z.getCategoria().equals(filtro) && z.esReposicion() == selecionado) {
                boolean flag = false;
                for (int k = 0; k < almas.size() && !flag; k++) {
                    Almacen papitas = almas.get(k);
                    if (papitas.getCiudad().equals(ka.getCiudad())) {

                        flag = true;
                        papitas.setTotalCosto(z.getPrecioCosto() * z.getCantidad());

                        ka.setTotalPares(z.getCantidad());

                        papitas.setTotalVenta(z.getPrecioVenta() * z.getCantidad());

                    }
                }
                if (!flag) {
                    ka.setTotalCosto(z.getPrecioCosto() * z.getCantidad());

                    ka.setTotalPares(z.getCantidad());

                    ka.setTotalVenta(z.getPrecioVenta() * z.getCantidad());

                    almas.add(ka);

                }
            }
        }
        Object[] res = new Object[4];
        int totalCosto = 0;
        int totalVenta = 0;
        int totalPares = 0;

        for (int i = 0; i < almas.size(); i++) {
            Almacen x = almas.get(i);

            res[0] = x.getCiudad();
            res[1] = x.getTotalCosto();

            res[2] = x.getTotalVenta();
            res[3] = x.getTotalPares();

            totalCosto += x.getTotalCosto();
            totalVenta += x.getTotalVenta();
            totalPares += x.getTotalPares();

            arr.add(res);
            x.volverCero();
            res = new Object[4];

        }
        Object[] ras = new Object[4];

        ras[0] = "TOTAL";
        ras[1] = totalCosto;

        ras[2] = totalVenta;
        ras[3] = totalPares;
        arr.add(ras);

        return arr;
    }

    
    public ArrayList<Object[]>   darGrandesTotalesFiltradoFecha(String filtro, boolean selecionado,Date fecha1, Date fecha2) {

        ArrayList<Object[]> arr = new ArrayList<Object[]>();

        ArrayList<Almacen> almas = new ArrayList<Almacen>();

        for (int j = 0; j < zapatos.size(); j++) {
            Zapato z = zapatos.get(j);

            Almacen ka = z.getAlamacenes().get(0);
            if (z.getCantidad() > 0 && z.getCategoria().equals(filtro) && z.esReposicion() == selecionado  && z.getFecha().after(fecha1)&&z.getFecha().before(fecha2)) {
                boolean flag = false;
                for (int k = 0; k < almas.size() && !flag; k++) {
                    Almacen papitas = almas.get(k);
                    if (papitas.getCiudad().equals(ka.getCiudad())) {

                        flag = true;
                        papitas.setTotalCosto(z.getPrecioCosto() * z.getCantidad());

                        ka.setTotalPares(z.getCantidad());

                        papitas.setTotalVenta(z.getPrecioVenta() * z.getCantidad());

                    }
                }
                if (!flag) {
                    ka.setTotalCosto(z.getPrecioCosto() * z.getCantidad());

                    ka.setTotalPares(z.getCantidad());

                    ka.setTotalVenta(z.getPrecioVenta() * z.getCantidad());

                    almas.add(ka);

                }
            }
        }
        Object[] res = new Object[4];
        int totalCosto = 0;
        int totalVenta = 0;
        int totalPares = 0;

        for (int i = 0; i < almas.size(); i++) {
            Almacen x = almas.get(i);

            res[0] = x.getCiudad();
            res[1] = x.getTotalCosto();

            res[2] = x.getTotalVenta();
            res[3] = x.getTotalPares();

            totalCosto += x.getTotalCosto();
            totalVenta += x.getTotalVenta();
            totalPares += x.getTotalPares();

            arr.add(res);
            x.volverCero();
            res = new Object[4];

        }
        Object[] ras = new Object[4];

        ras[0] = "TOTAL";
        ras[1] = totalCosto;

        ras[2] = totalVenta;
        ras[3] = totalPares;
        arr.add(ras);

        return arr;
    }
    
    public ArrayList<Object[]>   darGrandesTotalesFiltradoFechaUnica(String filtro, boolean selecionado,String fecha) {

        ArrayList<Object[]> arr = new ArrayList<Object[]>();

        ArrayList<Almacen> almas = new ArrayList<Almacen>();

        for (int j = 0; j < zapatos.size(); j++) {
            Zapato z = zapatos.get(j);

            Almacen ka = z.getAlamacenes().get(0);
            if (z.getCantidad() > 0 && z.getCategoria().equals(filtro) && z.esReposicion() == selecionado  && z.getStringFecha().equals(fecha)) {
                boolean flag = false;
                for (int k = 0; k < almas.size() && !flag; k++) {
                    Almacen papitas = almas.get(k);
                    if (papitas.getCiudad().equals(ka.getCiudad())) {

                        flag = true;
                        papitas.setTotalCosto(z.getPrecioCosto() * z.getCantidad());
                        ka.setTotalPares(z.getCantidad());
                        papitas.setTotalVenta(z.getPrecioVenta() * z.getCantidad());

                    }
                }
                if (!flag) {
                    ka.setTotalCosto(z.getPrecioCosto() * z.getCantidad());

                    ka.setTotalPares(z.getCantidad());

                    ka.setTotalVenta(z.getPrecioVenta() * z.getCantidad());

                    almas.add(ka);

                }
            }
        }
        Object[] res = new Object[4];
        int totalCosto = 0;
        int totalVenta = 0;
        int totalPares = 0;

        for (int i = 0; i < almas.size(); i++) {
            Almacen x = almas.get(i);

            res[0] = x.getCiudad();
            res[1] = x.getTotalCosto();

            res[2] = x.getTotalVenta();
            res[3] = x.getTotalPares();

            totalCosto += x.getTotalCosto();
            totalVenta += x.getTotalVenta();
            totalPares += x.getTotalPares();

            arr.add(res);
            x.volverCero();
            res = new Object[4];

        }
        Object[] ras = new Object[4];

        ras[0] = "TOTAL";
        ras[1] = totalCosto;

        ras[2] = totalVenta;
        ras[3] = totalPares;
        arr.add(ras);

        return arr;
    }
    
    
    public ArrayList<Object[]> darGrandesTotalesFiltradoDoble(String filtroA, String filtroB, boolean selecionado) {
        ArrayList<Object[]> arr = new ArrayList<Object[]>();

        Object[] res = new Object[4];
        res[0] = filtroA;
        res[1] = "";

        res[2] = "";
        res[3] = "";
        arr.add(res);
        ArrayList<Object[]> papitas = darGrandesTotalesFiltrado(filtroA, selecionado);
        for (int i = 0; i < papitas.size(); i++) {
            Object[] a = papitas.get(i);
            arr.add(a);

        }
        Object[] ras = new Object[4];

        ras[0] = filtroB;
        ras[1] = "";

        ras[2] = "";
        ras[3] = "";
        arr.add(ras);
        ArrayList<Object[]> doritos = darGrandesTotalesFiltrado(filtroB, selecionado);
        for (int i = 0; i < doritos.size(); i++) {
            Object[] a = doritos.get(i);
            arr.add(a);

        }
        Object[] ris = new Object[4];
        ris[0] = filtroA + " + " +filtroB;
        ris[1] = "";
        ris[2] = "";
        ris[3] = "";
        arr.add(ris);
        Object[] ros = new Object[4];
        int cantidad = (int)papitas.get(papitas.size()-1)[3] + (int)doritos.get(doritos.size()-1)[3];
        int totalPV = (int)papitas.get(papitas.size()-1)[2] + (int)doritos.get(doritos.size()-1)[2];
        int totalPC = (int)papitas.get(papitas.size()-1)[1] + (int)doritos.get(doritos.size()-1)[1];
        ros[0] = "TOTAL";
        ros[1] = totalPC;
        ros[2] = totalPV;
        ros[3] = cantidad;
        arr.add(ros);
              
        return arr;
    }
    
    public ArrayList<Object[]>  darGrandesTotalesFiltradoDobleDosFechas(String filtroA, String filtroB,boolean selecionado,Date fecha1, Date fecha2){
        ArrayList<Object[]> arr = new ArrayList<Object[]>();

        Object[] res = new Object[4];
        res[0] = filtroA;
        res[1] = "";

        res[2] = "";
        res[3] = "";
        arr.add(res);
        ArrayList<Object[]> papitas = darGrandesTotalesFiltradoFecha(filtroA, selecionado,fecha1,fecha2);
        for (int i = 0; i < papitas.size(); i++) {
            Object[] a = papitas.get(i);
            arr.add(a);

        }
        Object[] ras = new Object[4];

        ras[0] = filtroB;
        ras[1] = "";

        ras[2] = "";
        ras[3] = "";
        arr.add(ras);
        ArrayList<Object[]> doritos = darGrandesTotalesFiltradoFecha(filtroB, selecionado,fecha1,fecha2);
        for (int i = 0; i < doritos.size(); i++) {
            Object[] a = doritos.get(i);
            arr.add(a);

        }
        Object[] ris = new Object[4];
        ris[0] = filtroA + " + " +filtroB;
        ris[1] = "";
        ris[2] = "";
        ris[3] = "";
        arr.add(ris);
        Object[] ros = new Object[4];
        int cantidad = (int)papitas.get(papitas.size()-1)[3] + (int)doritos.get(doritos.size()-1)[3];
        int totalPV = (int)papitas.get(papitas.size()-1)[2] + (int)doritos.get(doritos.size()-1)[2];
        int totalPC = (int)papitas.get(papitas.size()-1)[1] + (int)doritos.get(doritos.size()-1)[1];
        ros[0] = "TOTAL";
        ros[1] = totalPC;
        ros[2] = totalPV;
        ros[3] = cantidad;
        arr.add(ros);
              
        return arr;
    }
    public ArrayList<Object[]>  darGrandesTotalesFiltradoDobleUnaFechas(String filtroA, String filtroB,boolean selecionado,String fecha1){
        ArrayList<Object[]> arr = new ArrayList<Object[]>();

        Object[] res = new Object[4];
        res[0] = filtroA;
        res[1] = "";

        res[2] = "";
        res[3] = "";
        arr.add(res);
        ArrayList<Object[]> papitas = darGrandesTotalesFiltradoFechaUnica(filtroA, selecionado,fecha1);
        for (int i = 0; i < papitas.size(); i++) {
            Object[] a = papitas.get(i);
            arr.add(a);

        }
        Object[] ras = new Object[4];

        ras[0] = filtroB;
        ras[1] = "";

        ras[2] = "";
        ras[3] = "";
        arr.add(ras);
        ArrayList<Object[]> doritos = darGrandesTotalesFiltradoFechaUnica(filtroB, selecionado,fecha1);
        for (int i = 0; i < doritos.size(); i++) {
            Object[] a = doritos.get(i);
            arr.add(a);

        }
        Object[] ris = new Object[4];
        ris[0] = filtroA + " + " +filtroB;
        ris[1] = "";
        ris[2] = "";
        ris[3] = "";
        arr.add(ris);
        Object[] ros = new Object[4];
        int cantidad = (int)papitas.get(papitas.size()-1)[3] + (int)doritos.get(doritos.size()-1)[3];
        int totalPV = (int)papitas.get(papitas.size()-1)[2] + (int)doritos.get(doritos.size()-1)[2];
        int totalPC = (int)papitas.get(papitas.size()-1)[1] + (int)doritos.get(doritos.size()-1)[1];
        ros[0] = "TOTAL";
        ros[1] = totalPC;
        ros[2] = totalPV;
        ros[3] = cantidad;
        arr.add(ros);
              
        return arr;
    }
    
    

    public ArrayList<Object[]> darGrandesTotalesFiltradoTriple(String filtroA, String filtroB, String filtroC, boolean selecionado) {
        ArrayList<Object[]> arr = new ArrayList<Object[]>();

        Object[] res = new Object[4];
        res[0] = filtroA;
        res[1] = "";

        res[2] = "";
        res[3] = "";
        arr.add(res);
        ArrayList<Object[]> papitas = darGrandesTotalesFiltrado(filtroA, selecionado);
        for (int i = 0; i < papitas.size(); i++) {
            Object[] a = papitas.get(i);
            arr.add(a);

        }
        Object[] ras = new Object[4];

        ras[0] = filtroB;
        ras[1] = "";

        ras[2] = "";
        ras[3] = "";
        arr.add(ras);
        ArrayList<Object[]> doritos = darGrandesTotalesFiltrado(filtroB, selecionado);
        for (int i = 0; i < doritos.size(); i++) {
            Object[] a = doritos.get(i);
            arr.add(a);

        }
        Object[] rus = new Object[4];

        rus[0] = filtroC;
        rus[1] = "";

        rus[2] = "";
        rus[3] = "";
        arr.add(rus);
        ArrayList<Object[]> tostacos = darGrandesTotalesFiltrado(filtroC, selecionado);
        for (int i = 0; i < tostacos.size(); i++) {
            Object[] a = tostacos.get(i);
            arr.add(a);

        }
        
        Object[] ris = new Object[4];

        ris[0] = "GASOLINA EXTRA";
        ris[1] = "";
        ris[2] = "";
        ris[3] = "";
        arr.add(ris);
        Object[] ros = new Object[4];

        int cantidad = (int)papitas.get(papitas.size()-1)[3] + (int)doritos.get(doritos.size()-1)[3] + (int)tostacos.get(tostacos.size()-1)[3] ;
        int totalPV = (int)papitas.get(papitas.size()-1)[2] + (int)doritos.get(doritos.size()-1)[2] +(int)tostacos.get(tostacos.size()-1)[2] ;
        int totalPC = (int)papitas.get(papitas.size()-1)[1] + (int)doritos.get(doritos.size()-1)[1] + (int)tostacos.get(tostacos.size()-1)[1] ;

        ros[0] = "TOTAL";
        ros[1] = totalPC;
        ros[2] = totalPV;
        ros[3] = cantidad;
        arr.add(ros);
        

        return arr;
    }

    
    public ArrayList<Object[]> darGrandesTotalesFiltradoTripleDosFechas(String filtroA, String filtroB, String filtroC, boolean selecionado,Date fecha1,Date fecha2) {
        ArrayList<Object[]> arr = new ArrayList<Object[]>();

        Object[] res = new Object[4];
        res[0] = filtroA;
        res[1] = "";

        res[2] = "";
        res[3] = "";
        arr.add(res);
        ArrayList<Object[]> papitas = darGrandesTotalesFiltradoFecha(filtroA, selecionado,fecha1,fecha2);
        for (int i = 0; i < papitas.size(); i++) {
            Object[] a = papitas.get(i);
            arr.add(a);

        }
        Object[] ras = new Object[4];

        ras[0] = filtroB;
        ras[1] = "";

        ras[2] = "";
        ras[3] = "";
        arr.add(ras);
        ArrayList<Object[]> doritos = darGrandesTotalesFiltradoFecha(filtroB, selecionado,fecha1,fecha2);
        for (int i = 0; i < doritos.size(); i++) {
            Object[] a = doritos.get(i);
            arr.add(a);

        }
        Object[] rus = new Object[4];

        rus[0] = filtroC;
        rus[1] = "";

        rus[2] = "";
        rus[3] = "";
        arr.add(rus);
        ArrayList<Object[]> tostacos = darGrandesTotalesFiltradoFecha(filtroC, selecionado,fecha1,fecha2);
        for (int i = 0; i < tostacos.size(); i++) {
            Object[] a = tostacos.get(i);
            arr.add(a);

        }
        
        Object[] ris = new Object[4];

        ris[0] = "GASOLINA EXTRA";
        ris[1] = "";
        ris[2] = "";
        ris[3] = "";
        arr.add(ris);
        Object[] ros = new Object[4];

        int cantidad = (int)papitas.get(papitas.size()-1)[3] + (int)doritos.get(doritos.size()-1)[3] + (int)tostacos.get(tostacos.size()-1)[3] ;
        int totalPV = (int)papitas.get(papitas.size()-1)[2] + (int)doritos.get(doritos.size()-1)[2] +(int)tostacos.get(tostacos.size()-1)[2] ;
        int totalPC = (int)papitas.get(papitas.size()-1)[1] + (int)doritos.get(doritos.size()-1)[1] + (int)tostacos.get(tostacos.size()-1)[1] ;

        ros[0] = "TOTAL";
        ros[1] = totalPC;
        ros[2] = totalPV;
        ros[3] = cantidad;
        arr.add(ros);
        

        return arr;
    }
    
    
    public ArrayList<Object[]>   darGrandesTotalesFiltradoTripleUnaFecha(String filtroA, String filtroB, String filtroC, boolean selecionado,String fecha1) {
        ArrayList<Object[]> arr = new ArrayList<Object[]>();

        Object[] res = new Object[4];
        res[0] = filtroA;
        res[1] = "";

        res[2] = "";
        res[3] = "";
        arr.add(res);
        ArrayList<Object[]> papitas = darGrandesTotalesFiltradoFechaUnica(filtroA, selecionado,fecha1);
        for (int i = 0; i < papitas.size(); i++) {
            Object[] a = papitas.get(i);
            arr.add(a);

        }
        Object[] ras = new Object[4];

        ras[0] = filtroB;
        ras[1] = "";

        ras[2] = "";
        ras[3] = "";
        arr.add(ras);
        ArrayList<Object[]> doritos = darGrandesTotalesFiltradoFechaUnica(filtroB, selecionado,fecha1);
        for (int i = 0; i < doritos.size(); i++) {
            Object[] a = doritos.get(i);
            arr.add(a);

        }
        Object[] rus = new Object[4];

        rus[0] = filtroC;
        rus[1] = "";

        rus[2] = "";
        rus[3] = "";
        arr.add(rus);
        ArrayList<Object[]> tostacos = darGrandesTotalesFiltradoFechaUnica(filtroC, selecionado,fecha1);
        for (int i = 0; i < tostacos.size(); i++) {
            Object[] a = tostacos.get(i);
            arr.add(a);

        }
        
        Object[] ris = new Object[4];

        ris[0] = "GASOLINA EXTRA";
        ris[1] = "";
        ris[2] = "";
        ris[3] = "";
        arr.add(ris);
        Object[] ros = new Object[4];

        int cantidad = (int)papitas.get(papitas.size()-1)[3] + (int)doritos.get(doritos.size()-1)[3] + (int)tostacos.get(tostacos.size()-1)[3] ;
        int totalPV = (int)papitas.get(papitas.size()-1)[2] + (int)doritos.get(doritos.size()-1)[2] +(int)tostacos.get(tostacos.size()-1)[2] ;
        int totalPC = (int)papitas.get(papitas.size()-1)[1] + (int)doritos.get(doritos.size()-1)[1] + (int)tostacos.get(tostacos.size()-1)[1] ;

        ros[0] = "TOTAL";
        ros[1] = totalPC;
        ros[2] = totalPV;
        ros[3] = cantidad;
        arr.add(ros);
        

        return arr;
    }
    
    
    public ArrayList<Object[]> setModelToHiperDuperTotal(boolean selecionado) {
        ArrayList<Object[]> arr = new ArrayList<Object[]>();

        ArrayList<Almacen> almas = new ArrayList<Almacen>();

        for (int j = 0; j < zapatos.size(); j++) {
            Zapato z = zapatos.get(j);

            Almacen ka = z.getAlamacenes().get(0);
            if (z.getCantidad() > 0 && z.esReposicion() == selecionado) {
                boolean flag = false;
                for (int k = 0; k < almas.size() && !flag; k++) {
                    Almacen papitas = almas.get(k);
                    if (papitas.getCiudad().equals(ka.getCiudad())) {

                        flag = true;
                        papitas.setTotalCosto(z.getPrecioCosto() * z.getCantidad());

                        ka.setTotalPares(z.getCantidad());

                        papitas.setTotalVenta(z.getPrecioVenta() * z.getCantidad());

                    }
                }
                if (!flag) {
                    ka.setTotalCosto(z.getPrecioCosto() * z.getCantidad());

                    ka.setTotalPares(z.getCantidad());

                    ka.setTotalVenta(z.getPrecioVenta() * z.getCantidad());

                    almas.add(ka);

                }
            }
        }
        Object[] res = new Object[4];

        int totalCosto = 0;
        int totalVenta = 0;
        int totalPares = 0;

        for (int i = 0; i < almas.size(); i++) {
            Almacen x = almas.get(i);

            totalCosto += x.getTotalCosto();
            totalVenta += x.getTotalVenta();
            totalPares += x.getTotalPares();
            x.volverCero();

        }
        res[0] = "GASOLINA EXTRA";
        res[1] = totalCosto;

        res[2] = totalVenta;
        res[3] = totalPares;


        arr.add(res);

        return arr;

    }
    public ArrayList<Object[]> setModelToHiperDuperTotalConFecha (boolean selecionado, String fecha1) {
        ArrayList<Object[]> arr = new ArrayList<Object[]>();

        ArrayList<Almacen> almas = new ArrayList<Almacen>();

        for (int j = 0; j < zapatos.size(); j++) {
            Zapato z = zapatos.get(j);

            Almacen ka = z.getAlamacenes().get(0);
            if (z.getCantidad() > 0 && z.esReposicion() == selecionado && z.getStringFecha().equals(fecha1)) {
                boolean flag = false;
                for (int k = 0; k < almas.size() && !flag; k++) {
                    Almacen papitas = almas.get(k);
                    if (papitas.getCiudad().equals(ka.getCiudad())) {

                        flag = true;
                        papitas.setTotalCosto(z.getPrecioCosto() * z.getCantidad());

                        ka.setTotalPares(z.getCantidad());

                        papitas.setTotalVenta(z.getPrecioVenta() * z.getCantidad());

                    }
                }
                if (!flag) {
                    ka.setTotalCosto(z.getPrecioCosto() * z.getCantidad());

                    ka.setTotalPares(z.getCantidad());

                    ka.setTotalVenta(z.getPrecioVenta() * z.getCantidad());

                    almas.add(ka);

                }
            }
        }
        Object[] res = new Object[4];

        int totalCosto = 0;
        int totalVenta = 0;
        int totalPares = 0;

        for (int i = 0; i < almas.size(); i++) {
            Almacen x = almas.get(i);

            totalCosto += x.getTotalCosto();
            totalVenta += x.getTotalVenta();
            totalPares += x.getTotalPares();
            x.volverCero();

        }
        res[0] = "GASOLINA EXTRA";
        res[1] = totalCosto;

        res[2] = totalVenta;
        res[3] = totalPares;
        arr.add(res);

        return arr;

    }
    
    public ArrayList<Object[]> setModelToHiperDuperTotalConFechas(boolean selecionado, Date fecha1, Date fecha2) {
        ArrayList<Object[]> arr = new ArrayList<Object[]>();

        ArrayList<Almacen> almas = new ArrayList<Almacen>();

        for (int j = 0; j < zapatos.size(); j++) {
            Zapato z = zapatos.get(j);

            Almacen ka = z.getAlamacenes().get(0);
            if (z.getCantidad() > 0 && z.esReposicion() == selecionado && z.getFecha().after(fecha1)&&z.getFecha().before(fecha2)) {
                boolean flag = false;
                for (int k = 0; k < almas.size() && !flag; k++) {
                    Almacen papitas = almas.get(k);
                    if (papitas.getCiudad().equals(ka.getCiudad())) {

                        flag = true;
                        papitas.setTotalCosto(z.getPrecioCosto() * z.getCantidad());

                        ka.setTotalPares(z.getCantidad());

                        papitas.setTotalVenta(z.getPrecioVenta() * z.getCantidad());

                    }
                }
                if (!flag) {
                    ka.setTotalCosto(z.getPrecioCosto() * z.getCantidad());

                    ka.setTotalPares(z.getCantidad());

                    ka.setTotalVenta(z.getPrecioVenta() * z.getCantidad());

                    almas.add(ka);

                }
            }
        }
        Object[] res = new Object[4];

        int totalCosto = 0;
        int totalVenta = 0;
        int totalPares = 0;

        for (int i = 0; i < almas.size(); i++) {
            Almacen x = almas.get(i);

            totalCosto += x.getTotalCosto();
            totalVenta += x.getTotalVenta();
            totalPares += x.getTotalPares();
            x.volverCero();

        }
        res[0] = "GASOLINA EXTRA";
        res[1] = totalCosto;

        res[2] = totalVenta;
        res[3] = totalPares;
        arr.add(res);

        return arr;

    }
  
    

    private ArrayList<Object[]> quickSort(ArrayList<Object[]> arr, int lowerIndex, int higherIndex) {
        int i = lowerIndex;
        int j = higherIndex;
        Object[] pivot = arr.get(lowerIndex + (higherIndex - lowerIndex) / 2);

        while (i <= j) {

            while (compararCategoria(arr.get(i), pivot) <= -1) {
                i++;
            }

            while (compararCategoria(arr.get(j), pivot) >= 1) {
                j--;
            }

            if (i <= j) {
                //intercambia i y j dentro de arr
                Object[] swap = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, swap);

                //mueve indice a la siguiente posicion en ambos lados
                i++;
                j--;
            }

        }

        if (lowerIndex < j) {
            arr = quickSort(arr, lowerIndex, j);
        }

        if (i < higherIndex) {
            arr = quickSort(arr, i, higherIndex);
        }

        return arr;
    }

    /**
     * el orden deseado es DAMA, luego, CABALLERO, luego INFANTIL
     *
     * @param a
     * @param b
     * @return
     */
    private int compararCategoria(Object[] a, Object[] b) {
        if (a[11].equals("X")) { //a es DAMA

            if (b[11].equals("X")) { //b es DAMA

    		    Date fecha1 = null;
    		    Date fecha2 = null ;
    			try {
    				fecha1 = new SimpleDateFormat(FORMATO_FECHA, new Locale("es", "ES")).parse((String) a[15]);
    				fecha2  =	new SimpleDateFormat(FORMATO_FECHA, new Locale("es", "ES")).parse((String) b[15]);

    			} catch (ParseException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			
    		        int compFechas= fecha1.compareTo(fecha2); // use your logic
    		   if(compFechas==0) 
    		   {
            	
            	
                int retorno = a[0].toString().compareTo(b[0].toString());
                if (retorno == 0) {
                    int alpha = Integer.parseInt(a[1].toString().split(" -")[0]);
                    int betha = Integer.parseInt(b[1].toString().split(" -")[0]);
                    if (alpha > betha) {
                        return 1;
                    } else if (alpha < betha) {
                        return -1;
                    } else {
                        return a[2].toString().compareTo(b[2].toString());

                    }
                } else {
                    return retorno;
                }
    		   }
    		   else
    		   {
    			   return compFechas;
    		   }

            } else if (b[12].equals("X")) { //b es CABALLERO
                return -1;
            } else if (b[13].equals("X")) { //b es INFANTIL
                return -1;
            }

        } else if (a[12].equals("X")) { //a es CABALLERO

            if (b[11].equals("X")) { //b es DAMA
                return 1;
            } else if (b[12].equals("X")) { //b es CABALLERO
                
            	
        	    Date fecha1 = null;
    		    Date fecha2 = null ;
    			try {
    				fecha1 = new SimpleDateFormat(FORMATO_FECHA, new Locale("es", "ES")).parse((String) a[15]);
    				fecha2  =	new SimpleDateFormat(FORMATO_FECHA, new Locale("es", "ES")).parse((String) b[15]);

    			} catch (ParseException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			
    		        int compFechas= fecha1.compareTo(fecha2); // use your logic
    		   if(compFechas==0) 
    		   {
            	int retorno = a[0].toString().compareTo(b[0].toString());
                if (retorno == 0) {
                    int alpha = Integer.parseInt(a[1].toString().split(" -")[0]);
                    int betha = Integer.parseInt(b[1].toString().split(" -")[0]);


                    if (alpha > betha) {
                        return 1;
                    } else if (alpha < betha) {
                        return -1;
                    } else {
                        return a[2].toString().compareTo(b[2].toString());

                    }
                } else {
                    return retorno;
                }
    		   }
    		   else
    		   {
    			   return compFechas;
    		   }
            } else if (b[13].equals("X")) { //b es INFANTIL
                return -1;
            }

        } else if (a[13].equals("X")) { //a es INFANTIL

            if (b[11].equals("X")) { //b es DAMA
                return 1;
            } else if (b[12].equals("X")) { //b es CABALLERO
                return 1;
            } else if (b[13].equals("X")) { //b es INFANTIL
            	
            	
            	   Date fecha1 = null;
       		    Date fecha2 = null ;
       			try {
       				fecha1 = new SimpleDateFormat(FORMATO_FECHA, new Locale("es", "ES")).parse((String) a[15]);
       				fecha2  =	new SimpleDateFormat(FORMATO_FECHA, new Locale("es", "ES")).parse((String) b[15]);

       			} catch (ParseException e) {
       				// TODO Auto-generated catch block
       				e.printStackTrace();
       			}
       			
       		        int compFechas= fecha1.compareTo(fecha2); // use your logic
       		   if(compFechas==0) 
       		   {
            	
                int retorno = a[0].toString().compareTo(b[0].toString());
                if (retorno == 0) {
                    int alpha = Integer.parseInt(a[1].toString().split(" -")[0]);
                    int betha = Integer.parseInt(b[1].toString().split(" -")[0]);
                   if (alpha > betha) {
                        return 1;
                    } else if (alpha < betha) {
                        return -1;
                    } else {
                        return a[2].toString().compareTo(b[2].toString());

                    }
                } else {
                    return retorno;
                }
    		   }
    		   else
    		   {
    			   return compFechas;
    		   }
            }

        }
        return Integer.MAX_VALUE;
    }

    public ArrayList<Object[]> darGrandiososTotalesCasoRaro(String prefix, boolean es) {

        fechas = new ArrayList<String>();
        fechasRepo= new ArrayList<String>();
        ArrayList<Object[]> arr = new ArrayList<Object[]>();
        for (int i = 0; i < almacenes.size(); i++) {
            for (int j = 0; j < zapatos.size(); j++) {
                Object[] res = new Object[16];
                Almacen a = almacenes.get(i);
                Zapato z = zapatos.get(j);
                int sumaCantidad = 0;
                int sumaPrecioCosto = 0;
                int sumaPrecioVenta = 0;
                Almacen ka = z.getAlamacenes().get(0);
                if (ka.toString().equals(a.toString()) && ka.toString().startsWith(prefix) && z.esReposicion() == es) {
                    sumaCantidad += z.getCantidad();
                    sumaPrecioCosto += z.getPrecioCosto() * z.getCantidad();
                    sumaPrecioVenta += z.getPrecioVenta() * z.getCantidad();
                    if(es )
                    {
                        addDateToARRREPO(z.getStringFecha(z.getFecha()));

                    }
                    else
                    {
                    addDateToARRNORMAL(z.getStringFecha(z.getFecha()));
                    }
                    
                    
                }
              res[0] = a.toString();
                res[1] = z.getProveedores().get(0).toString();
                res[2] = z.getReferencia();
                res[3] = z.getColor();
                res[4] = z.getPlanta();
                res[5] = z.getAltura();
                res[6] = z.getPrecioCosto();
                res[7] = z.getPrecioVenta();
                res[8] = sumaCantidad;
                res[9] = sumaPrecioCosto;
                res[10] = sumaPrecioVenta;
                res[15] = z.getStringFecha(z.getFecha());  
                if (z.getCategoria().equals("CABALLERO")) {
                    res[12] = "X";
                    res[11] = " ";
                    res[13] = " ";
                } else if (z.getCategoria().equals("DAMA")) {
                    res[11] = "X";
                    res[12] = " ";
                    res[13] = " ";
                } else if (z.getCategoria().equals("INFANTIL")) {
                    res[13] = "X";
                    res[11] = " ";
                    res[12] = " ";

                }
                String numeracion = z.getNumeracion();
                if(numeracion.equals("0"))
                {
                    res[14] = "";

                }
                else
                {
                res[14] = numeracion;
                }

                if ((int) res[8] > 0) {

                    arr.add(res);
                }
            }
        }
        if (arr.size() == 0) {

            return arr;

        } else {
            Object[] linea = new Object[16];
            linea[0] = "TOTALES";
            linea[1] = " ";
            linea[2] = " ";
            linea[3] = " ";
            linea[4] = " ";
            linea[9] = " ";
            linea[8] = " ";
            linea[10] = " ";
            linea[5] = " ";
            linea[6] = " ";
            linea[7] = " ";
            linea[11] = " ";
            linea[12] = " ";
            linea[13] = " ";
            linea[14] = " ";
            linea[15] = " ";

            Object[] fabulosoTotal = new Object[16];
            fabulosoTotal[0] = " ";
            fabulosoTotal[1] = " ";
            fabulosoTotal[2] = " ";
            fabulosoTotal[3] = " ";
            fabulosoTotal[4] = " ";
            fabulosoTotal[5] = " ";

            fabulosoTotal[9] = " ";
            fabulosoTotal[8] = " ";
            fabulosoTotal[10] = " ";
                        fabulosoTotal[11] = " ";
                        fabulosoTotal[12] = " ";
                        fabulosoTotal[13] = " ";
                        fabulosoTotal[14] = " ";
                        fabulosoTotal[15] = " ";

            int sumaCantidad = 0;
            int sumaPrecioCosto = 0;
            int sumaPrecioVenta = 0;

            for (int i = 0; i < arr.size(); i++) {
                Object[] res = arr.get(i);

                sumaCantidad += (int) res[8];
                sumaPrecioCosto += (int) res[9];
                sumaPrecioVenta += (int) res[10];

            }
            fabulosoTotal[8] = sumaCantidad;
            fabulosoTotal[9] = sumaPrecioCosto;
            fabulosoTotal[10] = sumaPrecioVenta;

            ArrayList<Object[]> retorno = quickSort(arr, 0, (arr.size()) - 1);
            retorno.add(linea);
            retorno.add(fabulosoTotal);

            return retorno;
        }

    }

    private List<Zapato> quickSortZapatos(List<Zapato> zapatos, int lowerIndex, int higherIndex) {
        int i = lowerIndex;
        int j = higherIndex;
        Zapato pivot = zapatos.get(lowerIndex + (higherIndex - lowerIndex) / 2);

        while (i <= j) {

            while (compararZapato(zapatos.get(i), pivot) < 0) {
                i++;
            }

            while (compararZapato(zapatos.get(j), pivot) > 0) {
                j--;
            }

            if (i <= j) {
                //intercambia i y j dentro de arr
                Zapato swap = zapatos.get(i);
                zapatos.set(i, zapatos.get(j));
                zapatos.set(j, swap);

                //mueve indice a la siguiente posicion en ambos lados
                i++;
                j--;
            }

        }

        if (lowerIndex < j) {
            zapatos = quickSortZapatos(zapatos, lowerIndex, j);
        }

        if (i < higherIndex) {
            zapatos = quickSortZapatos(zapatos, i, higherIndex);
        }

        return zapatos;
    }

    private int compararZapato(Zapato a, Zapato b) {

        int retorno = a.getAlmacenesString().compareTo(b.getAlmacenesString());
        if (retorno == 0) {
            if (a.getProveedoresNumber() > b.getProveedoresNumber()) {
                return 1;
            } else if (a.getProveedoresNumber() < b.getProveedoresNumber()) {
                return -1;
            } else {

                return a.getReferencia().compareTo(b.getReferencia());

            }

        } else {
            return retorno;
        }

    }

    public String eliminarReposicion(String referencia, String codigoProveedor, String codigoAlmacen, String color) {
        for (int i = 0; i < zapatos.size(); i++) {
            Zapato x = zapatos.get(i);
            if (x.esReposicion()) {
                if (x.getReferencia().equals(referencia) && x.getColor().equalsIgnoreCase(color)) {
                    for (int j = 0; j < x.getProveedores().size(); j++) {
                        Proveedor y = x.getProveedores().get(j);
                        if (y.getCodigo() == Integer.parseInt(codigoProveedor)) {

                            for (int k = 0; k < x.getAlamacenes().size(); k++) {
                                Almacen alm = x.getAlamacenes().get(k);

                                if (alm.getCiudad().equals(codigoAlmacen)) {
                                    zapatos.remove(i);
                                    return "Se ha eliminado exitosamente la reposicion de referencia: " + referencia
                                            + ", color "+color+" y proveedor relacionado con código: " + codigoProveedor;
                                }
                            }

                        }
                    }
                }
            }
        }
        return "No se ha encontrado la reposición especificada para eliminar.";
    }

    public ArrayList<Object[]> darTotalesResposicion() {
    
                     ArrayList<Object[]> arr = new ArrayList<Object[]>();
        for (int i = 0; i < almacenes.size(); i++) {
            for (int j = 0; j < zapatos.size(); j++) {
                Object[] res = new Object[14];
                Almacen a = almacenes.get(i);
                Zapato z = zapatos.get(j);
                int sumaCantidad = 0;
                int sumaPrecioCosto = 0;
                int sumaPrecioVenta = 0;
                Almacen ka = z.getAlamacenes().get(0);
                if (ka.toString().equals(a.toString()) && z.esReposicion()) {
                    sumaCantidad += z.getCantidad();
                    sumaPrecioCosto += z.getPrecioCosto() * z.getCantidad();
                    sumaPrecioVenta += z.getPrecioVenta() * z.getCantidad();
                }
                res[0] = a.toString();
                res[1] = z.getProveedores().get(0).toString();
                res[2] = z.getReferencia();
                res[3] = z.getColor();
                res[4] = z.getPlanta();
                res[5] = z.getAltura();
                res[6] = z.getPrecioCosto();
                res[7] = z.getPrecioVenta();
                res[8] = sumaCantidad;
                res[9] = sumaPrecioCosto;
                res[10] = sumaPrecioVenta;
                res[15] = z.getStringFecha(z.getFecha());  
                if (z.getCategoria().equals("CABALLERO")) {
                    res[12] = "X";
                    res[11] = " ";
                    res[13] = " ";
                } else if (z.getCategoria().equals("DAMA")) {
                    res[11] = "X";
                    res[12] = " ";
                    res[13] = " ";
                } else if (z.getCategoria().equals("INFANTIL")) {
                    res[13] = "X";
                    res[11] = " ";
                    res[12] = " ";

                }
                String numeracion = z.getNumeracion();
                if(numeracion.equals("0"))
                {
                    res[14] = "";

                }
                else
                {
                res[14] = numeracion;
                }

                if ((int) res[8] > 0) {

                    arr.add(res);
                }
            }
        }
        return quickSort(arr, 0, (arr.size()) - 1);

        }

	public void actualizarAlmacenesEnZapatosYReferencias() {
		for(Zapato z: zapatos){
			List<Almacen> almacenesz = z.getAlamacenes();
			for(int i = 0; i<almacenesz.size(); i++){
				Almacen az = almacenesz.get(i);
				for(Almacen a:almacenes){
					if(az.getCiudad().equalsIgnoreCase(a.getCiudad())){
						almacenesz.set(i, a);
					}
				}
			}
			z.setAlmacenes(almacenesz);
		}	
	}

	public void actualizarProveedoresEnZapatosYReferencias() {
		for(Zapato z: zapatos){
			List<Proveedor> proveedoresz = z.getProveedores();
			for(int i = 0; i<proveedoresz.size(); i++){
				Proveedor pz = proveedoresz.get(i);
				for(Proveedor p:proveedores){
					if(pz.getCodigo() == p.getCodigo()){
						proveedoresz.set(i, p);
					}
				}
			}
			z.setProveedores(proveedoresz);
		}
	}
	public List<String> getFechas()
	{
		Comparator<String> comparator = new Comparator<String>() {
		    @Override
		    public int compare(String left, String right) {
		    Date a = null;
		    Date b = null ;
			try {
				a = new SimpleDateFormat(FORMATO_FECHA, new Locale("es", "ES")).parse(left);
			     b  =	new SimpleDateFormat(FORMATO_FECHA, new Locale("es", "ES")).parse(right);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		        return a.compareTo(b); // use your logic
		    }
		};
	
		Collections.sort(fechas,comparator);
		
		
		return fechas;
		
	}
	public List<String> getFechasRepo()
	{
		
		Comparator<String> comparator = new Comparator<String>() {
		    @Override
		    public int compare(String left, String right) {
		    Date a = null;
		    Date b = null ;
			try {
				a = new SimpleDateFormat(FORMATO_FECHA, new Locale("es", "ES")).parse(left);
			     b  =	new SimpleDateFormat(FORMATO_FECHA, new Locale("es", "ES")).parse(right);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		        return a.compareTo(b); // use your logic
		    }
		};

		
		Collections.sort(fechasRepo,comparator);
		
		
		return fechasRepo;
		
	}
	public ArrayList<Object[]> darGrandiososTotalesCasoRaroConFecha(Object item, String prefix, boolean es)
	{

        fechas = new ArrayList<String>();
        fechasRepo= new ArrayList<String>();
        ArrayList<Object[]> arr = new ArrayList<Object[]>();
        for (int i = 0; i < almacenes.size(); i++) {
            for (int j = 0; j < zapatos.size(); j++) {
                Object[] res = new Object[16];
                Almacen a = almacenes.get(i);
                Zapato z = zapatos.get(j);
                int sumaCantidad = 0;
                int sumaPrecioCosto = 0;
                int sumaPrecioVenta = 0;
                Almacen ka = z.getAlamacenes().get(0);
                if (ka.toString().equals(a.toString()) && ka.toString().startsWith(prefix) && z.esReposicion() == es && z.getStringFecha(z.getFecha()).equals(item)) {
                    sumaCantidad += z.getCantidad();
                    sumaPrecioCosto += z.getPrecioCosto() * z.getCantidad();
                    sumaPrecioVenta += z.getPrecioVenta() * z.getCantidad();

                }
               res[0] = a.toString();
                res[1] = z.getProveedores().get(0).toString();
                res[2] = z.getReferencia();
                res[3] = z.getColor();
                res[4] = z.getPlanta();
                res[5] = z.getAltura();
                res[6] = z.getPrecioCosto();
                res[7] = z.getPrecioVenta();
                res[8] = sumaCantidad;
                res[9] = sumaPrecioCosto;
                res[10] = sumaPrecioVenta;
                res[15] = z.getStringFecha(z.getFecha());  
                if (z.getCategoria().equals("CABALLERO")) {
                    res[12] = "X";
                    res[11] = " ";
                    res[13] = " ";
                } else if (z.getCategoria().equals("DAMA")) {
                    res[11] = "X";
                    res[12] = " ";
                    res[13] = " ";
                } else if (z.getCategoria().equals("INFANTIL")) {
                    res[13] = "X";
                    res[11] = " ";
                    res[12] = " ";

                }
                String numeracion = z.getNumeracion();
                if(numeracion.equals("0"))
                {
                    res[14] = "";

                }
                else
                {
                res[14] = numeracion;
                }

                if ((int) res[8] > 0) {

                    arr.add(res);
                }
            }
        }
        if (arr.size() == 0) {

            return arr;

        } else {
            Object[] linea = new Object[16];
            linea[0] = "TOTALES";
            linea[1] = " ";
            linea[2] = " ";
            linea[3] = " ";
            linea[4] = " ";
            linea[9] = " ";
            linea[8] = " ";
            linea[10] = " ";
            linea[5] = " ";
            linea[6] = " ";
            linea[7] = " ";
            linea[11] = " ";
            linea[12] = " ";
            linea[13] = " ";
            linea[14] = " ";
            linea[15] = " ";

            Object[] fabulosoTotal = new Object[16];
            fabulosoTotal[0] = " ";
            fabulosoTotal[1] = " ";
            fabulosoTotal[2] = " ";
            fabulosoTotal[3] = " ";
            fabulosoTotal[4] = " ";
            fabulosoTotal[5] = " ";

            fabulosoTotal[9] = " ";
            fabulosoTotal[8] = " ";
            fabulosoTotal[10] = " ";
                        fabulosoTotal[11] = " ";
                        fabulosoTotal[12] = " ";
                        fabulosoTotal[13] = " ";
                        fabulosoTotal[14] = " ";
                        fabulosoTotal[15] = " ";

            int sumaCantidad = 0;
            int sumaPrecioCosto = 0;
            int sumaPrecioVenta = 0;

            for (int i = 0; i < arr.size(); i++) {
                Object[] res = arr.get(i);

                sumaCantidad += (int) res[8];
                sumaPrecioCosto += (int) res[9];
                sumaPrecioVenta += (int) res[10];

            }
            fabulosoTotal[8] = sumaCantidad;
            fabulosoTotal[9] = sumaPrecioCosto;
            fabulosoTotal[10] = sumaPrecioVenta;

            ArrayList<Object[]> retorno = quickSort(arr, 0, (arr.size()) - 1);
            retorno.add(linea);
            retorno.add(fabulosoTotal);

            return retorno;
	}

	}
	public ArrayList<Object[]> darTotalesFabulososConFecha(Date fecha,  boolean es)
	{

        fechas = new ArrayList<String>();
        fechasRepo= new ArrayList<String>();
        ArrayList<Object[]> arr = new ArrayList<Object[]>();
        for (int i = 0; i < almacenes.size(); i++) {
            for (int j = 0; j < zapatos.size(); j++) {
                Object[] res = new Object[16];
                Almacen a = almacenes.get(i);
                Zapato z = zapatos.get(j);
                int sumaCantidad = 0;
                int sumaPrecioCosto = 0;
                int sumaPrecioVenta = 0;
                Almacen ka = z.getAlamacenes().get(0);
                if (ka.toString().equals(a.toString())  && z.esReposicion() == es && z.getFecha().equals(fecha)) {
                    sumaCantidad += z.getCantidad();
                    sumaPrecioCosto += z.getPrecioCosto() * z.getCantidad();
                    sumaPrecioVenta += z.getPrecioVenta() * z.getCantidad();

                }
               res[0] = a.toString();
                res[1] = z.getProveedores().get(0).toString();
                res[2] = z.getReferencia();
                res[3] = z.getColor();
                res[4] = z.getPlanta();
                res[5] = z.getAltura();
                res[6] = z.getPrecioCosto();
                res[7] = z.getPrecioVenta();
                res[8] = sumaCantidad;
                res[9] = sumaPrecioCosto;
                res[10] = sumaPrecioVenta;
                res[15] = z.getStringFecha(z.getFecha());  
                if (z.getCategoria().equals("CABALLERO")) {
                    res[12] = "X";
                    res[11] = " ";
                    res[13] = " ";
                } else if (z.getCategoria().equals("DAMA")) {
                    res[11] = "X";
                    res[12] = " ";
                    res[13] = " ";
                } else if (z.getCategoria().equals("INFANTIL")) {
                    res[13] = "X";
                    res[11] = " ";
                    res[12] = " ";

                }
                String numeracion = z.getNumeracion();
                if(numeracion.equals("0"))
                {
                    res[14] = "";

                }
                else
                {
                res[14] = numeracion;
                }

                if ((int) res[8] > 0) {

                    arr.add(res);
                }
            }
        }
        if (arr.size() == 0) {

            return arr;

        } else {
            Object[] linea = new Object[16];
            linea[0] = "TOTALES";
            linea[1] = " ";
            linea[2] = " ";
            linea[3] = " ";
            linea[4] = " ";
            linea[9] = " ";
            linea[8] = " ";
            linea[10] = " ";
            linea[5] = " ";
            linea[6] = " ";
            linea[7] = " ";
            linea[11] = " ";
            linea[12] = " ";
            linea[13] = " ";
            linea[14] = " ";
            linea[15] = " ";

            Object[] fabulosoTotal = new Object[16];
            fabulosoTotal[0] = " ";
            fabulosoTotal[1] = " ";
            fabulosoTotal[2] = " ";
            fabulosoTotal[3] = " ";
            fabulosoTotal[4] = " ";
            fabulosoTotal[5] = " ";

            fabulosoTotal[9] = " ";
            fabulosoTotal[8] = " ";
            fabulosoTotal[10] = " ";
                        fabulosoTotal[11] = " ";
                        fabulosoTotal[12] = " ";
                        fabulosoTotal[13] = " ";
                        fabulosoTotal[14] = " ";
                        fabulosoTotal[15] = " ";

            int sumaCantidad = 0;
            int sumaPrecioCosto = 0;
            int sumaPrecioVenta = 0;

            for (int i = 0; i < arr.size(); i++) {
                Object[] res = arr.get(i);

                sumaCantidad += (int) res[8];
                sumaPrecioCosto += (int) res[9];
                sumaPrecioVenta += (int) res[10];

            }
            fabulosoTotal[8] = sumaCantidad;
            fabulosoTotal[9] = sumaPrecioCosto;
            fabulosoTotal[10] = sumaPrecioVenta;

            ArrayList<Object[]> retorno = quickSort(arr, 0, (arr.size()) - 1);
            retorno.add(linea);
            retorno.add(fabulosoTotal);

            return retorno;
	}
	}
	
	public ArrayList<Object[]>	darGrandiososTotalesCasoRaroConDosFecha(Date fecha, Date fecha1, String prefix,  boolean es)
	{

        fechas = new ArrayList<String>();
        fechasRepo= new ArrayList<String>();
        ArrayList<Object[]> arr = new ArrayList<Object[]>();
        for (int i = 0; i < almacenes.size(); i++) {
            for (int j = 0; j < zapatos.size(); j++) {
                Object[] res = new Object[16];
                Almacen a = almacenes.get(i);
                Zapato z = zapatos.get(j);
                int sumaCantidad = 0;
                int sumaPrecioCosto = 0;
                int sumaPrecioVenta = 0;
                Almacen ka = z.getAlamacenes().get(0);
                if (ka.toString().equals(a.toString()) && ka.toString().startsWith(prefix) && z.esReposicion() == es && z.getFecha().before(fecha1)&& z.getFecha().after(fecha)) {
                    sumaCantidad += z.getCantidad();
                    sumaPrecioCosto += z.getPrecioCosto() * z.getCantidad();
                    sumaPrecioVenta += z.getPrecioVenta() * z.getCantidad();

                }
              res[0] = a.toString();
                res[1] = z.getProveedores().get(0).toString();
                res[2] = z.getReferencia();
                res[3] = z.getColor();
                res[4] = z.getPlanta();
                res[5] = z.getAltura();
                res[6] = z.getPrecioCosto();
                res[7] = z.getPrecioVenta();
                res[8] = sumaCantidad;
                res[9] = sumaPrecioCosto;
                res[10] = sumaPrecioVenta;
                res[15] = z.getStringFecha(z.getFecha());  
                if (z.getCategoria().equals("CABALLERO")) {
                    res[12] = "X";
                    res[11] = " ";
                    res[13] = " ";
                } else if (z.getCategoria().equals("DAMA")) {
                    res[11] = "X";
                    res[12] = " ";
                    res[13] = " ";
                } else if (z.getCategoria().equals("INFANTIL")) {
                    res[13] = "X";
                    res[11] = " ";
                    res[12] = " ";

                }
                String numeracion = z.getNumeracion();
                if(numeracion.equals("0"))
                {
                    res[14] = "";

                }
                else
                {
                res[14] = numeracion;
                }

                if ((int) res[8] > 0) {

                    arr.add(res);
                }
            }
        }
        if (arr.size() == 0) {

            return arr;

        } else {
            Object[] linea = new Object[16];
            linea[0] = "TOTALES";
            linea[1] = " ";
            linea[2] = " ";
            linea[3] = " ";
            linea[4] = " ";
            linea[9] = " ";
            linea[8] = " ";
            linea[10] = " ";
            linea[5] = " ";
            linea[6] = " ";
            linea[7] = " ";
            linea[11] = " ";
            linea[12] = " ";
            linea[13] = " ";
            linea[14] = " ";
            linea[15] = " ";

            Object[] fabulosoTotal = new Object[16];
            fabulosoTotal[0] = " ";
            fabulosoTotal[1] = " ";
            fabulosoTotal[2] = " ";
            fabulosoTotal[3] = " ";
            fabulosoTotal[4] = " ";
            fabulosoTotal[5] = " ";

            fabulosoTotal[9] = " ";
            fabulosoTotal[8] = " ";
            fabulosoTotal[10] = " ";
                        fabulosoTotal[11] = " ";
                        fabulosoTotal[12] = " ";
                        fabulosoTotal[13] = " ";
                        fabulosoTotal[14] = " ";
                        fabulosoTotal[15] = " ";

            int sumaCantidad = 0;
            int sumaPrecioCosto = 0;
            int sumaPrecioVenta = 0;

            for (int i = 0; i < arr.size(); i++) {
                Object[] res = arr.get(i);

                sumaCantidad += (int) res[8];
                sumaPrecioCosto += (int) res[9];
                sumaPrecioVenta += (int) res[10];

            }
            fabulosoTotal[8] = sumaCantidad;
            fabulosoTotal[9] = sumaPrecioCosto;
            fabulosoTotal[10] = sumaPrecioVenta;

            ArrayList<Object[]> retorno = quickSort(arr, 0, (arr.size()) - 1);
            retorno.add(linea);
            retorno.add(fabulosoTotal);

            return retorno;
	}
	}
	
	
	public ArrayList<Object[]>	darGrandiososTotalesConDosFecha(Date fecha, Date fecha1,   boolean es)
	{

        fechas = new ArrayList<String>();
        fechasRepo= new ArrayList<String>();
        ArrayList<Object[]> arr = new ArrayList<Object[]>();
        for (int i = 0; i < almacenes.size(); i++) {
            for (int j = 0; j < zapatos.size(); j++) {
                Object[] res = new Object[16];
                Almacen a = almacenes.get(i);
                Zapato z = zapatos.get(j);
                int sumaCantidad = 0;
                int sumaPrecioCosto = 0;
                int sumaPrecioVenta = 0;
                Almacen ka = z.getAlamacenes().get(0);
                if (ka.toString().equals(a.toString())  && z.esReposicion() == es && z.getFecha().before(fecha1)&& z.getFecha().after(fecha)) {
                    sumaCantidad += z.getCantidad();
                    sumaPrecioCosto += z.getPrecioCosto() * z.getCantidad();
                    sumaPrecioVenta += z.getPrecioVenta() * z.getCantidad();

                }
                res[0] = a.toString();
                res[1] = z.getProveedores().get(0).toString();
                res[2] = z.getReferencia();
                res[3] = z.getColor();
                res[4] = z.getPlanta();
                res[5] = z.getAltura();
                res[6] = z.getPrecioCosto();
                res[7] = z.getPrecioVenta();
                res[8] = sumaCantidad;
                res[9] = sumaPrecioCosto;
                res[10] = sumaPrecioVenta;
                res[15] = z.getStringFecha(z.getFecha());  
                if (z.getCategoria().equals("CABALLERO")) {
                    res[12] = "X";
                    res[11] = " ";
                    res[13] = " ";
                } else if (z.getCategoria().equals("DAMA")) {
                    res[11] = "X";
                    res[12] = " ";
                    res[13] = " ";
                } else if (z.getCategoria().equals("INFANTIL")) {
                    res[13] = "X";
                    res[11] = " ";
                    res[12] = " ";

                }
                String numeracion = z.getNumeracion();
                if(numeracion.equals("0"))
                {
                    res[14] = "";

                }
                else
                {
                res[14] = numeracion;
                }

                if ((int) res[8] > 0) {

                    arr.add(res);
                }
            }
        }
        if (arr.size() == 0) {

            return arr;

        } else {
            Object[] linea = new Object[16];
            linea[0] = "TOTALES";
            linea[1] = " ";
            linea[2] = " ";
            linea[3] = " ";
            linea[4] = " ";
            linea[9] = " ";
            linea[8] = " ";
            linea[10] = " ";
            linea[5] = " ";
            linea[6] = " ";
            linea[7] = " ";
            linea[11] = " ";
            linea[12] = " ";
            linea[13] = " ";
            linea[14] = " ";
            linea[15] = " ";

            Object[] fabulosoTotal = new Object[16];
            fabulosoTotal[0] = " ";
            fabulosoTotal[1] = " ";
            fabulosoTotal[2] = " ";
            fabulosoTotal[3] = " ";
            fabulosoTotal[4] = " ";
            fabulosoTotal[5] = " ";

            fabulosoTotal[9] = " ";
            fabulosoTotal[8] = " ";
            fabulosoTotal[10] = " ";
                        fabulosoTotal[11] = " ";
                        fabulosoTotal[12] = " ";
                        fabulosoTotal[13] = " ";
                        fabulosoTotal[14] = " ";
                        fabulosoTotal[15] = " ";

            int sumaCantidad = 0;
            int sumaPrecioCosto = 0;
            int sumaPrecioVenta = 0;

            for (int i = 0; i < arr.size(); i++) {
                Object[] res = arr.get(i);

                sumaCantidad += (int) res[8];
                sumaPrecioCosto += (int) res[9];
                sumaPrecioVenta += (int) res[10];

            }
            fabulosoTotal[8] = sumaCantidad;
            fabulosoTotal[9] = sumaPrecioCosto;
            fabulosoTotal[10] = sumaPrecioVenta;

            ArrayList<Object[]> retorno = quickSort(arr, 0, (arr.size()) - 1);
            retorno.add(linea);
            retorno.add(fabulosoTotal);

            return retorno;
	}
	}
	
	
	
	
	
	}
