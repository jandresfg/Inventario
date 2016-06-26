package BackEnd;

public class Almacen
{
	private String ciudad;
	private String almacen;
	private String direccion;
	private String telefono;
	private String razonSocial;
	private String nit;


	public Almacen(String pCiudad, String pAlmacen,  String pDireccion,  String pTelefono, String pRazonSocial, String pNit)
	{
		ciudad = pCiudad;
		almacen = pAlmacen;
		direccion = pDireccion;
		telefono = pTelefono;
		razonSocial = pRazonSocial;
		nit =pNit;



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
}
