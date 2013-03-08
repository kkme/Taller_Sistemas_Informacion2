package superbet.servicios.externo.datatypes;

public class DataGanador {

	private Integer idUsuario;
	private Double ganancia; 
	private Integer idApuesta;
	
	
	
	
	
	public void setIdApuesta(Integer idApuesta) {
		this.idApuesta = idApuesta;
	}
	public Integer getIdApuesta() {
		return idApuesta;
	}
	public void setGanancia(Double ganancia) {
		this.ganancia = ganancia;
	}
	public Double getGanancia() {
		return ganancia;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	
	
	
}
