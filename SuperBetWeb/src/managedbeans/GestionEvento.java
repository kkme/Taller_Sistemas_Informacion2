package managedbeans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;

import negocio.eventos.interfaces.IEventosAdmin;

import org.primefaces.event.DragDropEvent;
import org.primefaces.event.RowEditEvent;

import utiles.MessagePrinter;
import utiles.Utiles;
import dominio.Combinacion;
import dominio.Competicion;
import dominio.Evento;
import dominio.TipoApuesta;
import dominio.TipoEvento;
import dominio.Ubicacion;

@ManagedBean(name = "gestionEvento")
@SessionScoped
public class GestionEvento {

	@ManagedProperty(value = "#{usuarioMB}", name = "guser")
	private GestionUsuario guser;

	private String nombre;

	private GregorianCalendar fechaInicio;
	private GregorianCalendar fechaFin;
	private GregorianCalendar fechaInicioApuesta;
	private GregorianCalendar fechaFinApuesta;

	private Integer minInicio;
	private Integer horaInicio;
	private Integer minFin;
	private Integer horaFin;
	private Integer minInicioApuesta;
	private Integer horaInicioApuesta;
	private Integer minFinApuesta;
	private Integer horaFinApuesta;

	private Double importancia;
	private ArrayList<Evento> eventos;

	private ArrayList<Evento> eventosPublic;

	private Evento eventoSelected;

	// prefijo alta combinada comb_
	private List<TipoEvento> comb_TipoEventosNoPagos = new ArrayList<TipoEvento>();
	private List<TipoEvento> comb_combinacion = new ArrayList<TipoEvento>();
	private TipoEvento comb_selectedTE = new TipoEvento();
	private String comb_desc;
	private String comb_nombre;
	


	// prefijo pago combinado pc_
	private List<Combinacion> pc_combinaciones = new ArrayList<Combinacion>();
	private Combinacion pc_selectedComb = new Combinacion();

	// Prefijo Ultimos Resultados ur

	private List<TipoEvento> ur_listaResult = new ArrayList<TipoEvento>();

	public void ur_cargarListaResultados() {

		ur_listaResult = this.eventosAdmin.getResultadosEventosRecientes(guser
				.getLogedUser());
	}
	
	
	public void ur_cargarListaResultadosGeneral(){
		ur_listaResult.clear();
		ur_listaResult = this.eventosAdmin.getResultadosEventosRecientes();
		
	}

	@EJB(lookup = "java:global/SuperBetNegocio/ControladorEventos!negocio.eventos.interfaces.IEventosAdmin")
	private IEventosAdmin eventosAdmin;

	@ManagedProperty(value = "#{coleccionesBean}")
	private ColeccionesBean coleccionesBean;

	@ManagedProperty(value = "#{mapaBean}")
	private MapaBean mapaBean;

	//@ManagedProperty(value = "#{gestionApuesta}")
//	private GestionApuesta gestionApuesta;

	public void populateAllTipoEvento() {
		setComb_TipoEventosNoPagos(eventosAdmin.tipoEventosNoPagos());
	}

	public void pc_cargarCombinaciones(ComponentSystemEvent event) {
		pc_combinaciones = eventosAdmin.getCombinacionesNoPagas();
	}

	public void pc_pagarApuestaCombinada() {

		try {

			eventosAdmin.pagarApuestaCombinada(pc_selectedComb);
			MessagePrinter
					.showInformationMessage("Se Han pagado la apuesta combinada!");
		} catch (Exception e) {
			MessagePrinter.showErrorMessage(e.getMessage());
		}finally{
			limpiarCampos();
		}
	}

	public String comb_onTEAdd() {
		comb_combinacion.add(comb_selectedTE);
		comb_TipoEventosNoPagos.remove(comb_selectedTE);
		System.out
				.println("Agrego: " + comb_selectedTE.getEvento().getNombre());
		return null;
	}

	public String comb_agregarCombinacion() {
		try {
			this.eventosAdmin.agregarCombinada(this.comb_combinacion,
					comb_nombre, comb_desc);
			MessagePrinter
					.showInformationMessage("Combinaci�n Ingresada con �xito");
			this.limpiarCampos();
		} catch (Exception e) {
			MessagePrinter.showErrorMessage("No se pudo crear la combinacion");
		}
		return "home";
	}

	public void setColeccionesBean(ColeccionesBean coleccionesBean) {
		this.coleccionesBean = coleccionesBean;
	}

	public void setMapaBean(MapaBean mapaBean) {
		this.mapaBean = mapaBean;
	}

	//public void setGestionApuesta(GestionApuesta gestionApuesta) {
	//	this.gestionApuesta = gestionApuesta;
	//}

	/*
	 * Navegacion y eventos
	 */

	public void comb_onTEDrop(DragDropEvent ddEvent) {
		TipoEvento te = ((TipoEvento) ddEvent.getData());
		comb_combinacion.add(te);
		comb_TipoEventosNoPagos.remove(te);
	}

	public String altaEvento() {
		System.out.println("Alta de evento...");
		Evento evento = new Evento();

		Ubicacion ubicacion = new Ubicacion();
		ubicacion.setLatitud(mapaBean.getLatitud());
		ubicacion.setLongitud(mapaBean.getLongitud());
		Competicion competicion = coleccionesBean.getCompeticion();

		fechaInicio.set(Calendar.HOUR_OF_DAY, horaInicio);
		fechaInicio.set(Calendar.MINUTE, minInicio);
		fechaFin.set(Calendar.HOUR_OF_DAY, horaFin);
		fechaFin.set(Calendar.MINUTE, minFin);

		evento.setNombre(nombre);
		evento.setImportancia(importancia);
		evento.setInicio(fechaInicio);
		evento.setFin(fechaFin);
		evento.setUbicacion(ubicacion);
		evento.setCompeticion(competicion);

		String outcome = null;
		
		try {

			boolean error = false;

			if (fechaInicio.after(fechaFin)) {
				Utiles.addMessage("fechaInicio",
						"La fecha de inicio no puede ser posterior a la de fin");
				error = true;
			}
			if ((coleccionesBean.getDeporte() == null)
					|| (coleccionesBean.getDeporte().getName() == null)) {
				Utiles.addMessage("deporteSelected",
						"Por favor seleccione un valor");
				error = true;
			}
			if ((competicion == null) || (competicion.getNombre() == null)) {
				Utiles.addMessage("compSelected",
						"Por favor seleccione un valor");
				error = true;
			}

			if (!error) {
				evento = eventosAdmin.insertarEvento(evento);
				MessagePrinter
						.showInformationMessage("Evento creado con �xito");
				limpiarCampos();
				outcome = "eventos";
			}

		} catch (Exception e) {
			MessagePrinter.showErrorMessage("No se pudo crear el evento");
		}

		return outcome;
	}

	public String editarEvento() {
		Evento evento = this.eventoSelected;
		if (evento != null) {
			try {
				Competicion competicion = this
						.obtenerCompeticionDeEvento(evento.getId());
				this.eventoSelected.setCompeticion(competicion);
				coleccionesBean.setCompeticion(competicion);
				coleccionesBean.setDeporte(competicion.getDeporte());
				Ubicacion ubicacion = this.obtenerUbicacionDeEvento(evento
						.getId());
				this.eventoSelected.setUbicacion(ubicacion);
			} catch (Exception e) {
				System.out.println("Error al traer competicion o ubicacion");
				evento.setCompeticion(null);
				evento.setUbicacion(null);
			}
		}

		this.fechaInicio = (GregorianCalendar) this.eventoSelected.getInicio();
		this.horaInicio = fechaInicio.get(Calendar.HOUR_OF_DAY);
		this.minInicio = fechaInicio.get(Calendar.MINUTE);

		this.fechaFin = (GregorianCalendar) this.eventoSelected.getFin();
		this.horaFin = fechaFin.get(Calendar.HOUR_OF_DAY);
		this.minFin = fechaFin.get(Calendar.MINUTE);

		return "editarEvento";
	}

	public String confirmaEdicionEvento() {
		try {
			this.eventoSelected
					.setCompeticion(coleccionesBean.getCompeticion());
			this.fechaInicio.set(Calendar.HOUR_OF_DAY, horaInicio);
			this.fechaInicio.set(Calendar.MINUTE, minInicio);
			this.eventoSelected.setInicio(this.fechaInicio);
			this.fechaFin.set(Calendar.HOUR_OF_DAY, horaFin);
			this.fechaFin.set(Calendar.MINUTE, minFin);
			this.eventoSelected.setFin(this.fechaFin);
			eventosAdmin.actualizarEvento(this.eventoSelected);
			MessagePrinter
					.showInformationMessage("Evento actualizado con exito");
			limpiarCampos();
		} catch (Exception e) {
			MessagePrinter.showErrorMessage("No se pudo actualizar el evento");
		}

		return "eventos";
	}

	private void limpiarCampos() {
		this.eventoSelected = new Evento();
		this.horaFin = 0;
		this.horaFinApuesta = 0;
		this.horaInicio = 0;
		this.horaInicioApuesta = 0;
		this.fechaFin = new GregorianCalendar();
		this.fechaInicio = new GregorianCalendar();
		this.nombre = "";
		this.importancia = 0.0;
		coleccionesBean.setCompeticion(null);

		this.comb_combinacion = new ArrayList<TipoEvento>();
		this.comb_desc = "";
		this.comb_nombre = "";
		this.comb_selectedTE = new TipoEvento();
	}

	public void onEditRow(RowEditEvent event) {
	}

	public Competicion obtenerCompeticionDeEvento(Integer idEvento) {
		return eventosAdmin.obtenerCompeticionDeEvento(idEvento);
	}

	public ArrayList<TipoApuesta> obtenerTiposApuestaEvento(Integer idEvento) {
		return eventosAdmin.obtenerTiposApuestaEvento(idEvento);
	}

	private Ubicacion obtenerUbicacionDeEvento(Integer id) {
		return this.eventosAdmin.obtenerUbicacionDeEvento(id);
	}

	/*
	 * Setters y getters
	 */
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setImportancia(Double importancia) {
		this.importancia = importancia;
	}

	public Double getImportancia() {
		return importancia;
	}

	public GregorianCalendar getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(GregorianCalendar fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public GregorianCalendar getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(GregorianCalendar fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getMinInicio() {
		return minInicio;
	}

	public void setMinInicio(Integer minInicio) {
		this.minInicio = minInicio;
	}

	public Integer getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Integer horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Integer getMinFin() {
		return minFin;
	}

	public void setMinFin(Integer minFin) {
		this.minFin = minFin;
	}

	public Integer getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Integer horaFin) {
		this.horaFin = horaFin;
	}

	public Integer getMinInicioApuesta() {
		return minInicioApuesta;
	}

	public void setMinInicioApuesta(Integer minInicioApuesta) {
		this.minInicioApuesta = minInicioApuesta;
	}

	public Integer getHoraInicioApuesta() {
		return horaInicioApuesta;
	}

	public void setHoraInicioApuesta(Integer horaInicioApuesta) {
		this.horaInicioApuesta = horaInicioApuesta;
	}

	public Integer getMinFinApuesta() {
		return minFinApuesta;
	}

	public void setMinFinApuesta(Integer minFinApuesta) {
		this.minFinApuesta = minFinApuesta;
	}

	public Integer getHoraFinApuesta() {
		return horaFinApuesta;
	}

	public void setHoraFinApuesta(Integer horaFinApuesta) {
		this.horaFinApuesta = horaFinApuesta;
	}

	public void setEventos(ArrayList<Evento> eventos) {
		this.eventos = eventos;
	}

	public ArrayList<Evento> getEventos() {
		eventos = eventosAdmin.obtenerTodosEventos();
		return eventos;
	}

	public void setEventoSelected(Evento eventoSelected) {
		this.eventoSelected = eventoSelected;
	}

	public Evento getEventoSelected() {
		return eventoSelected;
	}

	public void setEventosPublic(ArrayList<Evento> eventosPublic) {
		this.eventosPublic = eventosPublic;
	}

	public void setFechaInicioApuesta(GregorianCalendar fechaInicioApuesta) {
		this.fechaInicioApuesta = fechaInicioApuesta;
	}

	public GregorianCalendar getFechaInicioApuesta() {
		return fechaInicioApuesta;
	}

	public void setFechaFinApuesta(GregorianCalendar fechaFinApuesta) {
		this.fechaFinApuesta = fechaFinApuesta;
	}

	public GregorianCalendar getFechaFinApuesta() {
		return fechaFinApuesta;
	}

	public void setComb_combinacion(List<TipoEvento> comb_combinacion) {
		this.comb_combinacion = comb_combinacion;
	}

	public List<TipoEvento> getComb_combinacion() {
		return comb_combinacion;
	}

	public void setComb_TipoEventosNoPagos(
			List<TipoEvento> comb_TipoEventosNoPagos) {
		this.comb_TipoEventosNoPagos = comb_TipoEventosNoPagos;
	}

	public List<TipoEvento> getComb_TipoEventosNoPagos() {
		return comb_TipoEventosNoPagos;
	}

	public void setComb_selectedTE(TipoEvento comb_selectedTE) {
		this.comb_selectedTE = comb_selectedTE;
	}

	public TipoEvento getComb_selectedTE() {
		return comb_selectedTE;
	}

	public String getComb_desc() {
		return comb_desc;
	}

	public void setPc_combinaciones(List<Combinacion> pc_combinaciones) {
		this.pc_combinaciones = pc_combinaciones;
	}

	public List<Combinacion> getPc_combinaciones() {
		return pc_combinaciones;
	}

	public void setPc_selectedComb(Combinacion pc_selectedComb) {
		this.pc_selectedComb = pc_selectedComb;
	}

	public Combinacion getPc_selectedComb() {
		return pc_selectedComb;
	}

	public void setComb_desc(String comb_desc) {
		this.comb_desc = comb_desc;
	}

	public GestionUsuario getGuser() {
		return guser;
	}

	public void setGuser(GestionUsuario guser) {
		this.guser = guser;
	}

	public String getComb_nombre() {
		return comb_nombre;
	}

	public void setComb_nombre(String comb_nombre) {
		this.comb_nombre = comb_nombre;
	}

	public void setUr_listaResult(List<TipoEvento> ur_listaResult) {
		this.ur_listaResult = ur_listaResult;
	}

	public List<TipoEvento> getUr_listaResult() {
		return ur_listaResult;
	}

}
