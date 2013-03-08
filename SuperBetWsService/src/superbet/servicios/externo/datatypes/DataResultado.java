package superbet.servicios.externo.datatypes;


public class DataResultado {
	
	private Integer id;
	private String descripcion; 
	private Double dividendo;
	
	
	
	
	public void setDividendo(Double dividendo) {
		this.dividendo = dividendo;
	}
	public Double getDividendo() {
		return dividendo;
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
