package negocio.fuentes.interfaces;

import java.util.List;

import javax.ejb.Local;

import negocio.utiles.ItemNoticia;
import dominio.Fuente;

@Local
public interface IFuentes {

	public void altaFuente(Fuente fuente);

	public List<Fuente> obtenerTodasFuentes();

	public List<ItemNoticia> obtenerNoticias(List<Fuente> fuentes, int cantNoticiasInicCompeticion);

}
