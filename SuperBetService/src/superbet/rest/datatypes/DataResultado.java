package superbet.rest.datatypes;

public class DataResultado {
	
	private Integer id;
	private String descripcion;
	private Double cuota;
	
	
	
	
	
	
	
	
	public String toString(){
		return "{'id': '"+id+"', 'desc': '"+descripcion+"', 'cuota': '"+cuota+"' }";
	}
	
	
	
	
	public void setCuota(Double cuota) {
		this.cuota = cuota;
	}
	public Double getCuota() {
		return cuota;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	
	
	

}
