package dominio.externo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import dominio.Usuario;



@Entity
public class ApuestaExterna {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="USER_ID")
	private Usuario usuario;
	
	private Integer idApuestaExterna;
	
	private Double montoApostado;
	
	private Double gananciaEsperada;
	
	
	
	

	public void setGananciaEsperada(Double gananciaEsperada) {
		this.gananciaEsperada = gananciaEsperada;
	}

	public Double getGananciaEsperada() {
		return gananciaEsperada;
	}

	public void setMontoApostado(Double montoApostado) {
		this.montoApostado = montoApostado;
	}

	public Double getMontoApostado() {
		return montoApostado;
	}

	public void setIdApuestaExterna(Integer idApuestaExterna) {
		this.idApuestaExterna = idApuestaExterna;
	}

	public Integer getIdApuestaExterna() {
		return idApuestaExterna;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	
	
	
	

}
