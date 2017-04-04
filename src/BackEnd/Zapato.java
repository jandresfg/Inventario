package BackEnd;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Zapato
{ 	

	private String referencia;
	private String planta;
	private String altura;
	private String color;
	private String material;
	private int precioCosto;
	private int precioVenta;
	private int cantidad;
	private int vendidos;
	private String categoria;
	private String numeracion;

	private List<Proveedor> proveedores;
	private List<Almacen> almacenes;
	
	private Date fecha;
        
        private boolean esReposicion;



	public Zapato(String pRef, String pPlanta, String pAltura , String pColor, String pMaterial, int pPrecioCosto, int pPrecioVenta,int pCantidad,String pCategoria, int pVendidos, Date pFecha,String pnumeracion )
	{
		numeracion = "0";
		referencia =pRef;
		planta =pPlanta;
		altura = pAltura;
		material = pMaterial;
		color =  pColor;
		precioCosto = pPrecioCosto;
		precioVenta = pPrecioVenta;
		cantidad = pCantidad;
		setCategoria(pCategoria);
		vendidos=pVendidos;
		fecha = pFecha;
		proveedores = new ArrayList<Proveedor>();
		almacenes = new ArrayList<Almacen>();
                esReposicion=false;
                if(            !pnumeracion.equals("") &&  !pnumeracion.equals(" "))
                {
                	numeracion = pnumeracion;


                }	}



        public Zapato(String pRef, String pPlanta, String pAltura , String pColor, String pMaterial, int pPrecioCosto, int pPrecioVenta,int pCantidad,String pCategoria, int pVendidos, Date pFecha, boolean pesReposicion,String pnumeracion )
	{

        	
    		numeracion = "0";
if(            !pnumeracion.equals("") &&  !pnumeracion.equals(" "))
{
	numeracion = pnumeracion;


}
		referencia =pRef;
		planta =pPlanta;
		altura = pAltura;
		material = pMaterial;
		color =  pColor;
		precioCosto = pPrecioCosto;
		precioVenta = pPrecioVenta;
		cantidad = pCantidad;
		setCategoria(pCategoria);
		vendidos=pVendidos;
		fecha = pFecha;
		proveedores = new ArrayList<Proveedor>();
		almacenes = new ArrayList<Almacen>();
                esReposicion=pesReposicion;

	}




        public boolean esReposicion(){
            return esReposicion;
        }
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getPlanta() {
		return planta;
	}
	public void setPlanta(String planta) {
		this.planta = planta;
	}
	public String getAltura() {
		return altura;
	}
	public void setAltura(String altura) {
		this.altura = altura;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	public void vender(int cantidad2)
	{
		vendidos+= cantidad2;
	}







	public String getCategoria() {
		return categoria;
	}











	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}











	public int getVendidos() {
		return vendidos;
	}











	public void setVendidos(int vendidos) {
		this.vendidos = vendidos;
	}











	public int getPrecioCosto() {
		return precioCosto;
	}











	public void setPrecioCosto(int precioCosto) {
		this.precioCosto = precioCosto;
	}











	public int getPrecioVenta() {
		return precioVenta;
	}











	public void setPrecioVenta(int precioVenta) {
		this.precioVenta = precioVenta;
	}











	public List<Proveedor> getProveedores() {
		return proveedores;
	}











	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}











	public List<Almacen> getAlamacenes() {
		return almacenes;
	}











	public void setAlmacenes(List<Almacen> alamacenes) {
		almacenes = alamacenes;
	}











	public Date getFecha() {
		return fecha;
	}











	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	
	public static Date getFechaFromString(String string) throws ParseException {
		return new SimpleDateFormat(FORMATO_FECHA, new Locale("es", "ES")).parse(string);
	}
	
	public String getStringFecha()  {
		return new SimpleDateFormat(FORMATO_FECHA, new Locale("es", "ES")).format(fecha);
	}
	
	public static final String FORMATO_FECHA = "dd-MMM-yyyy";
	
	public static String cadenafechaEjemplo()  {
		return new SimpleDateFormat(FORMATO_FECHA, new Locale("es", "ES")).format(new Date());
	}











	public String getProveedoresString() {
		String resp = "";
		for (Proveedor p: proveedores){
			resp = resp + p + "\n";
		}
		resp = resp.trim();
		resp = resp.replaceAll("\n", "{");
		return resp;
	}



public int getProveedoresNumber() {
		
		return proveedores.get(0).getCodigo();
	}







	public String getAlmacenesString() {
		String resp = "";
		for (Almacen a: almacenes){
			resp = resp + a + "\n";
		}
		resp = resp.trim();
		resp = resp.replaceAll("\n", "{");
		return resp;
	}



	@Override
	public String toString() {
		return "Zapato [referencia=" + referencia + ", planta=" + planta + ", altura=" + altura + ", color=" + color
				+ ", material=" + material + ", precioCosto=" + precioCosto + ", precioVenta=" + precioVenta
				+ ", cantidad=" + cantidad + ", vendidos=" + vendidos + ", categoria=" + categoria + ", proveedores="
				+ proveedores + ", almacenes=" + almacenes + ", fecha=" + fecha + ", esReposicion=" + esReposicion
				+ ", numeracion=" + numeracion
				+ "]";
	}



	public String getNumeracion() {
		return numeracion;
	}



	public void setNumeracion(String numeracion) {
		this.numeracion = numeracion;
	}

}
