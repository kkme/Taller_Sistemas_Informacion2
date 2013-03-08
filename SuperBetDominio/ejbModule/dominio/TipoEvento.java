package dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EVENT_TYPE")
@IdClass(IdTipoEvento.class)
public class TipoEvento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.MERGE)
    @JoinColumn(name="EVENT_ID")
    private Evento evento;
	
	@Id
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="BET_TYPE_ID")
	private TipoApuesta tipoApuesta;
	
	@OneToMany(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
	private List<Resultado> resultados;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="COMBINATION_ID",nullable=true)
	private Combinacion combinacion;

	@Column(name="PAYED")
	private Boolean fuePagada;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	private Resultado ganador;
	
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name="DATE_BET_BEGIN")
	private Date inicioPerApuesta;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name="DATE_BET_END")
	private Date finPerApuesta;
	
	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setTipoApuesta(TipoApuesta tipoApuesta) {
		this.tipoApuesta = tipoApuesta;
	}

	public TipoApuesta getTipoApuesta() {
		return tipoApuesta;
	}

	public void setResultados(List<Resultado> resultados) {
		this.resultados = resultados;
	}

	public List<Resultado> getResultados() {
		return resultados;
	}

	public void setCombinacion(Combinacion combinacion) {
		this.combinacion = combinacion;
	}

	public Combinacion getCombinacion() {
		return combinacion;
	}

	public void setGanador(Resultado ganador) {
		this.ganador = ganador;
	}

	public Resultado getGanador() {
		return ganador;
	}

	public void setInicioPerApuesta(Date inicioPerApuesta) {
		this.inicioPerApuesta = inicioPerApuesta;
	}

	public Date getInicioPerApuesta() {
		return inicioPerApuesta;
	}

	public void setFinPerApuesta(Date finPerApuesta) {
		this.finPerApuesta = finPerApuesta;
	}

	public Date getFinPerApuesta() {
		return finPerApuesta;
	}

	public void setFuePagada(Boolean fuePagada) {
		this.fuePagada = fuePagada;
	}

	public Boolean getFuePagada() {
		return fuePagada;
	}

}
