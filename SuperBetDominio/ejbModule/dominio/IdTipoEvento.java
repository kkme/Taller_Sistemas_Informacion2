package dominio;

import java.io.Serializable;

public class IdTipoEvento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int evento;
	
	private int tipoApuesta;

	public IdTipoEvento()  { }
	
	public IdTipoEvento(int evento, int tipoApuesta) {
		this.evento = evento;
		this.tipoApuesta = tipoApuesta;
	}

	public boolean equals(Object object) {
		if (object instanceof IdTipoEvento) {
			IdTipoEvento pk = (IdTipoEvento) object;
			return evento == pk.evento && tipoApuesta == pk.tipoApuesta;
		} else {
			return false;
		}
	}

	public int hashCode() {
		return evento + tipoApuesta;
	}
}
