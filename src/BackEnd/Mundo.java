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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.ComboBoxModel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Mundo
{

	private List<Almacen> almacenes;
	private List<Proveedor> proveedores;
	private List<Zapato> zapatos;






	public Mundo() 
	{

		almacenes = new ArrayList<Almacen>();

		proveedores = new ArrayList<Proveedor> ();
		zapatos = new ArrayList<Zapato> ();
		
		//primero se cargan los proveedores y almacenes, luego los Zapatos.
		//para que no apunten a proveedores/almacenes no cargados aun
		try
		{
			cargarProveedores();


		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try
		{
			cargarAlmacenes();


		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		

		try
		{
			cargarZapatos();


		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


	}


	public String agregarZapato(Zapato pZapato)
	{
		zapatos.add(pZapato);
		guardar();



		return "Se ha agregado exitosamente el zapato de Referencia: \"" + pZapato.getReferencia() + "\" y Almacén: \"" + pZapato.getAlmacenesString() + "\"";


	}

	public String agregarProovedores(Proveedor pProveedor)
	{
		proveedores.add(pProveedor);
		guardar();


		return "Se ha agregado exitosamente el proveedor " + pProveedor.getNombre();


	}

	public String agregarAlmacen(Almacen pAlmacen)
	{
		almacenes.add(pAlmacen);
		guardar();


		return "Se ha agregado exitosamente el almancen " + pAlmacen.getAlmacen() ;

	}
	public void cargarZapatos() throws Exception
	{
		String cadena;
		FileReader f = new FileReader("zapatos.txt");
		BufferedReader b = new BufferedReader(f);
		while((cadena = b.readLine())!=null)
		{

			String [] arr = cadena.split("\\}");


			Zapato zap = new Zapato(arr[0], arr[1], arr[2], arr[3], arr[4], Integer.parseInt(arr[5]), Integer.parseInt(arr[6]), Integer.parseInt(arr[7]), arr[8],Integer.parseInt(arr[9]), Zapato.getFechaFromString(arr[10]));
			zap.setProveedores(darProveedores(arr[11]));
			zap.setAlmacenes(darAlmacenes(arr[12]));
			zapatos.add(zap);  

		}
		b.close();
	}  

	


	public void cargarAlmacenes() throws Exception
	{
		String alma;

		FileReader ff = new FileReader("almacenes.txt");
		BufferedReader bb = new BufferedReader(ff);

		while((alma = bb.readLine())!=null)
		{
			String [] arr = alma.split(",");


			Almacen almacen = new Almacen(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
			almacenes.add(almacen);  

		}
		bb.close();

	}

	public void cargarProveedores()throws Exception
	{

		String prov;
		FileReader fff = new FileReader("proveedores.txt");
		BufferedReader bbb = new BufferedReader(fff);

		while((prov = bbb.readLine())!=null)
		{
			String [] arr = prov.split(",");

			Proveedor provee = new Proveedor(Integer.parseInt(arr[0]), arr[1], arr[2], arr[3], arr[4], arr[5]);
			proveedores.add(provee);
		}
		bbb.close();



	}


	public List<Zapato> darZapatos()
	{
		return zapatos;

	}

	public List<Almacen> darAlmacenes()
	{
		return almacenes;

	}

	public List<Proveedor> darProveedores()
	{
		return proveedores;

	}


	public String darCodigo() 
	{
		// TODO Auto-generated method stub
		return (proveedores.size()	+1) + "";
	}


	public void exportarExcel(String rutaDestino) throws IOException
	{

		XSSFWorkbook workbook = new XSSFWorkbook();

		//Create a blank sheet
		XSSFSheet sheetZapatos = workbook.createSheet("Zapatos");

		int rownum = 0;
		Row row = sheetZapatos.createRow(rownum++);
		row.createCell(0).setCellValue("Referencia");
		row.createCell(1).setCellValue("Planta");
		row.createCell(2).setCellValue("Altura");
		row.createCell(3).setCellValue("Color");
		row.createCell(4).setCellValue("Material");
		row.createCell(5).setCellValue("Proveedores");
		row.createCell(6).setCellValue("Almacenes");
		row.createCell(7).setCellValue("Cantidad");
		row.createCell(8).setCellValue("Precio Costo");
		row.createCell(8).setCellValue("Precio Venta");
		row.createCell(9).setCellValue("Categoria");
		row.createCell(10).setCellValue("Fecha");

		for (Zapato z : zapatos)
		{
			row = sheetZapatos.createRow(rownum++);
			row.createCell(0).setCellValue(z.getReferencia());
			sheetZapatos.autoSizeColumn(0);
			
			row.createCell(1).setCellValue(z.getPlanta());
			sheetZapatos.autoSizeColumn(1);
			
			row.createCell(2).setCellValue(z.getAltura());
			sheetZapatos.autoSizeColumn(2);
			
			row.createCell(3).setCellValue(z.getColor());
			sheetZapatos.autoSizeColumn(3);
			
			row.createCell(4).setCellValue(z.getMaterial());
			sheetZapatos.autoSizeColumn(4);
			
			Cell cellProveedores = row.createCell(5);
			cellProveedores.setCellValue(z.getProveedoresString().replace("{", "\n"));
			CellStyle cs = workbook.createCellStyle();
		    cs.setWrapText(true);
		    cellProveedores.setCellStyle(cs);

		    
		    Cell cellAlmacenes = row.createCell(6);
		    cellAlmacenes.setCellValue(z.getAlmacenesString().replace("{", "\n"));
		    cellAlmacenes.setCellStyle(cs);
		    sheetZapatos.autoSizeColumn(6);
		    
			row.createCell(7).setCellValue(z.getCantidad());
			sheetZapatos.autoSizeColumn(7);
			
			row.createCell(8).setCellValue(z.getPrecioCosto());
			sheetZapatos.autoSizeColumn(8);
			
			row.createCell(8).setCellValue(z.getPrecioVenta());
			sheetZapatos.autoSizeColumn(9);
			
			row.createCell(9).setCellValue(z.getCategoria());
			sheetZapatos.autoSizeColumn(10);
			
			row.createCell(10).setCellValue(z.getFecha());
			sheetZapatos.autoSizeColumn(11);

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

		for (Almacen a : almacenes)
		{
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

		for (Proveedor p: proveedores)
		{
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
		row.createCell(1).setCellValue("Referencia");
		row.createCell(2).setCellValue("Proveedor");
		row.createCell(3).setCellValue("Cantidad Total");
		row.createCell(4).setCellValue("Precio Costo Total");
		row.createCell(5).setCellValue("Precio Venta Total");

		for (Object[] o: darTotales())
		{
			row = sheetTotales.createRow(rownum++);
			
			row.createCell(0).setCellValue((String)o[0]);
			sheetTotales.autoSizeColumn(0);
			
			row.createCell(1).setCellValue((String)o[1]);
			sheetTotales.autoSizeColumn(1);
			
			row.createCell(2).setCellValue((String)o[2]);
			sheetTotales.autoSizeColumn(2);
			
			row.createCell(3).setCellValue((Integer)o[3]);
			sheetTotales.autoSizeColumn(3);
			
			row.createCell(4).setCellValue((Integer)o[4]);
			sheetTotales.autoSizeColumn(4);
			
			row.createCell(5).setCellValue((Integer)o[5]);
			sheetTotales.autoSizeColumn(5);

		}
		
		try
		{
			//Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File(rutaDestino+"\\inventario_export_"+new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date())+".xlsx"));
			workbook.write(out);
			out.close();
			System.out.println("export written successfully on disk.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		// TODO Auto-generated method stub

	}


	public void llenarProveedores(FileInputStream excel) {
		// TODO Auto-generated method stub

	}


	public void llenarAlmacenes(FileInputStream excel) {
		// TODO Auto-generated method stub

	}


	public void vender(String referencia, int cantidad) 
	{
		int indiceInteresante = 0;
		for (int i = 0; i < zapatos.size(); i++)
		{
			Zapato x = zapatos.get(i);
			x.vender(cantidad);
			if (x.getReferencia().equals(referencia))
			{
				zapatos.remove(i);
				zapatos.add(x);
				break;
			}

		}

	}
	public String eliminarZapato(String referencia, String NIT) 
	{
		for (int i = 0; i < zapatos.size(); i++)
		{
			Zapato x = zapatos.get(i);
			if (x.getReferencia().equals(referencia) && x.getAlamacenes().get(0).getNit().equals(NIT))
			{
				zapatos.remove(i);
				return "Se ha eliminado exitosamente el zapato de referencia: " + referencia + " y Alamcen: "+NIT;
			}

		}
		return "No se ha encontrado el Zapato especificado para eliminar.";
	}




	public void guardar() 
	{
		try {
			if (proveedores.size()>0)
			{File fw = new File("proveedores.txt");

			PrintWriter out = new PrintWriter(fw);

			for (int i = 0; i < proveedores.size(); i++)
			{
				Proveedor pProveedor = proveedores.get(i);
				out.println(pProveedor.getCodigo() + "," + pProveedor.getNombre() + "," +pProveedor.getFabrica()+ "," + pProveedor.getDireccion() + ","+ pProveedor.getTelefono() + "," + pProveedor.getCiudad() );
			}
			out.close();
			}
			else
			{
				File fw = new File("proveedores.txt");
				fw.delete();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} 

		try {
			if (almacenes.size()>0)
			{
				File fw = new File("almacenes.txt");


				PrintWriter out = new PrintWriter(fw);

				for (int i = 0; i < almacenes.size(); i++) {

					Almacen pAlmacen = almacenes.get(i);
					out.println(pAlmacen.getCiudad() + "," + pAlmacen.getAlmacen() + "," +pAlmacen.getDireccion()+ "," + pAlmacen.getTelefono() + ","+ pAlmacen.getRazonSocial() + "," + pAlmacen.getNit() );
				}
				out.close();
			}
			else
			{
				File fw = new File("almacenes.txt");
				fw.delete();
			}


		} catch (Exception e) {
			e.printStackTrace();
		}


		try {
			if (zapatos.size()>0)
			{
				File fw = new File("zapatos.txt");

				PrintWriter out = new PrintWriter(fw);

				for (int i = 0; i < zapatos.size(); i++) {

					Zapato pZapato=	zapatos.get(i);
					out.println(pZapato.getReferencia() + "}" + pZapato.getPlanta() + "}" +   pZapato.getAltura()+ "}" + pZapato.getColor() + "}"+ pZapato.getMaterial() + "}" + pZapato.getPrecioCosto() + "}" + pZapato.getPrecioVenta() + "}"+ pZapato.getCantidad() + "}" + pZapato.getCategoria()+ "}" + pZapato.getVendidos() + "}" + pZapato.getStringFecha() + "}" + pZapato.getProveedoresString() + "}" + pZapato.getAlmacenesString());

				}
				out.close();
			}
			else
			{
				File fw = new File("zapatos.txt");
				fw.delete();
			}


		} catch (Exception e) {
			e.printStackTrace();
		} 



		System.out.println("mundo guardado");

	}


	public void eliminarProveedor(String referencia) 
	{
		for (int i = 0; i < proveedores.size(); i++)
		{
			Proveedor x = proveedores.get(i);
			if (x.getNombre().equals(referencia))
			{
				proveedores.remove(i);
				break;
			}

		}
		// TODO Auto-generated method stub

	}


	public void eliminarAlmacen(String ciudad)
	{
		for (int i = 0; i < almacenes.size(); i++)
		{
			Almacen x = almacenes.get(i);
			if (x.getCiudad().equals(ciudad))
			{
				almacenes.remove(i);
				break;
			}

		}
		// TODO Auto-generated method stub

	}

	public void setAlmacenes(List<Almacen> almacenes) {
		this.almacenes = almacenes;
	}


	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}


	public void setZapatos(List<Zapato> zapatos) {
		this.zapatos = zapatos;
	}


	public List<Proveedor> darProveedores(ArrayList<String> provs) {
		ArrayList<Proveedor> resp = new ArrayList<Proveedor>();
		for(Proveedor p: proveedores){
			for(String s: provs){
				if(p.getFabrica().equals(s.split(" - ")[0]) && p.getNombre().equals(s.split(" - ")[1]))resp.add(p);
			}
		}
		System.out.println(resp.size()+" proveedores");
		return resp;
	}
	
	public List<Almacen> darAlmacenes(ArrayList<String> alms) {
		ArrayList<Almacen> resp = new ArrayList<Almacen>();
		for(Almacen a: almacenes){
			for(String s: alms){
				if(a.getCiudad().equals(s))resp.add(a);
			}
		}
		System.out.println(resp.size()+" almacenes");
		return resp;
	}

	public List<Proveedor> darProveedores(String provs) {
		ArrayList<Proveedor> resp = new ArrayList<Proveedor>();
		System.out.println("provs: "+provs);
		String[] provsArr = provs.split("\\{");
		System.out.println("provsArr len: "+provsArr.length);
		System.out.println("proveedores size: "+proveedores.size());
		for(Proveedor p: proveedores){
			for(String s: provsArr){
				if(p.getFabrica().equals(s.split(" - ")[0]) && p.getNombre().equals(s.split(" - ")[1]))resp.add(p);
			}
		}
		System.out.println(resp.size()+" proveedores");
		return resp;
	}
	
	public List<Almacen> darAlmacenes(String alms) {
		ArrayList<Almacen> resp = new ArrayList<Almacen>();
		System.out.println("alms: "+alms);
		String[] almsArr = alms.split("\\{");
		System.out.println("almsArr len: "+almsArr.length);
		System.out.println("almacenes size: "+almacenes.size());
		for(Almacen a: almacenes){
			for(String s: almsArr){
				if(a.getCiudad().equals(s))resp.add(a);
			}
		}
		System.out.println(resp.size()+" almacenes");
		return resp;
	}

	/**
	 * Actualiza los NIT de los Almacenes relacionados a todos los Zapatos, por medio de comparacion del atributo "Almacen"
	 */
	public void actualizarNITsAlmacenes() {
		for(Zapato z: zapatos){
			for(Almacen aTemp: z.getAlamacenes()){
				for(Almacen a: almacenes){
					if(aTemp.getAlmacen().equals(a.getAlmacen())) aTemp.setNit(a.getNit());//tal vez hay que borrarlo y voverlo a insertar
				}
			}
		}
	}

	/**
	 * Actualiza los "Alamcen" de los Almacenes relacionados a todos los Zapatos, por medio de comparacion del NIT
	 */
	public void actualizarAlmacenesAlmacenes() {
		for(Zapato z: zapatos){
			for(Almacen aTemp: z.getAlamacenes()){
				for(Almacen a: almacenes){
					if(aTemp.getNit().equals(a.getNit())) aTemp.setAlmacen((a.getAlmacen()));
				}
			}
		}
	}

	/**
	 * Actualiza los codigos de los Proveedores relacionados a todos los Zapatos, por medio de comparacion del Nombre
	 */
	public void actualizarCodigosProveedores() {
		for(Zapato z: zapatos){
			for(Proveedor pTemp: z.getProveedores()){
				for(Proveedor p: proveedores){
					if(pTemp.getNombre().equals(p.getNombre())) pTemp.setCodigo((p.getCodigo()));
				}
			}
		}
	}

	/**
	 * Actualiza los nombres de los Proveedores relacionados a todos los Zapatos, por medio de comparacion del codigo
	 */
	public void actualizarNombresProveedores() {
		for(Zapato z: zapatos){
			for(Proveedor pTemp: z.getProveedores()){
				for(Proveedor p: proveedores){
					if(pTemp.getCodigo() == p.getCodigo()) pTemp.setNombre((p.getNombre()));
				}
			}
		}
	}


	public ArrayList<String> darListadoReferenciasZapatos() {
		ArrayList<String> res = new ArrayList<String>();
		res.add("Seleccione una referencia");
		for(Zapato z: zapatos){
			if(!res.contains(z.getReferencia()))res.add(z.getReferencia());
		}
		
		return res;
	}


	public int[] darTotalesReferencia(String referencia) {
		int[] res = new int[3];

		for(Zapato z: zapatos){
			if(z.getReferencia().equals(referencia)){
				res[0] += z.getCantidad();
				res[1] += z.getCantidad()*z.getPrecioCosto();
				res[2] += z.getCantidad()*z.getPrecioVenta();
			}
		}
		
		return res;
	}


	public ArrayList<Object[]> darTotales() {
		ArrayList<Object[]> arr = new ArrayList<Object[]>();
		for (int i = 0; i<almacenes.size(); i++) {
			for(int j = 0; j<zapatos.size(); j++){
				Object[] res = new Object[6];
				Almacen a = almacenes.get(i);
				Zapato z = zapatos.get(j);
				int sumaCantidad = 0;
				int sumaPrecioCosto = 0;
				int sumaPrecioVenta = 0;
				Almacen ka = z.getAlamacenes().get(0);
				if(ka.toString().equals(a.toString())){
					sumaCantidad+=z.getCantidad();
					sumaPrecioCosto+=z.getPrecioCosto()*z.getCantidad();
					sumaPrecioVenta+=z.getPrecioVenta()*z.getCantidad();
				}
				res[0] = a.toString();
				res[1] = z.getReferencia();
				res[2] = z.getProveedores().get(0).toString();
				res[3] = sumaCantidad;
				res[4] = sumaPrecioCosto;
				res[5] = sumaPrecioVenta;
				if((int)res[3]>0)arr.add(res);
			}
		}
		return arr;
	}

}
