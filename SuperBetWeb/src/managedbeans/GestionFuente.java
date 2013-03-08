package managedbeans;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionListener;

import org.apache.http.util.LangUtils;

import com.google.gdata.client.Query;
import com.google.gdata.client.youtube.YouTubeQuery;
import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.Category;
import com.google.gdata.data.dublincore.Language;
import com.google.gdata.data.extensions.Country;
import com.google.gdata.data.extensions.Rating;
import com.google.gdata.data.geo.impl.GeoRssWhere;
import com.google.gdata.data.media.mediarss.MediaKeywords;
import com.google.gdata.data.media.mediarss.MediaPlayer;
import com.google.gdata.data.media.mediarss.MediaThumbnail;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.data.youtube.YouTubeMediaContent;
import com.google.gdata.data.youtube.YouTubeMediaGroup;
import com.google.gdata.data.youtube.YouTubeNamespace;
import com.google.gdata.data.youtube.YtStatistics;
import com.google.gdata.util.ServiceException;

import utiles.Constantes;
import utiles.Multimedia;
import dominio.Evento;
import dominio.Fuente;
import negocio.eventos.interfaces.IEventosAdmin;
import negocio.eventos.interfaces.IEventosUsuario;
import negocio.fuentes.interfaces.IFuentes;
import negocio.utiles.ItemNoticia;

@ManagedBean(name="gestionFuente")
@SessionScoped
public class GestionFuente {
	
	// altaFuente
	private String nombreFuente;
	private String urlFuente;
	public static final String USER_TOKEN = "AI39si4yF1AzFO9ZnyvEvxLXHDCYFGvOpVJWbIYzHV3zyvKWioCm0QOUl_WkTCI0mS485K1ijP4ARIew8-FsUfp2vQsq2rTIxg";
	public static final String YOU_TUBE_URL = "http://gdata.youtube.com/feeds/api/videos";
	
	// obtener noticias de portada
	private List<ItemNoticia> noticiasPortada;
	private List<ItemNoticia> noticiasPortadaAux;
	private String criterioBusqueda;
	
	// videos
	private List<Multimedia> videosDestacados;
	private String urlNuevoVideoDestacado;
	private String nombreNuevoVideoDestacado;
	private Multimedia videoSelected;
	
	@EJB(lookup="java:global/SuperBetNegocio/ControladorFuentes!negocio.fuentes.interfaces.IFuentes")
	private IFuentes fuentesAdmin;
	
	@EJB(lookup = "java:global/SuperBetNegocio/ControladorEventos!negocio.eventos.interfaces.IEventosAdmin")
	private IEventosAdmin eventosAdmin;
		
	@ManagedProperty(value="#{coleccionesBean}")
	private ColeccionesBean coleccionesBean;
	
	public void setColeccionesBean(ColeccionesBean coleccionesBean) {
		this.coleccionesBean = coleccionesBean;
	}

	@PostConstruct
	public void crearColecciones () {
		noticiasPortadaAux = new ArrayList<ItemNoticia> ();
		List<Fuente> fuentes = fuentesAdmin.obtenerTodasFuentes();
		noticiasPortadaAux.addAll(fuentesAdmin.obtenerNoticias(fuentes,Constantes.NUM_NOTICIAS_POR_FUENTE));
		noticiasPortada = noticiasPortadaAux;
		
	}
		
	/*
	 * Acciones
	 */
	
	public String altaFuente () {
		try {
			Fuente fuente = new Fuente();
			fuente.setNombre(nombreFuente);
			fuente.setUrl(urlFuente);
			fuentesAdmin.altaFuente(fuente);			
			FacesMessage message = new FacesMessage("Fuente agregada exitosamente");
			FacesContext.getCurrentInstance().addMessage("message", message);
		} catch (Exception ex) {
			FacesMessage message = new FacesMessage("No pudo darse de alta a la fuente");
			FacesContext.getCurrentInstance().addMessage("message", message);
		}
		return null;
	}
	
	public String buscarNoticias () {
		noticiasPortada = new ArrayList<ItemNoticia>();
		for (ItemNoticia in : noticiasPortadaAux) {
			if (contieneTokens (in.getContenido(),criterioBusqueda)) {
				noticiasPortada.add(in);
			}
		}
		if ((noticiasPortada == null) || (noticiasPortada.size() == 0)) {
			FacesMessage message = new FacesMessage("No hay noticias disponibles para ese criterio de b�squeda");
			FacesContext.getCurrentInstance().addMessage("message", message);
			noticiasPortada = noticiasPortadaAux;
		} 
		return null;
	}
	
	public String altaVideoDestacado () {
		Multimedia mmedia = new Multimedia();
		mmedia.setType(Constantes.MMEDIA_VIDEO);
		mmedia.setUrl(urlNuevoVideoDestacado);
		mmedia.setNombre(nombreNuevoVideoDestacado);
		return null;
	}
	
	private boolean contieneTokens (String str1, String str2) {
		String [] tokens = str2.split(" ");
		for (String str : tokens) {
			if (str1.toLowerCase().contains(str.toLowerCase())) return true;
		}
		return false;
	}
	
	/*
	 * Setters y getters
	 */

	public void setNombreFuente(String nombreFuente) {
		this.nombreFuente = nombreFuente;
	}
	
	public String getNombreFuente() {
		return nombreFuente;
	}

	public void setUrlFuente(String urlFuente) {
		this.urlFuente = urlFuente;
	}

	public String getUrlFuente() {
		return urlFuente;
	}	
	
	/*
	 * Metodos privados
	 */

	public void setNoticiasPortada(List<ItemNoticia> noticiasPortada) {
		this.noticiasPortada = noticiasPortada;
	}

	public List<ItemNoticia> getNoticiasPortada() {
		return noticiasPortada;
	}

	public void setCriterioBusqueda(String criterioBusqueda) {
		this.criterioBusqueda = criterioBusqueda;
	}

	public String getCriterioBusqueda() {
		return criterioBusqueda;
	}

	public void setVideosDestacados(List<Multimedia> videosDestacados) {
		this.videosDestacados = videosDestacados;
	}

	public List<Multimedia> getVideosDestacados() {
		
		videosDestacados = new ArrayList<Multimedia>();
		
		try {
		
			List<Evento> eventosMasImportantes = eventosAdmin.obtenerEventosImportantes(Constantes.MAX_NUM_VIDEOS);
			YouTubeService service = new YouTubeService("",USER_TOKEN);
			
			if ((eventosMasImportantes != null) && (eventosMasImportantes.size() > 0)) {
			
				for (Evento ev : eventosMasImportantes) {
				
					YouTubeQuery query;   
			
					query = new YouTubeQuery(new URL(YOU_TUBE_URL));
				    query.setSafeSearch(YouTubeQuery.SafeSearch.NONE);
				    
				    query.setMaxResults(Constantes.MAX_VIDEOS_POR_EVENTO);

				    // creo el filtro
				    
				    String term = ev.getCompeticion().getNombre() + " " + ev.getNombre();
				    
				    query.setFullTextQuery(term);
				    VideoFeed videoFeed = service.query(query, VideoFeed.class);
				    
				    String [] keywords = term.split("[ -,\\.]");
				    
				    if ((videoFeed != null) && (videoFeed.getEntries() != null) && 
				    	(videoFeed.getEntries().size() > 0)) {
				    	
				    	VideoEntry vEntry = null;
				    	
				    	// buscar los videos que tienen la mayor cantidad de tokens en su titulo
				    	int maxCantCoincidencias = 0;
				    	for (VideoEntry ve : videoFeed.getEntries()) {
				    		int i = 0; 
				    		for (int j = 0; j < keywords.length; j++) {
				    			if (ve.getTitle().getPlainText().contains(keywords[j])) {
				    				i++;
				    			}
				    		}
				    		if (i > maxCantCoincidencias) {
				    			maxCantCoincidencias = i;
				    			vEntry = ve;
				    		}
				    	}
				    	
				    	if (vEntry != null) {
					    	YouTubeMediaGroup mediaGroup = vEntry.getMediaGroup();
					    	Multimedia mmedia = new Multimedia();
					    	mmedia.setNombre(vEntry.getTitle().getPlainText());
					    	mmedia.setType(utiles.Constantes.MMEDIA_VIDEO);
					    	mmedia.setHtml(mediaGroup.getYouTubeContents().get(0).getUrl());
					    	mmedia.setUrlThumb(mediaGroup.getThumbnails().get(0).getUrl());
					    	videosDestacados.add(mmedia);
				    	}
				    }
	
				}
		    
			}
		    	// Impresion del resto de los valores 
		    	
		    	/*
		    	System.out.println("Title: " + videoEntry.getTitle().getPlainText());
		    	System.out.println("Description: " + mediaGroup.getDescription().getPlainTextContent());

		    	MediaPlayer mediaPlayer = mediaGroup.getPlayer();
		    	System.out.println("Web Player URL: " + mediaPlayer.getUrl());
		    	MediaKeywords keywords = mediaGroup.getKeywords();
		    	System.out.print("Keywords: ");
		    	for(String keyword : keywords.getKeywords()) {
		    	  System.out.print(keyword + ",");
		    	}
		    	
		    	Rating rating = videoEntry.getRating();
		    	if(rating != null) {
		    	  System.out.println("Average rating: " + rating.getAverage());
		    	}
		    	YtStatistics stats = videoEntry.getStatistics();
		    	if(stats != null ) {
		    	  System.out.println("View count: " + stats.getViewCount());
		    	}
		    	System.out.println();
		    	System.out.println("\tThumbnails:");
		    	for(MediaThumbnail mediaThumbnail : mediaGroup.getThumbnails()) {
		    	  System.out.println("\t\tThumbnail URL: " + mediaThumbnail.getUrl());
		    	  System.out.println("\t\tThumbnail Time Index: " +
		    	      mediaThumbnail.getTime());
		    	  System.out.println();
		    	}
		    	System.out.println("\tMedia:");
		    	for(YouTubeMediaContent mediaContent : mediaGroup.getYouTubeContents()) {
		    	  System.out.println("\t\tMedia Location: "+ mediaContent.getUrl());
		    	  System.out.println("\t\tMedia Type: "+ mediaContent.getType());
		    	  System.out.println("\t\tDuration: " + mediaContent.getDuration());
		    	  System.out.println();
		    	}
		    	
		    	System.out.println("------------------STEP:" + i);
		    	*/

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return videosDestacados;
	}
	
	public String videoSeleccionado() {
		return null;
	}
	
	public void videoSeleccionadoListener (ActionListener ae) {
		System.out.println("---------------VIDEO SELECTED titulo!!!!! - " + videoSelected.getNombre());
	}

	public void setUrlNuevoVideoDestacado(String urlNuevoVideoDestacado) {
		this.urlNuevoVideoDestacado = urlNuevoVideoDestacado;
	}

	public String getUrlNuevoVideoDestacado() {
		return urlNuevoVideoDestacado;
	}

	public void setNombreNuevoVideoDestacado(String nombreNuevoVideoDestacado) {
		this.nombreNuevoVideoDestacado = nombreNuevoVideoDestacado;
	}

	public String getNombreNuevoVideoDestacado() {
		return nombreNuevoVideoDestacado;
	}

	public void setVideoSelected(Multimedia videoSelected) {
		this.videoSelected = videoSelected;
	}

	public Multimedia getVideoSelected() {
		return videoSelected;
	}

}
