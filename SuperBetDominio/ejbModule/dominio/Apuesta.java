package dominio;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="BET")
public class Apuesta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY )
	@Column(name="BET_ID" ,nullable=false )
	private Integer id;
	
	
	@Column(name="AMOUNT",nullable=false)
	private Double amount;
	
	@ManyToOne( cascade= CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	private Usuario user;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
	@JoinColumn(name="RESULT_ID")
	private List<Resultado> resultados;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
	@JoinColumn(name="BET_TYPE_ID")
	private TipoApuesta tipoApuesta;
	
	@ManyToOne
	@JoinColumn(name="COMBINATION_ID",nullable=true)
	private Combinacion combinacion;
	
	@Column(name="PAID", nullable=true)
	private Boolean fuePaga;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name="BET_DATE")
	private Calendar fechaApuesta;
	
	@Column(name="BENEFIT",nullable=true)
	private Double ganancia;
	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public Integer getId() {
		return id;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	
	public Double getAmount() {
		return amount;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	
	public Usuario getUser() {
		return user;
	}

	public void setResultados(List<Resultado> resultados) {
		this.resultados = resultados;
	}

	
	public List<Resultado> getResultados() {
		return resultados;
	}

	public void setTipoApuesta(TipoApuesta tipoApuesta) {
		this.tipoApuesta = tipoApuesta;
	}

	
	public TipoApuesta getTipoApuesta() {
		return tipoApuesta;
	}

	public void setCombinacion(Combinacion combinacion) {
		this.combinacion = combinacion;
	}
	
	
	public Combinacion getCombinacion() {
		return combinacion;
	}

	public void setFuePaga(Boolean fuePaga) {
		this.fuePaga = fuePaga;
	}

	public Boolean getFuePaga() {
		return fuePaga;
	}

	public void setFechaApuesta(Calendar fechaApuesta) {
		this.fechaApuesta = fechaApuesta;
	}

	public Calendar getFechaApuesta() {
		return fechaApuesta;
	}


	public void setGanancia(Double ganancia) {
		this.ganancia = ganancia;
	}


	public Double getGanancia() {
		return ganancia;
	}
}
