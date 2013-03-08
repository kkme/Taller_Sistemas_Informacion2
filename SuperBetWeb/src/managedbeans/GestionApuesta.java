package managedbeans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import utiles.MessagePrinter;

import dominio.Apuesta;
import dominio.Combinacion;
import dominio.Competicion;
import dominio.Pais;
import dominio.Resultado;
import dominio.TipoApuesta;
import dominio.TipoEvento;

import negocio.apuestas.interfaces.IApuestasUsuario;
import negocio.eventos.interfaces.IEventosUsuario;
import negocio.utiles.interfaces.Colecciones;

@ManagedBean(name = "gestionApuesta")
@SessionScoped
public class GestionApuesta {

	@EJB(lookup = "java:global/SuperBetNegocio/ControladorApuestas!negocio.apuestas.interfaces.IApuestasUsuario")
	private IApuestasUsuario apuestasUsuario;

	public void setApuestasUsuario(IApuestasUsuario apuestasUsuario) {
		this.apuestasUsuario = apuestasUsuario;
	}

	@EJB(lookup = "java:global/SuperBetNegocio/ControladorEventos!negocio.eventos.interfaces.IEventosUsuario")
	private IEventosUsuario eventosUsuario;

	public void setEventosUsuario(IEventosUsuario eventosUsuario) {
		this.eventosUsuario = eventosUsuario;
	}

	@EJB(lookup = "java:global/SuperBetNegocio/ColeccionesImpl!negocio.utiles.interfaces.Colecciones")
	private Colecciones colecciones;

	public void setColecciones(Colecciones colecciones) {
		this.colecciones = colecciones;
	}

	@ManagedProperty("#{usuarioMB}")
	private GestionUsuario gestionUsuario;

	public void setGestionUsuario(GestionUsuario gestionUsuario) {
		this.gestionUsuario = gestionUsuario;
	}

	private ArrayList<TipoApuesta> tiposApuesta;

	private TreeNode rootFutbol = new DefaultTreeNode();
	private TreeNode rootBasquetbol = new DefaultTreeNode();
	private TreeNode rootAutomovilismo = new DefaultTreeNode();

	private TreeNode selectedNode;

	private HtmlPanelGrid gridElems = new HtmlPanelGrid();

	private List<TipoEvento> calendarioTipoEventos = new ArrayList<TipoEvento>();
	private Map<Integer, TipoEvento> carritoTipoEvento = new HashMap<Integer, TipoEvento>();
	private List<Resultado> carritoResultados = new ArrayList<Resultado>();

	private String selectedTipoApuesta = "";
	private String selectedPais = "";
	private String selectedCompetencia = "";
	private TipoEvento selectedTipoEvento;
	private Resultado selectedResultado;

	private String formaApuesta = "Apuesta Simple";
	private String deporte = "Futbol";
	
	private boolean hayresultados = true;

	private double cuotaTotal = 1;
	private double montoApuesta;
	private double gananciaPosible;

	public String getSelectedTipoApuesta() {
		return selectedTipoApuesta;
	}

	public void setSelectedTipoApuesta(String selectedTipoApuesta) {
		this.selectedTipoApuesta = selectedTipoApuesta;
	}

	public void setCalendarioTipoEventos(List<TipoEvento> calendarioTipoEventos) {
		this.calendarioTipoEventos = calendarioTipoEventos;
	}

	public List<TipoEvento> getCalendarioTipoEventos() {
		return calendarioTipoEventos;
	}

	public TreeNode getRootFutbol() {
		if (rootFutbol == null) {
			renderAccordeonFilter();
		}
		return rootFutbol;
	}

	public void setRootFutbol(TreeNode rootFutbol) {
		this.rootFutbol = rootFutbol;
	}

	public void setRootBasquetbol(TreeNode rootBasquetbol) {
		this.rootBasquetbol = rootBasquetbol;
	}

	public TreeNode getRootBasquetbol() {
		if (rootBasquetbol == null) {
			renderAccordeonFilter();
		}
		return rootBasquetbol;
	}

	public void setRootAutomovilismo(TreeNode rootAutomovilismo) {
		this.rootAutomovilismo = rootAutomovilismo;
	}

	public TreeNode getRootAutomovilismo() {
		if (rootAutomovilismo == null) {
			renderAccordeonFilter();
		}
		return rootAutomovilismo;
	}

	public HtmlPanelGrid getGridElems() {
		FacesContext.getCurrentInstance().renderResponse();
		return gridElems;
	}

	public void setGridElems(HtmlPanelGrid gridElems) {
		this.gridElems = gridElems;
	}

	public GestionApuesta() {
		System.out.println("consteoi");
		// generateDummyData();
		// renderAccordeonFilter();
	}

	@PostConstruct
	@SuppressWarnings("unused")
	private void renderAccordeonFilter() {
		List<Pais> lpaises = colecciones.getPaises();

		rootBasquetbol = new DefaultTreeNode("Root", null);
		for (Pais pais : lpaises) {
			TreeNode node = new DefaultTreeNode(pais.getName(), rootBasquetbol);
			for (Competicion competicion : pais.getContest()) {
				if (competicion.getDeporte().getName().equals("Basquetbol")) {
					TreeNode nodeSon = new DefaultTreeNode(
							competicion.getNombre(), node);
				}
			}
		}
		
		rootAutomovilismo = new DefaultTreeNode("Root", null);
		for (Pais pais : lpaises) {
			TreeNode node = new DefaultTreeNode(pais.getName(), rootAutomovilismo);
			for (Competicion competicion : pais.getContest()) {
				if (competicion.getDeporte().getName().equals("Automovilismo")) {
					TreeNode nodeSon = new DefaultTreeNode(
							competicion.getNombre(), node);
				}
			}
		}

		rootFutbol = new DefaultTreeNode("Root", null);
		for (Pais pais : lpaises) {
			TreeNode node = new DefaultTreeNode(pais.getName(), rootFutbol);
			for (Competicion competicion : pais.getContest()) {
				if (competicion.getDeporte().getName().equals("Futbol")) {
					TreeNode nodeSon = new DefaultTreeNode(
							competicion.getNombre(), node);
				}
			}
		}
	}


	public void onSelectTipoApuesta(ValueChangeEvent changeEvent) {
		System.out.println(changeEvent.getOldValue() + " cambia por "
				+ changeEvent.getNewValue());
		selectedTipoApuesta = (String) changeEvent.getNewValue();
		calendarioTipoEventos.clear();
		calendarioTipoEventos.addAll(eventosUsuario
				.obtenerTipoEventosCondicion(selectedTipoApuesta, deporte,
						selectedPais, selectedCompetencia));
		hayresultados = calendarioTipoEventos.size() != 0;
		System.out.println("onSelectTipoApuesta");
		System.out.println(selectedTipoApuesta);
		System.out.println(selectedPais);
		System.out.println(selectedCompetencia);
	}

	public void onNodeSelect(NodeSelectEvent event) {
		if (event.getTreeNode() != null) {
			TreeNode padre = event.getTreeNode().getParent();

			if (padre != null) {
				TreeNode root = null;
				if (deporte.equals("Basquetbol")) {
					root = rootBasquetbol;
				} else if (deporte.equals("Automovilismo")) {
					root = rootAutomovilismo;
				} else {
					root = rootFutbol;
				}
				if (padre.equals(root)) {
					selectedPais = event.getTreeNode().getData().toString();
					selectedCompetencia = "";
				} else {
					selectedPais = padre.getData().toString();
					selectedCompetencia = event.getTreeNode().getData()
							.toString();
				}
			}
			calendarioTipoEventos.clear();
			calendarioTipoEventos.addAll(eventosUsuario
					.obtenerTipoEventosCondicion(selectedTipoApuesta, deporte,
							selectedPais, selectedCompetencia));
			hayresultados = calendarioTipoEventos.size() != 0;
			System.out.println("cant tipo eventos"
					+ eventosUsuario.obtenerTipoEventosCondicion(
							selectedTipoApuesta, deporte, selectedPais,
							selectedCompetencia).size());
			FacesContext.getCurrentInstance().renderResponse();
		}
	}

	public String onSelectResult() {
		if ((selectedTipoEvento != null) && (selectedResultado != null)) {
			System.out.println("onSelectResult");
			System.out.println(selectedTipoEvento.getEvento().getNombre()
					+ "##" + selectedResultado.getDescription());
			System.out
					.println("*********************************************************************");
			List<Resultado> aux = new ArrayList<Resultado>();
			aux.add(selectedResultado);
			aux.addAll(carritoResultados);
			carritoResultados = new ArrayList<Resultado>();
			carritoResultados.addAll(aux);
			for (Resultado resultado : carritoResultados) {
				System.out.println(resultado.getCuota());
			}
			carritoTipoEvento
					.put(selectedResultado.getId(), selectedTipoEvento);
			System.out
					.println("*********************************************************************");
			if (carritoResultados.size() > 1) {
				formaApuesta = "Apuesta Combinada";
			} else {
				formaApuesta = "Apuesta Simple";
			}
			System.out.println("selectedResultado.getCuota():"
					+ selectedResultado.getCuota());
			cuotaTotal = cuotaTotal * selectedResultado.getCuota();
			System.out.println("cuotaTotal:" + cuotaTotal);
		}
		FacesContext.getCurrentInstance().renderResponse();
		return null;
	}

	public String onSelectCloseResult() {
		if ((selectedTipoEvento != null) && (selectedResultado != null)) {
			System.out.println("onSelectCloseResult");
			System.out.println(selectedTipoEvento.getEvento().getNombre()
					+ "##" + selectedResultado.getDescription());
			carritoResultados.remove(selectedResultado);
			carritoTipoEvento.remove(selectedResultado.getId());
			if (carritoResultados.size() > 1) {
				formaApuesta = "Apuesta Combinada";
			} else {
				formaApuesta = "Apuesta Simple";
			}
			System.out.println("selectedResultado.getCuota():"
					+ selectedResultado.getCuota());
			cuotaTotal = cuotaTotal / selectedResultado.getCuota();
			System.out.println("cuotaTotal:" + cuotaTotal);
		}
		FacesContext.getCurrentInstance().renderResponse();
		return null;
	}

	public String onKeyUpCalculateGananciaPosible() {
		gananciaPosible = cuotaTotal * montoApuesta;
		return null;
	}

	public void onTabChange(TabChangeEvent event) {
		deporte = event.getTab().getTitle();
	}

	public String onClickApostar() {
		try {
			
			//Controlo monto mayor a cero
			if(gestionUsuario.getLogedUser().getMonto()<montoApuesta){
				MessagePrinter
				.showInformationMessage("Saldo insuficiente");
				return null;
			};
			
			
			System.out.println("onClickApostar");
			Apuesta ap = new Apuesta();
			ap.setAmount(montoApuesta);
			ap.setFechaApuesta(Calendar.getInstance());
			ap.setFuePaga(false);

			Combinacion combinacion = null;
			if (carritoResultados.size() > 1) {
				combinacion = eventosUsuario
						.obtenerCombinacionTiposEvento(carritoTipoEvento
								.values());
				System.out.println("La combinada encontrada permite realizar apuestas sobre: ");
				for(TipoEvento te: combinacion.getTipo_eventos()){
					System.out.println(" { "+te.getEvento().getNombre() +"  "+ te.getTipoApuesta().getName()+"} ,");
				}
				gestionUsuario.getLogedUser().quitarMontoCuenta(montoApuesta);
				//para actulizar el monto del usuario logueado
				gestionUsuario.setLogedUser(gestionUsuario.updateUser(gestionUsuario.getLogedUser()));
				apuestasUsuario.altaApuestaCombinada(gestionUsuario.getLogedUser(), combinacion, carritoResultados, ap);
				
			} else {
				TipoEvento te = carritoTipoEvento.get(carritoResultados.get(0).getId());
				gestionUsuario.getLogedUser().quitarMontoCuenta(montoApuesta);
				//para actulizar el monto del usuario logueado
				gestionUsuario.setLogedUser(gestionUsuario.updateUser(gestionUsuario.getLogedUser()));				
				apuestasUsuario.altaApuesta(ap, combinacion, carritoResultados,
						te.getTipoApuesta(), gestionUsuario.getLogedUser());
			}
			MessagePrinter
					.showInformationMessage("Gracias por apostar en Superbet!");
			
		} catch (Exception e) {
			e.printStackTrace();
			MessagePrinter.showErrorMessage(e.getMessage());
		}finally{
			 limpiarCampos();
		}
		return "home";
	}

	public void limpiarCampos() {
		this.carritoResultados.clear();
		this.carritoTipoEvento.clear();
		this.calendarioTipoEventos.clear();
		hayresultados = false;
		if(tiposApuesta != null){
			selectedTipoApuesta = tiposApuesta.get(0).getName();
		}
		selectedPais = "";
		selectedCompetencia = "";
		selectedTipoEvento = new TipoEvento();
		selectedResultado = new Resultado();
		selectedTipoApuesta = tiposApuesta.get(0).getName();
		
		this.montoApuesta=0.0;
		this.gananciaPosible=0.0;
		//TODO: falta seguir limpiando
		
	}

	@PostConstruct
	public void crearColecciones() {
		setTiposApuesta((ArrayList<TipoApuesta>) colecciones.getTipoApuestas());
		TipoApuesta aBorrar = null;
		for (TipoApuesta tipoApuesta : tiposApuesta) {
			if(tipoApuesta.getName().equalsIgnoreCase("Combinada")){
				aBorrar = tipoApuesta;
			}
		}
		tiposApuesta.remove(aBorrar);
		selectedTipoApuesta = tiposApuesta.get(0).getName();
	}

	/*
	 * getters y setters
	 */

	public void setTiposApuesta(ArrayList<TipoApuesta> tiposApuesta) {
		this.tiposApuesta = tiposApuesta;
	}

	public ArrayList<TipoApuesta> getTiposApuesta() {
		return tiposApuesta;
	}

	public void setSelectedTipoEvento(TipoEvento selectedTipoEvento) {
		this.selectedTipoEvento = selectedTipoEvento;
	}

	public TipoEvento getSelectedTipoEvento() {
		return selectedTipoEvento;
	}

	public void setSelectedResultado(Resultado selectedResultado) {
		this.selectedResultado = selectedResultado;
	}

	public Resultado getSelectedResultado() {
		return selectedResultado;
	}

	public Map<Integer, TipoEvento> getCarritoTipoEvento() {
		return carritoTipoEvento;
	}

	public void setCarritoTipoEvento(Map<Integer, TipoEvento> carritoTipoEvento) {
		this.carritoTipoEvento = carritoTipoEvento;
	}

	public List<Resultado> getCarritoResultados() {
		return carritoResultados;
	}

	public void setCarritoResultados(List<Resultado> carritoResultados) {
		this.carritoResultados = carritoResultados;
	}

	public void setFormaApuesta(String formaApuesta) {
		this.formaApuesta = formaApuesta;
	}

	public String getFormaApuesta() {
		return formaApuesta;
	}

	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}

	public String getDeporte() {
		return deporte;
	}

	public void setHayresultados(boolean hayresultados) {
		this.hayresultados = hayresultados;
	}

	public boolean isHayresultados() {
		return hayresultados;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setCuotaTotal(double cuotaTotal) {
		this.cuotaTotal = cuotaTotal;
	}

	public double getCuotaTotal() {
		return cuotaTotal;
	}

	public void setMontoApuesta(double montoApuesta) {
		this.montoApuesta = montoApuesta;
	}

	public double getMontoApuesta() {
		return montoApuesta;
	}

	public void setGananciaPosible(double gananciaPosible) {
		this.gananciaPosible = gananciaPosible;
	}

	public double getGananciaPosible() {
		return gananciaPosible;
	}

	public String getSelectedPais() {
		return selectedPais;
	}

	public void setSelectedPais(String selectedPais) {
		this.selectedPais = selectedPais;
	}

	public String getSelectedCompetencia() {
		return selectedCompetencia;
	}

	public void setSelectedCompetencia(String selectedCompetencia) {
		this.selectedCompetencia = selectedCompetencia;
	}
	
	
	/********************APUESTAS EXTERNAS********************/
	
	private List<TipoEvento> ex_eventos= new ArrayList<TipoEvento>();
	private TipoEvento ex_selectedEvent;
	private List<Resultado> ex_resultadosPosibles;
	private Resultado ex_selectedResult;
	
	
	public void ex_cargarEventosExternos(ComponentSystemEvent event ){
		try{
		ex_eventos=this.apuestasUsuario.getEventosExternos();
		}catch(Exception e){
			//cargo datos dummy
			//actualmente ya trae datos dummy
		}
	}

	public void onRowSelect(SelectEvent event) {  
        FacesMessage msg = new FacesMessage("Car Selected", ((Resultado) event.getObject()).getDescription());  
  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
  
    public void onRowUnselect(UnselectEvent event) {  
        FacesMessage msg = new FacesMessage("Car Unselected", ((Resultado) event.getObject()).getDescription());  
  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	
	
	public void setEx_eventos(List<TipoEvento> ex_eventos) {
		this.ex_eventos = ex_eventos;
	}

	public List<TipoEvento> getEx_eventos() {
		return ex_eventos;
	}

	public void setEx_selectedEvent(TipoEvento event) {
		//TODO: esto supo�endo que ellos nos mandan todo el coso
		ex_resultadosPosibles= event.getResultados();
		this.ex_selectedEvent = event;
	}

	public TipoEvento getEx_selectedEvent() {
		return ex_selectedEvent;
	}

	public void setEx_selectedResult(Resultado ex_selectedResult) {
		this.ex_selectedResult = ex_selectedResult;
	}

	public Resultado getEx_selectedResult() {
		return ex_selectedResult;
	}

	public void setEx_resultadosPosibles(List<Resultado> ex_resultadosPosibles) {
		this.ex_resultadosPosibles = ex_resultadosPosibles;
	}

	public List<Resultado> getEx_resultadosPosibles() {
		return ex_resultadosPosibles;
	}
	
	/********************APUESTAS EXTERNAS********************/

}
