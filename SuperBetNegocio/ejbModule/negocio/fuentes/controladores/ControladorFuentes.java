package negocio.fuentes.controladores;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dominio.Fuente;
import persistencia.interfaces.FuenteDAO;

import negocio.fuentes.interfaces.IFuentes;
import negocio.utiles.ItemNoticia;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ControladorFuentes implements IFuentes {


	@EJB(lookup="java:global/SuperBetPersistencia/FuenteDAOImpl!persistencia.interfaces.FuenteDAO")
	private FuenteDAO fuenteDAO;

	
	@Override
	public void altaFuente(Fuente fuente) {
		fuenteDAO.altaFuente(fuente);
	}


	@Override
	public List<Fuente> obtenerTodasFuentes() {
		return fuenteDAO.obtenerTodasFuentes();
	}


	@Override
	public List<ItemNoticia> obtenerNoticias (List<Fuente> fuentes, int cantNoticiasInic) {
		List<ItemNoticia> itemsNoticia = new ArrayList<ItemNoticia> ();
		for (Fuente f : fuentes) {
			itemsNoticia.addAll(obtenerNoticias(f,cantNoticiasInic));
		}
		return itemsNoticia;
	}
	
	private List<ItemNoticia> obtenerNoticias (Fuente f, int cantNoticias) {
		List<ItemNoticia> noticias = null;

		try {
			
			noticias = new ArrayList<ItemNoticia> ();
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			URL u = new URL(f.getUrl());
			Document doc = builder.parse(u.openStream());
			NodeList nodes = doc.getElementsByTagName("item");
			
			for (int i = 0; i < nodes.getLength(); i++) {
				Element element = (Element) nodes.item(i);
				ItemNoticia noticia = new ItemNoticia();
				noticia.setNombreFuente(f.getNombre());
				noticia.setTitulo(getElementValue(element,"title"));
				noticia.setContenido(getElementValue(element,"description"));
				noticia.setFechaPublicacion(getElementValue(element,"pubDate"));
				noticia.setLink(getElementValue(element,"link"));
				noticias.add(noticia);
			}
						
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return noticias;
	}
	
	private String getElementValue(Element parent,String label) {		
		String result = getCharacterDataFromElement((Element)parent.getElementsByTagName(label).item(0));		
		String [] specialChar = {"&aacute;", "&eacute;", "&iacute;", "&oacute;", "&uacute;", "&Aacute;", "&Eacute;",
				"&Iacute;", "&Oacute;", "&Uacute;", "&uuml;", "&Uuml;", "&ntilde;", "&Ntilde;", "&quest;", "&#161;", "&ordf;", 
				"&nbsp;"};
		String [] asciiChar = {"á", "é", "í", "ó", "ú", "Á", "É", "Í", "Ó", "Ú", "ü", "Ü", "ñ" , "Ñ", "¿", "¡", "°", " "};
		for (int i = 0; i < specialChar.length; i++) {
			result = result.replaceAll(specialChar[i], asciiChar[i]);  
		}
		result = result.toString().replaceAll("\\<.*?>","");
		return result;
	} 
	
	private String getCharacterDataFromElement(Element e) {
		try {
			Node child = e.getFirstChild();
			if(child instanceof CharacterData) {
				CharacterData cd = (CharacterData) child;
				return cd.getData();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

}
