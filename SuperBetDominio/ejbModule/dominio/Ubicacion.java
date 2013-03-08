package dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LOCATION")
public class Ubicacion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY )
	@Column(name="LOCATION_ID" ,nullable=false )
	private Integer id;
	
	@Column(name="LATITUDE")
	private Double latitud;
	
	@Column(name="LONGITUDE")
	private Double longitud;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public Double getLongitud() {
		return longitud;
	}
}
