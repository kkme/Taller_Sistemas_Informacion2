package superbet.rest.datatypes;


public class DataEvento {
	
	private Integer id;
	private String nombre;
	
	
	
	@Override
	public String toString(){
		return "evento: { id : "+id+" , nombre: "+nombre+" }";
	}
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}

}
