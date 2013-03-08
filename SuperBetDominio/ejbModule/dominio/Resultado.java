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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="RESULT")
public class Resultado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY )
	@Column(name="RESULT_ID" ,nullable=false )
	private Integer id;
	
	@Column(name="PAYMENT",nullable=false)
	private Double cuota;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@ManyToMany(mappedBy="resultados", fetch = FetchType.LAZY, cascade= CascadeType.ALL)
	private List<Apuesta> apuestas;
	
	@OneToMany(mappedBy="resultado", fetch = FetchType.LAZY, cascade= CascadeType.ALL)
	private List<ApuestaPizarra> apuestasPizarra;
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}

	public void setCuota(Double cuota) {
		this.cuota = cuota;
	}

	public Double getCuota() {
		return cuota;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setApuestas(List<Apuesta> apuestas) {
		this.apuestas = apuestas;
	}

	public List<Apuesta> getApuestas() {
		return apuestas;
	}

	public void setApuestasPizarra(List<ApuestaPizarra> apuestasPizarra) {
		this.apuestasPizarra = apuestasPizarra;
	}

	public List<ApuestaPizarra> getApuestasPizarra() {
		return apuestasPizarra;
	}

	@Override
	public boolean equals(Object r){
		return ((Resultado)r).getId()==this.id;
	}


}
