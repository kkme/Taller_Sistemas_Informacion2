package managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import presentacion.DocumentTree;
import utiles.Constantes;

import dominio.Evento;
import dominio.TipoEvento;
import negocio.eventos.interfaces.IEventosAdmin;

@ManagedBean(name = "documentsTreeController")
@SessionScoped
public class DocumentTreeController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB(lookup = "java:global/SuperBetNegocio/ControladorEventos!negocio.eventos.interfaces.IEventosAdmin")
	private IEventosAdmin eventosAdmin;
	
	public void setEventosAdmin(IEventosAdmin eventosAdmin) {
		this.eventosAdmin = eventosAdmin;
	}

	@ManagedProperty(value = "#{usuarioMB}")
	private GestionUsuario usuarioBean;

	public void setUsuarioBean(GestionUsuario usuarioBean) {
		this.usuarioBean = usuarioBean;
	}

	private TreeNode root;
	private TreeNode rootLocal;
	private TreeNode rootResultados;
	private Evento evento;
	
	private List<TipoEvento> tipoEventos;

	private TreeNode documentoSeleccionado;

	public DocumentTreeController() {
	}

	@PostConstruct
	public void construirTree() {
		root = new DefaultTreeNode("root", null);
		rootLocal = new DefaultTreeNode("rootLocal", null);
		rootResultados = new DefaultTreeNode("rootResultados", null);

		List<Evento> eventos = eventosAdmin.obtenerEventosImportantes(10);
		Map<String, Map<String, List<Evento>>> data = new HashMap<String, Map<String, List<Evento>>>();
		Map<String, List<String>> categories = new HashMap<String, List<String>>();
		List<String> paises = new ArrayList<String>();

		// La primer recorrida es para obtener primero los eventos locales
		for (Evento evento : eventos) {
			String pais = evento.getCompeticion().getCountry().getName();
			String competicion = evento.getCompeticion().getNombre();
			if (pais.equals(usuarioBean.getLogedUser().getCountry())) {
				if (!paises.contains(pais)) {
					paises.add(pais);
					categories.put(pais, new ArrayList<String>());
					data.put(pais, new HashMap<String, List<Evento>>());
				}

				if (!categories.get(pais).contains(competicion)) {
					categories.get(pais).add(competicion);
					data.get(pais).put(competicion, new ArrayList<Evento>());
				}

				data.get(pais).get(competicion).add(evento);
			}
		}

		for (String pais : paises) {
			for (String competicion : categories.get(pais)) {
				TreeNode categoriaNode = new DefaultTreeNode(new DocumentTree(
						competicion, null, null), rootLocal);
				for (Evento evento : data.get(pais).get(competicion)) {
					@SuppressWarnings("unused")
					TreeNode otherNodes = new DefaultTreeNode(new DocumentTree(
							null, evento, null), categoriaNode);
				}
			}
		}

		List<Evento> eventosInternacionales = eventosAdmin
				.obtenerEventosImportantes(10);
		Map<String, Map<String, List<Evento>>> dataInternacionales = new HashMap<String, Map<String, List<Evento>>>();
		Map<String, List<String>> categoriesInternacionales = new HashMap<String, List<String>>();
		List<String> paisesInternacionales = new ArrayList<String>();

		// Obtengo el resto de los eventos
		for (Evento evento : eventosInternacionales) {
			String pais = evento.getCompeticion().getCountry().getName();
			String competicion = evento.getCompeticion().getNombre();
			if (!pais.equals(usuarioBean.getLogedUser().getCountry())) {
				if (!paisesInternacionales.contains(pais)) {
					paisesInternacionales.add(pais);
					categoriesInternacionales
							.put(pais, new ArrayList<String>());
					dataInternacionales.put(pais,
							new HashMap<String, List<Evento>>());
				}

				if (!categoriesInternacionales.get(pais).contains(competicion)) {
					categoriesInternacionales.get(pais).add(competicion);
					dataInternacionales.get(pais).put(competicion,
							new ArrayList<Evento>());
				}

				dataInternacionales.get(pais).get(competicion).add(evento);
			}
		}

		for (String pais : paisesInternacionales) {
			TreeNode paisNode = new DefaultTreeNode(new DocumentTree(pais,
					null, null), root);
			for (String competicion : categoriesInternacionales.get(pais)) {
				TreeNode categoriaNode = new DefaultTreeNode(new DocumentTree(
						competicion, null, null), paisNode);
				for (Evento evento : dataInternacionales.get(pais).get(
						competicion)) {
					@SuppressWarnings("unused")
					TreeNode otherNodes = new DefaultTreeNode(new DocumentTree(
							null, evento, null), categoriaNode);
				}
			}
		}

		List<TipoEvento> eventosResultados = eventosAdmin.getResultadosEventosRecientes(usuarioBean.getLogedUser());
		Map<String, Map<String, List<TipoEvento>>> dataResultados = new HashMap<String, Map<String, List<TipoEvento>>>();
		Map<String, List<String>> categoriesResultados = new HashMap<String, List<String>>();
		List<String> paisesResultados = new ArrayList<String>();

		// Obtengo el resto de los eventos
		for (TipoEvento tipoEvento : eventosResultados) {
			String pais = tipoEvento.getEvento().getCompeticion().getCountry()
					.getName();
			String competicion = tipoEvento.getEvento().getCompeticion()
					.getNombre();

			if (!paisesResultados.contains(pais)) {
				paisesResultados.add(pais);
				categoriesResultados.put(pais, new ArrayList<String>());
				dataResultados.put(pais,
						new HashMap<String, List<TipoEvento>>());
			}

			if (!categoriesResultados.get(pais).contains(competicion)) {
				categoriesResultados.get(pais).add(competicion);
				dataResultados.get(pais).put(competicion,
						new ArrayList<TipoEvento>());
			}

			dataResultados.get(pais).get(competicion).add(tipoEvento);
		}

		for (String pais : paisesResultados) {
			TreeNode paisNode = new DefaultTreeNode(new DocumentTree(pais,
					null, null), rootResultados);
			for (String competicion : categoriesResultados.get(pais)) {
				TreeNode categoriaNode = new DefaultTreeNode(new DocumentTree(
						competicion, null, null), paisNode);
				for (TipoEvento tipoEvento : dataResultados.get(pais).get(
						competicion)) {
					@SuppressWarnings("unused")
					TreeNode otherNodes = new DefaultTreeNode(new DocumentTree(
							null, null, tipoEvento), categoriaNode);
				}
			}
		}

	}
	
	public String onLupaClick(){
		return Constantes.DOMINIO()+":"+Constantes.PUERTO + "/SuperBetWeb/views/public/eventos/evento.xhtml";
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public TreeNode getRoot() {
		return root;
	}

	public TreeNode getRootLocal() {
		return rootLocal;
	}

	public void setRootLocal(TreeNode rootLocal) {
		this.rootLocal = rootLocal;
	}

	public void setRootResultados(TreeNode rootResultados) {
		this.rootResultados = rootResultados;
	}

	public TreeNode getRootResultados() {
		return rootResultados;
	}

	public void setDocumentoSeleccionado(TreeNode documentoSeleccionado) {
		if(documentoSeleccionado != null){
			evento = ((DocumentTree)documentoSeleccionado.getData()).getEvento();
			tipoEventos = eventosAdmin.getResultadosEvento(evento.getId());
		}
		this.documentoSeleccionado = documentoSeleccionado;
	}

	public TreeNode getDocumentoSeleccionado() {
		return documentoSeleccionado;
	}

	public void setTipoEventos(List<TipoEvento> tipoEventos) {
		this.tipoEventos = tipoEventos;
	}

	public List<TipoEvento> getTipoEventos() {
		return tipoEventos;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Evento getEvento() {
		return evento;
	}

}
