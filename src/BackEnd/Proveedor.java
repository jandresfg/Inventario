package BackEnd;

public class Proveedor 
{
	
	private int codigo;
	private String nombre;
	private String fabrica;
	private String direccion;
	private String telefono;
	private String ciudad;










	public Proveedor(int pCodigo, String pNombre, String pFabrica, String pDireccion, String pTelefono, String pCiudad )
	{
		codigo = pCodigo;
		nombre = pNombre;
		fabrica = pFabrica;
		direccion = pDireccion;
		telefono =  pTelefono;
		ciudad = pCiudad;



	}






	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFabrica() {
		return fabrica;
	}
	public void setFabrica(String fabrica) {
		this.fabrica = fabrica;
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
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return fabrica + " - " + nombre;
	}

}
