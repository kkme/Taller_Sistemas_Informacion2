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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.ManyToMany;

@Entity
@Table(name="COMBINATION")
public class Combinacion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY )
	@Column(name="COMBINATION_ID" ,nullable=false )
	private Integer id;
	
	@Column(name="NAME")
	private String nombre;
	
	@Column(name="DESCRIPTION")
	private String descripcion;
	
	@OneToMany(mappedBy="combinacion", fetch = FetchType.LAZY, cascade= {CascadeType.REFRESH})
	private List<TipoEvento> tipo_eventos;
	
	@Column(name="PAID")
	private Boolean fuePagada;

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

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setTipo_eventos(List<TipoEvento> tipo_eventos) {
		this.tipo_eventos = tipo_eventos;
	}

	public List<TipoEvento> getTipo_eventos() {
		return tipo_eventos;
	}

	public void setFuePagada(Boolean fuePagada) {
		this.fuePagada = fuePagada;
	}

	public Boolean getFuePagada() {
		return fuePagada;
	}

}
