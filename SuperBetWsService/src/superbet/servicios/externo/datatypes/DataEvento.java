package superbet.servicios.externo.datatypes;

import java.util.GregorianCalendar;

public class DataEvento { 
	
	private String nombre;
	
	private GregorianCalendar fecha;
	
	private String descripcion;

	private Integer id;
	private Double latitud;
	private Double longitud;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setFecha(GregorianCalendar fecha) {
		this.fecha = fecha;
	}

	public GregorianCalendar getFecha() {
		return fecha;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLatitud() {
		return latitud;
	}
	
	

}
