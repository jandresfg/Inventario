package BackEnd;

public class Almacen implements Comparable<Almacen> 
{
	private String ciudad;
	private String almacen;
	private String direccion;
	private String telefono;
	private String razonSocial;
	private String nit;
	public long getTotalCosto() {
		return totalCosto;
	}







	public void setTotalCosto(int totalCosto) {
		this.totalCosto += totalCosto;
	}







	public long getTotalVenta() {
		return totalVenta;
	}







	public void setTotalVenta(long totalVenta) {
		this.totalVenta += totalVenta;
	}







	public int getTotalPares() {
		return totalPares;
	}







	public void setTotalPares(int totalPares) {
		this.totalPares += totalPares;
	}







	public int getTotalDama() {
		return totalDama;
	}







	public void setTotalDama(int totalDama) {
		this.totalDama += totalDama;
	}







	public int getTotalCaballero() {
		return totalCaballero;
	}







	public void setTotalCaballero(int totalCaballero) {
		this.totalCaballero += totalCaballero;
	}
	public int getTotalInfantil() {
		return totalInfantil;
	}
public void setTotalInfantil(int totalInfantil) {
		this.totalInfantil += totalInfantil;
	}







	private long totalCosto = 0;
	private long totalVenta= 0;
	private int totalPares= 0;
	private int totalDama= 0;
	private int totalCaballero= 0;
	private int totalInfantil= 0;








	public Almacen(String pCiudad, String pAlmacen,  String pDireccion,  String pTelefono, String pRazonSocial, String pNit)
	{
		ciudad = pCiudad;
		almacen = pAlmacen;
		direccion = pDireccion;
		telefono = pTelefono;
		razonSocial = pRazonSocial;
		nit =pNit;



	}


public void volverCero()
{
	 totalCosto = 0;
	 totalVenta= 0;
	totalPares= 0;
	 totalDama= 0;
	totalCaballero= 0;
	 totalInfantil= 0;	
}




	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getAlmacen() {
		return almacen;
	}
	public void setAlmacen(String almacen) {
		this.almacen = almacen;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}







	@Override
	public String toString() {
		return ciudad;
	}







	@Override
	public int compareTo(Almacen arg0) {
		return ciudad.compareTo(arg0.getCiudad());
	}
}
