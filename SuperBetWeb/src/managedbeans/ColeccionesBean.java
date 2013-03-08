package managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.event.SelectEvent;

import dominio.Competicion;
import dominio.ConfigParam;
import dominio.Deporte;
import dominio.Pais;
import dominio.TipoApuesta;
import negocio.utiles.interfaces.Colecciones;

@ManagedBean(name="coleccionesBean")
@SessionScoped
public class ColeccionesBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EJB(lookup="java:global/SuperBetNegocio/ColeccionesImpl!negocio.utiles.interfaces.Colecciones")
	private Colecciones colecciones;
	
	private ArrayList<Deporte> deportes;
	private HashMap<String,List<Competicion>> mapCompeticionesPorDeportePais;
	private ArrayList<Pais> paises;
	
	private Deporte deporte;
	private Competicion competicion;
	private Pais pais;
	
	private List<TipoApuesta> tipoApuestas;
	
	@PostConstruct
	public void crearColecciones() {
		deportes = (ArrayList<Deporte>) colecciones.getDeportes();
		paises = (ArrayList<Pais>) colecciones.getPaises();
		mapCompeticionesPorDeportePais = new HashMap<String,List<Competicion>> ();
		for (Deporte d : deportes) {
			for (Pais pais : paises) {
				List<Competicion> competiciones = colecciones.getCompeticionesPorDeportePais(d.getId(), pais.getId());
				mapCompeticionesPorDeportePais.put(d.getName() + pais.getName(),competiciones);
			}
		}
		pais = colecciones.getPaises().get(0);
		
		tipoApuestas = colecciones.getTipoApuestas();
	}

	public ArrayList<Deporte> completeDeporte(String deporte) {
		ArrayList<Deporte> resultados = new ArrayList<Deporte>();
		for (Deporte d : deportes) {
			if (d.getName().contains(deporte) || d.getName().toLowerCase().contains(deporte)) resultados.add(d);
		}
		return resultados;
	}
	
	public void handleSelectDeporte (SelectEvent event) {
		deporte = (Deporte) event.getObject();
	}
	
	public void handleSelectCompeticion (SelectEvent event) {
		competicion = (Competicion) event.getObject();
	}
	
	public void handleSelectPais (ValueChangeEvent event) {
		pais = (Pais) event.getNewValue();
		System.out.println("pais: " + pais.getName());
	}
	
	public ArrayList<Competicion> completeCompeticion(String competicion) {
		ArrayList<Competicion> resultados = new ArrayList<Competicion>();
		if ((deporte != null) && (pais != null)) {
			List<Competicion> competiciones = mapCompeticionesPorDeportePais.get(deporte.getName() + pais.getName());
			if (competiciones != null) {
				for (Competicion c : competiciones) { 
					if (c.getNombre().contains(competicion) || (c.getNombre().toLowerCase().contains(competicion))) {
						resultados.add(c);
					}
				}
			}
		} 
		return resultados;
	}
	
	public ArrayList<Pais> completePais (String pais) {
		ArrayList<Pais> resultados = new ArrayList<Pais>();
		for (Pais p : paises) {
			if (p.getName().contains(pais) || p.getName().toLowerCase().contains(pais)) resultados.add(p);
		}
		return resultados;
	}
	
	public ArrayList<Pais> getPaises() {
		return paises;
	}

	public void setPaises(ArrayList<Pais> paises) {
		this.paises = paises;
	}

	public void setDeporte(Deporte deporte) {
		this.deporte = deporte;
	}

	public Deporte getDeporte() {
		return deporte;
	}

	public void setCompeticion(Competicion competicion) {
		this.competicion = competicion;
	}

	public Competicion getCompeticion() {
		if ((deporte != null) && (pais != null) && (competicion != null)) {
			ArrayList<Competicion> competiciones = (ArrayList<Competicion>) mapCompeticionesPorDeportePais.get(deporte.getName() + pais.getName());
			if (competiciones != null) {
				for (Competicion c : competiciones) {
					if (c.getNombre().equals(competicion.getNombre())) {
						return c;
					}
				}
			}
		}
		return null;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Pais getPais() {
		for (Pais p : paises) {
			if (p.getName().equals(pais.getName())) {
				return p;
			}
		}
		return null;
	}

	public Pais obtenerPaisPorNombre(String name) {
		for (Pais p : paises) {
			if (p.getName().equalsIgnoreCase(name)) {
				return p;
			}
		}
		return null;
	}

	public void setTipoApuestas(List<TipoApuesta> tipoApuestas) {
		this.tipoApuestas = tipoApuestas;
	}

	public List<TipoApuesta> getTipoApuestas() {
		return tipoApuestas;
	}

	public List<Competicion> getCompeticiones() {
		return colecciones.getCompeticiones();
	}
	
	public ConfigParam obtenerParamsConfig () {
		return colecciones.obtenerParamsConfig();
	}
	
}
