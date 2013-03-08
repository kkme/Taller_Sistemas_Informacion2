package presentacion;

import dominio.Evento;
import dominio.TipoEvento;

public class DocumentTree {
	
	private String categoria;
	private Evento evento;
	private TipoEvento tipoEvento;
	
	public DocumentTree(String categoria, Evento evento, TipoEvento tipoEvento){
		this.categoria = categoria;
		this.evento = evento;
		this.tipoEvento = tipoEvento;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getCategoria() {
		return categoria;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}


	
}
