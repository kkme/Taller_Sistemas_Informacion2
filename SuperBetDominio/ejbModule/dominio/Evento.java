package dominio;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EVENT")
public class Evento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="EVENT_ID", nullable=false )
	private Integer id;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name="DATE_BEGIN")
	private Calendar inicio;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name="DATE_END")
	private Calendar fin;
	
	
	@OneToOne
	@JoinColumn(name="LOCATION_ID")
	private Ubicacion ubicacion;
	
	@ManyToOne
	@JoinColumn(name="CONTEST_ID",nullable=true)
	private Competicion competicion;
	
	@Column(name="NAME")
	private String nombre;
	
	@Column(name="IMPORTANCE")
	private Double importancia;
	
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setInicio(Calendar inicio) {
		this.inicio = inicio;
	}

	public Calendar getInicio() {
		return inicio;
	}

	public void setFin(Calendar fin) {
		this.fin = fin;
	}

	public Calendar getFin() {
		return fin;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setCompeticion(Competicion competicion) {
		this.competicion = competicion;
	}

	public Competicion getCompeticion() {
		return competicion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setImportancia(Double importancia) {
		this.importancia = importancia;
	}

	public Double getImportancia() {
		return importancia;
	}


}
