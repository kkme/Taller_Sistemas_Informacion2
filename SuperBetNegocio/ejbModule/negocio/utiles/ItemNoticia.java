package negocio.utiles;

import java.awt.Image;
import java.util.Date;

public class ItemNoticia {

	private String titulo;
	private String contenido;
	private String urlFoto;
	private String nombreFuente;
	private String nombreCompeticion;
	private String fechaPublicacion;
	private String link;
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setNombreFuente(String nombreFuente) {
		this.nombreFuente = nombreFuente;
	}

	public String getNombreFuente() {
		return nombreFuente;
	}

	public void setNombreCompeticion(String nombreCompeticion) {
		this.nombreCompeticion = nombreCompeticion;
	}

	public String getNombreCompeticion() {
		return nombreCompeticion;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getContenido() {
		return contenido;
	}

	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLink() {
		return link;
	} 
	
	
}
