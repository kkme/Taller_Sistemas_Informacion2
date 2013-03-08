package dominio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CONTEST")
public class Competicion implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY )
	@Column(name="CONTEST_ID" ,nullable=false )
	private Integer id;
	
	@Column(name="NAME")
	private String nombre;
	
	@OneToMany(mappedBy="competicion", cascade= CascadeType.ALL)
	private List<Evento> eventos;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade= CascadeType.ALL)
	@JoinColumn(name="SPORT_ID")
	private Deporte deporte;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade= CascadeType.ALL)
	@JoinColumn(name="COUNTRY_ID")
	private Pais country;

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setDeporte(Deporte deporte) {
		this.deporte = deporte;
	}

	public Deporte getDeporte() {
		return deporte;
	}

	public void setCountry(Pais country) {
		this.country = country;
	}

	public Pais getCountry() {
		return country;
	}
	
}
